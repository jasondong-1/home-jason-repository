package com.ideal.hive.udf;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.Text;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2015/11/6.
 */
public class Meid  extends UDF{
    public static Text evaluate(String meid3,String meid4){
        //设置set用于存放meid
        Set<String> meidSet=trans(meid3,meid4);
        String meids="";
        for(String s:meidSet){
            meids=meids+s+"_MEID_";
        }
        StringBuilder builder = new StringBuilder(meids);
        if(!StringUtils.isEmpty(meids)) {
            builder.delete(meids.lastIndexOf("_MEID_"), meids.length());
        }
        return new Text(builder.toString());

    }
    /**
     * 该方法用于将meid号由String变为set
     */
    public static Set<String> trans(String meid3,String meid4){
        Set<String> set=new HashSet<String>();
        if(!StringUtils.isEmpty(meid3)){
            String[] meids=meid3.split(",");
            for(String s:meids){
                set.add(s);
            }
        }

        if(!StringUtils.isEmpty(meid4)){
            String[] meids=meid4.split(",");
            for(String s:meids){
              set.add(s);
            }
        }
        return set;

    }


}
