package edu.csula.aquila.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.csula.aquila.model.User;
import edu.csula.aquila.model.UserDao;

@RestController
public class UserController {

    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "/api/users", method = RequestMethod.GET)
    public List<User> getUsers()
    {
        return userDao.getUsers();
    }
    
    @RequestMapping(value ="/api/user1", method = RequestMethod.GET)
    public User getUser(@RequestParam("id") Long id)
    {
    	return userDao.getUser(id); 
    }
    
    @RequestMapping(value = "/saveuser", method = RequestMethod.POST)
    public User saveUser(@RequestBody User user) {
    	System.out.println("username: " + user.getUsername() + 
    			"\nfirstname: " + user.getFirstName() +
    			"\nlastname: " + user.getLastName() +
    			"\nemail: " + user.getEmail() +
    			"\npassword: " + user.getPassword() +
    			"\nhash: " + user.getHash());
    	return userDao.saveUser(user);
    }
    
    @RequestMapping(value = "/edituser", method = RequestMethod.PUT)
    public User editUser(@RequestBody User user)
    {
    	return userDao.editUser(user);
    			
    }

    
}