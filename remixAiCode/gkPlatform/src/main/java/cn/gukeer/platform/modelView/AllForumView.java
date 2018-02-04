package cn.gukeer.platform.modelView;

import java.io.Serializable;

public class AllForumView  {
   private String id;
   private String title;
   private String create_time;
   private String view_count;
   private String reply_count;
   private String last_reply_time;
   private String big_title;
   private String uid;
   private String nickname;
   private String path;
   private String url;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public String getView_count() {
		return view_count;
	}

	public void setView_count(String view_count) {
		this.view_count = view_count;
	}

	public String getReply_count() {
		return reply_count;
	}

	public void setReply_count(String reply_count) {
		this.reply_count = reply_count;
	}

	public String getLast_reply_time() {
		return last_reply_time;
	}

	public void setLast_reply_time(String last_reply_time) {
		this.last_reply_time = last_reply_time;
	}

	public String getBig_title() {
		return big_title;
	}

	public void setBig_title(String big_title) {
		this.big_title = big_title;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
