package tk.mybatis.springboot.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.SubjectContext;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.subject.support.WebDelegatingSubject;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;

import tk.mybatis.springboot.service.RedisService;

public class StatelessSecurityManager extends DefaultWebSecurityManager {
	@Autowired
	private RedisService redisService;

	@Override
	public Subject createSubject(SubjectContext subjectContext) {
		SessionKey sessionKey = getSessionKey(subjectContext);
		if (WebUtils.isHttp(sessionKey)) {
			HttpServletRequest request = (HttpServletRequest) WebUtils.getRequest(sessionKey);
			ServletResponse response = WebUtils.getResponse(sessionKey);
			String token = request.getHeader("token");
			if (token != null) {
				try {
					StatelessSubject subjectCache = (StatelessSubject) redisService.get(token);
					if (subjectCache != null) {
						Subject subject = super.createSubject(subjectContext);
						WebDelegatingSubject subject2 = (WebDelegatingSubject) subject;
						return new WebDelegatingSubject(subjectCache.getPrincipals(), subjectCache.isAuthenticated(),
								subject2.getHost(), subject2.getSession(), request, response,
								subject2.getSecurityManager());
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return super.createSubject(subjectContext);
	}

}
