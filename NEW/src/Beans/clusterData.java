package Beans;

public class clusterData {
	
	int idclusterData =0;
	int idClusterSector =0;
	int DocNum =0;
	String DocUrl;
	
	
	public clusterData(int idclusterData, int idClusterSector, int docNum,String docUrl, String docTitle) {
		super();
		this.idclusterData = idclusterData;
		this.idClusterSector = idClusterSector;
		DocNum = docNum;
		DocUrl = docUrl;
		DocTitle = docTitle;
	}
	public int getIdclusterData() {
		return idclusterData;
	}
	public void setIdclusterData(int idclusterData) {
		this.idclusterData = idclusterData;
	}
	public int getIdClusterSector() {
		return idClusterSector;
	}
	public void setIdClusterSector(int idClusterSector) {
		this.idClusterSector = idClusterSector;
	}
	public int getDocNum() {
		return DocNum;
	}
	public void setDocNum(int docNum) {
		DocNum = docNum;
	}
	public String getDocUrl() {
		return DocUrl;
	}
	public void setDocUrl(String docUrl) {
		DocUrl = docUrl;
	}
	public String getDocTitle() {
		return DocTitle;
	}
	public void setDocTitle(String docTitle) {
		DocTitle = docTitle;
	}
	String DocTitle;
	

}
