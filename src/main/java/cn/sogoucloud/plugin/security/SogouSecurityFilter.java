package cn.sogoucloud.plugin.security;

import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.mgt.CachingSecurityManager;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.web.mgt.WebSecurityManager;
import org.apache.shiro.web.servlet.ShiroFilter;

import cn.sogoucloud.plugin.security.realm.SogouCustomRealm;
import cn.sogoucloud.plugin.security.realm.SogouJdbcRealm;

public class SogouSecurityFilter extends ShiroFilter{

	
	 @Override
	    public void init() throws Exception {
	        super.init();
	        WebSecurityManager webSecurityManager = super.getSecurityManager();
	        setRealms(webSecurityManager);
	        setCache(webSecurityManager);
	    }

	    private void setRealms(WebSecurityManager webSecurityManager) {
	        String securityRealms = SecurityConfig.getRealms();
	        if (securityRealms != null) {
	            String[] securityRealmArray = securityRealms.split(",");
	            if (securityRealmArray.length > 0) {
	                Set<Realm> realms = new LinkedHashSet<Realm>();
	                for (String securityRealm : securityRealmArray) {
	                    if (securityRealm.equalsIgnoreCase(SecurityConstant.REALMS_JDBC)) {
	                        addJdbcRealm(realms);
	                    } else if (securityRealm.equalsIgnoreCase(SecurityConstant.REALMS_CUSTOM)) {
	                        addCustomRealm(realms);
	                    }
	                }
	                RealmSecurityManager realmSecurityManager = (RealmSecurityManager) webSecurityManager;
	                realmSecurityManager.setRealms(realms);
	            }
	        }
	    }

	    private void addJdbcRealm(Set<Realm> realms) {
	        SogouJdbcRealm smartJdbcRealm = new SogouJdbcRealm();
	        realms.add(smartJdbcRealm);
	    }

	    private void addCustomRealm(Set<Realm> realms) {
	        SogouSecurity smartSecurity = SecurityConfig.getSogouSecurity();
	        SogouCustomRealm smartCustomRealm = new SogouCustomRealm(smartSecurity);
	        realms.add(smartCustomRealm);
	    }

	    private void setCache(WebSecurityManager webSecurityManager) {
	        if (SecurityConfig.isCache()) {
	            CachingSecurityManager cachingSecurityManager = (CachingSecurityManager) webSecurityManager;
	            CacheManager cacheManager = new MemoryConstrainedCacheManager();
	            cachingSecurityManager.setCacheManager(cacheManager);
	        }
	    }
	
}
