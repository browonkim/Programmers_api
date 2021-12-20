package browon.apiserver.orders;

import static browon.apiserver.utils.ApiUtils.ApiResult;
import static browon.apiserver.utils.ApiUtils.success;

import browon.apiserver.security.JwtAuthentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/orders")
public class ReviewRestController {

    private final ReviewService reviewService;

    public ReviewRestController(ReviewService reviewService){
        this.reviewService = reviewService;
    }

    @PostMapping(path= "{id}/review")
    public ApiResult<ReviewDto> insert(
            @AuthenticationPrincipal JwtAuthentication authentication,
            @RequestBody String content,
            @PathVariable Long id) {

        return success(
                new ReviewDto(reviewService.insert(authentication.id, id, content))
        );
    }

}


