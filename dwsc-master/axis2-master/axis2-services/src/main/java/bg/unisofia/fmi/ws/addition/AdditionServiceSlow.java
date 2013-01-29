package bg.unisofia.fmi.ws.addition;

import bg.unisofia.fmi.ws.util.RandomWaitTime;

public class AdditionServiceSlow implements IAdditionService {

	@Override
	public double add(double a, double b) {
		double result = a + b;

		int waitTimeInMiliseconds = RandomWaitTime.getWaitTimeInMiliseconds();
		try {
			Thread.sleep(waitTimeInMiliseconds);
		} catch (InterruptedException e) {
			// Intentionally left blank
			// this is a test service
			e.printStackTrace();
		}
		return 1;
	}

}
