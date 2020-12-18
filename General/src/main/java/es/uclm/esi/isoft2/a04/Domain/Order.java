package es.uclm.esi.isoft2.a04.Domain;

import java.util.Date;

public interface Order {

	int getID();

	Date getDatetime();

	void setDatetime(Date datetime);

	Waiter getWaiter();

	Table getTable();

	Food[] getFood();

	void setFood(Food[] food);

	float getCost();

	void readAll();

	int read();

	int create();

	int update();

	int delete();

}