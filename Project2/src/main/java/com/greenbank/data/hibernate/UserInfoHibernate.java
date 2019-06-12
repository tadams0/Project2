package com.greenbank.data.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.greenbank.beans.UserInfo;
import com.greenbank.data.UserInfoDAO;
import com.greenbank.utils.HibernateUtil;

import com.greenbank.utils.LogUtil;
import com.revature.beans.User;

public class UserInfoHibernate implements UserInfoDAO{

	private HibernateUtil hu = HibernateUtil.getInstance();

	@Override
	public int addUser(UserInfo user) {
		Session s = hu.getSession();
		Transaction t = s.beginTransaction();
		Integer i = 0;
		try {
			i = (Integer) s.save(user);
			t.commit();
		}catch(HibernateException e) {
			t.rollback();
			LogUtil.logException(e, UserInfoHibernate.class);
		}finally {
			s.close();
		}
		
		return i;
	}

	@Override
	public UserInfo getUser(String username, String password) {
		
		Session s = hu.getSession();
		String query = "from bankuser u where u.username=:username and u.user_password=:password";
		Query<UserInfo> q = s.createQuery(query, UserInfo.class);
		q.setParameter("username", username);
		q.setParameter("password", password);
		UserInfo u = q.getSingleResult();
		s.close();
		return u;
	}

	@Override
	public UserInfo getUser(UserInfo u) {
		 
		return null;
	}

	@Override
	public UserInfo getUserById(UserInfo u) {
		 
		return null;
	}

	@Override
	public void deleteUser(UserInfo user) {
		 
		
	}

	@Override
	public void updateUser(UserInfo user) {
		 
		
	}
	
	
}
