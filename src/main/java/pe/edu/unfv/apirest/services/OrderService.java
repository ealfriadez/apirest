package pe.edu.unfv.apirest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.unfv.apirest.dto.order.CreateOrderRequest;
import pe.edu.unfv.apirest.dto.order.CreateOrderResponse;
import pe.edu.unfv.apirest.models.*;
import pe.edu.unfv.apirest.models.Id.OrderHasProductId;
import pe.edu.unfv.apirest.repositories.*;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderHasProductsRepository orderHasProductsRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private ProductRepository productRepository;

    public CreateOrderResponse create(CreateOrderRequest request){
        User user = userRepository.findById(request.getIdUser()).orElseThrow(
                ()-> new RuntimeException("El usuario no existe")
        );

        Address address = addressRepository.findById(request.getIdAddress()).orElseThrow(
                ()-> new RuntimeException("La direccion no existe")
        );

        Order order = new Order();
        order.setUser(user);
        order.setAddress(address);
        order.setStatus(request.getStatus() != null ? request.getStatus() : "PAGADO");
        Order savedOrder = orderRepository.save(order);

        if(request.getProducts() == null || request.getProducts().isEmpty()){
            throw new RuntimeException(("Debes enviar al menos un producto"));
        }

        for(CreateOrderRequest.ProductQuantity productQuantity: request.getProducts()){
            Product product = productRepository.findById(productQuantity.getId()).orElseThrow(
                    ()-> new RuntimeException("El producto con ID: " + productQuantity.getId() + " no existe")
            );
            OrderHasProductId id = new OrderHasProductId(product.getId(), savedOrder.getId());
            OrderHasProducts orderHasProducts = new OrderHasProducts();
            orderHasProducts.setId(id);
            orderHasProducts.setProduct(product);
            orderHasProducts.setOrder(savedOrder);
            orderHasProducts.setQuantity(productQuantity.getQuantity());

            orderHasProductsRepository.save(orderHasProducts);
        }

        CreateOrderResponse response = new CreateOrderResponse();
        response.setId(savedOrder.getId());
        response.setIdUser(savedOrder.getUser().getId());
        response.setIdAddress(savedOrder.getAddress().getId());
        response.setStatus(savedOrder.getStatus());
        response.setCreatedAt(savedOrder.getCreatedAt());
        response.setUpdatedAt(savedOrder.getUpdatedAt());

        return response;
    }
}
