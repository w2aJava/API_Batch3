package com.w2a.API_Batch3.listeners;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

public class RetryTransformer implements IAnnotationTransformer {

	public void transform(ITestAnnotation annotation, Class arg1, Constructor arg2, Method arg3) {
		// TODO Auto-generated method stub
		
		annotation.setRetryAnalyzer(RetryListeners.class);
	}

}
