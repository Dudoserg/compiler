import main.Lab3.LLK;
import main.Lab3.exceptions.*;
import org.junit.Assert;
import org.junit.Test;

public class Lab3Test_Ok {


    Boolean devMode = false;

//    @Test(expected = Ex_Dublicate.class)
//    public void test_error_1() throws Exception {
//        LLK LLK = new LLK(devMode);
//        try {
//            boolean isOk = LLK.start(System.getProperty("user.dir") + "/tests" + "/error/semantic" + "/test_error_1");
//        }catch (Ex_Dublicate ex){
//            Assert.assertEquals(ex.savePoint.lines + 1, 2);
//            throw ex;
//        }
//    }



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
