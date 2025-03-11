package com.flamengo.ecommerce.service;

import com.flamengo.ecommerce.dtos.OrderDTO;
import com.flamengo.ecommerce.entities.Order;
import com.flamengo.ecommerce.respository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Transactional(readOnly = true)
    public List<OrderDTO> findAll() {
        List<Order> list = orderRepository.findAll();
        return list.stream().map(order -> orderToDTO(order)).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public OrderDTO findById(Long id) {
        Order order = orderRepository.findById(id).orElseThrow();
        return orderToDTO(order);
    }

    @Transactional
    public OrderDTO save(OrderDTO orderDTO) {
        Order order = DTOToOrder(orderDTO);
        order.setMoment(LocalDateTime.now());
        Order savedOrder = orderRepository.save(order);
        return orderToDTO(savedOrder);
    }

    @Transactional
    public OrderDTO update(Long id,OrderDTO orderDTO) {
        Order order = orderRepository.findById(id).orElseThrow();
        order.setStatus(orderDTO.getStatus());
        order.setId(id);
        order = orderRepository.save(order);
        return orderToDTO(order);
    }

    private Order DTOToOrder(OrderDTO orderDTO) {
        Order order = new Order();
        order.setId(orderDTO.getId());
        order.setMoment(orderDTO.getMoment());
        order.setStatus(orderDTO.getStatus());
        return order;
    }


    public OrderDTO orderToDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setMoment(order.getMoment());
        orderDTO.setStatus(order.getStatus());
        return orderDTO;
    }

}