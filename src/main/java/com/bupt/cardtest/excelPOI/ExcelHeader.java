/**
 * 文件名：ExcelHeader.java
 * 版权：Copyright by www.bjleisen.com
 * 描述：
 * 修改人：zhangziqi
 * 修改时间：2015年12月3日
 * 修改内容：
 */

package com.bupt.cardtest.excelPOI;

import org.apache.poi.ss.usermodel.CellStyle;

import java.util.List;

public class ExcelHeader extends ExcelBaseBean
{
    
    /**
     * 要绑定的字段名(反射用到)
     * 如果是复合表头无绑定字段，可不填
     */
    private String headField;
    
    /**
     * 样式
     */
    private CellStyle cellstyle;
    
    /**
     * 用于复合表头中的子表头，无限递归方式
     * 如果不是符合表头，或者无子表头，可不填
     */
    private List<ExcelHeader> childHead; 
    
    public String getHeadField()
    {
        return headField;
    }

    public void setHeadField(String headField)
    {
        this.headField = headField;
    }

    public List<ExcelHeader> getChildHead()
    {
        return childHead;
    }

    public void setChildHead(List<ExcelHeader> childHead)
    {
        this.childHead = childHead;
    }

    public CellStyle getCellstyle()
    {
        return cellstyle;
    }

    public void setCellstyle(CellStyle cellstyle)
    {
        this.cellstyle = cellstyle;
    }



  
}
