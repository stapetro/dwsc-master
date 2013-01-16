package bg.unisofia.fmi.dwsc.qosmodel.dao;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import bg.unisofia.fmi.dwsc.qosmodel.domain.User;

/**
 * Represents DAO for user entities.
 * 
 */
public class UserDAO extends GenericAppManagedDAOImpl<User> {

	public UserDAO() {
		super();
	}

	public UserDAO(EntityManager entityMgr) {
		super(entityMgr);
	}

	public User save(User user) {
		User foundUser = this.save(user, true);
		return foundUser;
	}

	public User save(String userName) {
		if (userName == null || userName.equals("")) {
			return null;
		}
		User user = new User();
		user.setName(userName);
		return save(user);
	}

	public Collection<User> save(Collection<User> users) {
		if (users != null && users.size() > 0) {
			Collection<User> newUsers = new ArrayList<>();
			EntityTransaction tx = getTransaction();
			tx.begin();
			for (User user : users) {
				User newUser = this.save(user, false);
				if (newUser != null) {
					newUsers.add(newUser);
				}
			}
			tx.commit();
			return newUsers;
		}
		return null;
	}

	public void remove(Collection<User> users) {
		if (users != null && users.size() > 0) {
			EntityTransaction tx = getTransaction();
			tx.begin();
			for (User user : users) {
				remove(user.getId(), false);
			}
			tx.commit();
		}
	}

	public Collection<User> getUsers() {
		TypedQuery<User> query = this.entityMgr.createQuery(
				"Select user from User user", User.class);
		return query.getResultList();
	}

	/**
	 * 
	 * @param user
	 *            User entity to be specified.
	 * @param createTransaction
	 *            True - create transaction, false - otherwise.
	 * @return Saved user
	 */
	private User save(User user, boolean createTransaction) {
		if (user != null) {
			User foundUser = find(user.getId());
			if (foundUser != null) {
				foundUser = update(user, createTransaction);
			} else {
				foundUser = create(user, createTransaction);
			}
			return foundUser;
		}
		return null;
	}

}
