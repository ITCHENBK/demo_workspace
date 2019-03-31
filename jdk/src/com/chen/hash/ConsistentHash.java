package com.chen.hash;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.UUID;

public class ConsistentHash {


    public static int COUNT = 1000000;


    private static List<String> NODES = Arrays.asList("192.168.1.1:1100", "192.168.1.2:1100", "192.168.1.3:1100", "192.168.1.4:1100", "192.168.1.5:1100");

    private static List<List<String>> NEW_NODES = Arrays.asList(
            Arrays.asList("192.168.1.1:1100", "192.168.1.2:1100", "192.168.1.3:1100", "192.168.1.4:1100", "192.168.1.5:1100", "192.168.1.6:1100", "192.168.1.7:1100"),
            Arrays.asList("192.168.1.1:1100", "192.168.1.2:1100", "192.168.1.3:1100", "192.168.1.4:1100", "192.168.1.5:1100", "192.168.1.6:1100"),
            Arrays.asList("192.168.1.1:1100", "192.168.1.2:1100", "192.168.1.3:1100", "192.168.1.4:1100"),
            Arrays.asList("192.168.1.1:1100", "192.168.1.2:1100", "192.168.1.3:1100")
    );

    private static int VIRTUAL = 10;

    public static int NODE1 = 5;

    public static List<Integer> NEW_NODE_COUNT = Arrays.asList(3, 4, 6, 7);

    public static String getNode(TreeMap<Integer, String> nodeMap, Integer hash) {

        SortedMap<Integer, String> sortedMap = nodeMap.tailMap(hash);
        if (sortedMap.isEmpty()) {
            return nodeMap.firstEntry().getValue();
        } else {
            return sortedMap.get(sortedMap.firstKey());
        }
    }

    public static int hash(String str) {
        final int p = 16777619;
        int hash = (int) 2166136261L;
        for (int i = 0; i < str.length(); i++)
            hash = (hash ^ str.charAt(i)) * p;
        hash += hash << 13;
        hash ^= hash >> 7;
        hash += hash << 3;
        hash ^= hash >> 17;
        hash += hash << 5;

        if (hash < 0)
            hash = Math.abs(hash);
        return hash;

    }


    @Test
    public void test() throws Exception {
        Map<Integer, List<String>> map = new HashMap<>();

        for (int i = 0; i < COUNT; i++) {
            String s = i + "";
            int mod = (hash(s)) % NODE1;
            if (map.containsKey(mod)) {
                map.get(mod).add(s);
            } else {
                List<String> l = new ArrayList<>();
                l.add(s);
                map.put(mod, l);
            }
        }
        for (Integer c : NEW_NODE_COUNT) {
            int i = 0;
            for (Map.Entry<Integer, List<String>> entry : map.entrySet()) {
                Integer key = entry.getKey();
                for (String s : entry.getValue()) {
                    if ((hash(s) % c) != key) {
                        i++;
                    }
                }
            }
            System.out.println(i / (COUNT + 0.0));
        }


    }

    /**
     * 不带有虚拟节点
     *
     * @throws Exception
     */
    @Test
    public void test1() throws Exception {
        TreeMap<Integer, String> nodeHashMap = new TreeMap<>();
        for (String node : NODES) {
            Integer hash = hash(node);
            nodeHashMap.put(hash, node);
        }
        System.out.println(nodeHashMap);
        Map<String, List<String>> dataNodeMap = distribute(nodeHashMap);
        for (List<String> newNodes : NEW_NODES) {
            nodeHashMap = new TreeMap<>();
            for (String node : newNodes) {
                Integer hash = hash(node);
                nodeHashMap.put(hash, node);
            }
            System.out.println(nodeHashMap);
            redistribute(nodeHashMap, dataNodeMap);
        }

    }


    /**
     * 带有虚拟节点
     *
     * @throws Exception
     */
    @Test
    public void test2() throws Exception {
        TreeMap<Integer, String> nodeHashMap = new TreeMap<>();
        for (String node : NODES) {
            for (int i = 0; i < VIRTUAL; i++) {
                Integer hash = hash(node + "#" + i);
                nodeHashMap.put(hash, node);
            }
        }
        System.out.println(nodeHashMap);
        Map<String, List<String>> dataNodeMap = distribute(nodeHashMap);
        for (List<String> newNodes : NEW_NODES) {
            nodeHashMap = new TreeMap<>();
            for (String node : newNodes) {
                for (int i = 0; i < VIRTUAL; i++) {
                    Integer hash = hash(node + "#" + i);
                    nodeHashMap.put(hash, node);
                }
            }
            System.out.println(nodeHashMap);
            redistribute(nodeHashMap, dataNodeMap);
        }

    }


    private static Map<String, List<String>> distribute(TreeMap<Integer, String> nodeHashMap) {
        Map<String, List<String>> dataNodeMap = new HashMap<>();
        for (int i = 0; i < COUNT; i++) {
            String s = UUID.randomUUID().toString();
            Integer hash = hash(s);
            String node = getNode(nodeHashMap, hash);
            if (dataNodeMap.containsKey(node)) {
                dataNodeMap.get(node).add(s);
            } else {
                List<String> l = new ArrayList<>();
                l.add(s);
                dataNodeMap.put(node, l);
            }
        }
        for (Map.Entry<String, List<String>> entry : dataNodeMap.entrySet()) {
            System.out.println(entry.getKey() + "  :  " + entry.getValue().size());
        }
        return dataNodeMap;
    }

    private static Map<String, List<String>> redistribute(TreeMap<Integer, String> nodeHashMap, Map<String, List<String>> dataNodeMap) {
        Map<String, List<String>> redataNodeMap = new HashMap<>();
        int redistributeCount = 0;
        for (Map.Entry<String, List<String>> entry : dataNodeMap.entrySet()) {
            String oldNode = entry.getKey();
            List<String> data = entry.getValue();
            for (String s : data) {
                Integer hash = hash(s);
                String node = getNode(nodeHashMap, hash);
                if (redataNodeMap.containsKey(node)) {
                    redataNodeMap.get(node).add(s);
                } else {
                    List<String> l = new ArrayList<>();
                    l.add(s);
                    redataNodeMap.put(node, l);
                }
                if (!oldNode.equals(node)) {
                    redistributeCount++;
                }
            }
        }
        for (Map.Entry<String, List<String>> entry : redataNodeMap.entrySet()) {
            System.out.println(entry.getKey() + "  :  " + entry.getValue().size());
        }
        System.out.println(redistributeCount / (COUNT + 0.0));
        return redataNodeMap;

    }


}
