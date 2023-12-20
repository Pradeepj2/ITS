package com.ITS.ITS.Controller;


import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.modelmapper.internal.bytebuddy.asm.Advice.This;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StreamUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ITS.ITS.Entity.Users;
import com.ITS.ITS.Response.GeneralMessage;
import com.ITS.ITS.Response.ServiceResponse;
import com.ITS.ITS.Services.UsersServices;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/home")
public class MainController {

	@Autowired
	private UsersServices usersServices;

	@GetMapping("/allUsers")
	public ServiceResponse<?> getallUseres() {
		try {
	  
//			return new ServiceResponse<>(new GeneralMessage<>("Data loaded",1,200),HttpStatus.OK);
			return new ServiceResponse<>(new GeneralMessage<>("Data loaded", usersServices.getAllUsers(), 1, 200),
					HttpStatus.OK);

		} catch (Exception e) {
			// TODO: handle exception
			return new ServiceResponse<>(new GeneralMessage<>("Data not found", 0, 404), HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/getuser/{id}")
	public ServiceResponse<?> getSingleUser(@PathVariable("id") String id) {
		try {
			Users data = usersServices.findById(Integer.parseInt(id));
			return new ServiceResponse<>(new GeneralMessage<>("Data found ", data, 1, 200), HttpStatus.OK);
//			return new ServiceResponse<>(new GeneralMessage<>("Data found ",1,200),HttpStatus.OK);
		} catch (Exception e) {
			return new ServiceResponse<>(new GeneralMessage<>("Data not found by service", 0, 404),
					HttpStatus.NOT_FOUND);
		}
	}

//	@GetMapping("/getuser/{id}")
//	public GeneralMessage<?> getSingleCourse(@PathVariable("id") String id) {
//		try {
//			Users data = usersServices.getSingleUser(Integer.parseInt(id));
//			return new GeneralMessage<>("Data found ",data,1,200);
//		} catch (Exception e) {
//			return new GeneralMessage<>("Data not found by service",0,404);
//		}
//	}

	@PostMapping("/addUser")
	public ServiceResponse<?> addUsers(@RequestBody Users users) {
		try {
			usersServices.add(users);
			return new ServiceResponse<>(new GeneralMessage<>("User added ", 1, 200), HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ServiceResponse<>(new GeneralMessage<>("something went wrong ", 1, 500),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/update/{id}")
	public ServiceResponse<?> updateUser(@PathVariable("id") int id ,@RequestParam("email") String email) {

		try {
			
			Users users = usersServices.findById(id);
			users.setEmail(email);
			usersServices.updateUser(users);
			return new ServiceResponse<>(new GeneralMessage<>("user Updated", 1, 200), HttpStatus.OK);
		} catch (Exception e) {
			return new ServiceResponse<>(new GeneralMessage<>("Error", 1, 500), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/delete/{id}")
	public ServiceResponse<?> delete(@PathVariable("id") String id) {
		try {
			this.usersServices.delete(Integer.parseInt(id));
			return new ServiceResponse<>(new GeneralMessage<>("user deleted", 1, 200), HttpStatus.OK);
		} catch (Exception e) {
			return new ServiceResponse<>(new GeneralMessage<>("User not found", 1, 404),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/allUsersdelete")
	public ServiceResponse<?> deleteAllUsers() {
		try {
			usersServices.allUserDelete();
			return new ServiceResponse<>(new GeneralMessage<>("All users deleted", 1, 200), HttpStatus.OK);
		} catch (Exception e) {
			return new ServiceResponse<>(new GeneralMessage<>("User not found", 1, 404),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@PutMapping("/updateUserByfield/{id}")
	public ServiceResponse<?> updateUserByField(@PathVariable("id") int id ,@RequestBody Map<String, Object> mp){
	  	
	   try {
			return new ServiceResponse<>(new GeneralMessage<>("User Updated", this.usersServices.updateUserByfield(id,mp), 1, 200), HttpStatus.OK);
		} catch (Exception e) {
			return new ServiceResponse<>(new GeneralMessage<>("User not found", 1, 404),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Value("${project.imageFolder}")
	private String path;
	
	@PostMapping("/imageUpload")
	public ServiceResponse<?> uploadImg(@RequestParam("image") MultipartFile img) {
		
		   try {
				return new ServiceResponse<>(new GeneralMessage<>("Image Uploaded", this.usersServices.uploadImg(path,img), 1, 200), HttpStatus.OK);
			} catch (Exception e) {
				return new ServiceResponse<>(new GeneralMessage<>("Something went wrong", 1, 404),HttpStatus.INTERNAL_SERVER_ERROR);
			}
	}
	
	
	@GetMapping("/getImage/{imgName}" )
	public ServiceResponse<?> downloadImage(@PathVariable("imgName") String imgName , HttpServletResponse response){
		   try {
			   
			   InputStream resources = this.usersServices.getResources(path,imgName);
			   response.setContentType(MediaType.IMAGE_JPEG_VALUE);
			   
//			   downloadImagebythiscode
//			   response.setHeader("Content-Disposition",
//		                "attachment;filename="+"first");
			   
//			   Showmagebythiscode
			   response.setHeader("Content-Disposition","");
			   StreamUtils.copy(resources, response.getOutputStream());
			  
				return new ServiceResponse<>(new GeneralMessage<>("Image download", 1, 200), HttpStatus.OK);
			} catch (Exception e) {
				return new ServiceResponse<>(new GeneralMessage<>("Image not found", 1, 404),
						HttpStatus.INTERNAL_SERVER_ERROR);
			}
	}
	
	
//	@PostMapping("/sendMail")
//	public boolean sendMail(@ResponseBody ) {
//		return true;
//	}
	


//	@PostMapping("/login")
//	public ServiceResponse<?> loginByEmail(@RequestBody Users user) {
//		int loginByEmail = usersServices.loginByEmail(user.getEmail(), user.getPassword_hash());
//		if (loginByEmail > 0)
//			return new ServiceResponse<>(new GeneralMessage<>("You are successfully logged in", 1, 200), HttpStatus.OK);
//		else
//			return new ServiceResponse<>(new GeneralMessage<>("Invalid Crediential", 0, 409),
//					HttpStatus.CONFLICT);
//	}
}
