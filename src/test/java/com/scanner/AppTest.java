package com.scanner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.scanner.AppScanner;
import com.scanner.AppScanner.ProjectType;

import static org.assertj.core.api.Assertions.assertThat;

public class AppTest {
	public static final String INVALID_PATH_NAME="/Users/rahultiwari/Documents/tdd";

	public static final String VALID_PATH_NAME="/Users/rahultiwari/Documents/tdd2/java";
	
	public static final String VALID_PATH_NAME_NODEJS ="/Users/rahultiwari/Documents/tdd2/nodejs";

	@Test
	void testIsValidProject_WhenValid_ShouldReturnTrue() {
		AppScanner appScanner = new AppScanner("./tdd", ProjectType.JAVA);
		boolean isValid = appScanner.isValid();
		assertThat(isValid).isTrue();
	}

	@Test
	void verifySupportedFilesName() {
		List<String> supportedFileExtennsion = new ArrayList<String>(Arrays.asList(".java", ".js", ".ts"));
		AppScanner appScanner = new AppScanner(VALID_PATH_NAME, ProjectType.JAVA);
		String getFileExtention = appScanner.getFileExtension();
		assertThat(supportedFileExtennsion).contains(getFileExtention);
	}
	
	@Test
	void verifyLocalDirectoryIsNotEmpty() {
		AppScanner appScanner = new AppScanner(VALID_PATH_NAME, ProjectType.JAVA);
		boolean isEmpty = appScanner.isEmpty();
		assertThat(isEmpty).isFalse();
	}
	
	@Test
	void verifyLocalDirectoryIsEmpty() {
		AppScanner appScanner = new AppScanner(INVALID_PATH_NAME, ProjectType.JAVA);
		boolean isEmpty = appScanner.isEmpty();
		assertThat(isEmpty).isTrue();
	}
	
	@Test
	void verifyNumberOfLinesOfActualCode() {
		AppScanner appScanner = new AppScanner(VALID_PATH_NAME, ProjectType.JAVA);
		double count = appScanner.countSource();
		double expectedCount=53;
		assertThat(count).isEqualTo(expectedCount);
	}

	@Test
	void verifyNumberOfLinesOfTestCode() {
		AppScanner appScanner = new AppScanner(VALID_PATH_NAME, ProjectType.JAVA);
		double count = appScanner.countTest();
		double expectedCount=17;
		assertThat(count).isEqualTo(expectedCount);
	}
	
	@Test
	void shouldReturnCorrectRatioOfJavaProject() {
		AppScanner appScanner = new AppScanner(VALID_PATH_NAME, ProjectType.JAVA);
		double result = appScanner.countResult();
		assertThat(result).isEqualTo(17.0/53.0);
	}
	
	@Test
	void shouldReturnCorrectRatioOfNodeJsProject() {
		AppScanner appScanner = new AppScanner(VALID_PATH_NAME_NODEJS, ProjectType.NODEJS);
		double result = appScanner.countResult();
		assertThat(result).isEqualTo(3.0);
		
	}	
}