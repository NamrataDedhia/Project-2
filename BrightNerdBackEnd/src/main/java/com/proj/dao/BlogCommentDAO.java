package com.proj.dao;

import java.util.List;

import com.proj.models.BlogComment;

public interface BlogCommentDAO {

		void addBlogComment(BlogComment blogComment );
		List<BlogComment> getBlogComments(int blogPostId);
		}

