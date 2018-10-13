package com.proj.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.proj.models.BlogComment;
import com.proj.models.BlogPost;
import com.proj.models.Friend;
import com.proj.models.Job;
import com.proj.models.Notification;
import com.proj.models.ProfilePic;
import com.proj.models.User;


@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages="com.socio.*")
public class DBConfiguration {
	public DBConfiguration(){
		 System.out.println("DBConfiguration class is instantiated"); 
	  }
	  @Bean
		public SessionFactory sessionFactory() {
			LocalSessionFactoryBuilder lsf=
					new LocalSessionFactoryBuilder(getDataSource());
			Properties hibernateProperties=new Properties();
			hibernateProperties.setProperty(
					"hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
			hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "update");
			hibernateProperties.setProperty("hibernate.show_sql", "true");
			lsf.addProperties(hibernateProperties);
			Class classes[]=new Class[]{User.class,Job.class,BlogPost.class,Notification.class,BlogComment.class,Friend.class,ProfilePic.class};//class objects of all entities
		   return lsf.addAnnotatedClasses(classes).buildSessionFactory();
		}
		@Bean
		public DataSource getDataSource() {
		    BasicDataSource dataSource = new BasicDataSource();
		    dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
		    dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
		    dataSource.setUsername("Test");
		    dataSource.setPassword("Test");
		    System.out.println("DB connected");
		    return dataSource;	    
		}
		@Bean
		public HibernateTransactionManager hibTransManagement(){
			return new HibernateTransactionManager(sessionFactory());
		}
	}