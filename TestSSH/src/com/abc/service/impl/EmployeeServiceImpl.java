package com.abc.service.impl;

import java.util.List;

import com.abc.dao.EmployeeDao;
import com.abc.entity.Employee;
import com.abc.entity.PageBean;
import com.abc.service.EmployeeService;

public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeDao employeeDao;



	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao; 
	}


	/**
	 * ҵ����¼����
	 */

	@Override
	public Employee login(Employee employee) {
	Employee existEmployee =	employeeDao.findbyNameAndPassword(employee);
		
		return existEmployee;
	}


	@Override
	public PageBean<Employee> findAll(int currPage) {
		// TODO Auto-generated method stub
		PageBean<Employee> pageBean = new PageBean<Employee>();
		pageBean.setCurrPage(currPage);
		int pageSize = 3;
		pageBean.setPageSize(pageSize);
		int totalCount = employeeDao.findCount();//������
		pageBean.setTotalCount(totalCount);
		int totalPage = 0;
		if(totalCount%pageSize==0){
			totalPage = totalCount/pageSize;
		}else{
			totalPage = totalCount/pageSize+1;
		}
		pageBean.setTotalPage(totalPage);
		
		//��ȡȫ��Ա����Ϣ
		int begin = (currPage-1)*pageSize;
		List<Employee> list= employeeDao.findAll(begin,pageSize); 
		pageBean.setList(list);
		
		System.out.println("totalPage:"+totalPage);
		return pageBean;
	}
	
	
}
