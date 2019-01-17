/**
 * 文件名：ExcelBaseBean.java 版权：Copyright by www.bjleisen.com 描述： 修改人：zhangziqi 修改时间：2015年12月7日 修改内容：
 */

package com.bupt.cardtest.excelPOI;


import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.util.HSSFColor;


public abstract class ExcelBaseBean
{

    /**
     * excel要显示的内容
     */
    private String showValue;

    /**
     * 此表头的垂直排版方式，默认为垂直居中 HSSFCellStyle.VERTICAL_CENTER 垂直居中
     */
    private short style_vertical_type = HSSFCellStyle.VERTICAL_CENTER;

    /**
     * 此表头的垂直排版方式，默认为水平居中 HSSFCellStyle.ALIGN_CENTER 水平居中
     */
    private short style_align_type = HSSFCellStyle.ALIGN_CENTER;

    /**
     * 字体大小，默认11
     */
    private short font_size = 11;

    /**
     * 字体 默认宋体
     */
    private String font_family = "宋体";

    /**
     * 字体颜色 默认黑色
     */
    private short font_color = HSSFColor.BLACK.index;

    /**
     * 字体是否加粗 默认不加粗
     */
    private boolean font_bold = false;

    /**
     * 是否有边框 默认有 
     */
    private boolean border = true; 
    
    /**
     * 背景色 默认白色
     */
    private short background_color = HSSFColor.WHITE.index;
    
    /**
     * 内容改变类
     */
    public BaseChangeValue basechangevalue;

    public short getStyle_vertical_type()
    {
        return style_vertical_type;
    }

    public void setStyle_vertical_type(short style_vertical_type)
    {
        this.style_vertical_type = style_vertical_type;
    }

    public short getStyle_align_type()
    {
        return style_align_type;
    }

    public void setStyle_align_type(short style_align_type)
    {
        this.style_align_type = style_align_type;
    }

    public short getFont_size()
    {
        return font_size;
    }

    public void setFont_size(short font_size)
    {
        this.font_size = font_size;
    }

    public String getFont_family()
    {
        return font_family;
    }

    public void setFont_family(String font_family)
    {
        this.font_family = font_family;
    }

    public short getFont_color()
    {
        return font_color;
    }

    public void setFont_color(short font_color)
    {
        this.font_color = font_color;
    }

    public boolean isFont_bold()
    {
        return font_bold;
    }

    public void setFont_bold(boolean font_bold)
    {
        this.font_bold = font_bold;
    }

    public String getShowValue()
    {
        return showValue;
    }

    public void setShowValue(String showValue)
    {
        this.showValue = showValue;
    }

    public BaseChangeValue getBasechangevalue()
    {
        return basechangevalue;
    }

    public void setBasechangevalue(BaseChangeValue basechangevalue)
    {
        this.basechangevalue = basechangevalue;
    }

    public boolean isBorder()
    {
        return border;
    }

    public void setBorder(boolean border)
    {
        this.border = border;
    }

    public short getBackground_color()
    {
        return background_color;
    }

    public void setBackground_color(short background_color)
    {
        this.background_color = background_color;
    }

}
