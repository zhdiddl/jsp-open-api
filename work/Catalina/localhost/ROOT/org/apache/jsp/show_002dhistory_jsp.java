/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.5.100
 * Generated at: 2024-05-08 09:20:44 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import access.WifiAccess;
import param.WifiParam;
import access.HistoryAccess;
import param.HistoryParam;
import call.APICall;
import call.DBCall;
import java.util.List;

public final class show_002dhistory_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(1);
    _jspx_dependants.put("/menu.jsp", Long.valueOf(1715071302595L));
  }

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.LinkedHashSet<>(3);
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = new java.util.LinkedHashSet<>(7);
    _jspx_imports_classes.add("call.APICall");
    _jspx_imports_classes.add("call.DBCall");
    _jspx_imports_classes.add("java.util.List");
    _jspx_imports_classes.add("param.HistoryParam");
    _jspx_imports_classes.add("access.WifiAccess");
    _jspx_imports_classes.add("param.WifiParam");
    _jspx_imports_classes.add("access.HistoryAccess");
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    final java.lang.String _jspx_method = request.getMethod();
    if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSP들은 오직 GET, POST 또는 HEAD 메소드만을 허용합니다. Jasper는 OPTIONS 메소드 또한 허용합니다.");
      return;
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<script src=\"https://code.jquery.com/jquery-3.5.1.js\"></script>\r\n");
      out.write("\r\n");
      out.write("<head>\r\n");
      out.write("    <title>와이파이 정보 구하기</title>\r\n");
      out.write("    <style>\r\n");
      out.write("        /* 테이블 기본 스타일 */\r\n");
      out.write("        table {\r\n");
      out.write("            width: 100%;\r\n");
      out.write("            border-collapse: collapse;\r\n");
      out.write("            border: 1px solid grey;\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        /* 테이블 헤더 스타일 */\r\n");
      out.write("        th {\r\n");
      out.write("            background-color: #3CB371;\r\n");
      out.write("            color: white;\r\n");
      out.write("            border: 1px solid white;\r\n");
      out.write("            padding: 8px;\r\n");
      out.write("            text-align: center;\r\n");
      out.write("            font-size: 14px;\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        /* 테이블 데이터 스타일 */\r\n");
      out.write("        td {\r\n");
      out.write("            color: black;\r\n");
      out.write("            border: 1px solid #ddd;\r\n");
      out.write("            padding: 8px;\r\n");
      out.write("            text-align: center;\r\n");
      out.write("            font-size: 14px;\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        /* 테이블 데이터의 홀수 행 스타일 */\r\n");
      out.write("        tbody tr:nth-child(odd) {\r\n");
      out.write("            background-color: #ffffff;\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        /* 테이블 데이터의 짝수 행 스타일 */\r\n");
      out.write("        tbody tr:nth-child(even) {\r\n");
      out.write("            background-color: #f9f9f9;\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        /* 마우스 오버 시 행의 배경색 변경 */\r\n");
      out.write("        tbody tr:hover {\r\n");
      out.write("            background-color: #c8c8c8;\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        /* 버튼 */\r\n");
      out.write("        .btn-center {\r\n");
      out.write("                    display: inline-block;\r\n");
      out.write("                    padding: 5px 10px;\r\n");
      out.write("                    background-color: #4CAF50;\r\n");
      out.write("                    border: none;\r\n");
      out.write("                    color: white;\r\n");
      out.write("                    cursor: pointer;\r\n");
      out.write("                    border-radius: 5px;\r\n");
      out.write("        }\r\n");
      out.write("    </style>\r\n");
      out.write("</head>\r\n");
      out.write("<h1>위치 히스토리 목록</h1>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<head>\r\n");
      out.write("    <title>메뉴</title>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("<div>\r\n");
      out.write("    <!-- 홈 링크 -->\r\n");
      out.write("    <a href=\"index.jsp\">홈</a> |\r\n");
      out.write("\r\n");
      out.write("    <!-- 위치 히스토리 목록 링크 -->\r\n");
      out.write("    <a href=\"show-history.jsp\">위치 히스토리 목록</a> |\r\n");
      out.write("\r\n");
      out.write("    <!-- Open API 와이파이 정보 가져오기 링크 -->\r\n");
      out.write("    <a href=\"fetch-wifi-data.jsp\">Open API 와이파이 정보 가져오기</a>\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("</body>");
      out.write("\r\n");
      out.write("<br>\r\n");
      out.write("\r\n");
      out.write("<body>\r\n");

    HistoryAccess access = new HistoryAccess();
    List<HistoryParam> list = access.selectHistory();

    String idStr = request.getParameter("id");
    if (idStr != null) {
    int id = Integer.parseInt(idStr);
    access.deleteHistory(id);
    response.sendRedirect("show-history.jsp");
    }
    
      out.write("\r\n");
      out.write("    <table>\r\n");
      out.write("        <thead>\r\n");
      out.write("        <tr>\r\n");
      out.write("            <th>ID</th>\r\n");
      out.write("            <th>X좌표</th>\r\n");
      out.write("            <th>Y좌표</th>\r\n");
      out.write("            <th>조회일자</th>\r\n");
      out.write("            <th>비고</th>\r\n");
      out.write("        </tr>\r\n");
      out.write("        </thead>\r\n");
      out.write("        <tbody>\r\n");
      out.write("        ");
 if (list.isEmpty()) {
      out.write("\r\n");
      out.write("        <tr>\r\n");
      out.write("            <td colspan=\"5\">위치 정보를 조회하신 이력이 없습니다.</td>\r\n");
      out.write("        </tr>\r\n");
      out.write("        ");
 } else { 
      out.write("\r\n");
      out.write("        ");
 for (HistoryParam historyParam : list) { 
      out.write("\r\n");
      out.write("        <tr>\r\n");
      out.write("            <td>");
      out.print(historyParam.getId());
      out.write("</td>\r\n");
      out.write("            <td>");
      out.print(historyParam.getLnt());
      out.write("</td>\r\n");
      out.write("            <td>");
      out.print(historyParam.getLat());
      out.write("</td>\r\n");
      out.write("            <td>");
      out.print(historyParam.getSearchDttm());
      out.write("</td>\r\n");
      out.write("            <td><button onclick=\"deleteHistory(");
      out.print(historyParam.getId());
      out.write(")\">삭제</button></td>\r\n");
      out.write("        </tr>\r\n");
      out.write("        ");
 }} 
      out.write("\r\n");
      out.write("        </tbody>\r\n");
      out.write("    </table>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("    <script>\r\n");
      out.write("        function deleteHistory(ID) {\r\n");
      out.write("            if (confirm(\"데이터를 삭제하시겠습니까?\")) {\r\n");
      out.write("                $.ajax({\r\n");
      out.write("                    url: \"show-history.jsp\",\r\n");
      out.write("                    data: {id : ID},\r\n");
      out.write("                    success: function () {\r\n");
      out.write("                        $('tr').filter(function() {\r\n");
      out.write("                            return $(this).find('td:first').text() == ID;\r\n");
      out.write("                        }).remove();\r\n");
      out.write("                    },\r\n");
      out.write("                    error: function (request, status, error) {\r\n");
      out.write("                        alert(\"code: \" + request.status + \"\\n\" + \"message: \" + request.responseText + \"\\n\" + \"error: \" + error);\r\n");
      out.write("                    }\r\n");
      out.write("                })\r\n");
      out.write("        }\r\n");
      out.write("    }\r\n");
      out.write("    </script>\r\n");
      out.write("    </body>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
