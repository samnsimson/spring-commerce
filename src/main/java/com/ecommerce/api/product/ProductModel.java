package com.ecommerce.api.product;

import com.ecommerce.api.utils.AppUtils;
import com.ecommerce.api.utils.BaseModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "products", uniqueConstraints = { @UniqueConstraint(columnNames = "sku") })
public class ProductModel extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private String id;

    @Column(name = "sku", nullable = false, unique = true, length = 64)
    private String sku;

    @Column(name = "name")
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "slug")
    private String slug;

    @Column(name = "retailPrice")
    private BigDecimal retailPrice;

    @Column(name = "salePrice")
    private BigDecimal salePrice;

    @PrePersist
    @PreUpdate
    private void prePersist() {
        if (this.sku == null || this.sku.isEmpty())  this.sku = AppUtils.randomId();
        if (this.name != null && (this.slug == null || this.slug.isEmpty())) {
            this.slug = this.name.trim().toLowerCase().replaceAll("[^a-z0-9\\s]", "").replaceAll("\\s+", "-");
        }
    }

}
