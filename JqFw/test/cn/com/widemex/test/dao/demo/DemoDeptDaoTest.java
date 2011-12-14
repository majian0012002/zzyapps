package cn.com.widemex.test.dao.demo;


import org.junit.Before;
import org.junit.Test;

import cn.com.widemex.core.utils.reflection.Bean;
import cn.com.widemex.dao.demo.DemoDeptDao;

public class DemoDeptDaoTest {
	DemoDeptDao demoDeptDao;
	
	@Before
	public void setUp() throws Exception {
		demoDeptDao = Bean.get(DemoDeptDao.class);
	}

	@Test
	public void list(){
		System.out.println("共：" + demoDeptDao.getAll().size());
	}
}
