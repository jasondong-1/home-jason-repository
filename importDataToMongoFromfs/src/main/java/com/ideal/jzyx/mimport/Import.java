package com.ideal.jzyx.mimport;

import com.ideal.jzyx.utils.MongoInfo;
import com.ideal.jzyx.utils.MongoUtil;
import com.mongodb.*;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.PathFilter;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/11/27.
 */
public class Import {
    public static void update(String path, String area) {
        Mongo mongo = null;
        try {
            mongo = MongoUtil.getMongo();
            Configuration config = new Configuration();
            FileSystem fs = FileSystem.get(config);
            FileStatus[] fileStatuses = fs.listStatus(new Path(path), new PathFilter() {
                public boolean accept(Path path) {
                    if (path == null || path.getName().equals("_SUCCESS")) {
                        return false;
                    } else {
                        return true;
                    }
                }

            });
            if (fileStatuses != null && fileStatuses.length != 0) {
                for (FileStatus fileStatus : fileStatuses) {
                    String filePath = fileStatus.getPath().toUri().getPath();
                    System.out.print("process file:" + filePath);
                    InputStream in = null;
                    in = fs.open(fileStatus.getPath());
                    List<MongoInfo> lists = new ArrayList<MongoInfo>();
                    for (LineIterator it = IOUtils.lineIterator(in, "utf-8"); it.hasNext(); ) {
                        String line = it.next();
                        if (StringUtils.isNotEmpty(line)) {
                            MongoInfo mi = makeLine(line, area);
                            if(mi!=null)
                            lists.add(mi);
                        }

                    }
                    process(mongo, lists);
                    lists = null;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(mongo!=null)
            MongoUtil.closeMongo(mongo);
        }

    }

    public static MongoInfo makeLine(String line, String area) {
        MongoInfo mi = new MongoInfo();
        String[] mes = line.split("\t");
        if (mes.length == 2) {
            String tag = mes[0];
            String ads = mes[1];
            mi.setAds(ads);
            mi.setCity(area);
            mi.setTag(tag);
            return mi;
        }
        return null;
    }
    public static void process(Mongo mongo,List<MongoInfo> list){
        DB db=mongo.getDB(MongoUtil.database);
        DBCollection coll=db.getCollection(MongoUtil.collection);
        for(MongoInfo mi:list){
            DBObject find=new BasicDBObject();
            DBObject update=new BasicDBObject();
            DBObject upsert=new BasicDBObject();
            find.put("city", mi.getCity());
            find.put("tag", mi.getTag());
            update.put("city", mi.getCity());
            update.put("tag", mi.getTag());
            update.put("ads", mi.getAds());
            upsert.put("$set", update);
            //���¼��ϣ������ҵ�find������£��������
            coll.update(find, upsert,true,true);
        }
    }
}
