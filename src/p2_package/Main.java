package p2_package;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
public class Main {
	

	public static void main(String[] args) throws ParseException {
		// all the testcode generated by me.
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");		
		Ov_ChipkaartDaoImpl ov_dao = new Ov_ChipkaartOracleDao();
		//System.out.println(ov_dao.findall());
		for(Ov_Chipkaart k : ov_dao.findall()) {
			System.out.println(k.getReiziger().toString());
			
		}
		
		ReizigerOracleDao reiziger_dao = new ReizigerOracleDao();
		ArrayList<Reiziger> reizigers_list = reiziger_dao.findall();
		//System.out.println(reizigers_list);

//		Reiziger r1= new Reiziger();
//		r1.setVoorletters("b");
//		r1.setTussenvoegsel("big");
//		r1.setAchternaam("achternamo");
//		r1.setGeboorteDatum(sdf.parse("09-01-1994"));
//		reiziger_dao.save(r1);
//		Reiziger r2 = reizigers_list.get(reizigers_list.size()-1);
//		r2.setAchternaam("halloa");
//		reiziger_dao.update(r2);
	
		
//		reizigers_list= reiziger_dao.findByGBdatum("17-09-2002");
//		
//		System.out.println(r2);
//		d.delete(r2);
		//System.out.println(reizigers_list);
	}

}