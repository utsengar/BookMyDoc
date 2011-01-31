package beans;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class Admin {
	int id;
	String password;

	dBase dbConnection;

	public Admin() {
		dbConnection = new dBase();

	}

	public boolean login(int chk_id, String chk_password) throws Exception {
		dbConnection.createConn();
		String query = "Select * from admin where id=" + chk_id;

		if (dbConnection.getRowCount(query) == 0) {
			dbConnection.closeConn();
			return false;
		} else {
			ResultSet results = dbConnection.executeQuery(query);
			results.next();
			String corr_pass = results.getString("password");
			if (corr_pass.equals(chk_password)) {
				dbConnection.closeConn();
				return true;
			} else {
				dbConnection.closeConn();
				return false;
			}
		}

	}

	public void setpassword(String newpwd) throws SQLException,
			ClassNotFoundException {
		dbConnection.createConn();
		String query = "Update admin set password=" + newpwd + "where id=" + id;
		dbConnection.executeQuery(query);
		dbConnection.closeConn();
	}

	public int CreateDoc() throws SQLException, ClassNotFoundException {
		dbConnection.createConn();
		Random gen = new Random();
		int pin = (gen.nextInt(9999));
		String query = "Select pin from doctor";
		ResultSet rs;
		rs = dbConnection.executeQuery(query);
		outer: while (rs.next()) {
			int chk = rs.getInt("pin");
			if (chk == pin) {
				pin = (gen.nextInt(9999));
				rs = dbConnection.executeQuery(query);
				continue outer;
			}
		}
		String pinquery = "Insert into doctor (pin) values (" + pin + ")";
		dbConnection.executeQuery(pinquery);
		dbConnection.closeConn();
		return pin;
	}

	public ResultSet ShowDoc() throws SQLException, ClassNotFoundException {
		dbConnection.createConn();
		String query = "Select id,name,age from doctor";
		ResultSet rs = dbConnection.executeQuery(query);
		rs.next();
		dbConnection.closeConn();
		return rs;
	}

	public ResultSet ShowPatient() throws SQLException, ClassNotFoundException {
		dbConnection.createConn();
		String query = "Select id,name,age from patient";
		ResultSet rs = dbConnection.executeQuery(query);
		rs.next();
		dbConnection.closeConn();
		return rs;
	}

	public boolean DeleteDoc(int doc_id) throws Exception {
		dbConnection.createConn();
		String query = "Select * from doctor where id=" + doc_id;
		if (dbConnection.getRowCount(query) != 0) {
			String deletequery = "Delete from doctor where id=" + doc_id;
			dbConnection.executeQuery(deletequery);
			dbConnection.closeConn();
			return true;
		} else
			return false;
	}

	public boolean DeletePatient(int pat_id) throws Exception {
		dbConnection.createConn();
		String query = "Select * from patient where id=" + pat_id;
		if (dbConnection.getRowCount(query) != 0) {
			String deletequery = "Delete from patient where id=" + pat_id;
			dbConnection.executeQuery(deletequery);
			dbConnection.createConn();
			return true;
		} else
			return false;
	}
}