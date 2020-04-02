import main.Lab2.Lab2;
import org.junit.Assert;
import org.junit.Test;

public class Lab2Test {


    @Test(expected = Exception.class)
    public void test_error_1() throws Exception {
        Lab2 lab2 = new Lab2();
        boolean isOk = lab2.start(System.getProperty("user.dir") + "/tests" + "/error" + "/test_error_1");
    }

    @Test
    public void test_ok_1() throws Exception {
        Lab2 lab2 = new Lab2();
        boolean isOk = lab2.start(System.getProperty("user.dir") + "/tests" + "/ok" + "/test_ok_1");
        Assert.assertTrue(isOk);
    }
    @Test
    public void test_ok_2() throws Exception {
        Lab2 lab2 = new Lab2();
        boolean isOk = lab2.start(System.getProperty("user.dir") + "/tests" + "/ok" + "/test_ok_2");
        Assert.assertTrue(isOk);
    }
    @Test
    public void test_ok_3() throws Exception {
        Lab2 lab2 = new Lab2();
        boolean isOk = lab2.start(System.getProperty("user.dir") + "/tests" + "/ok" + "/test_ok_3");
        Assert.assertTrue(isOk);
    }
}
