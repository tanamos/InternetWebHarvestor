package Controller;

/*
 * Carrot2 project.
 *
 * Copyright (C) 2002-2012, Dawid Weiss, Stanisław Osiński.
 * All rights reserved.
 *
 * Refer to the full license file "carrot2.LICENSE"
 * in the root folder of the repository checkout or at:
 * http://www.carrot2.org/carrot2.LICENSE
 */



import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.carrot2.core.Cluster;
import org.carrot2.core.Document;
import org.carrot2.core.ProcessingResult;
import org.carrot2.core.attribute.CommonAttributesDescriptor;

/**
 * Simple console formatter for dumping {@link ProcessingResult}.
 */
public class ConsoleFormatter
{   static int count = 0;
static SQLManager connection = new SQLManager();
	static Connection conn = connection.getConnection();
	static ResultSet rs = null;
	static PreparedStatement ps = null;
    public static void displayResults(ProcessingResult processingResult)
    {
        final Collection<Document> documents = processingResult.getDocuments();
        final Collection<Cluster> clusters = processingResult.getClusters();
        final Map<String, Object> attributes = processingResult.getAttributes();

        // Show documents
        if (documents != null)
        {
            displayDocuments(documents);
        }

        // Show clusters
        if (clusters != null)
        {
            displayClusters(clusters);
        }

        // Show attributes other attributes
        displayAttributes(attributes);
    }

    public static void displayDocuments(final Collection<Document> documents)
    {
        System.out.println("Collected " + documents.size() + " documents\n");
        for (final Document document : documents)
        {
            displayDocument(0, document);
        }
    }

    public static void displayAttributes(final Map<String, Object> attributes)
    {
        System.out.println("Attributes:");

        String DOCUMENTS_ATTRIBUTE = CommonAttributesDescriptor.Keys.DOCUMENTS;
        String CLUSTERS_ATTRIBUTE = CommonAttributesDescriptor.Keys.CLUSTERS;
        for (final Map.Entry<String, Object> attribute : attributes.entrySet())
        {
            if (!DOCUMENTS_ATTRIBUTE.equals(attribute.getKey())
                && !CLUSTERS_ATTRIBUTE.equals(attribute.getKey()))
            {
                System.out.println(attribute.getKey() + ":   " + attribute.getValue());
            }
        }
    }

    public static void displayClusters(final Collection<Cluster> clusters)
    {
        displayClusters(clusters, Integer.MAX_VALUE);
    }

    public static void displayClusters(final Collection<Cluster> clusters,int maxNumberOfDocumentsToShow)
    {
        displayClusters(clusters, maxNumberOfDocumentsToShow,
            ClusterDetailsFormatter.INSTANCE);
    }

    @SuppressWarnings("deprecation")
	public static void displayClusters(final Collection<Cluster> clusters,
        int maxNumberOfDocumentsToShow, ClusterDetailsFormatter clusterDetailsFormatter)
    {
        //System.out.println("\n\nCreated " + clusters.size() + " clusters\n"); // First Print out Created 2 clusters
    	//String text = "\n\nCreated " + clusters.size() + " clusters\n";
        try
    	{
    	    String filename= "MyFile.txt";
    	    FileWriter fw = new FileWriter(filename,true); //the true will append the new data
    	    fw.write("\n\nCreated " + clusters.size() + " clusters\n");//appends the string to the file
    	    String ClusterTitle = "\n\nCreated " + clusters.size() + " clusters\n";
    	    fw.close();
    	
    	    Date date = new Date();
    	SQLManager connection = new SQLManager();
        Connection conn = connection.getConnection();
        ResultSet rs = null;
        PreparedStatement ps = null;
    
                try {
                	
                	
					ps = conn.prepareStatement("INSERT INTO clusters (idClusters,dataCreated,ClusterTitle) VALUES (?,?,?)");
					ps.setInt(1, 1);
					ps.setString(2, date.toGMTString());
					ps.setString(3, ClusterTitle);
               
                    ps.executeUpdate();
			
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.print("SQL Error"+e);
					e.printStackTrace();
				}
                
    	}
    	
    	catch(IOException ioe)
    	{
    	    System.err.println("IOException: " + ioe.getMessage());
    	}
        int clusterNumber = 1;
        for (final Cluster cluster : clusters)
        {
            displayCluster(0, "" + clusterNumber++, cluster, maxNumberOfDocumentsToShow,
                clusterDetailsFormatter);
        }
    }

