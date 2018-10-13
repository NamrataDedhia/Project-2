package com.proj.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name="BlogPost_BN")
public class BlogPost {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
private int id;
private String blogTitle;
@Lob
private String blogContent;//DATABASE datatype as CLOB
private Date postedOn;
@ManyToOne
private User postedBy;
private boolean isApproved;

private int likes;



public int getLikes() {
	return likes;
}
public void setLikes(int likes) {
	this.likes = likes;
}

public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getBlogTitle() {
	return blogTitle;
}
public void setBlogTitle(String blogTitle) {
	this.blogTitle = blogTitle;
}
public String getBlogContent() {
	return blogContent;
}
public void setBlogContent(String blogContent) {
	this.blogContent = blogContent;
}
public Date getPostedOn() {
	return postedOn;
}
public void setPostedOn(Date postedOn) {
	this.postedOn = postedOn;
}
public User getPostedBy() {
	return postedBy;
}
public void setPostedBy(User postedBy) {
	this.postedBy = postedBy;
}
public boolean isApproved() {
	return isApproved;
}
public void setisApproved(boolean isApproved) {
	this.isApproved = isApproved;
}



}