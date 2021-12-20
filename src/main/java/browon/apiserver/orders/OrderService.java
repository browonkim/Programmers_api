package browon.apiserver.orders;

import browon.apiserver.errors.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    @Transactional
    public Boolean accept(Long orderId){
        Optional<Order> order = orderRepository.findById(orderId);
        return orderRepository.accept(order.orElseThrow(() -> new NotFoundException("could not Found product for " + orderId)));
    }

    @Transactional
    public Boolean reject(Long orderId){
        Optional<Order> order = orderRepository.findById(orderId);
        return orderRepository.reject(order.orElseThrow(() -> new NotFoundException("could not Found product for " + orderId)));
    }

    @Transactional
    public Boolean shipping(Long orderId){
        Optional<Order> order = orderRepository.findById(orderId);
        return orderRepository.shipping(order.orElseThrow(() -> new NotFoundException("could not Found product for " + orderId)));
    }

    @Transactional
    public Boolean complete(Long orderId){
        Optional<Order> order = orderRepository.findById(orderId);
        return orderRepository.complete(order.orElseThrow(() -> new NotFoundException("could not Found product for " + orderId)));
    }

    @Transactional(readOnly = true)
    public Optional<Order> findById(Long orderId){
        return orderRepository.findById(orderId);
    }

    @Transactional(readOnly = true)
    public List<Order> findAll(Long offset, Integer size, Long userId){
        return orderRepository.findAll(offset, size, userId);
    }
}
