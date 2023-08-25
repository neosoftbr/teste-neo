package br.com.neo.token.dao;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import br.com.neo.token.domain.User;
import br.com.neo.token.domain.UserMapper;

@Component
public class UserDAOImpl implements UserDAO {
	
	JdbcTemplate jdbcTemplate;

	private final String SQL_FIND_USER = "select * from user where name = ?";
	private final String SQL_FIND_ID = "select * from user where id = ?";
	private final String SQL_INSERT_USER = "insert into user(id, name) values(?,?)";

	@Autowired
	public UserDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public User getUserById(Integer id) {
		return jdbcTemplate.queryForObject(SQL_FIND_ID, new Object[] { id }, new UserMapper());
	}
	
	@Override
	public User getUserByName(String name) {
		return jdbcTemplate.queryForObject(SQL_FIND_USER, new Object[] { name }, new UserMapper());
	}



	@Override
	public boolean createUser(User user) {
		return jdbcTemplate.update(SQL_INSERT_USER, user.getId(), user.getName()) > 0;
	}

}
