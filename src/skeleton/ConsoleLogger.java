package skeleton;
public class ConsoleLogger extends Logger {
		
	public void logMessage(String message) {
		for(int i=0; i<level*spacePerLevel; i++) {
			System.out.print(" ");
		}
		System.out.println(message);
	}
	
}