package com.lidsys.application.app.entity.bo;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "SETTING")
public class Setting implements Serializable
	{
		private static final long	serialVersionUID	= -4233243284524756887L;
		
		private String				envoirnment;
		
		private String				configKey;
		
		private String				configValue;
		
		private String				application;
		
		private Date				creationDate;
		
		private Date				lastModifiedDate;
		
		public String getApplication()
			{
				return application;
			}
			
		public void setApplication(String application)
			{
				this.application = application;
			}
			
		public String getEnvoirnment()
			{
				return envoirnment;
			}
			
		public void setEnvoirnment(String envoirnment)
			{
				this.envoirnment = envoirnment;
			}
			
		public String getConfigKey()
			{
				return configKey;
			}
			
		public void setConfigKey(String configKey)
			{
				this.configKey = configKey;
			}
			
		public String getConfigValue()
			{
				return configValue;
			}
			
		public void setConfigValue(String configValue)
			{
				this.configValue = configValue;
			}

		public Date getCreationDate()
			{
				return creationDate;
			}

		public void setCreationDate(Date creationDate)
			{
				this.creationDate = creationDate;
			}

		public Date getLastModifiedDate()
			{
				return lastModifiedDate;
			}

		public void setLastModifiedDate(Date lastModifiedDate)
			{
				this.lastModifiedDate = lastModifiedDate;
			}
			
		
		
	}
