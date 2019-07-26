package tk.mybatis.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.minidev.json.JSONObject;
import tk.mybatis.springboot.service.RedisService;

@RestController
@RequestMapping("/login")
public class LoginController {
	@Autowired
	private RedisService redisService;

	@RequestMapping("/login")
	public JSONObject login() {
		System.out.println("======================执行了=======================");
		JSONObject json = new JSONObject();
		redisService.set("test", "trterre");
		return json;
	}

	@RequestMapping("/logininit")
	public JSONObject logininit() {
		JSONObject json = new JSONObject();
		Object object = redisService.get("test");
		json.put("name", object);
		return json;
	}
}
