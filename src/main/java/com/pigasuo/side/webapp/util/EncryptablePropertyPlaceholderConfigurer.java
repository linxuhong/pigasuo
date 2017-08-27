package com.pigasuo.side.webapp.util;

/**
 * Created by linxuhong on 2017/8/17.
 */
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;


import java.util.Properties;

/**
 * @author  linxuhong
 * pwd 解析
 */
public class EncryptablePropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer{

    private static final String KEY = "*#06#";

    protected void processProperties(ConfigurableListableBeanFactory beanFactory, Properties props)
            throws BeansException {
        try {
            EncryptionDecryption decryption = new EncryptionDecryption(KEY);
            String username = props.getProperty(LoginConstants.JDBC_DATASOURCE_USERNAME_KEY);
            if (username != null) {
                props.setProperty(LoginConstants.JDBC_DATASOURCE_USERNAME_KEY, decryption.decrypt(username));
            }

            String password = props.getProperty(LoginConstants.JDBC_DATASOURCE_PASSWORD_KEY);
            if (password != null) {
                props.setProperty(LoginConstants.JDBC_DATASOURCE_PASSWORD_KEY, decryption.decrypt(password));
            }
            super.processProperties(beanFactory, props);
        } catch (Exception e) {
            throw new BeanInitializationException(e.getMessage());
        }
    }


}
