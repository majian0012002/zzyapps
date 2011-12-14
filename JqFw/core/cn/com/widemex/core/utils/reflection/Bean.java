package cn.com.widemex.core.utils.reflection;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.hibernate.Hibernate;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.id.UUIDHexGenerator;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.com.widemex.core.json.hibernate.HibernateModule;
import cn.com.widemex.core.utils.data.DateHelper;

/**
 * 封装了Bean的获取、反射、GET、SET等
 * @author 张中原
 * @version JqFw
 *
 */
public class Bean{
	protected static Log logger = LogFactory.getLog(Bean.class);
	
	public static BeanFactory beanFactory = null;
	
	/**
	 * 获取getBeanFactory
	 * @return
	 */
	private static BeanFactory getBeanFactory(){
		if(beanFactory == null){
			logger.info("获取不到WebApplicationContext,手工加载appContext");
			beanFactory = new ClassPathXmlApplicationContext("classpath*:/config/spring/core/applicationContext.xml");;
		}
		return beanFactory;
	}
	
	/**
	 * 根据beanName返回bean对象
	 * @param String beanName 
	 * @return Object
	 */	
	public static Object get(String beanName){
		return getBeanFactory().getBean(beanName); 
	}
	
	/**
	 * 根据class类获取bean对象
	 * @param <T>
	 * @param cls
	 * @return
	 */
	public static <T> T get(Class<T> cls){
		return getBeanFactory().getBean(cls); 
	}
	
	
	/**
	 * 根据beanName和class类型获取bean对象
	 * @param <T>
	 * @param beanName
	 * @param cls
	 * @return
	 */
	public static <T> T get(String beanName, Class<T> cls){
		return getBeanFactory().getBean(beanName, cls);
	}
	

	/**
	 * 打印request中的所有参数
	 * @param HttpServletRequest request
	 */	
	public static void printReq(HttpServletRequest request) {
		logger.info("开始打印查询参数[" + DateHelper.getDateString("yyyy-MM-dd hh:mm:ss") + "]-------------------------------------");
		for (Enumeration<String> em = request.getParameterNames(); em.hasMoreElements();) {
			String key = em.nextElement();
			logger.info(key + ":" + request.getParameter(key));
		}
		logger.info("-------------------------------------------------------------------------\n\n");
	}

	/**
	 * 执行某对象的方法(带参数)
	 * @param Object 类
	 * @param String 方法名称
	 * @param Object[] 参数
	 * @return Object
	 * @throws ExtFwException 
	 */
	public static Object invokeMethod(Object owner, String methodName, Object[] args) throws Exception {
	    Class<?> ownerClass = owner.getClass();
	    Class[] argsClass = new Class[args.length];
	    for (int i = 0, j = args.length; i < j; i++) {
	        argsClass[i] = args[i].getClass();
	        if(("org.apache.catalina.connector.RequestFacade").equals(argsClass[i].getName())){
	        	argsClass[i] = javax.servlet.http.HttpServletRequest.class;
	        }
	    }
		try {
			Method method = ownerClass.getMethod(methodName, argsClass);
			return method.invoke(owner, args);
		}  catch (SecurityException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
			throw new Exception(e);
		} catch (IllegalArgumentException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			throw new Exception(e.getTargetException());
		}
	    return null;
	}
	
	/**
	 * 执行静态对象的静态方法(带参数)
	 * @param Object 类
	 * @param String 方法名称
	 * @param Object[] 参数
	 * @return Object
	 */
	public static Object invokeStaticMethod(String className, String methodName, Object[] args) throws Exception {
//		try {
			return invokeStaticMethod(Class.forName(className), methodName, args);
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return null;
	}
	public static Object invokeStaticMethod(Class<?> ownerClass, String methodName, Object[] args) throws Exception{
//		try {
		    Class[] argsClass = new Class[args.length];
		    for (int i = 0, j = args.length; i < j; i++) {
		        argsClass[i] = args[i].getClass();
		    }
			Method method = ownerClass.getMethod(methodName, argsClass);
			return method.invoke(ownerClass, args);
//		} catch (SecurityException e) {
//			// TODO 自动生成 catch 块
//			e.printStackTrace();
//		} catch (NoSuchMethodException e) {
//			// TODO 自动生成 catch 块
//			e.printStackTrace();
//		} catch (IllegalArgumentException e) {
//			// TODO 自动生成 catch 块
//			e.printStackTrace();
//		} catch (IllegalAccessException e) {
//			// TODO 自动生成 catch 块
//			e.printStackTrace();
//		} catch (InvocationTargetException e) {
//			// TODO 自动生成 catch 块
//			e.printStackTrace();
//		}
//	    return null;
	}
	
	/**
	 * 执行某对象的方法(不带参数)
	 * @param Object 类
	 * @param String 方法名称
	 * @return Object
	 * @throws ExtFwException 
	 */	
	public static Object invokeMethod(Object owner, String methodName) throws Exception{
		return invokeMethod(owner, methodName, new Object[0]);
	}
	
