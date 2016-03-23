package cn.edu.seu.ytw.dao.impl;

import org.springframework.stereotype.Repository;

import cn.edu.seu.ytw.bean.User;
import cn.edu.seu.ytw.common.BaseDAOImpl;
import cn.edu.seu.ytw.dao.UserDAO;

@Repository("userDAO")
public class UserDAOImpl extends BaseDAOImpl<User, Integer> implements UserDAO {


}
