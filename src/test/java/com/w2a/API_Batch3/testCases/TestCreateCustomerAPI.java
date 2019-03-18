package com.w2a.API_Batch3.testCases;

import org.aeonbits.owner.ConfigFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

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

public class TestCreateCustomerAPI extends APISetUp {

	@Test(dataProviderClass=DataProviderClass.class,dataProvider="dp",enabled=false)
	public void validateCreateCustomerAPI(Hashtable<String, String> data) {

		testLevelLog.get().assignAuthor("Rahul");
		testLevelLog.get().assignCategory("Regression");
		RequestSpecification spec = setRequestSpecification().formParam("email", data.get("email"))
				.formParam("description", data.get("descrption")).formParam("account_balance", 10000).log()
				.all();

		System.out.println("====================================================");

		Response response = spec.post("customers");
		
		testLevelLog.get().info(response.asString());
		
		
		// fetch the email from the response
		
		String emailInTheResponse=response.path("email");
		System.out.println("Ëmail In Response--->"+emailInTheResponse);
		
		String descriptionInResponse=response.path("description");
		System.out.println("Description in the response-->"+descriptionInResponse);
		
		System.out.println("Footer--->"+response.path("invoice_settings.footer"));
		System.out.println("URL Under Subscription-->"+response.path("subscriptions.url"));
		
		JsonPath responseJson=new JsonPath(response.asString());
		
		System.out.println("Email using JsonPath-->"+responseJson.get("email"));
		
		
		// fetch the description from the response
		

		response.prettyPrint();

		//Assert.assertEquals(response.getStatusCode(), 200);

	}
	
	@Test(dataProviderClass=DataProviderClass.class,dataProvider="dp",enabled=false)
	public void fetchCustomers(Hashtable<String, String> data)
	{

		testLevelLog.get().assignAuthor("Rahul");
		testLevelLog.get().assignCategory("Regression");
		RequestSpecification spec = setRequestSpecification().log().all();

		System.out.println("====================================================");

		Response response = spec.request(data.get("methodType"), data.get("endPoint"));
		
		//ArrayList<String> listOfData=response.path("data");
		int lengthOfData=response.path("data.size()");
		String expectedDefaultSource="card_1EEVXuLA9CZuKh76xWdjvTF5";
		
		for(int i=0;i<lengthOfData;i++)
		{
			String actualDefaultSource=response.path("data["+i+"].default_source");
			if(expectedDefaultSource.equals(actualDefaultSource))
			{
				System.out.println(response.path("data["+i+"].id"));
				break;
			}
			
		}
		
		
		//System.out.println(response.size());
		/*response.prettyPrint();
		
		System.out.println("Size of Data-->"+response.path("data[0].size()"));
		
		ArrayList<String> listOfIds=response.path("data");
		
		System.out.println(listOfIds);
		// default_source = card_1EEVXuLA9CZuKh76xWdjvTF5
*/	
		
	
		
		
	}
	
	@Test	
	public void parseJsonUsingPath()
	{
		String json="{\"destination_addresses\": [\"Philadelphia, PA, USA\"],\"origin_addresses\": [\"New York, NY, USA\"],\"rows\": [{\"elements\": [{\"distance\": {\"text\": \"94.6 mi\",\"value\": 152193},\"duration\": {\"text\": \"1 hour 44 mins\",\"value\": 6227},\"status\": \"OK\"]}],\"status\": \"OK\"}";
		JsonPath path= new JsonPath(json);
		System.out.println(path.get("rows.size()"));
	
	}
	

}
