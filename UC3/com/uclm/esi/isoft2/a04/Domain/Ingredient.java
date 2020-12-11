import General.Domain.*;

package Domain;

public interface Ingredient {
	
	private int id;
	private float amount;
	private String name;
	
	public Ingredient(int id, float amount, String name) {
		
		this.id = id;
		this.amount = amount;
		this.name = name;
		
	}

	public int getID() {
		
		return this.id;
		
	}

	float getAmount() {
		
		return this.amount;
		
	}
	
	public string getName() {
		
		return this.name
				
	}

	/**
	 * 
	 * @param amount
	 */
	public void setAmount(float amount) {
		this.amount = amount;
	}

	
	/**
	 * 
	 * @param name
	 */
	public void setName(string name) {
		
		this.name = name;
		
	}

}