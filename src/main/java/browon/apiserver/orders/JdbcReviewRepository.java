package browon.apiserver.orders;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static browon.apiserver.utils.DateTimeUtils.dateTimeOf;
import static java.util.Optional.ofNullable;

@Repository
public class JdbcReviewRepository implements ReviewRepository {
    private final JdbcTemplate jdbcTemplate;

    public JdbcReviewRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Review> findById(Long id) {
        List<Review> results = jdbcTemplate.query(
                "SELECT * FROM reviews WHERE seq=?",
                mapper,
                id
        );
        return ofNullable(results.isEmpty() ? null : results.get(0));
    }

    public Optional<Review> findByProductAndSeq(Long user_id, Long product_id) {
        List<Review> results = jdbcTemplate.query(
                "SELECT * FROM reviews WHERE user_seq = ? AND product_seq = ?",
                mapper,
                user_id,
                product_id
        );
        return ofNullable(results.isEmpty() ? null : results.get(0));
    }

    @Override
    public Optional<Review> insert(Long user_id, Long product_id, String content) {
        jdbcTemplate.update(
                "INSERT INTO reviews(user_seq, product_seq, content) VALUES(?,?,?)",
                user_id,
                product_id,
                content
        );
        return this.findByProductAndSeq(user_id, product_id);
    }

    static RowMapper<Review> mapper = (rs, rowNum) ->
            new Review(
                    rs.getLong("seq"),
                    rs.getLong("user_seq"),
                    rs.getLong("product_seq"),
                    rs.getString("content"),
                    dateTimeOf(rs.getTimestamp("create_at"))
            );
}
