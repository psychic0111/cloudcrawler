import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class T {
	public static void main(String[] args) {
		String content = "<html><head></head><body><input type=text/>阿娇的烧烤<div>爱科技<textarea></textarea>大吉萨</div></body></html>";
		Document doc = Jsoup.parseBodyFragment(content);
		Elements eles = doc.select("textarea");
		for (Element ele : eles) {
			ele.remove();
		}
		System.out.println(doc.body());
	}
}
