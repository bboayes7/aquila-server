//package edu.csula.aquila.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.MailSender;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//@Controller
//public class EmailController {
//
//
//	@Autowired
//	MailSender mailSender;
//	
//	@Autowired
//	JavaMailSender javaMailSender;
//	
//	@RequestMapping(value ="/email/", method=RequestMethod.POST)
//	@ResponseBody
//	public EmailResponse email(@RequestParam String from,@RequestParam String to,@RequestParam String subject,@RequestParam String content) 
//		{
//		
//			System.out.println("im in the post method at least");
//			System.out.println(from);
//			System.out.println(subject);
//			System.out.println(to);
//			System.out.println(content);
//			SimpleMailMessage msg = new SimpleMailMessage();
//			msg.setFrom( from );
//			msg.setTo( to );
//			msg.setSubject( subject );
//			msg.setText(content);
//			mailSender.send(msg);
////			javaMailSender.send(msg);
//
//			
//
//			return new EmailResponse("Email Sent!"); 
//		}
//	
//	public class EmailResponse {
//		private String message;
//
//		public EmailResponse(String message) {
//			this.message = message;
//		}
//
//		public String getMessage() {
//			return message;
//		}
//
//		public void setMessage(String message) {
//			this.message = message;
//		}
//
//	}
//
//}
