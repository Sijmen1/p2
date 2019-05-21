package p2_package;


import java.sql.*;
import java.util.ArrayList;

public class Ov_ChipkaartOracleDao extends OracleBaseDao implements Ov_ChipkaartDaoImpl{

	@Override
	public ArrayList<Ov_Chipkaart> findall() {
		// TODO Auto-generated method stub	
		
		ArrayList<Ov_Chipkaart> ov_list= new ArrayList();
		// opens a connection in the try statement, it automatically closes after use
		try (Connection conn = getConnection();){
			//the sql query
			String sql= "select * from OV_chipkaart";
			//add it to prepared statement
			PreparedStatement pstmnt = conn.prepareStatement(sql);
			ResultSet rs= pstmnt.executeQuery();
	
			while(rs.next()) {
				//add all the information into a pojo
				//the DAO is to set a reiziger
				ReizigerOracleDao d = new ReizigerOracleDao();
				Ov_Chipkaart ov = new Ov_Chipkaart();
				ov.setKaartnummer(rs.getInt("kaartnummer"));
				ov.setGeldigtot(rs.getDate("geldigtot"));
				ov.setKlasse(rs.getInt("klasse"));
				ov.setSaldo(rs.getDouble("saldo"));
				//we now get the reiziger from the database.
				ov.setReiziger(d.findById(rs.getInt("reizigerid")));
				ov_list.add(ov);
				
			}
		// catch the error
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return ov_list;
	}
	@Override
	public ArrayList<Ov_Chipkaart> findByReiziger(Reiziger r1) {
		// TODO Auto-generated method stub	
		// Get's all the ov_chipkaart from a single reiziger instance
		ArrayList<Ov_Chipkaart> ov_list= new ArrayList();
		// opens a connection in the try statement, it automatically closes after use
		try (Connection conn = getConnection();){
			//create the preparedStament with the sql code
			String sql= "select * from OV_chipkaart where reizigerid = ?";
			PreparedStatement pstmnt = conn.prepareStatement(sql);
			pstmnt.setInt(1, r1.getReizigerid());
			ResultSet rs= pstmnt.executeQuery();
	
			while(rs.next()) {
				//fill in the the data
				Ov_Chipkaart ov = new Ov_Chipkaart();
				ov.setKaartnummer(rs.getInt("kaartnummer"));
				ov.setGeldigtot(rs.getDate("geldigtot"));
				ov.setKlasse(rs.getInt("klasse"));
				ov.setSaldo(rs.getDouble("saldo"));
				// set the reiziger, we do it in this way to prevent infinite loops
				// would we use a int here to get reiziger it would get ov chipcards with would get reizigers etc.
				ov.setReiziger(r1);
				ov_list.add(ov);
			}
			//catch the exceptions
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return ov_list;
	}

	@Override
	//save your own Ov_chipCard
	public boolean save(Ov_Chipkaart o) {
		// TODO Auto-generated method stub
		boolean b = false;
		// opens a connection in the try statement, it automatically closes after use
		try(Connection conn = getConnection()){
			// set the prepared statement
			String sql= "insert into OV_CHIPKAART(kaartnummer,geldigtot,klasse,saldo,reizigerid) "
					+ "values(?,?,?,?,?)";
			PreparedStatement pstmnt = conn.prepareStatement(sql);
			pstmnt.setInt(1, o.getKaartnummer());
			pstmnt.setDate(2, new java.sql.Date(o.getGeldigtot().getTime()));
			pstmnt.setInt(3, o.getKlasse());
			pstmnt.setDouble(4, o.getSaldo());
			pstmnt.setInt(5, o.getReiziger().getReizigerid());
			pstmnt.executeQuery();
			//if a violation occurs this code will not be reached and an exception is thrown the boolean stays false
			b=true;
		} 
		
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return b;
	}

	@Override
	// deletes an object from the database
	public boolean delete(Ov_Chipkaart o) {
		// TODO Auto-generated method stub
		boolean b = false;
		// opens a connection in the try statement, it automatically closes after use
		try(Connection conn = getConnection()){
			String sql = "delete from OV_Chipkaart where kaartnummer = ?";
			PreparedStatement pstmnt = conn.prepareStatement(sql);
			pstmnt.setInt(1, o.getKaartnummer());
			pstmnt.execute();
			// this code gets reached almost always, it is not dangerous to delete an object that wasn't there.
			b= true;
		} 
		// catch an exception if it should occur.
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b;
	}
	
	@Override
	// changes an object
	public Ov_Chipkaart update(Ov_Chipkaart o) {
		// TODO Auto-generated method stub
		// opens a connection in the try statement, it automatically closes after use
		try(Connection conn = getConnection()){
			//create the preparedStatement
			String sql = "update OV_Chipkaart "
					+ "set geldigtot = ?, klasse = ?, saldo = ?, reizigerid = ?"
					+ " where kaartnummer = ?";
			PreparedStatement pstmnt = conn.prepareStatement(sql);
			pstmnt.setDate(1, new java.sql.Date(o.getGeldigtot().getTime()));
			pstmnt.setInt(2, o.getKlasse());
			pstmnt.setDouble(3, o.getSaldo());
			pstmnt.setInt(4,o.getReiziger().getReizigerid());
			pstmnt.setInt(5, o.getKaartnummer());
			pstmnt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// if an error occurs the ov_chipcard returns a null object so you cannot corrupt your list with wrong objects
			return null;
		}
		//returns the ov Chipcard so you can update the list you use as well if you made it as a loose object first
		return o;
	}
	

}
