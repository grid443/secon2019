package org.grid.secon.organizationui.config.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class ScopedOidcUserService extends OidcUserService {
    private static final String SCOPE_AUTHORITY_PREFIX = "SCOPE_";

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
        return user.getAuthorities()
                .stream()
                .map(authority -> SCOPE_AUTHORITY_PREFIX + authority)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
