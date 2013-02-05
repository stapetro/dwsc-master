package bg.unisofia.fmi.dwsc.qosmodel.api.exceptions;

public class SaveQoSDataException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4988488613199545261L;

	public SaveQoSDataException(String message) {
		super(message);
	}

	public SaveQoSDataException(String message, Exception exception) {
		super(message, exception);
	}
}
