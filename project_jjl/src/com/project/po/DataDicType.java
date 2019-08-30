package com.project.po;

import java.io.Serializable;

/** 数据字典类别表 */
public class DataDicType implements Serializable {
	/** 数据字典类别 id  */
	private Long id;
	/** 数据字典类别代号   */
	private String typeCode;
	/** 数据字典类别名称  */
	private String typeName;
	/** 数据字典类别备注  */
	private String memo;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	
}
