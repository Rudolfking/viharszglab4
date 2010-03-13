package skeleton;
import java.io.*;

public class CustomReader {
	
	private BufferedReader input;
	
	public void setInput(BufferedReader input) {
		this.input = input;
	}
	
	public String readLine() throws IOException{
		String result;
		do {
			result = input.readLine();
		} while (result.charAt(0) == '#');
		return result;
	}

	
}
