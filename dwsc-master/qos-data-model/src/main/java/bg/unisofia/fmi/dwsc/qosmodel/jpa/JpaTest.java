package bg.unisofia.fmi.dwsc.qosmodel.jpa;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

import bg.unisofia.fmi.dwsc.qosmodel.dao.ManagedEntityMgrFactory;
import bg.unisofia.fmi.dwsc.qosmodel.dao.OperationDAO;
import bg.unisofia.fmi.dwsc.qosmodel.dao.OperationInvocationDAO;
import bg.unisofia.fmi.dwsc.qosmodel.dao.ServiceDAO;
import bg.unisofia.fmi.dwsc.qosmodel.dao.UserDAO;
import bg.unisofia.fmi.dwsc.qosmodel.domain.Operation;
import bg.unisofia.fmi.dwsc.qosmodel.domain.OperationInvocation;
import bg.unisofia.fmi.dwsc.qosmodel.domain.Service;
import bg.unisofia.fmi.dwsc.qosmodel.domain.User;

public class JpaTest {

	private ServiceDAO serviceDAO;
	private OperationDAO operationDAO;
	private UserDAO userDAO;
	private OperationInvocationDAO operationInvocationDAO;

	static {
		ManagedEntityMgrFactory.initialize();
	}

	public static void main(String[] args) {
		JpaTest jpaTest = new JpaTest();

		jpaTest.addServices();
		// jpaTest.listServices();

		jpaTest.addUsers();
		
		jpaTest.addOperationInvocations();
		
		jpaTest.destroy();
		ManagedEntityMgrFactory.release();
	}

	public JpaTest() {
		this.serviceDAO = new ServiceDAO();
		this.userDAO = new UserDAO(this.serviceDAO.getEntityMgr());
		this.operationDAO = new OperationDAO(serviceDAO.getEntityMgr());
		this.operationInvocationDAO = new OperationInvocationDAO(this.serviceDAO.getEntityMgr());
	}

	public void addServices() {
		String[] serviceNames = { "AdditionService", "add",
				"MultiplicityService", "multiply" };
		Collection<Service> services = new ArrayList<>();
		for (int index = 0; index < serviceNames.length; index = index + 2) {
			Service service = new Service();
			service.setName(serviceNames[index]);
			service.setServiceKey("dummy" + index);
			Operation operation = new Operation();
			operation.setName(serviceNames[index + 1]);
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
		String[] userNames = { "stanislavp@uni-sofia.bg",
				"krasimirb@uni-sofia.bg" };
		Collection<User> users = new ArrayList<>();
		for (String userName : userNames) {
			User user = new User();
			user.setName(userName);
			users.add(user);
		}
		userDAO.save(users);
	}

	public void addOperationInvocations() {
		Collection<Operation> operations = operationDAO.getOperations();
		if (operations != null && operations.size() > 0) {
			Collection<User> users = userDAO.getUsers();
			if(users != null && users.size() > 0) {
				User[] usersArr = new User[users.size()];
				usersArr = users.toArray(usersArr);
				Calendar cal = Calendar.getInstance();
				int index = 0;
				for (Operation operation : operations) {
					if (operation.getName().equals("add")) {
						User user = usersArr[index%usersArr.length];
						OperationInvocation operationInvocation = createOperationInvocation(
								123L, 246L, 1164, true, user, cal);
						operationInvocation.setOperation(operation);
						operationInvocationDAO.save(operationInvocation);
					} else if (operation.getName().equals("multiply")) {
						User user = new User();
						user.setName("staskata aha");
						OperationInvocation operationInvocation = createOperationInvocation(
								456L, 93L, 3897, false, user, cal);
						operation.add(operationInvocation);
						operationInvocationDAO.save(operationInvocation);
					}
					index++;
				}
			}
		}
	}

	private OperationInvocation createOperationInvocation(long reqSoapMsgSize,
			long respSoapMsgSize, int execTime, boolean successful, User user, Calendar cal) {
		OperationInvocation operationInvocation = new OperationInvocation();
		operationInvocation.setReqSoapMsgSize(reqSoapMsgSize);
		operationInvocation.setRespSoapMsgSize(respSoapMsgSize);
		Timestamp startTimestamp = new Timestamp(cal.getTimeInMillis());
		operationInvocation.setRequestReceived(startTimestamp);
		cal.add(Calendar.MILLISECOND, execTime);
		Timestamp finishTimestamp = new Timestamp(cal.getTime().getTime());
		operationInvocation.setResponseSent(finishTimestamp);
		operationInvocation.setSuccessful(successful);
		operationInvocation.add(user);
		user.add(operationInvocation);
		return operationInvocation;
	}

	public void destroy() {
		if (operationDAO != null) {
			operationDAO.destroy();
		}
		if (serviceDAO != null) {
			serviceDAO.destroy();
		}
		if (userDAO != null) {
			userDAO.destroy();
		}
	}
}
