package com.qingdy.shiro;

import java.util.Collection;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

import com.qingdy.dao.UserDao;
import com.qingdy.model.User;
import com.qingdy.service.FacadeManager;

public class QingDyShiro extends AuthorizingRealm {
	@Resource(name = "facadeManager")
	private FacadeManager facadeManager;

	public static final void setLogin(String userId, String password) {
		Subject currentUser = SecurityUtils.getSubject();
		System.out.println("**************" + currentUser.isAuthenticated());
		if (!currentUser.isAuthenticated()) {
			// collect user principals and credentials in a gui specific manner
			// such as username/password html form, X509 certificate, OpenID,
			// etc.
			// We'll use the username/password example here since it is the most
			// common.
			// (do you know what movie this is from? ;)
			UsernamePasswordToken token = new UsernamePasswordToken(userId,
					password);
			// this is all you have to do to support 'remember me' (no config -
			// built in!):
			token.setRememberMe(true);
			currentUser.login(token);
		}
	};

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken  authToken) {
		UsernamePasswordToken token = (UsernamePasswordToken) authToken;
		User user = facadeManager.getUser(token.getUsername());
		if (user != null) {
			return new SimpleAuthenticationInfo(
					user.getUsername(), user.getUsername(), user.getPassword());
		} else {
			return null;
		}
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) throws AuthenticationException {
		String loginName = (String) (principals).fromRealm(getName()).iterator().next();
		User user = facadeManager.getUser(loginName);
		if (user != null) {
			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
			info.addStringPermission("common-user");
			return info;
		} else {
			return null;
		}
	}
}
