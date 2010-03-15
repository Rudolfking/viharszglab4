package skeleton;

public abstract class Logger {
    protected int level;
    protected final int spacePerLevel = 2;
    protected boolean silent;
    protected boolean superSilent;

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean getSilent() {
        return silent;
    }

    public void setSilent(boolean silent) {
        this.silent = silent;
    }

    public void setSuperSilent(boolean ssilent) {
        this.superSilent = ssilent;
    }

    private static String className(Object o) {
        String[] fullName = o.toString().split("@");
        String[] splitName = (fullName[0]).split("\\.");
        return splitName[splitName.length - 1];
    }

    public abstract void log(String message);

    public void logMessage(String message) {
        if (!silent) log(message);
    }

    public void logCall(INamedObject caller, INamedObject called, String function) {
        if (!superSilent) {
            log("[" + caller.getName() + "|" + className(caller) + "|" + caller.hashCode() + "] -> [" + called.getName() + "|" + className(called) + "|" + called.hashCode() + "] . " + function + "|CALL");
            level++;
        }
    }

    public void logReturn(INamedObject caller, INamedObject called, String function, INamedObject result) {
        if (!superSilent) {
            level--;
            String message = "[" + caller.getName() + "|" + className(caller) + "|" + caller.hashCode() + "] <- [" + called.getName() + "|" + className(called) + "|" + called.hashCode() + "] . " + function + "|RETURN";
            if (result != null) {
                message += " [" + result.getName() + "|" + className(result) + "|" + result.hashCode() + "]";
            }
            log(message);
        }
    }

    public void logCreate(INamedObject caller, String newClassName) {
        if (!superSilent) {
            log("[" + caller.getName() + "|" + className(caller) + "|" + caller.hashCode() + "] -> creates new [" + newClassName + "]");
            level++;
        }
    }

    public void logCreated(INamedObject caller, INamedObject created) {
        if (!superSilent) {
            level--;
            log("[" + caller.getName() + "|" + className(caller) + "|" + caller.hashCode() + "] -> has created [" + created.getName() + "|" + className(created) + "|" + created.hashCode() + "]");
        }
    }
}
