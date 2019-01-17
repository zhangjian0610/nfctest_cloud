package com.bupt.cardtest.changevalue;

import com.bupt.cardtest.excelPOI.BaseChangeValue;

public class ChangeValueInteger extends BaseChangeValue {

	@Override
	public Object changevalue(Object obj) {
		if (obj == null) {
			return 0;

		}
		if (obj instanceof Integer) {
			Integer Int = Integer.parseInt(obj.toString());
			if (Int.intValue() == 0) {
				return 0;
			}
			return Int;
		}
		return 0;
	}

}
