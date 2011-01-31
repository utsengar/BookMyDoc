package beans;

public class Appointment {
	int ref;
	int doc_id;
	int pat_id;
	String feedback;
	String prescription;
	String slot, date;

	public int getref() {
		return ref;
	}

	public int getdoc_id() {
		return doc_id;
	}

	public int getpat_id() {
		return pat_id;
	}

	public String getfeedback() {
		return feedback;
	}

	public String getprescription() {
		return prescription;
	}

	public String getslot() {
		return slot;
	}

	public String getdate() {
		return date;
	}

	public void setref(int newref) {
		this.ref = newref;
	}

	public void setdoc_id(int newdoc_id) {
		this.doc_id = newdoc_id;
	}

	public void setpat_id(int newpat_id) {
		this.pat_id = newpat_id;
	}

	public void setfeedback(String newfeedback) {
		this.feedback = newfeedback;
	}

	public void setprescription(String newprescription) {
		this.prescription = newprescription;
	}

	public void setslot(String newslot) {
		this.slot = newslot;
	}

	public void setdate(String newdate) {
		this.date = newdate;
	}

}
