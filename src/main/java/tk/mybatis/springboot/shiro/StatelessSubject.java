package tk.mybatis.springboot.shiro;

import java.io.Serializable;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.web.subject.support.WebDelegatingSubject;

public class StatelessSubject implements Serializable {
	/**
	 * @Field @serialVersionUID : TODO(这里用一句话描述这个类的作用)
	 */
	private static final long serialVersionUID = 1L;

	protected PrincipalCollection principals;
	protected boolean authenticated;
	protected String host;
	protected Session session;
	protected transient SecurityManager securityManager;

	public StatelessSubject(WebDelegatingSubject subject) {
		this(subject.getPrincipals(), subject.isAuthenticated(), subject.getHost(), subject.getSession(),
				subject.getSecurityManager());
		// TODO Auto-generated constructor stub
	}

	public StatelessSubject(PrincipalCollection principals, boolean authenticated, String host, Session session,
			SecurityManager securityManager) {
		super();
		this.principals = principals;
		this.authenticated = authenticated;
		this.host = host;
		//this.session = session;
		this.securityManager = securityManager;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public PrincipalCollection getPrincipals() {
		return principals;
	}

	public boolean isAuthenticated() {
		return authenticated;
	}

	public String getHost() {
		return host;
	}

	public Session getSession() {
		return session;
	}

	public SecurityManager getSecurityManager() {
		return securityManager;
	}

}
