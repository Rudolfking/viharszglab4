package skeleton;
public abstract class Logger {
	
	protected int level;
	protected final int spacePerLevel = 2;
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	public abstract void logMessage(String message);
	
	public void logCall(INamedObject caller, INamedObject called, String function) {
		String[] callerClass = caller.toString().split("@");
		String[] calledClass = called.toString().split("@");
		logMessage("[" + caller.getName() + "|" + callerClass[0] + "|" + caller.hashCode() + "] -> [" 
				+ called.getName() + "|" + calledClass[0] + "|" + called.hashCode() + "] . " + function + "|CALL");
		level++;
	}
	
	public void logReturn(INamedObject caller, INamedObject called, String function, INamedObject result) {
		level--;
		String[] callerClass = caller.toString().split("@");
		String[] calledClass = called.toString().split("@");
		String message = "[" + caller.getName() + "|" + callerClass[0] + "|" + caller.hashCode() + "] <- [" 
			+ called.getName() + "|" + calledClass[0] + "|" + called.hashCode() + "] . " + function + "|RETURN";
		if(result != null) {			
			String[] resultClass = result.toString().split("@");
			message += " [" + result.getName() + "|" + resultClass[0] + "|" + result.hashCode() + "]";
		}
		logMessage(message);
	}
	
	public void logCreate(INamedObject caller, String className) {
		String[] callerClass = caller.toString().split("@");
		logMessage("[" + caller.getName() + "|" + callerClass[0] + "|" + caller.hashCode() + "] -> creates new [" + className + "]");
		level++;
	}
	
	public void logCreated(INamedObject caller, INamedObject created) {
		level--;
		String[] callerClass = caller.toString().split("@");
		String[] createdClass = created.toString().split("@");
		logMessage("[" + caller.getName() + "|" + callerClass[0] + "|" + caller.hashCode() + "] -> has created [" 
				+ created.getName() + "|" + createdClass[0] + "|" + created.hashCode() + "]");
	}
	
}
