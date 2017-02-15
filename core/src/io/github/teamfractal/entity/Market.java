package io.github.teamfractal.entity;

import io.github.teamfractal.entity.enums.ResourceType;
import io.github.teamfractal.exception.InvalidResourceTypeException;
import io.github.teamfractal.exception.NotCommonResourceException;

public class Market {
	/**
	 * Initialise the market
	 */
	public Market() {
		setFood(16);
		setEnergy(16);
		setOre(0);
		setRoboticon(12);
	}

	//<editor-fold desc="Resource getters and setters">
	private int food;
	private int energy;
	private int ore;
	private int roboticon;

	/**
	 * Get the amount of food in the market
	 * @return The amount of food in the market.
	 */
	int getFood() {
		return food;
	}

	/**
	 * Get the amount of energy in the market
	 * @return The amount of energy in the market.
	 */
	int getEnergy() {
		return energy;
	}

	/**
	 * Get the amount of ore in the market
	 * @return The amount of ore in the market.
	 */
	int getOre() {
		return ore;
	}

	/**
	 * Get the amount of roboticon in the market
	 * @return The amount of roboticon in the market.
	 */
	int getRoboticon() {
		return roboticon;
	}

	/**
	 * Get the total amount of all available resources added together.
	 * @return   The total amount.
	 */
	private synchronized int getTotalResourceCount() {
		return food + energy + ore + roboticon;
	}

	/**
	 * Set the amount of ore in the market
	 * @param amount                     The amount of new ore count.
	 * @throws IllegalArgumentException  If the new amount if negative, this exception will be thrown.
	 */
	synchronized void setOre(int amount) throws IllegalArgumentException {
		if (amount < 0) {
			throw new IllegalArgumentException("Error: Ore can't be negative.");
		}

		this.ore = amount;
	}

	/**
	 * Set the amount of energy in the market
	 * @param amount                     The amount of new energy count.
	 * @throws IllegalArgumentException  If the new amount if negative, this exception will be thrown.
	 */
	synchronized void setEnergy(int amount) throws IllegalArgumentException {
		if (amount < 0) {
			throw new IllegalArgumentException("Error: Energy can't be negative.");
		}

		this.energy = amount;
	}

	/**
	 * Set the amount of food in the market
	 * @param amount                     The amount of new food amount.
	 * @throws IllegalArgumentException  If the new amount if negative, this exception will be thrown.
	 */
	synchronized void setFood(int amount) throws IllegalArgumentException {
		if (amount < 0) {
			throw new IllegalArgumentException("Error: Food can't be negative.");
		}

		this.food = amount;
	}

	/**
	 * Set the amount of roboticon in the market
	 * @param amount                     The amount of new roboticon count.
	 * @throws IllegalArgumentException  If the new amount if negative, this exception will be thrown.
	 */
	void setRoboticon(int amount) throws IllegalArgumentException {
		if (amount < 0) {
			throw new IllegalArgumentException("Error: Roboticon can't be negative.");
		}

		roboticon = amount;
	}
	//</editor-fold>

	/**
	 * Get the amount of specific resource.
	 * @param type   The {@link ResourceType}.
	 * @return       The amount.
	 */
	public int getResource(ResourceType type) {
		switch (type) {
			case ORE:
				return getOre();

			case ENERGY:
				return getEnergy();

			case ROBOTICON:
				return getRoboticon();

			case FOOD:
				return getFood();
				
			case CUSTOMISATION:
				return 1000;

			default:
				throw new NotCommonResourceException(type);
		}
	}



	/**
	 * Set the amount of specific resource.
	 * @param type   The {@link ResourceType}.
	 * @param amount The new resource amount.
	 * @throws IllegalArgumentException      Will be thrown if the new amount is negative.
	 * @throws InvalidResourceTypeException  Will be thrown if the resource specified is invalid.
	 */
	void setResource(ResourceType type, int amount)
			throws IllegalArgumentException, InvalidResourceTypeException {

		switch (type) {
			case ORE:
				setOre(amount);
				break;

			case ENERGY:
				setEnergy(amount);
				break;

			case ROBOTICON:
				setRoboticon(amount);
				break;

			case FOOD:
				setFood(amount);
				break;
				
			case CUSTOMISATION:
				break;

			default:
				throw new NotCommonResourceException(type);
		}

	}

	/**
	 * Method to ensure the market have enough resources for user to purchase.
	 * @param type    The {@link ResourceType}.
	 * @param amount  the amount of resource to check.
	 * @return        If there are enough resources.
	 * @throws InvalidResourceTypeException  Will be thrown if the resource specified is invalid.
	 */
	boolean hasEnoughResources(ResourceType type, int amount)
			throws InvalidResourceTypeException {
		int resource = getResource(type);
		return amount <= resource;
	}

	/**
	 * Get the single price for a resource type.
	 * Buying price dictated via exponential growth in getCurvePoint
	 * THIS DOES NOT WORK AS OF YET
	 * @param resource   The {@link ResourceType}.
	 * @return           The buy in price.
	 */
	public int getBuyPrice(ResourceType resource) {
		int price;
		switch (resource) {
			case ORE:
				price = getCurvePoint(resource, 7, true);
				return price;

			case ENERGY:
				price = getCurvePoint(resource, 8, true);
				return price;

			case FOOD:
				price = getCurvePoint(resource, 9 , true);
				return price;

			case ROBOTICON:
				price = getCurvePoint(resource, 10, true);
				return price;

			case CUSTOMISATION:
				price = 10;
				return price;

			default:
				throw new IllegalArgumentException("Error: Resource type is incorrect.");
		}
	}

	/**
	 * Get the single price for a resource type.
	 * Selling prices will be dictated via exponential decay in getCurvePoint
	 * @param resource   The {@link ResourceType}.
	 * @return           The sell price.
	 */
	public int getSellPrice(ResourceType resource) {
		int price;
		switch (resource) {
			case ORE:
				price = getCurvePoint(resource, 7, true);
				return price;

			case ENERGY:
				price = getCurvePoint(resource, 8, true);
				return price;

			case FOOD:
				price = getCurvePoint(resource, 9, true);
				return price;

			case ROBOTICON:
				price = getCurvePoint(resource, 10, true);
				return price;

			case CUSTOMISATION:
				price = 10;
				return price;

			default:
				throw new IllegalArgumentException("Error: Resource type is incorrect.");
		}
	}

	private int getCurvePoint(ResourceType resource, int curveMultiplier, boolean selling){
		int sellBuyMultiplier;
		float pointCurve;

		if (selling){
			sellBuyMultiplier = 1;
		}
		else{
			sellBuyMultiplier = -1;
		}

		pointCurve = curveMultiplier^(sellBuyMultiplier*((1/20)*getResource(resource)));
		return Math.round(pointCurve);
	}


	/**
	 * Buy Resource from the market, caller <i>must</i> be doing all the checks.
	 * For example, take money away from the player.
	 *
	 * This method will only increase the amount of specified resource.
	 *
	 * @param resource    The {@link ResourceType}
	 * @param amount      The amount of resource to buy in.
	 */
	public synchronized void buyResource(ResourceType resource, int amount){
		setResource(resource, getResource(resource) + amount);
	}

	/**
	 * Sell Resource from the market, caller <i>must</i> be doing all the checks.
	 * For example, add money in to the player.
	 *
	 * This method will only decrease the amount of specified resource.
	 *
	 * @param resource    The {@link ResourceType}
	 * @param amount      The amount of resource to sell out.
	 */
	public synchronized void sellResource(ResourceType resource, int amount) {
		setResource(resource, getResource(resource) - amount);
	}
}








