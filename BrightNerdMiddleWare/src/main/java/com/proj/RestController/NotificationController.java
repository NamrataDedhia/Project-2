package com.proj.RestController;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.proj.dao.NotificationDAO;
import com.proj.models.ErrorClazz;
import com.proj.models.Notification;

@Controller
public class NotificationController {
	@Autowired
private NotificationDAO notificationDAO;
	
	public NotificationController() {
		System.out.println("Notification Controller bean created");
	}
	
	
	@RequestMapping(value="/notifications",method=RequestMethod.GET)
public ResponseEntity<?> getNotificationsNotViewed(HttpSession session){
	String email=(String)session.getAttribute("loggedInUser");
	if(email==null){
		ErrorClazz errorClazz=new ErrorClazz(5,"Unauthorized access.. please login..");
		return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
	}
	List<Notification> notifications=notificationDAO.getNotificationNotViewed(email);
	return new ResponseEntity<List<Notification>>(notifications,HttpStatus.OK);
}
	@RequestMapping(value="/notification/{id}",method=RequestMethod.GET)
	public ResponseEntity<?> getNotification(@PathVariable int id,HttpSession session){
		String email=(String)session.getAttribute("loggedInUser");
		if(email==null){
			ErrorClazz errorClazz=new ErrorClazz(5,"Unauthorized access.. please login..");
			return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
		}
		Notification notification=notificationDAO.getNotification(id);
		return new ResponseEntity<Notification>(notification,HttpStatus.OK);
	}
	
	@RequestMapping(value="/updatenotification/{id}",method=RequestMethod.PUT)
	public ResponseEntity<?> updatenotification(@PathVariable int id,HttpSession session){
		String email=(String)session.getAttribute("loggedInUser");
		if(email==null){
			ErrorClazz errorClazz=new ErrorClazz(5,"Unauthorized access.. please login..");
			return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
		}
		notificationDAO.updateNotification(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
} 

}