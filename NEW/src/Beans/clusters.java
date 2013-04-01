package Beans;

public class clusters {
	
	int idCluster =0;
	String dataCreated;
	String ClusterTitle;
	
	public clusters(int idCluster,String dataCreated, String ClusterTitle){
		this.idCluster = idCluster;
		this.dataCreated = dataCreated;
		this.ClusterTitle = ClusterTitle;
	}
	
	public int getIdCluster() {
		return idCluster;
	}
	public void setIdCluster(int idCluster) {
		this.idCluster = idCluster;
	}
	public String getDataCreated() {
		return dataCreated;
	}
	public void setDataCreated(String dataCreated) {
		this.dataCreated = dataCreated;
	}
	public String getClusterTitle() {
		return ClusterTitle;
	}
	public void setClusterTitle(String clusterTitle) {
		ClusterTitle = clusterTitle;
	}

}
