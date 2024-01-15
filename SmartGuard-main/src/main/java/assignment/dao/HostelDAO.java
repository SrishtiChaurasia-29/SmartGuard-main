package assignment.dao;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
import java.sql.SQLException;
import assignment.model.Hostel;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
public class HostelDAO {
	private String jdbcURL="jdbc:mysql://localhost:3306/smartguard?useUnicode=true&useJDBCComplaintTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Asia/Calcutta";
	private String jdbcUsername="root";
	private String jdbcPassword="";
	private static final String GET_HOSTELER_DETAILS="SELECT id,name,course,branch,year,hostelname,photo FROM hostler WHERE rollnumber = ?;";
//	private static final String GET_HOSTELER_ID="SELECT id FROM hostler WHERE rollnumber = ?;";
	private static final String INSERT_CHECKOUT_DETAILS = "insert into checkinout(hostler_id,placetogo) values(?,?);";
	private static final String UPDATE_CHECKIN_DETAIL = "update checkinout set datetime_in=? where check_id=?;";
	private static final String FETCH_ALL_DETAILS = "select check_id,name,rollnumber,hostelname,contactnumber,course,branch,datetime_out,placetogo"
			+ " from hostler FULL JOIN checkinout ON id = hostler_id where checkinout.datetime_in IS NULL;";
	private static final String SEARCH_STUDENT = "select check_id,name,rollnumber,hostelname,contactnumber,course,branch,datetime_out,placetogo"
			+ "  from hostler FULL JOIN checkinout ON id = hostler_id where checkinout.datetime_in IS NULL and rollnumber=?;";
	private static final String FETCH_HOSTLER_THROUGH_DATE = "select name,rollnumber,hostelname,datetime_out,datetime_in,placetogo from hostler FULL JOIN checkinout ON id = hostler_id;";
//	SELECT column_name(s)
//	FROM table1
//	FULL OUTER JOIN table2
//	ON table1.column_name = table2.column_name
//	WHERE condition;
	protected Connection getConnection() {
		System.out.println("Hello");
		Connection conn=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection(jdbcURL,jdbcUsername,jdbcPassword);
			System.out.println("Connection Established");
		}catch(SQLException e) {
			e.printStackTrace();
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	
	public Hostel selecthostlerdetails(long rollnumber)throws SQLException{
		Hostel hostler=null;
		try(Connection conn=getConnection();
			PreparedStatement ps=conn.prepareStatement(GET_HOSTELER_DETAILS);){
			ps.setLong(1, rollnumber);
//			System.out.println(ps);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
			int id = rs.getInt("id"); 
			String name=rs.getString("name");
			String course=rs.getString("course");
			String branch=rs.getString("branch");
			int year=rs.getInt("year");
			String hostelname=rs.getString("hostelname");
			Blob photo = rs.getBlob("photo");
			InputStream is = photo.getBinaryStream();
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			byte[] b = new byte[4096];
			int bytesRead = -1;
            
            while ((bytesRead = is.read(b)) != -1) {
                os.write(b, 0, bytesRead);                  
            }
            byte[] imageBytes = os.toByteArray();
            String base64image = Base64.getEncoder().encodeToString(imageBytes);
            is.close();
            os.close();
			hostler = new Hostel(id,rollnumber,name,course,branch,year,hostelname); 
			hostler.setBase64image(base64image);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return hostler;
	}
	public void insertchechout(Hostel hostler)throws SQLException{
		try(Connection conn=getConnection();
			PreparedStatement ps=conn.prepareStatement(INSERT_CHECKOUT_DETAILS);){
			ps.setInt(1, hostler.getHostler_id());
			ps.setString(2, hostler.getPlacetogo());
			ps.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public boolean updatehostler(Hostel hostler)throws SQLException{
		boolean rowupdated;
		try(Connection conn=getConnection();
				PreparedStatement ps=conn.prepareStatement(UPDATE_CHECKIN_DETAIL);){
			Timestamp datetime = new Timestamp(new java.util.Date().getTime());
			ps.setTimestamp(1, datetime);
			ps.setInt(2, hostler.getCheck_id());
			rowupdated=ps.executeUpdate() > 0;
		}
		return rowupdated;
	}
	public List<Hostel> selectallhostler()throws SQLException{
		List<Hostel> hostlers=new ArrayList<>();
		try(Connection conn=getConnection();
			PreparedStatement ps=conn.prepareStatement(FETCH_ALL_DETAILS);){
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) {
			int check_id = rs.getInt("check_id"); 
			String name=rs.getString("name");
			long rollnumber=rs.getLong("rollnumber");
			String hostelname=rs.getString("hostelname");
			long contactnumber = rs.getLong("contactnumber");
			String course = rs.getString("course");
			String branch = rs.getString("branch");
			Timestamp datetime_out = rs.getTimestamp("datetime_out");
//			Date date = new Date(datetime_out.getTime());
//			System.out.println("this is date"+date);
//			System.out.println(datetime_out);
			String placetogo = rs.getString("placetogo");
			hostlers.add(new Hostel(check_id,name,rollnumber,hostelname,contactnumber,course,branch,datetime_out,placetogo)); 
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return hostlers;
	}
	public List<Hostel> selectallhostlerthroughdate(String checkdate)throws SQLException{
		List<Hostel> hostlers=new ArrayList<>();
		try(Connection conn=getConnection();
			PreparedStatement ps=conn.prepareStatement(FETCH_HOSTLER_THROUGH_DATE);){
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				Timestamp datetime_o = rs.getTimestamp("datetime_out");
				Date date = new Date(datetime_o.getTime());
				String fdate = date.toString();
				if(fdate.equals(checkdate)) {
					System.out.println(fdate);
					System.out.println(checkdate);
					String name=rs.getString("name");
					long rollnumber=rs.getLong("rollnumber");
					String hostelname=rs.getString("hostelname");
					Timestamp datetime_out = rs.getTimestamp("datetime_out");
					Timestamp datetime_in = rs.getTimestamp("datetime_in");
					String placetogo = rs.getString("placetogo");
					hostlers.add(new Hostel(name,rollnumber,hostelname,datetime_out,datetime_in,placetogo)); 
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return hostlers;
	}
	
	public List<Hostel> selectonehostler(long rollnumber)throws SQLException{
		List<Hostel> hostlers=new ArrayList<>();
		try(Connection conn=getConnection();
			PreparedStatement ps=conn.prepareStatement(SEARCH_STUDENT);){
			ps.setLong(1, rollnumber);
			System.out.println(ps);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				int check_id = rs.getInt("check_id"); 
				String name=rs.getString("name");
				long roll=rs.getLong("rollnumber");
				String hostelname=rs.getString("hostelname");
				long contactnumber = rs.getLong("contactnumber");
				String course = rs.getString("course");
				String branch = rs.getString("branch");
				Timestamp datetime_out = rs.getTimestamp("datetime_out");
				System.out.println(datetime_out);
				String placetogo = rs.getString("placetogo");
				hostlers.add(new Hostel(check_id,name,roll,hostelname,contactnumber,course,branch,datetime_out,placetogo)); 
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return hostlers;
	}
}
