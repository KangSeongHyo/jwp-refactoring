package kitchenpos.menuproduct.domain;

import kitchenpos.menu.domain.Menu;
import kitchenpos.product.domain.Product;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class MenuProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;
    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Menu menu;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    private Long quantity;

    public MenuProduct() {
    }

    public MenuProduct(Product product, Long quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public MenuProduct(Menu menu, Product product, Long quantity) {
        this.menu = menu;
        this.product = product;
        this.quantity = quantity;
    }

    public MenuProduct(Long seq, Menu menu, Product product, Long quantity) {
        this.seq = seq;
        this.menu = menu;
        this.product = product;
        this.quantity = quantity;
    }

    public BigDecimal menuProductPrice() {
        return this.product.getPrice().multiply(BigDecimal.valueOf(this.quantity));
    }

    public Long productId() {
        return this.product.getId();
    }

    public Long getSeq() {
        return seq;
    }

    public Menu getMenu() {
        return menu;
    }

    public Product getProduct() {
        return product;
    }

    public Long getQuantity() {
        return quantity;
    }
}