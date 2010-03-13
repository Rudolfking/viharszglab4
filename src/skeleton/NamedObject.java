package skeleton;
public class NamedObject implements INamedObject {

	private String name;
	
	public NamedObject(String name) {
		setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
