package bg.unisofia.fmi.ws.pow;

public class PowServiceRandomAvailability implements IPowService {

	private static final int RANDOM_FAILURE_RATE = 3;
	
	@Override
	public double pow(double a, double b) {
		double result = Math.pow(a, b);

		long currentTime = System.currentTimeMillis();
		if (currentTime % RANDOM_FAILURE_RATE == 0) {
			throw new RuntimeException("The service is not available");
		}

		return result;
	}

}
