package cn.sogoucloud.plugin.security;

/**
 * 常量接口
 *
 * @author 
 * @since 1.0.0
 */
public interface SecurityConstant {

    String REALMS = "sogou.plugin.security.realms";
    String REALMS_JDBC = "jdbc";
    String REALMS_CUSTOM = "custom";

    String SOGOU_SECURITY = "sogou.plugin.security.custom.class";

    String JDBC_AUTHC_QUERY = "sogou.plugin.security.jdbc.authc_query";
    String JDBC_ROLES_QUERY = "sogou.plugin.security.jdbc.roles_query";
    String JDBC_PERMISSIONS_QUERY = "sogou.plugin.security.jdbc.permissions_query";

    String CACHE = "sogou.plugin.security.cache";
}
