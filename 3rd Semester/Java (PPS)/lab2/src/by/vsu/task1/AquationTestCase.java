package by.vsu.task1;

import org.junit.Assert;
import org.junit.Test;


public class AquationTestCase {
    @Test
    public void TestMergerString(){
        String[] args = {"Hello", "World"};
        Assert.assertEquals(
                "Hello World",
                Main.MergerString(args)
        );
    }

    @Test
    public void TestArrayIsEmpty(){
        String[] args = {};
        Assert.assertEquals(
                true,
                Main.ArrayIsEmpty(args)
        );
    }
}
