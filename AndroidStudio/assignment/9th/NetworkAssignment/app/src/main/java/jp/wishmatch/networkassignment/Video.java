package jp.wishmatch.networkassignment;

import android.util.Log;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class Video {
	private static final int PARSED_NONE = 0;
	private static final int PARSED_TITLE = 1;
	private static final int PARSED_AUTHOR = 2;

	private String title;
	private String author;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public static List<Video> parseXml(InputStream is) {
		List<Video> list = new ArrayList<Video>();

		XmlPullParser xmlPullParser = Xml.newPullParser();

		int parsedState = 0;

		try {
			xmlPullParser.setInput(is, "UTF-8");
			int eventType = xmlPullParser.getEventType();
			String title = "";
			String author = "";

			while (eventType != XmlPullParser.END_DOCUMENT) {
				//titleの取得
				if(eventType == XmlPullParser.START_TAG && xmlPullParser.getName().equals("title") && xmlPullParser.getAttributeName(0).equals("type") && xmlPullParser.getAttributeValue(0).equals("text")) {
					title = xmlPullParser.nextText();
					parsedState = PARSED_TITLE;
				}
				//authorの取得
				if(parsedState == PARSED_TITLE && eventType == XmlPullParser.START_TAG && xmlPullParser.getName().equals("name")) {
					author = xmlPullParser.nextText();
					parsedState = PARSED_AUTHOR;
				}

				if (parsedState == PARSED_AUTHOR) {
					Video video = new Video();
					video.setTitle(title);
					video.setAuthor(author);
					Log.v("test-video", video.getTitle() + "/" + video.getAuthor());
					list.add(video);

					// 初期化
					parsedState = PARSED_NONE;
					title = "";
					author = "";
				}
				eventType = xmlPullParser.next();
			}

		} catch (XmlPullParserException e) {
			Log.v("test", e.toString());
		} catch (IOException e) {
			Log.v("test", e.toString());
		}

		// 最初の要素は下なので除外
		// {"author":"Youtube", "title": "Top Rated"}
		// {"author":"RinpaEshidan", "title":"ようこそ、Youtube Japanへ"}
		list.remove(0);
		list.remove(0);
		return list;
	}
}

//public class Video {
//	private static final int PARSED_NONE = 0;
//	private static final int PARSED_TITLE = 1;
//	private static final int PARSED_AUTHOR = 2;
//
//	private String title;
//	private String author;
//
//	public String getTitle() {
//		return title;
//	}
//
//	public void setTitle(String title) {
//		this.title = title;
//	}
//
//	public String getAuthor() {
//		return author;
//	}
//
//	public void setAuthor(String author) {
//		this.author = author;
//	}
//
//	public static List<Video> parseXml(InputStream is){
//		List<Video> list = new ArrayList<Video>();
//
//		XmlPullParser xmlPullParser = Xml.newPullParser();
//
//		int parsedState = 0;
//		try {
//			xmlPullParser.setInput(is, "UTF-8");
//			int eventType = xmlPullParser.getEventType();
//			String title = "";
//			String author = "";
//			while(eventType != XmlPullParser.END_DOCUMENT) {
//				//titleの取得
//				if(eventType == XmlPullParser.START_TAG && xmlPullParser.getName().equals("title") && xmlPullParser.getAttributeName(0).equals("type") && xmlPullParser.getAttributeValue(0).equals("text")) {
//					title = xmlPullParser.nextText();
//					parsedState = PARSED_TITLE;
//				}
//				//authorの取得
//				if(parsedState == PARSED_TITLE && eventType == XmlPullParser.START_TAG && xmlPullParser.getName().equals("name")) {
//					author = xmlPullParser.nextText();
//					parsedState = PARSED_AUTHOR;
//				}
//
//				if(parsedState == PARSED_AUTHOR) {
//					Video video = new Video();
//					video.setTitle(title);
//					video.setAuthor(author);
//					Log.v("test-video", video.getTitle() + " / " + video.getAuthor() );
//
//					list.add(video);
//
//					//初期化
//					parsedState = PARSED_NONE;
//					title = "";
//					author = "";
//				}
//				eventType = xmlPullParser.next();
//			}
//		} catch(XmlPullParserException e) {
//			Log.v("test", e.toString());
//		} catch(IOException e) {
//			Log.v("test", e.toString());
//		}
//
//		//最初の要素は下なので除外
//		// {"author":"YouTube","title":"Top Rated"},
//		// {"author":"RinpaEshidan","title":"ようこそ、YouTube Japanへ"}
//		list.remove(0);
//		list.remove(0);
//		return list;
//	}
//}
