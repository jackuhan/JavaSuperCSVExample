package com.rensanning.csv;

import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.CsvListReader;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.prefs.CsvPreference;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class SuperCSVExample {

    public static void main(String[] args) {

        //Super CSV是一个速度奇快、免费跨平台的 CVS 格式数据的读写库，可以方便的处理对象、Map、列表的读写操作，以及自动化的类型转换和数据检查功能。
        //http://supercsv.sourceforge.net/
        try {

            test1();//通过Bean读写

            test3();//通过List读写
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void test1() throws Exception {

        InputStreamReader freader = new InputStreamReader(new FileInputStream(
                new File("csv/test1.csv")), "GB2312");

        ICsvBeanReader reader = new CsvBeanReader(freader,
                CsvPreference.EXCEL_PREFERENCE);

        //获取头部信息
        String[] headers = UserBean.header;

        //获取数据部分
        UserBean bean = null;
        while ((bean = reader.read(UserBean.class, headers, UserBean.readProcessors)) != null) {
            System.out.print(bean.getName() + "\t");
            System.out.print(bean.getAge() + "\t");
            System.out.print(bean.getBirthday() + "\t");
            System.out.println(bean.getAddress());
        }
        System.out.println();

        reader.close();
    }


    private static void test3() throws Exception {
        List<String[]> content = new ArrayList<String[]>();

        InputStreamReader freader = new InputStreamReader(new FileInputStream(
                new File("csv/test3.csv")), "GB2312");

        CsvListReader reader = new CsvListReader(freader,CsvPreference.EXCEL_PREFERENCE);

        List<String> line = new ArrayList<String>();
        while ((line = reader.read()) != null) {
            content.add(line.toArray(new String[]{}));
        }

        for (int i = 0; i <content.size() ; i++) {
            System.out.println(content.get(i)[0] + "\t" + content.get(i)[1] + "\t" + content.get(i)[2] + "\t" + content.get(i)[3]);
        }
        System.out.println();

        reader.close();
    }


}
