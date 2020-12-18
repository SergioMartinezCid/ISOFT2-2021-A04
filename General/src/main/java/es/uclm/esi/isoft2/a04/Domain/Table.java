package es.uclm.esi.isoft2.a04.Domain;

public interface Table {

	static int FREE = 0;
	static int RESERVED = 1;
	static int BUSY = 2;
	static int ASKING = 3;
	static int WAITING_FOR_FOOD = 4;
	static int SERVED = 5;
	static int WAITING_FOR_BILL = 6;
	static int PAYING = 7;
	static int IN_PREPARATION = 8;

	void setState();

	int getState();

	void setSeats();

	int getSeats();

	int getID();

	void readAll();

	int read();

	int create();

	int update();

	int delete();
}