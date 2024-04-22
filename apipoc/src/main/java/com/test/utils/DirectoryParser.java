package com.test.utils;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;
import java.util.stream.Stream.Builder;

public class DirectoryParser {

	public static Stream<Object> getFilesNameFromDirectory(String directoryName) {
	
		String directoryPath = System.getProperty("user.dir") + "/src/main/java/com/test/UBL/" + directoryName;
		
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
}
