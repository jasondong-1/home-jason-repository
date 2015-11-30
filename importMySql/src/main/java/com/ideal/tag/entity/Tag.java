package com.ideal.tag.entity;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;

import com.google.common.collect.Maps;

public class Tag {	
	private Long id;
	//private int level;
	private String name;
	
	public Tag(){}
	
	public Tag(Long id, String name) {
		this.id = id;
		this.name = name;

	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

//	public int getLevel() {
//		return level;
//	}
//
//	public void setLevel(int level) {
//		this.level = level;
//	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Tag make(String line) {
		String[] parts = line.trim().split("\t",  -1);
		if(parts.length != 2) {
			throw new IllegalArgumentException("Initial failure because of the wrong numbers while prcoessing the 'tag'." + line);
		}
		this.setId(Long.parseLong(parts[1]));
		this.setName(parts[0]);
		//this.setLevel(Integer.parseInt(parts[2]));
		return this;
	}
	
	public static Map<String, Long> tag(String fileName) throws FileNotFoundException, IOException {
		Map<String, Long> tag = Maps.newHashMap();
		List<String> lines = IOUtils.readLines(Tag.class.getClassLoader().getResourceAsStream(fileName), "utf-8");
		for(String line : lines) {
			Tag tmp = new Tag();
			tmp.make(line);
			//tag.put(tmp.getLevel() + "\t" + tmp.getName(),  tmp.getId());
			tag.put(tmp.getName(),tmp.getId());
		}
		return tag;
	}
	
	

}
