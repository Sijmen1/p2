package p2_package;
import java.util.ArrayList;


public interface Ov_ChipkaartDaoImpl {
	// all standard DAO functionality
	public ArrayList<Ov_Chipkaart> findall();
	public boolean save(Ov_Chipkaart k);
	public boolean delete(Ov_Chipkaart k);
	public Ov_Chipkaart update(Ov_Chipkaart k);
	// this one is one I added to save on database calls, more information in the DAO itself
	public ArrayList<Ov_Chipkaart> findByReiziger(Reiziger r1);
	

}
