package view;

/**
 * This interface gives the observer an update method. It is used to update the
 * observed object whenever there is a change. The new information is then pulled
 * from the model.
 * 
 * @author Rick Zwaneveld
 * @version 30-01-2018
 */

public interface Observer {
	
	/**
	 * This method is called upon by an observed object.
	 */
	public void update();
}
