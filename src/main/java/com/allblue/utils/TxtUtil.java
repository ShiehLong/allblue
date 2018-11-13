package com.allblue.utils;

import java.io.*;

/**
 * @Description:
 * @Author Xone
 * @Date 15:02 2018/11/10
 **/
public class TxtUtil {
    public static void main(String args[]) {
        try { // 防止文件建立或读取失败，用catch捕捉错误并打印，也可以throw

            /* 读入TXT文件 */
            String pathname = "C:\\Users\\Xone\\Downloads\\part102.txt"; // 绝对路径或相对路径都可以，这里是绝对路径，写入文件时演示相对路径
            String pathname1 = "C:\\Users\\Xone\\Downloads\\part"; // 绝对路径或相对路径都可以，这里是绝对路径，写入文件时演示相对路径
            int count = 0;//计数器


            BufferedReader br = fileReader(pathname); // 建立一个对象，它把文件内容转成计算机能读懂的语言
            String line = br.readLine();
            while (line != null) {
                for (int i = 1; i <= 5; i++) {
                    BufferedReader br1 = fileReader(pathname1 + i + ".txt"); // 建立一个对象，它把文件内容转成计算机能读懂的语言
                    String line1 = br1.readLine();
                    while (line1 != null) {
                        if (line.equals(line1)) {
                            System.out.println(line + "重复！！！");
                        }
                        line1 = br1.readLine(); // 一次读入一行数据
                    }
                }
                line = br.readLine(); // 一次读入一行数据
                System.out.println("比较次数：" + (++count));
            }
            System.out.println("比较结束！！！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static BufferedReader fileReader(String path) {
        File filename = new File(path); // 要读取以上路径的input。txt文件
        InputStreamReader reader = null; // 建立一个输入流对象reader
        try {
            reader = new InputStreamReader(
                    new FileInputStream(filename));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader br = null; // 建立一个对象，它把文件内容转成计算机能读懂的语言
        if (reader != null) {
            br = new BufferedReader(reader);
        }
        return br;
    }

    public static void fileWriter(String path) {
        /* 写入Txt文件 */
        File writename = new File(path); // 相对路径，如果没有则要建立一个新的output.txt文件
        try {
            writename.createNewFile(); // 创建新文件
            BufferedWriter out = new BufferedWriter(new FileWriter(writename));
            out.write("我会写入文件啦\r\n"); // \r\n即为换行
            out.flush(); // 把缓存区内容压入文件
            out.close(); // 最后记得关闭文件
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}