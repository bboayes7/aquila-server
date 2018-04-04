package edu.csula.aquila.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.csula.aquila.daos.UserDao;
import edu.csula.aquila.model.User;
import edu.csula.aquila.model.User.Type;


@RestController
public class UserController {

    @Autowired
    private UserDao userDao;
    

	BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();

    //Get a list of all users
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<User> getUsers()
    {
        return userDao.getUsers();
    }
    
    //get a user
    @RequestMapping(value ="/user/{id}", method = RequestMethod.GET)
    public User getUser(@PathVariable Long id)
    {
    	return userDao.getUser(id); 
    }
    
    //create a user
    //implementing hashing for passwords now
    @RequestMapping(value = "/user/", method = RequestMethod.POST)
    public User saveUser(@RequestBody User user, @ModelAttribute("currentUser") User currentUser, @RequestHeader("Authorization") String token) 
    {
    	user.setType(Type.INVESTIGATOR);

    	System.out.println(currentUser);
    	//hash the password
    	user.setHash(bcrypt.encode(user.getPassword()));
    	
    	return userDao.saveUser(user);
    }
    
    //update a user
    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    public User editUser(@RequestBody User user, @PathVariable Long id)
    {
    	user.setId(id);
    	return userDao.editUser(user);
    			
    }

}