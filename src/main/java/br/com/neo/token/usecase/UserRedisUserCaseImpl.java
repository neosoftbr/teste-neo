package br.com.neo.token.usecase;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import br.com.neo.token.dao.UserDAO;
import br.com.neo.token.domain.User;
import lombok.extern.log4j.Log4j2;
import net.bytebuddy.implementation.bytecode.Throw;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;

@Log4j2
@Service
public class UserRedisUserCaseImpl implements UserRedisUserCase{


    @Value("${neo.api.redis}")
	private String redisUrl;
    
    @Autowired
    private UserDAO userDao;
    
	public User getUserId(Integer id) {
		try {
            return userDao.getUserById(id);
		} catch (final Exception e) {
	        log.error("ERROR TO [getUser]: " + e.getMessage());
	        return null;
	    }
	}

	public User getUserName(String name) {
		try {
            return userDao.getUserByName(name);
		} catch (final Exception e) {
	        log.error("ERROR TO [getUser]: " + e.getMessage());
	        return null;
	    }
	}
	
	public boolean saveUser(User user) {
		try {
            return  userDao.createUser(user);
		} catch (final Exception e) {
	        log.error("ERROR TO SAVE:: " + e.getMessage());
	        throw e;
	    }
	}
	
    public User  getAccessToken(String name) {
    	User user =  getUserName(name);
		return user;
    }
    
    public JSONObject getNameRedis(User user) {
    	JSONObject userJson = new JSONObject(user);
    	Jedis jedis = new Jedis(redisUrl);
    	String chave = new StringBuilder()
			.append(user.getName())
			.toString();
    	
			try {
    			if(jedis.get(chave) == null) {
    				User us  = getUserName(user.getName());
    				if(us == null) {
    					 saveUser(user);
    				}
					addToken(userJson, jedis, chave);
					userJson.put("forma-inclusao", "Banco de dados");
    			} else {
    				userJson = new JSONObject(jedis.get(chave).toString());
    				userJson.put("forma-inclusao", "Redis");
    			}
		} catch (JedisConnectionException e) {
			userJson.put("erro", e.getMessage());
			log.error("Erro na conexao com o Redis:::"+ redisUrl +":::\""+ e.getMessage());
		} catch (Exception e) {
			userJson.put("erro", e.getMessage());
			log.error("Erro ao acessar o Redis:::"+ redisUrl +":::"+ e.getMessage());
		}
    	return userJson;
    }

	private void addToken(JSONObject token, Jedis jedis, String chave) {
		JSONObject tempJson = token;
		tempJson.put("age", token.getInt("age"));
		tempJson.put("name", token.getString("name"));
		jedis.append(chave, tempJson.toString());
	}


}
