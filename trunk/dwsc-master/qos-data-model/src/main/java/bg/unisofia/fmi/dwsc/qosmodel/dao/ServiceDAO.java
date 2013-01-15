package bg.unisofia.fmi.dwsc.qosmodel.dao;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import bg.unisofia.fmi.dwsc.qosmodel.domain.Service;

/**
 * Represents DAO for service entities.
 * 
 */
public class ServiceDAO extends GenericAppManagedDAOImpl<Service> {

	public ServiceDAO() {
		super();
	}

	public ServiceDAO(EntityManager entityMgr) {
		super(entityMgr);
	}

	public Service save(Service service) {
		return this.save(service, true);
	}

	public Collection<Service> save(Collection<Service> services) {
		if (services != null && services.size() > 0) {
			Collection<Service> newServices = new ArrayList<>();
			EntityTransaction tx = getTransaction();
			tx.begin();
			for (Service service : services) {
				Service newService = this.save(service, false);
				if(newService != null) {
					newServices.add(newService);
				}
			}
			tx.commit();
			return newServices;
		}
		return null;
	}

	public Service save(String serviceName) {
		if (serviceName == null || serviceName.equals("")) {
			return null;
		}
		Service webService = new Service();
		webService.setName(serviceName);
		return this.save(webService, true);
	}

	public void remove(Collection<Service> services) {
		if (services != null && services.size() > 0) {
			EntityTransaction tx = getTransaction();
			tx.begin();
			for (Service service : services) {
				remove(service.getId(), false);
			}
			tx.commit();
		}
	}

	public Collection<Service> getServices() {
		TypedQuery<Service> query = this.entityMgr.createQuery(
				"Select s from Service s", Service.class);
		return query.getResultList();
	}
	
	/**
	 * 
	 * @param service
	 *            Service entity to be specified.
	 * @param createTransaction
	 *            True - create transaction, false - otherwise.
	 * @return Saved service
	 */
	private Service save(Service service, boolean createTransaction) {
		if(service != null) {
			Service foundService = find(service.getId());
			if (foundService != null) {
				foundService = update(service, createTransaction);
			} else {
				foundService = create(service, createTransaction);
			}
			return foundService;
		}
		return null;
	}

}
