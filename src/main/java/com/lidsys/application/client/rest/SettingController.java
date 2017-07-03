package com.lidsys.application.client.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lidsys.application.app.entity.bo.Setting;
import com.lidsys.application.app.service.business.SettingService;

@RestController
@CrossOrigin
public class SettingController
	{
		@Autowired
		private SettingService settingService;
		
		@PostMapping(value = "/setting/add/version/1", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
		public void post(@RequestBody Setting setting)
			{
				settingService.save(setting);
			}
			
		@GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE, value = "/setting/findAll/version/1")
		public @ResponseBody ResponseEntity<List<Setting>> get()
			{
				List<Setting> settings = settingService.findAll();
				return ResponseEntity.accepted().body(settings);
			}
	}
