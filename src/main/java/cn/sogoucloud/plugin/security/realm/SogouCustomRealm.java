package cn.sogoucloud.plugin.security.realm;

import java.util.HashSet;
import java.util.Set;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.Md5CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;

import cn.sogoucloud.plugin.security.SecurityConstant;
import cn.sogoucloud.plugin.security.SogouSecurity;


/**
 * 基于 Smart 的自定义 Realm（需要实现 SogouSecurity 接口）
 *
 * @author 
 * @since 1.0.0
 */
public class SogouCustomRealm extends AuthorizingRealm {

    private final SogouSecurity smartSecurity;

    public SogouCustomRealm(SogouSecurity smartSecurity) {
        this.smartSecurity = smartSecurity;
        super.setName(SecurityConstant.REALMS_CUSTOM);
        super.setCredentialsMatcher(new Md5CredentialsMatcher());
    }

    @Override
    public AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        if (token == null) {
            throw new AuthenticationException("parameter token is null");
        }

        String username = ((UsernamePasswordToken) token).getUsername();

        String password = smartSecurity.getPassword(username);

        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo();
        authenticationInfo.setPrincipals(new SimplePrincipalCollection(username, super.getName()));
        authenticationInfo.setCredentials(password);
        return authenticationInfo;
    }

    @Override
    public AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        if (principals == null) {
            throw new AuthorizationException("parameter principals is null");
        }

        String username = (String) super.getAvailablePrincipal(principals);

        Set<String> roleNameSet = smartSecurity.getRoleNameSet(username);

        Set<String> permissionNameSet = new HashSet<String>();
        if (roleNameSet != null && roleNameSet.size() > 0) {
            for (String roleName : roleNameSet) {
                Set<String> currentPermissionNameSet = smartSecurity.getPermissionNameSet(roleName);
                permissionNameSet.addAll(currentPermissionNameSet);
            }
        }

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(roleNameSet);
        authorizationInfo.setStringPermissions(permissionNameSet);
        return authorizationInfo;
    }
}
