/**
 * 文件名：ExcelBulid.java 版权：Copyright by www.bjleisen.com 描述： 修改人：zhangziqi 修改时间：2015年12月2日 修改内容：
 */

package com.bupt.cardtest.excelPOI;


import org.apache.commons.beanutils.PropertyUtilsBean;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.awt.font.FontRenderContext;
import java.awt.font.TextAttribute;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.lang.reflect.InvocationTargetException;
import java.text.AttributedString;
import java.util.*;


public class ExcelBulid
{

    /**
     * 每个excel中sheet的数量
     */
    private int sheet_num = 0;

    /**
     * 每个sheet中有多少行
     */
    private int row_num = 0;

    /**
     * 导出的excel表格的数据内容，是否需要增加序号，默认不加
     */
    private boolean isHavaNum = false;

    /**
     * 和序号属性为true的情况下，一起使用
     */
    private int listindex = 1;

    public ExcelBulid(int sheet_num, int row_num, boolean isHavaNum)
    {
        super();
        this.sheet_num = sheet_num;
        this.row_num = row_num;
        this.isHavaNum = isHavaNum;
    }

    public List<SXSSFWorkbook> excelbuild(String sheetName, List<ExcelMessage> message,
                                          List<ExcelHeader> head, List<?> data)
    {
        // 头信息不能为空
        if (head != null && head.size() != 0)
        {
            // 响应的excel的集合
            List<SXSSFWorkbook> listexcel = new ArrayList<SXSSFWorkbook>();
            // 得到头信息的层级数
            int headheight = getMaxHeadLevel(head);
            int cellnum = 0;
            // 用来记录数据序号，可能不使用
            // spring bean处理工具，反射用

            if (data.size() <= sheet_num * row_num) // list里的数据可以用一个excel放下。
            {
                SXSSFWorkbook wb = new SXSSFWorkbook();
                listexcel.add(wb);
                if (data.size() <= row_num)
                {
                    int rownum = 0;
                    if (message != null)
                    {
                        rownum += message.size();
                    }
                    SXSSFSheet sheet = wb.createSheet(sheetName);
                    List<String> field = new ArrayList<String>();
                    Map<String, ExcelHeader> headmap = new HashMap<String, ExcelHeader>();
                    sheet = bulidHead(wb, sheet, head, rownum, cellnum, headheight + rownum,
                        field, headmap, isHavaNum);

                    sheet = bulidMessage(wb, sheet, message);
                    rownum += headheight;
                    for (int i = 0; i < data.size(); i++ )
                    {
                        SXSSFRow row = sheet.createRow(rownum++ );
                        writeCell(row, field, headmap, data.get(i));
                    }
                    if (isHavaNum)
                    {
                        field.add(0, "last_index");
                    }
                    for (int k = 0; k < field.size(); k++ )
                    {
                        sheet.autoSizeColumn(k, true);
                    }
                }
                else
                {
                    int sheetnum = 1;
                    List<String> field = new ArrayList<String>();
                    SXSSFSheet sheet = null;
                    Map<String, ExcelHeader> headmap = new HashMap<String, ExcelHeader>();
                    int rownum = 0;
                    for (int i = 0; i < data.size(); i++ )
                    {
                        if (i % row_num == 0)
                        {
                            rownum = 0;
                            if (message != null)
                            {
                                rownum += message.size();
                            }
                            sheet = wb.createSheet(sheetName + "_" + sheetnum);
                            bulidHead(wb, sheet, head, rownum, cellnum, headheight + rownum,
                                field, headmap, isHavaNum);

                            bulidMessage(wb, sheet, message);
                            rownum += headheight;
                            sheetnum++ ;
                        }
                        SXSSFRow row = sheet.createRow(rownum++ );
                        writeCell(row, field, headmap, data.get(i));
                        if ((row_num - (i % row_num) == 1))
                        {
                            for (int k = 0; k < field.size(); k++ )
                            {
                                sheet.autoSizeColumn(k, true);
                            }
                        }
                    }
                    for (int k = 0; k < field.size(); k++ )
                    {
                        sheet.autoSizeColumn(k, true);
                    }
                }
            }
            else
            // list里的数据，一个excel放不下，需要生成多个excel
            {
                int sheetnum = 1;
                SXSSFWorkbook wb = null;
                SXSSFSheet sheet = null;
                List<String> field = new ArrayList<String>();
                int rownum = 0;
                Map<String, ExcelHeader> headmap = new HashMap<String, ExcelHeader>();
                for (int i = 0; i < data.size(); i++ )
                {
                    if (i % (sheet_num * row_num) == 0)
                    {
                        wb = new SXSSFWorkbook();
                        listexcel.add(wb);
                    }
                    if (i % row_num == 0)
                    {
                        rownum = 0;
                        if (message != null)
                        {
                            rownum += message.size();
                        }
                        sheet = wb.createSheet(sheetName + "_" + sheetnum);
                        bulidHead(wb, sheet, head, rownum, cellnum, headheight + rownum, field,
                            headmap, isHavaNum);
                        bulidMessage(wb, sheet, message);
                        rownum += headheight;
                        sheetnum++ ;
                    }
                    SXSSFRow row = sheet.createRow(rownum++ );
                    writeCell(row, field, headmap, data.get(i));
                    if ((row_num - (i % row_num) == 1))
                    {
                        for (int k = 0; k < field.size(); k++ )
                        {
                            sheet.autoSizeColumn(k, true);
                        }
                    }
                }
                for (int k = 0; k < field.size(); k++ )
                {
                    sheet.autoSizeColumn(k, true);
                }
            }
            return listexcel;
        }
        else
        {
            return null;
        }
    }

