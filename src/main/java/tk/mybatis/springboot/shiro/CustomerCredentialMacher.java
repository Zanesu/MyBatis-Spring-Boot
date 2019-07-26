package tk.mybatis.springboot.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;

public class CustomerCredentialMacher extends HashedCredentialsMatcher {

	@Override
	public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
		// TODO Auto-generated method stub
		UsernamePasswordToken aToken = (UsernamePasswordToken) token;
		if (new String(aToken.getPassword()).toString().trim().equals(info.getCredentials().toString().trim())) {
			return true;
		}
		return false;
	}

}
