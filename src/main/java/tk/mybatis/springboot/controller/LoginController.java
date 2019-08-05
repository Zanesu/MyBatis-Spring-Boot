package tk.mybatis.springboot.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ShearCaptcha;
import cn.hutool.core.img.ImgUtil;
import cn.hutool.core.lang.Console;
import net.minidev.json.JSONObject;
import tk.mybatis.springboot.service.RedisService;

@RestController
@RequestMapping("/login")
public class LoginController {
	@Autowired
	private RedisService redisService;

	@RequestMapping("/login")
	public JSONObject login() {
		JSONObject json = new JSONObject();
		return json;
	}

	@RequestMapping("/logininit")
	public JSONObject logininit() {
		JSONObject json = new JSONObject();
		
		Object object = redisService.get("test");
		json.put("name", object);
		return json;
	}

	@RequestMapping("/getCode")
	public void getCode(HttpServletResponse response) {
		ShearCaptcha captcha = CaptchaUtil.createShearCaptcha(200, 100, 4, 4);
		try {
			captcha.write(response.getOutputStream());
			Console.log(captcha.getCode());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
