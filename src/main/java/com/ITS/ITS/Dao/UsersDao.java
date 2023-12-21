package com.ITS.ITS.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ITS.ITS.Entity.Users;

import jakarta.transaction.Transactional;

public interface UsersDao extends JpaRepository<Users, Integer> {

	@Query(value = "SELECT EXISTS (SELECT * FROM user_auth WHERE email = :email and password_hash = :password)", nativeQuery = true)
	int findByEmailAndPassword(String email, String password);

	@Query(value = "SELECT * FROM user_auth WHERE email = :email", nativeQuery = true)
	Users findByEmail(String email);

	@Query(value = "SELECT unique_code FROM user_auth WHERE email = :email", nativeQuery = true)
	int getCount(String email);

	@Modifying
	@Transactional
	@Query(value = "UPDATE user_auth SET unique_code = :incrementCount WHERE email = :email", nativeQuery = true)
	void setUniqueCode(int incrementCount, String email);

}
