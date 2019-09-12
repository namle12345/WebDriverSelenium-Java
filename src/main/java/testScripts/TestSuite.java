package testScripts;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
    RegisterTest.class,
    CurrencyAddTest.class,
    BuyStockTest.class,
    ResetPasswordTest.class
})

public class TestSuite
{
 /* empty class */
}

