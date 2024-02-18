package com.casia.isiteam.mogodb.common;

/**
 * 枚举类 - 排序
 * @author zhangqingzhou
 *
 */
public enum MgDBSort {

	ASC(1, "升序"),

	DESC(-1, "降序");

	int value;
	String remark;


	private MgDBSort(int value, String remark) {
		this.value = value;
		this.remark = remark;
	}


	public int getVal(){

		return this.value;
	}

	public String getRemark(){

		return this.remark;
	}
}
