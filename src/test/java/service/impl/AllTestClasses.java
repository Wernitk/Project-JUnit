package service.impl;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SelectClasses({WorkerServiceImplTest.class, StoreServiceImplTest.class})
@SuiteDisplayName("All works")
public class AllTestClasses {

}
