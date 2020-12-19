package es.uclm.esi.isoft2.a04.Domain;

public class Ingredient {
	
	private int id;
	private float amount;
	private String name;
	
	public Ingredient (int id, String name, float amount) {
		
		this.id = id;
		this.name = name;
		this.amount = amount;
		
	}
	public int getID() {
		return this.id;
	}
	
	public float getAmount() {
		return this.amount;
	}
	
	public void setAmount(float amount) {
		this.amount = amount;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
}
