package pl.polsl.paweljaneta.databasebenchmark.dataInsertion.dataInsertors;

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
            address.setEntityId(Long.parseLong(record.get("entityId")));
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
            address.setEntityId(Long.parseLong(record.get("entityId")));
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
            client.setAddressId(addressList.get(addressIndexes.get(index)).getId());
            client.setEntityId(Long.parseLong(record.get("entityId")));
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
            store.setEntityId(Long.parseLong(record.get("entityId")));
            storeDataList.add(store);
            index++;
        }
        storeRepository.saveAll(storeDataList);
    }

    public void insertDiscountData(List<MongoDiscount> discountDataList, Iterable<CSVRecord> records) {
        for (CSVRecord record : records) {
            MongoDiscount discount = new MongoDiscount();
            discount.setDiscountValue(Float.parseFloat(record.get("discountValue").replace(',', '.')));
            discount.setEntityId(Long.parseLong(record.get("entityId")));
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
            product.setDiscountId(discountList.get(discountIdForProductId.get(index)).getId());
            product.setEntityId(Long.parseLong(record.get("entityId")));
            productDataList.add(product);
            index++;
        }
        productRepository.saveAll(productDataList);
    }

    public void insertCartData(List<MongoCart> cartDataList, List<MongoClient> clientList, List<Integer> clientIdForCartId, List<MongoProduct> productsList, List<List<Integer>> listOfProductIdsForCartId, List<Long> entityIds) {
        int index = 0;
        for (List<Integer> productIds : listOfProductIdsForCartId) {
            MongoCart cart = new MongoCart();
            cart.setClient(clientList.get(clientIdForCartId.get(index)));
            List<MongoProduct> products = new ArrayList<>();
            for (Integer productIndex : productIds) {
                products.add(productsList.get(productIndex));
            }
            cart.setProducts(products);
            cart.setEntityId(entityIds.get(index));
            cartDataList.add(cart);
            index++;
        }
        cartRepository.saveAll(cartDataList);
    }

    public void insertOrderData(List<MongoOrder> orderDataList, List<MongoClient> clientList, List<Integer> clientIdForOrderId, List<MongoProduct> productsList, List<List<Integer>> listOfProductIdsForOrderId, List<Long> entityIds) {
        int index = 0;
        for (List<Integer> productIds : listOfProductIdsForOrderId) {
            MongoOrder order = new MongoOrder();
            order.setClient(clientList.get(clientIdForOrderId.get(index)));
            List<MongoProduct> products = new ArrayList<>();
            for (Integer productIndex : productIds) {
                products.add(productsList.get(productIndex));
            }
            order.setProducts(products);
            order.setEntityId(entityIds.get(index));
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
            mongoShipment.setEntityId(Long.parseLong(record.get("entityId")));
            shipmentDataList.add(mongoShipment);
            index++;
        }
        shipmentRepository.saveAll(shipmentDataList);
    }

    public void insertTransactionData(List<MongoTransaction> transactionDataList, Iterable<CSVRecord> records, List<MongoStore> storeList, List<DeliveryMode> deliveryModeList, List<Integer> storeIdForTransactionId, List<MongoProduct> productsList, List<List<Integer>> listOfProductIdsForTransactionId, List<MongoClient> clientList, List<Integer> listOfClientIds) {
        int index = 0;
        for (CSVRecord record : records) {
            MongoTransaction transaction = new MongoTransaction();
            transaction.setDate(new Date(Long.parseLong(record.get("date")) * 1000));
            transaction.setDeliveryMode(deliveryModeList.get(index));
            transaction.setStoreId(storeList.get(storeIdForTransactionId.get(index)).getId());
            transaction.setClientId(clientList.get(listOfClientIds.get(index)).getId());
            List<MongoProduct> products = new ArrayList<>();
            for (Integer productId : listOfProductIdsForTransactionId.get(index)) {
                products.add(productsList.get(productId));
            }
            transaction.setProducts(products);
            transaction.setEntityId(Long.parseLong(record.get("entityId")));
            transactionDataList.add(transaction);
            index++;
        }
        transactionRepository.saveAll(transactionDataList);
    }

    public void insertProductsInStores(List<MongoStore> storeList, List<MongoProduct> productsList, List<List<Integer>> listOfProductIdsForStoreId, List<List<Long>> quantityOfProductsForId, List<Long> entityIds) {
        List<MongoProductsInStores> productsInStores = new ArrayList<>();
        int index = 0;
        for (int i = 0; i < storeList.size(); i++) {
            MongoStore storeEntity = storeList.get(i);
            for (int j = 0; j < listOfProductIdsForStoreId.get(i).size(); j++) {
                MongoProductsInStores data = new MongoProductsInStores();
                MongoProduct productEntity = productsList.get(listOfProductIdsForStoreId.get(i).get(j));
                data.setProductId(productEntity.getId());
                data.setStoreId(storeEntity.getId());
                Long quantity = quantityOfProductsForId.get(i).get(j);
                data.setQuantity(quantity);
                data.setEntityId(entityIds.get(index));
                productsInStores.add(data);
                index++;
            }
        }
        productsInStoresRepository.saveAll(productsInStores);
    }
}
