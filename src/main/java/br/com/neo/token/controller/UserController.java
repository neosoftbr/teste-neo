package br.com.neo.token.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.neo.token.domain.User;
import br.com.neo.token.dto.UserDTO;
import br.com.neo.token.usecase.UserRedisUserCase;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/v1")
@Api(value = "Save User")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserRedisUserCase userService;

	@ApiOperation(value = "Register user.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Bad Resquest"), @ApiResponse(code = 403, message = "Not autorized"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
    @PostMapping(value = "/save/redis/user", consumes = MediaType.APPLICATION_JSON_VALUE, produces =  MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> saveUser(@RequestBody UserDTO user) {
		JSONObject json = new JSONObject();
		try {
			User userX = new User();
			userX.setAge(user.getAge());
			userX.setName(user.getName());
			json = userService.getNameRedis(userX);	
		} catch (Exception e) {
			json = new JSONObject();
			json.put("ERROR", e.getMessage());
			return new ResponseEntity<>(json,  HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(json.toString(), !json.isEmpty() ? HttpStatus.OK : HttpStatus.NO_CONTENT);
    }

}
