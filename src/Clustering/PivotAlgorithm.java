package Clustering;

import java.util.ArrayList;
import java.util.Random;

public class PivotAlgorithm {
	private static double accuracy;

    public void setAccuracy(double accuracy) {
        this.accuracy = accuracy;
    }



    public double getAccuracy() {
        return accuracy;
    }



    public ArrayList<ArrayList<String>> Pivot(int[][] DSM, double threshold,Cluster cluster){
        ArrayList<ArrayList<String>> finalClustering= new ArrayList<>();

        ArrayList<String> temp = cluster.getItems();	// P
        int clusterCounter = 0;
        Main.println("---------  FINAL CLUSTERING  --------");
        while(temp.size() > 0){
            clusterCounter++;
            String rPro = temp.get(random(temp));
            char num1 = rPro.charAt(rPro.length()-1);
            int rValue =  Character.getNumericValue(num1) - 1;
            ArrayList<String> ItemList = new ArrayList<>();		// C
            ItemList.add(rPro);
            for (String cPro : temp) {
                if (cPro.equals(rPro)) {
                    continue;
                }
                char num = cPro.charAt(cPro.length() - 1);
                int matrixColumn = Character.getNumericValue(num) - 1;
                if (DSM[rValue][matrixColumn] > threshold) {
                    ItemList.add(cPro);
                }
            }
            finalClustering.add(ItemList);
            for(String item : ItemList){
                temp.remove(item);
            }
            Main.println("C"+ clusterCounter +": ");
            Main.println(ItemList.toString());
            Main.println("Current Clustering:");
            Main.println(finalClustering.toString());
            Main.println("---------------");
        }
        int metric =0;
        for (int[] ints : DSM) {
            for (int j = 0; j < DSM[0].length; j++) {
                if (ints[j] <= threshold && ints[j] != 0) {
                    metric++;
                }
                if(ints[j]>=threshold){
                    metric++;
                }
            }
        }
        int numOfPairs = cluster.getNumberOfPairs();
        int majorityMatchCount= ((metric/2)+cluster.getCount());
        accuracy = ((double) majorityMatchCount/numOfPairs);
        Main.println("The majority match count: "+ majorityMatchCount );
        Main.println("The number of pairs: "+ numOfPairs);
        Main.print("The accuracy is: ");
        Main.printf("%.4f",accuracy);
        
        return finalClustering;
    }
    public int random(ArrayList<String> randomProcedure){
        Random rnd = new Random();		//just to pick a random procedure of P
        int size = randomProcedure.size()-1;
        if(size <= 0)
            size = 1;
        return rnd.nextInt(size);
    }

}
