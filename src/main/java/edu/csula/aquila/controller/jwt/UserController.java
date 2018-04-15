package edu.csula.aquila.controller.jwt;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.csula.aquila.daos.UserDao;
import edu.csula.aquila.error.RestException;
import edu.csula.aquila.model.User;
import edu.csula.aquila.model.User.Type;


@RestController
public class UserController {

    @Autowired
    private UserDao userDao;
    

	BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();

    //Get a list of all users
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<User> getUsers(@ModelAttribute("currentUser") User currentUser)
    {
    	if(currentUser.getType() != Type.SYSADMIN)
    		throw new RestException(401, "UNAUTHORIZED");
    	
    	return userDao.getUsers();    		
    }
    
    //get a user
    @RequestMapping(value ="/user/{userId}", method = RequestMethod.GET)
    public User getUser(@PathVariable Long userId, @ModelAttribute("currentUser") User currentUser)
    {

    	System.out.println("CURRENT USER INFO:");
    	System.out.println("username : " + currentUser.getUsername());
    	System.out.println("first : " + currentUser.getFirstName());
    	System.out.println("last : " + currentUser.getLastName());
    	System.out.println("Type : " + currentUser.getType());
    	System.out.println("ID : " + currentUser.getId());
    	
    	System.out.println("Path Variable ID : " + userId);
    	switch(currentUser.getType()) {
	    	case INVESTIGATOR : {
	    		if(currentUser.getId().equals(userId)) {
	    			return userDao.getUser(userId);
	    		} else {
	    			throw new RestException(401, "UNAUTHORIZED");
	    		}
	    	}
	    	
	    	case UAS_ANALYST : {
	    		User getUser = userDao.getUser(userId);
	    		if(getUser.getType() == Type.SYSADMIN) {
	    			throw new RestException(401, "UNAUTHORIZED");
	    		}
	    		return getUser;
	    	}
	    	
	    	//Sysadmin
	    	default: return userDao.getUser(userId);
    	}
    	
    }
    
    //create a user
    @RequestMapping(value = "/user/", method = RequestMethod.POST)
    public User saveUser(@RequestBody User user, @ModelAttribute("currentUser") User currentUser) 
    {
    	if(currentUser.getType() != Type.SYSADMIN)
    		throw new RestException(401, "UNAUTHORIZED");
    	
    	if(user.getType() == Type.INVESTIGATOR)
    		throw new RestException(403, "FORBIDDEN");
    	
    	//hash the password
    	user.setHash(bcrypt.encode(user.getPassword()));
    	
    	return userDao.saveUser(user);
    }
    
    //update a user
    @RequestMapping(value = "/user/{userId}", method = RequestMethod.PUT)
    public User editUser(@RequestBody User user, @PathVariable Long userId, @ModelAttribute("currentUser") User currentUser)
    {
    	user.setId(userId);
    	
    	String hash = userDao.getUser(userId).getHash();
    	user.setHash(hash);
    	
    	switch(currentUser.getType()) {
    	
	    	case INVESTIGATOR : {
	    		if(currentUser.getId().equals(userId)) {
	    			return userDao.editUser(user);
	    		} else {
	    			throw new RestException(401, "UNAUTHORIZED");
	    		}
	    	}
	    	
	    	case UAS_ANALYST : {
	    		if(currentUser.getId().equals(userId)) {
	    			return userDao.editUser(user);
	    		} else {
	    			throw new RestException(401, "UNAUTHORIZED");
	    		}
	    	}
	    	
	    	default: return userDao.editUser(user);
    	}
    			
    }

}