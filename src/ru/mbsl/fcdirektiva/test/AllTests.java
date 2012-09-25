package ru.mbsl.fcdirektiva.test;

import junit.framework.Test;
import junit.framework.TestSuite;
import android.test.suitebuilder.TestSuiteBuilder;

public class AllTests extends TestSuite {  
	 public static Test suite() {  
		 TestSuiteBuilder testSuiteBuilder = new TestSuiteBuilder(AllTests.class);
		 //testSuiteBuilder.includePackages("ru.mbsl.fcdirektiva.importData");
		 testSuiteBuilder.includePackages("ru.mbsl.fcdirektiva.test");
		 return testSuiteBuilder.build();
	//  return new TestSuiteBuilder(AllTests.class).includeAllPackagesUnderHere().build();  
	 }  
	}  