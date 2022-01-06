package com.example.searchmovies;

import static org.junit.Assert.assertEquals;

import com.example.searchmovies.theOtherTask.Person;

import org.junit.Test;

public class PersonClassTest {

    @Test
    public void getFullName() {
//        Person reza = new Person("Reza", "Zareii");
//        Person sean = new Person("Sean", "Goudarzi");
//        Person bryan = new Person("Bryan", "Kiani");

//        assertEquals("Full Name: " , "Reza Zareii",reza.getFullName()); // Expect to pass the test!
//        assertEquals("Full Name: " , "Bryan Goudarzi", sean.getFullName()); //Expect to fail the test!
//        assertEquals("Full Name: " , "Sean Kiani", bryan.getFullName()); // Expect to fail the test!

        Person sean = new Person("Sean goudarzi");
        Person cr = new Person("Cristiano Ronaldo dos Santos Aveiro");

        assertEquals("Full Name: ", "Sean goudarzi", sean.getFullName());
        assertEquals("Full Name: ", "Sean", sean.getFirstName());
        assertEquals("Full Name: ", "goudarzi", sean.getLastName());
        assertEquals("Full Name: ", "Cristiano Ronaldo dos Santos Aveiro", cr.getFullName());
    }
}
