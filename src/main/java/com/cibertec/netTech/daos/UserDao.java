package com.cibertec.netTech.daos;

import java.util.List;

import com.cibertec.netTech.models.User;

public interface UserDao {

	public void create(User u);
	public List<User> read();
	public void update(User u);
	public void delete(Integer id);
}
