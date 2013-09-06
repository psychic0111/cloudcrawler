package com.xdtech.cloudsearch.webservice.client;

public class TestClient {
	
	public static void main(String[] args) {
		ServerCrawlerServiceImplService service = new ServerCrawlerServiceImplService();
		Crawler crawler = service.getServerCrawlerServiceImplPort().getCrawlerSite("crawler1111222");
		System.out.println(crawler.getCode()+"\t"+crawler.getName());
		for(Task task:crawler.getTasks()){
			System.out.println(task.getTaskName()+"\t"+task.getOperateTime());
			for(Site site:task.getSiteList()){
				System.out.println(site.getName()+"");
				for(Template t:site.getTemplates()){
					System.out.println("********模板***********");
					System.out.println(t.getName());
				}
			}
		}
		System.out.println(123);
	}

}
