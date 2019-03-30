package com.w2a.API_Batch3.Rough;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {

	public static void main(String[] args) {
		String reportName = "TestReport.html";

		String lastTestReportFilePath = "./src/test/resources/testReports/";

		Date date = new Date();
		SimpleDateFormat dateFormate = new SimpleDateFormat("dd_MM_yyyy:hh_mm_ss");
		System.out.println(dateFormate.format(date));
		String updatedName=dateFormate.format(date)+":"+reportName;
		System.out.println(updatedName);

	}

}
