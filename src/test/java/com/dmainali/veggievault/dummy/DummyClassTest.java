package com.dmainali.veggievault.dummy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class DummyClassTest {

    private DummyClass dummyClass = new DummyClass();

    @BeforeEach
    public void testName(){
        dummyClass.result=-9999;
    }

    @Test
    public void positive(){
        dummyClass.add(3,4);
        assertThat(dummyClass.result)
                .isEqualTo(7);
    }

    @Test
    public void negativeTest(){
        dummyClass.add(6,4);
        assertThat(dummyClass.result)
                .isEqualTo(7);
    }

}
