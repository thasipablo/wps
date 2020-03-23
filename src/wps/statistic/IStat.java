package wps.statistic;

import java.util.ArrayList;
import java.util.HashMap;

public interface IStat {
	
	abstract ArrayList<HashMap<String, String>> getResultByPromotion(String promotion, String faculty, int semester, String year);

	abstract ArrayList<HashMap<String, String>> getGeneralResult(int semester, String year);
	
}
