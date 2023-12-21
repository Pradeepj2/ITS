package com.ITS.ITS.Services;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.ITS.ITS.Entity.Users;


public interface UsersServices {
	public List<Users> getAllUsers();
	public Users findById(int id);
	public void add(Users user);
	public void updateUser(Users user);

	public void delete(int id);
	public void allUserDelete();
	public String uploadImg(String pathString , MultipartFile file);
	public InputStream getResources(String path , String filename) throws FileNotFoundException;
//	public sendEmail(EmailContent e);
//	public String isValidUser(String email, String password);
//	public int loginByEmail(String email, String password_hash);
	public Users updateUserByfield(int id,Map<String , Object> mp);
	
	public int getCount(String email);
	public void setUniqueCode(int i, String emailString);
} 
