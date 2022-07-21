package com;

import java.io.*;
import java.util.*;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 读取Excel
 *
 * @author zengwendong
 */
public class ReadExcelUtils {


    private static String regExp="[\"\\uFEFF\"\n`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。， 、？-]";

    //2. 这里是将特殊字符换为空字符串,""代表直接去掉
    private static String replace = "";

    public static void main(String[] args) {
//        发送数据
        //第一步：先获取csv文件的路径，通过BufferedReader类去读该路径中的文件
        File csv = new File("D:\\projects\\部门业务\\0-nim-sms文档\\发送数据.csv");

        Map<Integer, List<String>> map = new HashMap<>();
        List<String> successList = new ArrayList<>();
        List<String> failList = new ArrayList<>();
        List<String> unknownList = new ArrayList<>();
        List<String> badList = new ArrayList<>();
        map.put(0, successList);
        map.put(1, failList);
        map.put(2, unknownList);
        map.put(3, badList);

        try{
            //第二步：从字符输入流读取文本，缓冲各个字符，从而实现字符、数组和行（文本的行数通过回车符来进行判定）的高效读取。
            BufferedReader textFile = new BufferedReader(new FileReader(csv));
            String lineDta = "";
            int cnt = 0;
            //第三步：将文档的下一行数据赋值给lineData，并判断是否为空，若不为空则输出
            while ((lineDta = textFile.readLine()) != null){

                String firstString = lineDta.replaceAll(regExp, replace);
                if (firstString.length() != 42) {
                    System.out.println(firstString);
                }
//                System.out.println(firstString);
                String s1 = firstString.substring(8, 27);
                String s2 = firstString.substring(27, 40);
                String secondString = s1 + " " + s2 + " ";
                String thirdString = firstString.substring(40);
                if (thirdString.equals("成功")) {
                    successList.add(secondString);
                } else if (thirdString.equals("失败")) {
                    failList.add(secondString);
                } else if (thirdString.equals("未知")) {
                    unknownList.add(secondString);
                } else {
                    badList.add(firstString);
                }

            }
            System.out.println("发送数据 successList 长度：" + successList.size());
            System.out.println("发送数据 failList 长度：" + failList.size());
            System.out.println("发送数据 unknownList 长度：" + unknownList.size());
            System.out.println("发送数据 badList 长度：" + badList.size());

            textFile.close();
        }catch (FileNotFoundException e){
            System.out.println("没有找到指定文件");
        }catch (IOException e){
            System.out.println("文件读写出错");
        }

        // 通道数据
        ReadExcel obj = new ReadExcel();
        // 此处为我创建Excel路径：E:/zhanhj/studysrc/jxl下
        File file = new File("D:\\projects\\部门业务\\0-nim-sms文档\\通道数据.xls");
        Map<Integer, List<List<String>>> sheetMap = obj.readExcel(file);

        Map<Integer, List<String>> map2 = new HashMap<>();
        List<String> successList2 = new ArrayList<>();
        List<String> failList2 = new ArrayList<>();
        List<String> unknownList2 = new ArrayList<>();
        List<String> badList2 = new ArrayList<>();
        map.put(0, successList2);
        map.put(1, failList2);
        map.put(2, unknownList2);
        map.put(3, badList2);

//        System.out.println(sheetMap.size());
        for (int i = 0; i < sheetMap.size(); i++) {
            List<List<String>> excelList = sheetMap.get(i);
            for (int j = 0; j < excelList.size(); j++) {
                List<String> list = excelList.get(j);
                StringBuffer sb = new StringBuffer();
                for (int k = 0; k < list.size(); k++) {
//                    System.out.print(list.get(k));
                    if (list.get(k).length() == 11) {
                        sb.append("86");
                        sb.append(list.get(k));
                        sb.append(" ");
                    } else {
                        sb.append(list.get(k));
                        sb.append(" ");
                    }
                }
                if (i == 0) {
                    successList2.add(sb.toString());
                } else if (i == 1) {
                    failList2.add(sb.toString());
                } else if (i == 2) {
                    unknownList2.add(sb.toString());
                } else {
                    badList2.add(sb.toString());
                }
//                System.out.println();
            }
//            System.out.println(excelList.size());
        }
        System.out.println("运营商数据 successList2 长度：" + successList2.size());
        System.out.println("运营商数据 failList2 长度：" + failList2.size());
        System.out.println("运营商数据 unknownList2 长度：" + unknownList2.size());
        System.out.println("运营商数据 badList2 长度：" + badList2.size());


        Iterator<String> iterator1 = successList.iterator();
        int cnt1 = 0;
        while (iterator1.hasNext()) {
            String s = iterator1.next();
            if (successList2.contains(s)) {
                successList2.remove(s);
                iterator1.remove();
                cnt1++;
            }
        }
        System.out.println("成功正确数据量：" + cnt1);

        Iterator<String> iterator2 = failList.iterator();
        int cnt2 = 0;
        while (iterator2.hasNext()) {
            String s = iterator2.next();
            if (failList2.contains(s)) {
                failList2.remove(s);
                iterator2.remove();
                cnt2++;
            }
        }
        System.out.println("失败正确数据量：" + cnt2);

        Iterator<String> iterator3 = unknownList2.iterator();
        int cnt3 = 0;
        while (iterator3.hasNext()) {
            String s = iterator3.next();
            if (unknownList.contains(s)) {
                unknownList.remove(s);
                iterator3.remove();
                cnt3++;
            }
        }
        System.out.println("未知正确数据量：" + cnt3);


        System.out.println("成功异常数据");
        System.out.println("发送数据中有但通道数据没有的");
        for (String s : successList) {
            System.out.println(s);
        }
        System.out.println("通道数据中有但发送数据没有的");
        for (String s : successList2) {
            System.out.println(s);
        }
        System.out.println("=====================================================");
        System.out.println("失败异常数据");
        System.out.println("发送数据中有但通道数据没有的");
        for (String s : failList) {
            System.out.println(s);
        }
        System.out.println("通道数据中有但发送数据没有的");
        for (String s : failList2) {
            System.out.println(s);
        }
        System.out.println("=====================================================");
        System.out.println("未知异常数据");
        System.out.println("发送数据中有但通道数据没有的");
        for (String s : unknownList) {
            System.out.println(s);
        }
        System.out.println("通道数据中有但发送数据没有的");
        for (String s : unknownList2) {
            System.out.println(s);
        }
        System.out.println("=====================================================");

        System.out.println("==========================================================================================================");
        //发送成功，通道失败
        List<String> s1 = new ArrayList<>();
        //发送成功，通道未知
        List<String> s2 = new ArrayList<>();
        //发送失败，通道成功
        List<String> s3 = new ArrayList<>();
        //发送失败，通道异常
        List<String> s4 = new ArrayList<>();
        //发送异常，通道成功
        List<String> s5 = new ArrayList<>();
        //发送异常，通道失败
        List<String> s6 = new ArrayList<>();

        Iterator<String> it1 = successList.iterator();
        while (it1.hasNext()) {
            String s = it1.next();
            if (failList2.contains(s)) {
                s1.add(s);
                failList2.remove(s);
                it1.remove();
            }
            if (unknownList2.contains(s)) {
                s2.add(s);
                unknownList2.remove(s);
                it1.remove();
            }
        }

        Iterator<String> it2 = failList.iterator();
        while (it2.hasNext()) {
            String s = it2.next();
            if (successList2.contains(s)) {
                s3.add(s);
                successList2.remove(s);
                it2.remove();
            }
            if (unknownList2.contains(s)) {
                s4.add(s);
                unknownList2.remove(s);
                it2.remove();
            }
        }

        Iterator<String> it3 = unknownList.iterator();
        while (it3.hasNext()) {
            String s = it3.next();
            if (successList2.contains(s)) {
                s5.add(s);
                successList2.remove(s);
                it3.remove();
            }
            if (failList2.contains(s)) {
                s6.add(s);
                failList2.remove(s);
                it3.remove();
            }
        }

        System.out.println("发送成功，通道失败");
        for (String s : s1) {
            System.out.println(s);
        }
        System.out.println("====================================================");
        //发送成功，通道未知
        System.out.println("发送成功，通道未知");
        for (String s : s2) {
            System.out.println(s);
        }
        System.out.println("====================================================");
        //发送失败，通道成功
        System.out.println("发送失败，通道成功");
        for (String s : s3) {
            System.out.println(s);
        }
        System.out.println("====================================================");
        //发送失败，通道异常
        System.out.println("发送失败，通道异常");
        for (String s : s4) {
            System.out.println(s);
        }
        System.out.println("====================================================");
        //发送异常，通道成功
        System.out.println("发送异常，通道成功");
        for (String s : s5) {
            System.out.println(s);
        }
        System.out.println("====================================================");
        //发送异常，通道失败
        System.out.println("发送异常，通道失败");
        for (String s : s6) {
            System.out.println(s);
        }
        System.out.println("====================================================");


        System.out.println("==========================================================================================================");

        //发送成功剩余数据
        System.out.println("发送成功剩余数据");
        for (String s : successList) {
            System.out.println(s);
        }
        System.out.println("====================================================");
        //发送失败剩余数据
        System.out.println("发送失败剩余数据");
        for (String s : failList) {
            System.out.println(s);
        }
        System.out.println("====================================================");
        //发送未知剩余数据
        System.out.println("发送未知剩余数据");
        for (String s : unknownList) {
            System.out.println(s);
        }
        System.out.println("====================================================");
        //通道成功剩余数据
        System.out.println("通道成功剩余数据");
        for (String s : successList2) {
            System.out.println(s);
        }
        System.out.println("====================================================");
        //通道失败剩余数据
        System.out.println("通道失败剩余数据");
        for (String s : failList2) {
            System.out.println(s);
        }
        System.out.println("====================================================");
        //通道未知剩余数据
        System.out.println("通道未知剩余数据");
        for (String s : unknownList2) {
            System.out.println(s);
        }
        System.out.println("====================================================");
    }

