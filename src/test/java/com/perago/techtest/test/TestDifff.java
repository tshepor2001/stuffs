package com.perago.techtest.test;

import com.perago.techtest.Diff;
import com.perago.techtest.DiffEngineService;
import com.perago.techtest.DiffException;
import com.perago.techtest.Person;

/**
 * Created by tshepo on 2016/11/13.
 */
public class TestDifff {

    public static void main(String[] args) throws DiffException {
        DiffEngineService x = new DiffEngineService();
        Person original = new Person();
        original.setFirstName("original");
        original.getNickNames().add("o nick 1");
        original.getNickNames().add("o nick 2");

        Person modified = new Person();
        modified.setFirstName("modified");
        modified.getNickNames().add("M nick 1");

        Person friend = new Person();
        friend.setFirstName("Friend");
        modified.setFriend(friend);

        Diff<Person> calculate = x.calculate(original, modified);
        Person apply = x.apply(original, calculate);
        System.out.println(apply);
    }
}
