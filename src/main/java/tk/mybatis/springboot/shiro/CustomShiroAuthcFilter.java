package tk.mybatis.springboot.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import tk.mybatis.springboot.util.ShiroUtil;

public class CustomShiroAuthcFilter extends FormAuthenticationFilter {

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response, Object mappedValue)
			throws Exception {
		// TODO Auto-generated method stub
		if (isLoginRequest(request, response)) {
			return true;
		} else {
			if (ShiroUtil.getShiroUser() == null) {
				HttpServletResponse rep = (HttpServletResponse) response;
				rep.setStatus(202);
				return false;
			}
		}
		return super.onAccessDenied(request, response, mappedValue);
	}

}
