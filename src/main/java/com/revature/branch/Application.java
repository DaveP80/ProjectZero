package com.revature.branch;

import com.revature.service.ApplicationService;

public class Application {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ApplicationStatus getApplicationStatus() {
        return applicationStatus;
    }

    public void setApplicationStatus(ApplicationStatus applicationStatus) {
        this.applicationStatus = applicationStatus;
    }

    private int id;
    private String username;
    private ApplicationStatus applicationStatus;

    public Application() {

    }

    public Application(int id, String username, ApplicationStatus applicationStatus) {
        this.id = id;
        this.username = username;
        this.applicationStatus = applicationStatus;
    }

    public Application(String username, ApplicationStatus applicationStatus) {
        this.username = username;
        this.applicationStatus = applicationStatus;
    }

    @Override
    public String toString() {
        return "Application{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", applicationStatus=" + applicationStatus +
                '}';
    }
}
