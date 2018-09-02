package pl.polsl.paweljaneta.databasebenchmark.dataConfig;

import org.springframework.stereotype.Component;

@Component
public class DataConfig {
    public final int NO_OF_STORES = 100;
    public final int NO_OF_CLIENTS = 1000;
    public final int NO_OF_CARTS = 20000;
    public final int NO_OF_DISCOUNTS = 500;
    public final int NO_OF_ORDERS = 10000;
    public final int NO_OF_PRODUCTS = 50000;
    public final int NO_OF_SHIPMENTS= 2000;
    public final int NO_OF_TRANSACTIONS = 10000;
    public final int MAX_NO_OF_PRODUCTS_IN_TRANSACTION_CART_ORDER = 15;
    public final int MIN_NO_OF_PRODUCTS_IN_STORE = 100;
    public final int MAX_NO_OF_PRODUCTS_IN_STORE = 5000;
    public final int MAX_PRODUCT_QUANTITY = 100;
}
