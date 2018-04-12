package edu.csula.aquila.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;

import edu.csula.aquila.daos.UserDao;
import edu.csula.aquila.model.User;
import edu.csula.aquila.model.User.Type;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
//
//@ControllerAdvice(annotations = ModelAttribute.class)
public class UserAdvice {
	
//	@Autowired
//	UserDao userDao;
//	
//	@ModelAttribute("currentUser")
//	public User getUserFromJwt(@RequestHeader("Authorization") String token) {

//		System.out.println(token);
//		
//		//parse the token
//		Claims claims = Jwts.parser().setSigningKey("Chengyu's Gold Team").parseClaimsJws(token).getBody();
//		
//		//grab user
//		String username = claims.getSubject();
//		String firstName = (String) claims.get("first");
//		String lastName = (String) claims.get("last");
//		String typeString = (String) claims.get("type");
//		String id = (String) claims.get("id");
//		
//		System.out.println("username : " + username +
//							"firstName : " + firstName +
//							"lastName : " + lastName + 
//							"type : " + typeString +
//							"id : " + id);
//		
//		Type type = Type.valueOf(typeString);
//
//
//		User currentUser = new User();
//
//		currentUser.setUsername(username);
//		currentUser.setFirstName(firstName);
//		currentUser.setLastName(lastName);
//		currentUser.setType(type);
//		
//		return currentUser;
//	}

}