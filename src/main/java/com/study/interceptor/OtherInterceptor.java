package com.study.interceptor;

import com.study.pojo.category.Category;
import com.study.pojo.order.OrderItem;
import com.study.pojo.user.User;
import com.study.service.CategoryService;
import com.study.service.OrderItemService;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class OtherInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private OrderItemService orderItemService;

    // 生成视图之前执行此方法
    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response,
                           Object handler,
                           ModelAndView modelAndView) throws Exception {
        // 获取所有的分类信息用于放入simpleSearch下的分类信息
        List<Category> categories = categoryService.findAll();
        request.setAttribute("cs", categories);

        // 设置contextPath值
        request.setAttribute("contextPath", request.getContextPath());

        // 获取购物车一共有多少数量
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            // 查询未生成订单的订单项
            List<OrderItem> orderItems = orderItemService.findByUidWithNotOrder(user.getUid());
            // 统计总共数量
            Integer totalNum = 0;
            for (OrderItem orderItem : orderItems) {
                totalNum += orderItem.getNum();
            }
            request.getSession().setAttribute("cartTotalItemNumber", totalNum);
        }
    }
}
