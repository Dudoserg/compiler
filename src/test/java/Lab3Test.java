import main.Lab3.LLK;
import main.Lab3.exceptions.*;
import org.junit.Assert;
import org.junit.Test;

public class Lab3Test {


    Boolean devMode = false;

    @Test(expected = Ex_Dublicate.class)
    public void test_error_1() throws Exception {
        LLK LLK = new LLK(devMode);
        try {
            boolean isOk = LLK.start(System.getProperty("user.dir") + "/tests" + "/error/semantic" + "/test_error_1");
        }catch (Ex_Dublicate ex){
            Assert.assertEquals(ex.savePoint.lines + 1, 2);
            throw ex;
        }
    }

    @Test(expected = Ex_Dublicate.class)
    public void test_error_2() throws Exception {
        LLK LLK = new LLK(devMode);
        try {
            boolean isOk = LLK.start(System.getProperty("user.dir") + "/tests" + "/error/semantic" + "/test_error_2");
        }catch (Ex_Dublicate ex){
            Assert.assertEquals(ex.savePoint.lines + 1, 2);
            throw ex;
        }
    }

    @Test(expected = Ex_NotFound.class)
    public void test_error_3() throws Exception {
        LLK LLK = new LLK(devMode);
        try {
            boolean isOk = LLK.start(System.getProperty("user.dir") + "/tests" + "/error/semantic" + "/test_error_3");
        }catch (Ex_Dublicate ex){
            Assert.assertEquals(ex.savePoint.lines + 1, 1);
            throw ex;
        }
    }

    @Test(expected = Ex_NotFound.class)
    public void test_error_4() throws Exception {
        LLK LLK = new LLK(devMode);
        try {
            boolean isOk = LLK.start(System.getProperty("user.dir") + "/tests" + "/error/semantic" + "/test_error_4");
        }catch (Ex_NotFound ex){
            Assert.assertEquals(ex.savePoint.lines + 1, 1);
            throw ex;
        }
    }

    @Test(expected = Ex_Dublicate_Func.class)
    public void test_error_5() throws Exception {
        LLK LLK = new LLK(devMode);
        try {
            boolean isOk = LLK.start(System.getProperty("user.dir") + "/tests" + "/error/semantic" + "/test_error_5");
        }catch (Ex_Dublicate_Func ex){
            Assert.assertEquals(ex.savePoint.lines + 1, 5);
            throw ex;
        }
    }

    @Test(expected = Ex_NotFound.class)
    public void test_error_6() throws Exception {
        LLK LLK = new LLK(devMode);
        try {
            boolean isOk = LLK.start(System.getProperty("user.dir") + "/tests" + "/error/semantic" + "/test_error_6");
        }catch (Ex_NotFound ex){
            Assert.assertEquals(ex.savePoint.lines + 1, 2);
            throw ex;
        }
    }

    @Test(expected = Ex_Signature.class)
    public void test_error_7() throws Exception {
        LLK LLK = new LLK(devMode);
        try {
            boolean isOk = LLK.start(System.getProperty("user.dir") + "/tests" + "/error/semantic" + "/test_error_7");
        }catch (Ex_Signature ex){
            Assert.assertEquals(ex.savePoint.lines + 1, 6);
            throw ex;
        }
    }
    @Test(expected = Ex_Dublicate.class)
    public void test_error_8() throws Exception {
        LLK LLK = new LLK(devMode);
        try {
            boolean isOk = LLK.start(System.getProperty("user.dir") + "/tests" + "/error/semantic" + "/test_error_8");
        }catch (Ex_Dublicate ex){
            Assert.assertEquals(ex.savePoint.lines + 1, 1);
            throw ex;
        }
    }

