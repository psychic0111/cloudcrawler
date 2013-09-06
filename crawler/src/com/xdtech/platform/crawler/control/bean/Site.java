package com.xdtech.platform.crawler.control.bean;

import java.lang.reflect.Field;

import com.xdtech.platform.crawler.ws.dispatcher.SiteBean;

/**
 * 站点配置
 * 
 * @author zhangjianbing@msn.com
 */
public class Site {
	/** 主键 */
	private String id;
	/** 站点名称 */
	private String siteName;
	/** 站点分类主键 */
	private String sortId;
	/** 站点分类名称 */
	private String category;
	/** 入口url */
	private String inUrl;
	/** 站点状态 0禁用 1启用 */
	private int siteStatue;
	/** 内容编码 */
	private String contentEncode;
	/** 更新频率 */
	private int updaeInterval;
	/** 所属爬虫 */
	private String crawler;
	/** 抓取深度 */
	private short depth;
	/** 包含字符 */
	private String contain;
	/** 匹配字符 */
	private String regrule;
	/** 代理主键 */
	private String proxyId;
	/** 采集协议 */
	private String protocol;
	/** 登陆主键 */
	private String loginId;
	/** 所使用的解析模板的Id */
	private String templateids;
	/** 国家代码 */
	private String contryId;
	/** 国家名称 */
	private String contryName;
	/** 省Id */
	private String provinceId;
	/** 省名称 */
	private String provinceName;
	/** 城市ID */
	private String cityId;
	/** 城市名称 */
	private String cityName;
	/** 县Id */
	private String countyId;
	/** 县名称 */
	private String countyName;
	/** 网站类型 1：网页 2：图片 3：视频 */
	private int webType;
	/** 区域类型 */
	private String areaType;

	public String getId() {
		return id;
	}

	public String getSiteName() {
		return siteName;
	}

	public String getSortId() {
		return sortId;
	}

	public String getCategory() {
		return category;
	}

	public String getInUrl() {
		return inUrl;
	}

	public int getSiteStatue() {
		return siteStatue;
	}

	public String getContentEncode() {
		return contentEncode;
	}

	public int getUpdaeInterval() {
		return updaeInterval;
	}

	public String getCrawler() {
		return crawler;
	}

	public short getDepth() {
		return depth;
	}

	public String getContain() {
		return contain;
	}

	public String getRegrule() {
		return regrule;
	}

	public String getProxyId() {
		return proxyId;
	}

	public String getProtocol() {
		return protocol;
	}

	public String getLoginId() {
		return loginId;
	}

	public String getTemplateids() {
		return templateids;
	}

	public String getContryId() {
		return contryId;
	}

	public String getContryName() {
		return contryName;
	}

	public String getProvinceId() {
		return provinceId;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public String getCityId() {
		return cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public String getCountyId() {
		return countyId;
	}

	public String getCountyName() {
		return countyName;
	}

	public int getWebType() {
		return webType;
	}

	public String getAreaType() {
		return areaType;
	}

	/**
	 * 站点赋值
	 * 
	 * @param site
	 */
	public void fix(SiteBean siteBean) {
		if (siteBean != null) {
			Field[] beanFields = SiteBean.class.getDeclaredFields();
			Field[] siteFields = Site.class.getDeclaredFields();
			for (Field siteField : siteFields) {
				for (Field beanField : beanFields) {
					if (beanField.getName().equals(siteField.getName())) {
						beanField.setAccessible(true);
						try {
							siteField.set(this, beanField.get(siteBean));
						} catch (IllegalArgumentException e) {
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
	}

}
