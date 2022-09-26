package com.ssafy.gumid207.user;

import java.io.UnsupportedEncodingException;
import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class MailSendService {
	@Autowired
	private JavaMailSenderImpl mailSender;
	private int authNumber; 
	private String newPass;
	// 난수 발생(여러분들 맘대러)
	
		public void makeRandomNumber() {
			// 난수의 범위 111111 ~ 999999 (6자리 난수)
			Random r = new Random();
			int checkNum = r.nextInt(888888) + 111111;
			log.info("인증번호 : " + checkNum);
			authNumber = checkNum;
		}
		
		public void makeNewPass() {
			StringBuilder pass = new StringBuilder();
			Random r = new Random();
			char[] alpha = new char[] {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
			for(int i = 0; i <5; i++) {
				int index = r.nextInt(26);
				int ran = r.nextInt(10);
				pass.append(alpha[index]);
				pass.append(ran);
			}
			newPass = pass.toString();
			
		}
		
		
		//이메일 보낼 양식! 
		public String joinEmail(String email) throws UnsupportedEncodingException {
			makeRandomNumber();
			String setFrom = "jongui7303@gmail.com"; // email-config에 설정한 자신의 이메일 주소를 입력 
			String toMail = email;
			String title = "(SONG FOR YOU)회원 가입 인증 이메일 입니다."; // 이메일 제목 
			String content = 
					"song for you를 방문해주셔서 감사합니다." + 	//html 형식으로 작성 ! 
	                "<br><br>" + 
				    "인증 번호는 " + authNumber + "입니다." + 
				    "<br>" + 
				    "해당 인증번호를 인증번호 확인란에 기입하여 주세요."; //이메일 내용 삽입
			mailSend(setFrom, toMail, title, content);
			return Integer.toString(authNumber);
		}
		
		//비밀번호 보낼 양식
		public String passEmail(String email) throws UnsupportedEncodingException {
			makeNewPass();
			String setFrom = "jongui7303@gmail.com"; // email-config에 설정한 자신의 이메일 주소를 입력 
			String toMail = email;
			String title = "(SONG FOR YOU)새 비밀번호 전송 이메일 입니다."; // 이메일 제목 
			String content = 
					"song for you를 방문해주셔서 감사합니다." + 	//html 형식으로 작성 ! 
	                "<br><br>" + 
				    "새 비밀번호는 " + newPass + "입니다." + 
				    "<br>" + 
				    "새로운 비밀번호로 로그인해 주세요"; //이메일 내용 삽입
			mailSend(setFrom, toMail, title, content);
			return newPass;
		}
		
		//이메일 전송 메소드
		public void mailSend(String setFrom, String toMail, String title, String content) throws UnsupportedEncodingException { 
			MimeMessage message = mailSender.createMimeMessage();
			// true 매개값을 전달하면 multipart 형식의 메세지 전달이 가능.문자 인코딩 설정도 가능하다.
			try {
				MimeMessageHelper helper = new MimeMessageHelper(message,true,"utf-8");
				helper.setFrom(new InternetAddress(setFrom, "SONG FOR YOU"));
				helper.setTo(new InternetAddress(toMail, "songforyou 회원"));
				helper.setSubject(title);
				System.setProperty("https.protocols", "TLSv1.2");
				// true 전달 > html 형식으로 전송 , 작성하지 않으면 단순 텍스트로 전달.
				helper.setText(content,true);
				mailSender.send(message);
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		}
		
	
}
