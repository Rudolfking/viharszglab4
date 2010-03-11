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
		logMessage("[" + caller.getName() + "|" + caller.toString() + "|" + caller.hashCode() + "] -> [" 
				+ called.getName() + "|" + called.toString() + "|" + caller.hashCode() + "] . " + function + "|CALL");
	}
	
	public void logReturn() {
		
	}
	
}