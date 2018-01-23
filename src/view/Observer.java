package view;

/**
 * This interface gives the observer an update method. It is used to updated the
 * observed object whenever their is a change. The new information is then pulled
 * from the model.
 * 
 * @author Rick Zwaneveld
 * @version 23-01-2018
 */

public interface Observer {
	
	/**
	 * This method is called upon by an observed object.
	 */
	public void update();
}
