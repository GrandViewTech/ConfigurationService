package com.lidsys.application.app.service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.lidsys.application.app.entity.bo.Setting;

@Repository
public interface SettingRepository extends MongoRepository<Setting, String>
	{
		
	}
