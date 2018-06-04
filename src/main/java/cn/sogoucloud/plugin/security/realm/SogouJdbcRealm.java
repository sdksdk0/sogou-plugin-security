package cn.sogoucloud.plugin.security.realm;

import org.apache.shiro.authc.credential.Md5CredentialsMatcher;
import org.apache.shiro.realm.jdbc.JdbcRealm;

import cn.sogoucloud.framework.helper.DatabaseHelper;
import cn.sogoucloud.plugin.security.SecurityConfig;


/**
 * 基于 Smart 的 JDBC Realm（需要提供相关 sogou.plugin.security.jdbc.* 配置项）
 *
 * @author 
 * @since 1.0.0
 */
public class SogouJdbcRealm extends JdbcRealm {

    public SogouJdbcRealm() {
        super.setDataSource(DatabaseHelper.getDataSource());
        super.setAuthenticationQuery(SecurityConfig.getJdbcAuthcQuery());
        super.setUserRolesQuery(SecurityConfig.getJdbcRolesQuery());
        super.setPermissionsQuery(SecurityConfig.getJdbcPermissionsQuery());
        super.setPermissionsLookupEnabled(true);
        super.setCredentialsMatcher(new Md5CredentialsMatcher());
    }
}
