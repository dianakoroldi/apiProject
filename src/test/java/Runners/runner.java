package Runners;

import endPoints.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)
@Suite.SuiteClasses({ ListOfUsers.class, ListResources.class, SingleResource.class,  SingleUser.class, SingleUserNotFound.class})
public class runner {}


