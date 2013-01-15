package bg.unisofia.fmi.dwsc.qosmodel.jpa;

import java.util.ArrayList;
import java.util.Collection;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;

import bg.unisofia.fmi.dwsc.qosmodel.dao.ManagedEntityMgrFactory;
import bg.unisofia.fmi.dwsc.qosmodel.dao.OperationDAO;
import bg.unisofia.fmi.dwsc.qosmodel.dao.ServiceDAO;
import bg.unisofia.fmi.dwsc.qosmodel.domain.Operation;
import bg.unisofia.fmi.dwsc.qosmodel.domain.Service;
import bg.unisofia.fmi.dwsc.qosmodel.domain.User;

public class JpaTest {
	
	private ServiceDAO serviceDAO;
	private OperationDAO operationDAO;
	
	static {
		ManagedEntityMgrFactory.initialize();
	}

	public static void main(String[] args) {
		JpaTest jpaTest = new JpaTest();
		
		jpaTest.addServices();
		jpaTest.listServices();
		
		jpaTest.destroy();
		ManagedEntityMgrFactory.release();
	}
	
	public JpaTest() {
		this.serviceDAO = new ServiceDAO();
//		this.operationDAO = new OperationDAO(serviceDAO.getEntityMgr());
	}
	
	public void addServices() {
		String[] serviceNames = {"AdditionService", "add", "MultiplicityService", "multiply"};
		Collection<Service> services = new ArrayList<>();
		for(int index = 0; index < serviceNames.length; index=index+2) {
			Service service = new Service();
			service.setName(serviceNames[index]);
			Operation operation = new Operation();
			operation.setName(serviceNames[index+1]);
			service.add(operation);
			services.add(service);
		}
		serviceDAO.save(services);
	}
	
	public void listServices() {
		Collection<Service> services = serviceDAO.getServices();
		for (Service s : services) {
			System.out.println(s);
		}
	}
	
	public void addUsers() {
		String[] userNames = {"stanislavp@uni-sofia.bg", "krasimirb@uni-sofia.bg"};
		for(String userName : userNames) {
			User user = new User();
			user.setName(userName);
		}
	}
	
	public void destroy() {
		if(operationDAO != null) {
			operationDAO.destroy();
		}
		if(serviceDAO != null) {
			serviceDAO.destroy();
		}
	}
}
