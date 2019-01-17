package com.bupt.cardtest.changevalue;

import com.bupt.cardtest.excelPOI.BaseChangeValue;
import com.bupt.cardtest.util.DateUtil;


import java.util.Date;

public class ChangeValueDate extends BaseChangeValue {

	@Override
	public Object changevalue(Object obj) {
		if (obj == null) {
			return "";
		} else if (obj instanceof Date) {
			return DateUtil.format((Date) obj, "yyyy-MM-dd HH:mm:ss");
		}
		return "";
	}

}
