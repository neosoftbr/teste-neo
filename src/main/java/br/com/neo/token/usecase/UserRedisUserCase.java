package br.com.neo.token.usecase;

import org.json.JSONObject;
import br.com.neo.token.domain.User;

public interface UserRedisUserCase {
    public User getUserId(Integer id);
    public User getUserName(String name);
    public boolean saveUser(User user) ;
    public JSONObject getNameRedis(User user);
}
	