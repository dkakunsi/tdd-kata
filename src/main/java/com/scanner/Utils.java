package com.scanner;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Utils {
	public static long countLinesOfCode(String path) {
		 Path path1 = Paths.get(path);
	     long lines = 0;
	      try {

	          lines = Files.lines(path1).count();

	      } catch (Exception e) {
	  
	      }
	      return lines;

	}
	
	public static long countLinesOfCode(Path path) {
	      try {
	          return Files.lines(path).count();
	      } catch (Exception e) {
	    	  return 0;
	      }
	}

	public static long calculateLinesOfCodeInAFolder(String sourceFolderPath) {
		try {
			File sourceFolder = new File(sourceFolderPath);
			List<Long> counts = new ArrayList<>();
			Files.walk(sourceFolder.toPath()).filter(Files::isRegularFile).forEach(path -> {
				counts.add(countLinesOfCode(path));
			});
			long counter = 0;
			for (Long l : counts) {
				counter += l;
			}
			return counter;
		} catch(IOException ex) {
			throw new RuntimeException(ex);
		}
	}
	
	public static double calculateRatio(double testLinesCount,double sourceLinesCount) {
		return testLinesCount/sourceLinesCount;
	}

}
