package com.xdtech.cloudsearch.module.sitecrawler.testservice;

import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.xdtech.cloudsearch.module.base.page.PageResult;
import com.xdtech.cloudsearch.module.sitecrawler.bean.CrawlerLog;
import com.xdtech.cloudsearch.module.sitecrawler.service.CrawlerLogService;
import com.xdtech.cloudsearch.testbase.TestBaseService;

public class TestCrawlerLogService extends TestBaseService{
	
	@Autowired
	private CrawlerLogService service;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCheckCrawlerLog() {
		CrawlerLog crawlerlog = null;
		if(crawlerlog == null){
			fail("方法参数CrawlerLog实例为空！");
		}
		crawlerlog = new CrawlerLog();
		String result = service.checkCrawlerLog(crawlerlog);
		Assert.assertEquals("true", result);
		
		crawlerlog.setId("0005c6cc41c540e791f382e48826feaa");
		String result2 = service.checkCrawlerLog(crawlerlog);
		Assert.assertEquals("true", result2);
	}

	@Test
	@Ignore
	public void testFindByPageCrawlerLogPageResult() {
		PageResult pageResult = new PageResult();
		CrawlerLog crawlerlog = new CrawlerLog();
		pageResult = service.findByPage(crawlerlog, pageResult);
		Assert.assertNotNull(pageResult);
		Assert.assertEquals(4541,pageResult.getTotal().longValue());
		
		Assert.assertNotNull(pageResult.getRows());
		Assert.assertEquals(pageResult.getRows().size(), pageResult.getPageSize());
	}

}
