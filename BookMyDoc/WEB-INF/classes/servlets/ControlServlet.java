package servlets;

import javax.servlet.http.HttpServlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletConfig;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.*;
import java.util.ArrayList;
import beans.*;

/**
 * Servlet Class
 * 
 * @web.servlet name="Control" display-name="Name for Control"
 *              description="Description for Control"
 * @web.servlet-mapping url-pattern="/Control"
 * @web.servlet-init-param name="A parameter" value="A value"
 */
public class ControlServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6877908094225562455L;
	ServletConfig sc;

	public void init(ServletConfig config) throws ServletException {
		sc = config;
	}

	public void destroy() {

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String page = req.getParameter("page");
		if (page.equals("admin_login.jsp")
				|| page.equals("fail_admin_login.jsp")) {
			Admin A = new Admin();
			int id1 = Integer.parseInt(req.getParameter("id"));
			String password1 = req.getParameter("password");

			try {
				if (A.login(id1, password1)) {
					@SuppressWarnings("unused")
					Integer id = new Integer(id1);
					HttpSession session = req.getSession(true);
					String s = session.getId();
					session.setAttribute("key", s);
					RequestDispatcher rd = req
							.getRequestDispatcher("admin_home.jsp");
					rd.forward(req, resp);
				} else {
					RequestDispatcher rd = req
							.getRequestDispatcher("fail_admin_login.jsp");
					rd.forward(req, resp);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		else if (page.equals("doc_login.jsp")
				|| page.equals("fail_doc_login.jsp")) {
			Doctor D = new Doctor();
			int id1 = Integer.parseInt(req.getParameter("id"));
			String password1 = req.getParameter("password");

			try {
				if (D.login(id1, password1)) {
					@SuppressWarnings("unused")
					Integer id = new Integer(id1);
					HttpSession session = req.getSession(true);
					String s = session.getId();
					session.setAttribute("key", s);
					RequestDispatcher rd = req
							.getRequestDispatcher("doc_home.jsp");
					rd.forward(req, resp);
				} else {
					RequestDispatcher rd = req
							.getRequestDispatcher("fail_doc_login.jsp");
					rd.forward(req, resp);
				}

			} catch (Exception e) {

				e.printStackTrace();
			}
		}

		else if (page.equals("pat_login.jsp")
				|| page.equals("fail_pat_login.jsp")) {
			Patient P = new Patient();
			int id1 = Integer.parseInt(req.getParameter("id"));
			String password1 = req.getParameter("password");

			try {
				if (P.Login(id1, password1)) {
					@SuppressWarnings("unused")
					Integer id = new Integer(id1);
					HttpSession session = req.getSession(true);
					String s = session.getId();
					session.setAttribute("key", s);
					RequestDispatcher rd = req
							.getRequestDispatcher("pat_home.jsp");
					rd.forward(req, resp);
				} else {
					RequestDispatcher rd = req
							.getRequestDispatcher("fail_pat_login.jsp");
					rd.forward(req, resp);
				}

			} catch (Exception e) {

				e.printStackTrace();
			}
		}

		else if (page.equals("doc_register.jsp")) {
			Doctor D = new Doctor();
			int age1 = Integer.parseInt(req.getParameter("age"));
			String password1 = req.getParameter("password");
			int pin1 = Integer.parseInt(req.getParameter("pin"));
			String name1 = req.getParameter("name");
			String type1 = req.getParameter("type");

			try {
				int id1 = D.register(pin1, password1, age1, name1, type1);
				if (id1 != 0) {
					Integer id = new Integer(id1);
					req.setAttribute("user", id);
					RequestDispatcher rd = req
							.getRequestDispatcher("doc_new_login.jsp");
					rd.forward(req, resp);
				} else {
					RequestDispatcher rd = req
							.getRequestDispatcher("invalid_doc.jsp");
					rd.forward(req, resp);
				}

			} catch (Exception e) {

				e.printStackTrace();
			}
		}

		else if (page.equals("pat_register.jsp")) {
			Patient P = new Patient();
			int age1 = Integer.parseInt(req.getParameter("age"));
			String password1 = req.getParameter("password");
			String name1 = req.getParameter("name");

			try {
				int id1 = P.Register(password1, age1, name1);
				if (id1 != 0) {
					Integer id = new Integer(id1);
					req.setAttribute("user", id);
					RequestDispatcher rd = req
							.getRequestDispatcher("pat_new_login.jsp");
					rd.forward(req, resp);
				} else {
					RequestDispatcher rd = req
							.getRequestDispatcher("invalid_pat.jsp");
					rd.forward(req, resp);
				}

			} catch (Exception e) {
				// 
				e.printStackTrace();
			}
		}

		else if (page.equals("create_doc.jsp")) {
			Admin A = new Admin();
			HttpSession session = req.getSession();
			String s1 = (String) session.getAttribute("key");
			// String s2=session.getId();
			if (s1 != null) {
				try {
					int pin1 = A.CreateDoc();
					Integer pin = new Integer(pin1);
					req.setAttribute("pin", pin);
					RequestDispatcher rd = req
							.getRequestDispatcher("doc_created.jsp");
					rd.forward(req, resp);
				} catch (SQLException e) {
					// 
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// 
					e.printStackTrace();
				}
			} else {
				resp.sendRedirect("admin_login.jsp");
			}
		}

		else if (page.equals("change_admin_pass.jsp")) {
			Admin A = new Admin();
			HttpSession session = req.getSession();
			String s1 = (String) session.getAttribute("key");
			// String s2=session.getId();
			if (s1 != null) {
				try {
					String newpwd = req.getParameter("newpassword");
					A.setpassword(newpwd);
					RequestDispatcher rd = req
							.getRequestDispatcher("admin_login");
					rd.forward(req, resp);
				} catch (SQLException e) {
					// 
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// 
					e.printStackTrace();
				}
			} else {
				resp.sendRedirect("admin_login.jsp");
			}
		}

		else if (page.equals("delete_doc.jsp")) {
			Admin A = new Admin();
			HttpSession session = req.getSession();
			String s1 = (String) session.getAttribute("key");
			// String s2=session.getId();
			if (s1 != null) {
				try {
					int id1 = Integer.parseInt(req.getParameter("deleteid"));
					if (A.DeleteDoc(id1)) {
						Integer id = new Integer(id1);
						req.setAttribute("deletedid", id);
						req.setAttribute("type", "Doctor");
						RequestDispatcher rd = req
								.getRequestDispatcher("delete_success.jsp");
						rd.forward(req, resp);
					} else {
						RequestDispatcher rd = req
								.getRequestDispatcher("delete_fail.jsp");
						rd.forward(req, resp);
					}
				} catch (SQLException e) {
					// 
					e.printStackTrace();
				} catch (ServletException e) {
					// 
					e.printStackTrace();
				} catch (IOException e) {
					// 
					e.printStackTrace();
				} catch (Exception e) {
					// 
					e.printStackTrace();
				}
			} else {
				resp.sendRedirect("admin_login.jsp");
			}
		}

		else if (page.equals("delete_pat.jsp")) {
			Admin A = new Admin();
			HttpSession session = req.getSession();
			String s1 = (String) session.getAttribute("key");
			// String s2=session.getId();
			if (s1 != null) {
				try {
					int id1 = Integer.parseInt(req.getParameter("deleteid"));
					if (A.DeletePatient(id1)) {
						Integer id = new Integer(id1);
						req.setAttribute("deletedid", id);
						req.setAttribute("type", "Patient");
						RequestDispatcher rd = req
								.getRequestDispatcher("delete_success.jsp");
						rd.forward(req, resp);
					} else {
						RequestDispatcher rd = req
								.getRequestDispatcher("delete_fail.jsp");
						rd.forward(req, resp);
					}
				} catch (SQLException e) {
					// 
					e.printStackTrace();
				} catch (ServletException e) {
					// 
					e.printStackTrace();
				} catch (IOException e) {
					// 
					e.printStackTrace();
				} catch (Exception e) {
					// 
					e.printStackTrace();
				}
			} else {
				resp.sendRedirect("admin_login.jsp");
			}
		}

		else if (page.equals("book_apps.jsp")) {
			Patient P = new Patient();
			HttpSession session = req.getSession();
			String s1 = (String) session.getAttribute("key");
			// String s2=session.getId();
			if (s1 != null) {
				try {
					int docid1 = Integer.parseInt(req.getParameter("docid"));
					String date1 = req.getParameter("date1");
					String problemtype1 = req.getParameter("type");
					String slot1 = req.getParameter("slot");
					int ref1 = P.BookAppointment(date1, slot1, docid1,
							problemtype1);
					Integer ref = new Integer(ref1);
					req.setAttribute("reference", ref);
					RequestDispatcher rd = req
							.getRequestDispatcher("appointment_confirm.jsp");
					rd.forward(req, resp);

				} catch (SQLException e) {
					// 
					e.printStackTrace();
				} catch (Exception e) {
					// 
					e.printStackTrace();
				}
			} else {
				resp.sendRedirect("pat_login.jsp");
			}
		}

		else if (page.equals("docs_list.jsp")) {
			Patient P = new Patient();
			HttpSession session = req.getSession();
			String s1 = (String) session.getAttribute("key");
			// String s2=session.getId();
			if (s1 != null) {
				try {
					int docid1 = Integer.parseInt(req.getParameter("doc_id"));
					System.out.println(docid1);
					String date1 = req.getParameter("firstinput");
					System.out.println(date1);

					// String problemtype1=req.getParameter("type");
					String slot1 = req.getParameter("slot");
					if (P.CheckAvailabity(date1, slot1, docid1)) {
						Integer docid = new Integer(docid1);
						req.setAttribute("firstinput1", date1);
						// req.setAttribute("type",problemtype1);
						req.setAttribute("slot2", slot1);
						req.setAttribute("doc_id1", docid);
						RequestDispatcher rd = req
								.getRequestDispatcher("book_apps.jsp");
						rd.forward(req, resp);
					} else {
						RequestDispatcher rd = req
								.getRequestDispatcher("unavailable_doc.jsp");
						rd.forward(req, resp);
					}
				} catch (SQLException e) {
					// 
					e.printStackTrace();
				} catch (Exception e) {
					// 
					e.printStackTrace();
				}
			} else {
				resp.sendRedirect("pat_login.jsp");
			}
		}

		else if (page.equals("change_pat_pass.jsp")) {
			Patient P = new Patient();
			HttpSession session = req.getSession();
			String s1 = (String) session.getAttribute("key");
			// String s2=session.getId();
			if (s1 != null) {
				try {
					String newpwd = req.getParameter("newpassword");
					P.SetPassword(newpwd);
					RequestDispatcher rd = req
							.getRequestDispatcher("pat_login.jsp");
					rd.forward(req, resp);
				} catch (SQLException e) {
					// 
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// 
					e.printStackTrace();
				}
			} else {
				resp.sendRedirect("pat_login.jsp");
			}
		}

		else if (page.equals("change_doc_pass.jsp")) {
			Doctor D = new Doctor();
			HttpSession session = req.getSession();
			String s1 = (String) session.getAttribute("key");
			// String s2=session.getId();
			if (s1 != null) {
				try {
					String newpwd = req.getParameter("newpassword");
					D.setpassword(newpwd);
					RequestDispatcher rd = req
							.getRequestDispatcher("doc_login.jsp");
					rd.forward(req, resp);
				} catch (SQLException e) {
					// 
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// 
					e.printStackTrace();
				}
			} else {
				resp.sendRedirect("doc_login.jsp");
			}
		}

		else if (page.equals("compose_feedback.jsp")) {
			Patient P = new Patient();
			HttpSession session = req.getSession();
			String s1 = (String) session.getAttribute("key");
			// String s2=session.getId();
			if (s1 != null) {
				try {
					String data = req.getParameter("feedback");
					int ref = Integer.parseInt(req.getParameter("reference"));
					P.GiveFeedback(data, ref);
					RequestDispatcher rd = req
							.getRequestDispatcher("pat_home.jsp");
					rd.forward(req, resp);
				} catch (SQLException e) {
					// 
					e.printStackTrace();
				} catch (Exception e) {
					// 
					e.printStackTrace();
				}
			} else {
				resp.sendRedirect("pat_login.jsp");
			}
		}

		else if (page.equals("compose_prescription.jsp")) {
			Doctor D = new Doctor();
			HttpSession session = req.getSession();
			String s1 = (String) session.getAttribute("key");
			// String s2=session.getId();
			if (s1 != null) {
				try {
					String data = req.getParameter("prescription");
					int ref = Integer.parseInt(req.getParameter("reference"));
					D.GivePrescription(data, ref);
					RequestDispatcher rd = req
							.getRequestDispatcher("doc_home.jsp");
					rd.forward(req, resp);
				} catch (SQLException e) {
					// 
					e.printStackTrace();
				} catch (Exception e) {
					// 
					e.printStackTrace();
				}
			} else {
				resp.sendRedirect("doc_login.jsp");
			}
		}

		else if (page.equals("pat_compose_mail.jsp")) {
			Patient P = new Patient();
			HttpSession session = req.getSession();
			String s1 = (String) session.getAttribute("key");
			// String s2=session.getId();
			if (s1 != null) {
				try {
					String data = req.getParameter("data");
					String subject = req.getParameter("subject");
					int to = Integer.parseInt(req.getParameter("to"));
					P.SendMail(data, to, subject);
					RequestDispatcher rd = req
							.getRequestDispatcher("pat_home.jsp");
					rd.forward(req, resp);
				} catch (SQLException e) {
					// 
					e.printStackTrace();
				} catch (Exception e) {
					//
					e.printStackTrace();
				}
			} else {
				resp.sendRedirect("pat_login.jsp");
			}
		}

		else if (page.equals("doc_compose_mail.jsp")) {
			Doctor D = new Doctor();
			HttpSession session = req.getSession();
			String s1 = (String) session.getAttribute("key");
			// String s2=session.getId();
			if (s1 != null) {
				try {
					String data = req.getParameter("data");
					String subject = req.getParameter("subject");
					int to = Integer.parseInt(req.getParameter("to"));
					D.SendMail(data, to, subject);
					RequestDispatcher rd = req
							.getRequestDispatcher("doc_home.jsp");
					rd.forward(req, resp);
				} catch (SQLException e) {
					// 
					e.printStackTrace();
				} catch (Exception e) {
					// 
					e.printStackTrace();
				}
			} else {
				resp.sendRedirect("doc_login.jsp");
			}
		}

		else if (page.equals("apply_leave.jsp")) {
			Doctor D = new Doctor();
			HttpSession session = req.getSession();
			String s1 = (String) session.getAttribute("key");
			// String s2=session.getId();
			if (s1 != null) {
				try {
					String date1 = req.getParameter("date");
					D.ApplyForLeave(date1);
					RequestDispatcher rd = req
							.getRequestDispatcher("doc_home.jsp");
					rd.forward(req, resp);
				} catch (SQLException e) {
					// 
					e.printStackTrace();
				} catch (Exception e) {
					// 
					e.printStackTrace();
				}
			} else {
				resp.sendRedirect("doc_login.jsp");
			}
		}

		else if (page.equals("show_docs.jsp")) {
			Admin A = new Admin();
			HttpSession session = req.getSession();
			String s1 = (String) session.getAttribute("key");
			// String s2=session.getId();
			ResultSet rs;
			if (s1 != null) {
				try {
					rs = A.ShowDoc();
					ArrayList<Doctor> al = new ArrayList<Doctor>();
					while (rs.next()) {
						Doctor D = new Doctor();
						D.setid(rs.getInt("id"));
						D.setname(rs.getString("name"));
						D.settype(rs.getString("type"));
						al.add(D);
					}
					req.setAttribute("Result", al);
					RequestDispatcher rd = req
							.getRequestDispatcher("doc_list.jsp");
					rd.forward(req, resp);
				} catch (SQLException e) {
					// 
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// 
					e.printStackTrace();
				}

			} else {
				resp.sendRedirect("admin_login.jsp");
			}
		}

		else if (page.equals("show_pats.jsp")) {
			Admin A = new Admin();
			HttpSession session = req.getSession();
			String s1 = (String) session.getAttribute("key");
			// String s2=session.getId();

			ResultSet rs;
			if (s1 != null) {
				try {
					rs = A.ShowPatient();
					ArrayList<Patient> al = new ArrayList<Patient>();
					while (rs.next()) {
						Patient P = new Patient();
						P.setage(rs.getInt("age"));
						P.setid(rs.getInt("id"));
						P.setname(rs.getString("name"));
						al.add(P);
					}
					req.setAttribute("Result", al);
					RequestDispatcher rd = req
							.getRequestDispatcher("pat_list.jsp");
					rd.forward(req, resp);

				} catch (SQLException e) {
					// 
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// 
					e.printStackTrace();
				}

			} else {
				resp.sendRedirect("admin_login.jsp");
			}
		}

		else if (page.equals("show_pat_inbox.jsp")) {
			Patient P = new Patient();
			HttpSession session = req.getSession();
			String s1 = (String) session.getAttribute("key");
			// String s2=session.getId();

			ResultSet rs;
			if (s1 != null) {
				try {
					rs = P.CheckInbox();
					ArrayList<Mail> al = new ArrayList<Mail>();
					while (rs.next()) {
						Mail M = new Mail();
						M.setid(rs.getInt("id"));
						M.setto(rs.getInt("to1"));
						M.setfrom(rs.getInt("from1"));
						M.setdata(rs.getString("data"));
						al.add(M);
					}
					req.setAttribute("Result", al);
					RequestDispatcher rd = req
							.getRequestDispatcher("pat_inbox.jsp");
					rd.forward(req, resp);

				} catch (SQLException e) {
					// 
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					//
					e.printStackTrace();
				}

			} else {
				resp.sendRedirect("pat_login.jsp");
			}
		}

		else if (page.equals("show_doc_inbox.jsp")) {
			Doctor D = new Doctor();
			HttpSession session = req.getSession();
			String s1 = (String) session.getAttribute("key");
			// String s2=session.getId();

			ResultSet rs;
			if (s1 != null) {
				try {
					rs = D.CheckInbox();
					ArrayList<Mail> al = new ArrayList<Mail>();
					while (rs.next()) {
						Mail M = new Mail();
						M.setid(rs.getInt("id"));
						M.setto(rs.getInt("to1"));
						M.setfrom(rs.getInt("from1"));
						M.setdata(rs.getString("data"));
						al.add(M);
					}
					req.setAttribute("Result", al);
					RequestDispatcher rd = req
							.getRequestDispatcher("doc_inbox.jsp");
					rd.forward(req, resp);

				} catch (SQLException e) {
					// 
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// 
					e.printStackTrace();
				}

			} else {
				resp.sendRedirect("doc_login.jsp");
			}
		}

		else if (page.equals("show_pat_sent.jsp")) {
			Patient P = new Patient();
			HttpSession session = req.getSession();
			String s1 = (String) session.getAttribute("key");
			// String s2=session.getId();

			ResultSet rs;
			if (s1 != null) {
				try {
					rs = P.CheckSentMail();
					ArrayList<Mail> al = new ArrayList<Mail>();
					while (rs.next()) {
						Mail M = new Mail();
						M.setid(rs.getInt("id"));
						M.setto(rs.getInt("to1"));
						M.setfrom(rs.getInt("from1"));
						M.setdata(rs.getString("data"));
						al.add(M);
					}
					req.setAttribute("Result", al);
					RequestDispatcher rd = req
							.getRequestDispatcher("pat_sent.jsp");
					rd.forward(req, resp);

				} catch (SQLException e) {
					// 
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// 
					e.printStackTrace();
				}

			} else {
				resp.sendRedirect("pat_login.jsp");
			}
		}

		else if (page.equals("show_doc_sent.jsp")) {
			Doctor D = new Doctor();
			HttpSession session = req.getSession();
			String s1 = (String) session.getAttribute("key");
			// String s2=session.getId();

			ResultSet rs;
			if (s1 != null) {
				try {
					rs = D.CheckSentMail();
					ArrayList<Mail> al = new ArrayList<Mail>();
					while (rs.next()) {
						Mail M = new Mail();
						M.setid(rs.getInt("id"));
						M.setto(rs.getInt("to1"));
						M.setfrom(rs.getInt("from1"));
						M.setdata(rs.getString("data"));
						al.add(M);
					}
					req.setAttribute("Result", al);
					RequestDispatcher rd = req
							.getRequestDispatcher("doc_sent.jsp");
					rd.forward(req, resp);

				} catch (SQLException e) {
					// 
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// 
					e.printStackTrace();
				}

			} else {
				resp.sendRedirect("doc_login.jsp");
			}
		}

		else if (page.equals("doc_app_hist.jsp")) {
			Doctor D = new Doctor();
			HttpSession session = req.getSession();
			String s1 = (String) session.getAttribute("key");
			// String s2=session.getId();

			ResultSet rs;
			if (s1 != null) {
				try {
					rs = D.ViewAppHistory();
					ArrayList<Appointment> al = new ArrayList<Appointment>();
					while (rs.next()) {
						Appointment Ap = new Appointment();
						Ap.setref(rs.getInt("ref"));
						Ap.setdoc_id(rs.getInt("doc_id"));
						Ap.setpat_id(rs.getInt("pat_id"));
						Ap.setfeedback(rs.getString("feedback"));
						Ap.setprescription(rs.getString("prescription"));
						Ap.setdate(rs.getString("date1"));
						Ap.setslot(rs.getString("slot"));
						al.add(Ap);
					}
					req.setAttribute("Result", al);
					RequestDispatcher rd = req
							.getRequestDispatcher("doc_app_list.jsp");
					rd.forward(req, resp);

				} catch (SQLException e) {
					// 
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// 
					e.printStackTrace();
				}

			} else {
				resp.sendRedirect("doc_login.jsp");
			}
		}

		else if (page.equals("pat_app_hist.jsp")) {
			Patient P = new Patient();
			HttpSession session = req.getSession();
			String s1 = (String) session.getAttribute("key");
			// String s2=session.getId();

			ResultSet rs;
			if (s1 != null) {
				try {
					rs = P.ViewAppHistory();
					ArrayList<Appointment> al = new ArrayList<Appointment>();
					while (rs.next()) {
						Appointment Ap = new Appointment();
						Ap.setref(rs.getInt("ref"));
						Ap.setdoc_id(rs.getInt("doc_id"));
						Ap.setpat_id(rs.getInt("pat_id"));
						Ap.setfeedback(rs.getString("feedback"));
						Ap.setprescription(rs.getString("prescription"));
						Ap.setdate(rs.getString("date1"));
						Ap.setslot(rs.getString("slot"));
						al.add(Ap);
					}
					req.setAttribute("Result", al);
					RequestDispatcher rd = req
							.getRequestDispatcher("pat_app_list.jsp");
					rd.forward(req, resp);

				} catch (SQLException e) {
					// 
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// 
					e.printStackTrace();
				}

			} else {
				resp.sendRedirect("pat_login.jsp");
			}
		}

		else if (page.equals("search_docs.jsp")) {
			Patient P = new Patient();
			HttpSession session = req.getSession();
			String s1 = (String) session.getAttribute("key");
			// String s2=session.getId();
			String type = req.getParameter("type");

			ResultSet rs;
			if (s1 != null) {
				try {

					rs = P.SearchDoctors(type);

					ArrayList<Doctor> al = new ArrayList<Doctor>();
					while (rs.next()) {
						Doctor D = new Doctor();
						System.out.println(rs.getString("name"));
						D.setname(rs.getString("name"));
						D.setid(rs.getInt("id"));
						D.settype(rs.getString("type"));
						al.add(D);
					}
					HttpSession session1 = req.getSession();
					session1.setAttribute("Result", al);
					RequestDispatcher rd = req
							.getRequestDispatcher("docs_list.jsp");
					rd.forward(req, resp);
				} catch (SQLException e) {
					// 
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// 
					e.printStackTrace();
				}
			} else {
				resp.sendRedirect("pat_login.jsp");
			}
		} else if (page.equals("logout.jsp")) {
			HttpSession session = req.getSession();
			session.invalidate();
			RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
			rd.forward(req, resp);
		}

		else if (page.equals("update_app.jsp")) {
			Doctor D = new Doctor();
			HttpSession session = req.getSession();
			String s1 = (String) session.getAttribute("key");
			// String s2=session.getId();
			if (s1 != null) {
				String date1 = req.getParameter("date");
				String slot1 = req.getParameter("slot1");
				try {
					if (D.UpdateAppointment(date1, slot1)) {
						RequestDispatcher rd = req
								.getRequestDispatcher("update_success.jsp");
						rd.forward(req, resp);
					} else {
						RequestDispatcher rd = req
								.getRequestDispatcher("update_fail.jsp");
						rd.forward(req, resp);
					}
				} catch (Exception e) {
					// 
					e.printStackTrace();
				}

			}

			else {
				resp.sendRedirect("doc_login.jsp");
			}
		}

	}
}
