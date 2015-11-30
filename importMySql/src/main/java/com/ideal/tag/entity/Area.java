package com.ideal.tag.entity;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;

import com.google.common.collect.Maps;

public class Area {
	private Long id;
	private String name;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Area(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public Area(){};
	
	public Area make(String line) {
		String[] parts = line.trim().split("\t");
		if(parts.length != 2) {
			throw new IllegalArgumentException("Initial failure because of the wrong numbers while prcoessing the 'area'.");
		}
		this.setId(Long.parseLong(parts[0]));
		this.setName(parts[1]);
		return this;
	}

	public static Map<String, Long> area(String fileName) throws FileNotFoundException, IOException {
		Map<String, Long> area = Maps.newHashMap();
		List<String> lines = IOUtils.readLines(Area.class.getClassLoader().getResourceAsStream(fileName), "utf-8");
		for(String line : lines) {
			Area tmp = new Area();
			tmp.make(line);
			area.put(tmp.getName(),  tmp.getId());
		}
		return area;
	}
}
