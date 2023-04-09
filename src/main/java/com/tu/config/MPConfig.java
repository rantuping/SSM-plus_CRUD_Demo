package com.tu.config;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


//分页查询需要使用到的拦截器的定义
//使spring管理Bean
//使主程序在运行时，可以扫描到这里的配置类信息
@Configuration   // 必须要添加这个配置注解，才能使主程序在运行的时候扫描到该配置信息
@MapperScan("com.tu.mapper")     //扫描dao层(对应mapper)的包
public class MPConfig {
    //一定要写
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.H2));
        return interceptor;
    }
}
