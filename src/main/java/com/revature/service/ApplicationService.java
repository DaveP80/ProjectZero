package com.revature.service;

import com.revature.branch.Application;
import com.revature.branch.ApplicationStatus;
import com.revature.dao.ApplicationDao;
import com.revature.dao.IApplicationDao;

import java.util.List;

public class ApplicationService {

    private ApplicationDao applicationDao = new ApplicationDao();

    public ApplicationService() {

    }

    public Application createApplication(String username) {
        Application newApplication = new Application(username, ApplicationStatus.Waiting);
        int appId = applicationDao.insert(newApplication);
        if (appId > -1) {
            newApplication.setId(appId);
            return newApplication;
        }
        return null;
    }

    public List<Application> getOpenApplications(String username) {
        return applicationDao.getOpenApplications(username);
    }

    public int updateApplication(int appId, ApplicationStatus approved) {
        return applicationDao.updateApplication(appId, approved);
    }

    public boolean applicationExists(int appId) {
        return applicationDao.applicationExists(appId);
    }

    public String usernameById(int appId) {
        return applicationDao.usernameById(appId);
    }
}
