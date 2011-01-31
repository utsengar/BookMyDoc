package beans;

public class Mail {
	int id;
	int to, from;
	String data, subject;

	public int getto() {
		return to;
	}

	public int getid() {
		return id;
	}

	public int getfrom() {
		return from;
	}

	public String getdata() {
		return data;
	}

	public String getsubject() {
		return subject;
	}

	public void setdata(String newdata) {
		this.data = newdata;
	}

	public void setsubject(String newsubject) {
		this.subject = newsubject;
	}

	public void setto(int newto) {
		this.to = newto;
	}

	public void setfrom(int newfrom) {
		this.from = newfrom;
	}

	public void setid(int newid) {
		this.id = newid;
	}

}
