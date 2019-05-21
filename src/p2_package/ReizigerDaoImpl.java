package p2_package;

import java.util.ArrayList;
import java.util.Date;

public interface ReizigerDaoImpl {
	//still only default programs
	public ArrayList<Reiziger> findall();
	public ArrayList<Reiziger> findByGBdatum(String GBdatum);
	public Reiziger save(Reiziger r);
	public Reiziger update(Reiziger r);
	public boolean delete(Reiziger r);

}
