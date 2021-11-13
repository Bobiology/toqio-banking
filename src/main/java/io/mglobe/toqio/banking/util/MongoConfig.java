package io.mglobe.toqio.banking.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Collections;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

import io.mglobe.toqio.banking.Application;

@Configuration
public class MongoConfig extends AbstractMongoClientConfiguration {

public Logger logger = LogManager.getLogger(this.getClass());

public static MongoTemplate mongoTemplate;

@Value("${mongo.database}")
private String dbName;

@Value("${mongo.host}")
private String host;

@Value("${mongo.port}")
private int port;

@Value("${mongo.username}")
private String username;

@Value("${mongo.password}")
private char[] password;

@Value("${mongo.authdb}")
private String authdb;

/**
* @return the dbName
*/
public String getDbName() {
return dbName;
}

/**
* @param dbName the dbName to set
*/
public void setDbName(String dbName) {
this.dbName = dbName;
}

/**
* @return the host
*/
public String getHost() {
return host.trim();
}

/**
* @param host the host to set
*/
public void setHost(String host) {
this.host = host;
}

/**
* @return the port
*/
public int getPort() {
return port;
}

/**
* @param port the port to set
*/
public void setPort(int port) {
this.port = port;
}

/**
* @return the username
*/
public String getUsername() {

try {
return URLEncoder.encode(username.trim(), StandardCharsets.UTF_8.name());
} catch (UnsupportedEncodingException e) {
}

return username.trim();
}

/**
* @param username the username to set
*/
public void setUsername(String username) {
this.username = username;
}

/**
* @return the password
*/
public char[] getPassword() {
try {
return URLEncoder.encode(new String(password).trim(), StandardCharsets.UTF_8.name()).toCharArray();
} catch (UnsupportedEncodingException e) {
e.printStackTrace();
}
return null;
}

/**
* @param password the password to set
*/
public void setPassword(char[] password) {
this.password = password;
}

/**
* @return the authdb
*/
public String getAuthdb() {
return authdb.trim();
}

/**
* @param authdb the authdb to set
*/
public void setAuthdb(String authdb) {
this.authdb = authdb;
}

/**
* =================================================
*/

@Override
public MongoClient mongoClient() {
ConnectionString connectionString = new ConnectionString(
"mongodb://" + getUsername() + ":" + new String(getPassword()) + "@" + getHost() + ":" + getPort() + "/"
+ getDbName() + "?authSource=" + getAuthdb() + "&readPreference=primary&ssl=false");

MongoClientSettings mongoClientSettings = MongoClientSettings.builder().applyConnectionString(connectionString)
.build();
return MongoClients.create(mongoClientSettings);
}

@Override
public Collection getMappingBasePackages() {
return Collections.singleton("io.mglobe.toqio.banking");
}

@Override
protected String getDatabaseName() {
logger.info("==================================== getDbName: {} ", getDbName());
return getDbName();
}

@Bean
public MongoTemplate mongoTemplate() throws Exception {
MappingMongoConverter converter = new MappingMongoConverter(new DefaultDbRefResolver(mongoDbFactory()),
new MongoMappingContext());
converter.setTypeMapper(new DefaultMongoTypeMapper(null));
converter.setApplicationContext(Application.context);
converter.afterPropertiesSet();

mongoTemplate = new MongoTemplate(mongoDbFactory());
//logger.info("==================================== mongoTemplate: {}  ", mongoTemplate);
logger.info("==================================== mongoTemplate:getDbName: {}  ", mongoTemplate.getDb().getName());
return mongoTemplate;
}

}
