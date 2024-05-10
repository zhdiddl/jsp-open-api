package call;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBCall {
     public static Connection connectDB() {

          // DB에 접속하려면 필요한 것: IP(url, domain), port, user, password, 인스턴스
          String url = "jdbc:mariadb://localhost:3306/wifi_db";
          String dbUserID = "wifi_user";
          String dbPassword = "wifi1234";

          // DB 연결 실행
          Connection connection = null;

          try { // try-catch 로 Class.forName을 감싸서 직접 예외 처리
               Class.forName("org.mariadb.jdbc.Driver"); // 1. 드라이버 파일이 있는 위치와 패키지를 로드
               connection = DriverManager.getConnection(url, dbUserID, dbPassword); // 2. DriverManager로 Connection 객체 생성

          } catch (SQLException | ClassNotFoundException e) { // 예외가 생기면 catch하는 부분
               e.printStackTrace();
          }
          return connection;
     }
}
