package cn.edu.seu.ytw.service.impl;
import java.util.List;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.edu.seu.ytw.bean.User;
import cn.edu.seu.ytw.bean.Order;

import cn.edu.seu.ytw.common.PageResults;
import cn.edu.seu.ytw.dao.UserDAO;
import cn.edu.seu.ytw.dao.OrderDAO;

import cn.edu.seu.ytw.service.InfoService;

@Service("infoService")
@Transactional
public class InfoServiceImpl implements InfoService{
	private UserDAO userDAO;
	private OrderDAO orderDAO;
	
	
	@Autowired
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	
	@Autowired
	public void setOrderDAO(OrderDAO orderDAO) {
		this.orderDAO = orderDAO;
	}
	
    public boolean createOrder(String sender,String senderphone,String idnum,String sAddr,String recipient,String recipientphone,String rAddr) 
	{
    	User user1,user2;
    	Order order;
    	user1 = new User();
		user1.setName(sender);
		user1.setAddress(sAddr);
		user1.setIDnumber(idnum);
		user1.setPhone(senderphone);
		userDAO.save(user1);
		user2 = new User();
		
		user2.setName(recipient);
		user2.setPhone(recipientphone);
		user2.setAddress(rAddr);
		userDAO.save(user2);
		order = new Order();
		
		order.setsId(user1.getId());
		order.setrId(user2.getId());
		
		orderDAO.save(order);
		
		String a=order.getId();
		String b = "http://223.3.169.92:8080/ytw/";
		String text=b+a;
		
		return false;
	}


	public String getAddbyID(int id) 
	{
		// TODO Auto-generated method stub
		String ID=(String)"id";
		String hql="select * from order where id="+ID;
		Order order=orderDAO.getBySQL(hql);
		int userid=order.getrId();
		String userID=(String)"userid";
		String userhql="select * from user where id="+userID;
        User user=userDAO.getBySQL(userhql);
        return user.getAddress();
	}
}
