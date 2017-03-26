package figures;
import java.io.IOException;
import java.nio.file.*;

public class FileIO {

	public static void main(String[] args) throws IOException {
		doFigure17_1();
	}
	
	private static void doFigure17_1() throws IOException {
		// Create directory if it doesn't already exist
		String dirStr = "/Users/Brienna/Documents/GitHub repositories/java-practice/new directory";
		Path dirPath = Paths.get(dirStr);
		if (Files.notExists(dirPath)) {
			Files.createDirectories(dirPath);
		}
		
		// Create a file if it doesn't already exist
		String fileStr = "example.txt";
		Path filePath = Paths.get(dirStr, fileStr);
		if (Files.notExists(filePath)) {
			Files.createFile(filePath);
		}
		
		// Display information about the file
		System.out.println("File name: " + filePath.getFileName());
		System.out.println("Absolute path: " + filePath.toAbsolutePath());
		System.out.println("Is writable: " + Files.isWritable(filePath));
	}

}