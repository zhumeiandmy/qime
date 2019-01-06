package one;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;


import two.twoo;
import two.twow;



//import top.gendseo.books.pojo.*;

public class onee {
	
	private static Gson gson = new Gson();
	private static Connection connection = null;
	
	private static void getConnection() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Class.forName("com.mysql.jdbc.Driver");
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/job_test", "root", "1");
	}
	
	private static void getClose(Connection connection, Statement st, PreparedStatement ps, ResultSet rs) throws SQLException {
		if (rs != null) {
			rs.close();
		}
		if (ps != null) {
			ps.close();
		}
		if (st != null) {
			st.close();
		}
		if (connection != null) {
			connection.close();
		}
	}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		System.out.println(Query());
	}
	
	public static String Query() throws ClassNotFoundException, SQLException {
		PreparedStatement ps = null;
		/*
		 * n 本书的集合列表 booksList
		 * 声明 Book 的 POJO 实体类
		 * 实际参见 top.gendseo.books.pojo.Books
		 */
		List<twoo> booksList = new ArrayList<>();
		getConnection();
		
		String sql = "SELECT * FROM emp;";
		System.out.println(sql);
		ps = connection.prepareStatement(sql);
		// 获得查询出来的结果集合
		ResultSet rs = ps.executeQuery();
		// 如果结果集合不为空 do while
		while (rs.next()) {
			// 声明一本书的类，并且往里添加数据，一一对应
		twoo book = new twoo();
			book.setEmpno(rs.getInt("empno"));
			book.setEname(rs.getString("ename"));
			book.setJob(rs.getString("job"));
			book.setHiredate(rs.getString("hiredate"));
			book.setSal(rs.getInt("sal"));
			// 最后把这本书添加到书的集合列表 booksList
//			booksList.add(book);
			System.out.println(book.getEmpno()+" "+book.getEname()
			+" "+book.getJob()+" "+book.getHiredate()+" "+book.getSal());
		}
		/*
		 * n 本书的集合列表 BooksBean
		 * 声明 BooksBean 的 POJO 实体类
		 * 实际参见 top.gendseo.books.pojo.BooksBean
		 */
		twow booksBean = new twow();
		// 图书的列表
		booksBean.setRows(booksList);
		// 图书的总数
		booksBean.setTotal(String.valueOf(booksList.size()));
		
		getClose(connection, null, ps, rs);
		return gson.toJson(booksBean);
	}
	public static String DELETE(String empno) throws ClassNotFoundException, SQLException {
		Statement st = null;
		getConnection();
		
		st = connection.createStatement();
		String sql = "DELETE FROM emp WHERE \"empno\" in (" + empno + ");";
		System.out.println(sql);
		// executeUpdate 不同于 executeQuery
		// executeUpdate 执行更新操作，不返回任何结果
		st.executeUpdate(sql);
		
		getClose(connection, st, null, null);
		return "true";
	}
	public static String UPDATE(String json) throws ClassNotFoundException, SQLException {
		PreparedStatement ps = null;
		getConnection();
		
		// 使用 Gson 将 JSON 转换成 POJO 实体类 Book
		twoo book = gson.fromJson(json, twoo.class);
		String sql = "UPDATE books SET \"ename\" = ?,\"job\" = ?,\"hiredate\" = ?,\"sal\" = ? WHERE \"empno\" = ?;";
		System.out.println(sql);
		
		ps = connection.prepareStatement(sql);
		ps.setString(1, book.getEname());
		ps.setString(2, book.getJob());
		ps.setString(3, book.getHiredate());
		ps.setInt(4, book.getSal());
		ps.setInt(5, book.getEmpno());
		ps.executeUpdate();
		
		getClose(connection, null, ps, null);
		return "true";
	}
	public static String INSERT(String json) throws ClassNotFoundException, SQLException {
		PreparedStatement ps = null;
		getConnection();

		// 使用 Gson 将 JSON 转换成 POJO 实体类 Book
		twoo book = gson.fromJson(json, twoo.class);
		String sql = "INSERT INTO emp (\"empno\", \"ename\", \"job\",\"hiredate\",\"sal\") VALUES (?, ?, ?, ?, ?);";
		 System.out.println(sql);

			
		ps = connection.prepareStatement(sql);
		ps.setInt(1, book.getEmpno());
		ps.setString(2, book.getEname());
		ps.setString(3, book.getJob());
		ps.setString(4, book.getHiredate());
		ps.setInt(5, book.getSal());
		ps.executeUpdate();
		
		getClose(connection, null, ps, null);
		return "true";
	}
}
