package bg.unisofia.fmi.dwsc.qosmodel.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public abstract class GenericAppManagedDAOImpl<T> implements
		GenericAppManagedDAO<T> {

	private static final String PERSISTANCE_UNIT_NAME = "dwscqosPU";

	private EntityManagerFactory entityMgrFactory;
	protected EntityManager entityMgr;

	/**
	 * Stores class instance of specified entity type.
	 */
	private Class<T> type;

	public GenericAppManagedDAOImpl() {
		Type t = getClass().getGenericSuperclass();
		ParameterizedType pt = (ParameterizedType) t;
		this.type = (Class) pt.getActualTypeArguments()[0];
		getEntityMgr(PERSISTANCE_UNIT_NAME);
	}

	@Override
	public T create(final T t) {
		EntityTransaction tx = this.entityMgr.getTransaction();
		return create(t, tx);
	}

	@Override
	public T create(final T t, EntityTransaction tx) {
		tx.begin();
		this.entityMgr.persist(t);
		tx.commit();
		return t;
	}

	@Override
	public T update(final T t) {
		EntityTransaction tx = this.entityMgr.getTransaction();
		return update(t, tx);
	}

	@Override
	public T update(final T t, EntityTransaction tx) {
		tx.begin();
		T updated = this.entityMgr.merge(t);
		tx.commit();
		return updated;
	}

	@Override
	public void remove(final Object id) {
		EntityTransaction tx = this.entityMgr.getTransaction();
		remove(id, tx);
	}

	@Override
	public void remove(final Object id, EntityTransaction tx) {
		if (id != null) {
			T foundT = find(id);
			if (foundT != null) {
				tx.begin();
				this.entityMgr.remove(foundT);
				tx.commit();
			}
		}
	}

	@Override
	public T find(final Object id) {
		if (id != null) {
			return this.entityMgr.find(type, id);
		}
		return null;
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
}
