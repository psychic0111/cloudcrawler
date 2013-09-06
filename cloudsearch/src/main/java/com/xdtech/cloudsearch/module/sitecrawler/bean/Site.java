package com.xdtech.cloudsearch.module.sitecrawler.bean;

import java.util.ArrayList;
import java.util.Date;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

/**
 * 站点分类
 * 
 * @author WangWei
 * 2013-06-13
 */
@Entity
@Table(name = "xd_crawler_site")
public class Site {
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
	 * 代理
	 */
	private SiteProxy siteProxy;
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
	private List<Template> templates = new ArrayList<Template>();
	/**
	 * 站点是否分配
	 */
	private Integer ispart;
	
	/**
	 * 地址规则
	 */
	private String addressRule;
	/**
	 * 网站编码
	 */
	private String htmlCode;
	@Id
	@Column(length = 32)
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Column(columnDefinition="varchar(150)")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(columnDefinition="varchar(32)")
	public String getSiteCategoryId() {
		return siteCategoryId;
	}
	public void setSiteCategoryId(String siteCategoryId) {
		this.siteCategoryId = siteCategoryId;
	}
	@Column(columnDefinition="varchar(15)")
	public String getSiteCategoryName() {
		return siteCategoryName;
	}
	public void setSiteCategoryName(String siteCategoryName) {
		this.siteCategoryName = siteCategoryName;
	}
	@Column(columnDefinition="varchar(1500)")
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
	@Column(columnDefinition="varchar(20)")
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
	@Column(columnDefinition="varchar(32)")
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
	@Column(columnDefinition="varchar(600)")
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
	@Column(columnDefinition="varchar(32)")
	public String getProxyId() {
		return proxyId;
	}
	public void setProxyId(String proxyId) {
		this.proxyId = proxyId;
	}
	@Column(columnDefinition="varchar(60)")
	public String getProtocol() {
		return protocol;
	}
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}
	@Column(columnDefinition="varchar(32)")
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	@Column(columnDefinition="text")
	public String getTemplateIds() {
		return templateIds;
	}
	public void setTemplateIds(String templateIds) {
		this.templateIds = templateIds;
	}
	@Column(columnDefinition="varchar(32)")
	public String getContryId() {
		return contryId;
	}
	public void setContryId(String contryId) {
		this.contryId = contryId;
	}
	@Column(columnDefinition="varchar(60)")
	public String getContryName() {
		return contryName;
	}
	public void setContryName(String contryName) {
		this.contryName = contryName;
	}
	@Column(columnDefinition="varchar(32)")
	public String getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}
	@Column(columnDefinition="varchar(60)")
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	@Column(columnDefinition="varchar(32)")
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	@Column(columnDefinition="varchar(60)")
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	@Column(columnDefinition="varchar(32)")
	public String getAreaId() {
		return areaId;
	}
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	@Column(columnDefinition="varchar(60)")
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
	@Column(columnDefinition="varchar(60)")
	public String getMediaType() {
		return mediaType;
	}
	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}
	@Column(columnDefinition="varchar(60)")
	public String getAreaType() {
		return areaType;
	}
	public void setAreaType(String areaType) {
		this.areaType = areaType;
	}
	@Column(columnDefinition="varchar(32)")
	public String getSitePluginId() {
		return sitePluginId;
	}
	public void setSitePluginId(String sitePluginId) {
		this.sitePluginId = sitePluginId;
	}
	@Column(columnDefinition="text")
	public String getDynamicRule() {
		return dynamicRule;
	}
	public void setDynamicRule(String dynamicRule) {
		this.dynamicRule = dynamicRule;
	}
	@Column(columnDefinition="varchar(32)")
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
	@Transient
	public List<Template> getTemplates() {
		return templates;
	}
	public void setTemplates(List<Template> templates) {
		this.templates = templates;
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
	public Integer getIspart() {
		return ispart;
	}
	public void setIspart(Integer ispart) {
		this.ispart = ispart;
	}
	@Column(columnDefinition="varchar(600)")
	public String getAddressRule() {
		return addressRule;
	}
	public void setAddressRule(String addressRule) {
		this.addressRule = addressRule;
	}
	public String getHtmlCode() {
		return htmlCode;
	}
	public void setHtmlCode(String htmlCode) {
		this.htmlCode = htmlCode;
	}
	@Transient
	public SiteProxy getSiteProxy() {
		return siteProxy;
	}
	public void setSiteProxy(SiteProxy siteProxy) {
		this.siteProxy = siteProxy;
	}

}