    private static void displayDocument(final int level, Document document)
    {
        final String indent = getIndent(level);

        //System.out.printf(indent + "[%2d] ", document.getId()); //3rd // Document Number
        String data3 = ""+document.getId();
        
        
        try
    	{
    	    String filename= "MyFile.txt";
    	    FileWriter fw = new FileWriter(filename,true); //the true will append the new data
    	    fw.write("["+data3+"]"+System.getProperty("line.separator"));
    	    
    	    fw.close();
    	}catch(Exception ex){
    		
    	}
        //System.out.println(document.getField(Document.TITLE)); //URL
        
       // String url = document.getField(Document.TITLE);
        final String url = document.getField(Document.TITLE); // Snippet
        
       // System.out.println(indent +document.getField(Document.SUMMARY));
        String title = indent +document.getField(Document.CONTENT_URL); // URL Title
       if (StringUtils.isNotBlank(url))
        {
            //System.out.println(indent + "     " + url);
        }
    	try 
    	{
    	    String filename= "MyFile.txt";
    	    FileWriter fw = new FileWriter(filename,true); //the true will append the new data
    	    fw.write("Url: " +url + System.getProperty("line.separator") +"Title: "+ title + System.getProperty("line.separator"));
    	    fw.close();
    	    
  try {
            	
    	    	
    	   
				ps = conn.prepareStatement("INSERT INTO clusterdata (idClusterSector,DocNum,DocUrl,DocTitle) VALUES (?,?,?,?)");
				
				ps.setInt(1,count );
				ps.setInt(2,document.getId());
				ps.setString(3, url);
				ps.setString(4, title);
           
                ps.executeUpdate();
		
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.print("SQL Error"+e);
				e.printStackTrace();
			}
    	    
    	}catch(Exception ex){
    		
    	}
        
        System.out.println();
    }

    private static void displayCluster(final int level, String tag, Cluster cluster,
        int maxNumberOfDocumentsToShow, ClusterDetailsFormatter clusterDetailsFormatter)
    
    {
    	  
        final String label = cluster.getLabel();

        // indent up to level and display this cluster's description phrase
        for (int i = 0; i < level; i++)
        {
            System.out.print("  ");
        }
        //System.out.println(label + "  "+ clusterDetailsFormatter.formatClusterDetails(cluster)); // 2nd Prints VR-Zone  (6 docs, score: 0.56)
    	try
    	{
    	    String filename= "MyFile.txt";
    	    FileWriter fw = new FileWriter(filename,true); //the true will append the new data
    	    fw.write(label + "  "+ clusterDetailsFormatter.formatClusterDetails(cluster)+"HAHA"+ System.getProperty("line.separator"));
    	   String ClusterTitle = label + "  "+ clusterDetailsFormatter.formatClusterDetails(cluster)+"HAHA"+ System.getProperty("line.separator");
    	
    	    try {
            	
    	    	SQLManager connection = new SQLManager();
    	        Connection conn = connection.getConnection();
    	        ResultSet rs = null;
    	        PreparedStatement ps = null;
    	        count++;
				ps = conn.prepareStatement("INSERT INTO clustersector (idClusterSector,idClusters,SectorClusterTitle) VALUES (?,?,?)");
				
				ps.setInt(1, count);
				ps.setInt(2,1);
				ps.setString(3, ClusterTitle);
           
                ps.executeUpdate();
		
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.print("SQL Error"+e);
				e.printStackTrace();
			}
    	    
    	    
    	    
    	    fw.close();
    	}catch(Exception ex){
    		
    	}
        
        
        
        
        // if this cluster has documents, display three topmost documents.
        int documentsShown = 0;
        for (final Document document : cluster.getDocuments())
        {
            if (documentsShown >= maxNumberOfDocumentsToShow)
            {
                break;
            }
            displayDocument(level + 1, document);
            documentsShown++;
        }
        if (maxNumberOfDocumentsToShow > 0
            && (cluster.getDocuments().size() > documentsShown))
        {
          //  System.out.println(getIndent(level + 1) + "... and "
               // + (cluster.getDocuments().size() - documentsShown) + " more\n");
        }

        // finally, if this cluster has subclusters, descend into recursion.
        final int num = 1;
        for (final Cluster subcluster : cluster.getSubclusters())
        {
            displayCluster(level + 1, tag + "." + num, subcluster,
                maxNumberOfDocumentsToShow, clusterDetailsFormatter);
        }
    }

    private static String getIndent(final int level)
    {
        final StringBuilder indent = new StringBuilder();
        for (int i = 0; i < level; i++)
        {
            indent.append("  ");
        }

        return indent.toString();
    }

    public static class ClusterDetailsFormatter
    {
        public final static ClusterDetailsFormatter INSTANCE = new ClusterDetailsFormatter();

        protected NumberFormat numberFormat;

        public ClusterDetailsFormatter()
        {
            numberFormat = NumberFormat.getInstance();
            numberFormat.setMaximumFractionDigits(2);
        }

        public String formatClusterDetails(Cluster cluster)
        {
            final Double score = cluster.getScore();
            return "(" + cluster.getAllDocuments().size() + " docs"
                + (score != null ? ", score: " + numberFormat.format(score) : "") + ")";
        }
    }
}
