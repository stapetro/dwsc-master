package bg.unisofia.fmi.ws.pow;

public class PowServiceStandard implements IPowService {

	@Override
	public double pow(double a, double b) {
		double result = Math.pow(a, b);
		return result;
	}

}
