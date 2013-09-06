package com.xdtech.platform.crawler;

public class CrawlerStatus {
	/** 抓取的页面的总数 */
	private static long total = 0L;
	/** HTML下载活动的线程数 */
	private static int crawlThreadNum;
	/** 失败数 */
	public static int failCount = 0;
	/** 爬虫状态 1:停止 2：运行 3:停止中 */
	public static String RUNSTATUS = "1";
	/** 爬虫的流量 */
	private static Flow flow = new Flow();

	/**
	 * 添加流量
	 * 
	 * @param contentLengthB
	 */
	public static synchronized void addLength(long contentLengthB) {
		flow.addB(contentLengthB);
	}

	/**
	 * 获取当前爬虫流量
	 * 
	 * @return
	 */
	public static Flow getFlow() {
		Flow result = new Flow(flow);
		return result;
	}

	public static void addTotal(int count) {
		total = total + count;
	}

	public static long getTotal() {
		return total;
	}

	public static int getCrawlThreadNum() {
		return crawlThreadNum;
	}

	public static void setCrawlThreadNum(int crawlThreadNum) {
		CrawlerStatus.crawlThreadNum = crawlThreadNum;
	}

	/**
	 * 流量
	 * 
	 * @author zhangjianbing@msn.com
	 */
	public static class Flow {
		/** G */
		private long g = 0;
		/** M */
		private long m = 0;
		/** K */
		private long k = 0;
		/** b */
		private long b = 0;

		/**
		 * 默认构造方法
		 */
		public Flow() {
		}

		/**
		 * 默认构造方法
		 * 
		 * @param flow
		 */
		public Flow(Flow flow) {
			setB(flow.getB());
			setK(flow.getK());
			setM(flow.getM());
			setG(flow.getG());
		}

		public long getG() {
			return g;
		}

		public void setG(long g) {
			this.g = g;
		}

		public long getM() {
			return m;
		}

		public void setM(long m) {
			this.m = m;
		}

		public long getK() {
			return k;
		}

		public void setK(long k) {
			this.k = k;
		}

		public long getB() {
			return b;
		}

		public void setB(long b) {
			this.b = b;
		}

		/**
		 * 添加字节
		 * 
		 * @param length
		 */
		private void addB(long lengthB) {
			b = b + lengthB;
			if (b >= 1024) {
				addK(b >> 10);
				b = b & 1023;
			}
		}

		/**
		 * 添加K
		 * 
		 * @param length
		 */
		private void addK(long lengthK) {
			k = k + lengthK;
			if (k >= 1024) {
				addM(k >> 10);
				k = k & (1023);
			}
		}

		private void addM(long lengthM) {
			m = m + lengthM;
			if (m >= 1024) {
				addG(m >> 10);
				m = m & 1023;
			}
		}

		private void addG(long lengthG) {
			g = g + lengthG;
		}

		public String toString() {
			StringBuilder sbInfo = new StringBuilder();
			sbInfo.append(g).append('G').append(m).append('M').append(k).append('K').append(b).append('B');
			return sbInfo.toString();
		}
	}
}
