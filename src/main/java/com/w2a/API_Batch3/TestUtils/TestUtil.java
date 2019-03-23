package com.w2a.API_Batch3.TestUtils;

import java.util.Hashtable;

import com.w2a.API_Batch3.setUp.APISetUp;

import io.restassured.specification.RequestSpecification;

public class TestUtil extends APISetUp{
	
	public static RequestSpecification setFormParam(String arguments,RequestSpecification reqSpecs)
	{
	 String[] listOfAruments=	arguments.split(",");
	 for(String singleArgumen:listOfAruments)
	 {
		String[] keyValue= singleArgumen.split(":");
		{
			String key=keyValue[0];
			String value=keyValue[1];
			reqSpecs.formParam(key, value);
		}
		
	 }
	 return reqSpecs;	
		
	}

}
