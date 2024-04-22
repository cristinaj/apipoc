package apipoc.apipoc;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class BaseTest implements BeforeAllCallback, BeforeTestExecutionCallback, AfterAllCallback, AfterTestExecutionCallback{

    static ExtentReports reports;
    static ExtentTest test;
    
	@Override
    public void beforeAll(ExtensionContext context) throws Exception {
		
        String filename = "test-output/" + context.getDisplayName() + "_Results.html";
        /*ExtentSparkReporter htmlReporterFailsOnly = new ExtentSparkReporter(filename).filter().statusFilter()
        		.as(new Status[] { Status.FAIL }).apply();*/
        ExtentSparkReporter htmlReporterFailsOnly = new ExtentSparkReporter(filename);
        
        //or new ExtentSparkReporter("target/results" +".html");
        
        reports = new ExtentReports();
        reports.attachReporter(htmlReporterFailsOnly);
    }
	
    @Override
    public void beforeTestExecution(ExtensionContext context) throws Exception {
         test = reports.createTest(context.getDisplayName());
         //test.log(Status.INFO, context.getDisplayName() + " - started");
    }
    
    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {
        if (context.getExecutionException().isPresent()) {
            test.fail(context.getExecutionException().get().getLocalizedMessage());
        } else {
        }
    }
    
    @Override
    public void afterAll(ExtensionContext context) throws Exception {
        reports.flush();
    }
}
