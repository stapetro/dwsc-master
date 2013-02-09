package bg.unisofia.fmi.dwsc.uddi;

public class UddiUtilStarter {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DwscServicesManager dwscPublisher = new DwscServicesManager();
		dwscPublisher.publish();
		dwscPublisher.release();
		dwscPublisher = null;
	}
}
