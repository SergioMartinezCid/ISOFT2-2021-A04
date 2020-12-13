package es.uclm.esi.isoft2.a04.Domain;

public interface Waiter extends Subject {

	int getID();

	void readAll();

	int read();

	int create();

	int update();

	int delete();
}