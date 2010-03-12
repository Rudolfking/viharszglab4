import java.io.*;
import java.util.*;

public class ConsoleLogger implements ILogger {
	
	private int level;
	private final int spacePerLevel = 2;
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	public void logMessage(String message) {
		for(int i=0; i<level*spacePerLevel; i++) {
			System.out.print(" ");
		}
		System.out.println(message);
	}
	
	public void logCall(INamedObject caller, INamedObject called, String function) {
		String[] callerClass = caller.toString().split("@");
		String[] calledClass = called.toString().split("@");
		logMessage("[" + caller.getName() + "|" + callerClass[0] + "|" + caller.hashCode() + "] -> [" 
				+ called.getName() + "|" + calledClass[0] + "|" + caller.hashCode() + "] . " + function + "|CALL");
		level++;
	}
	
	public void logReturn(INamedObject caller, INamedObject called, String function, INamedObject result) {
		level--;
		String[] callerClass = caller.toString().split("@");
		String[] calledClass = called.toString().split("@");
		String message = "[" + caller.getName() + "|" + callerClass[0] + "|" + caller.hashCode() + "] <- [" 
			+ called.getName() + "|" + calledClass[0] + "|" + caller.hashCode() + "] . " + function + "|RETURN";
		if(result != null) {			
			String[] resultClass = result.toString().split("@");
			message += " [" + result.getName() + "|" + resultClass[0] + "|" + result.hashCode() + "]";
		}
		logMessage(message);
	}
	
}