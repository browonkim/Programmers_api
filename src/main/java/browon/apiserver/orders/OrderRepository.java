package browon.apiserver.orders;

import java.util.List;
import java.util.Optional;

public interface OrderRepository {

    Boolean accept(Order order);

    Boolean reject(Order order);

    Boolean shipping(Order order);

    Boolean complete(Order order);

    Optional<Order> findById(long order_id);

    void writeReview(Long order_id, Long review_id);

    Boolean hasReview(Long order_id);

    List<Order> findAll(long offset, int size, long user_id);


}
