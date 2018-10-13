package com.proj.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

import com.proj.models.ProfilePic;


@Repository
@Transactional
public class ProfilePicDAOImpl implements ProfilePicDAO{
	@Autowired
	private SessionFactory sessionFactory;
	
	
		public void uploadProfilePicture(ProfilePic profilePic) {
			Session session=sessionFactory.getCurrentSession();
			session.saveOrUpdate(profilePic);
		}

		public ProfilePic getProfilePicture(String email) {
			Session session=sessionFactory.getCurrentSession();
			ProfilePic profilePic=(ProfilePic)session.get(ProfilePic.class, email);
			return profilePic;
		}
}
