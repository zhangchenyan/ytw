package cn.edu.seu.ytw.service;
import cn.edu.seu.ytw.bean.User;
import cn.edu.seu.ytw.bean.Order;
public interface InfoService {
  
	public boolean createOrder(String sender,String senderphone,String idnum,String sAddr,String recipient,String recipientphone,String rAddr);
	public String getAddbyID(int id);
}
