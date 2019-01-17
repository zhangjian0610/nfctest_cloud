/**
 * 文件名：E.java
 * 版权：Copyright by www.bjleisen.com
 * 描述：
 * 修改人：zhangziqi
 * 修改时间：2015年12月2日
 * 修改内容：
 */

package com.bupt.cardtest.excelPOI;


import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

public class ExcelStyle
{

    public static CellStyle getStyle(SXSSFWorkbook wb,
                                     short vertical_type,
                                     short align_type,
                                     String fontname,
                                     short fontsize,
                                     short fontcolor,
                                     boolean fontbold,
                                     boolean border,
                                     short backgroundcolor)
    
    {
        CellStyle style = wb.createCellStyle();
        style.setVerticalAlignment(vertical_type);
        style.setAlignment(align_type);
        Font font = getFontStyle(wb, fontname, fontsize,fontcolor,fontbold);
        style.setFont(font);
        
        if(border)
        {
            style.setBorderTop((short)1);
            style.setBorderBottom((short)1);
            style.setBorderLeft((short)1);
            style.setBorderRight((short)1);
        }
        style.setFillForegroundColor(HSSFColor.WHITE.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND); 
        
        return style;
    }
    
    
    public static Font getFontStyle(SXSSFWorkbook wb,
                                    String fontname,
                                    short fontsize,
                                    short fontcolor,
                                    boolean fontbold)
    {
        Font font = wb.createFont();
        font.setFontName(fontname);
        font.setColor(fontcolor);
        font.setBold(fontbold);
        font.setFontHeightInPoints(fontsize);
        return font;
    }
    
}
