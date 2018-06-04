package cn.sogoucloud.plugin.security;

import cn.sogoucloud.framework.helper.ConfigHelper;
import cn.sogoucloud.framework.util.ReflectionUtil;

/**
 * 从配置文件中获取相关属性
 *
 * @author 
 * @since 1.0.0
 */
public final class SecurityConfig {

    public static String getRealms() {
        return ConfigHelper.getString(SecurityConstant.REALMS);
    }

    public static SogouSecurity getSogouSecurity() {
        String className = ConfigHelper.getString(SecurityConstant.SOGOU_SECURITY);
        return (SogouSecurity) ReflectionUtil.newInstance(className);
    }

    public static String getJdbcAuthcQuery() {
        return ConfigHelper.getString(SecurityConstant.JDBC_AUTHC_QUERY);
    }

    public static String getJdbcRolesQuery() {
        return ConfigHelper.getString(SecurityConstant.JDBC_ROLES_QUERY);
    }

    public static String getJdbcPermissionsQuery() {
        return ConfigHelper.getString(SecurityConstant.JDBC_PERMISSIONS_QUERY);
    }

    public static boolean isCache() {
        return ConfigHelper.getBoolean(SecurityConstant.CACHE);
    }
}
