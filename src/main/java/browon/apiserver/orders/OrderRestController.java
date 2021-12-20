package browon.apiserver.orders;

import browon.apiserver.configures.web.SimplePageRequest;
import browon.apiserver.errors.NotFoundException;
import browon.apiserver.security.JwtAuthentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static browon.apiserver.utils.ApiUtils.ApiResult;
import static browon.apiserver.utils.ApiUtils.success;
import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("api/orders")
public class OrderRestController {
    // TODO findAll, findById, accept, reject, shipping, complete 메소드 구현이 필요합니다.
    private final OrderService orderService;

    OrderRestController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ApiResult<List<OrderDto>> findAll(@RequestParam SimplePageRequest simplePageRequest,
                                             @AuthenticationPrincipal JwtAuthentication authentication) {

        return success(
                orderService.findAll(simplePageRequest.getOffset(), simplePageRequest.getSize(), authentication.id)
                        .stream()
                        .map(OrderDto::new)
                        .collect(toList())
        );
    }

    @GetMapping(path = "{id}")
    public ApiResult<OrderDto> findById(@PathVariable Long id, @AuthenticationPrincipal JwtAuthentication authentication) {
        return success(
                orderService.findById(id)
                        .map(OrderDto::new)
                        .orElseThrow(() -> new NotFoundException("Could not found order for " + id))
        );
    }

    @GetMapping("{id}/accept")
    public ApiResult<Boolean> accept(@PathVariable Long id, @AuthenticationPrincipal JwtAuthentication authentication) {
        //TODO if review_seq != null : review 객체 (JSON 배열)도 반환할 수 있어야함.
        // ex) review : {seq: 1, productId: 2, content: "I like It" ...}
        // 즉 Order 자체에 review 객체를 포함하거나 혹은 Dto에 review 객체를 포함해야함
        // accept, reject, shipping, complete 는 requestBody 로 Message를 가져야함.
        // 이것은 insert할 때 주입되어야함
        // 각 시각은 현재 시각을 기준으로 설정해줘야함


        return success(
                orderService.accept(id)
        );
    }

    @GetMapping("{id}/reject")
    public ApiResult<Boolean> reject(@PathVariable Long id, @AuthenticationPrincipal JwtAuthentication authentication) {
        return success(
                orderService.reject(id)
        );
    }

    @GetMapping("{id}/shipping")
    public ApiResult<Boolean> shipping(@PathVariable Long id, @AuthenticationPrincipal JwtAuthentication authentication) {
        return success(
                orderService.shipping(id)
        );
    }

    @GetMapping("{id}/complete")
    public ApiResult<Boolean> complete(@PathVariable Long id, @AuthenticationPrincipal JwtAuthentication authentication) {
        return success(
                orderService.complete(id)
        );
    }

}