package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener {
	
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	
	String repName;
	
	public void onStart(ITestContext testContext) {
		
		/*SimpleDateFormat df=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		 Date dt=new Date;
		 String currentdatetimestamp=df.format(dt);
		 */
	
		String timestamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		repName="Test-Report-" +timestamp +".html";
		sparkReporter=new ExtentSparkReporter(".\\reports\\"+repName);
		
		sparkReporter.config().setDocumentTitle("Opencart Automation Report");//title of report
		sparkReporter.config().setReportName("Opencart Functional Testing");//Name of the report
		sparkReporter.config().setTheme(Theme.DARK);
		
		extent=new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application", "opencart");
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("Sub Module", "Customers");
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "QA");
		
		String os= testContext.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Operating System", os);
		
		String browser=testContext.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser", browser);
		
		List<String> includeGroups=testContext.getCurrentXmlTest().getIncludedGroups();
		if(!includeGroups.isEmpty()) {
			extent.setSystemInfo("Groups", includeGroups.toString());
		}
			
	}
	
	public void onTestSuccess(ITestResult result) {
		
		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());// to display groups in report
		test.log(Status.PASS, result.getName()+"got Successfully Executed");
	}
	
	public void onTestFailure(ITestResult result) {
		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		
		test.log(Status.FAIL, result.getName()+" got failed");
		test.log(Status.INFO, result.getThrowable().getMessage());
		
		try {
			String imgpath= new BaseClass().captureScreen(result.getName());
			test.addScreenCaptureFromPath(imgpath);
			
		}catch(IOException e1){
			e1.printStackTrace();
		}
	}
	
	public void onTestSkipped(ITestResult result) {
		
		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, result.getName()+"get skipped");
		test.log(Status.INFO, result.getThrowable().getMessage());
	}
	
	//To open report automatically
	public void onFinish(ITestContext testContext) {
		
		extent.flush();
		
		String pathOfExtentReport=System.getProperty("user.dir")+"\\reports\\"+repName;
		File extentReport=new File(pathOfExtentReport);
		
		try{
			Desktop.getDesktop().browse(extentReport.toURI());
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		//To send report automatically through an email
		/*
		 try{ URL url= new URL("file:///"+System.getProperty("user.dir")+"\\reports\\"+repName);
		 
		 //Create email message
		 ImageHtmlEmail email=new ImageHtmlEmail();
		 email.setDataSourceResolver(new DataSourceUrlResolver(url));
		 email.setHostName("smtp.googlemail.com");
		 email.setSmtpPort(465);
		 email.setAuthenticator(new DefaultAuthenticator("pavanonlinetraining@gmail.com","password"));
		 email.setSSLOnConnect(true);
		 email.setFrom("pavanonlinetraining@gmail.com");//sender
		 email.setSubject("Test Result");
		 email.setMsg("Please find attached report...");
		 email.addTo("pavankumar.busyqa@gmail.com");//Receiver
		 email.attach(url, "extent report","Please check report...");
		 email.send();//send the email
		 
		 }
		 catch(Exception e) {
			 e.printStackTrace();
		 }*/
	}
		

}
