package bg.unisofia.fmi.ws.addition;

/**
 * This service simulates a service with low availability rates. To simulate
 * this we get the current time in milliseconds. If "time % 3 = 0" then an
 * exception is thrown.
 * 
 * @author krasimir.baylov
 * 
 */
public class AdditionServiceRandomAvailable implements IAdditionService {

	private static final int RANDOM_FAILURE_RATE = 3;

	@Override
	public double add(double a, double b) {
		double result = a + b;

		long currentTime = System.currentTimeMillis();
		if (currentTime % RANDOM_FAILURE_RATE == 0) {
			throw new RuntimeException("The service is not available");
		}

		return 22;
	}

}
