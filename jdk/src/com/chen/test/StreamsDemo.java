package com.chen.test;



import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.nio.file.Files;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by Kang on 2017/8/8.
 */
public class StreamsDemo {
    private enum Status {
        OPEN, CLOSED
    };

    private static final class Task {
        private final Status status;
        private final Integer points;

        Task( final Status status, final Integer points ) {
            this.status = status;
            this.points = points;
        }

        public Integer getPoints() {
            return points;
        }

        public Status getStatus() {
            return status;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Task task = (Task) o;

            if (status != task.status) return false;
            return points != null ? points.equals(task.points) : task.points == null;
        }

        @Override
        public int hashCode() {
            int result = status != null ? status.hashCode() : 0;
            result = 31 * result + (points != null ? points.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return String.format( "[%s, %d]", status, points );
        }
    }

    private Collection<Task> tasks;



    @Before
    public void init(){
        tasks= Arrays.asList(new Task( Status.OPEN, 5 ),
                new Task( Status.OPEN, 13 ),
                new Task( Status.OPEN, 19 ),
                new Task( Status.OPEN, 19 ),
                new Task( Status.CLOSED, 8 ) );
    }

    @Test
    public void test1(){
        Stream<Task> s1=tasks.stream();
        Stream<Task> s2=s1.filter(task->task.getStatus()==Status.OPEN);
        System.out.println(s1==s2);
        s1=tasks.stream();
        long l=s1.filter(task->task.getStatus()==Status.OPEN).distinct().count();
        System.out.println(l);
       // l=s1.filter(task->task.getStatus()==Status.OPEN).distinct().count();
       // System.out.println(l);
        Optional<Task> o=tasks.stream().filter(task->task.getStatus()==Status.OPEN).findFirst();
        if(o.isPresent()){
            System.out.println(o.get());
        }
    }

    @Test
    public void testArrayStream(){
        long l = Arrays.stream(new Task[]{new Task(Status.OPEN, 5),
                new Task(Status.OPEN, 13),
                new Task(Status.OPEN, 19),
                new Task(Status.OPEN, 19),
                new Task(Status.CLOSED, 8)}).filter(task -> task.getStatus() == Status.OPEN).distinct().count();
        System.out.println(l);
         Stream.of(new Task(Status.OPEN, 5),
                new Task(Status.OPEN, 13),
                new Task(Status.OPEN, 19),
                new Task(Status.OPEN, 19),
                new Task(Status.CLOSED, 8)).filter(task -> task.getStatus() == Status.OPEN).collect(Collectors.toList());
        System.out.println(l);
        int i=Arrays.stream(new int[]{1,2,3,4,5,6,7,8,9}).sum();
        System.out.println(i);
        i=IntStream.of(1,2,3,4,5,6,7,8,9).sum();
        System.out.println(i);
        i=IntStream.range(5,20).sum();   //不包含20
        System.out.println(i);
    }

    @Test
    public void testBufferedReaderStream() throws Exception{
        InputStream in=this.getClass().getClassLoader().getResourceAsStream("bufferedStream");
        System.out.println(in);
        BufferedReader reader=new BufferedReader(new InputStreamReader(in));
        int i=reader.lines().mapToInt(s->{
            return Integer.parseInt(s);
        }).sum();
        System.out.println(i);
    }

    @Test
    public void testCollectors(){
        Stream<Task> s= tasks.stream().parallel();
        Collector<Task,?,List<Task>> c=Collectors.toList();
        Supplier<List<Task>>  supplier=()->{return new ArrayList<Task>();};
        BiConsumer<List<Task>, Task> accumulator=(l,t)->{l.add(t);};
        BiConsumer<List<Task>, List<Task>> combiner=(l1,l2)->{l1.addAll(l2);};
        List<Task> tts=s.collect(supplier,accumulator,combiner);
        System.out.print(tts);
    }


}
