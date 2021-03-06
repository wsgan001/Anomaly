package ca.pfv.spmf.test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import ca.pfv.spmf.algorithms.clustering.dbscan.AlgoDBSCAN;
import ca.pfv.spmf.algorithms.clustering.distanceFunctions.DistanceEuclidian;
import ca.pfv.spmf.algorithms.clustering.distanceFunctions.DistanceFunction;
import ca.pfv.spmf.algorithms.clustering.optics.AlgoOPTICS;
import ca.pfv.spmf.patterns.cluster.Cluster;
import ca.pfv.spmf.patterns.cluster.DoubleArray;

/**
 *  Example of how to use the OPTICS algorithm to extract DBSCAN-style clusters, in source code,
 *  and then save the result to file.
 */
public class MainTestOPTICS_extractDBScan_saveToFile {
	
	public static void main(String []args) throws NumberFormatException, IOException{
		
		String input = fileToPath("inputDBScan.txt");
		String output = ".//output.txt";
		
		// we set the parameters of DBScan:
		int minPts=2;
		double epsilon = 20d;
		double epsilonPrime = 5d;
		
		// We specify that in the input file, double values on each line are separated by spaces
		String separator = " ";
		
		// Apply the algorithm to compute a cluster ordering
		AlgoOPTICS algo = new AlgoOPTICS();  
		
		/* Set the distance function to be used for clustering  
		 * Others are available (subclasses of DistanceFunction) */
		DistanceFunction distanceFunction = new DistanceEuclidian(); 
		algo.setDistanceFunction(distanceFunction);
		
		algo.computerClusterOrdering(input, minPts, epsilon, separator);

		//  generate dbscan clusters from the cluster ordering:
		algo.extractDBScan(minPts,epsilonPrime);

		algo.printStatistics();
		algo.saveToFile(output);
	}
	
	public static String fileToPath(String filename) throws UnsupportedEncodingException{
		URL url = MainTestOPTICS_extractDBScan_saveToFile.class.getResource(filename);
		 return java.net.URLDecoder.decode(url.getPath(),"UTF-8");
	}
	
	
}
