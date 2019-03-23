package com.w2a.API_Batch3.API;

import java.util.Hashtable;

import com.w2a.API_Batch3.TestUtils.TestUtil;
import com.w2a.API_Batch3.setUp.APISetUp;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CustomerAPI extends APISetUp {

	public static Response sendPostRequestWithValidDataWithArguments(Hashtable<String, String> data)
	{
		Response response=TestUtil.setFormParam(data.get("arguments"), setRequestSpecification()).post(data.get("endpoint"));
		return response;
	}

	/*
	 * public static Response sendPostRequestWithInValidData() {
	 * 
	 * }
	 */

}
