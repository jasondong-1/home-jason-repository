package com.ideal.tag;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;


import com.ideal.tag.entity.Area;
import com.ideal.tag.entity.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Initial {
	private static final Logger log = LoggerFactory.getLogger(Initial.class);

    public static final String TAG = "tag";
    public static final String AREA = "area";
    
    public static Map<String, Long> tag = null;
    public static Map<String, Long> area = null;

    public static void initial() {
    	try {
			tag = Tag.tag(TAG);
			area = Area.area(AREA);
		} catch(FileNotFoundException e) {
			log.error(e.getMessage(), e);
			System.exit(-1);
		} catch (IOException e) {
			log.error(e.getMessage(), e);
			System.exit(-1);
		}
    }

}
