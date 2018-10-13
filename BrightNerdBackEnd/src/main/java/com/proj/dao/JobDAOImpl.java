package com.proj.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.proj.models.Job;

@Repository
@Transactional
public class JobDAOImpl implements JobDAO{
	@Autowired
	private SessionFactory sessionFactory;
		public void saveJob(Job job) {
			Session session=sessionFactory.getCurrentSession();
			session.save(job);

		}
		public List<Job> getAllJobs() {
			Session session=sessionFactory.getCurrentSession();
	        Query query=session.createQuery("from Job");//HQL
	        List<Job> jobs=query.list();
	        return jobs;
		}

	}


