package com.rick;

import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author: Rick
 * @Date: 2020-07-27
 */
public class BaseTest {

    protected Logger log = Logger.getLogger(this.getClass());
    protected static ApplicationContext appContext;
    protected static String[] string = new String[]{"spring-config.xml"};

    @BeforeClass
    public static void setUp() throws Exception {
        try {
            long start = System.currentTimeMillis();
            System.out.println("加载开始..." + start);
            appContext = new ClassPathXmlApplicationContext(string);
        } catch (Exception e) {
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