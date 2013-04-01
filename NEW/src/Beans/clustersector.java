package Beans;

public class clustersector {
	
	int idClusterSector =0;
	int idClusters =0;
	String SectorClusterTitle;
	
	public clustersector(int idClusterSector,int idCluster, String SectorClusterTitle){
		this.idClusterSector = idClusterSector;
		this.idClusters = idCluster;
		this.SectorClusterTitle = SectorClusterTitle;
	}
	
	public int getIdClusterSector() {
		return idClusterSector;
	}
	public void setIdClusterSector(int idClusterSector) {
		this.idClusterSector = idClusterSector;
	}
	public int getIdClusters() {
		return idClusters;
	}
	public void setIdClusters(int idClusters) {
		this.idClusters = idClusters;
	}
	public String getSectorClusterTitle() {
		return SectorClusterTitle;
	}
	public void setSectorClusterTitle(String sectorClusterTitle) {
		SectorClusterTitle = sectorClusterTitle;
	}
	
	

}
