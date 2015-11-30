package com.ideal.jzyx;

import com.ideal.jzyx.mimport.Import;

/**
 * Created by Administrator on 2015/11/27.
 */
public class ImportDataToMongo {
    public static void main(String[] args) {
        if(args==null||args.length!=2){
            System.out.println("reference [path of hdfs] [area]");
            System.exit(-1);
        }
        String path=args[0];
        String area=args[1];
        Import.update(path,area);
    }
}
