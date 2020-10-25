package tool;



import org.apache.poi.hpsf.DocumentSummaryInformation;
import org.apache.poi.hpsf.SummaryInformation;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



public class POIUtils {

   
    public static void txt2Excel(File inFile, File outFile, String splie, String... columnName) {

        // txt输出list
        List<String> list = txt2List(inFile);
        //字节数组输出流
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        FileOutputStream fileOutputStream = null;
        // 获取文件名
        String fileName = inFile.getName().substring(0, inFile.getName().indexOf("."));

        //1. 创建一个 Excel 文档
        HSSFWorkbook workbook = new HSSFWorkbook();
        //2. 创建文档摘要
        workbook.createInformationProperties();
        //3. 获取并配置文档信息
        DocumentSummaryInformation docInfo = workbook.getDocumentSummaryInformation();
        //文档类别
        docInfo.setCategory(inFile.getName());
        //文档管理员
        docInfo.setManager("liruilong");
        //设置公司信息
        docInfo.setCompany("www.liruiong.org");
        //4. 获取文档摘要信息
        SummaryInformation summInfo = workbook.getSummaryInformation();
        //文档标题
        summInfo.setTitle(inFile.getName());
        //文档作者
        summInfo.setAuthor("liruilong");
        // 文档备注
        summInfo.setComments("本文档由 liruilong 提供");
        //5. 创建样式
        //创建标题行的样式
        HSSFCellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.YELLOW.index);
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        HSSFCellStyle dateCellStyle = workbook.createCellStyle();
        dateCellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy"));
        HSSFSheet sheet = workbook.createSheet(fileName);
        HSSFRow rx = null;
        //构造表格框架
        rx = sheet.createRow(0);
        for (int i = 0; i < columnName.length; i++) {
            //设置列的宽度
            sheet.setColumnWidth(i, 12 * 256);
            //创建标题行
            HSSFCell cx = rx.createCell(i);
            cx.setCellValue(columnName[i]);
            cx.setCellStyle(headerStyle);
        }
        // 填充数据
        for (int i = 0; i < list.size(); i++) {
            HSSFRow row = sheet.createRow(i + 1);
            String[] tempLine = list.get(i).toString().split(splie);
            for (int i1 = 0; i1 < tempLine.length; i1++) {
                {row.createCell(i1).setCellValue(tempLine[i1]);}
                List<String> yu= Arrays.asList(tempLine[i1].split("( )+"));
                System.out.println(yu);
                int j=0;
                for(String s : yu){
                    row.createCell(i1+j).setCellValue(s);
                    j++;
                }
                j=0;

            }
        }
        // 写入文件
        try {
            if (!outFile.exists()) {
                System.out.println("***************************文件错误****************************");
                throw new Exception();
            } else if (outFile.isDirectory()) {
                outFile.mkdirs();
                fileOutputStream = new FileOutputStream(outFile.getAbsolutePath() + "\\" + fileName + ".xlsx");
            } else {
                fileOutputStream = new FileOutputStream(outFile);
            }
            workbook.write(baos);
            fileOutputStream.write(baos.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   

    public static List<String> txt2List(File file) {
        String string = null;
        List<String> txtListAll = new ArrayList<>();
        if (!file.exists() || file.isDirectory()) {
            System.out.println("***************************文件错误****************************");
        } else {
            fileToBufferedReader((bufferedReader) -> {
                String str = null;
                StringBuilder stringBuilder = new StringBuilder();
                while ((str = bufferedReader.readLine()) != null) {
                    // TODO 此处可以书写去重逻辑。

                        txtListAll.add(str);

                }
            }, file);
        }
        if (txtListAll.size() > 65535) {
            System.out.println("***************************行数太多错误****************************");
        }
        return txtListAll;
    }

   
    public static void fileToBufferedReader(InputStreamPeocess1 inputStreamPeocess, File file) {
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            try (InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream)) {
                try (BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
                    inputStreamPeocess.peocess(bufferedReader);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
      File editFile= new File("C:\\Users\\lucky\\Desktop\\text.txt");
      System.out.println(editFile.toString());

        txt2Excel(editFile,new File("C:\\Users\\lucky\\Desktop\\1.xlsx"),";","列名1", "列名2", "列名2","列名2");

    }

}