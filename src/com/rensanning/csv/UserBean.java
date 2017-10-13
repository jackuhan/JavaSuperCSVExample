package com.rensanning.csv;

import java.util.Date;

import org.supercsv.cellprocessor.FmtDate;
import org.supercsv.cellprocessor.ParseDate;
import org.supercsv.cellprocessor.ParseInt;
import org.supercsv.cellprocessor.constraint.StrMinMax;
import org.supercsv.cellprocessor.ift.CellProcessor;

public class UserBean {
	
	public static final CellProcessor[] readProcessors = new CellProcessor[] {
			new StrMinMax(2, 10),
			new ParseInt(),
			new ParseDate("yyyy/M/d"), 
			new StrMinMax(2, 10) };
	
	public static final CellProcessor[] writeProcessors = new CellProcessor[] {
			new StrMinMax(2, 10), 
			new ParseInt(),
			new FmtDate("yyyy/M/d"), 
			new StrMinMax(2, 10) };

	public static String[] header = {"name", "age", "birthday", "address",};

	private String name;
	private int age;
	private Date birthday;
	private String address;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

}
