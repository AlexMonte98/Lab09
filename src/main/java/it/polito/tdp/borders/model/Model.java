package it.polito.tdp.borders.model;

import java.util.*;
import java.util.Map;

import org.jgrapht.Graphs;
import org.jgrapht.graph.*;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.borders.db.BordersDAO;

public class Model {
	Map<String,Border>idMap;
	BordersDAO d;
	SimpleGraph<Country,DefaultEdge>grafo;
	List<Border>listaBorders;
	List<Country>listaNazioni;
	
	public Model() {
		idMap=new HashMap<>();
		d=new BordersDAO();
		listaNazioni=new LinkedList<>();
		listaBorders=new LinkedList<>();
		
	}
	public void creaGrafo(int anno) {
		for(Country cc:d.getCountryPairs(idMap, anno)) {
			listaNazioni.add(cc);
		}
		d.getCountryPairs(idMap, anno);
		grafo=new SimpleGraph<>(DefaultEdge.class);
		//this.loadCountries();
		Graphs.addAllVertices(grafo, listaNazioni);
		
		/*for (Border b:idMap.values()){
			System.out.println(b.toString());
			
		}*/
		listaBorders.addAll(idMap.values());
		for (Border bo:listaBorders) {
			if(bo!=null) {
				DefaultEdge e=grafo.getEdge(bo.getC1(),bo.getC2());
				if(e==null) {
					grafo.addEdge(bo.getC1(), bo.getC2());
				}
			}
		}
		
	}
	
	public int numVertici() {
		return grafo.vertexSet().size();
	}
	
	
	/*public void loadCountries() {
		for(Country c:d.loadAllCountries()) {
			if(c!=null) {
				for (Border b:idMap.values()) {
					if(b!=null&&(c.getCcode()==b.getId1()||c.getCcode()==b.getId2())) {
						mappaNazioni.put(c.getCcode(), c);
					}
				}
			}
		}
	}*/
	public String listaStati() {
		String temp="";
		int count=0;
		for(Country c:listaNazioni) {
			count=0;
			for(Border b:listaBorders) {
				if(c.getCcode()==b.getC1().getCcode()||c.getCcode()==
						b.getC2().getCcode()) {
					count++;
				}
			}
			temp+=c.getStateName()+" "+count+"\n";
		}
		return temp;
	}
	
	
	

}
