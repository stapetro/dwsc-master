package bg.unisofia.fmi.ws.pow;

import bg.unisofia.fmi.ws.util.RandomWaitTime;

public class PowServiceSlow implements IPowService {

	@Override
	public double pow(double a, double b) {
		double result = Math.pow(a, b);

		int waitTimeInMiliseconds = RandomWaitTime.getWaitTimeInMiliseconds();
		try {
			Thread.sleep(waitTimeInMiliseconds);
		} catch (InterruptedException e) {
			// Intentionally left blank
			// this is a test service
			e.printStackTrace();
		}
		return result;
	}

}
