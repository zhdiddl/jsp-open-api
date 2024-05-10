package access;

import call.DBCall;
import param.HistoryParam;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HistoryAccess {

     public static Connection connection;
     public static ResultSet resultSet;
     public static PreparedStatement preparedStatement;

     public static void insertHistory(String lnt, String lat) throws SQLException { // DB 테이블에 입력
          connection = null;
          preparedStatement = null;


          try {
               connection = DBCall.connectDB();

               String sql = " INSERT INTO SEARCH_HISTORY (lnt, lat, search_dttm) VALUES ( ?, ?, CURRENT_TIMESTAMP) ";

               preparedStatement = connection.prepareStatement(sql);

               preparedStatement.setString(1, lnt);
               preparedStatement.setString(2, lat);
               preparedStatement.executeUpdate();
               System.out.println("데이터 조회 기록 저장 성공");

          } catch (SQLException e) {
               e.printStackTrace();
          }
     }

          public static List<HistoryParam> selectHistory() throws SQLException { // DB 테이블에서 조회
               connection = null;
               resultSet = null;
               preparedStatement = null;

               List<HistoryParam> searchHistory = new ArrayList<>();

               try {
                    connection = DBCall.connectDB();

                    String sql = " SELECT * FROM SEARCH_HISTORY ORDER BY ID DESC ";

                    preparedStatement = connection.prepareStatement(sql);
                    resultSet = preparedStatement.executeQuery();

                    while (resultSet.next()) {
                         HistoryParam historyParam = HistoryParam.builder()
                                 .id(resultSet.getInt("id"))
                                 .lnt(resultSet.getString("lnt"))
                                 .lat(resultSet.getString("lat"))
                                 .searchDttm(resultSet.getString("search_dttm"))
                                 .build();
                         searchHistory.add(historyParam);
                    }
               } catch (SQLException e) {
                    e.printStackTrace();
               }

               return searchHistory;
          }

          public static void deleteHistory (int id) throws SQLException { // DB 테이블에서 삭제
               connection = null;
               resultSet = null;
               preparedStatement = null;

               try {
                    connection = DBCall.connectDB();
                    String sql = " DELETE FROM SEARCH_HISTORY WHERE ID = ? ";

                    preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setInt(1, id);
                    preparedStatement.executeUpdate();

               } catch (SQLException e) {
                    e.printStackTrace();
               }
          }
}
