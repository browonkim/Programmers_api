package browon.apiserver.orders;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static browon.apiserver.utils.DateTimeUtils.dateTimeOf;
import static java.util.Optional.ofNullable;

@Repository
public class JdbcOrderRepository implements OrderRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcOrderRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Boolean accept(Order order) {
        if (order.getState().equals("REQUESTED")) {
            order.setState("ACCEPTED");
            jdbcTemplate.update(
                    "UPDATE orders SET state=? WHERE seq=?",
                    order.getState(),
                    order.getSeq()
            );
            return true;
        } else{
            return false;
        }
    }
    @Override
    public Boolean reject(Order order) {
        if (order.getState().equals("REQUESTED")) {
            order.setState("REJECTED");
            jdbcTemplate.update(
                    "UPDATE orders SET state=? WHERE seq=?",
                    order.getState(),
                    order.getSeq()
            );
            return true;
        } else{
            return false;
        }
    }

    @Override
    public Boolean shipping(Order order) {
        if (order.getState().equals("ACCEPTED")) {
            order.setState("SHIPPING");
            jdbcTemplate.update(
                    "UPDATE orders SET state=? WHERE seq=?",
                    order.getState(),
                    order.getSeq()
            );
            return true;
        } else{
            return false;
        }
    }

    @Override
    public Boolean complete(Order order) {
        if (order.getState().equals("SHIPPING")) {
            order.setState("COMPLETED");
            jdbcTemplate.update(
                    "UPDATE orders SET state=? WHERE seq=?",
                    order.getState(),
                    order.getSeq()
            );
            return true;
        } else{
            return false;
        }
    }

    @Override
    public Optional<Order> findById(long id) {
        //TODO
        /*
        * userId가 필요없어졌습니다. (id는 order의 primarykey입니다.)
        * */
        List<Order> result = jdbcTemplate.query(
                "SELECT * FROM orders WHERE seq=?",
                mapper,
                id
                );

        return ofNullable(result.isEmpty() ? null : result.get(0));
    }

    @Override
    public void writeReview(Long order_id, Long review_id) {
        jdbcTemplate.update(
                "UPDATE orders SET review_seq=? WHERE seq=?",
                review_id,
                order_id
        );
    }

    @Override
    public List<Order> findAll(long offset, int size, long user_id) {
        return jdbcTemplate.query(
                "SELECT * FROM orders WHERE user_id=? ORDER BY seq DESC LIMIT ? OFFSET ?",
                mapper,
                user_id,
                size,
                offset
                );
    }

    static RowMapper<Order> mapper = (rs, rowNum) ->
            new Order.Builder()
                    .seq(rs.getLong("seq"))
                    .user_seq(rs.getLong("user_seq"))
                    .product_seq(rs.getLong("product_seq"))
                    .review_seq(rs.getLong("review_seq"))
                    .state(rs.getString("state"))
                    .request_msg(rs.getString("request_msg"))
                    .reject_msg(rs.getString("reject_msg"))
                    .completed_at(dateTimeOf(rs.getTimestamp("completed_at")))
                    .rejected_at(dateTimeOf(rs.getTimestamp("rejected_at")))
                    .create_at(dateTimeOf(rs.getTimestamp("create_at")))
                    .build();
}
