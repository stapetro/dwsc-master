package bg.unisofia.fmi.ws.addition;

import java.util.Random;

public class AdditionServiceSlow implements IAdditionService {

	/**
	 * The maximum time in milliseconds to wait for the service to return the
	 * result
	 */
	private static final int WAIT_UPPER_LIMIT_MILIS = 1000;

	@Override
	public double add(double a, double b) {
		double result = a + b;

		int waitTimeInMiliseconds = getWaitTimeInMiliseconds();
		try {
			Thread.sleep(waitTimeInMiliseconds);
		} catch (InterruptedException e) {
			// Intentionally left blank
			// this is a test service
			e.printStackTrace();
		}
		return result;
	}

	private int getWaitTimeInMiliseconds() {
		Random rnd = new Random();

		int waitTime = rnd.nextInt(WAIT_UPPER_LIMIT_MILIS);
		return waitTime;
	}
}