    // 去读Excel的方法readExcel，该方法的入口参数为一个File对象
    public Map<Integer, List<List>> readExcel(File file) {
        try {
            // 创建输入流，读取Excel
            InputStream is = new FileInputStream(file.getAbsolutePath());
            // jxl提供的Workbook类
            jxl.Workbook wb = Workbook.getWorkbook(is);
            // Excel的页签数量
            int sheet_size = wb.getNumberOfSheets();
            Map<Integer, List<List>> res = new HashMap<>();
            for (int index = 0; index < sheet_size; index++) {
                List<List> outerList=new ArrayList<>();
                // 每个页签创建一个Sheet对象
                Sheet sheet = wb.getSheet(index);
                // sheet.getRows()返回该页的总行数
                for (int i = 0; i < sheet.getRows(); i++) {
                    List innerList=new ArrayList();
                    // sheet.getColumns()返回该页的总列数
                    for (int j = 0; j < sheet.getColumns(); j++) {
                        String cellinfo = sheet.getCell(j, i).getContents();
                        if(cellinfo.isEmpty()){
                            continue;
                        }
                        innerList.add(cellinfo);
//                        System.out.print(cellinfo);
                    }
                    outerList.add(i, innerList);
//                    System.out.println();
                }
                res.put(index, outerList);
            }
            return res;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
