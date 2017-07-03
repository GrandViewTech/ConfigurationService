package com.lidsys.application.app.service.business;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.lidsys.application.app.entity.bo.Setting;
import com.lidsys.application.app.service.repository.SettingRepository;

@Service
public class SettingService
	{
		@Autowired
		private SettingRepository settingRepository;
		
		public void save(Setting setting)
			{
				setting.setCreationDate(new Date());
				setting.setLastModifiedDate(new Date());
				settingRepository.save(setting);
			}
			
		public List<Setting> findAll()
			{
				return settingRepository.findAll(new Sort(Direction.DESC, "lastModifiedDate"));
			}
	}
