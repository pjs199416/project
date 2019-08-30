package com.project.service;

import java.util.List;

import com.project.dao.DataDicDao;
import com.project.po.DataDicType;
import com.project.po.DataItem;

public class DataDicService {

	private DataDicDao dataDicDao = new DataDicDao();

	/**
	 * 查询所有数据字典类别信息
	 * @return
	 */
	public List<DataDicType> findAll() {
		
		return dataDicDao.findAll();
	}

	/**
	 * 根据id查询数据字典类别信息
	 * @param dataDicTypeId
	 * @return
	 */
	public DataDicType findDataDicTypeById(Long dataDicTypeId) {
		
		return dataDicDao.findDataDicTypeById(dataDicTypeId);
	}

	/**
	 * 根据数据字典类别id查询数据字典迭代描述信息
	 * @param dataDicTypeId
	 * @return
	 */
	public List<DataItem> findDataItemListByDataTypeId(Long dataDicTypeId) {
		
		return dataDicDao.findDataItemListByDataTypeId(dataDicTypeId);
	}

	/**
	 * 将添加的数据保存到数据库中
	 * @param dataItem
	 */
	public void addDataItem(DataItem dataItem,Long dataDicTypeId) {
		dataDicDao.save(dataItem,dataDicTypeId);
		
	}

	/**
	 * 根据id查询数据字典迭代项
	 * @param id
	 * @return
	 */
	public DataItem findDataItemById(Long id) {
		
		return dataDicDao.findDataItemById(id);
	}

	/**
	 * 把修改的数据保存到数据库中
	 * @param dataItem
	 */
	public void updateDataItem(DataItem dataItem) {
		dataDicDao.updateDataItem(dataItem);
		
	}

	/**
	 * 根据typeCode查询属于数据字典数据
	 * @param typecode
	 * @return
	 */
	public List<DataItem> findDataDicTypeBytypecode(String typecode) {
		Long datatypeId  = dataDicDao.findDataDicTypeByTypeId(typecode);
		List<DataItem>list = dataDicDao.getListDataItem(datatypeId);
		return list;
	}

	public void selected(String codeType, String itemValue) {
		//1.通过code码 找到数据字典项id
		Long dataTypeId = dataDicDao.findDataDicTypeByTypeId(codeType);
		//2.通过字典项id 找到数据字典迭代数据
		List<DataItem> list = dataDicDao.getListDataItem(dataTypeId);
		for(DataItem dataItem : list){
			if(dataItem.getItem_name().equals(itemValue)){
				dataDicDao.selected(1,dataItem.getId());
			} else {
				dataDicDao.selected(0,dataItem.getId());
			}
		}
	}
}
