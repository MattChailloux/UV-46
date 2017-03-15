package baseUnit;

public class UnitClass {

	int value;
	
	public UnitClass( int aValue ) {
		value = aValue;
		
	}
	
	public int getValue() {
		
		return value;
		
	}
	
	public void setValue( int aValue ) {
		if(aValue > 5)
			value = aValue;
		
	}
	
	public String toString() {
		
		return "Valeur de Value : " + value;
		
	}
	
	
}
