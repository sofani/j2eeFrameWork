package com.sam.dao;

import java.util.List;

import com.sam.model.Users;

public interface UserDAO {

	public void addUser(Users p);
	public void updateUser(Users p);
	public List<Users> listUsers();
	public Users getUserById(int id);
	public void removeUser(int id);
}
