package bg.unisofia.fmi.dwsc.qosmodel.jpa;

import java.util.ArrayList;
import java.util.Collection;

import bg.unisofia.fmi.dwsc.qosmodel.dao.ManagedEntityMgrFactory;
import bg.unisofia.fmi.dwsc.qosmodel.dao.OperationDAO;
import bg.unisofia.fmi.dwsc.qosmodel.dao.ServiceDAO;
import bg.unisofia.fmi.dwsc.qosmodel.dao.UserDAO;
import bg.unisofia.fmi.dwsc.qosmodel.domain.Operation;
import bg.unisofia.fmi.dwsc.qosmodel.domain.Service;
import bg.unisofia.fmi.dwsc.qosmodel.domain.User;

public class JpaTest {
	
	private ServiceDAO serviceDAO;
	private OperationDAO operationDAO;
	private UserDAO userDAO;
	
	static {
		ManagedEntityMgrFactory.initialize();
	}

	public static void main(String[] args) {
		JpaTest jpaTest = new JpaTest();
		
		jpaTest.addServices();
//		jpaTest.listServices();
		
		jpaTest.addUsers();
		
		jpaTest.destroy();
		ManagedEntityMgrFactory.release();
	}
	
	public JpaTest() {
		this.serviceDAO = new ServiceDAO();
		this.userDAO = new UserDAO(this.serviceDAO.getEntityMgr());
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
		Collection<User> users = new ArrayList<>();
		for(String userName : userNames) {
			User user = new User();
			user.setName(userName);
			users.add(user);
		}
		userDAO.save(users);
	}
	
	public void destroy() {
		if(operationDAO != null) {
			operationDAO.destroy();
		}
		if(serviceDAO != null) {
			serviceDAO.destroy();
		}
		if(userDAO != null) {
			userDAO.destroy();
		}
	}
}
