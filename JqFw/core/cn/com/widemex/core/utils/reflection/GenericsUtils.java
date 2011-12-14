package cn.com.widemex.core.utils.reflection;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * 处理范型参数的类型
 * @author 何启伟
 * @since 2010-6-26
 * @version ExtFw3.0
 */
public class GenericsUtils {
    private static final Log LOGGER = LogFactory.getLog(GenericsUtils.class);

    /**
     * 构造
     */
    protected GenericsUtils() {
    }

    /**
     * 通过反射，获得定义Class时声明的范型参数的类型
     * @param clazz
     * @return
     */
    public static Class getSuperClassGenricType(Class clazz) {
        return getSuperClassGenricType(clazz, 0);
    }

    /**
     * 通过反射，获得定义Class时声明的父类的范型参数的类型
     * @param clazz
     * @param index
     * @return
     */
    public static Class getSuperClassGenricType(Class clazz, int index) {
        Type genType = clazz.getGenericSuperclass();
        if (!(genType instanceof ParameterizedType)) {
            LOGGER.warn(clazz.getSimpleName() + "'s superclass not ParameterizedType");
            return Object.class;
        }
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();

        if (index >= params.length || index < 0) {
            LOGGER.warn("Index: " + index + ", Size of " + clazz.getSimpleName() + "'s Parameterized Type: " + params.length);
            return Object.class;
        }

        if (!(params[index] instanceof Class)) {
            LOGGER.warn(clazz.getSimpleName() + " not set the actual class on superclass generic parameter");
            return Object.class;
        }

        return (Class) params[index];
    }
    
    /**
     * 获取方法返回类型的泛型类型
     * 如果：Set<User> ，返回User
     * @param method 有返回参数的方法
     * @return
     */
    public static Type[] getMethodGenericReturnType(Method method){
    	Type returnType = method.getGenericReturnType();
    	
    	 Type[] types = null;
    	if (returnType instanceof ParameterizedType)/**//* 如果是泛型类型 */{
           types = ((ParameterizedType) returnType).getActualTypeArguments();// 泛型类型列表
        }
    	
    	return types;
    }
    
}
