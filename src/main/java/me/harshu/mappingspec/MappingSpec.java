package me.harshu.mappingspec;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.stream.Collectors;

public class MappingSpec extends HttpServlet {
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
//        System.out.println("Mapping Spec");
//        System.out.println("Context path " + request.getContextPath());
//        System.out.println("Path info " + request.getPathInfo());
//        System.out.println("Query String " + request.getQueryString());
//        System.out.println("Request URL " + request.getRequestURL());
//        System.out.println("Protocol " + request.getProtocol());
//        System.out.println("Schema " + request.getScheme());
//        System.out.println("Remote Host " + request.getRemoteHost());
//        System.out.println("Remote Address " + request.getRemoteAddr());
//        System.out.println("Server name " + request.getServerName());
//        System.out.println("Server port " + request.getServerPort());
//        System.out.println("Local name " + request.getLocalName());
//        System.out.println("Local address " + request.getLocalAddr());
//        System.out.println("Local port " + request.getLocalPort());
//        System.out.println("Get method " + request.getMethod());
//    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestBody = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        JSONObject jsonBody = new JSONObject(requestBody);
        String name = jsonBody.getString("name");
        int id = jsonBody.getInt("id");

        System.out.println("ID : " + id);
        System.out.println("Name : " + name);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/student", "root", "");
            String sql = "INSERT INTO students (name, id) VALUES (?, ?)";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, name);
            statement.setInt(2, id);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Data saved successfully.");
            } else {
                System.err.println("Failed to save data.");
            }
            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
