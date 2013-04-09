package springdata.jpa.service;

/**
 * This exception is thrown if the requested contact is not found.
 * @author Petri Kainulainen
 */
public class NotFoundException extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NotFoundException(String message) {
        super(message);
    }
}
