package es.uclm.esi.isoft2.a04.Domain;

/**
 * @version 0.1.0
 *
 */
public interface Subject {

	/**
	 * @param o The observer to be attached to this subject
	 */
	void attach(Observer o);

	/**
	 * @param o The observer to be detached from this subject
	 */
	void detach(Observer o);

	/**
	 * 
	 */
	void notifyMe();

}