    public SXSSFSheet bulidHead(SXSSFWorkbook wb, SXSSFSheet sheet, List<ExcelHeader> head,
                                int rownum, int cellnum, final int headheight, List<String> field,
                                Map<String, ExcelHeader> headmap, boolean ishavanumber)
    {
        if (head != null && head.size() != 0)
        {
            // 获取当前行,同样是考虑递归的问题。
            SXSSFRow row = sheet.getRow(rownum);
            // 使用临时值，保留当前行号
            int temprownum = rownum;
            if (row == null)
            {
                // 如果没有根据传递过来的行号，找到row信息，就创建一个新行
                row = sheet.createRow(rownum);
            }

            for (int i = 0; i < head.size(); i++ )
            {
                ExcelHeader excelheader = head.get(i);
                CellStyle style = ExcelStyle.getStyle(wb, excelheader.getStyle_vertical_type(),
                    excelheader.getStyle_align_type(), excelheader.getFont_family(),
                    excelheader.getFont_size(), excelheader.getFont_color(),
                    excelheader.isFont_bold(), excelheader.isBorder(),
                    excelheader.getBackground_color());
                excelheader.setCellstyle(style);
                if (ishavanumber)
                {
                    // 创建新列
                    SXSSFCell cell = row.createCell(cellnum);
                    cell.setCellValue(ExcelConstant.NUM_STR);
                    cell.setCellStyle(style);
                    ishavanumber = false;
                    CellRangeAddress cellRangeAddress = new CellRangeAddress(temprownum,
                        headheight - 1, cellnum, cellnum);
                    sheet.addMergedRegion(cellRangeAddress);
                    fillMergedRegion(sheet, cellRangeAddress, style);
                    cellnum++ ;
                }

                // 创建新列
                SXSSFCell cell = row.createCell(cellnum);
                // 给新列复制，这里都是string
                cell.setCellValue(excelheader.getShowValue());
                // 新列的样式
                cell.setCellStyle(style);
                // 判断是否有子级
                if (excelheader.getChildHead() != null && excelheader.getChildHead().size() != 0)
                {
                    // 存在，就要增加一行
                    int createrow = rownum + 1;
                    // 递归
                    bulidHead(wb, sheet, excelheader.getChildHead(), createrow, cellnum,
                        headheight, field, headmap, false);
                    // 使用临时值，保留当前的列值
                    int temp = cellnum;
                    // 获取当前的子级最大宽度
                    cellnum += getMaxHeadWeight(excelheader.getChildHead()) - 1;
                    CellRangeAddress cellRangeAddress = new CellRangeAddress(temprownum,
                        temprownum, temp, cellnum);
                    // 合并单元格，横向
                    sheet.addMergedRegion(cellRangeAddress);
                    fillMergedRegion(sheet, cellRangeAddress, style);
                }
                else
                {
                    // 如果没有子级，就根据最深层级，合并单元格，纵向
                    CellRangeAddress cellRangeAddress = new CellRangeAddress(temprownum,
                        headheight - 1, cellnum, cellnum);
                    sheet.addMergedRegion(cellRangeAddress);
                    fillMergedRegion(sheet, cellRangeAddress, style);
                }
                if (excelheader.getHeadField() != null
                    && !field.contains(excelheader.getHeadField()))
                {
                    field.add(excelheader.getHeadField());
                }
                if (excelheader.getHeadField() != null
                    && !headmap.containsKey(excelheader.getHeadField()))
                {
                    headmap.put(excelheader.getHeadField(), excelheader);
                }

                cellnum++ ;
            }
        }
        return sheet;
    }

