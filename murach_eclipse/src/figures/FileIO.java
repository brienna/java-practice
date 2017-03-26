package figures;
import java.io.IOException;
import java.nio.file.*;
import java.io.*;

public class FileIO {

	public static void main(String[] args) throws IOException {
		doFigure17_1();
		doFigure17_3();
		doFigure17_4();
	}
	
	// How to work with directories and files
	private static void doFigure17_1() throws IOException {
		// Create directory if it doesn't already exist
		String dirStr = "/Users/Brienna/Documents/GitHub repositories/java-practice/murach_eclipse/new directory";
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
	
	// A file I/O example
	private static void doFigure17_3() {
		// Get a Path object for the file
		Path testPath = Paths.get("test.txt");
		File testFile = testPath.toFile();
		// NOTE: It is necessary to convert the Path object to a File object,
		// because many of the classes in the file.io package were created
		// before the Path object was introduced. As a result, they are 
		// designed to work with a File object.
		 
		// Write data, starting by creating an output stream
		try (PrintWriter out = new PrintWriter(
							   new BufferedWriter(
						       new FileWriter(testFile)))) {
			// Use println method of output stream to write data to file
			out.println("java\tMurach's Beginning Java\t57.50");
		} catch (IOException e) {
			System.out.println(e);
		}
		// NOTE: To create a stream that has all the functionality that you
		// need for an application, you can layer two or more streams into 
		// a single stream. To layer streams in Java, you use an object of
		// one class as the argument for the constructor of another class.
		// In this example, a BufferedWriter object is used as the argument
		// of the PrintWriter constructor, and a FileWriter object is used
		// as the argument of the BufferedWriter constructor. 
		
		// Read data from the file by creating an input stream
		try (BufferedReader in = new BufferedReader(
								 new FileReader(testFile))) {
			String line = in.readLine();
			System.out.println(line);
		} catch (IOException e) {
			System.out.println(e);
		}
		
		// NOTE: The BufferedWriter object adds a block of internal memory 
		// known as a buffer to the stream. This causes the data in the stream
		// to be stored in a buffer before it is written to the output device.
		// Then when the buffer is full or the stream is closed, all of the data
		// in the buffer is flushed to the disk file in a single I/O operation.
		// Similarly, when you use a buffer for input, a full buffer of data is 
		// read in a single I/O operation. 
		// The benefit of buffering is that it dramatically reduces the number 
		// of I/O operations that are done by a disk device. If, for example, a 
		// buffer can hold 4000 bytes of data, only one write or read operation 
		// is required to flush or fill the buffer. In contrast, if the data is 
		// written or read one field at a time, 4000 bytes might require hundreds 
		// of I/O operations. 
	}
	
	// How to work with I/O exceptions
	private static void doFigure17_4() {
		Path fPath = Paths.get("f.txt");
		if (Files.exists(fPath)) {  // prevents FileNotFoundException
			File fFile = fPath.toFile();
			try (BufferedReader in = new BufferedReader(
									 new FileReader(fFile))) {
				String line = in.readLine();
				while (line != null) {  // prevents EOFException
					System.out.println(line);
					line = in.readLine();
				}
			} catch (IOException e) {  // catches IOException
				System.out.println(e);
			}
		} else {
			System.out.println(fPath.toAbsolutePath() + " doesn't exist");
		}
		
		// NOTE: IOException - thrown when an error occurs in I/O processing
		// EOFException - thrown when code attempts to read beyond end of file
		// FileNotFoundException - thrown when code attempts to open nonexistent file
	}

}