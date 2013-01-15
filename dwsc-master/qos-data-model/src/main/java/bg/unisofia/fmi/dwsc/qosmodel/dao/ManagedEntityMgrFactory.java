package bg.unisofia.fmi.dwsc.qosmodel.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ManagedEntityMgrFactory /*implements ServletContextListener*/ {
	
	private static final String PERSISTANCE_UNIT_NAME = "dwscqosPU";
	
	private static EntityManagerFactory entityMgrFactory;
	
	public static void initialize() {
		if(entityMgrFactory == null) {
			entityMgrFactory = Persistence
					.createEntityManagerFactory(PERSISTANCE_UNIT_NAME);
		}
	}
	
	public static void release() {
		if(entityMgrFactory.isOpen()) {
			entityMgrFactory.close();
		}
	}

/*	@Override
	public void contextInitialized(ServletContextEvent sce) {
		if(entityMgrFactory != null) {
			throw new IllegalStateException("EntityManagerFactory is not created from the right place.");
		}
			entityMgrFactory = Persistence
					.createEntityManagerFactory(PERSISTANCE_UNIT_NAME);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		if(entityMgrFactory.isOpen()) {
			entityMgrFactory.close();
		}
	}*/
	
	public static EntityManager createEntityManager() {
		if(entityMgrFactory == null) {
			throw new IllegalStateException("Context is not initialized yet");
		}
		return entityMgrFactory.createEntityManager();
	}

}
