package com.ITS.ITS.ServiceImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.multipart.MultipartFile;

import com.ITS.ITS.Dao.UsersDao;
import com.ITS.ITS.Entity.Users;
 
import com.ITS.ITS.Services.UsersServices;

@Service
public class UserServiceImpl implements UsersServices {

	@Autowired
	UsersDao usersDao;

	@Override
	public List<Users> getAllUsers() {
		return usersDao.findAll();
	}

	@Override
	public void add(Users c) {
		c.setCreatedOn(new Date());
		usersDao.save(c);
	}

	@Override
	public Users findById(int id) {
		return usersDao.findById(id).get();
	}

	@Override
	public void delete(int id) {
		usersDao.deleteById(id);
	}

	@Override
	public void updateUser(Users user) {
		usersDao.save(user);

	}

	@Override
	public void allUserDelete() {
		usersDao.deleteAll();
	}

	@Override
	public Users updateUserByfield(int id, Map<String, Object> mp) {
		Optional<Users> user = usersDao.findById(id);

		if (user.isPresent()) {
			mp.forEach((key, val) -> {
				Field findField = ReflectionUtils.findField(Users.class, key);
				findField.setAccessible(true);
				ReflectionUtils.setField(findField, user.get(), val);

			});

			return this.usersDao.save(user.get());
		}

		return null;

	}

	@Override
	public String uploadImg(String path, MultipartFile file) {
		//filename
		
		String filename = file.getOriginalFilename();
		
		//full 
		
		String filePath = path + File.separator + filename;
		
		
		//create folder if its not created
		
		File file2 = new File(path);
		
		if(!file2.exists()) {
			file2.mkdir();
		}
		
		try {
			Files.copy(file.getInputStream(), Paths.get(filePath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return filename;
		
		//file copy
	}

	@Override
	public InputStream getResources(String path, String filename) throws FileNotFoundException {
		String fullFilePath = path + File.separator + filename;
		
		InputStream iStream = new FileInputStream(fullFilePath);
 		return iStream;
	}
//
//	@Override
//	public void sendEmail(EmailContent e) {
//	   
//		//variable for gmail host
//		String host = "smtp.gmail.com"; 
//		
//		//set system properties
//		
//		Properties properties = System.getProperties();
//		
//		System.out.println(properties);
//	 //setting important info to properties object
//		properties.put("mail.smtp.host", host);
//		properties.put("mail.smtp.port", "465");
//		properties.put("mail.smtp.ssl.enable", "true");
//		properties.put("mail.smtp.auth", "true");
//		
//		//get session instance
//		
//	    Session session = Session.getInstance(properties, new Authenticator() {
//	    
//	    	protected PasswordAuthentication getparPasswordAuthentication() {
//	    		return new PasswordAuthentication("pradeeprao31110@gmail.com", "@@Kst123@@");
//	    	}
//		});
//	    
//	    session.setDebug(true);
//	    
//	    //Compose the message
//	    
//	    MimeMessage message = new MimeMessage(session);
//	    
//	    //from
//	    message.addFrom("form");
//	    
//	    //recipient
//	    message.addRecipient(Message.RecipientType.TO, new InternetAddress("to"));
//	   
//	    //adding subject
//	}

	@Override
	public int getCount(String email) {
		return usersDao.getCount(email);
	}

	@Override
	public void setUniqueCode(int incrementCount, String emailString) {
		usersDao.setUniqueCode(incrementCount , emailString);
		
	}

	 

//	@Override
//	public int loginByEmail(String email, String password_hash) {
//			return usersDao.findByEmailAndPassword(email, password_hash);
//		
//	}

}
