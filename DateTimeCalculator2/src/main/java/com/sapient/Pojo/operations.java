package com.sapient.Pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class operations {
	@Id
	@Column(name = "sno")
	private int sno;

	@Column(name = "id")
	private int id;

	@Column(name = "time")
	private String time;

	@Column(name = "input1")
	private String input1;

	@Column(name = "input2")
	private String input2;

	@Column(name = "operation")
	private String operation;

	@Column(name = "result")
	private String result;

	public operations() {

	}

	public operations(String time, String input1, String input2, String operation, String result) {
		this.time = time;
		this.input1 = input1;
		this.input2 = input2;
		this.operation = operation;
		this.result = result;
	}

	public int getSno() {
		return sno;
	}

	public void setSno(int sno) {
		this.sno = sno;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getInput1() {
		return input1;
	}

	public void setInput1(String input1) {
		this.input1 = input1;
	}

	public String getInput2() {
		return input2;
	}

	public void setInput2(String input2) {
		this.input2 = input2;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "operations [sno=" + sno + ", id=" + id + ", time=" + time + ", input1=" + input1 + ", input2=" + input2
				+ ", operation=" + operation + ", result=" + result + "]";
	}

}
