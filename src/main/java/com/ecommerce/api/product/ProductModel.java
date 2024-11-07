package com.ecommerce.api.product;

import com.ecommerce.api.utils.BaseModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products", uniqueConstraints = { @UniqueConstraint(columnNames = "sku") })
public class ProductModel extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private String id;

    @Column(name = "sku", nullable = false, unique = true, length = 50)
    private String sku;

    @Column(name = "name")
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "slug")
    private String slug;

    @Column(name = "retailPrice")
    private Float retailPrice;

    @Column(name = "salePrice")
    private Float salePrice;

    @PrePersist
    @PreUpdate
    private void generateSlug(){
        if (this.name != null && (this.slug == null || this.slug.isEmpty())){
            this.slug = name.toLowerCase().replaceAll("[^a-z0-9\\s]", "").replaceAll("\\s+", "-");
        }
    }
}
