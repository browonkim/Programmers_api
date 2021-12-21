package browon.apiserver.orders;

import browon.apiserver.configures.web.Pageable;
import browon.apiserver.configures.web.SimplePageRequest;
import browon.apiserver.errors.NotFoundException;
import browon.apiserver.security.JwtAuthentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static browon.apiserver.utils.ApiUtils.ApiResult;
import static browon.apiserver.utils.ApiUtils.success;
import static com.google.common.base.Preconditions.checkArgument;
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
    public ApiResult<List<OrderDto>> findAll(
            @RequestParam(name = "offset", required = false, defaultValue = "0") Long offset,
            @RequestParam(name = "size", required = false, defaultValue = "5") Integer size,
            @AuthenticationPrincipal JwtAuthentication authentication) {

        if (offset < 0 || size <= 0) {
            offset = 0L;
            size = 5;
        }

        return success(
                orderService.findAll(offset, size, authentication.id)
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
    public ApiResult<Boolean> accept(@PathVariable Long id, @AuthenticationPrincipal JwtAuthentication authentication
            , @RequestBody Map<String, String> requestBody) {
        String message = requestBody.get("message");
        return success(
                orderService.accept(id, message)
        );
    }

    @GetMapping("{id}/reject")
    public ApiResult<Boolean> reject(@PathVariable Long id,
                                     @AuthenticationPrincipal JwtAuthentication authentication,
                                     @RequestBody Map<String, String> requestBody) {
        String message = requestBody.get("message");
        return success(
                orderService.reject(id, message)
        );
    }

    @GetMapping("{id}/shipping")
    public ApiResult<Boolean> shipping(@PathVariable Long id,
                                       @AuthenticationPrincipal JwtAuthentication authentication,
                                       @RequestBody Map<String, String> requestBody) {
        String message = requestBody.get("message");
        return success(
                orderService.shipping(id, message)
        );
    }

    @GetMapping("{id}/complete")
    public ApiResult<Boolean> complete(@PathVariable Long id,
                                       @AuthenticationPrincipal JwtAuthentication authentication,
                                       @RequestBody Map<String, String> requestBody) {
        String message = requestBody.get("message");
        return success(
                orderService.complete(id, message)
        );
    }

}