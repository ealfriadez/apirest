package pe.edu.unfv.apirest.models.Id;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class OrderHasProductId implements Serializable {

    @Column(name = "id_product")
    private Long idProduct;

    @Column(name = "id_order")
    private Long idOrder;

    public OrderHasProductId() {
    }

    public OrderHasProductId(Long idProduct, Long idOrder) {
        this.idProduct = idProduct;
        this.idOrder = idOrder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderHasProductId)) return false;
        OrderHasProductId orderHasProductId = (OrderHasProductId) o;
        return Objects.equals(idProduct, orderHasProductId.idProduct) && Objects.equals(idOrder, orderHasProductId.idOrder);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProduct, idOrder);
    }
}
