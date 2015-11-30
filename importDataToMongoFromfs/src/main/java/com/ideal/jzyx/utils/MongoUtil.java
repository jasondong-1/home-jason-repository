package com.ideal.jzyx.utils;

import com.mongodb.Mongo;

import java.util.Properties;

public class MongoUtil {
    private static Properties conf;
    //��ȡ������
    public static String host;
    //��ȡ�˿�
    public static String port;
    public static String database;
    public static String collection;
    static {
        try {
            conf = ProUtil.getConf();
            host = conf.getProperty("host");
            port = conf.getProperty("port");
            database = conf.getProperty("database");
            collection=conf.getProperty("collection");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * ��ȡMongo����
     *
     * @return
     * @throws Exception
     */
    public static Mongo getMongo() {
        Mongo mongo = null;
        int p = Integer.valueOf(port);
        mongo = new Mongo(host, p);
        return mongo;
    }

    /**
     * �ر�mongo
     *
     * @param mongo
     */
    public static void closeMongo(Mongo mongo) {
        if (mongo != null)
            mongo.close();
    }

    public static void main(String[] args) {
        System.out.println(MongoUtil.database);
    }
}
