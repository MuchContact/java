package design.patterns.specification;

public class ProductColorSpecification implements ProductSpecification{
    private String color;

    public ProductColorSpecification(String color) {
        this.color = color;
    }

    @Override
    public boolean isSatisfiedBy(Product product) {
        return product.getColor().equalsIgnoreCase(color);
    }
}
