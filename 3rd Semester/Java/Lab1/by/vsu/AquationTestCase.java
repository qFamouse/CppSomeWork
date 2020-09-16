package by.vsu;

// import by.vsu.Runner; 

import org.junit.Assert;
import org.junit.Test;

public class AquationTestCase{
    @Test
    public void DivisionByZero_Infinity(){
        Assert.assertEquals(
            Double.POSITIVE_INFINITY,
            Runner.aquation(1, 2, 0),
            0.00000001
        );
    }
    @Test
    public void DivisionByZero_Negative(){
        Assert.assertEquals(
            Double.NEGATIVE_INFINITY,
            Runner.aquation(-5, 2, 0),
            0.00000001
        );
    }
}