package param;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class HistoryParam {

     private int id;            // 아이디
     private String lnt;        // X좌표
     private String lat;        // Y좌표
     private String searchDttm; // 조회 일자

}
