package com.proj.dao;

import java.util.List;

import com.proj.models.Job;

public interface JobDAO {
	void saveJob(Job job);
	List<Job>   getAllJobs();
	}
