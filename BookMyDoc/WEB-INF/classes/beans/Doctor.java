package beans;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class Doctor {
	private int id;
	private int pin;
	private int age;
	private String type;
	private String name;
	private String password;
	private dBase obj;

	public Doctor() {
		obj = new dBase();
	}

	public int getid() {
		return id;
	}

	public String getname() {
		return name;
	}

	public void setname(String newname) {
		this.name = newname;
	}

	public void setage(int newage) {
		this.setAge(newage);
	}

	public void settype(String newtype) {
		this.setType(newtype);
	}

	public void setid(int newid) {
		this.id = newid;
	}

	public String getpassword(int doc_id) throws SQLException,
			ClassNotFoundException {
		obj.createConn();
		String query = "Select * from doctor where id=" + doc_id;
		ResultSet rs;
		String password1 = null;
		rs = obj.executeQuery(query);
		while (rs.next()) {
			password1 = rs.getString("password");
			return password1;
		}
		obj.createConn();
		return password1;
	}

	public void setpassword(String newpwd) throws SQLException,
			ClassNotFoundException {
		obj.createConn();
		String query = "Update doctor set password=" + newpwd + "where id="
				+ id;
		obj.executeQuery(query);
		obj.closeConn();
	}

	public boolean login(int chk_id, String chk_password) throws Exception {
		obj.createConn();
		String corr_pass = getpassword(chk_id);
		if (corr_pass.equals(chk_password)) {
			obj.closeConn();
			return true;
		} else {
			obj.closeConn();
			return false;
		}
	}

	public ResultSet CheckSentMail() throws SQLException,
			ClassNotFoundException {
		obj.createConn();
		String query = "Select * from mail where from1=" + id;
		ResultSet rs = obj.executeQuery(query);
		rs.next();
		obj.closeConn();
		return rs;
	}

	public ResultSet CheckInbox() throws SQLException, ClassNotFoundException {
		obj.createConn();
		String query = "Select * from mail where to1=" + id;
		ResultSet rs = obj.executeQuery(query);
		rs.next();
		obj.closeConn();
		return rs;
	}

	public void SendMail(String data, int to, String subject)
			throws SQLException, ClassNotFoundException {
		obj.createConn();
		Random gen = new Random();
		int id1 = (gen.nextInt(9999));
		String query = "Select * from mail";
		ResultSet rs;
		rs = obj.executeQuery(query);
		outer: while (rs.next()) {
			int chk = rs.getInt("id");
			if (chk == id1) {
				id1 = (gen.nextInt(9999));
				rs = obj.executeQuery(query);
				continue outer;
			}
		}
		int num = id1;
		String insertquery = "Insert into mail (id,from1,to1,subject,data) values ("
				+ num
				+ ","
				+ id
				+ ","
				+ to
				+ ",'"
				+ subject
				+ "','"
				+ data
				+ "')";
		obj.executeQuery(insertquery);
		obj.closeConn();
	}

	public int register(int pin1, String password1, int age1, String name1,
			String type1) throws Exception {
		obj.createConn();
		String pinvalidate = "Select * from doctor where pin=" + pin1;
		if (obj.getRowCount(pinvalidate) == 0) {
			return 0;
		} else { // pin=pin1;
			// age=age1;
			// name=name1;
			// password=password1;
			// type=type1;
			Random gen = new Random();
			int id1 = (gen.nextInt(9999));
			String query = "Select * from doctor";
			ResultSet rs;
			rs = obj.executeQuery(query);
			outer: while (rs.next()) {
				int chk = rs.getInt("id");
				if (chk == id1) {
					id1 = (gen.nextInt(9999));
					rs = obj.executeQuery(query);
					continue outer;
				}
			}
			// id=id1;
			String updatequery = "update doctor set id=" + id1 + ",name='"
					+ name1 + "',age=" + age1 + ",password='" + password1
					+ "',type='" + type1 + "'where pin=" + pin1;
			obj.doUpdate(updatequery);
			obj.closeConn();
			return id;
		}
	}

	public ResultSet ViewAppHistory() throws SQLException,
			ClassNotFoundException {
		obj.createConn();
		String searchquery = " Select * from appointment where doc_id=" + id;
		ResultSet rs = obj.executeQuery(searchquery);
		rs.next();
		obj.closeConn();
		return rs;
	}

	public void ApplyForLeave(String date1) throws SQLException,
			ClassNotFoundException {
		obj.createConn();
		String searchquery = " Select * from appointment where doc_id=" + id
				+ "and date1=(to_date)'" + date1 + "'";
		ResultSet rs = obj.executeQuery(searchquery);
		while (rs.next()) {
			int id1 = rs.getInt("pat_id");
			String data = "Your appointment for the date"
					+ date1
					+ "has been cancelled as the concerned doctor is unavailable! Kindly book for sum other day!";
			String subject = "AUTO GENERATED MAIL:Cancellation of appointment ! !";
			SendMail(data, id1, subject);
		}
		String updatequery = "Update table appointment set pat_id=0 where slot IN ('A','B','C','D')";
		obj.executeQuery(updatequery);
		obj.closeConn();
	}

	public boolean UpdateAppointment(String date1, String slot1)
			throws Exception {
		obj.createConn();
		String searchquery = "Select * from appointment where doc_id=" + id
				+ "and date1=(to_date)'" + date1 + "' and slot=" + slot1;
		if (obj.getRowCount(searchquery) != 0) {
			String deletequery = "Delete from appointment where doc_id=" + id
					+ "and date1=(to_date)'" + date1 + "' and slot='" + slot1
					+ "'";
			obj.executeQuery(deletequery);
			obj.closeConn();
			return true;
		} else
			return false;
	}

	public void GivePrescription(String prescription, int num) throws Exception {
		obj.createConn();
		String checkquery = "select * from appointment where id=" + num;
		if (obj.getRowCount(checkquery) != 0) {
			String mergequery = "merge into appointment using (select * from appointment) on id="
					+ num
					+ "when matched then update set prescription="
					+ prescription
					+ "when not matched insert (prescription) values ("
					+ prescription + ")";
			obj.executeQuery(mergequery);
			obj.closeConn();
		}
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getAge() {
		return age;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}

	public int getPin() {
		return pin;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}
	
	public int getId() {
		return id;
	}

}
