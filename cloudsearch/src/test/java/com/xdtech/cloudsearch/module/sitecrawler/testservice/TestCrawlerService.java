package com.xdtech.cloudsearch.module.sitecrawler.testservice;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.xdtech.cloudsearch.module.base.page.PageResult;
import com.xdtech.cloudsearch.module.sitecrawler.bean.Crawler;
import com.xdtech.cloudsearch.module.sitecrawler.service.CrawlerService;
import com.xdtech.cloudsearch.testbase.TestBaseService;

public class TestCrawlerService extends TestBaseService{
	
	@Autowired
	private CrawlerService service;
	
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
	public void testCheckCrawler() {
		Crawler crawler = null;
		if(crawler == null){
			Assert.assertNull("方法参数Crawler实例为空！", crawler);
		}
		crawler = new Crawler();
		String result = service.checkCrawler(crawler);
		Assert.assertEquals("true", result);
		
		crawler.setName("爬虫一");
		String result2 = service.checkCrawler(crawler);
		Assert.assertEquals("false", result2);
		
		crawler.setName("爬虫一");
		crawler.setId("4028811f404c7b6e01404c7e24ff0001");
		String result3 = service.checkCrawler(crawler);
		Assert.assertEquals("true", result3);
	}

	@Test
	public void testCheckCode() {
		Crawler crawler = null;
		if(crawler == null){
			Assert.assertNull("方法参数Crawler实例为空！", crawler);
		}
		crawler = new Crawler();
		String result = service.checkCrawler(crawler);
		Assert.assertEquals("true", result);
		
		crawler.setCode("crawler1");
		String result2 = service.checkCrawler(crawler);
		Assert.assertEquals("false", result2);
		
		crawler.setCode("crawler1");
		crawler.setId("4028811f404c7b6e01404c7e24ff0001");
		String result3 = service.checkCrawler(crawler);
		Assert.assertEquals("true", result3);
	}
	
	@Test
	public void testFindByPage() {
		Crawler crawler = null;
		if(crawler == null){
			Assert.assertNull("方法参数Crawler实例为空！", crawler);
		}
		
		PageResult pageResult = null;
		if(pageResult == null){
			Assert.assertNull("方法参数pageResult实例为空！", pageResult);
		}
		
		crawler = new Crawler();
		PageResult result = service.findByPage(crawler, pageResult);
		Assert.assertEquals(2,result.getTotal().longValue());
		Assert.assertEquals(2,result.getRows().size());
		
		crawler.setName("虫一");
		PageResult result2 = service.findByPage(crawler, pageResult);
		Assert.assertEquals(1,result2.getTotal().longValue());
		Assert.assertEquals(1,result2.getRows().size());
		
		crawler.setName("虫多个");
		PageResult result3 = service.findByPage(crawler, pageResult);
		Assert.assertEquals(0,result3.getTotal().longValue());
		Assert.assertEquals(0,result3.getRows().size());
	}
	
	@Test
	public void testFindCrawlersByExample() {
		Crawler crawler = null;
		if(crawler == null){
			Assert.assertNull("方法参数Crawler实例为空！", crawler);
		}
		
		PageResult pageResult = null;
		if(pageResult == null){
			Assert.assertNull("方法参数pageResult实例为空！", pageResult);
		}
		
		crawler = new Crawler();
		PageResult result = service.findByPage(crawler, pageResult);
		Assert.assertEquals(2,result.getTotal().longValue());
		Assert.assertEquals(2,result.getRows().size());
		
		crawler.setName("虫一");
		PageResult result2 = service.findByPage(crawler, pageResult);
		Assert.assertEquals(0,result2.getTotal().longValue());
		Assert.assertEquals(0,result2.getRows().size());
		
		crawler.setName("爬虫一");
		PageResult result3 = service.findByPage(crawler, pageResult);
		Assert.assertEquals(1,result3.getTotal().longValue());
		Assert.assertEquals(1,result3.getRows().size());
	}
	
	@Test
	public void testGetCrawlerByCode() {
		String code = null;
		if(code == null){
			Assert.assertNotNull("方法参数code实例为空！", code);
		}
		Assert.assertNotSame("", code);
		
		code = "crawler1";
		Crawler crawler = service.getCrawlerByCode(code);
		Assert.assertNotNull(crawler);
		Assert.assertEquals("4028811f404c7b6e01404c7e24ff0001",crawler.getId());
		
		code = "crawler10";
		Crawler crawler1 = service.getCrawlerByCode(code);
		Assert.assertNull(crawler1);
	}
	
	@Test
	public void testGetAllCrawlerUsedAndNoStop() {
		List<Crawler> list = service.getAllCrawlerUsedAndNoStop();
		Assert.assertNotNull(list);
		Assert.assertEquals(2,list.size());
		
		Crawler crawler = list.get(0);
		Assert.assertEquals(1, crawler.getStatus().intValue());
	}
	
	@Test
	public void testCheckAllCrawlerStart() {
		boolean result = service.checkAllCrawlerStart();
		Assert.assertTrue(result);
	}
	
	@Test
	public void testCheckCrawlerStart() {
		boolean result = service.checkCrawlerStart();
		Assert.assertFalse(result);
	}
}
