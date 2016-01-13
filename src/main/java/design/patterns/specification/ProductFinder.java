package design.patterns.specification;

import java.util.List;

public class ProductFinder {
    private ProductRepository repository;
    private ProductSpecification spec;

    public ProductFinder() {
        spec = new ProductSpecification() {
            @Override
            public boolean isSatisfiedBy(Product product) {
                return false;
            }
        };
    }

    public List<Product> belowPrice(double price){
        ProductSpecification filter = spec.belowPrice(20d);
        return repository.selectBy(filter);
    }

    public List<Product> belowPriceAndNotSpecificColor(double price, String color){
        ProductSpecification below = spec.belowPrice(30);
        ProductSpecification not = spec.not(spec.byColor(color));
        ProductSpecification and = below.and(not);
        return repository.selectBy(and);
    }

}
