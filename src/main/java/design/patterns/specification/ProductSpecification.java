package design.patterns.specification;

public interface ProductSpecification {
    boolean isSatisfiedBy(Product product);
//    ProductSpecification belowPrice(double price);
//    ProductSpecification and(ProductSpecification productSpecification);
//    ProductSpecification not(ProductSpecification productSpecification);
//    ProductSpecification byColor(ProductSpecification productSpecification);
}
