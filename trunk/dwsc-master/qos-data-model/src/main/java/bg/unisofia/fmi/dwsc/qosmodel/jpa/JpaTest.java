package bg.unisofia.fmi.dwsc.qosmodel.jpa;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

import org.slf4j.LoggerFactory;

import bg.unisofia.fmi.dwsc.qosmodel.dao.ManagedEntityMgrFactory;
import bg.unisofia.fmi.dwsc.qosmodel.dao.OperationDAO;
import bg.unisofia.fmi.dwsc.qosmodel.dao.OperationInvocationDAO;
import bg.unisofia.fmi.dwsc.qosmodel.dao.OperationMessageDAO;
import bg.unisofia.fmi.dwsc.qosmodel.dao.ServiceDAO;
import bg.unisofia.fmi.dwsc.qosmodel.dao.UserDAO;
import bg.unisofia.fmi.dwsc.qosmodel.domain.Operation;
import bg.unisofia.fmi.dwsc.qosmodel.domain.OperationInvocation;
import bg.unisofia.fmi.dwsc.qosmodel.domain.OperationMessage;
import bg.unisofia.fmi.dwsc.qosmodel.domain.Service;
import bg.unisofia.fmi.dwsc.qosmodel.domain.User;

public class JpaTest {

	private ServiceDAO serviceDAO;
	private OperationDAO operationDAO;
	private UserDAO userDAO;
	private OperationInvocationDAO operationInvocationDAO;
	private OperationMessageDAO opMsgDao;
	private org.slf4j.Logger logger;

	static {
		ManagedEntityMgrFactory.initialize();
	}

	public static void main(String[] args) {
		JpaTest jpaTest = new JpaTest();

		 jpaTest.addServices();
		jpaTest.testCustom();
		// jpaTest.listServices();

		// jpaTest.addUsers();

		// jpaTest.addOperationInvocations();

		jpaTest.destroy();
		ManagedEntityMgrFactory.release();
	}

	public JpaTest() {
		this.logger = LoggerFactory.getLogger(this.getClass());
		this.serviceDAO = new ServiceDAO();
		this.userDAO = new UserDAO(this.serviceDAO.getEntityMgr());
		this.operationDAO = new OperationDAO(serviceDAO.getEntityMgr());
		this.operationInvocationDAO = new OperationInvocationDAO(
				this.serviceDAO.getEntityMgr());
		this.opMsgDao = new OperationMessageDAO(this.serviceDAO.getEntityMgr());
	}

	public void addServices() {
		String[] serviceNames = { "AdditionServiceStandard", "add",
				"MultiplicityService", "multiply" };
		Collection<Service> services = new ArrayList<>();
		for (int index = 0; index < serviceNames.length; index = index + 2) {
			Service service = new Service();
			service.setName(serviceNames[index]);
			service.setServiceKey("dummy" + index);
			Operation operation = new Operation();
			operation.setName(serviceNames[index + 1]);
			operation.setService(service);
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
			if (users != null && users.size() > 0) {
				User[] usersArr = new User[users.size()];
				usersArr = users.toArray(usersArr);
				Calendar cal = Calendar.getInstance();
				int index = 0;
				for (Operation operation : operations) {
					if (operation.getName().equals("add")) {
						User user = usersArr[index % usersArr.length];
						OperationMessage operationInvocation = createOperationInvocation(
								123L, 246L, 1164, true, user, cal);
						// operationInvocation.setOperation(operation);
						// operationInvocationDAO.save(operationInvocation);
					} else if (operation.getName().equals("multiply")) {
						User user = new User();
						user.setName("staskata aha");
						OperationMessage operationInvocation = createOperationInvocation(
								456L, 93L, 3897, false, user, cal);
						// operation.add(operationInvocation);
						// operationInvocationDAO.save(operationInvocation);
					}
					index++;
				}
			}
		}
	}

	private OperationMessage createOperationInvocation(long reqSoapMsgSize,
			long respSoapMsgSize, int execTime, boolean successful, User user,
			Calendar cal) {
		OperationMessage operationInvocation = new OperationMessage();
		operationInvocation.setSize(reqSoapMsgSize);
		// operationInvocation.setRespSoapMsgSize(respSoapMsgSize);
		Timestamp startTimestamp = new Timestamp(cal.getTimeInMillis());
		operationInvocation.setProcessed(startTimestamp);
		cal.add(Calendar.MILLISECOND, execTime);
		Timestamp finishTimestamp = new Timestamp(cal.getTime().getTime());
		// operationInvocation.setResponseSent(finishTimestamp);
		// operationInvocation.setSuccessful(successful);
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

	public void testCustom() {

		String corrId = "alabalaStasalabala";
		Service service = this.serviceDAO.getService("AdditionServiceStandard");
		if (service != null) {
			Operation operation = this.operationDAO.getOperation(
					service.getId(), "add");
			if (operation != null) {
				this.logger.debug("Operation found");
				OperationInvocation opInvocation = new OperationInvocation();
				opInvocation.setCorrelationId(corrId);
				opInvocation.setOperation(operation);
				OperationMessage opMessage = new OperationMessage();
				opMessage.setCorrelationId(corrId);
				opMessage.setFlow(1);
				opMessage.setSize(254);
				Timestamp timeStamp = new Timestamp(Calendar.getInstance()
						.getTimeInMillis());
				opMessage.setProcessed(timeStamp);
				opInvocation.addOperationMessage(opMessage);
				Collection<OperationInvocation> temp = new ArrayList<>();
				temp.add(opInvocation);
				operation.setOperationInvocation(temp);
				this.operationDAO.save(operation);
			}
		}

		OperationInvocation opInvocation = this.operationInvocationDAO
				.getOperationInvocation(corrId);
		if (opInvocation != null) {
			OperationMessage opMessage = new OperationMessage();
			opMessage
					.setCorrelationId(corrId);
			opMessage.setFlow(2);
			opMessage.setSize(3634);
			Timestamp timeStamp = new Timestamp(Calendar.getInstance()
					.getTimeInMillis());
			opMessage.setProcessed(timeStamp);
			this.opMsgDao.save(opMessage);
			this.logger.debug("OPMSGG saved");
		}
	}
}
