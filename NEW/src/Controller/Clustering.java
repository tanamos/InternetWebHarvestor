package Controller;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.carrot2.clustering.lingo.LingoClusteringAlgorithm;
import org.carrot2.clustering.stc.STCClusteringAlgorithm;
import org.carrot2.clustering.synthetic.ByUrlClusteringAlgorithm;
import org.carrot2.core.Cluster;
import org.carrot2.core.Controller;
import org.carrot2.core.ControllerFactory;
import org.carrot2.core.Document;
import org.carrot2.core.ProcessingResult;


public class Clustering {
	 static ArrayList<Document> documentsList = new ArrayList<Document>();
	 
	public static void main(String[] args) {
		
		Clustering c = new Clustering();
		
		documentsList = c.GetAllSlot();

		System.out.print("returned topicList");
		
		for (int i = 0; i > documentsList.size(); i++) { // **line 2**
			
			String title = documentsList.get(i).getTitle();
			String snippet = documentsList.get(i).getSummary();
			String link = documentsList.get(i).getContentUrl();
			
			documentsList.add(new Document(link, title,snippet));
		//	System.out.println(documentsList.size());
			//System.out.print("Title: "+title);
			//System.out.print("snippet: "+snippet);
			//System.out.print("link: "+link);
			//System.out.print("Processing Please wait.");
			
		}     	
			// A controller to manage the processing pipeline. 
	         final Controller controller = ControllerFactory.createSimple();
	     	System.out.print("Stage1");
	         
          //* Perform clustering by topic using the Lingo algorithm. Lingo can 
	         // * take advantage of the original query, so we provide it along with the documents.
	          
	         final ProcessingResult byTopicClusters = controller.process(documentsList, "Trends",LingoClusteringAlgorithm.class );
	        
	         final List<Cluster> clustersByTopic = byTopicClusters.getClusters();
	         System.out.print("Stage2");
	        //  Perform clustering by domain. In this case query is not useful, hence it is null. 
	         final ProcessingResult byDomainClusters = controller.process(documentsList, null,ByUrlClusteringAlgorithm.class);
	         System.out.print("Stage3");
	         //STCClusteringAlgorithm.class
	         //
	         // [[[end:clustering-document-list]]]
	         
	         ConsoleFormatter.displayClusters(clustersByTopic);
	         System.out.print("Stage4");
		
		
	}

	int count=0;
	
	 private ArrayList<Document> topicList = new ArrayList<Document>();
	public ArrayList<Document> GetAllSlot() {

		try {

			
			SQLManager connection = new SQLManager();
			Connection conn = connection.getConnection();
			PreparedStatement ps = null;
			ResultSet rs = null;

			ps = conn.prepareStatement("SELECT url,title,snippet FROM vrzone");
			
			rs = ps.executeQuery();

			while (rs.next()) {
				
				
				String url = rs.getString("url");
				String snippet = rs.getString("snippet");
				String title = rs.getString("title");
			
				topicList.add(new Document(url, snippet, title));
				System.out.println(url);
				count++;
}
			
		} catch (SQLException e) {
			System.out.println(e);
		}
		System.out.println(count);
		return topicList;
	
	}

}
