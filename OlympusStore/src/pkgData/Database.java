package pkgData;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

@ManagedBean
@SessionScoped

public class Database implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2948438178157372271L;
	private DataSource ds=null;
	private java.sql.Connection conn=null;


	public Database()
	{
		Context ctx;
		try {
			ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			ds = (DataSource) envContext.lookup("jdbc/resStore");
		} catch (NamingException e) {
			e.printStackTrace();
		}

	}

	private void Connect() throws SQLException
	{
		conn=ds.getConnection();
	}

	private void CloseConnection() throws SQLException
	{
		conn.close();
	}

	/*public Vector<String> getAllUsers() {
		Vector<String> vec=new Vector<String>();
		try
		{
			this.Connect();
			PreparedStatement stmt=null;
			String select ="select username from users1";
			stmt = conn.prepareStatement(select);

			ResultSet rs=stmt.executeQuery();
			while(rs.next())
			{
				vec.add(rs.getString("username"));
			}

			this.CloseConnection();
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		return vec;
	}
	 */
	public boolean isPasswordCorrect(String name, String passwd) throws SQLException {
		this.Connect();
		String selectCount = "SELECT count(*) as cnt FROM users1 WHERE username=? AND passwort=?";
		PreparedStatement stmt = conn.prepareStatement(selectCount,ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY);
		stmt.setString(1, name);
		stmt.setString(2, passwd);
		ResultSet rs=stmt.executeQuery();
		rs.next();
		boolean ret=false;
		if(rs.getInt("cnt")>0)
			ret=true;
		this.CloseConnection();
		return ret;
	}

	public void createUser(String username, String adress, String url,String birthdate,String email,String pw ) throws SQLException
	{
		this.Connect();
		PreparedStatement stmt=conn.prepareStatement("insert into users values(seq_users.NEXTVAL,?,?,?,?,?,?,?)");

		stmt.setString(1, username);
		stmt.setString(2, adress);
		stmt.setString(3, url);
		stmt.setString(4, birthdate);
		stmt.setString(5, email);
		stmt.setString(6, pw);
		stmt.setInt(7, 	0);
	

		stmt.executeUpdate();
	}

}
