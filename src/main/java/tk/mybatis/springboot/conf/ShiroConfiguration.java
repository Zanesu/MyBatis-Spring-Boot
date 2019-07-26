package tk.mybatis.springboot.conf;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import tk.mybatis.springboot.shiro.CustomShiroAuthcFilter;
import tk.mybatis.springboot.shiro.CustomerCredentialMacher;
import tk.mybatis.springboot.shiro.StatelessSecurityManager;
import tk.mybatis.springboot.shiro.SystemRealm;

import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;

/**
 * shiro的配置类
 * 
 * @author Administrator
 *
 */
@Configuration
public class ShiroConfiguration {
	@Bean(name = "shiroFilter")
	public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager") SecurityManager manager) {
		ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
		bean.setSecurityManager(manager);
		// 自定义拦截器
		Map<String, Filter> filtersMap = new LinkedHashMap<String, Filter>();
		filtersMap.put("customerfilter", new CustomShiroAuthcFilter());
		bean.setFilters(filtersMap);
		bean.setLoginUrl("/login/**");
		// 配置访问权限
		LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
		filterChainDefinitionMap.put("/test/**", "anon"); // 表示可以匿名访问
		filterChainDefinitionMap.put("/**", "customerfilter");
		/*
		 * filterChainDefinitionMap.put("/loginUser", "anon");
		 * filterChainDefinitionMap.put("/logout*", "anon");
		 * filterChainDefinitionMap.put("/jsp/error.jsp*", "anon");
		 * filterChainDefinitionMap.put("/jsp/index.jsp*", "authc");
		 * filterChainDefinitionMap.put("/*", "authc");// 表示需要认证才可以访问
		 * filterChainDefinitionMap.put("/*.*", "authc");
		 */
		bean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return bean;
	}

	// 配置自定义的密码比较器
	@Bean
	public CredentialsMatcher credentialsMatcher() {
		// return new HashedCredentialsMatcher();
		return new CustomerCredentialMacher();
	}

	// 配置核心安全事务管理器
	@Bean(name = "securityManager")
	public SecurityManager securityManager(@Qualifier("authRealm") SystemRealm authRealm) {
		System.err.println("--------------shiro已经加载----------------");
		DefaultWebSecurityManager manager = new StatelessSecurityManager();
		manager.setRealm(authRealm);
		return manager;
	}

	// 配置自定义的权限登录器
	@Bean(name = "authRealm")
	public SystemRealm authRealm(@Qualifier("credentialsMatcher") CredentialsMatcher matcher) {
		SystemRealm authRealm = new SystemRealm();
		authRealm.setCredentialsMatcher(matcher);
		return authRealm;
	}

	@Bean
	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}

	@Bean
	public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
		creator.setProxyTargetClass(true);
		return creator;
	}

	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(
			@Qualifier("securityManager") SecurityManager manager) {
		AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
		advisor.setSecurityManager(manager);
		return advisor;
	}
}