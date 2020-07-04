package it.polito.tdp.borders.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.borders.model.Border;
import it.polito.tdp.borders.model.Country;

public class BordersDAO {
	private Map<Integer,Country>mappaNazioni=new HashMap<>();

	public List<Country> loadAllCountries() {

		String sql = "SELECT ccode, StateAbb, StateNme FROM country ORDER BY StateAbb";
		List<Country> result = new ArrayList<Country>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Country c=new Country(rs.getInt("ccode"),rs.getString("StateAbb"),
						rs.getString("StateNme"));
				result.add(c);
				mappaNazioni.put(rs.getInt("ccode"),c);
			}			
			conn.close();
			return result;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
	}

	public List<Country>getCountryPairs(Map<String, Border>idMap,int anno) {
		this.loadAllCountries();
		String sql="SELECT c1.state1no,c1.state1ab,c1.state2no,c1.state2ab,"
				+ "c1.year FROM contiguity AS "
				+ "c1 WHERE c1.YEAR <=? AND c1.state1no< c1.state2no"
				+ " AND c1.conttype=1 ORDER BY c1.state1no";
		Country c1 = null;
		Country c2 = null;
		List<Country>result=new LinkedList<>();
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, anno);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				if(mappaNazioni.containsKey(rs.getInt("state1no"))&&
						mappaNazioni.containsKey(rs.getInt("state2no"))) {
					c1=mappaNazioni.get(rs.getInt("state1no"));
					c2=mappaNazioni.get(rs.getInt("state2no"));
					
				}
				
				Border b=new Border(rs.getInt("state1no"),rs.getString("state1ab"),
						rs.getInt("state2no"),rs.getString("state2ab"),
						rs.getInt("year"),c1,c2);
				if(!idMap.containsKey((rs.getInt("state1no")+" "+rs.getInt("state2no")))) {
					idMap.put((rs.getInt("state1no")+" "+rs.getInt("state2no")), b);
					
				}
				
			}			
			conn.close();
			for(Country c:mappaNazioni.values()) {
				result.add(c);
			}
			
			return result;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
	}
}
