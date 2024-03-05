package productservice.productservice.Entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "products", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "productBarcode"
        })
})
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 40)
    private String productName;


    @NotBlank
    private String productUrl;

    @NotBlank
    @Size(min = 0)
    private int productNumber;

    @NotBlank
    @Size(min = 0)
    private BigDecimal productPrice;

    @NotBlank
    private Long productCode;

    @NotBlank
    private boolean isHasBarcode;

    @NotBlank
    private boolean isFavorite;
}
