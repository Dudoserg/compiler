import main.Lab3.LLK;
import org.junit.Assert;
import org.junit.Test;

public class Lab3Test_Triads_Ok {


    Boolean devMode = false;


    @Test
    public void test_ok_1() throws Exception {
        LLK LLK = new LLK(devMode);
//        boolean isOk = LLK.start(System.getProperty("user.dir") + "/tests" + "/ok" +" /triads" + "/test_triad_ok_1");
        boolean isOk = LLK.start(System.getProperty("user.dir") + "/tests" + "/ok/triads" + "/test_triad_ok_1");
        Assert.assertEquals(LLK.triadsStr, result);
        Assert.assertTrue(isOk);
    }

    String result = "0)    proc    heh\n" +
            "1)    prolog\n" +
            "2)    _INT    x\n" +
            "3)    *    a    a\n" +
            "4)    =    x    (3)\n" +
            "5)    *    x    3\n" +
            "6)    push_for_return    (5)\n" +
            "7)    epilog\n" +
            "8)    ret\n" +
            "9)    endp\n" +
            "10)    proc    main\n" +
            "11)    prolog\n" +
            "12)    -    0    2\n" +
            "13)    +    (12)    5\n" +
            "14)    >    (13)    3\n" +
            "15)    if    (16)    (33)\n" +
            "16)    _INT    a\n" +
            "17)    =    a    2\n" +
            "18)    _INT    qwer\n" +
            "19)    push    a\n" +
            "20)    call    heh\n" +
            "21)    =    qwer    (20)\n" +
            "22)    _INT    b\n" +
            "23)    =    b    2\n" +
            "24)    >    123    138\n" +
            "25)    if    (26)    (29)\n" +
            "26)    =    a    228\n" +
            "27)    goto    (29)\n" +
            "28)    =    a    282\n" +
            "29)    NOP\n" +
            "30)    goto    (33)\n" +
            "31)    _INT    x\n" +
            "32)    =    x    2\n" +
            "33)    NOP\n" +
            "34)    _DOUBLE    cc\n" +
            "35)    =    cc    228\n" +
            "36)    *    cc    3\n" +
            "37)    push_for_return    (36)\n" +
            "38)    epilog\n" +
            "39)    ret\n" +
            "40)    endp";
}
