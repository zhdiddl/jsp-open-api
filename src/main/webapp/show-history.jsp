<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="access.WifiAccess" %>
<%@ page import="param.WifiParam" %>
<%@ page import="access.HistoryAccess" %>
<%@ page import="param.HistoryParam" %>
<%@ page import="call.APICall" %>
<%@ page import="call.DBCall" %>
<%@ page import="java.util.List" %>

<script src="https://code.jquery.com/jquery-3.5.1.js"></script>

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

        /* 버튼 */
        .btn-center {
                    display: inline-block;
                    padding: 5px 10px;
                    background-color: #4CAF50;
                    border: none;
                    color: white;
                    cursor: pointer;
                    border-radius: 5px;
        }
    </style>
</head>
<h1>위치 히스토리 목록</h1>
<%@ include file="menu.jsp"%>
<br>

<body>
<%
    HistoryAccess access = new HistoryAccess();
    List<HistoryParam> list = access.selectHistory();

    String idStr = request.getParameter("id");
    if (idStr != null) {
    int id = Integer.parseInt(idStr);
    access.deleteHistory(id);
    response.sendRedirect("show-history.jsp");
    }
    %>
    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>X좌표</th>
            <th>Y좌표</th>
            <th>조회일자</th>
            <th>비고</th>
        </tr>
        </thead>
        <tbody>
        <% if (list.isEmpty()) {%>
        <tr>
            <td colspan="5">위치 정보를 조회하신 이력이 없습니다.</td>
        </tr>
        <% } else { %>
        <% for (HistoryParam historyParam : list) { %>
        <tr>
            <td><%=historyParam.getId()%></td>
            <td><%=historyParam.getLnt()%></td>
            <td><%=historyParam.getLat()%></td>
            <td><%=historyParam.getSearchDttm()%></td>
            <td><button onclick="deleteHistory(<%=historyParam.getId()%>)">삭제</button></td>
        </tr>
        <% }} %>
        </tbody>
    </table>


    <script>
        function deleteHistory(ID) {
            if (confirm("데이터를 삭제하시겠습니까?")) {
                $.ajax({
                    url: "show-history.jsp",
                    data: {id : ID},
                    success: function () {
                        $('tr').filter(function() {
                            return $(this).find('td:first').text() == ID;
                        }).remove();
                    },
                    error: function (request, status, error) {
                        alert("code: " + request.status + "\n" + "message: " + request.responseText + "\n" + "error: " + error);
                    }
                })
        }
    }
    </script>
</body>