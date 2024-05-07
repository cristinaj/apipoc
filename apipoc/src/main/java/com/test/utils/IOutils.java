package com.test.utils;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;
import java.util.stream.Stream.Builder;

public class IOutils {
	
	private static String path = System.getProperty("user.dir") + "/src/main/java/com/test/UBL/";

	public static Stream<Object> getFilesNameFromDirectory(String directoryName) {
	
		String directoryPath = path + directoryName;
		
	    Path directory = Paths.get(directoryPath); 
	    Builder<Object> b = Stream.builder();
	    
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(directory)) { 
            for (Path file : stream) { 
                b.add(file.getFileName());
            } 
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         return b.build();
	}
	
	public static void writeToFile(String directoryName, String fileName, String text) {
		
		String directoryPath = path + directoryName;

	    try {
			FileOutputStream fos = new FileOutputStream(directoryPath+fileName.toString());
		    DataOutputStream outStream = new DataOutputStream(new BufferedOutputStream(fos));
		    outStream.writeChars(text);
			outStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
