package bg.unisofia.fmi.dwsc.qosmodel.jpa;

import java.util.Collection;

import bg.unisofia.fmi.dwsc.qosmodel.dao.ServiceDAO;
import bg.unisofia.fmi.dwsc.qosmodel.domain.Service;

public class JpaTest {

	public static void main(String[] args) {
		ServiceDAO serviceDAO = new ServiceDAO();
		serviceDAO.save("Test Service 1");
		serviceDAO.save("Test Service 2");
		Collection<Service> services = serviceDAO.getServices();
		for (Service s : services) {
			System.out.println("Service id: '" + s.getId() + "', name: '"
					+ s.getName() + "'");
			// serviceDAO.remove(s.getId());
		}
	}
}
