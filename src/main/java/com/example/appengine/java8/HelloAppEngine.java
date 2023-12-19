/*
 * Copyright 2017 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.appengine.java8;

import com.google.appengine.api.utils.SystemProperty;
import java.io.IOException;
import java.util.Properties;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
// With @WebServlet annotation the webapp/WEB-INF/web.xml is no longer required.
@WebServlet(name = "HelloAppEngine", value = "/hello")
public class HelloAppEngine extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        Connection conn = null;
        String jdbcUrl = "jdbc:sqlserver://localhost;"
    + "databaseName=quanlybanram;"
    + "socketFactoryClass=com.google.cloud.sql.sqlserver.SocketFactory;"
    + "ipTypes=PRIVATE;"
    + "socketFactoryConstructorArg=java-cloud-app-407108:asia-east2:banram;"
    + "user=sqlserver;"
    + "password=12345;"
    + "encrypt=true;"
    + "trustServerCertificate=true"; // Added this line

        try {
            // Thực hiện kết nối SQL Server
           
        
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        conn = DriverManager.getConnection(jdbcUrl);
            // Kiểm tra kết nối thành công
            if (conn != null) {
                out.println("Connect succes!");
                conn.close(); // Đóng kết nối
            } else {
                out.println("Failed");
            }
        } catch (SQLException | ClassNotFoundException e) {
            out.println("Failed" + e.getMessage());
        }
  }

  public static String getInfo() {
    return "Version: " + System.getProperty("java.version")
          + " OS: " + System.getProperty("os.name")
          + " User: " + System.getProperty("user.name");
  }

}
