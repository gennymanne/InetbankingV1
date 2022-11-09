package com.intebanking.utilities;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelUtils {

    public static FileInputStream fi;
    public static FileOutputStream fo;
    public static XSSFWorkbook wb;
    public static XSSFSheet ws;
    public static XSSFCell cell;
    public static XSSFRow row;


    public static int getRowCount(String xl,String sheet) throws IOException {
        fi= new FileInputStream(xl);
        wb= new XSSFWorkbook(fi);
        ws= wb.getSheet(sheet);
        int rowcount= ws.getLastRowNum();
        fi.close();
        wb.close();
        return rowcount;


    }

    public static int getCellCount(String xl,String sheet,int rownum) throws IOException {
        fi= new FileInputStream(xl);
        wb= new XSSFWorkbook(fi);
        ws= wb.getSheet(sheet);
        row = ws.getRow(rownum);
        int cellcount= row.getLastCellNum();
        fi.close();
        wb.close();
        return cellcount;
    }

    public static String getCellData(String xl,String sheet,int rownum,int colnum) throws IOException {
        fi= new FileInputStream(xl);
        wb= new XSSFWorkbook(fi);
        ws= wb.getSheet(sheet);
        row = ws.getRow(rownum);
        cell= row.getCell(colnum);
        String data;
        try{
            DataFormatter df= new DataFormatter();
            String celldata= df.formatCellValue(cell);
            return celldata;
        }
        catch(Exception e){
            data= "";
        }
        fi.close();
        wb.close();
        return data;
    }

    public static String setCellData(String xl,String sheet,int rownum,int colnum,String data) throws IOException {
        fi= new FileInputStream(xl);
        wb= new XSSFWorkbook(fi);
        ws= wb.getSheet(sheet);
        row = ws.getRow(rownum);
        cell =row.createCell(colnum);
        cell.setCellValue(data);
        fo= new FileOutputStream(xl);
        wb.write(fo);
        wb.close();
        fi.close();
        fo.close();
        return data;
    }


}
