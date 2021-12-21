package browon.apiserver.orders;

import browon.apiserver.errors.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ReviewRepository reviewRepository;

    public OrderService(OrderRepository orderRepository, ReviewRepository reviewRepository){
        this.orderRepository = orderRepository;
        this.reviewRepository = reviewRepository;
    }

    @Transactional
    public Boolean accept(Long orderId, String message){
        Optional<Order> order = orderRepository.findById(orderId);
        return orderRepository.accept(order.orElseThrow(() -> new NotFoundException("could not Found product for " + orderId)));
    }

    @Transactional
    public Boolean reject(Long orderId, String message){
        Optional<Order> order = orderRepository.findById(orderId);
        return orderRepository.reject(order.orElseThrow(() -> new NotFoundException("could not Found product for " + orderId)));
    }

    @Transactional
    public Boolean shipping(Long orderId, String message){
        Optional<Order> order = orderRepository.findById(orderId);
        return orderRepository.shipping(order.orElseThrow(() -> new NotFoundException("could not Found product for " + orderId)));
    }

    @Transactional
    public Boolean complete(Long orderId, String message){
        Optional<Order> order = orderRepository.findById(orderId);
        return orderRepository.complete(order.orElseThrow(() -> new NotFoundException("could not Found product for " + orderId)));
    }

    @Transactional(readOnly = true)
    public Optional<Order> findById(Long orderId){
        Order order = orderRepository.findById(orderId)
                .orElseThrow(
                        () -> new NotFoundException("Cannot find order for id: " + orderId)
                );
        Review review = reviewRepository.findById(order.getReview_seq())
                .orElseThrow(
                        () -> new NotFoundException("Cannot find review in order for id: " + orderId)
                );
        order.setReview(review);
        return Optional.of(order);
    }

    @Transactional(readOnly = true)
    public List<Order> findAll(Long offset, Integer size, Long userId){
        return orderRepository.findAll(offset, size, userId);
    }
}
