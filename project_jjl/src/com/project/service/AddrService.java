package com.project.service;

import java.util.List;

import com.project.dao.AddrDao;
import com.project.po.Addr;

/**
 * 地址信息业务层处理
 * @author Administrator
 *
 */
public class AddrService {

	private AddrDao addrDao = new AddrDao();
	
	/**
	 * 查询所有省
	 * @return
	 */
	public List<Object[]> findProvinceAll() {
		
		return addrDao.findProvinceAll();
	}
	/**
	 * 查询所有市
	 * @param provinceId
	 * @return
	 */
	public List<Object[]> findCityAll(Long provinceId) {
		
		return addrDao.findCityAll(provinceId);
	}
	/**
	 * 查询所有县或区
	 * @param cityId
	 * @return
	 */
	public List<Object[]> findDistrictAll(Long cityId) {
		
		return addrDao.findDistrictAll(cityId);
	}
	/**
	 * 把添加的地址保存到数据库
	 * @param addr
	 */
	public void saveAddr(Addr addr) {
		addrDao.saveAddr(addr);
		
	}
	/**
	 * 查询所有地址信息
	 * @param user_id
	 * @return
	 */
	public List<Addr> findAddrAll(Long user_id) {
		
		return addrDao.findAddrAll(user_id);
	}
	
	/**
	 * 根据id删除地址信息
	 * @param id
	 */
	public void deleteAddr(String id) {
		addrDao.deleteAddr(id);
		
	}
	
	/**
	 * 根据id查询所地址信息
	 * @param id
	 * @return
	 */
	public Addr findAddrById(Long id) {
		
		return addrDao.findAddrById(id);
	}
	
	/**
	 * 修改地址信息
	 * @param addr
	 */
	public void updateAddr(Addr addr) {
		addrDao.updateAddr(addr);
		
	}
	public Addr setDefaultAddr(Long id) {
		Addr addr = addrDao.findAddrById(id);
		addr.setStatus(1);
		addrDao.updateAddrStatus(addr);
		return addr;
	}

}
