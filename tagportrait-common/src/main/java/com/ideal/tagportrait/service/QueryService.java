package com.ideal.tagportrait.service;

import com.ideal.tagportrait.dto.PageModel;
import com.ideal.tagportrait.util.FilterParser;
import com.ideal.tagportrait.util.MongoManager;
import com.mongodb.*;
import com.mongodb.util.Hash;
import org.bson.BSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.net.UnknownHostException;
import java.util.*;
import java.util.regex.Pattern;

/**
 * Created by zhou on 2015/10/29.
 */
@Service
public class QueryService {
    private Logger logger = LoggerFactory.getLogger(QueryService.class);
    @Resource
    private MongoManager mongoManager;

    public PageModel query(String filter, String city,int resultType) {
        Assert.hasLength(filter, "---过滤条件不能为空---");
        Assert.hasLength(city, "---城市不能为空---");
        HashSet<String> strings = new HashSet<>();
        FUN fun=FUN.GETMDN;
        if(resultType==1)fun=FUN.GETMEID;
        queryResult(strings, new FilterParser(filter), city.trim(),fun);
        return new PageModel(strings.size(), strings);
    }

    public void queryResult(HashSet<String> rs, FilterParser parser, String city,FUN fun) {
        FilterParser.Rule rule = parser.getNextRule();
        String tag = parser.getNext().trim();
        DBCollection collection = mongoManager.getCollection("adTag_ciphertext");
        BasicDBObject object = new BasicDBObject("city", city).append("tag", tag);
        BasicDBObject k1 = new BasicDBObject("ads", 1);
        DBObject one = collection.findOne(object, k1);
        List<String> mdmes = null;
        if (one != null) {
            Object o = one.get("ads");//adxxx+meidsxxx_MEIDmeidxxxx:n,
            if (o != null) {
                String s = (String) o;
                if (!s.isEmpty()) {
                    mdmes = new ArrayList<String>(200);
                    String[] adss = s.split(",");
                    for (String a : adss) {
                        fun.getValue(mdmes,a);
                    }
                }
            }
        }
        rule.comp(rs, mdmes);
        if (parser.hasNext()) {
            queryResult(rs, parser, city,fun);
        }
    }

    private enum FUN{
        GETMDN{
            @Override
            public void getValue(List<String> mdmes, String record) {
                //返回手机号
                mdmes.add(record.split(":")[0].split(SEPARATOR_AD)[0]);
            }
        },
        GETMEID{
            @Override
            public void getValue(List<String> mdmes, String record) {
                //返回MEID
                String[] admeArr=record.split(":")[0].split(SEPARATOR_AD);
                if(admeArr.length==1||admeArr[1].isEmpty())return;
                String[] meArr=admeArr[1].split(SEPARATOR_MEID);
                for(String m :meArr){
                    mdmes.add(m);
                }
            }
        };
        final String SEPARATOR_AD="\\+";
        final String SEPARATOR_MEID="_MEID_";
        public abstract void getValue(List<String> mdmes,String record);
    }
}
