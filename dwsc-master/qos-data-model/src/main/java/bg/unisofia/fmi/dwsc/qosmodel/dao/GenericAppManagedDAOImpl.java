package bg.unisofia.fmi.dwsc.qosmodel.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class GenericAppManagedDAOImpl<T> {

	protected EntityManager entityMgr;
	
	protected Logger logger;

	/**
	 * Stores class instance of specified entity type.
	 */
	private Class<T> type;

	public GenericAppManagedDAOImpl() {
		initialize();
		this.entityMgr = ManagedEntityMgrFactory.createEntityManager();
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
	
	private void initialize() {
		this.logger = LoggerFactory.getLogger(this.getClass());
		Type t = getClass().getGenericSuperclass();
		ParameterizedType pt = (ParameterizedType) t;
		this.type = (Class) pt.getActualTypeArguments()[0];
	}
}
