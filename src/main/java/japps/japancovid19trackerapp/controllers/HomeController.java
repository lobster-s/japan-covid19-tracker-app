package japps.japancovid19trackerapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import japps.japancovid19trackerapp.models.PrefectureData;
import japps.japancovid19trackerapp.services.CoronaVirusDataService;

@Controller
public class HomeController {
	
	@Autowired
	CoronaVirusDataService coronaVirusDataService;
	
	@GetMapping("/home")
	public String home(Model model) {
		List<PrefectureData> allPrefectures = coronaVirusDataService.getAllPrefectures(); //データのリスト(全件)
		
		int totalCases = allPrefectures.stream().mapToInt(prefecture -> prefecture.getTotalPatients()).sum(); //国内感染者数の累計
		int totalRecovery = allPrefectures.stream().mapToInt(prefecture -> prefecture.getTotalRecovery()).sum(); //国内回復者数の累計
		int totalDeath = allPrefectures.stream().mapToInt(prefecture -> prefecture.getTotalDeath()).sum(); //国内死者数の累計
		
		//viewに値を渡す
		model.addAttribute("prefectureData", allPrefectures);
		model.addAttribute("totalCases", totalCases);
		model.addAttribute("totalRecovery", totalRecovery);
		model.addAttribute("totalDeath", totalDeath);
		
		return "home";
	}
    
}