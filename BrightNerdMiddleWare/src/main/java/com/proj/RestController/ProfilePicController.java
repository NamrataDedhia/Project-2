package com.proj.RestController;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.proj.dao.ProfilePicDAO;
import com.proj.models.ErrorClazz;
import com.proj.models.ProfilePic;

@Controller
public class ProfilePicController {

	@Autowired
	private ProfilePicDAO profilePicDAO;
	
	public ProfilePicController() {
		System.out.println("Profilepic Controller bean created");
	}
	
	@RequestMapping(value="/uploadprofilepicture",method=RequestMethod.POST)
	public ResponseEntity<?> uploadProfilePicture(@RequestParam CommonsMultipartFile image,HttpSession session){
		String email=(String)session.getAttribute("loggedInUser");
		if(email==null){
			ErrorClazz errorClazz=new ErrorClazz(5,"Unauthorized access.. please login..");
			return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
		}
		ProfilePic profilePic=new ProfilePic();
		profilePic.setEmail(email);
		profilePic.setImage(image.getBytes());
		profilePicDAO.uploadProfilePicture(profilePic);
		return new ResponseEntity<ProfilePic>(profilePic,HttpStatus.OK);
	}
    @RequestMapping(value="/getimage",method=RequestMethod.GET)
	public @ResponseBody byte[] getProfilePicture(@RequestParam String email,HttpSession session){
    	System.out.println("email is " + email);
		String authEmail=(String)session.getAttribute("loggedInUser");
		if(authEmail==null){
			return null;
		}
		ProfilePic profilePic=profilePicDAO.getProfilePicture(email);
		if(profilePic==null)//No image
			return null;
		else
			return profilePic.getImage();
	}
}


