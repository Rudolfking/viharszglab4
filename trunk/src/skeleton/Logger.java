package skeleton;

/**
 * Általános absztrakt naplózó osztály, mely egy nem definiált naplózó 
 * műveletet használva többszintű, speciális naplózást valósít meg.
 */
public abstract class Logger {
    protected int level;
    protected final int spacePerLevel = 2;
    protected boolean silent;
    protected boolean superSilent;

	/**
	 * level adattag elérése.
	 * @return level adattag értéke
	 */
    public int getLevel() {
        return level;
    }

	/**
	 * level adattag beállítása
	 * @param level level adattag új értéke
	 */
	public void setLevel(int level) {
        this.level = level;
    }

	/**
	 * silent adattag elérése
	 * @param silent adattag értéke
	 */
    public boolean getSilent() {
        return silent;
    }

	/**
	 * silent adattag beállítása
	 * @param silent silent adattag új értéke
	 */
    public void setSilent(boolean silent) {
        this.silent = silent;
    }

	/**
	 * superSilent adattag beállítása
	 * @param superSilent superSilent adattag új értéke
	 */
    public void setSuperSilent(boolean ssilent) {
        this.superSilent = ssilent;
    }

	/**
	 * Az átadott objektum osztályának nevét határozza meg
	 * @param o az objektum, aminek az osztálynevére vagyunk kíváncsiak
	 * @return az osztály neve
	 */
    private static String className(Object o) {
        String[] fullName = o.toString().split("@");
        String[] splitName = (fullName[0]).split("\\.");
        return splitName[splitName.length - 1];
    }

	/**
	 * Absztakt naplózó függvény, az összes többi ezt használja. Egy leszármazottban ezt felüldefiniálva
	 * egy konkrét naplózó osztályt lehet készíteni.
	 * @param message a naplózandó szöveges üzenet
	 */
    public abstract void log(String message);

	/**
	 * Csak akkor naplóz, ha az osztály nincs csendes módban.
	 * @param message a naplózandó szöveges üzenet
	 */
    public void logMessage(String message) {
        if (!silent) log(message);
    }

	/**
	 * Eljáráshívás naplózása.
	 * @param caller a hívó objektum
	 * @param called a hívott objektum
	 * @param function a hívott függvény
	 */
    public void logCall(INamedObject caller, INamedObject called, String function) {
        if (!superSilent) {
            log("[" + caller.getName() + "|" + className(caller) + "|" + caller.hashCode() + "] -> [" + called.getName() + "|" + className(called) + "|" + called.hashCode() + "] . " + function + "|CALL");
            level++;
        }
    }

	/**
	 * Eljárás-visszatérés naplózása.
	 * @param caller a hívó objektum
	 * @param called a hívott objektum
	 * @param function a hívott függvény
	 * @param result a függvény visszatérési értéke. Ha a függvény void, akkor null
	 */
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

	/**
	 * Konstruktor hívásának naplózása.
	 * @param caller a hívó objektum
	 * @param newClassName a létrehozandó objektum osztálya
	 */
    public void logCreate(INamedObject caller, String newClassName) {
        if (!superSilent) {
            log("[" + caller.getName() + "|" + className(caller) + "|" + caller.hashCode() + "] -> creates new [" + newClassName + "]");
            level++;
        }
    }

	/**
	 * Konstruktor visszatérésének naplózása.
	 * @param caller a hívó objektum
	 * @param created a létrehozott objektum
	 */
    public void logCreated(INamedObject caller, INamedObject created) {
        if (!superSilent) {
            level--;
            log("[" + caller.getName() + "|" + className(caller) + "|" + caller.hashCode() + "] -> has created [" + created.getName() + "|" + className(created) + "|" + created.hashCode() + "]");
        }
    }
}
