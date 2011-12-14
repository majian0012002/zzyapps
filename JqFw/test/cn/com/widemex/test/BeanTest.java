package cn.com.widemex.test;


import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import cn.com.widemex.core.utils.reflection.Bean;
import cn.com.widemex.dao.demo.DemoTypeDao;
public class BeanTest extends TestCase{

	private DemoTypeDao demoTypeDao;
	
	@Before
	public void setUp() throws Exception {
//		demoTypeDao = (DemoTypeDao) Bean.get( "demoTypeDao" );
//		demoTypeDao = Bean.get( "demoTypeDao", DemoTypeDao.class );
		demoTypeDao = Bean.get( DemoTypeDao.class );
		
	}
	
	@Test
	public void test(){
		System.out.println("共：" + demoTypeDao.getAll().size());
	}
	
	
	

}
