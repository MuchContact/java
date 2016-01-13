package design.patterns.specification;

public class ProductBelowPriceSpecification implements ProductSpecification{
    private final double price;

    public ProductBelowPriceSpecification(double price) {
        this.price = price;
    }

    @Override
    public boolean isSatisfiedBy(Product product) {
        return product.getPrice() < price;
    }
}
