package proto;

public interface ISign extends INamedObject {
    
	boolean isBlocking();
	void setBlocking(boolean value);

    void vehicleEntered();

    void tick();
}
