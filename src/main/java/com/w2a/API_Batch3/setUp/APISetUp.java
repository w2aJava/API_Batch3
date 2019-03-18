package com.w2a.API_Batch3.setUp;

import static io.restassured.RestAssured.given;

import java.lang.reflect.Method;
import java.util.ArrayList;

import org.aeonbits.owner.ConfigFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.w2a.API_Batch3.TestUtils.ConfigProperty;
import com.w2a.API_Batch3.TestUtils.ExcelReader;
import com.w2a.API_Batch3.TestUtils.Extentmanager;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class APISetUp {

	protected static ConfigProperty configProperty;

	public static ExcelReader excel = new ExcelReader(
			System.getProperty("user.dir") + "/src/test/resources/testData/TestData_1.xlsx");

	public static ExtentReports extentReport;
	public static ThreadLocal<ExtentTest> classLevelLog = new ThreadLocal<ExtentTest>();
	public static ThreadLocal<ExtentTest> testLevelLog = new ThreadLocal<ExtentTest>();

	@BeforeSuite
	public void beforeSuite() {

		configProperty = ConfigFactory.create(ConfigProperty.class);

		RestAssured.baseURI = configProperty.getBaseURI();
		RestAssured.basePath = configProperty.getBasePath();
		extentReport = Extentmanager
				.GetExtent(configProperty.getTestReportFilepath() + configProperty.getTestReportName());

	}

	@BeforeClass
	public void beforeClass() {
		// ExtentTest test = new ExtentTest(getClass().getSimpleName());
		ExtentTest classLevelTest = extentReport.createTest(getClass().getSimpleName());
		classLevelLog.set(classLevelTest);
	}

	@BeforeMethod
	public void beforeMethod(Method method) {

		ExtentTest test = classLevelLog.get().createNode(method.getName());
		testLevelLog.set(test);
		testLevelLog.get().info("Test Case:- " + method.getName() + " execution started");

		// System.out.println("Test Case :- " + method.getName() + " execution
		// started");

	}

	@AfterMethod
	public void afterMethod(ITestResult result) {

		if (result.getStatus() == ITestResult.SUCCESS) {
			testLevelLog.get().pass("Test Case passed");
			System.out.println("This Test cases is passed");
		} else if (result.getStatus() == ITestResult.FAILURE) {
			testLevelLog.get().fail("This test case failed");
			System.out.println("This Test Case is failed");
		} else if (result.getStatus() == ITestResult.SKIP) {
			testLevelLog.get().skip("Test case skipped");
			System.out.println("Test Case is skipped");
		}

		extentReport.flush();
	}

	@AfterSuite
	public void afterSuite() {
		configProperty = ConfigFactory.create(ConfigProperty.class);

		RestAssured.baseURI = configProperty.getBaseURI();
		RestAssured.basePath = configProperty.getBasePath();
	}

	public static RequestSpecification setRequestSpecification() {

		RequestSpecification spec = given().auth().basic(configProperty.getSecretKey(), "");

		testLevelLog.get().info("Request Specification set");
		// ArrayList<String> list = new ArrayList<String>();
		return spec;

	}

}
