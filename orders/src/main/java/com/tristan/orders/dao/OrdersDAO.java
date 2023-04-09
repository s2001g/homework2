package com.tristan.orders.dao;


import com.tristan.orders.po.Orders;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class OrdersDAO {
    private static OrdersMapper ordersMapper;
    public OrdersDAO() throws IOException {
        if (ordersMapper == null) {
            InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
            //2.创建SqlSessionFactory工厂
            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
            SqlSessionFactory factory = builder.build(in);
            //3.使用工厂生产SqlSession对象
            SqlSession session = factory.openSession(false);
            //4.使用SqlSession创建Dao接口的代理对象
            ordersMapper = session.getMapper(OrdersMapper.class);
        }
    }
    public Orders createOrders(Orders orders) throws IOException {
        int result = ordersMapper.insert(orders);
        if(result == 1){
            return orders;
        }

        return null;
    }
}
