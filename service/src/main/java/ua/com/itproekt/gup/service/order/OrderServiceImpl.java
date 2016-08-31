package ua.com.itproekt.gup.service.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.itproekt.gup.dao.order.OrderRepository;
import ua.com.itproekt.gup.model.offer.Offer;
import ua.com.itproekt.gup.model.order.Order;
import ua.com.itproekt.gup.model.order.OrderFeedback;
import ua.com.itproekt.gup.model.order.OrderStatus;
import ua.com.itproekt.gup.model.order.filter.OrderFilterOptions;
import ua.com.itproekt.gup.model.profiles.Profile;

import java.util.ArrayList;
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
                .setOrderType(order.getOrderType())
                .setOrderComments(order.getOrderComments())
                .setPaymentMethod(order.getPaymentMethod());

        orderRepository.create(newOrder);
        order.setId(newOrder.getId());
    }

    @Override
    public Order findById(String id) {
        return orderRepository.findById(id);
    }


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
                .setOrderType(order.getOrderType())
                .setOrderComments(order.getOrderComments())
                .setPaymentMethod(order.getPaymentMethod())
                .setOrderFeedback(order.getOrderFeedback());

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

    /**
     *
     * @param offerId
     * @return
     */
    @Override
    public List<OrderFeedback> findAllFeedbacksForOffer(String offerId) {
        List<OrderFeedback> orderFeedbackList = new ArrayList<>();

        OrderFilterOptions orderFilterOptions = new OrderFilterOptions();
        orderFilterOptions.setOfferId(offerId);
        List<Order> orderList = orderRepository.findOrdersWihOptions(orderFilterOptions);
        for (Order order : orderList) {
            orderFeedbackList.add(order.getOrderFeedback());
        }

        return orderFeedbackList;
    }

    /**
     * Calculate average points for one certain offer
     *
     * @param offer - offer
     * @return
     */
    private Integer calcAvgOfferPoints(Offer offer) {
        String offerId = offer.getId();
        OrderFilterOptions orderFilterOptions = new OrderFilterOptions();
        orderFilterOptions.setOfferId(offerId);

        int pointSum = 0;
        int count = 0;

        List<Order> orderList = findOrdersWihOptions(orderFilterOptions);
        for (Order order : orderList) {
            if (order.getOrderFeedback() != null) {
                count++;
                pointSum = pointSum + order.getOrderFeedback().getPoint();
            }
        }

        // return average or return zero
        return (count != 0) ? (pointSum / count) * 100 : count;
    }


    /**
     * Calculate average user points based on his orders feedback
     *
     * @param profile - profile
     * @return
     */
    private Integer calcAvgUserOrderPoints(Profile profile) {
        String userId = profile.getId();
        OrderFilterOptions orderFilterOptions = new OrderFilterOptions();
        orderFilterOptions.setSellerId(userId);

        int pointSum = 0;
        int count = 0;

        List<Order> orderList = findOrdersWihOptions(orderFilterOptions);

        for (Order order : orderList) {
            if (order.getOrderFeedback() != null) {
                count++;
                pointSum = pointSum + order.getOrderFeedback().getPoint();
            }
        }

        // return average or return zero
        return (count != 0) ? (pointSum / count) * 100 : count;
    }

}