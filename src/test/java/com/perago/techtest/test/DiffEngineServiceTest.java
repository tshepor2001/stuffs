package com.perago.techtest.test;

import com.perago.techtest.Diff;
import com.perago.techtest.DiffEngineService;
import com.perago.techtest.DiffException;
import com.perago.techtest.Person;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNull;

public class DiffEngineServiceTest {
    private DiffEngineService service;

    @Before
    public void setUp() throws Exception {
        service = new DiffEngineService();
    }

    //#Calculate
    @Test
    public void shouldReturnNullIfOriginalIsNullAndModifiedIsNull() throws Exception {
        assertNull(service.calculate(null, null));
    }

    @Test
    public void shouldReturnNullDiffObjectWhenModifiedIsNull() throws Exception {
        assertNull(service.calculate(new Person(), null).getObj());
    }

    @Test
    public void shouldReturnExpectedDiffWhenOriginalIsNotNullAndModifiedIsNotNull() throws Exception {
        assertThat(service.calculate(original(), modified()), is(diffObject()));
    }

    @Test
    public void shouldReturnExpectedChangedFieldListWhenModified() throws Exception {
        assertThat(service.calculate(original(), modified()).getFields(), is(changedFields()));

    }

    @Test
    public void shouldReturnExpectedDiffWhenOriginalIsNullAndModifiedIsNotNull() throws Exception {
        assertThat(service.calculate(null, modified()).getFields(), is(allFields()));
    }

    private List<String> allFields() {
        List<String> changedFields = new ArrayList<>();
        changedFields.add("firstName");
        changedFields.add("surname");
        changedFields.add("friend");
        changedFields.add("nickNames");
        return changedFields;
    }

    private List<String> changedFields() {
        List<String> changedFields = new ArrayList<>();
        changedFields.add("firstName");
        changedFields.add("friend");
        changedFields.add("nickNames");
        return changedFields;
    }

    private Diff<Person> diffObject() {
        Diff<Person> diff = new Diff<>();
        Person person = modified();
        diff.setObj(person);
        diff.getFields().addAll(changedFields());
        Diff<Person> friend = new Diff<>();
        friend.setObj(person.getFriend());
        friend.setParent("friend");
        friend.getFields().add("firstName");
        diff.setInner(friend);
        return diff;
    }

    private Person original() {
        Person person = new Person();
        person.setFirstName("name");
        person.setSurname("surname");
        Person friend = new Person();
        friend.setFirstName("Friend");
        person.setFriend(friend);
        return person;
    }

    private Person modified() {
        Person person = new Person();
        person.setFirstName("Modified");
        person.setSurname("surname");
        Person friend = new Person();
        friend.setFirstName("Another Friend");
        person.setFriend(friend);
        person.getNickNames().add("Nick 1");
        person.getNickNames().add("Nick 2");
        return person;
    }



    //#Apply


    @Test
    public void shouldReturnModifiedWhenCalledWithOriginalAndDiff() throws Exception {
        assertThat(service.apply(original(), diffObject()), is(modified()));
    }

    @Test
    public void shouldReturnNullIfDiffIsNull() throws Exception {
        assertNull(service.apply(original(), null));
    }

}
