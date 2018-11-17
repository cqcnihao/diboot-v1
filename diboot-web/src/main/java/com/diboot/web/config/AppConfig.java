package com.diboot.web.config;

import com.diboot.framework.utils.V;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/***
 * Dibo自定义系统配置
 * @author Mazc@dibo.ltd
 * @version 2016年11月10日
 *
 */
public class AppConfig extends com.diboot.framework.config.BaseConfig{
	private static final Logger logger = LoggerFactory.getLogger(AppConfig.class);

	/***
	 * 是否为生产环境
	 * @return
	 */
	public static boolean isProductionEnv(){
		//TODO 修改为自己的生产环境标识
		String editionFlag = getProperty("datasource.url");
		return editionFlag.contains(".rds.");
	}
}