package wone.sn;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class StudentServlet
 */
@WebServlet("/StudentServlet")
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try (PrintWriter out = response.getWriter()) {
			RequestDispatcher requestDispatcher;
			int result = DataBase.addStudent(request.getParameter("name"), request.getParameter("firstName"), request.getParameter("email"), request.getParameter("address"), request.getParameter("dateOfBirth"));
			if (result != 1) {
//				request.setAttribute("error", "true");
				response.sendRedirect(request.getContextPath() + "/registerStudent.jsp?success=false");
//				requestDispatcher = request.getRequestDispatcher("registerStudent.jsp?success=false");
			} else {
				response.sendRedirect(request.getContextPath() + "/registerStudent.jsp?success=true");
//				request.setAttribute("error", "false");
//				requestDispatcher = request.getRequestDispatcher("registerStudent.jsp?success=true");
			}
//			out.println("<h1>"+ result + "</h1>");
//			requestDispatcher.forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
