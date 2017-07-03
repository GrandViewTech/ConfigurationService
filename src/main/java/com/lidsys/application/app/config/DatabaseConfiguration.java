package com.lidsys.application.app.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Component;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientOptions.Builder;
import com.mongodb.MongoClientURI;

@Component
@EnableMongoRepositories(basePackages = "com.lidsys.application.app.service.repository")
public class DatabaseConfiguration extends AbstractMongoConfiguration
	{
		
		@Value("${spring.data.mongodb.host}")
		private String	host;
		
		@Value("${spring.data.mongodb.port}")
		private int		port;
		
		@Value("${spring.data.mongodb.database}")
		private String	database;
		
		@Override
		public @Bean MongoDbFactory mongoDbFactory() throws Exception
			{
				return new SimpleMongoDbFactory((MongoClient) mongo(), database);
			}
			
		@Override
		public @Bean MongoTemplate mongoTemplate() throws Exception
			{
				MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());
				return mongoTemplate;
			}
			
		@Override
		protected String getDatabaseName()
			{
				return database;
			}
			
		@Override
		public @Bean Mongo mongo() throws Exception
			{
				String url = host + ":" + port;
				Builder builder = MongoClientOptions.builder().connectTimeout(1500).connectionsPerHost(8).maxWaitTime(1500).socketKeepAlive(true);
				MongoClientURI mongoClientURI = new MongoClientURI(url, builder);
				return new MongoClient(mongoClientURI);
			}
			
	}
