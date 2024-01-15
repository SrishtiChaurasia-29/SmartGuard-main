package assignment.model;
//import java.sql.*;
import java.sql.Timestamp;

public class Hostel {
//	private byte[] photo;
	private String base64image;
	private long rollnumber; 
	private int id;
	private String name;
	private String course;
	private String branch;
	private int year;
	private String hostelname;
	private int hostler_id;
	private Timestamp datetime_out;
	private Timestamp datetime_in;
	private String placetogo;
	private int check_id;
	private long contactnumber;
//    public byte[] getImage() {
//        return this.photo;
//    }
	public Hostel(int id,long rollnumber,String name,String course,String branch,int year,String hostelname)
	{
		super();
		this.id = id;
		this.rollnumber = rollnumber;
		this.name = name;
		this.course = course;
		this.branch = branch;
		this.year = year;
		this.hostelname = hostelname;
	}
	public Hostel(int check_id,String name,long rollnumber,String hostelname,long contactnumber,String course,String branch,Timestamp datetime_out,String placetogo)
	{
		super();
		this.check_id = check_id;
		this.name = name;
		this.rollnumber = rollnumber;
		this.hostelname = hostelname;
		this.contactnumber = contactnumber;
		this.course = course;
		this.branch = branch;
		this.datetime_out = datetime_out;
		this.placetogo = placetogo;
	}
	public Hostel(int hostler_id,String placetogo)
	{
		super();
		this.hostler_id = hostler_id;
		this.placetogo = placetogo;
	}
	public Hostel(int check_id)
	{
		super();
		this.check_id = check_id;
	}
	public Hostel(String name, long rollnumber, String hostelname, Timestamp datetime_out, Timestamp datetime_in,
			String placetogo) {
		super();
		this.name = name;
		this.rollnumber = rollnumber;
		this.hostelname = hostelname;
		this.datetime_out = datetime_out;
		this.datetime_in = datetime_in;
		this.placetogo = placetogo;
	}
	public int getCheck_id() {
		return check_id;
	}
	public void setCheck_id(int check_id) {
		this.check_id = check_id;
	}
	public Timestamp getDatetime_out() {
		return datetime_out;
	}
	public void setDatetime_out(Timestamp datetime_out) {
		this.datetime_out = datetime_out;
	}
	public long getContactnumber() {
		return contactnumber;
	}
	public void setContactnumber(long contactnumber) {
		this.contactnumber = contactnumber;
	}
	public long getRollnumber() {
		return rollnumber;
	}
	public void setRollnumber(long rollnumber) {
		this.rollnumber = rollnumber;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getHostelname() {
		return hostelname;
	}
	public void setHostelname(String hostelname) {
		this.hostelname = hostelname;
	}
	public int getHostler_id() {
		return hostler_id;
	}
	public void setHostler_id(int hostler_id) {
		this.hostler_id = hostler_id;
	}
	public String getPlacetogo() {
		return placetogo;
	}
	public void setPlacetogo(String placetogo) {
		this.placetogo = placetogo;
	}
	public String getBase64image() {
		return base64image;
	}
	public void setBase64image(String base64image) {
		this.base64image = base64image;
	}
	public Timestamp getDatetime_in() {
		return datetime_in;
	}
	public void setDatetime_in(Timestamp datetime_in) {
		this.datetime_in = datetime_in;
	}

	
}
