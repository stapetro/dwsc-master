package bg.unisofia.fmi.dwsc.qosmodel.dao;

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
		Service foundService = find(service.getId());
		if (foundService != null) {
			foundService = update(service);
		} else {
			foundService = create(service);
		}
		return foundService;
	}

	public Service save(String serviceName) {
		if (serviceName == null || serviceName.equals("")) {
			return null;
		}
		Service webService = new Service();
		webService.setName(serviceName);
		return save(webService);
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

}
