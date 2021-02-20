package quest_two;

import org.junit.Assert;
import org.junit.Test;

public class module_test{
    @Test
    public void XmoreThenOne(){
        
        Assert.assertEquals(
            false,
            task_two.CheckX(2)
        );
    }
    @Test
    public void XlessThenMinusOne(){
        Assert.assertEquals(
            false,
            task_two.CheckX(-2)
        );
    }
    @Test
    public void CheckLeftSide(){
        Assert.assertEquals(
            0.463,
            task_two.GetResultLeftSide(0.5),
            0.001
        );
    }
    @Test
    public void CheckRightSide(){
        Assert.assertEquals(
            0.540,
            task_two.GetResultRitghSide(0.6, 5),
            0.001
        );
    }
}