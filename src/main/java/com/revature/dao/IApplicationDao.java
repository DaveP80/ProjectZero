package com.revature.dao;

import com.revature.branch.Application;
import com.revature.branch.ApplicationStatus;

public interface IApplicationDao {

    public int insert(Application application);
    public int updateApplication(int appId, ApplicationStatus newAppStatus);
}
