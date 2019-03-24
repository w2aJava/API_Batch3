package com.w2a.API_Batch3.testCases;

import org.aeonbits.owner.ConfigFactory;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.w2a.API_Batch3.API.CustomerAPI;
import com.w2a.API_Batch3.TestUtils.ConfigProperty;
import com.w2a.API_Batch3.TestUtils.DataProviderClass;
import com.w2a.API_Batch3.setUp.APISetUp;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.Hashtable;

public class TestCustomerAPI extends APISetUp {

	@Test(dataProviderClass=DataProviderClass.class,dataProvider="dp",priority=1,enabled=true,dependsOnMethods="m1")
	public void validateCreateCustomerAPIWithValidData(Hashtable<String, String> data)
	{
		testLevelLog.get().assignAuthor("Rahul");
		testLevelLog.get().assignCategory("Sanity");
		
		Response response=CustomerAPI.sendPostRequestWithValidDataWithArguments(data);
		response.prettyPrint();
		Assert.assertEquals(response.statusCode(),Integer.parseInt(data.get("expectedStatusCode")));
		Assert.assertEquals(response.jsonPath().get("email"), data.get("expectedEmail"));
		//Assert.assertEquals(response.statusLine(), "Created");
		//Assert.fail();
		
	}
	
	@Test(priority=0)
	public void m1()
	{
		
		//throw new SkipException("Skipping this test case");
		Assert.fail();
		
	}
	/*
	@Test(dataProviderClass=DataProviderClass.class,dataProvider="dp",priority=0,enabled=true)
	public void validateCreateCustomerAPIWithInValidAuthkey(Hashtable<String, String> data)
	{
		
		
	}
	
	@Test(dataProviderClass=DataProviderClass.class,dataProvider="dp",priority=4,enabled=true)
	public void validateCreateCustomerAPIWithInValidEmail(Hashtable<String, String> data)
	{
		
		
	}
	
	@Test(dataProviderClass=DataProviderClass.class,dataProvider="dp",priority=2,enabled=true)
	public void validateCreateCustomerAPIWithoutPassingAuthKey(Hashtable<String, String> data)
	{
		
		
	}
	
	@Test(dataProviderClass=DataProviderClass.class,dataProvider="dp",priority=3,enabled=true)
	public void validateCreateCustomerAPIWithInvalidField(Hashtable<String, String> data)
	{
		
		
	}
	*/
	
	
}
