import main.Lab3.LLK;
import org.junit.Assert;
import org.junit.Test;

public class Lab3Test {


    Boolean devMode = false;

    @Test(expected = Exception.class)
    public void test_error_1() throws Exception {
        LLK LLK = new LLK(devMode);
        boolean isOk = LLK.start(System.getProperty("user.dir") + "/tests" + "/error" + "/test_error_1");
    }

    @Test(expected = Exception.class)
    public void test_error_2() throws Exception {
        LLK LLK = new LLK(devMode);
        boolean isOk = LLK.start(System.getProperty("user.dir") + "/tests" + "/error" + "/test_error_2");
    }

    @Test(expected = Exception.class)
    public void test_error_3() throws Exception {
        LLK LLK = new LLK(devMode);
        boolean isOk = LLK.start(System.getProperty("user.dir") + "/tests" + "/error" + "/test_error_3");
    }

    @Test(expected = Exception.class)
    public void test_error_4() throws Exception {
        LLK LLK = new LLK(devMode);
        boolean isOk = LLK.start(System.getProperty("user.dir") + "/tests" + "/error" + "/test_error_4");
    }

    @Test(expected = Exception.class)
    public void test_error_5() throws Exception {
        LLK LLK = new LLK(devMode);
        boolean isOk = LLK.start(System.getProperty("user.dir") + "/tests" + "/error" + "/test_error_5");
    }

    @Test(expected = Exception.class)
    public void test_error_6() throws Exception {
        LLK LLK = new LLK(devMode);
        boolean isOk = LLK.start(System.getProperty("user.dir") + "/tests" + "/error" + "/test_error_6");
    }

    @Test(expected = Exception.class)
    public void test_error_7() throws Exception {
        LLK LLK = new LLK(devMode);
        boolean isOk = LLK.start(System.getProperty("user.dir") + "/tests" + "/error" + "/test_error_7");
    }

    @Test(expected = Exception.class)
    public void test_error_8() throws Exception {
        LLK LLK = new LLK(devMode);
        boolean isOk = LLK.start(System.getProperty("user.dir") + "/tests" + "/error" + "/test_error_8");
    }

    @Test(expected = Exception.class)
    public void test_error_9() throws Exception {
        LLK LLK = new LLK(devMode);
        boolean isOk = LLK.start(System.getProperty("user.dir") + "/tests" + "/error" + "/test_error_9");
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Test
    public void test_ok_1() throws Exception {
        LLK LLK = new LLK(devMode);
        boolean isOk = LLK.start(System.getProperty("user.dir") + "/tests" + "/ok" + "/test_ok_1");
        Assert.assertTrue(isOk);
    }

    @Test
    public void test_ok_2() throws Exception {
        LLK LLK = new LLK(devMode);
        boolean isOk = LLK.start(System.getProperty("user.dir") + "/tests" + "/ok" + "/test_ok_2");
        Assert.assertTrue(isOk);
    }

    @Test
    public void test_ok_3() throws Exception {
        LLK LLK = new LLK(devMode);
        boolean isOk = LLK.start(System.getProperty("user.dir") + "/tests" + "/ok" + "/test_ok_3");
        Assert.assertTrue(isOk);
    }

    @Test
    public void test_ok_4() throws Exception {
        LLK LLK = new LLK(devMode);
        boolean isOk = LLK.start(System.getProperty("user.dir") + "/tests" + "/ok" + "/test_ok_4");
        Assert.assertTrue(isOk);
    }

    @Test
    public void test_ok_5() throws Exception {
        LLK LLK = new LLK(devMode);
        boolean isOk = LLK.start(System.getProperty("user.dir") + "/tests" + "/ok" + "/test_ok_5");
        Assert.assertTrue(isOk);
    }

    @Test
    public void test_ok_6() throws Exception {
        LLK LLK = new LLK(devMode);
        boolean isOk = LLK.start(System.getProperty("user.dir") + "/tests" + "/ok" + "/test_ok_6");
        Assert.assertTrue(isOk);
    }

    @Test
    public void test_ok_7() throws Exception {
        LLK LLK = new LLK(devMode);
        boolean isOk = LLK.start(System.getProperty("user.dir") + "/tests" + "/ok" + "/test_ok_7");
        Assert.assertTrue(isOk);
    }

    @Test
    public void test_ok_8() throws Exception {
        LLK LLK = new LLK(devMode);
        boolean isOk = LLK.start(System.getProperty("user.dir") + "/tests" + "/ok" + "/test_ok_8");
        Assert.assertTrue(isOk);
    }

    @Test
    public void test_ok_9() throws Exception {
        LLK LLK = new LLK(devMode);
        boolean isOk = LLK.start(System.getProperty("user.dir") + "/tests" + "/ok" + "/test_ok_9");
        Assert.assertTrue(isOk);
    }

    @Test
    public void test_ok_10() throws Exception {
        LLK LLK = new LLK(devMode);
        boolean isOk = LLK.start(System.getProperty("user.dir") + "/tests" + "/ok" + "/test_ok_10");
        Assert.assertTrue(isOk);
    }

    @Test
    public void test_ok_11() throws Exception {
        LLK LLK = new LLK(devMode);
        boolean isOk = LLK.start(System.getProperty("user.dir") + "/tests" + "/ok" + "/test_ok_11");
        Assert.assertTrue(isOk);
    }

    @Test
    public void test_ok_12() throws Exception {
        LLK LLK = new LLK(devMode);
        boolean isOk = LLK.start(System.getProperty("user.dir") + "/tests" + "/ok" + "/test_ok_12");
        Assert.assertTrue(isOk);
    }

    @Test
    public void test_ok_13() throws Exception {
        LLK LLK = new LLK(devMode);
        boolean isOk = LLK.start(System.getProperty("user.dir") + "/tests" + "/ok" + "/test_ok_13");
        Assert.assertTrue(isOk);
    }

    @Test
    public void test_ok_14() throws Exception {
        LLK LLK = new LLK(devMode);
        boolean isOk = LLK.start(System.getProperty("user.dir") + "/tests" + "/ok" + "/test_ok_14");
        Assert.assertTrue(isOk);
    }

}
