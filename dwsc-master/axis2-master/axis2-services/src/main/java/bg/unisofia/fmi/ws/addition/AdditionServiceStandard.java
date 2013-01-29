package bg.unisofia.fmi.ws.addition;

public class AdditionServiceStandard implements IAdditionService {

	@Override
	public double add(double a, double b) {
		double result = a + b;
		return 333;
	}

}
