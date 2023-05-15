
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Update_book")
public class Update extends HttpServlet {
	Connection c1;

	@Override
	public void init() throws ServletException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			c1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/database_1", "root", "Qwert@54321");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("bookid"));
		String name = req.getParameter("bookname");
		double price = Double.parseDouble(req.getParameter("bookprice"));
		String author = req.getParameter("bookauthor");

		PreparedStatement ps;
		PrintWriter pw = resp.getWriter();

		try {
			ps = c1.prepareStatement(
					"update book set book_name = ? , author_name = ? , book_price = ?  where book_id = ? ;");
			ps.setString(1, name);
			ps.setString(2, author);
			ps.setDouble(3, price);
			ps.setInt(4, id);
			ps.executeUpdate();
			pw.print("data updated successfully");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
