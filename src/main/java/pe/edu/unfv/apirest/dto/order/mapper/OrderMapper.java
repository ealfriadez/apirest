package pe.edu.unfv.apirest.dto.order.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pe.edu.unfv.apirest.dto.address.mapper.AddressMapper;
import pe.edu.unfv.apirest.dto.order.OrderHasProductResponse;
import pe.edu.unfv.apirest.dto.order.OrderResponse;
import pe.edu.unfv.apirest.dto.product.mapper.ProductMapper;
import pe.edu.unfv.apirest.dto.user.mapper.UserMapper;
import pe.edu.unfv.apirest.models.Order;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {

    @Autowired
    private AddressMapper addressMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ProductMapper productMapper;

    public OrderResponse toOrderResponse(Order order){
        OrderResponse response = new OrderResponse();
        response.setId(order.getId());
        response.setStatus(order.getStatus());
        response.setCreatedAt(order.getCreatedAt());
        response.setUpdatedAt(order.getUpdatedAt());
        response.setAddress(addressMapper.toAddressResponse(order.getAddress()));
        response.setUser(userMapper.toSimpleUserResponse(order.getUser()));

        List<OrderHasProductResponse> products = order.getOrderHasProducts().stream().map(ohp -> {
            OrderHasProductResponse res = new OrderHasProductResponse();
            res.setProduct(productMapper.toProductResponse(ohp.getProduct()));
            res.setQuantity(ohp.getQuantity());
            res.setCreatedAt(ohp.getCreatedAt());
            res.setUpdatedAt(ohp.getUpdatedAt());
            return res;
        }).collect(Collectors.toList());

        response.setOrderHasProducts(products);
        return response;
    }
}
