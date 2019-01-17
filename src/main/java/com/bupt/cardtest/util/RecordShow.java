package com.bupt.cardtest.util;


import com.bupt.cardtest.model.pagebean.RecordPage;

import java.util.List;

public class RecordShow {
    public static void recordshow(List<RecordPage> l){

        if (l==null||l.size()==0) return;
        RecordPage a0=l.get(0);
        //设置第一条记录持续时间和上条间隔
        if (a0.getStart()!=null&&a0.getEnd()!=null){
            a0.setLast(a0.getEnd()-a0.getStart());
        }
        a0.setInterval(0);

        for (int i = 1; i <l.size() ; i++) {
            RecordPage r=l.get(i);
            if (r.getStart()!=null&&r.getEnd()!=null){
                r.setLast(r.getEnd()-r.getStart());//设置指令持续时间
            }
            if (l.get(i-1).getEnd()!=null&&r.getStart()!=null){
                //设置指令间隔时间
                r.setInterval(r.getStart()-l.get(i-1).getEnd());
            }
        }
    }
}
