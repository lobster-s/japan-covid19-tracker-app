package japps.japancovid19trackerapp.services;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import japps.japancovid19trackerapp.models.PrefectureData;

@Service
public class CoronaVirusDataService {
	
	//ソースのURL
	private static final String DATA_URL = "https://raw.githubusercontent.com/code4sabae/covid19/master/data/covid19japan.csv";
	
	//データのリスト(全件)
	private List<PrefectureData> allPrefectures = new ArrayList<>();
	
	//setter&getter
	public List<PrefectureData> getAllPrefectures() {
		return allPrefectures;
	}
	public void setAllPrefectures(List<PrefectureData> allPrefectures) {
		this.allPrefectures = allPrefectures;
	}

	@PostConstruct
	@Scheduled(cron = "* * 1 * * *")
	public void fetchVirusData() throws IOException, InterruptedException {
		
		//httpclientの作成
		HttpClient client = HttpClient.newBuilder().build(); 
		
		//httprequestの作成
		HttpRequest request = HttpRequest.newBuilder() 
				.uri(URI.create(DATA_URL))
				.build();
		
		//requestを送信しresponseを取得
		HttpResponse<String> response = client 
				.send(request, HttpResponse.BodyHandlers.ofString());
		
		//responseから本文データを取得
		StringReader csvReader = new StringReader(response.body());
		
		//本文データの解析
		Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvReader);
		
		//データのリスト（全件）
		List<PrefectureData> newPrefectures = new ArrayList<>();
		
		//データの格納
		for (CSVRecord record : records) {
			//インスタンスの作成
			PrefectureData prefectureData = new PrefectureData();
			
			//setterで値を代入
		    prefectureData.setName(record.get("name_jp"));
		    prefectureData.setTotalPatients(Integer.parseInt(record.get("npatients")));
		    prefectureData.setCurrentPatients(Integer.parseInt(record.get("ncurrentpatients")));
		    prefectureData.setTotalRecovery(Integer.parseInt(record.get("nexits")));
		    prefectureData.setTotalDeath(Integer.parseInt(record.get("ndeaths")));
		    
		    //リストに追加
		    newPrefectures.add(prefectureData);
		}
		this.allPrefectures = newPrefectures;	
	}

}
