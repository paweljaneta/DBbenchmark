package pl.polsl.paweljaneta.databasebenchmark.dataInsertion.dataInsertors.impl;

import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.polsl.paweljaneta.databasebenchmark.model.DeliveryMode;
import pl.polsl.paweljaneta.databasebenchmark.model.mongo.entities.*;
import pl.polsl.paweljaneta.databasebenchmark.model.mongo.repository.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class MongoDataInsertor {

    @Autowired
    private MongoAddressRepository addressRepository;
    @Autowired
    private MongoClientRepository clientRepository;
    @Autowired
    private MongoStoreRepository storeRepository;
    @Autowired
    private MongoDiscountRepository discountRepository;
    @Autowired
    private MongoProductRepository productRepository;
    @Autowired
    private MongoCartRepository cartRepository;
    @Autowired
    private MongoOrderRepository orderRepository;
    @Autowired
    private MongoShipmentRepository shipmentRepository;
    @Autowired
    private MongoTransactionRepository transactionRepository;
    @Autowired
    private MongoProductsInStoresRepository productsInStoresRepository;

    public void insertClientAddressData(List<MongoAddress> clientAddressDataList, Iterable<CSVRecord> records) {
        for (CSVRecord record : records) {
            MongoAddress address = new MongoAddress();
            address.setCity(record.get("city"));
            address.setPostalCode(record.get("postalCode"));
            address.setStreet(record.get("street"));
            address.setStreetNumber(record.get("streetNumber"));
            clientAddressDataList.add(address);
        }
        addressRepository.saveAll(clientAddressDataList);
    }

    public void insertStoreAddressData(List<MongoAddress> storeAddressDataList, Iterable<CSVRecord> records) {
        for (CSVRecord record : records) {
            MongoAddress address = new MongoAddress();
            address.setCity(record.get("city"));
            address.setPostalCode(record.get("postalCode"));
            address.setStreet(record.get("street"));
            address.setStreetNumber(record.get("streetNumber"));
            storeAddressDataList.add(address);
        }
        addressRepository.saveAll(storeAddressDataList);
    }

    public void insertClientData(List<MongoClient> clientDataList, Iterable<CSVRecord> records,
                                 List<MongoAddress> addressList, List<Integer> addressIndexes) {
        int index = 0;
        for (CSVRecord record : records) {
            MongoClient client = new MongoClient();
            client.setName(record.get("name"));
            client.setPhoneNumber(record.get("phoneNumber"));
            client.setEmail(record.get("email"));
            client.setAddress(addressList.get(addressIndexes.get(index)));
            clientDataList.add(client);
            index++;
        }
        clientRepository.saveAll(clientDataList);
    }

    public void insertStoreData(List<MongoStore> storeDataList, Iterable<CSVRecord> records,
                                List<MongoAddress> addressList, List<Integer> addressIndexes) {
        int index = 0;
        for (CSVRecord record : records) {
            MongoStore store = new MongoStore();
            store.setName(record.get("name"));
            store.setAddress(addressList.get(addressIndexes.get(index)));
            storeDataList.add(store);
            index++;
        }
        storeRepository.saveAll(storeDataList);
    }

    public void insertDiscountData(List<MongoDiscount> discountDataList, Iterable<CSVRecord> records) {
        for (CSVRecord record : records) {
            MongoDiscount discount = new MongoDiscount();
            discount.setDiscountValue(Float.parseFloat(record.get("discountValue").replace(',', '.')));
            discountDataList.add(discount);
        }
        discountRepository.saveAll(discountDataList);
    }

    public void insertProductData(List<MongoProduct> productDataList, Iterable<CSVRecord> records, List<MongoDiscount> discountList, List<Integer> discountIdForProductId) {
        int index = 0;
        for (CSVRecord record : records) {
            MongoProduct product = new MongoProduct();
            product.setName(record.get("name"));
            product.setPrice(Float.parseFloat(record.get("price").replace(',', '.')));
            product.setDiscount(discountList.get(discountIdForProductId.get(index)));
            productDataList.add(product);
            index++;
        }
        productRepository.saveAll(productDataList);
    }

    public void insertCartData(List<MongoCart> cartDataList, List<MongoClient> clientList, List<Integer> clientIdForCartId, List<MongoProduct> productsList, List<List<Integer>> listOfProductIdsForCartId) {
        int index = 0;
        for (List<Integer> productIds : listOfProductIdsForCartId) {
            MongoCart cart = new MongoCart();
            cart.setClient(clientList.get(clientIdForCartId.get(index)));
            List<MongoProduct> products = new ArrayList<>();
            for (Integer productIndex : productIds) {
                products.add(productsList.get(productIndex));
            }
            cart.setProducts(products);
            cartDataList.add(cart);
            index++;
        }
        cartRepository.saveAll(cartDataList);
    }

    public void insertOrderData(List<MongoOrder> orderDataList, List<MongoClient> clientList, List<Integer> clientIdForOrderId, List<MongoProduct> productsList, List<List<Integer>> listOfProductIdsForOrderId) {
        int index = 0;
        for (List<Integer> productIds : listOfProductIdsForOrderId) {
            MongoOrder order = new MongoOrder();
            order.setClient(clientList.get(clientIdForOrderId.get(index)));
            List<MongoProduct> products = new ArrayList<>();
            for (Integer productIndex : productIds) {
                products.add(productsList.get(productIndex));
            }
            order.setProducts(products);
            orderDataList.add(order);
            index++;
        }
        orderRepository.saveAll(orderDataList);
    }

    public void insertShipmentData(List<MongoShipment> shipmentDataList, Iterable<CSVRecord> records, List<MongoOrder> orderList, List<Integer> orderIdForShipmentId) {
        int index = 0;
        for (CSVRecord record : records) {
            MongoShipment mongoShipment = new MongoShipment();
            mongoShipment.setTracingNumber(record.get("tracingNumber"));
            String shipmentDetails = record.get("shipmentDetails");
            if (shipmentDetails.length() > 255) {
                shipmentDetails = shipmentDetails.substring(0, 254);
            }
            mongoShipment.setShipmentDetails(shipmentDetails);
            mongoShipment.setOrder(orderList.get(orderIdForShipmentId.get(index)));
            shipmentDataList.add(mongoShipment);
            index++;
        }
        shipmentRepository.saveAll(shipmentDataList);
    }

    public void insertTransactionData(List<MongoTransaction> transactionDataList, Iterable<CSVRecord> records, List<MongoStore> storeList, List<DeliveryMode> deliveryModeList, List<Integer> storeIdForTransactionId, List<MongoProduct> productsList, List<List<Integer>> listOfProductIdsForTransactionId) {
        int index = 0;
        for (CSVRecord record : records) {
            MongoTransaction transaction = new MongoTransaction();
            transaction.setDate(new Date(Long.parseLong(record.get("date")) * 1000));
            transaction.setDeliveryMode(deliveryModeList.get(index));
            transaction.setStore(storeList.get(storeIdForTransactionId.get(index)));
            List<MongoProduct> products = new ArrayList<>();
            for (Integer productId : listOfProductIdsForTransactionId.get(index)) {
                products.add(productsList.get(productId));
            }
            transaction.setProducts(products);
            transactionDataList.add(transaction);
            index++;
        }
        transactionRepository.saveAll(transactionDataList);
    }

    public void insertProductsInStores(List<MongoStore> storeList, List<MongoProduct> productsList, List<List<Integer>> listOfProductIdsForStoreId, List<List<Long>> quantityOfProductsForId) {
        List<MongoProductsInStores> productsInStores = new ArrayList<>();
        for (int i = 0; i < storeList.size(); i++) {
            MongoStore storeEntity = storeList.get(i);
            for (int j = 0; j < listOfProductIdsForStoreId.get(i).size(); j++) {
                MongoProductsInStores data = new MongoProductsInStores();
                MongoProduct productEntity = productsList.get(listOfProductIdsForStoreId.get(i).get(j));
                data.setProduct(productEntity);
                data.setStore(storeEntity);
                Long quantity = quantityOfProductsForId.get(i).get(j);
                data.setQuantity(quantity);
                productsInStores.add(data);
            }
        }
        productsInStoresRepository.saveAll(productsInStores);
    }
}