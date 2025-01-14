package com.mavin.SapConnectService.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mavin.SapConnectService.utils.EncryptUtils;

@Configuration
public class JpaConfig {
	@Value("${spring.datasource.url}")
    private String dbUrl;
	
	@Value("${spring.datasource.username}")
    private String username;
	
	@Value("${spring.datasource.password}")
    private String password;

    @Bean
    DataSource dataSource()
    {
		String deCryptedPass = EncryptUtils.decrypt(password);
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("com.mysql.cj.jdbc.Driver");
        dataSourceBuilder.url(dbUrl);
        dataSourceBuilder.username(username);
        dataSourceBuilder.password(deCryptedPass);
        return dataSourceBuilder.build();
    }
}
