package me.datapools.disposableemailapi;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Email {
	private String subject;
	private String content;
	private String contenttext;
	private String sender;
	private String url;
	private EmailAddress belongs;
	public Email(String href, EmailAddress belongs, String subject, String sender) throws IOException {
		this.belongs = belongs;
		this.subject = subject;
		this.sender = sender;
		this.url = "https://trashcanmail.com"+href;
		loadContent();
	}
	public void loadContent() throws IOException {
		Document page = Jsoup.connect(url).validateTLSCertificates(false).get();
		Element bodycontent = page.select("#msg_text_html").first();
		this.content = bodycontent.html();
		this.contenttext = bodycontent.text();
	}
	public String getContent() {
		return content;
	}
	public String getURL() {
		return url;
	}
	public String getSender() {
		return sender;
	}
	public String getSubject() {
		return subject;
	}
	public String getBelongs() {
		return belongs.toString();
	}
	public String getContentAsText() {
		return contenttext;
	}
	

}
