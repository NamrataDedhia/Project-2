package com.proj.RestController;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.proj.dao.FriendDAO;
import com.proj.dao.UserDAO;
import com.proj.models.ErrorClazz;
import com.proj.models.Friend;
import com.proj.models.User;

@Controller
public class FriendController {

	@Autowired
	private FriendDAO friendDAO;
		@Autowired
	private UserDAO userDao;
		
		public FriendController()
		{
			System.out.println("FriendController Bean Created");
		}
		@RequestMapping(value="/suggestedusers",method=RequestMethod.GET)
		public ResponseEntity<?> getAllSuggestedUsers(HttpSession session){
			String email=(String)session.getAttribute("loggedInUser");
			if(email==null){
				ErrorClazz errorClazz=new ErrorClazz(5,"Unauthorized access.. please login..");
				return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
			}
			List<User> suggestedUsers=friendDAO.getSuggestedUsers(email);
			return new ResponseEntity<List<User>>(suggestedUsers,HttpStatus.OK);
		}
		//create new friend object [id,toId,fromId,status]
		@RequestMapping(value="/friendrequest",method=RequestMethod.POST)
		public ResponseEntity<?> addFriendRequest(@RequestBody User friendRequestToId,HttpSession session){
			String email=(String)session.getAttribute("loggedInUser");
			if(email==null){
				ErrorClazz errorClazz=new ErrorClazz(5,"Unauthorized access.. please login..");
				return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
			}
			User fromId=userDao.getUser(email);
			Friend friend=new Friend();
			friend.setFromId(fromId);
			friend.setToId(friendRequestToId);
			friend.setStatus('P');
			friendDAO.addFriendRequest(friend);
			return new ResponseEntity<Friend>(friend,HttpStatus.OK);
		}
		@RequestMapping(value="/pendingrequests",method=RequestMethod.GET)
		public ResponseEntity<?> getPendingRequests(HttpSession session){
			String email=(String)session.getAttribute("loggedInUser");
			if(email==null){
				ErrorClazz errorClazz=new ErrorClazz(5,"Unauthorized access.. please login..");
				return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
			}
			List<Friend> pendingRequests=friendDAO.getPendingRequests(email);
			return new ResponseEntity<List<Friend>>(pendingRequests,HttpStatus.OK);
		}
		@RequestMapping(value="/acceptrequest",method=RequestMethod.PUT)
		public ResponseEntity<?> acceptFriendRequest(@RequestBody Friend friend,HttpSession session){
			System.out.println("Friend ID is "+friend.getId());
			String email=(String)session.getAttribute("loggedInUser");
			if(email==null){
				ErrorClazz errorClazz=new ErrorClazz(5,"Unauthorized access.. please login..");
				return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
			}
			friend.setStatus('A');
			friendDAO.acceptFriendRequest(friend);
			return new ResponseEntity<Friend>(friend,HttpStatus.OK);
		}
		@RequestMapping(value="/deleterequest",method=RequestMethod.PUT)
		public ResponseEntity<?> deleteFriendRequest(@RequestBody Friend friend,HttpSession session){
			System.out.println("Friend ID is "+friend.getId());
			String email=(String)session.getAttribute("loggedInUser");
			if(email==null){
				ErrorClazz errorClazz=new ErrorClazz(5,"Unauthorized access.. please login..");
				return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
			}
			friendDAO.deleteFriendRequest(friend);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		@RequestMapping(value="/listoffriends",method=RequestMethod.GET)
		public ResponseEntity<?> listOfFriends(HttpSession session){
			String email=(String)session.getAttribute("loggedInUser");
			if(email==null){
				ErrorClazz errorClazz=new ErrorClazz(5,"Unauthorized access.. please login..");
				return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
			}
			List<User> friendsDetails=friendDAO.listOfFriends(email);
			return new ResponseEntity<List<User>>(friendsDetails,HttpStatus.OK);
		}
	}



