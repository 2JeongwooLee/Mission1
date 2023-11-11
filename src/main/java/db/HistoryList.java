package db;
import lombok.Data;

@Data
public class HistoryList {
	private Integer HISTORY_ID; 
	private String LAT; 
	private String LNT; 
	private String REFERENCE_DATE;
}
