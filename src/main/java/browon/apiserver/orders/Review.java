package browon.apiserver.orders;

import java.time.LocalDateTime;
import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;

public class Review {
    private final Long seq;
    private final Long user_seq;
    private final Long product_seq;
    private final String content;
    private final LocalDateTime create_at;

    public Review(Long user_seq, Long product_seq, String content){
        this(null, user_seq, product_seq, content, null);
    }

    public Review(Long seq, Long user_seq, Long product_seq, String content, LocalDateTime create_at){
        this.seq = seq;
        this.user_seq = user_seq;
        this.product_seq = product_seq;
        this.content = defaultIfNull(content, "");
        this.create_at = defaultIfNull(create_at, LocalDateTime.now());
    }

    public Long getSeq() {
        return seq;
    }

    public Long getUser_seq() {
        return user_seq;
    }

    public Long getProduct_seq() {
        return product_seq;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreate_at() {
        return create_at;
    }


}
