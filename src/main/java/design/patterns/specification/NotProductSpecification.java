package design.patterns.specification;

public class NotProductSpecification implements ProductSpecification{
    private ProductSpecification spec;

    public NotProductSpecification(ProductSpecification spec) {
        this.spec = spec;
    }

    @Override
    public boolean isSatisfiedBy(Product product) {
        return !spec.isSatisfiedBy(product);
    }
}
