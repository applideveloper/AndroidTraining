package jp.wishmatch.serializableassignment;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class User {
	private int id;
	private String name;
	private int age;
	private String keyword;
	private Status status;
	private JoinDate joinDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public static class Status {
		private Date mPostedDate;
		private String mText;

		public Date getPostedDate() {
			return mPostedDate;
		}

		public void setPostedDate(Date date) {
			mPostedDate = date;
		}

		public String getText() {
			return mText;
		}

		public void setText(String text) {
			mText = text;
		}
	}

	public static class JoinDate {
		private String year;
		private String month;
		private String date;

		public String getYear() {
			return year;
		}

		public void setYear(String year) {
			this.year = year;
		}

		public String getMonth() {
			return month;
		}

		public void setMonth(String month) {
			this.month = month;
		}

		public String getDate() {
			return date;
		}

		public void setDate(String date) {
			this.date = date;
		}
	}

	public JoinDate getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(JoinDate joindate) {
		this.joinDate = joindate;
	}

	public static User parseJson(JSONObject userObj) {
		User u = new User();
		try {
			u.setId(userObj.optInt("id"));
			u.setName(userObj.optString("name"));
			u.setAge(userObj.optInt("age"));
			u.setKeyword(userObj.optString("keyword"));

			// joinDateのパース
			JSONObject joinDateObj = new JSONObject(userObj.optString("joinDate"));
			User.JoinDate joinDate = new JoinDate();
			joinDate.setYear(joinDateObj.optString("year"));
			joinDate.setMonth(joinDateObj.optString("month"));
			joinDate.setDate(joinDateObj.optString("date"));
			u.setJoinDate(joinDate);

			// statusのパース
			JSONObject statusObj = new JSONObject(userObj.getString("status"));
			User.Status status = new User.Status();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			status.setPostedDate(simpleDateFormat.parse(statusObj.optString("postedTime")));
			status.setText(statusObj.optString("text"));
			u.setStatus(status);

		} catch (Exception e) {
			Log.v("exception", e.toString());
		}
		return u;
	}

	public static String toJson(User u) {
		JSONObject json = new JSONObject();
		try {
			json.put("id", u.getId());
			json.put("name", u.getName());
			json.put("age", u.getAge());
			json.put("keyword", u.getKeyword());

			JSONObject joinDate = new JSONObject();
			joinDate.put("year", u.getJoinDate().getYear());
			joinDate.put("month", u.getJoinDate().getMonth());
			joinDate.put("date", u.getJoinDate().getDate());
			json.put("joinDate", joinDate);

			JSONObject status = new JSONObject();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			status.put("postedTime", simpleDateFormat.format(u.getStatus().getPostedDate()));
			status.put("text", u.getStatus().getText());
			json.put("status", status);
			return json.toString();
		} catch (JSONException e) {
			Log.v("exception", e.toString());
			return "";
		}

	}
}
