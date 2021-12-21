package browon.apiserver.orders;

import java.util.Optional;

public interface ReviewRepository {
    Optional<Review> findById(Long id);
    Optional<Review> insert(Long userId, Long productId, String content);

}
