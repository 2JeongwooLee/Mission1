package db;
import okhttp3.*;
import com.google.gson.*;

public class Okhttp3 {
	static public WifiList[] Get(int startNum, int lastNum) {
		try {
			
			String url = "http://openapi.seoul.go.kr:8088/6a53475661776a64383279766a6364/json/TbPublicWifiInfo/" + startNum + "/" + lastNum;
			
			// OkHttp 클라이언트 객체 생성
			OkHttpClient client = new OkHttpClient();

			// GET 요청 객체 생성
			Request.Builder builder = new Request.Builder().url(url).get();
			Request request = builder.build();

			// OkHttp 클라이언트로 GET 요청 객체 전송
			Response response = client.newCall(request).execute();
			if (response.isSuccessful()) {
				// 응답 받아서 처리
				ResponseBody body = response.body();
				if (body != null) {
					String jsonStr = body.string();
					Gson gson = new Gson();
					try {
						WifiList[] list = gson.fromJson(jsonStr.substring(jsonStr.indexOf("["), jsonStr.indexOf("]") + 1), WifiList[].class);
						return list;
					} catch(Exception e) {
						System.out.println("데이터 없음");
					}	
				}
			} else {
				System.err.println("Error Occurred");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
