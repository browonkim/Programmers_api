package browon.apiserver.orders;

import java.util.List;
import java.util.Optional;

public interface OrderRepository {

    Boolean accept(Order order);

    Boolean reject(Order order);

    Boolean shipping(Order order);

    Boolean complete(Order order);

    Optional<Order> findById(long product_id);

    List<Order> findAll();


}
