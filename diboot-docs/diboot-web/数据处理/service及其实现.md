# service及其实现

## 创建service接口

* 在service包下创建相对应的service接口，并继承framework中的 BaseService接口。

```java
import com.diboot.framework.service.BaseService;
import org.springframework.stereotype.Component;

/***
 * 测试富文本相关操作Service
 * @author Mazc
 * @version 2018-08-21
 * Copyright © www.dibo.ltd
*/
@Component
public interface MetadataService extends BaseService{

}
```

* BaseService中已有的接口都可以通过新建的该service调用，您也可以添加自己的相关接口到改service中。

## 创建service实现类

* 在service.impl包下创建相对应的service实现类，并集成framework中的BaseServiceImpl类。
* 注入相对应的Mapper类，为操作数据库数据作准备。
* 重写父类中的[getMapper()]()方法，将会调用这个mapper中相关方法来对数据库数据进行操作。
* 父类中已经内置相关的对数据的基础操作方法，具体可见framework中对于BaseService基类的文档描述，在该类中也可以重写父类中相关的实现。
* 在该类中必须对Service接口中新增接口进行实现。

```java
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diboot.framework.service.mapper.BaseMapper;
import com.diboot.framework.service.impl.BaseServiceImpl;

import com.diboot.web.service.MetadataService;
import com.diboot.web.service.mapper.MetadataMapper;

@Service
public class MetadataServiceImpl extends BaseServiceImpl implements MetadataService{
	private static final Logger logger = LoggerFactory.getLogger(MetadataServiceImpl.class);
	
	@Autowired
	MetadataMapper metadataMapper;
	
	@Override
	protected BaseMapper getMapper() {
		return metadataMapper;
	}
}
```