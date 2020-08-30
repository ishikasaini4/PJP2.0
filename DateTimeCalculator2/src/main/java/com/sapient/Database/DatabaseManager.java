package com.sapient.Database;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.sapient.Pojo.operations;

public class DatabaseManager {

	private Session session;
	private int currentId;

	public DatabaseManager() {

		Configuration conf = new Configuration().configure().addAnnotatedClass(operations.class);
		ServiceRegistry registry = new ServiceRegistryBuilder().applySettings(conf.getProperties())
				.buildServiceRegistry();

		SessionFactory sf = conf.buildSessionFactory(registry);
		this.session = sf.openSession();
		this.currentId = getCurrentSessionNumber();

	}

	public void insert(String input) {

		this.session.beginTransaction();

		operations tmp = stringToObject(input);
		tmp.setId(currentId);
		tmp.setSno(getSNo());

		this.session.save(tmp);
		this.session.getTransaction().commit();

	}

	@SuppressWarnings("unchecked")
	public List<String> retrieve(int n) {

		Query query;
		if (n == 0) {
			query = this.session.createQuery("FROM operations where id = " + currentId);
		} else {
			if (n > totalSessions()) {
				query = this.session.createQuery("FROM operations");
			} else {
				query = this.session.createQuery("FROM operations ORDER BY id DESC LIMIT " + n);
			}
		}
		List<String> calcSessions = (List<String>) query.list();
		return calcSessions;

	}

	public operations stringToObject(String input) {
		String[] tmp = input.split(",");
		return new operations(tmp[0], tmp[1], tmp[2], tmp[3], tmp[4]);
	}

	private int totalSessions() {
		@SuppressWarnings("unchecked")
		List<String> distinctSessions = this.session.createQuery("SELECT count(distinct id) FROM operations").list();
		return distinctSessions.size();
	}

	private int getCurrentSessionNumber() {
		totalSessions();
		int sno = 0;
		this.session.beginTransaction();

		Query query = this.session.createQuery("SELECT max(id) FROM operations");
		Integer prevMaxSession = (Integer) query.uniqueResult();

		if (prevMaxSession == null)
			sno = 1;
		else
			sno = prevMaxSession + 1;
		session.getTransaction().commit();
		return sno;

	}

	private int getSNo() {
		int sno;

		Query query = this.session.createQuery("SELECT max(sno) FROM operations");
		Integer prevMaxSNo = (Integer) query.uniqueResult();

		if (prevMaxSNo == null)
			sno = 1;
		else
			sno = prevMaxSNo + 1;
		return sno;

	}

	public void closeSession() {
		this.session.close();
	}
}