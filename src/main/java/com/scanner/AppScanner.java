package com.scanner;

import java.io.File;

public class AppScanner {
	
	private static final String JAVA_SOURCE_FOLDER = "src/main/java";
	
	private static final String JAVA_TEST_FOLDER = "src/test/java";
	
	private static final String NODE_SOURCE_FOLDER = "src";
	
	private static final String NODE_TEST_FOLDER = "src/__test__";

	public enum ProjectType {
		JAVA, NODEJS
	}
	
	private String rootPath;
	
	private ProjectType type;
	
	public AppScanner() {
		this.rootPath = null;
	}
	
	public AppScanner(String rootPath, ProjectType type) {
		this.rootPath = rootPath;
		this.type = type;
	}

	public double countResult(String testPath, String sourcePath) {
		double testCount= countTest(testPath);
		double sourceCount=countSource(sourcePath);
		System.out.println("Test line: " +testCount);
		System.out.println("Code line: " +sourceCount);
		return Utils.calculateRatio(testCount, sourceCount);
	}
	
	public double countResult() {
		if (type.equals(ProjectType.JAVA)) {
			return countResult(JAVA_TEST_FOLDER, JAVA_SOURCE_FOLDER);
		} else {
			return countResult(NODE_TEST_FOLDER, NODE_SOURCE_FOLDER);
		}
	}

	public boolean isValid() {
		return true;
	}
	
	public String getFileExtension() {
		return ".java";
	}

	public boolean isEmpty() {
		File root = readDirectory();
		//directory having a file
		if(root!= null && countFiles(root)>0) {
			return false;
		}else {
			return true;
		}
		
	}
	
	private File readDirectory() {
		System.out.println(this.rootPath);
		return new File(this.rootPath);
	}
	
	private static int countFiles(File file) {
    String[] list = file.list();
    if (list == null) {
      return 0;
    }
		return list.length;
	}
	
	public double countSource(String path) {
		String sourceFolderPath = this.rootPath + File.separator + path;
		return Utils.calculateLinesOfCodeInAFolder(sourceFolderPath);
		
	}
	
	public double countSource() {
		if (type.equals(ProjectType.JAVA)) {
			return countSource(JAVA_SOURCE_FOLDER);
		} else {
			return countSource(NODE_SOURCE_FOLDER);
		}
	}

	public double countTest(String path) {
		String sourceFolderPath = this.rootPath + File.separator + path;
		return Utils.calculateLinesOfCodeInAFolder(sourceFolderPath);
	}
	
	public double countTest() {
		if (type.equals(ProjectType.JAVA)) {
			return countTest(JAVA_TEST_FOLDER);
		} else {
			return countTest(NODE_TEST_FOLDER);
		}
	}

	public double countNumberOfLines(String filepath) {
		double count=Utils.countLinesOfCode(filepath);
		return count;
	}
	
}
