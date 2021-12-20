package browon.apiserver.orders;

import browon.apiserver.errors.NotFoundException;
import browon.apiserver.products.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    public ReviewService(ReviewRepository reviewRepository, ProductRepository productRepository, OrderRepository orderRepository) {
        this.productRepository = productRepository;
        this.reviewRepository = reviewRepository;
        this.orderRepository = orderRepository;
    }

    public Review insert(Long user_id, Long order_id, String content) {
        Order order = orderRepository.findById(order_id)
                .orElseThrow(() -> new NotFoundException("Could not found order for " + order_id));
        if (!order.getState().equals("COMPLETED")) {
            throw new IllegalStateException("Could not write review for order " + order_id
                    + "because state(" + order.getReview() + ") is not allowed");
        }
        if (order.getReview_seq() != null) {
            throw new IllegalStateException("Could not write review for order " + order_id
                    + " because have already written");
        }
        Review review = reviewRepository.insert(user_id, order_id, content)
                .orElseThrow(() -> new RuntimeException(order_id + "'s review insertion was failed."));
        orderRepository.writeReview(order_id, review.getSeq());
        productRepository.incrementReviewCount(order.getProductId());

        return review;
    }

}
