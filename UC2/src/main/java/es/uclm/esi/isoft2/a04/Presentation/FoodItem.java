package es.uclm.esi.isoft2.a04.Presentation;

import javax.swing.JPanel;

import es.uclm.esi.isoft2.a04.Domain.Beverage;
import es.uclm.esi.isoft2.a04.Domain.Dish;
import es.uclm.esi.isoft2.a04.Domain.Food;

public class FoodItem extends JPanel {

	private Food food;
	
	public FoodItem() {
		update();
	}
	
	public FoodItem(int type) {
		food = type == Food.DRINK ? new Beverage() : new Dish();
		update();
	}
	
	public FoodItem(Food food){
		this.food = food;
		update();
	}
	
	private void update() {
		if (food == null)
			;//pass
		
	}
}
