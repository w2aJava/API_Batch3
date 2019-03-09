package com.w2a.API_Batch3.testCases;

import org.aeonbits.owner.ConfigFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.w2a.API_Batch3.TestUtils.ConfigProperty;
import com.w2a.API_Batch3.setUp.APISetUp;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;

public class TestCreateCustomerAPI extends APISetUp {

	@Test
	public void validateCreateCustomerAPIWithValidData() {

		testLevelLog.get().assignAuthor("Rahul");
		testLevelLog.get().assignCategory("Regression");
		RequestSpecification spec = setRequestSpecification().formParam("email", "rahul.jha_rest@gmail.com")
				.formParam("description", "Testing Stripe using restASsured").formParam("account_balance", 10000).log()
				.all();

		System.out.println("====================================================");

		Response response = spec.post("customers");
		
		testLevelLog.get().info(response.asString());

		response.prettyPrint();

		Assert.assertEquals(response.getStatusCode(), 200);

	}

}
