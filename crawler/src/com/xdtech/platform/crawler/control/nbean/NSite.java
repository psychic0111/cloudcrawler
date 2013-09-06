package com.xdtech.platform.crawler.control.nbean;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.xdtech.platform.crawler.ws.ndispatcher.Site;



public class NSite {
	/**
	 * 主键
	 */
	private String id;
	/**
	 * 站点名称
	 */
	private String name;
	/**
	 * 站点分类
	 */
	private String siteCategoryId;
	/**
	 * 站点分类名称
	 */
	private String siteCategoryName;
	/**
	 * 入口地址规则
	 */
	private String inUrl;
	/**
	 * 站点状态
	 */
	private Integer statue; 
	/**
	 * 内容编码
	 */
	private String contentEncode;
	/**
	 * 更新频率
	 */
	private Integer updaeInterval;
	/**
	 * 所属爬虫
	 */
	private String crawlerId;
	/**
	 * 抓取深度
	 */
	private int depth;
	/**
	 * Url匹配规则
	 */
	private String urlReg;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 最后一次生成时间
	 */
	private Date lastUrlGenerateDate;
	/**
	 * 代理主键
	 */
	private String proxyId;
	/**
	 * 采集协议
	 */
	private String protocol;
	/**
	 * 登陆主键
	 */
	private String loginId;
	/**
	 * 采集模板IDS
	 */
	private String templateIds;
	/**
	 * 国家代码
	 */
	private String contryId;
	/**
	 * 国家名称
	 */
	private String contryName;
	/**
	 * 省代码
	 */
	private String provinceId;
	/**
	 * 省名称
	 */
	private String provinceName;
	/**
	 * 城市代码
	 */
	private String cityId;
	/**
	 * 城市名称
	 */
	private String cityName;
	/**
	 * 地区编码
	 */
	private String areaId;
	/**
	 * 地区名称
	 */
	private String areaName;
	/**
	 * 网站类型
	 */
	private Integer webType;
	/**
	 * 媒体类型 1 网页 2 媒体 3 资讯
	 */
	private String mediaType;
	/**
	 * 区域类型
	 */
	private String areaType;
	/**
	 * 插件ID
	 */
	private String sitePluginId;
	/**
	 * 动态地址规则
	 */
	private String dynamicRule;
	/**
	 * 操作人
	 */
	private String operateUser;
	/**
	 * 操作时间
	 */
	private Date operateTime;
	/**
	 * 采集深度
	 */
	private Integer crawlerDeep;
	/**
	 * 站点优先级 高 中 低
	 */
	private String level;
	/**
	 * 站点采用的解析模板
	 */
	private List<NTemplate> templates;
	/**
	 * 最后一次生成地址的时间
	 */
	private Date urlGenerateDate; 
	/**
	 * 地址规则
	 */
	private String addressRule;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSiteCategoryId() {
		return siteCategoryId;
	}
	public void setSiteCategoryId(String siteCategoryId) {
		this.siteCategoryId = siteCategoryId;
	}
	public String getSiteCategoryName() {
		return siteCategoryName;
	}
	public void setSiteCategoryName(String siteCategoryName) {
		this.siteCategoryName = siteCategoryName;
	}
	public String getInUrl() {
		return inUrl;
	}
	public void setInUrl(String inUrl) {
		this.inUrl = inUrl;
	}
	public Integer getStatue() {
		return statue;
	}
	public void setStatue(Integer statue) {
		this.statue = statue;
	}
	public String getContentEncode() {
		return contentEncode;
	}
	public void setContentEncode(String contentEncode) {
		this.contentEncode = contentEncode;
	}
	public Integer getUpdaeInterval() {
		return updaeInterval;
	}
	public void setUpdaeInterval(Integer updaeInterval) {
		this.updaeInterval = updaeInterval;
	}
	public String getCrawlerId() {
		return crawlerId;
	}
	public void setCrawlerId(String crawlerId) {
		this.crawlerId = crawlerId;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	public String getUrlReg() {
		return urlReg;
	}
	public void setUrlReg(String urlReg) {
		this.urlReg = urlReg;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getLastUrlGenerateDate() {
		return lastUrlGenerateDate;
	}
	public void setLastUrlGenerateDate(Date lastUrlGenerateDate) {
		this.lastUrlGenerateDate = lastUrlGenerateDate;
	}
	public String getProxyId() {
		return proxyId;
	}
	public void setProxyId(String proxyId) {
		this.proxyId = proxyId;
	}
	public String getProtocol() {
		return protocol;
	}
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getTemplateIds() {
		return templateIds;
	}
	public void setTemplateIds(String templateIds) {
		this.templateIds = templateIds;
	}
	public String getContryId() {
		return contryId;
	}
	public void setContryId(String contryId) {
		this.contryId = contryId;
	}
	public String getContryName() {
		return contryName;
	}
	public void setContryName(String contryName) {
		this.contryName = contryName;
	}
	public String getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getAreaId() {
		return areaId;
	}
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public Integer getWebType() {
		return webType;
	}
	public void setWebType(Integer webType) {
		this.webType = webType;
	}
	public String getMediaType() {
		return mediaType;
	}
	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}
	public String getAreaType() {
		return areaType;
	}
	public void setAreaType(String areaType) {
		this.areaType = areaType;
	}
	public String getSitePluginId() {
		return sitePluginId;
	}
	public void setSitePluginId(String sitePluginId) {
		this.sitePluginId = sitePluginId;
	}
	public String getDynamicRule() {
		return dynamicRule;
	}
	public void setDynamicRule(String dynamicRule) {
		this.dynamicRule = dynamicRule;
	}
	public String getOperateUser() {
		return operateUser;
	}
	public void setOperateUser(String operateUser) {
		this.operateUser = operateUser;
	}
	public Date getOperateTime() {
		return operateTime;
	}
	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}
	public Integer getCrawlerDeep() {
		return crawlerDeep;
	}
	public void setCrawlerDeep(Integer crawlerDeep) {
		this.crawlerDeep = crawlerDeep;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public List<NTemplate> getTemplates() {
		return templates;
	}
	public void setTemplates(List<NTemplate> templates) {
		this.templates = templates;
	}
	public Date getUrlGenerateDate() {
		return urlGenerateDate;
	}
	public void setUrlGenerateDate(Date urlGenerateDate) {
		this.urlGenerateDate = urlGenerateDate;
	}
	public String getAddressRule() {
		return addressRule;
	}
	public void setAddressRule(String addressRule) {
		this.addressRule = addressRule;
	}
	/**
	 * 站点赋值
	 * 
	 * @param site
	 */
	public void fix(Site siteBean) {
		if (siteBean != null) {
			Field[] beanFields = Site.class.getDeclaredFields();
			Field[] siteFields = NSite.class.getDeclaredFields();
			for (Field siteField : siteFields) {
				for (Field beanField : beanFields) {
					if (beanField.getName().equals(siteField.getName())) {
						beanField.setAccessible(true);
						try {
							if("operateTime".equals(beanField.getName())){
								SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
								String date = beanField.get(siteBean).toString().replace("T", " ").substring(0,19);
								try {
									siteField.set(this, format.parse(date));
								} catch (ParseException e) {
									siteField.set(this,new Date());
								}
							}else{
								siteField.set(this, beanField.get(siteBean));
							}
						} catch (IllegalArgumentException e) {
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							e.printStackTrace();
						}
					}
				}
			}
			this.setUrlGenerateDate(new Date());
		}
	}
}
