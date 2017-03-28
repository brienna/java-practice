package chapter_17;
import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.io.*;

public class Figures {
	
	// Prevent instantiation of class
	// NOTE: Good practice when class contains only static methods
	private Figures() {}

	public static void main(String[] args) throws IOException {
		//doFigure17_1();
		//doFigure17_3();
		//doFigure17_4();
		//doFigure17_5();
		doFigure17_6();
		doFigure17_7(); 
		//doFigure17_8 is called in doFigure17_7
	}
	
	////////////////////////////////////////////////////
	///// AN INTRODUCTION TO DIRECTORIES AND FILES /////
	////////////////////////////////////////////////////
	
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
		
		// Read data from the file by creating an input stream
		try (BufferedReader in = new BufferedReader(
								 new FileReader(testFile))) {
			String line = in.readLine();
			System.out.println(line);
		} catch (IOException e) {
			System.out.println(e);
		}
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
		
		// NOTE: When you work with I/O operations, you'll need to catch or
		// throw three types of checked exceptions:
		// IOException - thrown when an error occurs in I/O processing
		// EOFException - thrown when code attempts to read beyond end of file
		// FileNotFoundException - thrown when code attempts to open nonexistent file
	}
	
	///////////////////////////////////////
	///// HOW TO WORK WITH TEXT FILES /////
	///////////////////////////////////////
	
	// How to connect a character output stream to a file
	// NOTE: Before you can write to a text file, you need to create a character output 
	// stream, and you need to connect that stream to a file. To do that, you must layer 
	// two or more of the classes in the Writer hierarchy. 
	private static void doFigure17_5() throws IOException {
		// How to connect to a file without a buffer (not recommended)
		PrintWriter out = new PrintWriter(
						  new FileWriter("example.txt"));
		
		// How to connect to a file with a buffer
		PrintWriter out2 = new PrintWriter(
				           new BufferedWriter(  
				           new FileWriter("example2.txt")));
		
		// How to connect for an append operation
		PrintWriter out3 = new PrintWriter(  
				 		   new BufferedWriter(  
				 		   new FileWriter("example3.txt", true)));
		// NOTE: If the output file doesn't exist when the FileWriter object
		// is created, it's created automatically. If it does exist, it's overwritten
		// by default. To append data instead of overwriting, set the second 
		// argument of constructor to true. 
		
		// How to connect with the autoflush feature turned on
		PrintWriter out4 = new PrintWriter( 
						   new BufferedWriter(  
						   new FileWriter("example4.txt")), true);
		// By default, the data in an output stream is flushed from the buffer to the 
		// disk when the buffer is full. If you turn on the autoflush feature, then the
		// buffer is flushed each time the println method is executed.
		
		// NOTE: PrintWriter contains the methods for writing data to a text stream.
		// Buffered Writer creates a buffer for the stream.
		// FileWriter connects the stream to a file.
	}
	
	// How to write to a text file
	// NOTE: Use the print and println methods of a PrintWriter object, which work like 
	// the print and println methods of the System.out object.
	private static void doFigure17_6() throws IOException {
		// Append a string and an object to a text file
		// First, connect for an append operation
		PrintWriter out = new PrintWriter( 
				 	 	  new BufferedWriter( 
				 	      new FileWriter("log.txt", true)));
		// Second, write the string and object to the file
		out.print("This application was run on ");
		LocalDateTime currentDateTime = LocalDateTime.now();
		out.println(currentDateTime); 
		// Last, flush data to the file and close the output stream
		out.close();
		
		// NOTE: To flush all data to the file, you can use a try-with-resources statement
		// to automatically close the stream when you're done using it (like in doFigure17_3). 
		// If you don't use a try-with-resources statement, you can use the close method to 
		// manually close the output stream. Or, if you want to keep the output stream open,
		// you can use the flush method to flush all the data in the stream to the file.
	}
	
	// How to connect a character input stream to a file
	// NOTE: Before you can read characters from a text file, you must connect 
	// the character input stream to an existing file.
	private static void doFigure17_7() throws IOException {
		BufferedReader in = new BufferedReader( 
							new FileReader("log.txt"));
		
		// NOTE: Again, you can read files with the FileReader class alone. 
		// The BufferedReader class improves efficiency and provides better methods for 
		// reading character input streams. 
		
		// Since the constructor for the BufferedReader object accepts any object in the
		// Reader hierarchy, it can accept not only a FileReader object but also an 
		// InputStreamReader object, which can be used to connect the character input 
		// stream to the keyboard or to a network connection rather than to a file. 
		
		doFigure17_8(in);
	}
	
	// How to read from a text file
	private static void doFigure17_8(BufferedReader in) throws IOException {
		// Read the records in the text file
		String line;
		while ((line = in.readLine()) != null) {
			System.out.println(line);
		}
		// Flush the buffer and close the input stream
		in.close();
	}
}