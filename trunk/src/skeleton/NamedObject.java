package skeleton;
public class NamedObject implements INamedObject {

	private String name;
	protected CustomReader input;
	protected Logger logger;
	
	public NamedObject(String name,Logger logger,
			CustomReader input) {
		setName(name);
		this.logger=logger;
		this.input=input;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
