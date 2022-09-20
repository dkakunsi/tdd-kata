package com.scanner;

import com.scanner.AppScanner.ProjectType;

public class Main {

	public static void main(String[] args) {
		String rootPath = "/Users/rahultiwari/Desktop/scanner";
		ProjectType projectType = ProjectType.JAVA;
		
		AppScanner appScanner = new AppScanner(rootPath, projectType);
		double result = appScanner.countResult();
		System.out.println(result);
	}
}
