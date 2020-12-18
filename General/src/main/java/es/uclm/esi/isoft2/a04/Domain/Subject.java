package es.uclm.esi.isoft2.a04.Domain;

public interface Subject {

	/**
	 * 
	 * @param o
	 */
	void attach(Observer o);

	/**
	 * 
	 * @param o
	 */
	void detach(Observer o);

	
	void notifyMe();

}