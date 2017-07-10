package org.agile.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * token
 *
 * @author zhangmm
 * @email phoenix122411@126.com
 * @date 2017-05-06
 */
public class OAuth2Token implements AuthenticationToken {
	private String token;

	public OAuth2Token(String token) {
		this.token = token;
	}

	@Override
	public String getPrincipal() {
		return token;
	}

	@Override
	public Object getCredentials() {
		return token;
	}
}
