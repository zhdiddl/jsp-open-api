package call;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.Request;
import okhttp3.ResponseBody;

import java.io.IOException;
import java.net.URL;

public class APICall {
     private static final String API_KEY = "7a5671494c6c616c313030445555676f";
     private static final String API_URL = "http://openapi.seoul.go.kr:8088/" + API_KEY + "/json/TbPublicWifiInfo/";
     private static final OkHttpClient client = new OkHttpClient();
     private static int totalWifiCount = 0;

     public int getTotalCount() throws IOException { // 오픈 API에서 list_total_count 데이터 가져오기

          URL url = new URL(API_URL + "1/1");

          Request request = new Request.Builder().url(url).build();
          Response response = client.newCall(request).execute();

          if (response.isSuccessful()) {
               ResponseBody body = response.body();

               if (body != null) {
                    try {
                         String jsonData = body.string();
                         JsonElement jsonElement = JsonParser.parseString(jsonData);

                         totalWifiCount = jsonElement.getAsJsonObject().get("TbPublicWifiInfo")
                                                  .getAsJsonObject().get("list_total_count").getAsInt();
                    } catch (JsonSyntaxException | NullPointerException e) {
                         System.out.println("JSON 파싱 오류 또는 NULL 참조: " + e.getMessage());
                    }
               } else {
                    System.out.println("응답 내용 없음");
               }
          } else {
               System.out.println("응답 실패: " + response.code());
          }

          return totalWifiCount;
     }

     public JsonArray callAPI() throws IOException {
          JsonArray jsonArray = new JsonArray();
          int totalCount = getTotalCount(); // 총 데이터 수를 먼저 조회

          for (int i = 1; i <= totalCount; i += 1000) {
               String apiUrl = API_URL + i + "/" + Math.min(i + 999, totalCount);
               Request request = new Request.Builder().url(apiUrl).build();
               Response response = client.newCall(request).execute();

               if (response.isSuccessful() && response.body() != null) {
                    String jsonData = response.body().string();
                    JsonElement jsonElement = JsonParser.parseString(jsonData);
                    JsonArray data = jsonElement.getAsJsonObject().get("TbPublicWifiInfo")
                            .getAsJsonObject().get("row").getAsJsonArray();
                    jsonArray.addAll(data); // 수집된 데이터를 jsonArray에 추가
               }
          }
          return jsonArray; // 완성된 JsonArray 반환
     }
}