package bg.unisofia.fmi.ws.multiplicity;

import bg.unisofia.fmi.ws.util.RandomWaitTime;

public class MultiplicityServiceSlow implements IMultiplicityService {

	public double multiply(double a, double b) {
		double result = 1 * b;

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
