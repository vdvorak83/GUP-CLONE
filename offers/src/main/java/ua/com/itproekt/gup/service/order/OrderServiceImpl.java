package ua.com.itproekt.gup.service.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.itproekt.gup.dao.order.OrderRepository;
import ua.com.itproekt.gup.model.order.Order;
import ua.com.itproekt.gup.model.order.OrderStatus;
import ua.com.itproekt.gup.model.order.filter.OrderFilterOptions;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;


    @Override
    public void create(Order order) {
        Order newOrder = new Order()
                .setOfferId(order.getOfferId())
                .setBuyerId(order.getBuyerId())
                .setSellerId(order.getSellerId())
                .setPrice(order.getPrice())
                .setOfferTitle(order.getOfferTitle())
                .setOfferMainImageId(order.getOfferMainImageId())
                .setCreatedDateEqualsToCurrentDate()
                .setOrderAddress(order.getOrderAddress())
                .setOrderStatus(OrderStatus.NEW)
                .setSafeOrder(order.isSafeOrder())
                .setOrderType(order.getOrderType())
                .setOrderComments(order.getOrderComments());

        orderRepository.create(newOrder);
        order.setId(newOrder.getId());
    }

    @Override
    public Order findById(String id) {
        return orderRepository.findById(id);
    }



    //ToDo тут не хватает некоторых полей (тайтл...)
    @Override
    public Order findAndUpdate(Order order) {
        Order newOrder = new Order()
                .setOfferId(order.getOfferId())
                .setBuyerId(order.getBuyerId())
                .setSellerId(order.getSellerId())
                .setOrderAddress(order.getOrderAddress())
                .setSentDate(order.getSentDate())
                .setReceivedDate(order.getReceivedDate())
                .setCompleteDate(order.getCompleteDate())
                .setAcceptDate(order.getAcceptDate())
                .setOrderStatus(order.getOrderStatus())
                .setSafeOrder(order.isSafeOrder())
                .setOrderType(order.getOrderType())
                .setOrderComments(order.getOrderComments());

        return orderRepository.findAndUpdate(newOrder);

    }

    @Override
    public int delete(String id) {
        return orderRepository.delete(id);
    }

    @Override
    public List<Order> findOrdersWihOptions(OrderFilterOptions orderFilterOptions) {
        return orderRepository.findOrdersWihOptions(orderFilterOptions);
    }

    //ToDo метод для добавления комментария к ордеру
}
