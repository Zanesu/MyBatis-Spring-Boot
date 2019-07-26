package tk.mybatis.springboot.util;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;

import tk.mybatis.springboot.shiro.ShiroUser;

public class ShiroUtil {

	public static ShiroUser getShiroUser() {
		Subject subject = getSubject();
		ShiroUser shiroUser = (ShiroUser) subject.getPrincipal();
		return shiroUser;
	}

	private static Subject getSubject() {
		// TODO Auto-generated method stub
		return SecurityUtils.getSubject();
	}

	public static String getToken(HttpServletRequest request) {
		String token = request.getHeader("token");
		if (token == null) {
			token = request.getSession().getId();
		}
		return token;
	}

	public static String getUuid() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString();

	}

}
