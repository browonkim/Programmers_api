package browon.apiserver.orders;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.time.LocalDateTime;

import static org.springframework.beans.BeanUtils.copyProperties;

public class ReviewDto {

    private Long seq;
    private Long productId;
    private String content;
    private LocalDateTime createAt;

    public ReviewDto(Review source) {
        this.seq = source.getSeq();
        this.productId = source.getProduct_seq();
        this.content = source.getContent();
        this.createAt = source.getCreate_at();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("seq", seq)
                .append("productId", productId)
                .append("content", content)
                .append("createAt", createAt)
                .toString();
    }
}
