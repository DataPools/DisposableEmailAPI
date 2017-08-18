package me.datapools.disposableemailapi;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class EmailAddress {
	private String name;
	private String url;
	private ArrayList<Email> emailarray;
	public EmailAddress(String name) throws IOException {
		this.name = name.replace("@trashcanmail.com", "").trim();
		url = "https://trashcanmail.com/en/mailbox/"+name;
		refresh();
	}
	public void refresh() throws IOException {
		emailarray = new ArrayList<Email>();
		Document page = Jsoup.connect(url).validateTLSCertificates(false).get();
		Element emailslist = page.select("#msgs_list_table").first().select("tbody").first();
		Elements messages = emailslist.select("tr");
		for(int i=0;i<messages.size();i++) {
			Element indemail = messages.get(i);
			String subjectline = indemail.select("td").get(2).text();
			String link = indemail.select(".email").select("a").attr("href");
			String sender = indemail.select(".email").select("a").text().split("<")[1].split(">")[0];
			Email theemail = new Email(link,this,subjectline,sender);
			emailarray.add(theemail);
		}
	}
	public ArrayList<Email> getEmails() {
		return emailarray;
	}
	public String toString() {
		return name+"@trashcanmail.com";
	}
	public ArrayList<Email> getBySubject(String subject) {
		ArrayList<Email> toreturn = new ArrayList<Email>();
		for(int i=0;i<emailarray.size();i++) {
			if(emailarray.get(i).getSubject().contains(subject)) {
				toreturn.add(emailarray.get(i));
			}
		}
		return toreturn;
	}
	public ArrayList<Email> getBySender(String sender) {
		ArrayList<Email> toreturn = new ArrayList<Email>();
		for(int i=0;i<emailarray.size();i++) {
			if(emailarray.get(i).getSender().contains(sender)) {
				toreturn.add(emailarray.get(i));
			}
		}
		return toreturn;
	}
	public ArrayList<Email> getByText(String query) {
		ArrayList<Email> toreturn = new ArrayList<Email>();
		for(int i=0;i<emailarray.size();i++) {
			if(emailarray.get(i).getContentAsText().contains(query)) {
				toreturn.add(emailarray.get(i));
			}
		}
		return toreturn;
	}

}
