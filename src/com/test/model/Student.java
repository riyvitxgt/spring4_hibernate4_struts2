package com.test.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

/**
 * 学生实体
 * @author FengJianBo
 * 2014年3月13日 下午2:57:18
 */
@Entity
public class Student {
	/**
	 * id
	 */
	private String id;
	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 年龄
	 */
	private Integer age;
	/**
	 * 性别
	 */
	private String sex;
	@Id
	@Column(name="id",nullable=false,length = 32)
	 @GeneratedValue(generator="hibernate-uuid")
	 @GenericGenerator(name = "hibernate-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String toString(){
		return "id:"+this.id+";name:"+this.name+";age:"+age+";sex:"+sex;
	}
}
