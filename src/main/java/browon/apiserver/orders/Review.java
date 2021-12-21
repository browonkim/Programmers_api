package browon.apiserver.orders;

import java.time.LocalDateTime;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;

public class Review {
    private final Long seq;
    private final Long user_seq;
    private final Long productId;
    private final String content;
    private final LocalDateTime create_at;

    public Review(Long user_seq, Long productId, String content){
        this(null, user_seq, productId, content, null);
    }

    public Review(Long seq, Long user_seq, Long productId, String content, LocalDateTime create_at){
        this.seq = seq;
        this.user_seq = user_seq;
        this.productId = productId;
        checkNotNull(content, "content must be provided");
        checkArgument(
                1 <= content.length() && content.length() <= 1000,
                "content length must be between 1 and 1000 characters"
        );
        this.content = content;
        this.create_at = defaultIfNull(create_at, LocalDateTime.now());
    }

    public Long getSeq() {
        return seq;
    }

    public Long getUser_seq() {
        return user_seq;
    }

    public Long getProductId() {
        return productId;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreate_at() {
        return create_at;
    }


}
