package browon.apiserver.orders;

import static browon.apiserver.utils.ApiUtils.ApiResult;
import static browon.apiserver.utils.ApiUtils.success;
import static com.google.common.base.Preconditions.checkArgument;
import static org.apache.logging.log4j.util.Strings.isEmpty;
import static org.apache.logging.log4j.util.Strings.isNotEmpty;

import browon.apiserver.security.JwtAuthentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.Map;

@RestController
@RequestMapping("api/orders")
public class ReviewRestController {

    private final ReviewService reviewService;

    public ReviewRestController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping(path = "{id}/review")
    public ApiResult<ReviewDto> review(
            @AuthenticationPrincipal JwtAuthentication authentication,
            @RequestBody Map<String, String> content,
            @PathVariable Long id) {

        //RequestBody 를 Map의 형태로 가져오는 방법
        //HandlerMethodArgumentResolve를 사용하기

        String review_content = content.get("content");
        checkArgument(
                !isEmpty(review_content),
                "There is no content. Content must be exist."
        );
        checkArgument(
                1 <= review_content.trim().length() && review_content.length() <= 1000,
                "content length must be between 1 and 1000 characters"
        );
        return success(
                new ReviewDto(reviewService.insert(id, review_content))
        );
    }

}


