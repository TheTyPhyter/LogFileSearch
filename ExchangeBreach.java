//Written by Anthony Spears, 3/4/2021.


import java.io.*;
import java.util.Scanner;

public class ExchangeBreach {
    public static void main(String[] args) throws IOException {
    	System.out.println("Enter your indicator of compromise: ");
    	Scanner userIn = new Scanner(System.in);
    	//what we will name the list of log files, text file.
    	String indicator = userIn.nextLine();//the indicator of compromise (what to search the logs for).
		System.out.println("Enter the location of your log files (Absolute path)");
		String folder = userIn.nextLine()+"\\";//the folder where log files are stored.
		System.out.println("Enter the output filename. This will be the text file which consists of the list of" +
				" filenames for your log files.");
		String outFile = userIn.nextLine();
    	getFileNames(folder, outFile);
	    Scanner fileNames = new Scanner(new File(outFile));
	    while(fileNames.hasNext()) {//we read the list of file names to the end of the file.
			String path = fileNames.nextLine();
			Scanner log = new Scanner(new File(folder+path));//opening each file from the list(outFile).
			while (log.hasNext()){//we read every string in the log file
				String ioc = log.nextLine();
				if (ioc.contains(indicator)){//if the log file contains the indicator of compromise, we print the log
					//name to the console for manual review.
					System.out.println("IOC Detected in "+path);
				}
			}
		}
    }

    public static void getFileNames(String fold, String outFile)throws IOException{//this generates a text file which will
    	//be a list of file names.
		File folder = new File(fold);
		File[] listOfFiles = folder.listFiles();
		Writer out = new FileWriter(outFile);

		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				out.write(listOfFiles[i].getName()+"\n");
			}
		}
		out.close();
	}
}
