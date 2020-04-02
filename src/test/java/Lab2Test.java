import main.Lab2.Lab2;
import org.junit.Assert;
import org.junit.Test;

public class Lab2Test {


    @Test(expected = Exception.class)
    public void test_error_1() throws Exception {
        Lab2 lab2 = new Lab2();
        boolean isOk = lab2.start(System.getProperty("user.dir") + "/tests" + "/error" + "/test_error_1");
    }
    @Test(expected = Exception.class)
    public void test_error_2() throws Exception {
        Lab2 lab2 = new Lab2();
        boolean isOk = lab2.start(System.getProperty("user.dir") + "/tests" + "/error" + "/test_error_2");
    }
    @Test(expected = Exception.class)
    public void test_error_3() throws Exception {
        Lab2 lab2 = new Lab2();
        boolean isOk = lab2.start(System.getProperty("user.dir") + "/tests" + "/error" + "/test_error_3");
    }
    @Test(expected = Exception.class)
    public void test_error_4() throws Exception {
        Lab2 lab2 = new Lab2();
        boolean isOk = lab2.start(System.getProperty("user.dir") + "/tests" + "/error" + "/test_error_4");
    }
    @Test(expected = Exception.class)
    public void test_error_5() throws Exception {
        Lab2 lab2 = new Lab2();
        boolean isOk = lab2.start(System.getProperty("user.dir") + "/tests" + "/error" + "/test_error_5");
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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
    @Test
    public void test_ok_4() throws Exception {
        Lab2 lab2 = new Lab2();
        boolean isOk = lab2.start(System.getProperty("user.dir") + "/tests" + "/ok" + "/test_ok_4");
        Assert.assertTrue(isOk);
    }
    @Test
    public void test_ok_5() throws Exception {
        Lab2 lab2 = new Lab2();
        boolean isOk = lab2.start(System.getProperty("user.dir") + "/tests" + "/ok" + "/test_ok_5");
        Assert.assertTrue(isOk);
    }
    @Test
    public void test_ok_6() throws Exception {
        Lab2 lab2 = new Lab2();
        boolean isOk = lab2.start(System.getProperty("user.dir") + "/tests" + "/ok" + "/test_ok_6");
        Assert.assertTrue(isOk);
    }
    @Test
    public void test_ok_7() throws Exception {
        Lab2 lab2 = new Lab2();
        boolean isOk = lab2.start(System.getProperty("user.dir") + "/tests" + "/ok" + "/test_ok_7");
        Assert.assertTrue(isOk);
    }
    @Test
    public void test_ok_8() throws Exception {
        Lab2 lab2 = new Lab2();
        boolean isOk = lab2.start(System.getProperty("user.dir") + "/tests" + "/ok" + "/test_ok_8");
        Assert.assertTrue(isOk);
    }
    @Test
    public void test_ok_9() throws Exception {
        Lab2 lab2 = new Lab2();
        boolean isOk = lab2.start(System.getProperty("user.dir") + "/tests" + "/ok" + "/test_ok_9");
        Assert.assertTrue(isOk);
    }
    @Test
    public void test_ok_10() throws Exception {
        Lab2 lab2 = new Lab2();
        boolean isOk = lab2.start(System.getProperty("user.dir") + "/tests" + "/ok" + "/test_ok_10");
        Assert.assertTrue(isOk);
    }
}
