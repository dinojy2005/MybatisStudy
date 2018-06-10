package com.dino;

import JSON.Convers;
import mysql.MysqlTest;

import java.io.FileInputStream;
import java.io.IOException;

public class Test {

    public static void main(String[] args) throws IOException {
        System.out.println("hello");
        System.out.println("Process1");
        HelloWorld h1 = new HelloWorld();
        System.out.println("Process2");
        MysqlTest m1 = new MysqlTest();
        m1.main();
        System.out.println("Process3");
        Convers c1 = new Convers();
        System.out.println("Process4");
    }

    public void IoMethod1() throws IOException
    {
        FileInputStream fis = new FileInputStream("tmp2.txt");
        byte[] buf = new byte[1024];
        int hasRead = 0;

        //read()返回的是单个字节数据（字节数据可以直接专程int类型)，但是read(buf)返回的是读取到的字节数，真正的数据保存在buf中
        while ((hasRead = fis.read(buf)) > 0)
        {
            //每次最多将1024个字节转换成字符串，这里tmp2.txt中的字符小于1024，所以一次就读完了
            //循环次数 = 文件字符数 除以 buf长度
            System.out.println(new String(buf, 0 ,hasRead));
            break;
        }
        //在finally块里close更安全
        fis.close();
    }
}
