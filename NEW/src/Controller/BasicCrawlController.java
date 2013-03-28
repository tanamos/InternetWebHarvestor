/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
import org.carrot2.core.ProcessingResult;



import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

import org.carrot2.core.Document;



/**
 * @author Yasser Ganjisaffar <lastname at gmail dot com>
 */
public class BasicCrawlController {
	
	 static ArrayList<Document> documentsList = new ArrayList<Document>();
	 
		public static void main(String[] args) throws Exception {
			
			BasicCrawlController c = new BasicCrawlController();
			c.insertQuery("C:/crawler", 2, "http://vr-zone.com");
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

	 
	public ArrayList<Document> insertQuery(String path, int crawler, String url) throws Exception{
	
	

		/*
		 * crawlStorageFolder is a folder where intermediate crawl data is
		 * stored.
		 */
		String crawlStorageFolder = path;

		/*
		 * numberOfCrawlers shows the number of concurrent threads that should
		 * be initiated for crawling.
		 */
		int numberOfCrawlers = crawler;

		CrawlConfig config = new CrawlConfig();

		config.setCrawlStorageFolder(crawlStorageFolder);

		/*
		 * Be polite: Make sure that we don't send more than 1 request per
		 * second (1000 milliseconds between requests).
		 */
		config.setPolitenessDelay(1000);

		/*
		 * You can set the maximum crawl depth here. The default value is -1 for
		 * unlimited depth
		 */
		config.setMaxDepthOfCrawling(10);

		/*
		 * You can set the maximum number of pages to crawl. The default value
		 * is -1 for unlimited number of pages
		 */
		config.setMaxPagesToFetch(1);

		/*
		 * Do you need to set a proxy? If so, you can use:
		 * config.setProxyHost("proxyserver.example.com");
		 * config.setProxyPort(8080);
		 * 
		 * If your proxy also needs authentication:
		 * config.setProxyUsername(username); config.getProxyPassword(password);
		 */

		/*
		 * This config parameter can be used to set your crawl to be resumable
		 * (meaning that you can resume the crawl from a previously
		 * interrupted/crashed crawl). Note: if you enable resuming feature and
		 * want to start a fresh crawl, you need to delete the contents of
		 * rootFolder manually.
		 */
		config.setResumableCrawling(false);

		/*
		 * Instantiate the controller for this crawl.
		 */
		PageFetcher pageFetcher = new PageFetcher(config);
		RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
		RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
		CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);

		/*
		 * For each crawl, you need to add some seed urls. These are the first
		 * URLs that are fetched and then the crawler starts following links
		 * which are found in these pages
		 */

		//controller.addSeed("http://www.ics.uci.edu/");
		//controller.addSeed("http://www.ics.uci.edu/~lopes/");
		//controller.addSeed("http://www.ics.uci.edu/~welling/");
		
		controller.addSeed(url);
		//controller.addSeed("http://www.ics.uci.edu/~lopes/");
		//controller.addSeed("http://www.ics.uci.edu/~welling/");


		/*
		 * Start the crawl. This is a blocking operation, meaning that your code
		 * will reach the line after this only when crawling is finished.
		 */
		controller.start(BasicCrawler.class, numberOfCrawlers);
		return topicList;
		
		
		
		
 	
		
		
	
	}
	
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
				
	}
				
			} catch (SQLException e) {
				System.out.println(e);
			}
			return topicList;
	
		}
}
