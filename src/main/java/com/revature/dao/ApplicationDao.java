package com.revature.dao;

import com.revature.branch.Application;
import com.revature.branch.ApplicationStatus;
import util.ConnectionUtil;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ApplicationDao implements IApplicationDao {

    public String usernameById(int id) {
        Application app = new Application();
        String sql = "select username from applications where id = ?";
        Connection conn = ConnectionUtil.getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException s) {

        }

        return null;
    }

    public ArrayList<Application> getOpenApplications(String username) {
        ArrayList<Application> openApplications = new ArrayList<>();
        Connection conn = ConnectionUtil.getConnection();

        // for employees to view all applications in Waiting status
        String sql = "select * from applications where username = ? and application_status = ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setObject(2, ApplicationStatus.Waiting, Types.OTHER);

            ResultSet rs = stmt.executeQuery();

            // using while loop here allows us to iterate over a list of records
            while (rs.next()) {
                int id = rs.getInt(1);
                ApplicationStatus appStatus = ApplicationStatus.valueOf(rs.getString(3));
                openApplications.add(new Application(id, username, appStatus));
            }

            return openApplications;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public int insert(Application application) {
        // Let's use insert to practice creating a SQL operation

        // Step 1. Capture the DB connection by using the connection util

        Connection conn = ConnectionUtil.getConnection(); // Pulls in current connection from connection util

        // Step 2. Generate a sql statement like "Insert into ....."

        // Use ? to represent data we're looking to enter into our sql statment
        String sql = "INSERT INTO applications (username, application_status) values (?, ?) RETURNING applications.id";

        // Step 2b. Use a prepared statement to avoid SQL injection
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, application.getUsername());

            stmt.setObject(2, application.getApplicationStatus(), Types.OTHER);

            // Use a resultset to extract the primary key from the user object that was persisted

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                // if we return data, we can iterate over it
                // We need to capture the first column of data return (which is the id of the return user object)
                int id = rs.getInt(1);
                System.out.println("We returned an application with id #" + id);
                return id;
            }
        } catch (SQLException e) {
            System.out.println("Unable to insert user - sql exception");
            e.printStackTrace();
        }

        // If our database fails to insert we should return an index that we know our DB could never generate
        return -1;
    }

    public int updateApplication(int appId, ApplicationStatus approved) {
        Connection conn = ConnectionUtil.getConnection();
        String sql = "update applications set application_status = ? where id = ? returning applications.id";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setObject(1, ApplicationStatus.Approved, Types.OTHER);
            stmt.setInt(2, appId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int appIdUpdated = rs.getInt(1);
                return appIdUpdated;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    public boolean applicationExists(int appId) {
        Connection conn = ConnectionUtil.getConnection();
        String sql = "Select * from applications";
        return false;
    }
}
