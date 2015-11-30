package com.ideal.tag.entity;


public class Result {
	//private int level;
	private String name;
	private int userNums;//鐢ㄦ埛鏁�	
	private double tagNums;//鏉冮噸
	private double heatValue;
//	public int getLevel() {
//		return level;
//	}
//	public void setLevel(int level) {
//		this.level = level;
//	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getUserNums() {
		return userNums;
	}
	public void setUserNums(int userNums) {
		this.userNums = userNums;
	}
	public double getTagNums() {
		return tagNums;
	}
	public void setTagNums(double tagNums) {
		this.tagNums = tagNums;
	}
	public double getHeatValue() {
		return heatValue;
	}
	public void setHeatValue(double heatValue) {
		this.heatValue = heatValue;
	}
	
	public Result(int level, String name, int userNums, int tagNums,
			double heatValue) {
		super();
		//this.level = level;
		this.name = name;
		this.userNums = userNums;
		this.tagNums = tagNums;
		this.heatValue = heatValue;
	}
	
	public Result(){};

	 // 2       鏃ョ敤甯傚満        1       1       1.00
	public Result make(String line) {
		String[] parts = line.split("\t",  -1);
		if(parts.length != 4) {
			throw new IllegalArgumentException("Initial failure because of the wrong numbers while prcoessing the 'result'. --> " + line);
		}
		//this.setLevel(Integer.parseInt(parts[0]));
		this.setName(parts[0]);
		this.setUserNums(Integer.parseInt(parts[1]));
		this.setTagNums(Double.parseDouble(parts[2]));
		this.setHeatValue(Double.parseDouble(parts[3]));
		return this;
	}

	@Override
	public String toString() {
		return "Result{" +
				"name='" + name + '\'' +
				", userNums=" + userNums +
				", tagNums=" + tagNums +
				", heatValue=" + heatValue +
				'}';
	}
}
