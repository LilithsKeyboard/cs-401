package Clustering;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class FileParser {
    public static ArrayList<String> items = new ArrayList<>();

    public static void setSelectedFiles(ArrayList<String> of) {
        selectedFiles = of;
    }

    private static ArrayList<String> selectedFiles = new ArrayList<>();

    public ArrayList<String> getItems() {
        return items;
    }

    public static Cluster readFile(String filename)
    {
        ArrayList<ArrayList<String>> clusteredItems;


        try {
            File f = new File(filename);
            BufferedReader reader = new BufferedReader(new FileReader(f));
            String readLine;
            String clusterName;
            String currentCluster = "";
            String itemName;
            int clusterCount = 0;
            clusteredItems= new ArrayList<>();
            items= new ArrayList<>();

            while ((readLine = reader.readLine()) != null) {
                StringTokenizer tokenizer = new StringTokenizer(readLine);

                tokenizer.nextToken(); // contains

                clusterName = tokenizer.nextToken();
                if(!currentCluster.equals(clusterName)) {
                    currentCluster = clusterName;
                    clusterCount++;
                    clusteredItems.add(new ArrayList<>());
                }

                itemName = tokenizer.nextToken();
                clusteredItems.get(clusterCount -1).add(itemName);
                items.add(itemName);
            }

            reader.close();

            // System.out.println(clusteredItems);

            Cluster cluster = new Cluster();
            cluster.setClusteredItems(clusteredItems);
            cluster.setItems(items);

            //System.out.println(cluster.getClusteredItems().get(1));
            return cluster;

        } catch (IOException e) {
            System.out.println("Error while reading an input file!");
            e.printStackTrace();
            
            return new Cluster();
        }

    }


    public static Cluster readAllClusters()
    {
        Cluster cluster=new Cluster();
        System.out.println(selectedFiles);
        System.out.println();
        for (String files: selectedFiles) {
            Cluster cluster1=readFile(files);
            cluster.addCluster(cluster1);

        }

        return cluster;

    }

}
