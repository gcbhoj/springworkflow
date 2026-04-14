package com.bhoj.springbootapp.bootstrap;

import com.bhoj.springbootapp.beans.Ingredients;
import com.bhoj.springbootapp.beans.PackagedProduct;
import com.bhoj.springbootapp.beans.Retailer;
import com.bhoj.springbootapp.repository.PackagedProductRepository;
import com.bhoj.springbootapp.serviceImpl.RetailerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@RequiredArgsConstructor
@Async
@Order(3)
public class PackagedProductBootstrap implements CommandLineRunner {

    private final PackagedProductRepository packagedProductRepo;
    private final RetailerServiceImpl retailerServiceImpl;


    @Override
    public void run(String... args) {
        Retailer retailer =  retailerServiceImpl.getRetailerByName("Walmart");


        PackagedProduct coke = PackagedProduct.builder()
                .itemNumber("ef363a67-f33c-4c80-b27b-816195975f37")
                .productName("Coca-Cola")
                .imageURL("https://images.openfoodfacts.org/images/products/500/011/254/6415/front_de.137.400.jpg")
                .price(BigDecimal.valueOf(5.00))
                .upc("5000112546415")
                .packageWeight("2 L")
                .manufacturer("Coca-Cola Canada")
                .manufacturedIn("Canada")
                .quantity(1L)
                .retailer(retailer)
                .ingredients(List.of(
                        Ingredients.builder().ingredientName("water").build(),
                        Ingredients.builder().ingredientName("sugar").build(),
                        Ingredients.builder().ingredientName("added-sugar").build(),
                        Ingredients.builder().ingredientName("disaccharide").build(),
                        Ingredients.builder().ingredientName("e290").build(),
                        Ingredients.builder().ingredientName("colour").build(),
                        Ingredients.builder().ingredientName("acid").build(),
                        Ingredients.builder().ingredientName("e150d").build(),
                        Ingredients.builder().ingredientName("e338").build()
                ))

                .build();

        PackagedProduct sprite = PackagedProduct.builder()
                .itemNumber("88e0a890-634d-412c-9be9-8c4b05706505")
                .productName("Sprite Lemon-Lime Soda")
                .imageURL("https://images.openfoodfacts.org/images/products/049/000/050/158/front_en.3.400.jpg")
                .price(BigDecimal.valueOf(4.49))
                .upc("049000050158")
                .packageWeight("2 L")
                .manufacturer("The Coca-Cola Company")
                .manufacturedIn("USA")
                .quantity(1L)
                .retailer(retailer)
                .ingredients(List.of(
                        Ingredients.builder().ingredientName("carbonated water").build(),
                        Ingredients.builder().ingredientName("high fructose corn syrup").build(),
                        Ingredients.builder().ingredientName("citric acid").build(),
                        Ingredients.builder().ingredientName("natural flavors").build(),
                        Ingredients.builder().ingredientName("sodium citrate").build(),
                        Ingredients.builder().ingredientName("sodium benzoate").build()
                ))
                .build();

        PackagedProduct fanta = PackagedProduct.builder()
                .itemNumber("b9812241-64be-4256-b88c-a4aa7b02578f")
                .productName("Fanta Orange Soda")
                .imageURL("https://images.openfoodfacts.org/images/products/049/000/028/911/front_en.4.400.jpg")
                .price(BigDecimal.valueOf(4.29))
                .upc("049000028911")
                .packageWeight("2 L")
                .manufacturer("The Coca-Cola Company")
                .manufacturedIn("USA")
                .quantity(1L)
                .retailer(retailer)
                .ingredients(List.of(
                        Ingredients.builder().ingredientName("carbonated water").build(),
                        Ingredients.builder().ingredientName("high fructose corn syrup").build(),
                        Ingredients.builder().ingredientName("citric acid").build(),
                        Ingredients.builder().ingredientName("natural flavors").build(),
                        Ingredients.builder().ingredientName("modified food starch").build(),
                        Ingredients.builder().ingredientName("sodium benzoate").build(),
                        Ingredients.builder().ingredientName("color").build()
                ))
                .build();

        PackagedProduct pepsi = PackagedProduct.builder()
                .itemNumber("bd14be8c-e9c9-4c26-805c-8a4fcd7b3ce9")
                .productName("Pepsi Cola")
                .imageURL("https://images.openfoodfacts.org/images/products/012/000/809/151/front_en.5.400.jpg")
                .price(BigDecimal.valueOf(4.59))
                .upc("012000809151")
                .packageWeight("2 L")
                .manufacturer("PepsiCo")
                .manufacturedIn("USA")
                .quantity(1L)
                .retailer(retailer)
                .ingredients(List.of(
                        Ingredients.builder().ingredientName("carbonated water").build(),
                        Ingredients.builder().ingredientName("high fructose corn syrup").build(),
                        Ingredients.builder().ingredientName("caramel color").build(),
                        Ingredients.builder().ingredientName("phosphoric acid").build(),
                        Ingredients.builder().ingredientName("caffeine").build(),
                        Ingredients.builder().ingredientName("natural flavors").build()
                ))
                .build();

        PackagedProduct mountainDew = PackagedProduct.builder()
                .itemNumber("0ac6615d-c469-4d8a-b08e-cd36dc0117df")
                .productName("Mountain Dew")
                .imageURL("https://images.openfoodfacts.org/images/products/012/000/161/938/front_en.4.400.jpg")
                .price(BigDecimal.valueOf(4.99))
                .upc("012000161938")
                .packageWeight("2 L")
                .manufacturer("PepsiCo")
                .manufacturedIn("USA")
                .quantity(1L)
                .retailer(retailer)
                .ingredients(List.of(
                        Ingredients.builder().ingredientName("carbonated water").build(),
                        Ingredients.builder().ingredientName("high fructose corn syrup").build(),
                        Ingredients.builder().ingredientName("citric acid").build(),
                        Ingredients.builder().ingredientName("natural flavor").build(),
                        Ingredients.builder().ingredientName("sodium benzoate").build(),
                        Ingredients.builder().ingredientName("caffeine").build()
                ))
                .build();

        PackagedProduct drPepper = PackagedProduct.builder()
                .itemNumber("8f7399d6-0afa-4dd9-82a3-4db0448d6e5e")
                .productName("Dr Pepper")
                .imageURL("https://images.openfoodfacts.org/images/products/041/508/260/003/front_en.3.400.jpg")
                .price(BigDecimal.valueOf(4.79))
                .upc("041508260003")
                .packageWeight("2 L")
                .manufacturer("Keurig Dr Pepper")
                .manufacturedIn("USA")
                .quantity(1L)
                .retailer(retailer)
                .ingredients(List.of(
                        Ingredients.builder().ingredientName("carbonated water").build(),
                        Ingredients.builder().ingredientName("high fructose corn syrup").build(),
                        Ingredients.builder().ingredientName("caramel color").build(),
                        Ingredients.builder().ingredientName("phosphoric acid").build(),
                        Ingredients.builder().ingredientName("natural flavors").build(),
                        Ingredients.builder().ingredientName("caffeine").build()
                ))
                .build();

        PackagedProduct arizona = PackagedProduct.builder()
                .itemNumber("a448f2c1-bd9a-42cd-acde-f69d2d1eb14b")
                .productName("Arizona Green Tea with Honey")
                .imageURL("https://images.openfoodfacts.org/images/products/070/847/000/328/front_en.4.400.jpg")
                .price(BigDecimal.valueOf(1.99))
                .upc("070847000328")
                .packageWeight("680 ml")
                .manufacturer("Arizona Beverage Company")
                .manufacturedIn("USA")
                .quantity(1L)
                .retailer(retailer)
                .ingredients(List.of(
                        Ingredients.builder().ingredientName("filtered water").build(),
                        Ingredients.builder().ingredientName("high fructose corn syrup").build(),
                        Ingredients.builder().ingredientName("honey").build(),
                        Ingredients.builder().ingredientName("citric acid").build(),
                        Ingredients.builder().ingredientName("green tea extract").build()
                ))
                .build();

        PackagedProduct gatorade = PackagedProduct.builder()
                .itemNumber("4187d88a-db7f-4ab9-b461-5898caee10ed")
                .productName("Gatorade Lemon Lime")
                .imageURL("https://images.openfoodfacts.org/images/products/041/800/000/038/front_en.3.400.jpg")
                .price(BigDecimal.valueOf(2.49))
                .upc("041800000038")
                .packageWeight("591 ml")
                .manufacturer("PepsiCo")
                .manufacturedIn("USA")
                .quantity(1L)
                .retailer(retailer)
                .ingredients(List.of(
                        Ingredients.builder().ingredientName("water").build(),
                        Ingredients.builder().ingredientName("sugar").build(),
                        Ingredients.builder().ingredientName("dextrose").build(),
                        Ingredients.builder().ingredientName("citric acid").build(),
                        Ingredients.builder().ingredientName("salt").build(),
                        Ingredients.builder().ingredientName("natural flavor").build()
                ))
                .build();

        PackagedProduct pocari = PackagedProduct.builder()
                .itemNumber("0f3aff48-674f-4233-bc52-cf9aef042012")
                .productName("Pocari Sweat Isotonic Drink")
                .imageURL("https://images.openfoodfacts.org/images/products/490/243/078/0010/front_en.3.400.jpg")
                .price(BigDecimal.valueOf(2.79))
                .upc("4902430780010")
                .packageWeight("500 ml")
                .manufacturer("Otsuka Pharmaceutical")
                .manufacturedIn("Japan")
                .quantity(1L)
                .retailer(retailer)
                .ingredients(List.of(
                        Ingredients.builder().ingredientName("water").build(),
                        Ingredients.builder().ingredientName("sugar").build(),
                        Ingredients.builder().ingredientName("citric acid").build(),
                        Ingredients.builder().ingredientName("sodium chloride").build(),
                        Ingredients.builder().ingredientName("potassium chloride").build()
                ))
                .build();

        PackagedProduct oreo = PackagedProduct.builder()
                .itemNumber("81e5876f-663c-453e-8cd8-fe7bbcfd52c3")
                .productName("Oreo Original Cookies")
                .imageURL("https://images.openfoodfacts.org/images/products/044/000/032/029/front_en.5.400.jpg")
                .price(BigDecimal.valueOf(3.99))
                .upc("044000032029")
                .packageWeight("303 g")
                .manufacturer("Mondelez International")
                .manufacturedIn("USA")
                .quantity(1L)
                .retailer(retailer)
                .ingredients(List.of(
                        Ingredients.builder().ingredientName("enriched wheat flour").build(),
                        Ingredients.builder().ingredientName("sugar").build(),
                        Ingredients.builder().ingredientName("palm oil").build(),
                        Ingredients.builder().ingredientName("cocoa").build(),
                        Ingredients.builder().ingredientName("high fructose corn syrup").build(),
                        Ingredients.builder().ingredientName("soy lecithin").build()
                ))
                .build();

        PackagedProduct lays = PackagedProduct.builder()
                .itemNumber("411c3366-81ba-47ee-9932-0576f641c5e7")
                .productName("Lay's Classic Potato Chips")
                .imageURL("https://images.openfoodfacts.org/images/products/028/400/064/505/front_en.4.400.jpg")
                .price(BigDecimal.valueOf(3.49))
                .upc("028400064505")
                .packageWeight("235 g")
                .manufacturer("Frito-Lay")
                .manufacturedIn("USA")
                .quantity(1L)
                .retailer(retailer)
                .ingredients(List.of(
                        Ingredients.builder().ingredientName("potatoes").build(),
                        Ingredients.builder().ingredientName("vegetable oil").build(),
                        Ingredients.builder().ingredientName("salt").build()
                ))
                .build();

        packagedProductRepo.saveAll(List.of(
                coke, sprite, fanta, pepsi, mountainDew,
                drPepper, arizona, gatorade, pocari, oreo, lays
        ));
    }
}