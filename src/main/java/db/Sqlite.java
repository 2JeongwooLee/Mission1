package db;
import java.sql.*;
import java.util.*;


public class Sqlite {
	// WIFI 관련
	public List<WifiList> selectWifi(String reqLAT, String reqLNT) {
		String url = "jdbc:sqlite:C:\\Users\\abc\\Desktop\\Mission1\\Mission1\\test.db";
	    List<WifiList> list = new ArrayList<>();
	    // 드라이버 로드
		try {
	          Class.forName("org.sqlite.JDBC");
	    } catch (Exception e) {
	          e.printStackTrace();
	    }
	 
		// 스테이트먼트 객체 생성
		Connection connection = null;
		PreparedStatement preparedStatement = null;
	    ResultSet rs = null;
	    
	    // 커넥션 객체 생성
	    try {
	    	connection = DriverManager.getConnection(url);
	    	
	    	String sql = "select (6371 * acos(cos(CAST(LAT AS FLOAT) * 3.141592653589793 / 180.0) * cos(CAST(? AS FLOAT) * 3.141592653589793 / 180.0) "
	    			+ "	* cos((CAST(? AS FLOAT) * 3.141592653589793 / 180.0) - (CAST(LNT AS FLOAT) * 3.141592653589793 / 180.0)) + sin(CAST(LAT AS FLOAT) * 3.141592653589793 / 180.0) "
	    			+ "	* sin(CAST(? AS FLOAT) * 3.141592653589793 / 180.0))) as distance, *"
	    			+ " from TOTAL_WIFI_LIST "
	    			+ " order by distance asc "
	    			+ " limit 20";
	    	
	    	preparedStatement = connection.prepareStatement(sql);
	    	preparedStatement.setString(1, reqLAT);
	    	preparedStatement.setString(2, reqLNT);
	    	preparedStatement.setString(3, reqLAT);
	    	
	    	rs = preparedStatement.executeQuery();
	    	
	    	while(rs.next()) {
	    		String X_SWIFI_MGR_NO = rs.getString("X_SWIFI_MGR_NO");
	    		String X_SWIFI_WRDOFC = rs.getString("X_SWIFI_WRDOFC");
	    		String X_SWIFI_MAIN_NM = rs.getString("X_SWIFI_MAIN_NM");
	    		String X_SWIFI_ADRES1 = rs.getString("X_SWIFI_ADRES1");
	    		String X_SWIFI_ADRES2 = rs.getString("X_SWIFI_ADRES2");
	    		String X_SWIFI_INSTL_FLOOR = rs.getString("X_SWIFI_INSTL_FLOOR");
	    		String X_SWIFI_INSTL_TY = rs.getString("X_SWIFI_INSTL_TY");
	    		String X_SWIFI_INSTL_MBY = rs.getString("X_SWIFI_INSTL_MBY");
	    		String X_SWIFI_SVC_SE = rs.getString("X_SWIFI_SVC_SE");
	    		String X_SWIFI_CMCWR = rs.getString("X_SWIFI_CMCWR");
	    		String X_SWIFI_CNSTC_YEAR = rs.getString("X_SWIFI_CNSTC_YEAR");
	    		String X_SWIFI_INOUT_DOOR = rs.getString("X_SWIFI_INOUT_DOOR");
	    		String X_SWIFI_REMARS3 = rs.getString("X_SWIFI_REMARS3");
	    		String LAT = rs.getString("LAT");
	    		String LNT = rs.getString("LNT");
	    		String WORK_DTTM = rs.getString("WORK_DTTM");
	    		
	    		WifiList wifilist = new WifiList();
	    		
	    		wifilist.setX_SWIFI_MGR_NO(X_SWIFI_MGR_NO);
	    		wifilist.setX_SWIFI_WRDOFC(X_SWIFI_WRDOFC);
	    		wifilist.setX_SWIFI_MAIN_NM(X_SWIFI_MAIN_NM);
	    		wifilist.setX_SWIFI_ADRES1(X_SWIFI_ADRES1);
	    		wifilist.setX_SWIFI_ADRES2(X_SWIFI_ADRES2);
	    		wifilist.setX_SWIFI_INSTL_FLOOR(X_SWIFI_INSTL_FLOOR);
	    		wifilist.setX_SWIFI_INSTL_TY(X_SWIFI_INSTL_TY);
	    		wifilist.setX_SWIFI_INSTL_MBY(X_SWIFI_INSTL_MBY);
	    		wifilist.setX_SWIFI_SVC_SE(X_SWIFI_SVC_SE);
	    		wifilist.setX_SWIFI_CMCWR(X_SWIFI_CMCWR);
	    		wifilist.setX_SWIFI_CNSTC_YEAR(X_SWIFI_CNSTC_YEAR);
	    		wifilist.setX_SWIFI_INOUT_DOOR(X_SWIFI_INOUT_DOOR);
	    		wifilist.setX_SWIFI_REMARS3(X_SWIFI_REMARS3);
	    		wifilist.setLAT(LAT);
	    		wifilist.setLNT(LNT);
	    		wifilist.setWORK_DTTM(WORK_DTTM);
	    		
	    		list.add(wifilist);
	    	}
	    } catch(SQLException e) {
	    	e.printStackTrace();
	    } finally {
	    	try {
                if(rs != null && !rs.isClosed()) {
                    rs.close();;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if(preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if(connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
	    }
	    return list;
	}

	public void insertWifi(WifiList list) {
		String url = "jdbc:sqlite:C:\\Users\\abc\\Desktop\\Mission1\\Mission1\\test.db";
	    
	    // 드라이버 로드
		try {
	          Class.forName("org.sqlite.JDBC");
	    } catch (Exception e) {
	          e.printStackTrace();
	    }
	 
		// 스테이트먼트 객체 생성
		Connection connection = null;
		PreparedStatement preparedStatement = null;
	    ResultSet rs = null;
	    
	    // 커넥션 객체 생성
	    try {
	    	connection = DriverManager.getConnection(url);
	    	
	    	String sql = "insert into TOTAL_WIFI_LIST (X_SWIFI_MGR_NO, X_SWIFI_WRDOFC, X_SWIFI_MAIN_NM, X_SWIFI_ADRES1, X_SWIFI_ADRES2, X_SWIFI_INSTL_FLOOR, X_SWIFI_INSTL_TY, X_SWIFI_INSTL_MBY, X_SWIFI_SVC_SE"
	    			+ ", X_SWIFI_CMCWR, X_SWIFI_CNSTC_YEAR, X_SWIFI_INOUT_DOOR, X_SWIFI_REMARS3, LAT, LNT, WORK_DTTM) "
	    			+ " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	    	
	    	preparedStatement = connection.prepareStatement(sql);
	    	preparedStatement.setString(1, list.getX_SWIFI_MGR_NO());
	    	preparedStatement.setString(2, list.getX_SWIFI_WRDOFC());
	    	preparedStatement.setString(3, list.getX_SWIFI_MAIN_NM());
	    	preparedStatement.setString(4, list.getX_SWIFI_ADRES1());
	    	preparedStatement.setString(5, list.getX_SWIFI_ADRES2());
	    	preparedStatement.setString(6, list.getX_SWIFI_INSTL_FLOOR());
	    	preparedStatement.setString(7, list.getX_SWIFI_INSTL_TY());
	    	preparedStatement.setString(8, list.getX_SWIFI_INSTL_MBY());
	    	preparedStatement.setString(9, list.getX_SWIFI_SVC_SE());
	    	preparedStatement.setString(10, list.getX_SWIFI_CMCWR());
	    	preparedStatement.setString(11, list.getX_SWIFI_CNSTC_YEAR());
	    	preparedStatement.setString(12, list.getX_SWIFI_INOUT_DOOR());
	    	preparedStatement.setString(13, list.getX_SWIFI_REMARS3());
	    	preparedStatement.setString(14, list.getLNT());
	    	preparedStatement.setString(15, list.getLAT());
	    	preparedStatement.setString(16, list.getWORK_DTTM());
	    	
	    	int affected = preparedStatement.executeUpdate();
	    	
	    	if (affected > 0) {
                System.out.println("저장 성공");
            } else {
                System.out.println("저장 실패");
            }
	    	
	    } catch(SQLException e) {
	    	e.printStackTrace();
	    } finally {
	    	try {
                if(rs != null && !rs.isClosed()) {
                    rs.close();;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if(preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if(connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
	    }  
	}

	public void deleteWifi() {
		String url = "jdbc:sqlite:C:\\Users\\abc\\Desktop\\Mission1\\Mission1\\test.db";
	    
	    // 드라이버 로드
		try {
	          Class.forName("org.sqlite.JDBC");
	    } catch (Exception e) {
	          e.printStackTrace();
	    }
	 
		// 스테이트먼트 객체 생성
		Connection connection = null;
		PreparedStatement preparedStatement = null;
	    ResultSet rs = null;
	    
	    // 커넥션 객체 생성
	    try {
	    	connection = DriverManager.getConnection(url);
	    	
	    	String sql = "delete from TOTAL_WIFI_LIST";
	    	
	    	preparedStatement = connection.prepareStatement(sql);
	    	
	    	int affected = preparedStatement.executeUpdate();
	    	
	    	if (affected > 0) {
                System.out.println("전체 열 삭제 성공");
            } else {
                System.out.println("전체 열 삭제 실패");
            }
	    	
	    } catch(SQLException e) {
	    	e.printStackTrace();
	    } finally {
	    	try {
                if(rs != null && !rs.isClosed()) {
                    rs.close();;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if(preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if(connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
	    }  
	}
	
	// 상세보기
	public WifiList selectDetail(String MGR_NO) {
		String url = "jdbc:sqlite:C:\\Users\\abc\\Desktop\\Mission1\\Mission1\\test.db";
	    WifiList detail = new WifiList();
	    // 드라이버 로드
		try {
	          Class.forName("org.sqlite.JDBC");
	    } catch (Exception e) {
	          e.printStackTrace();
	    }
	 
		// 스테이트먼트 객체 생성
		Connection connection = null;
		PreparedStatement preparedStatement = null;
	    ResultSet rs = null;
	    
	    // 커넥션 객체 생성
	    try {
	    	connection = DriverManager.getConnection(url);
	    	
	    	String sql = "select * from TOTAL_WIFI_LIST where X_SWIFI_MGR_NO = ?";
	    	
	    	preparedStatement = connection.prepareStatement(sql);
	    	preparedStatement.setString(1, MGR_NO);
	    	
	    	rs = preparedStatement.executeQuery();
	    	
	    	if(rs.next()) {
	    		detail.setX_SWIFI_MGR_NO(rs.getString("X_SWIFI_MGR_NO"));
	    		detail.setX_SWIFI_WRDOFC(rs.getString("X_SWIFI_WRDOFC"));
	    		detail.setX_SWIFI_MAIN_NM(rs.getString("X_SWIFI_MAIN_NM"));
	    		detail.setX_SWIFI_ADRES1(rs.getString("X_SWIFI_ADRES1"));
	    		detail.setX_SWIFI_ADRES2(rs.getString("X_SWIFI_ADRES2"));
	    		detail.setX_SWIFI_INSTL_FLOOR(rs.getString("X_SWIFI_INSTL_FLOOR"));
	    		detail.setX_SWIFI_INSTL_TY(rs.getString("X_SWIFI_INSTL_TY"));
	    		detail.setX_SWIFI_INSTL_MBY(rs.getString("X_SWIFI_INSTL_MBY"));
	    		detail.setX_SWIFI_SVC_SE(rs.getString("X_SWIFI_SVC_SE"));
	    		detail.setX_SWIFI_CMCWR(rs.getString("X_SWIFI_CMCWR"));
	    		detail.setX_SWIFI_CNSTC_YEAR(rs.getString("X_SWIFI_CNSTC_YEAR"));
	    		detail.setX_SWIFI_INOUT_DOOR(rs.getString("X_SWIFI_INOUT_DOOR"));
	    		detail.setX_SWIFI_REMARS3(rs.getString("X_SWIFI_REMARS3"));
	    		detail.setLAT(rs.getString("LAT"));
	    		detail.setLNT(rs.getString("LNT"));
	    		detail.setWORK_DTTM(rs.getString("WORK_DTTM"));
	    		}
	    } catch(SQLException e) {
	    	e.printStackTrace();
	    } finally {
	    	try {
                if(rs != null && !rs.isClosed()) {
                    rs.close();;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if(preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if(connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
	    }
	    return detail;
	}
	
	// 히스토리 관련
	public void insertHistory(String LAT, String LNT) {
		String url = "jdbc:sqlite:C:\\Users\\abc\\Desktop\\Mission1\\Mission1\\test.db";
	    
	    // 드라이버 로드
		try {
	          Class.forName("org.sqlite.JDBC");
	    } catch (Exception e) {
	          e.printStackTrace();
	    }
	 
		// 스테이트먼트 객체 생성
		Connection connection = null;
		PreparedStatement preparedStatement = null;
	    ResultSet rs = null;
	    
	    // 커넥션 객체 생성
	    try {
	    	connection = DriverManager.getConnection(url);
	    	
	    	String sql = "insert into HISTORY_LIST (LAT, LNT, REFERENCE_DATE)"
	    			+ " values (?,?,datetime('now','localtime'))";
	    	
	    	preparedStatement = connection.prepareStatement(sql);
	    	preparedStatement.setString(1, LAT);
	    	preparedStatement.setString(2, LNT);
	    	
	    	int affected = preparedStatement.executeUpdate();
	    	
	    	if (affected > 0) {
                System.out.println("히스토리 저장 성공");
            } else {
                System.out.println("히스토리 저장 실패");
            }
	    	
	    } catch(SQLException e) {
	    	e.printStackTrace();
	    } finally {
	    	try {
                if(rs != null && !rs.isClosed()) {
                    rs.close();;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if(preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if(connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
	    }  
	}

	public void deleteHistory(String ID) {
		String url = "jdbc:sqlite:C:\\Users\\abc\\Desktop\\Mission1\\Mission1\\test.db";
	    
	    // 드라이버 로드
		try {
	          Class.forName("org.sqlite.JDBC");
	    } catch (Exception e) {
	          e.printStackTrace();
	    }
	 
		// 스테이트먼트 객체 생성
		Connection connection = null;
		PreparedStatement preparedStatement = null;
	    ResultSet rs = null;
	    
	    // 커넥션 객체 생성
	    try {
	    	connection = DriverManager.getConnection(url);
	    	
	    	String sql = "delete from HISTORY_LIST "
	    			+ " where HISTORY_ID = ?";
	    	
	    	preparedStatement = connection.prepareStatement(sql);
	    	preparedStatement.setInt(1, Integer.parseInt(ID));
	    	
	    	int affected = preparedStatement.executeUpdate();
	    	
	    	if (affected > 0) {
                System.out.println("히스토리 삭제 성공");
            } else {
                System.out.println("히스토리 삭제 실패");
            }
	    	
	    } catch(SQLException e) {
	    	e.printStackTrace();
	    } finally {
	    	try {
                if(rs != null && !rs.isClosed()) {
                    rs.close();;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if(preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if(connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
	    }  
	}
	
	public List<HistoryList> selectHistory() {
		String url = "jdbc:sqlite:C:\\Users\\abc\\Desktop\\Mission1\\Mission1\\test.db";
	    List<HistoryList> list = new ArrayList<>();
	    // 드라이버 로드
		try {
	          Class.forName("org.sqlite.JDBC");
	    } catch (Exception e) {
	          e.printStackTrace();
	    }
	 
		// 스테이트먼트 객체 생성
		Connection connection = null;
		PreparedStatement preparedStatement = null;
	    ResultSet rs = null;
	    
	    // 커넥션 객체 생성
	    try {
	    	connection = DriverManager.getConnection(url);
	    	
	    	String sql = "select * from HISTORY_LIST order by HISTORY_ID desc";
	    	
	    	preparedStatement = connection.prepareStatement(sql);
	    	
	    	rs = preparedStatement.executeQuery();
	    	
	    	while(rs.next()) {
	    		String HISTORY_ID = rs.getString("HISTORY_ID");
	    		String LAT = rs.getString("LAT");
	    		String LNT = rs.getString("LNT");
	    		String REFERENCE_DATE = rs.getString("REFERENCE_DATE");
	    		
	    		HistoryList history = new HistoryList();
	    		
	    		history.setHISTORY_ID(Integer.parseInt(HISTORY_ID));
	    		history.setLAT(LAT);
	    		history.setLNT(LNT);
	    		history.setREFERENCE_DATE(REFERENCE_DATE);
	    		
	    		list.add(history);
	    	}
	    } catch(SQLException e) {
	    	e.printStackTrace();
	    } finally {
	    	try {
                if(rs != null && !rs.isClosed()) {
                    rs.close();;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if(preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if(connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
	    }
	    return list;
	}

	// 북마크 그룹 관련
	public void insertBookmarkGroup(String bookmarkName, String sequence) {
		String url = "jdbc:sqlite:C:\\Users\\abc\\Desktop\\Mission1\\Mission1\\test.db";
	    
	    // 드라이버 로드
		try {
	          Class.forName("org.sqlite.JDBC");
	    } catch (Exception e) {
	          e.printStackTrace();
	    }
	 
		// 스테이트먼트 객체 생성
		Connection connection = null;
		PreparedStatement preparedStatement = null;
	    ResultSet rs = null;
	    
	    // 커넥션 객체 생성
	    try {
	    	connection = DriverManager.getConnection(url);
	    	
	    	String sql = "insert into BOOKMARK_GROUP (BOOKMARK_NAME, SEQUENCE, REGISTER_DATE, MODIFY_DATE)"
	    			+ " values (?,?,datetime('now','localtime'), ' ')";
	    	
	    	preparedStatement = connection.prepareStatement(sql);
	    	preparedStatement.setString(1, bookmarkName);
	    	preparedStatement.setInt(2, Integer.parseInt(sequence));
	    	
	    	int affected = preparedStatement.executeUpdate();
	    	
	    	if (affected > 0) {
                System.out.println("북마크 그룹 저장 성공");
            } else {
                System.out.println("북마크 그룹 저장 실패");
            }
	    	
	    } catch(SQLException e) {
	    	e.printStackTrace();
	    } finally {
	    	try {
                if(rs != null && !rs.isClosed()) {
                    rs.close();;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if(preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if(connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
	    }  
	}

	public List<BookmarkGroup> selectBookmarkGroup() {
		String url = "jdbc:sqlite:C:\\Users\\abc\\Desktop\\Mission1\\Mission1\\test.db";
	    List<BookmarkGroup> list = new ArrayList<>();
	    // 드라이버 로드
		try {
	          Class.forName("org.sqlite.JDBC");
	    } catch (Exception e) {
	          e.printStackTrace();
	    }
	 
		// 스테이트먼트 객체 생성
		Connection connection = null;
		PreparedStatement preparedStatement = null;
	    ResultSet rs = null;
	    
	    // 커넥션 객체 생성
	    try {
	    	connection = DriverManager.getConnection(url);
	    	
	    	String sql = "select * from BOOKMARK_GROUP order by SEQUENCE";
	    	
	    	preparedStatement = connection.prepareStatement(sql);
	    	
	    	rs = preparedStatement.executeQuery();
	    	
	    	while(rs.next()) {
	    		Integer BOOKMARK_ID = Integer.parseInt(rs.getString("BOOKMARK_ID"));
	    		String BOOKMARK_NAME = rs.getString("BOOKMARK_NAME");
	    		Integer SEQUENCE  = Integer.parseInt(rs.getString("SEQUENCE"));
	    		String REGISTER_DATE = rs.getString("REGISTER_DATE");
	    		String MODIFY_DATE = rs.getString("MODIFY_DATE");
	    		
	    		BookmarkGroup bookmarklist = new BookmarkGroup();
	    		
	    		bookmarklist.setBOOKMARK_ID(BOOKMARK_ID);
	    		bookmarklist.setBOOKMARK_NAME(BOOKMARK_NAME);
	    		bookmarklist.setSEQUENCE(SEQUENCE);
	    		bookmarklist.setREGISTER_DATE(REGISTER_DATE);
	    		bookmarklist.setMODIFY_DATE(MODIFY_DATE);
	    		
	    		list.add(bookmarklist);
	    	}
	    } catch(SQLException e) {
	    	e.printStackTrace();
	    } finally {
	    	try {
                if(rs != null && !rs.isClosed()) {
                    rs.close();;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if(preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if(connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
	    }
	    return list;
	}
	
	public BookmarkGroup selectBookmarkGroup(String id) {
		String url = "jdbc:sqlite:C:\\Users\\abc\\Desktop\\Mission1\\Mission1\\test.db";
	    BookmarkGroup bookmark = new BookmarkGroup();
	    // 드라이버 로드
		try {
	          Class.forName("org.sqlite.JDBC");
	    } catch (Exception e) {
	          e.printStackTrace();
	    }
	 
		// 스테이트먼트 객체 생성
		Connection connection = null;
		PreparedStatement preparedStatement = null;
	    ResultSet rs = null;
	    
	    // 커넥션 객체 생성
	    try {
	    	connection = DriverManager.getConnection(url);
	    	
	    	String sql = "select * from BOOKMARK_GROUP where BOOKMARK_ID = ?";
	    	
	    	preparedStatement = connection.prepareStatement(sql);
	    	preparedStatement.setInt(1, Integer.parseInt(id));
	    	
	    	rs = preparedStatement.executeQuery();
	    	
	    	if(rs.next()) {
	    		Integer BOOKMARK_ID = Integer.parseInt(rs.getString("BOOKMARK_ID"));
	    		String BOOKMARK_NAME = rs.getString("BOOKMARK_NAME");
	    		Integer SEQUENCE  = Integer.parseInt(rs.getString("SEQUENCE"));
	    		String REGISTER_DATE = rs.getString("REGISTER_DATE");
	    		String MODIFY_DATE = rs.getString("MODIFY_DATE");
	    		
	    		bookmark.setBOOKMARK_ID(BOOKMARK_ID);
	    		bookmark.setBOOKMARK_NAME(BOOKMARK_NAME);
	    		bookmark.setSEQUENCE(SEQUENCE);
	    		bookmark.setREGISTER_DATE(REGISTER_DATE);
	    		bookmark.setMODIFY_DATE(MODIFY_DATE);
	    	}
	    } catch(SQLException e) {
	    	e.printStackTrace();
	    } finally {
	    	try {
                if(rs != null && !rs.isClosed()) {
                    rs.close();;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if(preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if(connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
	    }
	    return bookmark;
	}

	public void updateBookmarkGroup(String id, String bookmarkName, String sequence) {
		String url = "jdbc:sqlite:C:\\Users\\abc\\Desktop\\Mission1\\Mission1\\test.db";
	    
	    // 드라이버 로드
		try {
	          Class.forName("org.sqlite.JDBC");
	    } catch (Exception e) {
	          e.printStackTrace();
	    }
	 
		// 스테이트먼트 객체 생성
		Connection connection = null;
		PreparedStatement preparedStatement = null;
	    ResultSet rs = null;
	    
	    // 커넥션 객체 생성
	    try {
	    	connection = DriverManager.getConnection(url);
	    	
	    	String sql = " update BOOKMARK_GROUP set BOOKMARK_NAME = ?, SEQUENCE = ?, MODIFY_DATE = datetime('now','localtime')  "
	    			+ " where BOOKMARK_ID = ?";
	    	
	    	preparedStatement = connection.prepareStatement(sql);
	    	preparedStatement.setString(1, bookmarkName);
	    	preparedStatement.setInt(2, Integer.parseInt(sequence));
	    	preparedStatement.setInt(3, Integer.parseInt(id));
	    	
	    	int affected = preparedStatement.executeUpdate();
	    	
	    	if (affected > 0) {
                System.out.println("북마크 수정 성공");
            } else {
                System.out.println("북마크 수정 실패");
            }
	    	
	    } catch(SQLException e) {
	    	e.printStackTrace();
	    } finally {
	    	try {
                if(rs != null && !rs.isClosed()) {
                    rs.close();;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if(preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if(connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
	    }  
	}
	
	public void deleteBookmarkGroup(String id) {
		String url = "jdbc:sqlite:C:\\Users\\abc\\Desktop\\Mission1\\Mission1\\test.db";
	    
	    // 드라이버 로드
		try {
	          Class.forName("org.sqlite.JDBC");
	    } catch (Exception e) {
	          e.printStackTrace();
	    }
	 
		// 스테이트먼트 객체 생성
		Connection connection = null;
		PreparedStatement preparedStatement = null;
	    ResultSet rs = null;
	    
	    // 커넥션 객체 생성
	    try {
	    	connection = DriverManager.getConnection(url);
	    	
	    	String sql = "delete from BOOKMARK_GROUP where BOOKMARK_ID = ? ";
	    	
	    	preparedStatement = connection.prepareStatement(sql);
	    	preparedStatement.setInt(1, Integer.parseInt(id));
	    	
	    	int affected = preparedStatement.executeUpdate();
	    	
	    	if (affected > 0) {
                System.out.println("북마크 그룹 삭제 성공");
            } else {
                System.out.println("북마크 그룹 삭제 실패");
            }
	    	
	    } catch(SQLException e) {
	    	e.printStackTrace();
	    } finally {
	    	try {
                if(rs != null && !rs.isClosed()) {
                    rs.close();;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if(preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if(connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
	    }  
	}
	
	// 북마크 관련
	public void insertBookmarkList(String bookmarkName, String wifiName) {
		String url = "jdbc:sqlite:C:\\Users\\abc\\Desktop\\Mission1\\Mission1\\test.db";
	    
	    // 드라이버 로드
		try {
	          Class.forName("org.sqlite.JDBC");
	    } catch (Exception e) {
	          e.printStackTrace();
	    }
	 
		// 스테이트먼트 객체 생성
		Connection connection = null;
		PreparedStatement preparedStatement = null;
	    ResultSet rs = null;
	    
	    // 커넥션 객체 생성
	    try {
	    	connection = DriverManager.getConnection(url);
	    	
	    	String sql = "insert into BOOKMARK_LIST (BOOKMARK_NAME, WIFI_NAME, REGISTER_DATE)"
	    			+ " values (?,?,datetime('now','localtime'))";
	    	
	    	preparedStatement = connection.prepareStatement(sql);
	    	preparedStatement.setString(1, bookmarkName);
	    	preparedStatement.setString(2, wifiName);
	    	
	    	int affected = preparedStatement.executeUpdate();
	    	
	    	if (affected > 0) {
                System.out.println("북마크 목록 저장 성공");
            } else {
                System.out.println("북마크 목록 저장 실패");
            }
	    	
	    } catch(SQLException e) {
	    	e.printStackTrace();
	    } finally {
	    	try {
                if(rs != null && !rs.isClosed()) {
                    rs.close();;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if(preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if(connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
	    }  
	}

	public List<Bookmark> selectBookmarkList() {
		String url = "jdbc:sqlite:C:\\Users\\abc\\Desktop\\Mission1\\Mission1\\test.db";
	    List<Bookmark> list = new ArrayList<>();
	    // 드라이버 로드
		try {
	          Class.forName("org.sqlite.JDBC");
	    } catch (Exception e) {
	          e.printStackTrace();
	    }
	 
		// 스테이트먼트 객체 생성
		Connection connection = null;
		PreparedStatement preparedStatement = null;
	    ResultSet rs = null;
	    
	    // 커넥션 객체 생성
	    try {
	    	connection = DriverManager.getConnection(url);
	    	
	    	String sql = "select * from BOOKMARK_LIST order by BOOKMARK_LIST_ID";
	    	
	    	preparedStatement = connection.prepareStatement(sql);
	    	
	    	rs = preparedStatement.executeQuery();
	    	
	    	while(rs.next()) {
	    		Integer BOOKMARK_LIST_ID = rs.getInt("BOOKMARK_LIST_ID");
	    		String BOOKMARK_NAME = rs.getString("BOOKMARK_NAME");
	    		String WIFI_NAME = rs.getString("WIFI_NAME");
	    		String REGISTER_DATE = rs.getString("REGISTER_DATE");
	    		
	    		Bookmark bookmark = new Bookmark();
	    		
	    		bookmark.setBOOKMARK_LIST_ID(BOOKMARK_LIST_ID);
	    		bookmark.setBOOKMARK_NAME(BOOKMARK_NAME);
	    		bookmark.setWIFI_NAME(WIFI_NAME);
	    		bookmark.setREGISTER_DATE(REGISTER_DATE);
	    		
	    		
	    		list.add(bookmark);
	    	}
	    } catch(SQLException e) {
	    	e.printStackTrace();
	    } finally {
	    	try {
                if(rs != null && !rs.isClosed()) {
                    rs.close();;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if(preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if(connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
	    }
	    return list;
	}
	
	public Bookmark selectBookmarkList(String id) {
		String url = "jdbc:sqlite:C:\\Users\\abc\\Desktop\\Mission1\\Mission1\\test.db";
	    Bookmark bookmark = new Bookmark();
	    // 드라이버 로드
		try {
	          Class.forName("org.sqlite.JDBC");
	    } catch (Exception e) {
	          e.printStackTrace();
	    }
	 
		// 스테이트먼트 객체 생성
		Connection connection = null;
		PreparedStatement preparedStatement = null;
	    ResultSet rs = null;
	    
	    // 커넥션 객체 생성
	    try {
	    	connection = DriverManager.getConnection(url);
	    	
	    	String sql = "select * from BOOKMARK_LIST where BOOKMARK_LIST_ID = ?";
	    	
	    	preparedStatement = connection.prepareStatement(sql);
	    	preparedStatement.setInt(1, Integer.parseInt(id));
	    	
	    	rs = preparedStatement.executeQuery();
	    	
	    	if(rs.next()) {
	    		Integer BOOKMARK_LIST_ID = rs.getInt("BOOKMARK_LIST_ID");
	    		String BOOKMARK_NAME = rs.getString("BOOKMARK_NAME");
	    		String WIFI_NAME = rs.getString("WIFI_NAME");
	    		String REGISTER_DATE = rs.getString("REGISTER_DATE");

	    		bookmark.setBOOKMARK_LIST_ID(BOOKMARK_LIST_ID);
	    		bookmark.setBOOKMARK_NAME(BOOKMARK_NAME);
	    		bookmark.setWIFI_NAME(WIFI_NAME);
	    		bookmark.setREGISTER_DATE(REGISTER_DATE);
	    	
	    	}
	    } catch(SQLException e) {
	    	e.printStackTrace();
	    } finally {
	    	try {
                if(rs != null && !rs.isClosed()) {
                    rs.close();;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if(preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if(connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
	    }
	    return bookmark;
	}

	public void deleteBookmarkList(String id) {
		String url = "jdbc:sqlite:C:\\Users\\abc\\Desktop\\Mission1\\Mission1\\test.db";
	    
	    // 드라이버 로드
		try {
	          Class.forName("org.sqlite.JDBC");
	    } catch (Exception e) {
	          e.printStackTrace();
	    }
	 
		// 스테이트먼트 객체 생성
		Connection connection = null;
		PreparedStatement preparedStatement = null;
	    ResultSet rs = null;
	    
	    // 커넥션 객체 생성
	    try {
	    	connection = DriverManager.getConnection(url);
	    	
	    	String sql = "delete from BOOKMARK_LIST where BOOKMARK_LIST_ID = ? ";
	    	
	    	preparedStatement = connection.prepareStatement(sql);
	    	preparedStatement.setInt(1, Integer.parseInt(id));
	    	
	    	int affected = preparedStatement.executeUpdate();
	    	
	    	if (affected > 0) {
                System.out.println("북마크 삭제 성공");
            } else {
                System.out.println("북마크 삭제 실패");
            }
	    	
	    } catch(SQLException e) {
	    	e.printStackTrace();
	    } finally {
	    	try {
                if(rs != null && !rs.isClosed()) {
                    rs.close();;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if(preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if(connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
	    }  
	}
}
