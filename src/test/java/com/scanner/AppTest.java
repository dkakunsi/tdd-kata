package com.scanner;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.scanner.AppScanner.ProjectType;

public class AppTest {
  public static final String INVALID_PATH_NAME = "invalid";

  public static final String VALID_PATH_NAME = "javaproject";

  public static final String VALID_PATH_NAME_NODEJS = "nodeproject";

  @Test
  void testIsValidProject_WhenValid_ShouldReturnTrue() throws URISyntaxException {
    File file = getResourceFile(VALID_PATH_NAME);
    AppScanner appScanner = new AppScanner(file.getAbsolutePath(), ProjectType.JAVA);
    boolean isValid = appScanner.isValid();
    assertThat(isValid).isTrue();
  }

  @Test
  void verifySupportedFilesName() throws URISyntaxException {
    File file = getResourceFile(VALID_PATH_NAME);
    List<String> supportedFileExtennsion = new ArrayList<String>(Arrays.asList(".java", ".js", ".ts"));
    AppScanner appScanner = new AppScanner(file.getAbsolutePath(), ProjectType.JAVA);
    String getFileExtention = appScanner.getFileExtension();
    assertThat(supportedFileExtennsion).contains(getFileExtention);
  }

  @Test
  void verifyLocalDirectoryIsNotEmpty() throws URISyntaxException {
    File file = getResourceFile(VALID_PATH_NAME);
    AppScanner appScanner = new AppScanner(file.getAbsolutePath(), ProjectType.JAVA);
    boolean isEmpty = appScanner.isEmpty();
    assertThat(isEmpty).isFalse();
  }

  @Test
  void verifyLocalDirectoryIsEmpty() throws URISyntaxException {
    File file = getResourceFile(INVALID_PATH_NAME);
    AppScanner appScanner = new AppScanner(file.getAbsolutePath(), ProjectType.JAVA);
    boolean isEmpty = appScanner.isEmpty();
    assertThat(isEmpty).isTrue();
  }

  @Test
  void verifyNumberOfLinesOfActualCode() throws URISyntaxException {
    File file = getResourceFile(VALID_PATH_NAME);
    AppScanner appScanner = new AppScanner(file.getAbsolutePath(), ProjectType.JAVA);
    double count = appScanner.countSource();
    assertThat(count).isGreaterThan(0.0);
  }

  @Test
  void verifyNumberOfLinesOfTestCode() throws URISyntaxException {
    File file = getResourceFile(VALID_PATH_NAME);
    AppScanner appScanner = new AppScanner(file.getAbsolutePath(), ProjectType.JAVA);
    double count = appScanner.countTest();
    assertThat(count).isGreaterThan(0.0);
  }

  @Test
  void shouldReturnCorrectRatioOfJavaProject() throws URISyntaxException {
    File file = getResourceFile(VALID_PATH_NAME);
    AppScanner appScanner = new AppScanner(file.getAbsolutePath(), ProjectType.JAVA);
    double result = appScanner.countResult();
    assertThat(result).isGreaterThan(0.0);
  }

  // @Test
  // void shouldReturnCorrectRatioOfNodeJsProject() {
  // AppScanner appScanner = new AppScanner(VALID_PATH_NAME_NODEJS,
  // ProjectType.NODEJS);
  // double result = appScanner.countResult();
  // assertThat(result).isEqualTo(3.0);
  // }

  private static File getResourceFile(String resourceName) throws URISyntaxException {
    URL resource = AppTest.class.getClassLoader().getResource(resourceName);
    return new File(resource.toURI());
  }
}