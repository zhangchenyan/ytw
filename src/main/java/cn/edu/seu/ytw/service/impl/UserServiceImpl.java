package cn.edu.seu.ytw.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.seu.ytw.bean.User;
import cn.edu.seu.ytw.common.PageResults;
import cn.edu.seu.ytw.dao.UserDAO;
import cn.edu.seu.ytw.exception.LoginException;
import cn.edu.seu.ytw.exception.RegisterException;
import cn.edu.seu.ytw.service.UserService;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	private UserDAO userDAO;
	
	@Autowired
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	@Transactional(readOnly=true)
	public User qryById(int id) {
		return userDAO.get(id);
	}

	public List<User> qryAll(int pageNo, int size) {
		String hql = "from User u";
		String countHql = "select count(*) from User";
		PageResults<User> results = userDAO.findPageByFetchedHql(hql, countHql,pageNo,size);
		
		return results.getResults();
	}

}
