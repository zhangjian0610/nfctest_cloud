package com.bupt.cardtest.changevalue;

import com.bupt.cardtest.excelPOI.BaseChangeValue;

public class ChangeValueDivisionStatus extends BaseChangeValue {

	@Override
	public Object changevalue(Object obj) {
		if (obj instanceof Integer) {
			int type = Integer.parseInt(obj.toString());
			if (type == 1) {
				return "未开始";
			} else if (type == 2) {
				return "已开始";
			} else {
				return "已结束";
			}
		}
		return null;
	}

}
