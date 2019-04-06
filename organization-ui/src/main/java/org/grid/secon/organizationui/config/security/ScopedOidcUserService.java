package org.grid.secon.organizationui.config.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUserAuthority;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class ScopedOidcUserService extends OidcUserService {
    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
        OidcUser user = super.loadUser(userRequest);
        Collection<? extends GrantedAuthority> authorities = buildAuthorities(
                user,
                userRequest.getAccessToken().getScopes()
        );
        return new DefaultOidcUser(
                authorities,
                userRequest.getIdToken(),
                user.getUserInfo()
        );
    }

    private Collection<? extends GrantedAuthority> buildAuthorities(OidcUser user, Set<String> scopes) {
        if (CollectionUtils.isEmpty(scopes)) {
            return user.getAuthorities();
        }
        Set<GrantedAuthority> authorities = new HashSet<>(user.getAuthorities());
        for (String scope : scopes) {
            if ("openid".equals(scope)) {
                continue;
            }
            authorities.add(new OidcUserAuthority("SCOPE_" + scope, user.getIdToken(), user.getUserInfo()));
        }
        return authorities;
    }
}
