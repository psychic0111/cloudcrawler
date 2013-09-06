package com.xdtech.cloudsearch.module.webcrawler.bean;

public enum ContentType {
	NEWS{
		public String toString(){
			return "新闻";
		}
	},
	BBS{
		public String toString(){
			return "论坛";
		}
	},
	BLOG{
		public String toString(){
			return "博客";
		}
	},
	OTHER{
		public String toString(){
			return "其它";
		}
	};
}
