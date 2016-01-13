package design.patterns.specification;

import java.util.List;
import java.util.stream.Collectors;

public class ProductRepository {
    private List<Product> products;

    public List<Product> selectBy(ProductSpecification spec) {
        return products.stream()
                .filter(product -> spec.isSatisfiedBy(product))
                .collect(Collectors.toList());
    }
}
