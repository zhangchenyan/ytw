package cn.edu.seu.ytw.dao.impl;

import org.springframework.stereotype.Repository;

import cn.edu.seu.ytw.bean.Order;
import cn.edu.seu.ytw.common.BaseDAOImpl;
import cn.edu.seu.ytw.dao.OrderDAO;

@Repository("orderDAO")
public class OrderDAOImpl extends BaseDAOImpl<Order, Integer> implements OrderDAO {


}
