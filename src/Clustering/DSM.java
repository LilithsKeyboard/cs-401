package Clustering;

import java.util.Collections;
import java.util.ArrayList;

public class DSM {
	public DSM() {
    }

    public int[][] ClusterAggregation(Cluster clusters)
    {
        //Cluster clusters=new Cluster();
        FileParser fp=new FileParser();
        int itemsCount= fp.getItems().size();
        int [][] DSMatrix= new int[itemsCount][itemsCount];


        int count;
        String pName;
        String nName;

        Collections.sort(fp.getItems());

        for(int p=0; p<itemsCount; p++ )
        {
            for( int n=p+1; n<itemsCount; n++)
            {
                pName = fp.getItems().get(p);
                nName = fp.getItems().get(n);
                count=0;
                for( Cluster c : clusters.getClusters())
                {
                    for(ArrayList<String> pair : c.getClusteredItems() )
                    {
                        if( pair.contains(pName) && pair.contains(nName))
                        {
                            count++;
                        }
                    }
                }
                DSMatrix[p][n]=DSMatrix[n][p]=count;
            }
        }
        return DSMatrix;
    }
}