    @Test(expected = Ex_Dublicate.class)
    public void test_error_9() throws Exception {
        LLK LLK = new LLK(devMode);
        try {
            boolean isOk = LLK.start(System.getProperty("user.dir") + "/tests" + "/error/semantic" + "/test_error_9");
        }catch (Ex_Dublicate ex){
            Assert.assertEquals(ex.savePoint.lines + 1, 2);
            throw ex;
        }
    }

    @Test(expected = Ex_Signature.class)
    public void test_error_10() throws Exception {
        LLK LLK = new LLK(devMode);
        try {
            boolean isOk = LLK.start(System.getProperty("user.dir") + "/tests" + "/error/semantic" + "/test_error_10");
        }catch (Ex_Signature ex){
            Assert.assertEquals(ex.savePoint.lines + 1, 5);
            throw ex;
        }
    }
    @Test(expected = Ex_Exception.class)
    public void test_error_1g() throws Exception {
        LLK LLK = new LLK(devMode);
        try {
            boolean isOk = LLK.start(System.getProperty("user.dir") + "/tests" + "/error/grammar" + "/test_error_1g");
        }catch (Ex_Exception ex){
            Assert.assertEquals(ex.savePoint.lines + 1, 1);
            throw ex;
        }
    }
    @Test(expected = Ex_Exception.class)
    public void test_error_2g() throws Exception {
        LLK LLK = new LLK(devMode);
        try {
            boolean isOk = LLK.start(System.getProperty("user.dir") + "/tests" + "/error/grammar" + "/test_error_2g");
        }catch (Ex_Exception ex){
            Assert.assertEquals(ex.savePoint.lines + 1, 2);
            throw ex;
        }
    }
    @Test(expected = Ex_Exception.class)
    public void test_error_3g() throws Exception {
        LLK LLK = new LLK(devMode);
        try {
            boolean isOk = LLK.start(System.getProperty("user.dir") + "/tests" + "/error/grammar" + "/test_error_3g");
        }catch (Ex_Exception ex){
            Assert.assertEquals(ex.savePoint.lines + 1, 4);
            throw ex;
        }
    }
    @Test(expected = Ex_Exception.class)
    public void test_error_4g() throws Exception {
        LLK LLK = new LLK(devMode);
        try {
            boolean isOk = LLK.start(System.getProperty("user.dir") + "/tests" + "/error/grammar" + "/test_error_4g");
        }catch (Ex_Exception ex){
            Assert.assertEquals(ex.savePoint.lines + 1, 6);
            throw ex;
        }
    }
    @Test(expected = Ex_Exception.class)
    public void test_error_5g() throws Exception {
        LLK LLK = new LLK(devMode);
        try {
            boolean isOk = LLK.start(System.getProperty("user.dir") + "/tests" + "/error/grammar" + "/test_error_5g");
        }catch (Ex_Exception ex){
            Assert.assertEquals(ex.savePoint.lines + 1, 5);
            throw ex;
        }
    }
    @Test(expected = Ex_Exception.class)
    public void test_error_6g() throws Exception {
        LLK LLK = new LLK(devMode);
        try {
            boolean isOk = LLK.start(System.getProperty("user.dir") + "/tests" + "/error/grammar" + "/test_error_6g");
        }catch (Ex_Exception ex){
            Assert.assertEquals(ex.savePoint.lines + 1, 2);
            throw ex;
        }
    }
    @Test(expected = Ex_Exception.class)
    public void test_error_7g() throws Exception {
        LLK LLK = new LLK(devMode);
        try {
            boolean isOk = LLK.start(System.getProperty("user.dir") + "/tests" + "/error/grammar" + "/test_error_7g");
        }catch (Ex_Exception ex){
            Assert.assertEquals(ex.savePoint.lines + 1, 6);
            throw ex;
        }
    }
    /*@Test(expected = Exception.class)
    public void test_error_10() throws Exception {
        LLK LLK = new LLK(devMode);
        boolean isOk = LLK.start(System.getProperty("user.dir") + "/tests" + "/error" + "/test_error_10");
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
    }*/

}
