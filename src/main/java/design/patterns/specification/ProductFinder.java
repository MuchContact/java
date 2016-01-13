package design.patterns.specification;

import java.util.List;

public class ProductFinder {
    private ProductRepository repository;

    public List<Product> belowPrice(double price){
        ProductSpecification filter = ProductSpecification.belowPrice(20d);
        return repository.selectBy(filter);
    }

    public List<Product> belowPriceAndNotSpecificColor(double price, String color){
        ProductSpecification below = ProductSpecification.belowPrice(30);
        ProductSpecification not = ProductSpecification.not(ProductSpecification.byColor(color));
        ProductSpecification and = below.and(not);
        return repository.selectBy(and);
    }

}
