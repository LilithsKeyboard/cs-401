package Clustering;


import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Cluster {

	private int count;
	private ArrayList<ArrayList<String>> clusteredItems;
	private ArrayList<String> items = new ArrayList<>();
	private final ArrayList<Cluster> clusters = new ArrayList<>();
	private JTextArea text;
	private static int numberOfPairs;

	public Cluster()
	{

	}
	public int getNumberOfPairs(){return numberOfPairs;}
	public int getCount(){
		return count;
	}

	public ArrayList<ArrayList<String>> getClusteredItems() {
		return clusteredItems;
	}
	public void setClusteredItems(ArrayList<ArrayList<String>> clusteredItems) {
		this.clusteredItems = clusteredItems;
	}

	public ArrayList<Cluster> getClusters() {
		return clusters;
	}

	public ArrayList<String> getItems() {
		return items;
	}
	public void setItems(ArrayList<String> items) {
		this.items = items;
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
	public void writeToFile(ArrayList<ArrayList<String>> rsfCluster, String fileName) {
		try {
			File file = new File(fileName); //kendi pathimi verdim şimdilik
			file.createNewFile();
			FileWriter writer = new FileWriter(file,false);
			for(int i = 0; i <rsfCluster.size(); i++) {
				ArrayList<String> cluster = rsfCluster.get(i);
				for (String s : cluster) writer.write("contain " + i + " " + s + "\n");
			}
			writer.close();
		} catch (IOException e) {
			System.out.println("Error while trying to create the output file!");
			e.printStackTrace();
		}
	}
	public void printClusters()
	{
		Main.println("--------  CLUSTERS PAIR --------");
		numberOfPairs =0;
		for(Cluster c : clusters )
		{
			Main.println(c.getClusteredItems().toString());
			numberOfPairs +=c.getClusteredItems().size();
			for(ArrayList<String> a:c.getClusteredItems()){
				if(a.size()==1){
					count++;
				}
			}
		}
	}

	public void printDSMMatrix(int[][] DSM)
	{
		Main.println("----------  DSM  ----------");

		Main.printf("%5s "," ");
		for(int i=0; i<DSM.length; i++)
		{
			Main.printf("%3s ", items.get(i));
		}
		Main.println();
		Main.printf("%5s "," ");
		for(int i=0; i<DSM.length; i++)
		{
			Main.printf("%3d ",i);
		}
		Main.println();
		Main.printf("%5s "," ");
		for(int i=0; i<DSM.length; i++)
		{
			Main.printf("%4s", "----");
		}
		Main.println();
		for(int i=0; i<DSM.length; i++)
		{
			Main.printf("%2s %2d |",items.get(i),i);
			for(int j=0; j<DSM[0].length; j++)
				Main.printf("%3d ", DSM[i][j]);
			Main.println();
		}


	}

	
	
}