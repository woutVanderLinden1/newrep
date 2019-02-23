package test;

import java.io.Serializable;

public class SubEmployee implements Serializable {

	
	private int age;
	private Employee leader;
	
	public SubEmployee(int a,Employee newLeader) {
		age=a;
		leader=newLeader;
	}
	
	public int getAge() {
		return age;
	}
}
