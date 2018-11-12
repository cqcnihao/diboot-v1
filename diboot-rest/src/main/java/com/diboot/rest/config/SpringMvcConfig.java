package com.diboot.rest.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.diboot.framework.config.BaseConfig;
import com.diboot.framework.config.BaseCons;
import com.diboot.framework.utils.D;
import com.diboot.framework.utils.DateConverter4Spring;
import com.diboot.rest.security.SecurityInterceptor;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/***
 * Dibo Spring配置文件
 * @author Mazc@dibo.ltd
 * @version 2016年12月7日
 *
 */
@Configuration
@EnableAutoConfiguration
@EnableTransactionManagement(proxyTargetClass=true)
@ComponentScan(basePackages = {"com.diboot"})
@MapperScan(basePackages={"com.diboot.**.mapper"})
public class SpringMvcConfig implements WebMvcConfigurer{
    private static final Logger logger = LoggerFactory.getLogger(SpringMvcConfig.class);

    //10M
    private static final Long MAX_UPLOAD_SIZE = 10*1024*1024L;

    @Bean(name = "dataSource")
    public DataSource dataSource() {
        // DataSource连接池配置
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(BaseConfig.getProperty("datasource.url"));
        config.setUsername(BaseConfig.getProperty("datasource.username"));
        config.setPassword(BaseConfig.getProperty("datasource.password"));
        config.setDriverClassName("com.mysql.jdbc.Driver");
        // 生产环境参数
        if(AppConfig.isProductionEnv()){
            config.setMaximumPoolSize(100);
        }
        else{
            // 开发模式下，用于diboot devtools
            config.setMaximumPoolSize(10);
            config.addDataSourceProperty("useInformationSchema", true);
        }
        return new HikariDataSource(config);
    }

    @Bean
    public PlatformTransactionManager txManager() {
        PlatformTransactionManager txManager = new DataSourceTransactionManager(dataSource());
        return txManager;
    }

    @Bean(name="conversionService")
    public ConversionService conversionService(){
        ConversionServiceFactoryBean factoryBean = new ConversionServiceFactoryBean();
        Set<Converter> converters = new HashSet<>();
        converters.add(new DateConverter4Spring());
        factoryBean.setConverters(converters);
        factoryBean.afterPropertiesSet();
        return factoryBean.getObject();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SecurityInterceptor()).addPathPatterns("/**");
    }

    /**
     * JSON转换组件替换为fastJson
     */
    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters() {
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        //处理中文乱码问题
        List<MediaType> fastMediaTypes = new ArrayList<>();
        fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        converter.setSupportedMediaTypes(fastMediaTypes);
        // 配置转换格式
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        fastJsonConfig.setDateFormat(D.FORMAT_DATETIME_Y4MDHM);
        converter.setFastJsonConfig(fastJsonConfig);

        HttpMessageConverter<?> httpMsgConverter = converter;
        return new HttpMessageConverters(httpMsgConverter);
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new DateConverter4Spring());
    }

    // 需要文件上传，开启此配置
    @Bean
    public MultipartResolver multipartResolver(){
        CommonsMultipartResolver bean = new CommonsMultipartResolver();
        bean.setDefaultEncoding(BaseCons.CHARSET_UTF8);
        bean.setMaxUploadSize(MAX_UPLOAD_SIZE);
        return bean;
    }

}