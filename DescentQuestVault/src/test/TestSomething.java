package test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class TestSomething {



		  public static void main(String[] args) {
		    try (Stream<Path> paths = Files.walk(Paths.get("Images/Tiles"))) {
		      paths.forEach(System.out::println);
		    } catch (IOException e) {
		      e.printStackTrace();
		    }
		  }

		
}
