package browon.apiserver.orders;


import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.time.LocalDateTime;
import java.util.Objects;

import static java.time.LocalDateTime.now;
import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;

public class Order {

    private final Long seq;
    private final Long userId;
    private final Long productId;
    private Long review;
    private String state;
    private String requestMessage;
    private String rejectMessage;
    private LocalDateTime completedAt;
    private LocalDateTime rejectedAt;
    private LocalDateTime createAt;

    public Long getReview() {
        return review;
    }

    public void setReview(Long review) {
        this.review = review;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getRequestMessage() {
        return requestMessage;
    }

    public void setRequestMessage(String requestMessage) {
        this.requestMessage = requestMessage;
    }

    public String getRejectMessage() {
        return rejectMessage;
    }

    public void setRejectMessage(String rejectMessage) {
        this.rejectMessage = rejectMessage;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }

    public LocalDateTime getRejectedAt() {
        return rejectedAt;
    }

    public void setRejectedAt(LocalDateTime rejectedAt) {
        this.rejectedAt = rejectedAt;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public Order(Long user_Seq, Long productId) {
        this(null, user_Seq, productId, null, null, null, null, null, null, null);
    }

    public Order(Long seq, Long userId, Long productId, Long review, String state, String requestMessage, String rejectMessage, LocalDateTime completedAt, LocalDateTime rejectedAt, LocalDateTime createAt) {
        this.seq = seq;
        this.userId = userId;
        this.productId = productId;
        this.review = review;
        this.state = defaultIfNull(state, "REQUESTED");
        this.requestMessage = requestMessage;
        this.rejectMessage = rejectMessage;
        this.completedAt = completedAt;
        this.rejectedAt = rejectedAt;
        this.createAt = defaultIfNull(createAt, now());
    }

    public Long getSeq() {
        return seq;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getProductId() {
        return productId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(seq);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(seq, order.seq);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("seq", seq)
                .append("userId", userId)
                .append("productId", productId)
                .append("review", review)
                .append("state", state)
                .append("requestMessage", requestMessage)
                .append("rejectMessage", rejectMessage)
                .append("completedAt", completedAt)
                .append("rejectedAt", rejectedAt)
                .append("createAt", createAt)
                .toString();
    }

    static public class Builder {
        private Long seq;
        private Long userId;
        private Long productId;
        private Long review;
        private String state;
        private String requestMessage;
        private String rejectMessage;
        private LocalDateTime completedAt;
        private LocalDateTime rejectedAt;
        private LocalDateTime createAt;

        public Builder() {/*empty*/}

        public Builder(Order order) {
            this.seq = order.seq;
            this.userId = order.userId;
            this.productId = order.productId;
            this.review = order.review;
            this.state = order.state;
            this.requestMessage = order.requestMessage;
            this.rejectMessage = order.rejectMessage;
            this.completedAt = order.completedAt;
            this.rejectedAt = order.rejectedAt;
            this.createAt = order.createAt;
        }

        public Builder seq(Long seq) {
            this.seq = seq;
            return this;
        }

        public Builder user_seq(Long user_seq) {
            this.userId = user_seq;
            return this;
        }

        public Builder product_seq(Long product_seq) {
            this.productId = product_seq;
            return this;
        }

        public Builder review_seq(Long review_seq) {
            this.review = review_seq;
            return this;
        }

        public Builder state(String state) {
            this.state = state;
            return this;
        }

        public Builder request_msg(String request_msg) {
            this.requestMessage = request_msg;
            return this;
        }

        public Builder reject_msg(String reject_msg) {
            this.rejectMessage = reject_msg;
            return this;
        }

        public Builder completed_at(LocalDateTime completed_at) {
            this.completedAt = completed_at;
            return this;
        }

        public Builder rejected_at(LocalDateTime rejected_at) {
            this.rejectedAt = rejected_at;
            return this;
        }

        public Builder create_at(LocalDateTime create_at) {
            this.createAt = create_at;
            return this;
        }

        public Order build() {
            return new Order(seq, userId, productId, review, state, requestMessage, rejectMessage, completedAt, rejectedAt, createAt);
        }
    }
}
