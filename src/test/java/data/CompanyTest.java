package data;

import org.junit.internal.builders.JUnit3Builder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CompanyTest {
    @Test
    void test1(){
        Company company = new Company();
        System.out.println(company.toString());
    }
}