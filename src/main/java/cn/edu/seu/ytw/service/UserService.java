package cn.edu.seu.ytw.service;

import java.util.List;

import cn.edu.seu.ytw.bean.User;
import cn.edu.seu.ytw.exception.LoginException;
import cn.edu.seu.ytw.exception.RegisterException;

public interface UserService {

	User qryById(int id);
	List<User> qryAll(int pageNo,int size);
}
