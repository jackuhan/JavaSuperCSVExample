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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SuperCSVExample {

    public static void main(String[] args) {

        //Super CSV是一个速度奇快、免费跨平台的 CVS 格式数据的读写库，可以方便的处理对象、Map、列表的读写操作，以及自动化的类型转换和数据检查功能。
        //http://supercsv.sourceforge.net/
        try {

            test1();//通过Bean读写

            test2();//通过List读写
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


    private static void test2() throws Exception {
        List<List<String>> content = new ArrayList<List<String>>();

        InputStreamReader freader = new InputStreamReader(new FileInputStream(
                new File("csv/test3.csv")), "GB2312");

        CsvListReader reader = new CsvListReader(freader,CsvPreference.EXCEL_PREFERENCE);

        List<String> lines = new ArrayList<String>();
        while ((lines = reader.read()) != null) {
            content.add(lines);
        }

        int lineNum = content.size()-1;
        int rowNum = 0;
        for (int i = 0; i <content.size() ; i++) {
            List<String> rows = new ArrayList<String>();
            rows = content.get(i);

            if(rows.size()>rowNum){
                rowNum = rows.size();
            }

            for (int j = 0; j <rows.size() ; j++) {
                System.out.print(rows.get(j).trim()+"\t");
            }
            System.out.println("\t");

        }
        System.out.println("\n"+"lineNum="+lineNum);
        System.out.println("rowNum="+rowNum);
        System.out.println();

        reader.close();
    }


}
