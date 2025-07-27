package gadgetStore.service.serviceImpl;

import gadgetStore.config.jwtConfig.JwtService;
import gadgetStore.dto.SimpleResponse;
import gadgetStore.dto.basketDto.BasketResponse;
import gadgetStore.dto.productDto.ProductResponseForGetAll;
import gadgetStore.entities.Basket;
import gadgetStore.entities.Product;
import gadgetStore.entities.User;
import gadgetStore.repository.BasketRepo;
import gadgetStore.repository.ProductRepo;
import gadgetStore.repository.UserRepo;
import gadgetStore.service.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BasketServiceImpl implements BasketService {
    private final BasketRepo basketRepo;
    private final JwtService jwtService;
    private final ProductRepo productRepo;
    private final UserRepo userRepo;

    @Override
    public SimpleResponse addProduct(Long id) { // productId
        User user = jwtService.getAuthentication();
        Product product = productRepo.getProductByIdOrException(id);

        boolean isRemoved = user.getBasket().getProducts().remove(product);

        if (!isRemoved) {
            user.getBasket().getProducts().add(product);
            userRepo.save(user);

            return SimpleResponse.builder()
                    .httpStatus(HttpStatus.OK)
                    .message("Product with id '" + id + "' added to basket")
                    .build();
        } else {
            userRepo.save(user);

            return SimpleResponse.builder()
                    .httpStatus(HttpStatus.OK)
                    .message("Product with id '" + id + "' removed in basket")
                    .build();
        }
    }

    @Override
    public SimpleResponse cleanBasket() {
        User user = jwtService.getAuthentication();
        Basket basket = user.getBasket();
        user.getBasket().getProducts().clear();
        basketRepo.save(basket);

        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Basket successfully cleaned")
                .build();
    }

    @Override
    public BasketResponse getAllProductsInBasket() {
        User user = jwtService.getAuthentication();
        List<Product> products = user.getBasket().getProducts();
        List<ProductResponseForGetAll> resProds = new ArrayList<>();
        double totalPrice = 0;
        for (Product product : products) {
            resProds.add(
                    ProductResponseForGetAll.builder()
                            .id(product.getId())
                            .name(product.getName())
                            .price(product.getPrice())
                            .madeIn(product.getMadeIn())
                            .category(product.getCategory())
                            .build()
            );
            totalPrice += product.getPrice();
        }

        return BasketResponse.builder()
                .id(user.getBasket().getId())
                .products(resProds)
                .totalPrice(totalPrice)
                .count(products.size())
                .build();
    }


}
