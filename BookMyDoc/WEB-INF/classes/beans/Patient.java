package beans;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class Patient {
	int id;
	int age;
	String password;
	String name;
	dBase obj;

	public Patient() {
		obj = new dBase();
	}

	public void setName(String newname) {
		setname(newname);
	}
	
	public void setname(String newname) {
		name = newname;
	}

	public void setAge(int newage) {
		this.setage(newage);
	}
	public void setage(int newage) {
		this.age = newage;
	}

	public void setid(int newid) {
		this.id = newid;
	}

	public boolean Login(int chk_id, String chk_password) throws Exception {
		obj.createConn();
		String query = "Select * from patient where id=" + chk_id;
		if (obj.getRowCount(query) == 0) {
			return false;
		} else {
			ResultSet rs = obj.executeQuery(query);
			rs.next();
			String corr_pass = rs.getString("password");
			if (corr_pass.equals(chk_password)) {
				obj.closeConn();
				return true;
			} else {
				obj.closeConn();
				return false;
			}
		}
	}

	public void SetPassword(String password) throws SQLException,
			ClassNotFoundException {
		obj.createConn();
		String query = "UPDATE patient SET password='" + password
				+ "',WHERE id=" + id;
		obj.executeQuery(query);
		obj.closeConn();
	}

	public int Register(String password1, int age1, String name1)
			throws Exception {
		obj.createConn();
		Random gen = new Random();
		int id1 = ((gen.nextInt(9999)) + 10000);

		String regquery = "SELECT id FROM patient";
		ResultSet rs;
		rs = obj.executeQuery(regquery);

		outer: while (rs.next()) {
			int chk = rs.getInt("id");
			if (chk == id1) {
				id1 = (gen.nextInt(9999) + 10000);
				rs = obj.executeQuery(regquery);
				continue outer;
			}
		}

		String insertquery = "INSERT INTO patient (id, password, name, age) VALUES ("
				+ id1 + ",'" + password1 + "','" + name1 + "'," + age1 + ")";
		obj.doUpdate(insertquery);
		obj.closeConn();
		return id1;
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

	public void GiveFeedback(String feedback, int num) throws Exception {
		obj.createConn();
		String checkquery = "select * from appointment where id=" + num;
		if (obj.getRowCount(checkquery) != 0) {
			String mergequery = "merge into appointment using (select * from appointment) on id="
					+ num
					+ "when matched then update set prescription="
					+ feedback
					+ "when not matched insert (prescription) values ("
					+ feedback + ")";
			obj.executeQuery(mergequery);
			obj.closeConn();
		}
	}

	public int BookAppointment(String date1, String slot, int docid,
			String problemtype) throws SQLException, ClassNotFoundException {
		obj.createConn();
		Random gen = new Random();
		int ref = (gen.nextInt(9999));
		String query = "Select * from appointment";
		ResultSet rs;
		rs = obj.executeQuery(query);
		outer: while (rs.next()) {
			int chk = rs.getInt("id");
			if (chk == ref) {
				ref = (gen.nextInt(9999));
				rs = obj.executeQuery(query);
				continue outer;
			}
		}

		String insertquery = "Insert into appointment (id,doc_id,pat_id,problem_type,slot,date1) values ("
				+ ref
				+ ","
				+ docid
				+ ","
				+ id
				+ ","
				+ problemtype
				+ ","
				+ slot
				+ ",(TO_DATE)('" + date1 + "'))";
		obj.executeQuery(insertquery);
		obj.closeConn();
		return ref;
	}

	public boolean CheckAvailabity(String date1, String slot, int docid)
			throws Exception {
		obj.createConn();
		String checkquery = "Select * from appointment where doc_id=" + docid
				+ " and date1='" + date1 + "' and slot='" + slot + "'";
		if (obj.getRowCount(checkquery) == 0)
			return true;
		else
			return false;
	}

	public ResultSet SearchDoctors(String problemtype) throws SQLException,
			ClassNotFoundException {
		obj.createConn();
		String query = "Select name,id,type from doctor where type='"
				+ problemtype + "'";
		System.out.println(query);
		ResultSet rs = obj.executeQuery(query);

		return rs;
	}

	public ResultSet CheckSentMail() throws SQLException,
			ClassNotFoundException {
		obj.createConn();
		String query = "Select * from mail where from1=" + id;
		ResultSet rs = obj.executeQuery(query);
		rs.next();
		
		@SuppressWarnings("unused")
		int id2 = rs.getInt("id");

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
				+ num + "," + id + "," + to + "," + subject + "," + data + ")";
		obj.executeQuery(insertquery);
		obj.closeConn();
	}

}
