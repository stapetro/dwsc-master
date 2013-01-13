package bg.unisofia.fmi.dwsc.qosmodel.dao;

import javax.persistence.EntityTransaction;

/**
 * Represents app-managed generic DAO.
 * 
 * @param <T>
 *            Entity type to be specified.
 */
public interface GenericAppManagedDAO<T> {

	public T create(T t);

	public T create(T t, EntityTransaction tx);

	public T update(T t);

	public T update(T t, EntityTransaction tx);

	public void remove(Object id);

	public void remove(final Object id, EntityTransaction tx);

	public T find(Object id);

}
