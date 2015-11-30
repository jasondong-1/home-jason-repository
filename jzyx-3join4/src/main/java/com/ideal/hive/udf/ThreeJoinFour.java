/*
* Copyright (C) China Telecom Corporation Limited, Cloud Computing Branch Corporation - All Rights Reserved
*
* Unauthorized copying of this file, via any medium is strictly prohibited
*
* Proprietary and confidential
*
* Contributors:
*     郭飞, g_fei@189.cn, 2015
*/ 
package com.ideal.hive.udf;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.Text;

public class ThreeJoinFour extends UDF {

	/**
	 * tag1:5;tag2:6
	 * tag1:3;tag2:4;tag3=5
	 * @param todayLine 4g
	 * @param sumLine	3g
	 * @return
	 */
	public static Text evaluate( String todayLine, String sumLine){
		try {
			//4gmap 将tag字符串转换为  标签 权重的map  null
			Map<String, Double> todayMap = getMap(todayLine);
			//3gmap 将tag字符串转换为  标签 权重的map
			Map<String, Double> sumMap = getMap(sumLine);
			//创建map存储最终结果
			Map<String, Double> desMap = new HashMap<String, Double>();
			//将 4g数据tag存入set
			Set<String> set1 = getSet(todayLine);
			//将 3g 数据tag存入set
			Set<String> set2 = getSet(sumLine);
			//取标签的交集
			Set<String> setInter = intersection(set1, set2);
			//取标签的差集 4g有，3g没有
			Set<String> set12 = diffSet(set1, set2);
			//取标签的差集 3g有，4g没有
			Set<String> set21 = diffSet(set2, set1);
			//遍历交集的setInter
			if (setInter != null) {
				for (String str : setInter) {
					double i = 0.0;
					double j = 0.0;
					if (todayMap != null) {
						i = todayMap.get(str);
					}
					if (sumMap != null) {
						j = sumMap.get(str);
					}
					desMap.put(str, i + j);
				}
			}
			//遍历set1-set2
			if (set12 != null) {
				for (String str : set12) {
					double i = 0.0;
					i = todayMap.get(str);
					desMap.put(str, i);
				}
			}
			//遍历set2-set1
			if (set21 != null) {
				for (String str : set21) {
					double i = 0.0;

					i = sumMap.get(str);

					desMap.put(str, i);
				}
			}
			String strl = "";
			Text text1 = null;
			Set<Entry<String, Double>> deSet = desMap.entrySet();
			Iterator<Entry<String, Double>> it = deSet.iterator();
			while (it.hasNext()) {
				Entry<String, Double> entry = it.next();
				String key = entry.getKey();
				double value = entry.getValue();
				strl += key + ":" + String.valueOf(value) + ";";
				text1 = new Text(strl);
			}
			return text1;
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public static  Map<String,Double> getMap(String line){
		try {
			Map<String, Double> map = new HashMap<String, Double>();
			if (line == null) {
				return null;
			}
			String[] str = line.split(";");

			for (String str1 : str) {
				String[] str2 = str1.split(":");

				//System.out.println(str2[0]+"   "+str2[1]);
				map.put(str2[0], Double.parseDouble(str2[1]));

			}
			return map;
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}
	public static Set<String> getSet(String line){
		try {
			Set<String> set = new HashSet<String>();
			if (line == null) {
				return null;
			}
			String[] str = line.split(";");
			for (String str1 : str) {
				String[] str2 = str1.split(":");
				set.add(str2[0]);
			}
			return set;
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}
	public static Set<String> intersection(Set<String> set1,Set<String>set2 ){
		try {
			if (set1 == null || set2 == null) {
				return null;
			}
			Set<String> set = new HashSet<String>();
			for (String str1 : set1) {
				for (String str2 : set2) {
					if (str1.equals(str2)) {
						set.add(str1);
					}
				}
			}
			return set;
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public static Set<String> diffSet(Set<String> set1,Set<String>set2 ){
		try {
			Set<String> set = new HashSet<String>();
			if (set2 == null) {
				return set1;
			}
			if (set1 == null) {
				return null;
			}
			for (String str1 : set1) {
				if (!set2.contains(str1)) {
					set.add(str1);
				}
			}
			return set;
		}catch (Exception e){
			e.printStackTrace();
		}
				return null;
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		String s ="tag1:3;tag2:4;tag3=5;";
//		System.out.println(s.substring(0, s.length()-1));
		
		String t2 = "电商购物,团购:1;社交/沟通,通信,即时通信:2;淘宝网:1;腾讯:20";
//		String t1 =null;
//		String t2 = "电商购物,团购:1;社交/沟通,通信,即时通信:2;淘宝网:1;百度:18";
		String t1 = null;
		Text evaluate = evaluate(t1,t2);
		System.out.println(evaluate.toString());
	}

}
