package com.ming.server.session;

import java.util.*;

import javax.servlet.http.*;

import org.hibernate.*;

import com.ming.server.persistence.*;

/**
 * Each call to the api starts a MingSession, which is retrievable from anywhere.
 * 
 * You can retrieve convenient objects from the session, such as request and 
 * response objects, hibernate session objects, as well as store any object you want in a map.
 */
public class MingSession {

	protected static Map<Thread, MingSession> lookup = new HashMap();
	
	public static void start(HttpServletRequest request, HttpServletResponse response) {
		lookup.put(Thread.currentThread(), new MingSession(request, response));
	}
	
	public static MingSession get() {
		MingSession ms = lookup.get(Thread.currentThread());
		
		if(ms == null) lookup.put(Thread.currentThread(), new MingSession(null, null));
		
		return lookup.get(Thread.currentThread());
	}
	
	public static void end() {
		MingSession ms = lookup.remove(Thread.currentThread());
		ms.commit();
	}
	
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	
	protected Session hibernateSession;
	
	protected String accountId;
	protected String userId;
	
	protected Map<String, Object> dataMap;
	
	private MingSession(HttpServletRequest request, HttpServletResponse response) {
		dataMap = new HashMap();
		this.request = request;
		this.response = response;
		
		try {
			hibernateSession = HibernateStarter.sessionFactory.openSession();
			hibernateSession.beginTransaction();
		} catch (NullPointerException e) {
			System.out.println("Failed to initialize hibernatesession");
			e.printStackTrace();
		}
	}
	
	public HttpServletRequest getRequest() {
		return request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public boolean hasAccount() {
		return accountId != null;
	}
	
	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	/**
	 * Store any data you want.
	 */
	public void setData(String key, Object value) {
		dataMap.put(key, value);
	}
	
	public Object getData(String key) {
		return dataMap.get(key);
	}

	public Session getHibernateSession() {
		return hibernateSession;
	}

	protected void commit() {
		try {
			hibernateSession.getTransaction().commit();
		} catch (NullPointerException e) {
			System.out.println("Failed to commit");
			e.printStackTrace();
		}
	}
	
}
