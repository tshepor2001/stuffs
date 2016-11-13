package com.perago.techtest;


import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Person implements Serializable {

	private static final long serialVersionUID = -4309657210316182163L;

	private String firstName;
	private String surname;
	private Person friend;
	private List<String> nickNames;


	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getSurname() {
		return surname;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public Person getFriend() {
		return friend;
	}
	
	public void setFriend(Person friend) {
		this.friend = friend;
	}

	public List<String> getNickNames() {
		if (nickNames == null) {
			nickNames = new ArrayList<>();
		}
		return nickNames;
	}

	public void setNickNames(List<String> nickNames) {
		this.nickNames = nickNames;
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	@Override
	public String toString() {
		return "Person{" +
				"firstName='" + firstName + '\'' +
				", surname='" + surname + '\'' +
				", friend=" + friend +
				", nickNames=" + nickNames +
				'}';
	}
}
