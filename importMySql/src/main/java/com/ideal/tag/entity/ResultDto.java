package com.ideal.tag.entity;

public class ResultDto {
	private long id;
	private long areaId;
	private long tagId;
	private int tagNums;
	private double percent;
	private double heatValue;
	private int userNum;

	public ResultDto() {

	}

	public ResultDto(long id, long areaId, long tagId, int tagNums, double percent, double heatValue, int userNum) {
		this.id = id;
		this.areaId = areaId;
		this.tagId = tagId;
		this.tagNums = tagNums;
		this.percent = percent;
		this.heatValue = heatValue;
		this.userNum = userNum;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getAreaId() {
		return areaId;
	}

	public void setAreaId(long areaId) {
		this.areaId = areaId;
	}

	public long getTagId() {
		return tagId;
	}

	public void setTagId(long tagId) {
		this.tagId = tagId;
	}

	public int getTagNums() {
		return tagNums;
	}

	public void setTagNums(int tagNums) {
		this.tagNums = tagNums;
	}

	public double getPercent() {
		return percent;
	}

	public void setPercent(double percent) {
		this.percent = percent;
	}

	public double getHeatValue() {
		return heatValue;
	}

	public void setHeatValue(double heatValue) {
		this.heatValue = heatValue;
	}

	public int getUserNum() {
		return userNum;
	}

	public void setUserNum(int userNum) {
		this.userNum = userNum;
	}

	@Override
	public String toString() {
		return "ResultDto{" +
				"id=" + id +
				", areaId=" + areaId +
				", tagId=" + tagId +
				", tagNums=" + tagNums +
				", percent=" + percent +
				", heatValue=" + heatValue +
				", userNum=" + userNum +
				'}';
	}
}
