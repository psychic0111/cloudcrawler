import java.util.List;

import java.util.Map;


public class Content {

	private String url;
	private String base;
	private String contentType;
	private byte[] content;
	private Map<String, List<String>> headers;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getBase() {
		return base;
	}

	public void setBase(String base) {
		this.base = base;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	public Map<String, List<String>> getHeaders() {
		return headers;
	}

	public void setHeaders(Map<String, List<String>> headers) {
		this.headers = headers;
	}

	public String getHeader(String header) {
		if (header != null) {
			List<String> headerList = headers.get(header);
			if (headerList != null) {
				return headerList.get(0);
			}
		}
		return null;
	}

}
