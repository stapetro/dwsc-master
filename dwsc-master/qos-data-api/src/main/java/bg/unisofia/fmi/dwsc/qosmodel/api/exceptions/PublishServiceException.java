package bg.unisofia.fmi.dwsc.qosmodel.api.exceptions;

/**
 * Stores error messages when publishing a service fails.
 * 
 */
public class PublishServiceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5464868579113127195L;

	public PublishServiceException(String message) {
		super(message);
	}

	public PublishServiceException(String message, Exception exception) {
		super(message, exception);
	}
}
