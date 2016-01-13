package design.patterns.specification;

public abstract class ProductSpecification {
    public abstract boolean isSatisfiedBy(Product product);

    public static ProductSpecification belowPrice(double price) {
        return new ProductSpecification() {
            @Override
            public boolean isSatisfiedBy(Product product) {
                return product.getPrice() < price;
            }
        };
    }

    public ProductSpecification and(ProductSpecification productSpecification) {
        return new AndProductSpecification(this, productSpecification);
    }

    public static ProductSpecification not(ProductSpecification productSpecification) {
        return new ProductSpecification() {
            @Override
            public boolean isSatisfiedBy(Product product) {
                return !productSpecification.isSatisfiedBy(product);
            }
        };
    }

    public static ProductSpecification byColor(String color) {
        return new ProductSpecification() {
            @Override
            public boolean isSatisfiedBy(Product product) {
               return product.getColor().equalsIgnoreCase(color);
            }
        };
    }
}
