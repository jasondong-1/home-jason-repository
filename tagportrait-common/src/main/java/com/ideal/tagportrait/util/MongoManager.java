package com.ideal.tagportrait.util;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;

import java.net.UnknownHostException;

/**
 * Created by zhou on 2015/10/29.
 */
public class MongoManager {
    private  MongoClient mongo=null;
    private  String host;
    private  String port;
    private  String dbName;

    private MongoManager(){}
    private void init() throws UnknownHostException {
        mongo=new MongoClient(host,new Integer(port));
    }

    public DBCollection getCollection(String collectionName){
        if(mongo==null){
            try {
                init();
            } catch (UnknownHostException e) {
                throw new RuntimeException(e);
            }
        }
        return mongo.getDB(dbName).getCollection(collectionName);
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host.trim();
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port.trim();
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }
}
