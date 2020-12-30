package es.uclm.esi.isoft2.a04.Domain;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Arrays;

/**
 * @version 0.1.0
 *
 */
public class IngredientControl {

	/**
	 * Method used for updating the number of ingredients in the database from the
	 * forecast
	 * 
	 * @param ingredientList The list of ingredients to be updated
	 * @param amount         The amount that was added to the warehouse of each
	 *                       ingredient
	 * @return The number of modified rows
	 * @throws NotMatchingLenghtsException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public int updateIngredientsFromForecast(Ingredient[] ingredientList, float[] amount)
			throws NotMatchingLenghtsException, InstantiationException, IllegalAccessException, ClassNotFoundException,
			SQLException {
		int modifiedRows = 0;
		if (ingredientList.length != amount.length)
			throw new NotMatchingLenghtsException();
		for (int i = 0; i < amount.length; i++) {
			ingredientList[i].setAmount(ingredientList[i].getAmount() + amount[i]);
			modifiedRows += ingredientList[i].update();
		}
		return modifiedRows;
	}

	/**
	 * Method used for updating the number of beverages in the database from the
	 * forecast
	 * 
	 * @param beverageList The list of beverages to be updated
	 * @param amount       The number that was added to the warehouse of each
	 *                     beverage
	 * @return The number of modified rows
	 * @throws NotMatchingLenghtsException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public int updateBeveragesFromForecast(Beverage[] beverageList, int[] amount) throws NotMatchingLenghtsException,
			InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		int modifiedRows = 0;
		if (beverageList.length != amount.length)
			throw new NotMatchingLenghtsException();
		for (int i = 0; i < amount.length; i++) {
			beverageList[i].setAmount(beverageList[i].getAmount() + amount[i]);
			modifiedRows += beverageList[i].update();
		}
		return modifiedRows;
	}

	/**
	 * @param ingredientdb An instance of Ingredient to be used for reading from the
	 *                     database
	 * @param threshold    The quality threshold
	 * @return An array containing all ingredients below the provided threshold
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws InvalidTypeException
	 * @throws ParseException
	 */
	public Ingredient[] getIngredientsBelowThreshold(Ingredient ingredientdb, int threshold)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException,
			InvalidTypeException, ParseException {
		return (Ingredient[]) Arrays.stream(ingredientdb.readAll())
				.filter(ingredient -> ingredient.getAmount() < threshold).toArray();
	}
}