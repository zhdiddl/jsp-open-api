<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.io.IOException"%>
<%@ page import="com.google.gson.JsonArray, com.google.gson.JsonElement, com.google.gson.JsonParser"%>
<%@ page import="call.APICall"%>
<%@ page import="access.WifiAccess"%>

<head>
    <title>와이파이 정보 구하기</title>
</head>
<body>

<%
APICall api = new APICall(); // APICall 객체 생성
WifiAccess wifiAccess = new WifiAccess(); // WifiAccess 객체 생성
int wifiCount = 0; // 와이파이 개수를 담을 변수 선언

try {
JsonArray wifiData = api.callAPI(); // callAPI()를 사용해 와이파이 정보를 JSON 배열로 반환 받아서 담아둔다.
wifiAccess.createWifiDB(wifiData); // createWifiDB()를 사용해 JSON 배열을 데이터베이스에 저장한다.

wifiCount = api.getTotalCount(); // getTotalCont()를 사용해 와이파이 총 개수를 불러온다.
} catch (IOException e) {
out.println("<h2>API 호출 또는 데이터베이스 작업 중 오류 발생: " + e.getMessage() + "</h2>");
}

%>

<div style="text-align: center;">
    <h1><%=wifiCount %>개의 WIFI 정보를 정상적으로 저장하였습니다.</h1>
    <a href="index.jsp">홈으로 가기</a>
</div>

</body>
</html>