package com.chenbk.utils.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * IO工具类
 * ❤文件输入输出流：
 * FileInputStream-->具体实现了在文件上读取数据
 * FileOutputStream-->实现了向文件中写出byte数据的方法
 * ❤数据输入输出流：
 * DataOutputStream / DataInputStream：对"流"功能的扩展，可以更加方面的读取int,long，字符等类型数据
 * DataOutputStream writeInt()/writeDouble()/writeUTF()
 * ❤字节缓冲流：
 * BufferedInputStream & BufferedOutputStream
 * 这两个流类位IO提供了带缓冲区的操作，一般打开文件进行写入或读取操作时，都会加上缓冲，这种流模式提高了IO的性能
 * 比如：从应用程序中把输入放入文件，相当于将一缸水倒入到另一个缸中:
 * FileOutputStream--->write()方法相当于一滴一滴地把水“转移”过去
 * DataOutputStream-->writeXxx()方法会方便一些，相当于一瓢一瓢把水“转移”过去
 * BufferedOutputStream--->write方法更方便，相当于一瓢一瓢先放入桶中（即缓存区），再从桶中倒入到另一个缸中，性能提高了
 */
public class IOUtil {
    /**
     * 读取指定文件内容，按照16进制输出到控制台
     * 并且每输出10个byte换行
     *
     * @param fileName 单字节读取不适合大文件，大文件效率很低
     */
    public static void printHex(String fileName) throws IOException {
//把文件作为字节流进行都操作
        FileInputStream in = new FileInputStream(fileName);
        int b;
        int i = 1;
        while ((b = in.read()) != -1) {
/* 0xff换成2进制就是8个1，这样与的话，其实就是取到了字符的低8位。
* oxf就是15， 小于15的数会转换成一个16进制数，
* 你的代码里希望是固定的两个16进制数，所以当只会产生一个时要加个0*/
            if (b <= 0xf) {
//单位数前面补0
                System.out.print("0");
            }
//Integer.toHexString(b)将整型b转换为16进制表示的字符串
            System.out.print(Integer.toHexString(b) + " ");
            if (i++ % 10 == 0) {
                System.out.println();
            }
        }
        in.close();//文件读写完成以后一定要关闭
    }

    /**
     * 批量读取，对大文件而言效率高，也是我们最常用的读文件的方式
     *
     * @param fileName
     * @throws IOException
     */
    public static void printHexByByteArray(String fileName) throws IOException {
        FileInputStream in = new FileInputStream(fileName);
        byte[] buf = new byte[8 * 1024];
/*从in中批量读取字节，放入到buf这个字节数组中，
* 从第0个位置开始放，最多放buf.length个
* 返回的是读到的字节的个数
*/
/*int bytes = in.read(buf,0,buf.length);//一次性读完，说明字节数组足够大
int j = 1;
for(int i = 0; i < bytes;i++){
System.out.print(Integer.toHexString(buf[i] & 0xff)+" ");
if(j++%10==0){
System.out.println();
}
}*/
        int bytes = 0;
        int j = 1;
        while ((bytes = in.read(buf, 0, buf.length)) != -1) {
            for (int i = 0; i < bytes; i++) {
                System.out.print(Integer.toHexString(buf[i] & 0xff) + " ");
/**
 * & 0xff:byte类型8位，int类型32位，为了避免数据转换错误，通过&0xff将高24位清零
 */
                if (j++ % 10 == 0) {
                    System.out.println();
                }
            }
        }
        in.close();
    }

    /**
     * 文件拷贝，字节批量读取
     *
     * @param srcFile
     * @param destFile
     * @throws IOException
     */
    public static void copyFile(File srcFile, File destFile) throws IOException {
        if (!srcFile.exists()) {
            throw new IllegalArgumentException("文件:" + srcFile + "不存在");
        }
        if (!srcFile.isFile()) {
            throw new IllegalArgumentException(srcFile + "不是文件");
        }
        FileInputStream in = new FileInputStream(srcFile);
        FileOutputStream out = new FileOutputStream(destFile);//文件不存在时，会直接创建；如果存在，删除后建
        byte[] buf = new byte[8 * 1024];//批量读写
        int b;
        while ((b = in.read(buf, 0, buf.length)) != -1) {//read(buf,0,buf.length)带参数的read返回的是字节的总长度；当全部读完后返回的是-1；
            out.write(buf, 0, b);
            out.flush();// 最好加上
        }
        in.close();
        out.close();
    }

    /**
     * 进行文件的拷贝，利用带缓冲的字节流
     *
     * @param srcFile
     * @param destFile
     * @throws IOException
     */
    public static void copyFileByBuffer(File srcFile, File destFile) throws IOException {
        if (!srcFile.exists()) {
            throw new IllegalArgumentException("文件:" + srcFile + "不存在");
        }
        if (!srcFile.isFile()) {
            throw new IllegalArgumentException(srcFile + "不是文件");
        }
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(srcFile));
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(destFile));
        int c;
        while ((c = bis.read()) != -1) {
            bos.write(c);
            bos.flush();//刷新缓冲区
        }
        bis.close();
        bos.close();
    }

    /**
     * 单字节，不带缓冲进行文件拷贝
     *
     * @param srcFile
     * @param destFile
     * @throws IOException
     */
    public static void copyFileByByte(File srcFile, File destFile) throws IOException {
        if (!srcFile.exists()) {
            throw new IllegalArgumentException("文件:" + srcFile + "不存在");
        }
        if (!srcFile.isFile()) {
            throw new IllegalArgumentException(srcFile + "不是文件");
        }
        FileInputStream in = new FileInputStream(srcFile);
        FileOutputStream out = new FileOutputStream(destFile);
        int c;
        while ((c = in.read()) != -1) {//read()不带参数的read返回的是读到的字节内容；当全部读完后返回的都是是-1；
            out.write(c);
            out.flush();
        }
        in.close();
        out.close();
    }
}