package bg.unisofia.fmi.dwsc.qosmodel.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public abstract class GenericAppManagedDAOImpl<T> {

	private static final String PERSISTANCE_UNIT_NAME = "dwscqosPU";

	private EntityManagerFactory entityMgrFactory;
	protected EntityManager entityMgr;

	/**
	 * Stores class instance of specified entity type.
	 */
	private Class<T> type;

	public GenericAppManagedDAOImpl() {
		initialize();
		getEntityMgr(PERSISTANCE_UNIT_NAME);
	}
	
	public GenericAppManagedDAOImpl(EntityManager entityMgr) {
		initialize();
		this.entityMgr = entityMgr;
	}

	public EntityManager getEntityMgr() {
		return this.entityMgr;
	}

	public T create(final T t) {
		return this.create(t, true);
	}

	public T update(final T t) {
		return this.update(t, true);
	}

	public void remove(final Object id) {
		this.remove(id);
	}

	public T find(final Object id) {
		if (id != null) {
			return this.entityMgr.find(type, id);
		}
		return null;
	}

	public void destroy() {
		if (this.entityMgr != null) {
			if (this.entityMgr.isOpen()) {
				this.entityMgr.close();
			}
			this.entityMgr = null;
		}
		if (this.entityMgrFactory != null) {
			if (this.entityMgrFactory.isOpen()) {
				this.entityMgrFactory.close();
			}
			this.entityMgrFactory = null;
		}
	}

	// @Override
	protected T create(final T t, boolean createTransaction) {
		if (createTransaction) {
			EntityTransaction tx = getTransaction();
			tx.begin();
			this.entityMgr.persist(t);
			tx.commit();
		} else {
			this.entityMgr.persist(t);
		}
		return t;
	}

	// @Override
	protected T update(final T t, boolean createTransaction) {
		T updated = null;
		if (createTransaction) {
			EntityTransaction tx = getTransaction();
			tx.begin();
			updated = this.entityMgr.merge(t);
			tx.commit();
		} else {
			updated = this.entityMgr.merge(t);
		}
		return updated;
	}

	// @Override
	protected void remove(final Object id, boolean createTransaction) {
		if (id != null) {
			T foundT = find(id);
			if (foundT != null) {
				if (createTransaction) {
					EntityTransaction tx = getTransaction();
					tx.begin();
					this.entityMgr.remove(foundT);
					tx.commit();
				} else {
					this.entityMgr.remove(foundT);
				}
			}
		}
	}

	protected EntityTransaction getTransaction() {
		return this.entityMgr.getTransaction();
	}
	
	private EntityManager getEntityMgr(String persistenceUnit) {
		if (persistenceUnit == null || persistenceUnit.equals("")) {
			throw new IllegalArgumentException("Persitence unit cannot be NULL");
		}
		if (this.entityMgrFactory == null) {
			this.entityMgrFactory = Persistence
					.createEntityManagerFactory(persistenceUnit);
		}
		if (this.entityMgr == null) {
			this.entityMgr = entityMgrFactory.createEntityManager();
		}
		return this.entityMgr;
	}
	
	private void initialize() {
		Type t = getClass().getGenericSuperclass();
		ParameterizedType pt = (ParameterizedType) t;
		this.type = (Class) pt.getActualTypeArguments()[0];
	}
}
