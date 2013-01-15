package bg.unisofia.fmi.dwsc.qosmodel.jpa;

import java.util.ArrayList;
import java.util.Collection;

import bg.unisofia.fmi.dwsc.qosmodel.dao.OperationDAO;
import bg.unisofia.fmi.dwsc.qosmodel.dao.ServiceDAO;
import bg.unisofia.fmi.dwsc.qosmodel.domain.Operation;
import bg.unisofia.fmi.dwsc.qosmodel.domain.Service;

public class JpaTest {

	public static void main(String[] args) {
		ServiceDAO serviceDAO = new ServiceDAO();
//		OperationDAO operationDAO = new OperationDAO(serviceDAO.getEntityMgr());
		Service s1 = serviceDAO.save("Test Service 4");
		Operation operation = new Operation();
		operation.setName("Test Operation 2");
		Collection<Operation> ops = new ArrayList<>();
		ops.add(operation);
		s1.setOperation(ops);
		s1 = serviceDAO.save(s1);
		Collection<Service> services = serviceDAO.getServices();
//		operation.setService(services);
//		operationDAO.save(operation);
//		serviceDAO.save("Test Service 4");
//		services = serviceDAO.getServices();
		for (Service s : services) {
			System.out.println("Service id: '" + s.getId() + "', name: '"
					+ s.getName() + "'");
//			 serviceDAO.remove(s.getId());
		}
//		serviceDAO.remove(services);
	}
}
