package Clustring;

import java.util.ArrayList;
import java.util.Collections;

public class ClusterManager {

	private ArrayList<Cluster> clusters;
	private ArrayList<String> items;
	int DSM[][];
	
	public ClusterManager()
	{
		clusters= new ArrayList<Cluster>();
		items= new ArrayList<String>();
	}
	public void addCluster(Cluster cluster)
	{
		clusters.add(cluster);
		
		for( String s : cluster.getItems())
		{
			if( !items.contains(s))
			{
				items.add(s);
			}
		}
		
		
	}
	
	public ArrayList<Cluster> getClusters() {
		return clusters;
	}
	public void setClusters(ArrayList<Cluster> clusters) {
		this.clusters = clusters;
	}
	public ArrayList<String> getItems() {
		return items;
	}
	public void setItems(ArrayList<String> items) {
		this.items = items;
	}
	public int[][] getDSM() {
		return DSM;
	}
	public void setDSM(int[][] dSM) {
		DSM = dSM;
	}
	
	
	/*
	 * : P the set of procedures
2: C the set of clusterings
3: DSM[][] 0
4: count 0
5: for each pair of procedures (p, n) such that p 6= n do
6: for each c 2 C do
7: if p and n are in the same cluster according to c then
8: count count + 1
9: end if
10: end for
11: DSM[p][n] DSM[n][p] count
12: count 0
13: end for
14: Pivot(DSM, |C|
2 )
	 * 
	 * 
	 * 
	 */
	public  void  ClusterAggregation()
	{
		int itemscount= items.size();
		DSM = new int[itemscount][itemscount];
		int count;
		String pname;
		String nname;
		
		Collections.sort(items); // haz�r sort algoritmas� kullan�labilir mi ?
		
		for(int p=0; p<itemscount; p++ )
		{
			for( int n=p+1; n<itemscount; n++)
			{
				pname= items.get(p);
				nname = items.get(n);
				count=0;
				for( Cluster c : clusters)
				{
					
					for(ArrayList<String> pair : c.getClusteredItems() )
					{
						if( pair.contains(pname) && pair.contains(nname))
						{
							count++;
						}
					}
					
					
				}
				
				DSM[p][n]=DSM[n][p]=count;
				
			}
		}
		
		
	}
	
	public void printClusters()
	{
		System.out.println("--------  CLUSTERS PAIR --------");
		for(Cluster c : clusters )
		{
			System.out.println(c.getClusteredItems());
		}
	}
	
	public void printDSMMatrix()
	{
		System.out.println("----------  DSM  ----------");
		
		System.out.printf("%5s "," ");
		for(int i=0; i<DSM.length; i++)
		{
			System.out.printf("%3s ", items.get(i));
		}
		System.out.println();
		System.out.printf("%5s "," ");
		for(int i=0; i<DSM.length; i++)
		{
			System.out.printf("%3d ",i);
		}
		System.out.println();
		System.out.printf("%5s "," ");
		for(int i=0; i<DSM.length; i++)
		{
			System.out.printf("%4s", "----");
		}
		System.out.println();
		for(int i=0; i<DSM.length; i++)
		{
			System.out.printf("%2s %2d |",items.get(i),i);
			for(int j=0; j<DSM[0].length; j++)
			{
				System.out.print( String.format("%3d ", DSM[i][j]));
			}
			System.out.println();
		}
	}
}
