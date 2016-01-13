package design.patterns.specification;

public class AndProductSpecification implements ProductSpecification{
    private ProductSpecification spec1;
    private ProductSpecification spec2;

    public AndProductSpecification(ProductSpecification spec1, ProductSpecification spec2) {
        this.spec1 = spec1;
        this.spec2 = spec2;
    }

    @Override
    public boolean isSatisfiedBy(Product product) {
        return spec1.isSatisfiedBy(product) && spec2.isSatisfiedBy(product);
    }

}
