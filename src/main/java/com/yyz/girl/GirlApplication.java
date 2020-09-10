package com.yyz.girl;

import com.yyz.girl.listener.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author : yyz
 * @date : 20181101
 */
@EnableAspectJAutoProxy //使用aspectJ自动代理
@EnableTransactionManagement //spring boot开启事务支持
@SpringBootApplication
@ServletComponentScan("com.yyz.girl.filter")
public class GirlApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		// 设置启动类,用于独立tomcat运行的入口
		return builder.sources(GirlApplication.class);
	}

	public static void main(String[] args) {
		//SpringApplication.run(GirlApplication.class, args);
		/**
		 * 启动类添加应用开始监听器,并禁用banner显示
		 */
		SpringApplication springApplication = new SpringApplication(GirlApplication.class);
		springApplication.addListeners(
				new MyApplicationReadyEventListener(),
				new MyApplicationStartingEventListener(),
				new MyApplicationEnvironmentPreparedEventListener(),
				new MyApplicationPreparedEventListener(),
				new MyApplicationFailedEventListener(),
				new MyApplicationStartedEventListener());
		/*springApplication.addListeners(new MyApplicationStartingEventListener());
		springApplication.addListeners(new MyApplicationStartedEventListener());*/
		//springApplication.setBannerMode(Banner.Mode.OFF);

		/*springBoot屏蔽命令行访问属性的设置*/
		springApplication.setAddCommandLineProperties(false);

		springApplication.run(args);
	}
}
