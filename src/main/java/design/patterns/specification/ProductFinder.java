package design.patterns.specification;

import java.util.List;

public class ProductFinder {
    private ProductRepository repository;

    public List<Product> belowPrice(double price){
        ProductBelowPriceSpecification filter = new ProductBelowPriceSpecification(price);
        return repository.selectBy(filter);
    }

    public List<Product> belowPriceAndNotSpecificColor(double price, String color){
        ProductBelowPriceSpecification belowFilter = new ProductBelowPriceSpecification(price);
        ProductColorSpecification colorFilter = new ProductColorSpecification(color);
        AndProductSpecification and = new AndProductSpecification(belowFilter, colorFilter);
        return repository.selectBy(and);
    }
//    ProductSpecification and(ProductSpecification productSpecification){
//        return new AndProductSpecification()
//    }
//    ProductSpecification not(ProductSpecification productSpecification);
//    ProductSpecification byColor(ProductSpecification productSpecification);
}
