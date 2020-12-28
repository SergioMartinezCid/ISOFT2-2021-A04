package es.uclm.esi.isoft2.a04.Domain;

import java.util.Date;

/**
 * @version 0.1.1
 *
 */
public abstract class FoodImplementation implements Food {

	private int id;
	private String name;
	private float cost;
	private int status;

	private OrderImplementation order;
	private int quantity;
	private Date timeReady;
	private Date timeDelivered;

	/**
	 * Empty constructor
	 */
	public FoodImplementation() {
	}

	/**
	 * @param id The id of the food in the database
	 */
	public FoodImplementation(int id) {
		this();
		this.setID(id);
	}

	@Override
	public int getID() {
		return this.id;
	}

	/**
	 * @param id The id of the food in the database
	 */
	public void setID(int id) {
		this.id = id;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public float getCost() {
		return cost;
	}

	@Override
	public void setCost(float cost) {
		this.cost = cost;
	}

	/**
	 * @return The order of this food
	 */
	public OrderImplementation getOrder() {
		return order;
	}

	/**
	 * @param order The order of this food
	 */
	public void setOrder(OrderImplementation order) {
		this.order = order;
	}

	/**
	 * @return The number of times this food appears in the order
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity The number of times this food appears in the order
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * @return The date and time when the food was ready for delivering it, or null
	 *         if it is not ready
	 */
	public Date getTimeReady() {
		return timeReady;
	}

	/**
	 * @param timeReady The date and time when the food was ready for delivering it,
	 *                  or null if it is not ready
	 */
	public void setTimeReady(Date timeReady) {
		this.timeReady = timeReady;
	}

	/**
	 * @return The date and time when the food was delivered for delivering it, or
	 *         null if it has not been delivered
	 */
	public Date getTimeDelivered() {
		return timeDelivered;
	}

	/**
	 * @param timeDelivered The date and time when the food was delivered for
	 *                      delivering it, or null if it has not been delivered
	 */
	public void setTimeDelivered(Date timeDelivered) {
		this.timeDelivered = timeDelivered;
	}

	/**
	 * @param status the new status of this food
	 */
	@Override
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * @return the status of this food
	 */
	@Override
	public int getStatus() {
		return this.status;
	}
}