package com.twj.book.controller;

import com.twj.book.pojo.OrderBean;
import com.twj.book.pojo.User;
import com.twj.book.service.OrderItemService;
import com.twj.book.service.OrderService;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @ClassNmae OrderController
 * @Description TODO
 * @Author twj280
 * @Date 2022/5/17 20:57
 * @Version 1.0
 **/
public class OrderController {
    private OrderService orderService;
    private OrderItemService orderItemService;
    public String checkout(HttpSession session){
        OrderBean orderBean = new OrderBean();
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String str = simpleDateFormat.format(date);
        orderBean.setOrderNo(UUID.randomUUID().toString() + "_" + str);
        orderBean.setOrderDate(date);
        User currUser = (User) session.getAttribute("currUser");
        orderBean.setOrderUser(currUser);

        Double OrderMoney = currUser.getCart().getTotalMoney();
        orderBean.setOrderMoney(OrderMoney);

        orderBean.setOrderStatus(0);
        //提交订单
        orderService.addOrderBean(orderBean);
        orderBean.getOrderUser().getCart().setCartItemMap(null);
        currUser.setOrderBean(orderBean);

        session.setAttribute("currUser",currUser);


        return "cart/checkout";
    }
    public String getOrderList(Integer pageNo, HttpSession session){
        User currUser = (User) session.getAttribute("currUser");
        if(pageNo==null){
            pageNo = 1;
        }
        List<OrderBean> orderList = orderService.getOrderList(currUser,pageNo);
        for (OrderBean orderBean : orderList) {
            orderBean.setOrderCount(orderItemService.getBuyCount(orderBean));
        }
        //总订单数
        Integer totalOrderCount = orderService.getTotalOrderCount(currUser);
        //总页数
        Integer totalPageNo = orderService.getTotalPageNo(currUser);

        currUser.setOrderBeanList(orderList);
        session.setAttribute("totaolPageNo",totalPageNo);
        session.setAttribute("totalOrderCount",totalOrderCount);
        session.setAttribute("currUser",currUser);
        session.setAttribute("pageNo",pageNo);
        return "order/order";
    }
}
