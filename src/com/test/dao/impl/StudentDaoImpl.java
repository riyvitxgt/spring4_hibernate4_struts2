package com.test.dao.impl;

import org.springframework.stereotype.Repository;

import com.test.dao.StudentDao;
import com.test.model.Student;

/**
 * @author FengJianBo
 * 2014年3月21日 下午3:04:01
 */
@Repository("studentDao")
public class StudentDaoImpl extends BaseDaoImpl<Student> implements StudentDao {
	
}
