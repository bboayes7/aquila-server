package edu.csula.aquila.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.csula.aquila.daos.LoginDao;
import edu.csula.aquila.daos.UserDao;
import edu.csula.aquila.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
public class LoginController {
	
	@Autowired
	private LoginDao loginDao;
	
	@Autowired
	private UserDao userDao;
	

	BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();

	String secret = "Chengyu's Gold Team";
	
	
	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Object> login(@RequestBody User user){
		
		System.out.println("hello");
		//check if user sent any fields
//		if(user.getUsername() == null && user.getPassword() == null) {
//			 throw new RestException( 400, "Missing username and/or password." );
//		}

		//get user from database, this means the username is correct
		User userDB = userDao.getUserByUsername(user.getUsername());
		
		//check if passwords are the same from the database
		if(bcrypt.matches(user.getPassword(), userDB.getHash())){
			//create jwt with username as the subject
			String compactJws = Jwts.builder()
					.setSubject(userDB.getUsername())
					.claim("id", userDB.getId())
					.claim("first", userDB.getFirstName())
					.claim("last", userDB.getLastName())
					.claim("type", userDB.getType().toString())
					.signWith(SignatureAlgorithm.HS512, secret)
					.compact();
		    HttpHeaders responseHeaders = new HttpHeaders();
		    responseHeaders.setContentType(MediaType.APPLICATION_JSON);
			return new ResponseEntity<Object>(new Jwt(compactJws, userDB), responseHeaders, HttpStatus.ACCEPTED);
		}
		
		//if passwords don't match
	    HttpHeaders responseHeaders = new HttpHeaders();
	    responseHeaders.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity<Object>("Password Failed To Match", responseHeaders, HttpStatus.UNAUTHORIZED);
	}
	
	public class Jwt {
		String token;
		User user; 
		
		public Jwt(String token, User user) {
			this.token = token;
			this.user = user;
		}
		
		public User getUser() {
			return user;
		}
		
		public void setUser(User user) {
			this.user = user;
		}
		
		public String getJwt() {
			return token;
		}
		
		public void setJwt(String token) {
			this.token = token;
		}
	}



}

