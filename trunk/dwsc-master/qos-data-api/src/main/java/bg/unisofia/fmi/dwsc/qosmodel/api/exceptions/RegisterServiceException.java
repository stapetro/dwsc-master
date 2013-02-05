package bg.unisofia.fmi.dwsc.qosmodel.api.exceptions;

/**
 * Stores error messages when publishing a service fails.
 * 
 */
public class RegisterServiceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5464868579113127195L;

	public RegisterServiceException(String message) {
		super(message);
	}

	public RegisterServiceException(String message, Exception exception) {
		super(message, exception);
	}
}
