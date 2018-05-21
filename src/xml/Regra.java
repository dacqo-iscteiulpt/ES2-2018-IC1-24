package xml;

public class Regra {
	private String name;
	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public int getLow() {
		return low;
	}

	public int getHigh() {
		return high;
	}

	private String type;
	private int low, high;
	
	public Regra(String name, String type, String low, String high) {
		this.name = name;
		this.type= type;
		this.low = Integer.parseInt(low);
		this.high = Integer.parseInt(high);
	}

	@Override
	public String toString() {
		return "Regra [name=" + name + ", type=" + type + ", low=" + low + ", high=" + high + "]";
	}
	
	
}
