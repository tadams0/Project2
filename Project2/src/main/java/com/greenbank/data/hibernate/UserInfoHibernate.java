package com.greenbank.data.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.greenbank.beans.UserInfo;
import com.greenbank.data.UserInfoDAO;
import com.greenbank.utils.HibernateUtil;

import com.greenbank.utils.LogUtil;


@Component
public class UserInfoHibernate implements UserInfoDAO{

	@Autowired
	private HibernateUtil hu;

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
		String query = "from com.greenbank.beans.UserInfo user where user.username=:username and user.user_password=:password";
		Query<UserInfo> q = s.createQuery(query, UserInfo.class);
		q.setParameter("username", username);
		q.setParameter("password", password);
		UserInfo u = q.uniqueResult();
		s.close();
		return u;
	}

	@Override
	public UserInfo getUser(UserInfo u) {
		Session s = hu.getSession();
		UserInfo ret = s.get(UserInfo.class, u.getId());
		if(ret==null) {
			String query = "from userinfo u where u.username=:username and u.user_password=:password";
			Query<UserInfo> q = s.createQuery(query, UserInfo.class);
			q.setParameter("username", u.getUsername());
			q.setParameter("password", u.getPassword());
			ret = q.getSingleResult();
		}
		s.close();
		return ret;
	}

	@Override
	public UserInfo getUserById(UserInfo u) {
		Session s = hu.getSession();
		UserInfo ret = s.get(UserInfo.class, u.getId());
		s.close();
		return ret;
	}

	@Override
	public void deleteUser(UserInfo user) {
		 Session s = hu.getSession();
		 Transaction t = s.beginTransaction();
		 s.delete(user.getId());
		 t.commit();
		 s.close();
		
	}

	@Override
	public void updateUser(UserInfo user) {
		 Session s = hu.getSession();
		 Transaction t = s.beginTransaction();
		 s.update(user.getId());
		 t.commit();
		 s.close();
		
	}
	
	
}
