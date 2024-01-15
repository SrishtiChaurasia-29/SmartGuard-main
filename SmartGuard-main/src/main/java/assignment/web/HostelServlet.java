package assignment.web;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import assignment.dao.HostelDAO;
import assignment.model.Hostel;
@WebServlet("/")
public class HostelServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private HostelDAO hdao;
	public HostelServlet() {
        this.hdao =new HostelDAO();
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action=request.getServletPath();
		switch(action) {
		case"/fetchdetails":
			try {
				selecthostlerdetails(request, response);
			} catch (ServletException | IOException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case"/checkoutinsert":
			try {
				checkoutinsert(request, response);
			} catch (ServletException | IOException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case"/checkinupdate":
			try {
				updatecheckin(request, response);
			} catch (ServletException | IOException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case"/search":
			try {
				searchonehostler(request, response);
			} catch (ServletException | IOException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case"/fetchthroughdate":
			try {
				selectallhostlersthroughdate(request, response);
			} catch (ServletException | IOException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		default:
			try {
				selectallhostlers(request, response);
			} catch (ServletException | IOException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
	}
	private void selecthostlerdetails(HttpServletRequest request,HttpServletResponse response)
			throws ServletException,IOException,SQLException{
		long rollnumber=Long.parseLong(request.getParameter("rollnumber"));
		Hostel existinghostler = hdao.selecthostlerdetails(rollnumber);
		RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
		request.setAttribute("hostler", existinghostler);
		rd.forward(request, response);
	}
	private void checkoutinsert(HttpServletRequest request,HttpServletResponse response)
			throws ServletException,IOException, SQLException{
		int hid=Integer.parseInt(request.getParameter("hid"));
		String placetogo=request.getParameter("placetogo");
		Hostel hs=new Hostel(hid,placetogo);
		hdao.insertchechout(hs);
		response.sendRedirect("list");
	}
	private void selectallhostlers(HttpServletRequest request,HttpServletResponse response)
			throws ServletException,IOException,SQLException
	{
			List<Hostel> listhostler=hdao.selectallhostler();
			request.setAttribute("listhostler", listhostler);
			RequestDispatcher rd=request.getRequestDispatcher("StudentDetail.jsp");
			rd.forward(request, response);
	}
	private void updatecheckin(HttpServletRequest request,HttpServletResponse response)
			throws ServletException,IOException,SQLException
	{
		int cid=Integer.parseInt(request.getParameter("check_id"));
		Hostel cheupdate=new Hostel(cid);
		hdao.updatehostler(cheupdate);
		response.sendRedirect("list");
	}
	private void searchonehostler(HttpServletRequest request,HttpServletResponse response)
			throws ServletException,IOException,SQLException
	{
		long rollnumber=Long.parseLong(request.getParameter("roll"));
		List<Hostel> existinghostler = hdao.selectonehostler(rollnumber);
		request.setAttribute("listhostler", existinghostler);
		RequestDispatcher rd = request.getRequestDispatcher("StudentDetail.jsp");
		rd.forward(request, response);
	}
	private void selectallhostlersthroughdate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException,IOException,SQLException{
		String date=request.getParameter("date");
		List<Hostel> hostlers=hdao.selectallhostlerthroughdate(date);
		request.setAttribute("hostlers", hostlers);
		RequestDispatcher rd=request.getRequestDispatcher("downloaddetails.jsp");
		rd.forward(request, response);
	}

}
