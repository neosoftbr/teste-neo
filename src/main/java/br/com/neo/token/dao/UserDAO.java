package br.com.neo.token.dao;

import br.com.neo.token.domain.User;

public interface UserDAO {
	
	User getUserById(Integer id);

	User getUserByName(String name);

	boolean createUser(User user);

}