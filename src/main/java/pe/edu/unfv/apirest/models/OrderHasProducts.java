package pe.edu.unfv.apirest.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.edu.unfv.apirest.models.Id.OrderHasProductId;

import java.time.LocalDateTime;

@Entity
@Table(name = "order_has_products")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderHasProducts {

    @EmbeddedId
    private OrderHasProductId id;

    @ManyToOne
    @MapsId("idProduct")
    @JoinColumn(name = "id_product")
    private Product product;

    @ManyToOne
    @MapsId("idOrder")
    @JoinColumn(name = "id_order")
    private Order order;

    private Long quantity;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt = LocalDateTime.now();
}
