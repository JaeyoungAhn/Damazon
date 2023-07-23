package com.damazon.generator;

import com.damazon.model.*;
import com.damazon.repository.*;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Component
public class DatabaseGenerator implements CommandLineRunner {

    private static final List<String> categories = Arrays.asList("Fiction", "Non-fiction", "Science", "History", "Philosophy", "Art");
    private final CategoryRepository categoryRepository;
    private final BookRepository bookRepository;
    private final CustomerRepository customerRepository;
    private final BookOrderRepository bookOrderRepository;
    private final OrderItemRepository orderItemRepository;

    public DatabaseGenerator(CategoryRepository categoryRepository, BookRepository bookRepository, CustomerRepository customerRepository, BookOrderRepository bookOrderRepository, OrderItemRepository orderItemRepository) {
        this.categoryRepository = categoryRepository;
        this.bookRepository = bookRepository;
        this.customerRepository = customerRepository;
        this.bookOrderRepository = bookOrderRepository;
        this.orderItemRepository = orderItemRepository;
    }

    @Override
    public void run(String... args) {
        if (categoryRepository.count() == 0) {
            categories.forEach(categoryName -> {
                Category category = new Category();
                category.setName(categoryName);
                categoryRepository.save(category);
            });
        }

        Faker faker = new Faker();
        Random random = new Random();
        List<Book> books = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            Book book = new Book();
            book.setTitle(faker.book().title());
            book.setPrice((faker.number().numberBetween(0, 12) * 1000) + 18000);
            book.setAuthor(faker.book().author());
            int randomCategoryId = random.nextInt(categories.size()) + 1;
            Category category = categoryRepository.findById(randomCategoryId).orElse(null);
            book.setCategory(category);
            books.add(book);
            bookRepository.save(book);

            Customer customer = new Customer();
            customer.setName(faker.name().fullName());
            customer.setEmail(faker.internet().emailAddress());
            customer.setAddress(faker.address().fullAddress());
            customerRepository.save(customer);
        }

        List<Customer> customers = customerRepository.findAll();
        OrderStatus[] orderStatus = OrderStatus.values();

        for (int i = 0; i < 100; i++) {
            BookOrder bookOrder = new BookOrder();
            Customer randomCustomer = customers.get(random.nextInt(customers.size()));
            OrderStatus randomStatus = orderStatus[random.nextInt(orderStatus.length)];

            bookOrder.setCustomer(randomCustomer);
            bookOrder.setStatus(randomStatus);

            bookOrderRepository.save(bookOrder);

            int numberOfItems = random.nextInt(5) + 1;
            List<OrderItem> orderItems = new ArrayList<>();

            for (int j = 0; j < numberOfItems; j++) {
                OrderItem item = new OrderItem();
                Book randomBook = books.get(random.nextInt(books.size()));
                int quantity = random.nextInt(3) + 1;

                item.setBook(randomBook);
                item.setQuantity(quantity);
                item.setBookOrder(bookOrder);

                orderItems.add(item);
            }

            orderItemRepository.saveAll(orderItems);
        }
    }
}