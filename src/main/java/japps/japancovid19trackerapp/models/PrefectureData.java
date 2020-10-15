package japps.japancovid19trackerapp.models;

public class PrefectureData {

    //プロパティ
	private String name; //都道府県名
	private int totalPatients; //感染者数の累計
	private int currentPatients; //現在の感染者数
	private int totalRecovery; //感染後の回復者
	private int totalDeath; //死者数
	
	//getters&setters
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getTotalPatients() {
		return totalPatients;
	}
	public void setTotalPatients(int totalPatients) {
		this.totalPatients = totalPatients;
	}
	public int getCurrentPatients() {
		return currentPatients;
	}
	public void setCurrentPatients(int currentPatients) {
		this.currentPatients = currentPatients;
	}
	public int getTotalRecovery() {
		return totalRecovery;
	}
	public void setTotalRecovery(int totalRecovery) {
		this.totalRecovery = totalRecovery;
	}
	public int getTotalDeath() {
		return totalDeath;
	}
	public void setTotalDeath(int totalDeath) {
		this.totalDeath = totalDeath;
	}
    
}
