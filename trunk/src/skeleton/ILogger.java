//import java.util.*;

public interface ILogger {
	
	public void setLevel(int level);
	public void logMessage(String message);
	public void logCall(INamedObject caller, INamedObject called, String function);
	public void logReturn(INamedObject caller, INamedObject called, String function, INamedObject result);
	public void logCreate(INamedObject caller, String className);
	public void logCreated(INamedObject caller, INamedObject created);
	
}