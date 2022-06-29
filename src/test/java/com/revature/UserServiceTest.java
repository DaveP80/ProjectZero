package com.revature;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

import com.revature.branch.Account;
import org.junit.Before;
import org.junit.Test;

import com.revature.branch.Role;
import com.revature.branch.User;
import com.revature.dao.UserDao;
import com.revature.service.UserService;

public class UserServiceTest {

	private UserService us;

	private UserDao mockDao;

	 ///declare a variable of the class to be tested
	//lets create a before to set up before our tests

	//lets define a stub
	private User dummyUser;

	@Before
	public void setup() {

		us = new UserService();

		//lets mock the class thats being injected

		mockDao = mock(UserDao.class);

		//Lets set the user dao inside the userservice to this mocked one
		us.setUdao(mockDao);

		//Lets also set up a stub user for passin in and stuff
	    dummyUser = new User();
	    dummyUser.setAccounts(new ArrayList<Account>());
	    dummyUser.setId(0);//were going to emulate a user generated from the console
	}

	//Lets create a teardown method to just reset what we had
	public void teardown() {

		us = null;
		dummyUser = null;
		mockDao = null;

	}

	// lets look at some actual testing
	//test register new user
	@Test
	public void testRegisterUserReturnsNewPKId() {
		//lets make a user object to test
		dummyUser = new User (0, "spongebob", "pass", Role.Admin, new LinkedList<Account>());
		//Lets generate a random number to pretend DB created it
		Random r = new Random();
		int fakePK = r.nextInt(100);
		//so now before we run the actual user service method we have to setu p a mock to emulate the function
		//of the insert method of the DAO

		when(mockDao.insert(dummyUser)).thenReturn(fakePK);

		//The registered user of our register method Should have the id thats returned
		//from insert
		User registeredUser = us.register(dummyUser);

		//the final thing to do is call our assert method and check
		//the id of the register user to see if it matches the fake PK
		assertEquals(registeredUser.getId(), fakePK);


	}
}
