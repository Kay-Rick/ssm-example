package com.rick;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author: Rick
 * @Date: 2020-07-27
 */
public class BaseTest {
    
    protected final static Logger log = LoggerFactory.getLogger(BaseTest.class);
    protected static ApplicationContext appContext;
    protected static String[] string = new String[]{"spring-config.xml"};

    @BeforeClass
    public static void setUp() throws Exception {
        try {
            long start = System.currentTimeMillis();
            log.info("加载开始：{}", start);
            appContext = new ClassPathXmlApplicationContext(string);
        } catch (Exception e) {
            log.error("加载出错：{}", e.getMessage());
            e.printStackTrace();
        }
    }

    @Before
    public void autoSetBean() {
        appContext.getAutowireCapableBeanFactory()
                .autowireBeanProperties(this, DefaultListableBeanFactory.AUTOWIRE_BY_NAME, false);
    }

    @AfterClass
    public static void tearDown() throws Exception {
    }

}