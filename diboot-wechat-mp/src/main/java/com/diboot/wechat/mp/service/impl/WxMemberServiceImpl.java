package com.diboot.wechat.mp.service.impl;

import com.diboot.framework.service.BaseService;
import com.diboot.framework.service.impl.BaseServiceImpl;
import com.diboot.framework.service.mapper.BaseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/***
* 服务号用户相关操作Service
* @version 1.0
* @date 2018-12-12
* @author Mazc
 * Copyright © www.dibo.ltd
*/
@Service
public class WxMemberServiceImpl extends BaseServiceImpl implements BaseService {
	private static final Logger logger = LoggerFactory.getLogger(WxMemberServiceImpl.class);
	
	@Autowired
	WxMemberMapper wxMemberMapper;
	
	@Override
	protected BaseMapper getMapper() {
		return wxMemberMapper;
	}
}
