package db;
import lombok.Data;

@Data
public class BookmarkGroup {
	private Integer BOOKMARK_ID; 
	private String BOOKMARK_NAME; 
	private Integer SEQUENCE; 
	private String REGISTER_DATE;
	private String MODIFY_DATE;
}
