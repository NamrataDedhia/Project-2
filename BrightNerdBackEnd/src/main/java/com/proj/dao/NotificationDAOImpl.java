package com.proj.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.proj.models.Notification;

@Repository
@Transactional

public class NotificationDAOImpl implements NotificationDAO {

	@Autowired
	private SessionFactory sessionFactory;
	public void addNotification(Notification notification) {
		Session session=sessionFactory.getCurrentSession();
		session.save(notification);
	}
	public List<Notification> getNotificationNotViewed(String email) {
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from Notification where viewed=0 and email=?");
		query.setString(0, email);
		return query.list();
	}
	public Notification getNotification(int id) {
		Session session=sessionFactory.getCurrentSession();
		Notification notification=(Notification)session.get(Notification.class, id);
		return notification;
	}
	public void updateNotification(int id) {
		Session session=sessionFactory.getCurrentSession();
		Notification notification=(Notification)session.get(Notification.class, id);
		notification.setViewed(true);
		session.update(notification);//update notification_s190035 set viewed=1 where id=?
	}

}

