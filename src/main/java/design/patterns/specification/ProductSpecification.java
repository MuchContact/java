package design.patterns.specification;

public interface ProductSpecification {
    boolean isSatisfiedBy(Product product);

    default ProductSpecification belowPrice(double price) {
        return new ProductSpecification() {
            @Override
            public boolean isSatisfiedBy(Product product) {
                return product.getPrice() < price;
            }
        };
    }

    default ProductSpecification and(ProductSpecification productSpecification) {
        return new AndProductSpecification(this, productSpecification);
    }

    default ProductSpecification not(ProductSpecification productSpecification) {
        return new ProductSpecification() {
            @Override
            public boolean isSatisfiedBy(Product product) {
                return !productSpecification.isSatisfiedBy(product);
            }
        };
    }

    default ProductSpecification byColor(String color) {
        return new ProductSpecification() {
            @Override
            public boolean isSatisfiedBy(Product product) {
               return product.getColor().equalsIgnoreCase(color);
            }
        };
    }
}
