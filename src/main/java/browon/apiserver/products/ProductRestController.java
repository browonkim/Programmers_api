package browon.apiserver.products;


import browon.apiserver.errors.NotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static browon.apiserver.utils.ApiUtils.ApiResult;
import static browon.apiserver.utils.ApiUtils.success;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("api/products")
public class ProductRestController {

    private final ProductService productService;

    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    // FIXME `요건 1` 정의에 맞게 응답 타입 수정이 필요합니다.
    // 현재 클래스에 대한 테스트를 작동시키면 success한 상황에서 오류가 발생합니다.
    // 따라서 저는 기존에 구현된 API(success, ApiResult)를 사용해서 타입을 수정했습니다.
    @GetMapping(path = "{id}")
    public ApiResult<ProductDto> findById(@PathVariable Long id) {
        return success(
                productService.findById(id)
                .map(ProductDto::new)
                .orElseThrow(() -> new NotFoundException("Could not found product for " + id))
        );
    }

    // FIXME `요건 1` 정의에 맞게 응답 타입 수정이 필요합니다.
    @GetMapping
    public ApiResult<List<ProductDto>> findAll() {

        return success(
                productService.findAll().stream()
                .map(ProductDto::new)
                .collect(toList())
        );
    }

}