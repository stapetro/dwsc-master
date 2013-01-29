package bg.unisofia.fmi.ws.util;

import java.util.Random;

public class RandomWaitTime {
	/**
	 * The maximum time in milliseconds to wait for the service to return the
	 * result
	 */
	private static final int WAIT_UPPER_LIMIT_MILIS = 1000;

	public static int getWaitTimeInMiliseconds() {
		Random rnd = new Random();

		int waitTime = rnd.nextInt(WAIT_UPPER_LIMIT_MILIS);
		return waitTime;
	}
}
