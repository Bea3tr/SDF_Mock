package mock;

import java.util.*;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) {
        List<Product> products = new ArrayList<>();
        // Add products into list
        products.add(new Product(1l, "Mouse", "For click UI on screen", "Computer", 99.0f, new Date()));
        products.add(new Product(2l, "Keyboard", "Device that allows alpha numeric inputs", "Computer", 235.5f, new Date()));
        products.add(new Product(3l, "15.6 inch Monitor", "Extended display panel", "Computer", 157.5f, new Date()));
        products.add(new Product(4l, "Huawei Pura 70 Ultra", "Huawei Phone", "Mobile", 900f, new Date()));
        products.add(new Product(5l, "Huawei Mate 50 Pro", "Huawei Phone", "Mobile", 1200f, new Date()));
        products.add(new Product(6l, "iPhone 16 Pro", "iPhone", "Mobile", 2000f, new Date()));
        products.add(new Product(7l, "iPhone 14 Pro", "iPhone", "Mobile", 1800f, new Date()));

        // Filter list using stream and lambda functions
        List<Product> filtered = products.stream()
                                        .filter(p -> p.getPrice() > 1500)
                                        .collect(Collectors.toList());
        filtered.forEach(System.out::println);
    }
    
}
