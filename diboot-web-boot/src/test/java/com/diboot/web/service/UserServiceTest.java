package com.diboot.web.service;

import com.diboot.framework.model.BaseUser;
import com.diboot.framework.service.BaseUserService;
import com.diboot.framework.utils.Encryptor;
import com.diboot.framework.utils.V;
import com.diboot.web.ApplicationTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserServiceTest extends ApplicationTest {

    @Autowired
    private BaseUserService baseUserService;

    @Test
    public void testGetModelList() throws Exception{

        List<BaseUser> userList = baseUserService.getModelList(null);
        Assert.assertTrue(V.notEmpty(userList));

    }
}