	/**
	 * 得到对象某个属性
	 * @param Object 类
	 * @param String 属性名称
	 * @param boolean true: 返回属性值　false: 返回属性
	 * @return Object
	 */
	public static Object getProperty(Object owner, String propertyName, boolean f){
	    Class<?> ownerClass = owner.getClass();
		try {
			Field field = ownerClass.getField(propertyName);
			if(!f) return field;
		    Object property = field.get(owner);
		    return property;
		} catch (SecurityException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO 自动生成 catch 块
			//e.printStackTrace();
			return null;
		} catch (IllegalArgumentException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 返回对象某个属性的值
	 * @param Object 类
	 * @param String 属性名称
	 * @return Object
	 */
	public static Object getPropertyValue(Object owner, String propertyName) {
		return getProperty(owner, propertyName, true);
	}
	
	/**
	 * 判断对象某个属性是否存在
	 * @param Object 类
	 * @param String 属性名称
	 * @return boolean
	 */
	public static boolean beingProperty(Object owner, String propertyName) {
		Object property = getProperty(owner, propertyName, false);
		return property != null;
	}
	
	/**
	 * 打印po所有get方法的值
	 * @param po
	 */
	public static void printValues(Object po){
		Class<?> ownerClass = po.getClass();
		int j = 0;
		for (Method method : ownerClass.getMethods()) {
			String methodName = method.getName();
			if (isGetter(method)) {
				try {
					System.err.println("["+(j++)+"]" + methodName + ":" + invokeMethod(po, methodName));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 将oldPo中不为null的值复制到newPo中
	 * @param oldPo
	 * @param newPo
	 */
	public static void copy(Object oldPo, Object newPo){
		copy(oldPo, newPo, false);
	}
	/**
	 * 将oldPo中不为null的值复制到newPo中
	 * @param oldPo
	 * @param newPo
	 * @param print
	 */
    public static void copy(Object oldPo, Object newPo, boolean print) {
        Class<?> oldPoClass = oldPo.getClass();
        Class<?> newPoClass = newPo.getClass();

        for (Method method : oldPoClass.getMethods()) {
            if (method.getName().equals("getClass")) {
                continue;
            }
            if (isGetter(method)) {
                try {
                    Object result = method.invoke(oldPo);
                    if (result == null) {
                        continue;
                    } 
                    else if (result instanceof Collection<?>) {
                        if (((Collection<?>) result).isEmpty()) {
                            continue;
                        }
                    } 
                    else if (result.getClass().isArray()) {
                        if (result instanceof Object[]) {
                            if (((Object[]) result).length == 0) {
                                continue;
                            }
                        } 
                        else if (result instanceof byte[]) {
                            if (((byte[]) result).length == 0) {
                                continue;
                            }
                        } 
                        else if (result instanceof short[]) {
                            if (((short[]) result).length == 0) {
                                continue;
                            }
                        } 
                        else if (result instanceof int[]) {
                            if (((int[]) result).length == 0) {
                                continue;
                            }
                        } 
                        else if (result instanceof long[]) {
                            if (((long[]) result).length == 0) {
                                continue;
                            }
                        } 
                        else if (result instanceof float[]) {
                            if (((float[]) result).length == 0) {
                                continue;
                            }
                        } 
                        else if (result instanceof double[]) {
                            if (((double[]) result).length == 0) {
                                continue;
                            }
                        } 
                        else if (result instanceof char[]) {
                            if (((char[]) result).length == 0) {
                                continue;
                            }
                        } 
                        else if (result instanceof boolean[]) {
                            if (((boolean[]) result).length == 0) {
                                continue;
                            }
                        }
                    }

                    //
                    String methodName = getter2Setter(method.getName());
                    Class<?> methodParam = method.getReturnType();
                    
                    try{
                    	Method setter = newPoClass.getMethod(methodName, methodParam);
	                    if(print){
		                    System.out.println("方法名称：" + methodName);
		                    System.out.println("参数类型：" + methodParam);
		                    System.out.println("set方法：" + setter);
		                    System.out.println();
	                    }
	                    //
	                    setter.invoke(newPo, result);
                    }
                    catch(NoSuchMethodException e){
                    	logger.warn("目标对象缺少" + e.getMessage() + "方法");
                    }
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
    
    /**
     * 返回是否为get方法
     * @param method
     * @return boolean
     */
    public static boolean isGetter(Method method) {
        String name = method.getName();
        if (method.getParameterTypes().length == 0 && 	//方法没有参数
        	!name.equals("getClass") &&					//方法名称<>getClass
        	name.startsWith("get") && 					//方法以get开头
        	name.length() > 3){							//方法名称3个字符以上
            return true;
        }
        else {
            return false;
        }
    }
    
    /**
     * 根据get方法名称得到set方法名称
     * @param methodName
     * @return
     */
    public static String getter2Setter(String methodName) {
        if (methodName.startsWith("get")) {
            return methodName.replaceFirst("g", "s");
        } 
        else {
            return methodName;
        }
    }
    
    /**
     * 返回32位uuid
     * @return
     */
    public static String uuid(){
		Properties props = new Properties();
		//props.setProperty("separator", "/");
		IdentifierGenerator gen = new UUIDHexGenerator();
		((Configurable) gen).configure(Hibernate.STRING, props, null);
		return (String)gen.generate(null, null);
	}
    
    /**
     * JSON转换
     * @return
     */
    public static String toJson(Object bean){
    	ObjectMapper mapper = new ObjectMapper();
    	try {
    		mapper.registerModule(new HibernateModule());
			return mapper.writeValueAsString(bean);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
    }
	
    /**
     * 
     * @param args
     */
	public static void main(String[] args){
		System.err.println(Bean.get("graphTypeService"));
	}
}
