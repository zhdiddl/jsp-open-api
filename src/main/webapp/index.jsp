<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="access.WifiAccess" %>
<%@ page import="param.WifiParam" %>
<%@ page import="call.APICall" %>
<%@ page import="call.DBCall" %>
<%@ page import="java.util.List" %>

<html>
<head>
    <title>와이파이 정보 구하기</title>
    <style>


        /* 테이블 기본 스타일 */
        table {
            width: 100%;
            border-collapse: collapse;
            border: 1px solid grey;
        }

        /* 테이블 헤더 스타일 */
        th {
            background-color: #3CB371;
            color: white;
            border: 1px solid white;
            padding: 8px;
            text-align: center;
            font-size: 14px;
        }

        /* 테이블 데이터 스타일 */
        td {
            color: black;
            border: 1px solid #ddd;
            padding: 8px;
            text-align: center;
            font-size: 14px;
        }

        /* 테이블 데이터의 홀수 행 스타일 */
        tbody tr:nth-child(odd) {
            background-color: #ffffff;
        }

        /* 테이블 데이터의 짝수 행 스타일 */
        tbody tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        /* 마우스 오버 시 행의 배경색 변경 */
        tbody tr:hover {
            background-color: #c8c8c8;
        }
    </style>
</head>

<body>
    <h1>와이파이 정보 구하기</h1>
    <%@ include file="menu.jsp"%>
    <br>

<!-- 입력창 -->

    <form method="post" action="<%=request.getRequestURI()%>">
        <label for="lat">LAT:</label>
        <input type="text" name="lat" id="lat" placeholder="Latitude 값 입력" value="<%= request.getParameter("lat") != null ? request.getParameter("lat") : "0.0" %>">

        <label for="lnt">LNT:</label>
        <input type="text" name="lnt" id="lnt" placeholder="Longitude 값 입력" value="<%= request.getParameter("lnt") != null ? request.getParameter("lnt") : "0.0" %>">


        <!-- 버튼 -->
        <button id="getLocationBtn" type="button">내 위치 가져오기</button>
        <button type="submit">근처 Wifi 정보 보기</button>

<br>

<table>
    <thead>
    <tr>
        <th>거리(Km)</th>
        <th>관리번호</th>
        <th>자치구</th>
        <th>와이파이명</th>
        <th>도로명주소</th>
        <th>상세주소</th>
        <th>설치위치(층)</th>
        <th>설치유형</th>
        <th>설치기관</th>
        <th>서비스구분</th>
        <th>망종류</th>
        <th>설치년도</th>
        <th>실내외구분</th>
        <th>WIFI접속환경</th>
        <th>X좌표</th>
        <th>Y좌표</th>
        <th>작업일자</th>
    </tr>
    </thead>
    <tbody>
    <%
    String lat = request.getParameter("lat");
    String lnt = request.getParameter("lnt");
    boolean isInitialLoad = (lat == null || lat.isEmpty() || "0.0".equals(lat)) &&
    (lnt == null || lnt.isEmpty() || "0.0".equals(lnt));

    if (isInitialLoad) {
    out.println("<tr><td colspan='17' class='placeholder'>위치 정보를 입력한 후에 조회해 주세요.</td></tr>");
    } else {
    try {
    double dLat = Double.parseDouble(lat);
    double dLnt = Double.parseDouble(lnt);
    WifiAccess wifiAccess = new WifiAccess();
    List<WifiParam> list = wifiAccess.createWifiList(String.valueOf(dLnt), String.valueOf(dLat));

        if (list != null && !list.isEmpty()) {
        for (WifiParam wifiParam : list) {
        out.println("<tr>");
            out.println("<td>" + wifiParam.getDistance() + "</td>");
            out.println("<td>" + wifiParam.getXSwifiMgrNo() + "</td>");
            out.println("<td>" + wifiParam.getXSwifiWrdofc() + "</td>");
            out.println("<td>" + wifiParam.getXSwifiMainNm() + "</td>");
            out.println("<td>" + wifiParam.getXSwifiAdres1() + "</td>");
            out.println("<td>" + wifiParam.getXSwifiAdres2() + "</td>");
            out.println("<td>" + wifiParam.getXSwifiInstlFloor() + "</td>");
            out.println("<td>" + wifiParam.getXSwifiInstlTy() + "</td>");
            out.println("<td>" + wifiParam.getXSwifiInstlMby() + "</td>");
            out.println("<td>" + wifiParam.getXSwifiSvcSe() + "</td>");
            out.println("<td>" + wifiParam.getXSwifiCmcWr() + "</td>");
            out.println("<td>" + wifiParam.getXSwifiCnstcYear() + "</td>");
            out.println("<td>" + wifiParam.getXSwifiInoutDoor() + "</td>");
            out.println("<td>" + wifiParam.getXSwifiRemars3() + "</td>");
            out.println("<td>" + wifiParam.getLnt() + "</td>");
            out.println("<td>" + wifiParam.getLat() + "</td>");
            out.println("<td>" + wifiParam.getWorkDttm() + "</td>");
            out.println("</tr>");
        }
        } else {
        out.println("<tr><td colspan='17' class='placeholder'>데이터를 불러올 수 없습니다.</td></tr>");
        }
        } catch (NumberFormatException e) {
        out.println("<tr><td colspan='17' class='placeholder'>위도와 경도 값을 올바르게 입력하세요.</td></tr>");
        }
        }
        %>

    </tbody>
</table>

        <script>
            // "내 위치 가져오기" 버튼에 이벤트 리스너를 설정
            document.getElementById('getLocationBtn').addEventListener('click', function(event) {
                event.preventDefault();  // 기본 이벤트를 방지합니다.

                // 위치 정보 기능을 지원하는 경우
                if (navigator.geolocation) {
                    navigator.geolocation.getCurrentPosition(function(position) {
                        // 위치 가져오기 성공 -> 입력 창에 위치 정보가 나타남
                        document.getElementById('lat').value = position.coords.latitude.toFixed(6);
                        document.getElementById('lnt').value = position.coords.longitude.toFixed(6);
                    }, function(error) {
                        // 위치 가져오기 실패 -> 오류 메시지를 표시
                        alert('위치 정보를 가져오는 데 실패했습니다: ' + error.message);
                    });
                } else {
                    // 브라우저가 위치 정보 기능을 지원하지 않는 경우
                    alert('브라우저가 위치 정보 기능을 지원하지 않습니다.');
                }
            });
        </script>
</body>
</html>