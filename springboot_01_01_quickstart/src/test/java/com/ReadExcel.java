package com;

/**
 * @author mucongcong
 * @date 2022/07/19 17:43
 * @since
 **/
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
public class ReadExcel {
    public static void main(String[] args) {
        ReadExcel obj = new ReadExcel();
        // 此处为我创建Excel路径：E:/zhanhj/studysrc/jxl下
        File file = new File("D:\\projects\\部门业务\\0-nim-sms文档\\通道数据.xls");
        Map<Integer, List<List<String>>> sheetMap = obj.readExcel(file);
        System.out.println(sheetMap.size());
        for (int i = 0; i < sheetMap.size(); i++) {
            List<List<String>> excelList = sheetMap.get(i);
            for (int j = 0; j < excelList.size(); j++) {
                List list = (List) excelList.get(j);
                for (int k = 0; k < list.size(); k++) {
                    System.out.print(list.get(k));
                }
                System.out.println();
            }
            System.out.println(excelList.size());
        }

    }
    // 去读Excel的方法readExcel，该方法的入口参数为一个File对象
    public Map<Integer, List<List<String>>> readExcel(File file) {
        try {
            // 创建输入流，读取Excel
            InputStream is = new FileInputStream(file.getAbsolutePath());
            // jxl提供的Workbook类
            Workbook wb = Workbook.getWorkbook(is);
            // Excel的页签数量
            int sheet_size = wb.getNumberOfSheets();
            Map<Integer, List<List<String>>> res = new HashMap<>();
            for (int index = 0; index < sheet_size; index++) {
                List<List<String>> outerList=new ArrayList<>();
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
