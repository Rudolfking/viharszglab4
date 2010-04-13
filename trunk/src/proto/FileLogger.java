package proto;

import java.io.*;

public class FileLogger extends Logger {
	
	BufferedWriter output;
	
	public FileLogger(String fileName) {
		try {
			output = new BufferedWriter(new FileWriter(new File(fileName)));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
    public void log(String message) {
		try {
			for (int i = 0; i < level * spacePerLevel; i++) {
				output.write(" ");
			}
			output.write(message);
			output.newLine();
			output.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    protected void finalize() throws Throwable
	{		
		output.close();
	} 
}
