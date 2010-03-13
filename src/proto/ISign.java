package proto;
public interface ISign extends INamedObject {
	
	boolean isBlocking();
	void vehicleEntered();
	void tick();

}
