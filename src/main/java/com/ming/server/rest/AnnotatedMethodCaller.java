package com.ming.server.rest;

import java.lang.reflect.*;
import java.util.*;

import org.reflections.*;

/**
 * Assists with finding and calling methods annotated with GET, PUT, POST or DELETE.
 * 
 * @author aris
 */
public class AnnotatedMethodCaller {
	
	public static Object call(ApiBase apiBase, Class annotationClass) {
		
		Set<Method> methods = ReflectionUtils.getAllMethods(apiBase.getClass(), ReflectionUtils.withAnnotation(annotationClass));
		
		if(methods.size() != 1) {
			throw new RuntimeException("Number of GET methods should be 1 but it is: " + methods.size());
		}
		
		Method method = methods.iterator().next();
		
		try {
			return method.invoke(apiBase, new Object[0]);
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
}
