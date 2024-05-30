package org.example;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() throws IOException {
        School school=new School();
        school.createStudents();
        System.out.println("\n");
        school.Highest();
        System.out.println("\n");
        school.ClassNum();
        school.WeightSort();
        System.out.println("\n");
        school.AllWeightSort();

        assertTrue( true );
    }

}
