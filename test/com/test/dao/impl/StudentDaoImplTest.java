package com.test.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.test.dao.StudentDao;
import com.test.model.PageBean;
import com.test.model.PageList;
import com.test.model.Student;

/**
 * @author FengJianBo
 * 2014年3月17日 上午11:04:17
 */

@Component  
@Transactional  
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })  
public class StudentDaoImplTest {
	@Autowired
	private StudentDao studentDao;
	@Test
	@Rollback(false) 
	public void add(){
		Student stu = new Student();
		stu.setName("test11111");
		stu.setSex("男");
		stu.setAge(23);
		try {
			studentDao.add(stu);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
		
	}
	@Test
	@Rollback(false)
	public void update(){
		try {
			Student stu = studentDao.findById("1");
			stu.setName("我改我改我改改改");
			studentDao.update(stu);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	
	public void delete(){
		try {
			this.studentDao.deleteById("402881eb483638b601483638bbd40000");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Test
	public void findAll(){
		List<Student> students;
		try {
			students = studentDao.findAll();
			System.out.println("findAll:"+students);
			PageBean pageBean = new PageBean(1, 5);
			PageList<Student> pageList = studentDao.findAll(pageBean);
			System.out.println("findAll(PageBean):"+pageList);
			Student student = new Student();
			student.setName("test");
			pageList = studentDao.findForList(student, pageBean);
			System.out.println("findForList(T,PageBean):"+pageList);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
		
	}

}
