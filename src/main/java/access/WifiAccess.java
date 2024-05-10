package access;

import call.DBCall;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import param.WifiParam;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WifiAccess {
     public static Connection connection;
     public static ResultSet resultSet;
     public static PreparedStatement preparedStatement;

     public static void createWifiDB(JsonArray jsonArray) throws SQLException { // JSON 데이터를 DB에 저장
          connection = null;
          preparedStatement = null;

          try {
               connection = DBCall.connectDB();
/*
               // 기존 데이터 삭제
               String deleteSQL = "DELETE FROM WIFI_DATA;";
               preparedStatement = connection.prepareStatement(deleteSQL);
               preparedStatement.executeUpdate();*/

               String sql = "INSERT INTO WIFI_DATA "
                         + " (X_SWIFI_MGR_NO, X_SWIFI_WRDOFC, X_SWIFI_MAIN_NM, X_SWIFI_ADRES1, "
                         + " X_SWIFI_ADRES2, X_SWIFI_INSTL_FLOOR, X_SWIFI_INSTL_TY, X_SWIFI_INSTL_MBY, X_SWIFI_SVC_SE, "
                         + " X_SWIFI_CMCWR, X_SWIFI_CNSTC_YEAR, X_SWIFI_INOUT_DOOR, X_SWIFI_REMARS3, LNT, "
                         + " LAT, WORK_DTTM) "
                         + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?); ";

               preparedStatement = connection.prepareStatement(sql);

               for (int i = 0; i < jsonArray.size(); i++) {
                    JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();

                    // row 출력명에 있던 데이터 가져오기
                    preparedStatement.setString(1, jsonObject.get("X_SWIFI_MGR_NO").getAsString());
                    preparedStatement.setString(2, jsonObject.get("X_SWIFI_WRDOFC").getAsString());
                    preparedStatement.setString(3, jsonObject.get("X_SWIFI_MAIN_NM").getAsString());
                    preparedStatement.setString(4, jsonObject.get("X_SWIFI_ADRES1").getAsString());
                    preparedStatement.setString(5, jsonObject.get("X_SWIFI_ADRES2").getAsString());
                    preparedStatement.setString(6, jsonObject.get("X_SWIFI_INSTL_FLOOR").getAsString());
                    preparedStatement.setString(7, jsonObject.get("X_SWIFI_INSTL_TY").getAsString());
                    preparedStatement.setString(8, jsonObject.get("X_SWIFI_INSTL_MBY").getAsString());
                    preparedStatement.setString(9, jsonObject.get("X_SWIFI_SVC_SE").getAsString());
                    preparedStatement.setString(10, jsonObject.get("X_SWIFI_CMCWR").getAsString());
                    preparedStatement.setString(11, jsonObject.get("X_SWIFI_CNSTC_YEAR").getAsString());
                    preparedStatement.setString(12, jsonObject.get("X_SWIFI_INOUT_DOOR").getAsString());
                    preparedStatement.setString(13, jsonObject.get("X_SWIFI_REMARS3").getAsString());
                    preparedStatement.setString(14, jsonObject.get("LNT").getAsString());
                    preparedStatement.setString(15, jsonObject.get("LAT").getAsString());
                    preparedStatement.setString(16, jsonObject.get("WORK_DTTM").getAsString());

                    // 실행 결과 int로 반환
                    int affectedRow = preparedStatement.executeUpdate(); // executeUpdate() => 내용 변경 쿼리 실행에 사용

                    if (affectedRow > 0) {
                         System.out.println(affectedRow + "개 저장 성공");
                    } else {
                         System.out.println("저장 실패");
                    }
               }
          } catch (SQLException e) { // 예외가 생기면 catch하는 부분
               e.printStackTrace();
          }
     }

     public List<WifiParam> createWifiList(String lnt, String lat) throws SQLException { // 사용자에게 데이터 받아 와이파이 리스트 반환
          connection = null;
          resultSet = null;
          preparedStatement = null;

          HistoryAccess.insertHistory(lnt, lat); // 조회 내용 저장
          List<WifiParam> nearbyWifiLocations = new ArrayList<>(); // 근처 와이파이 리스트 생성

          try {
               connection = DBCall.connectDB();

               // 사용자가 입력한 경도와 위도를 각 와이파이 데이터와 비교 => 내용 조회하는 SQL 명령문
               String sql = "SELECT * FROM (" +
                       "  SELECT *, ROUND(6371 * ACOS(" +
                       "    COS(RADIANS(?)) * COS(RADIANS(lat)) * COS(RADIANS(lnt) - RADIANS(?)) +" +
                       "    SIN(RADIANS(?)) * SIN(RADIANS(lat))" +
                       "  ), 4) AS distance" +
                       "  FROM WIFI_DATA" +
                       ") AS results " +
                       "ORDER BY distance " +
                       "LIMIT 20;";

               // SQL 명령을 실행하는 데 사용하는 preparedStatement 객체 만들기
               preparedStatement = connection.prepareStatement(sql);

               // 사용자가 입력한 경도와 위도 가져오기
               preparedStatement.setDouble(1, Double.parseDouble(lat));
               preparedStatement.setDouble(2, Double.parseDouble(lnt));
               preparedStatement.setDouble(3, Double.parseDouble(lat));

               // 실행 결과를 resultSet 객체로 반환
               resultSet = preparedStatement.executeQuery(); // executeQuery() => 조회 쿼리 실행에 사용

               // resultSet 내용을 읽어서 wifiParam 객체로 받은 후 리스트에 넣어주기
               while (resultSet.next()) {
                    WifiParam wifiParam = WifiParam.builder()
                            .distance(resultSet.getDouble("distance"))
                            .xSwifiMgrNo(resultSet.getString("x_swifi_mgr_no"))
                            .xSwifiWrdofc(resultSet.getString("x_swifi_wrdofc"))
                            .xSwifiMainNm(resultSet.getString("x_swifi_main_nm"))
                            .xSwifiAdres1(resultSet.getString("x_swifi_adres1"))
                            .xSwifiAdres2(resultSet.getString("x_swifi_adres2"))
                            .xSwifiInstlFloor(resultSet.getString("x_swifi_instl_floor"))
                            .xSwifiInstlTy(resultSet.getString("x_swifi_instl_ty"))
                            .xSwifiInstlMby(resultSet.getString("x_swifi_instl_mby"))
                            .xSwifiSvcSe(resultSet.getString("x_swifi_svc_se"))
                            .xSwifiCmcWr(resultSet.getString("x_swifi_cmcwr"))
                            .xSwifiCnstcYear(resultSet.getString("x_swifi_cnstc_year"))
                            .xSwifiInoutDoor(resultSet.getString("x_swifi_inout_door"))
                            .xSwifiRemars3(resultSet.getString("x_swifi_remars3"))
                            .lnt(resultSet.getString("lnt"))
                            .lat(resultSet.getString("lat"))
                            .workDttm(String.valueOf(resultSet.getTimestamp("work_dttm").toLocalDateTime()))
                            .build();
                    nearbyWifiLocations.add(wifiParam);
               }
          } catch (SQLException e) { // 예외가 생기면 catch하는 부분
               e.printStackTrace();
          }
          return nearbyWifiLocations;
     }
}
