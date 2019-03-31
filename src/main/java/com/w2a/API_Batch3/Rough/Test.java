package com.w2a.API_Batch3.Rough;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {

	public static void main(String[] args) {
		String reportName = "TestReports.html";

		String lastTestReportFilePath = System.getProperty("user.dir")+"/src/test/resources/testReports/";
		String archiveReportPath = System.getProperty("user.dir")+"/src/test/resources/archivedTestReport/";

		Date date = new Date();
		SimpleDateFormat dateFormate = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		String formatedDate = dateFormate.format(date);
		String archiveTestReportName = formatedDate + "_" + reportName;

		File oldReport = new File(lastTestReportFilePath + reportName);

		File newFile = new File(archiveReportPath + archiveTestReportName);
		System.out.println(oldReport);
		System.out.println(newFile);
		
		if (oldReport.exists()) {
			System.out.println("inside if");
			oldReport.renameTo(newFile);
			oldReport.delete();
		}
	

	}

}