    /**
     * @Description: 获取头信息的层级数
     * @Check parameters by the 【itself】(参数由谁校验)
     * @param list
     *            头信息list
     * @return 层级数
     */
    public int getMaxHeadLevel(List<ExcelHeader> list)
    {
        int maxheadlevel = 0;
        if (list != null && list.size() != 0)
        {
            int temp = 0;
            for (int i = 0; i < list.size(); i++ )
            {
                if (list.get(i).getChildHead() != null && list.get(i).getChildHead().size() != 0)
                {
                    // 通过递归得到所有子级中，最深的结果
                    int maxHeadLevel2 = getMaxHeadLevel(list.get(i).getChildHead());
                    if (temp < maxHeadLevel2)
                    {
                        temp = maxHeadLevel2;
                    }
                }
            }
            maxheadlevel += temp;
            maxheadlevel++ ;
        }
        return maxheadlevel;
    }

    /**
     * @Description: 获取当前子级的宽度(多层子级横向占用多少个单元格，从而决定父级合并多少个单元格)
     * @Check parameters by the 【itself】(参数由谁校验)
     * @param list
     *            自己list
     * @return 占用多少个单元格
     */
    public int getMaxHeadWeight(List<ExcelHeader> list)
    {
        int maxheadweight = 0;
        if (list != null && list.size() != 0)
        {
            for (int i = 0; i < list.size(); i++ )
            {

                if (list.get(i).getChildHead() != null && list.get(i).getChildHead().size() != 0)
                {
                    int maxheadweight2 = getMaxHeadWeight(list.get(i).getChildHead());
                    maxheadweight += maxheadweight2;
                }
                else
                {
                    maxheadweight++ ;
                }
            }
        }
        return maxheadweight;
    }

    public SXSSFSheet bulidMessage(SXSSFWorkbook wb, SXSSFSheet sheet, List<ExcelMessage> message)
    {
        if (message != null)
        {
            int maxCellNum = 0;
            // 得到当前sheet中最后一行的索引值
            int rowindex = sheet.getLastRowNum();
            // 从header中遍历信息
            for (int i = message.size(); i <= rowindex; i++ )
            {
                // 行信息
                SXSSFRow row = sheet.getRow(i);
                // 这行有多少个列
                short lastCellNum = row.getLastCellNum();
                if (lastCellNum > maxCellNum)
                {
                    maxCellNum = lastCellNum;
                }
            }
            for (int i = 0; i < message.size(); i++ )
            {
                SXSSFRow row = sheet.createRow(i);
                SXSSFCell cell = row.createCell(0);
                ExcelMessage excelMessage = message.get(i);
                cell.setCellValue(message.get(i).getShowValue());
                CellStyle style = ExcelStyle.getStyle(wb, excelMessage.getStyle_vertical_type(),
                    excelMessage.getStyle_align_type(), excelMessage.getFont_family(),
                    excelMessage.getFont_size(), excelMessage.getFont_color(),
                    excelMessage.isFont_bold(), excelMessage.isBorder(),
                    excelMessage.getBackground_color());
                // 新列的样式
                cell.setCellStyle(style);
                CellRangeAddress cellRangeAddress = new CellRangeAddress(i, i, 0, maxCellNum - 1);
                sheet.addMergedRegion(new CellRangeAddress(i, i, 0, maxCellNum - 1));
                fillMergedRegion(sheet, cellRangeAddress, style);
            }
        }
        return sheet;
    }

    public void writeCell(SXSSFRow row, List<String> field, Map<String, ExcelHeader> headmap,
                          Object obj)
    {
        PropertyUtilsBean propertyUtilsBean = new PropertyUtilsBean();
        int cellindex = 0;
        ExcelHeader excelheader = headmap.get(field.get(0));
        if (isHavaNum)
        {
            SXSSFCell cell = row.createCell(cellindex);
            cell.setCellValue(listindex);
            // 新列的样式
            cell.setCellStyle(excelheader.getCellstyle());
            listindex++ ;
            cellindex++ ;
        }

        for (int i = 0; i < field.size(); i++ )
        {

            String name = field.get(i);
            excelheader = headmap.get(field.get(i));
            SXSSFCell cell = row.createCell(cellindex);
            // 新列的样式
            cell.setCellStyle(excelheader.getCellstyle());
            cellindex++ ;
            if (name != null)
            {
                try
                {
                    Object property = propertyUtilsBean.getProperty(obj, name);
                    if (property != null && excelheader.getBasechangevalue() != null)
                    {
                        property = excelheader.getBasechangevalue().changevalue(property);
                    }
                    if (property != null)
                    {
                        if (property instanceof Integer)
                        {
                            cell.setCellValue((Integer)property);
                        }
                        else if (property instanceof Double)
                        {
                            cell.setCellValue((Double)property);
                        }
                        else if (property instanceof Float)
                        {
                            cell.setCellValue((Float)property);
                        }
                        else if (property instanceof Long)
                        {
                            cell.setCellValue((Long)property);
                        }
                        else if (property instanceof Short)
                        {
                            cell.setCellValue((Short)property);
                        }
                        else if (property instanceof Byte)
                        {
                            cell.setCellValue((Byte)property);
                        }
                        else if (property instanceof Boolean)
                        {
                            cell.setCellValue((Boolean)property);
                        }
                        else if (property instanceof Calendar)
                        {
                            cell.setCellValue((Calendar)property);
                        }
                        else if (property instanceof Date)
                        {
                            cell.setCellValue((Date)property);
                        }
                        else
                        {
                            cell.setCellValue(property.toString());
                        }
                    }
                    // ExcelAutoSizeColumn.excelAutoSizeColumn(sheet, cell);
                }
                catch (IllegalAccessException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                catch (InvocationTargetException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                catch (NoSuchMethodException e)
                {
                    // TODO Auto-generated catch block
                    // e.printStackTrace();
                }

            }
        }
    }

    /**
     * 给合并的单元格增加样式
     * 
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @Check parameters by the 【caller】 or 【itself】(参数由谁校验)
     * @param sheet
     * @param address
     * @param style
     */
    private void fillMergedRegion(SXSSFSheet sheet, CellRangeAddress address, CellStyle style)
    {
        for (int i = address.getFirstRow(); i <= address.getLastRow(); i++ )
        {
            SXSSFRow row = sheet.getRow(i);
            if (row == null)
            {
                row = sheet.createRow(i);
            }
            for (int j = address.getFirstColumn(); j <= address.getLastColumn(); j++ )
            {
                SXSSFCell cell = row.getCell(j);
                if (cell == null)
                {
                    cell = row.createCell(j);
                    if (style != null)
                    {
                        cell.setCellStyle(style);
                    }
                }
            }
        }
    }

    private static final char defaultChar = '0';

    private static final double fontHeightMultiple = 2.0;

    private static final FontRenderContext fontRenderContext = new FontRenderContext(null, true,
        true);

    private static void copyAttributes(Font font, AttributedString str, int startIdx, int endIdx)
    {
        str.addAttribute(TextAttribute.FAMILY, font.getFontName(), startIdx, endIdx);
        str.addAttribute(TextAttribute.SIZE, (float)font.getFontHeightInPoints());
        if (font.getBoldweight() == Font.BOLDWEIGHT_BOLD)
            str.addAttribute(TextAttribute.WEIGHT, TextAttribute.WEIGHT_BOLD, startIdx, endIdx);
        if (font.getItalic())
            str.addAttribute(TextAttribute.POSTURE, TextAttribute.POSTURE_OBLIQUE, startIdx,
                endIdx);
        if (font.getUnderline() == Font.U_SINGLE)
            str.addAttribute(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON, startIdx, endIdx);
    }

    public static double getCellWidth(Cell cell, int defaultCharWidth, DataFormatter formatter,
                                      boolean useMergedCells)
    {
        Sheet sheet = cell.getSheet();
        Workbook wb = sheet.getWorkbook();
        Row row = cell.getRow();
        int column = cell.getColumnIndex();

        int colspan = 1;
        for (int i = 0; i < sheet.getNumMergedRegions(); i++ )
        {
            CellRangeAddress region = sheet.getMergedRegion(i);
            if (containsCell(region, row.getRowNum(), column))
            {
                if (!useMergedCells)
                {
                    // If we're not using merged cells, skip this one and move on to the next.
                    return -1;
                }
                cell = row.getCell(region.getFirstColumn());
                colspan = 1 + region.getLastColumn() - region.getFirstColumn();
            }
        }

        CellStyle style = cell.getCellStyle();
        int cellType = cell.getCellType();

        // for formula cells we compute the cell width for the cached formula result
        if (cellType == Cell.CELL_TYPE_FORMULA) cellType = cell.getCachedFormulaResultType();

        Font font = wb.getFontAt(style.getFontIndex());

        double width = -1;
        if (cellType == Cell.CELL_TYPE_STRING)
        {
            RichTextString rt = cell.getRichStringCellValue();
            String[] lines = rt.getString().split("\\n");
            for (int i = 0; i < lines.length; i++ )
            {
                String txt = lines[i] + defaultChar;

                AttributedString str = new AttributedString(txt);
                copyAttributes(font, str, 0, txt.length());

                if (rt.numFormattingRuns() > 0)
                {
                    // TODO: support rich text fragments
                }

                width = getCellWidth(defaultCharWidth, colspan, style, width, str);
            }
        }
        else
        {
            String sval = null;
            if (cellType == Cell.CELL_TYPE_NUMERIC)
            {
                // Try to get it formatted to look the same as excel
                try
                {
                    sval = formatter.formatCellValue(cell, dummyEvaluator);
                }
                catch (Exception e)
                {
                    sval = String.valueOf(cell.getNumericCellValue());
                }
            }
            else if (cellType == Cell.CELL_TYPE_BOOLEAN)
            {
                sval = String.valueOf(cell.getBooleanCellValue()).toUpperCase(Locale.ROOT);
            }
            if (sval != null)
            {
                String txt = sval + defaultChar;
                AttributedString str = new AttributedString(txt);
                copyAttributes(font, str, 0, txt.length());

                width = getCellWidth(defaultCharWidth, colspan, style, width, str);
            }
        }
        return width;
    }

    private static double getCellWidth(int defaultCharWidth, int colspan, CellStyle style,
                                       double width, AttributedString str)
    {
        TextLayout layout = new TextLayout(str.getIterator(), fontRenderContext);
        if (style.getRotation() != 0)
        {
            /*
             * Transform the text using a scale so that it's height is increased by a multiple of
             * the leading, and then rotate the text before computing the bounds. The scale results
             * in some whitespace around the unrotated top and bottom of the text that normally
             * wouldn't be present if unscaled, but is added by the standard Excel autosize.
             */
            AffineTransform trans = new AffineTransform();
            trans.concatenate(AffineTransform.getRotateInstance(style.getRotation() * 2.0
                                                                * Math.PI / 360.0));
            trans.concatenate(AffineTransform.getScaleInstance(1, fontHeightMultiple));
            width = Math.max(width,
                ((layout.getOutline(trans).getBounds().getWidth() / colspan) / defaultCharWidth)
                    + style.getIndention());
        }
        else
        {
            width = Math.max(width, ((layout.getBounds().getWidth() / colspan) / defaultCharWidth)
                                    + style.getIndention());
        }
        return width;
    }

    private static final FormulaEvaluator dummyEvaluator = new FormulaEvaluator()
    {
        public void clearAllCachedResultValues()
        {}

        public void notifySetFormula(Cell cell)
        {}

        public void notifyDeleteCell(Cell cell)
        {}

        public void notifyUpdateCell(Cell cell)
        {}

        public CellValue evaluate(Cell cell)
        {
            return null;
        }

        public Cell evaluateInCell(Cell cell)
        {
            return null;
        }

        public void setupReferencedWorkbooks(Map<String, FormulaEvaluator> workbooks)
        {}

        public void setDebugEvaluationOutputForNextEval(boolean value)
        {}

        public void setIgnoreMissingWorkbooks(boolean ignore)
        {}

        public void evaluateAll()
        {}

        public int evaluateFormulaCell(Cell cell)
        {
            return cell.getCachedFormulaResultType();
        }
    };

    public static boolean containsCell(CellRangeAddress cr, int rowIx, int colIx)
    {
        if (cr.getFirstRow() <= rowIx && cr.getLastRow() >= rowIx && cr.getFirstColumn() <= colIx
            && cr.getLastColumn() >= colIx)
        {
            return true;
        }
        return false;
    }
}
