package pl.polsl.paweljaneta.databasebenchmark.dataInsertion.dataInsertors.impl;

import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.polsl.paweljaneta.databasebenchmark.model.DeliveryMode;
import pl.polsl.paweljaneta.databasebenchmark.model.sql.entities.*;
import pl.polsl.paweljaneta.databasebenchmark.model.sql.repository.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Component
public class SqlDataInsertor {

    @Autowired
    private SqlAddressRepository addressRepository;
    @Autowired
    private SqlClientRepository clientRepository;
    @Autowired
    private SqlStoreRepository storeRepository;
    @Autowired
    private SqlDiscountRepository discountRepository;
    @Autowired
    private SqlProductRepository productRepository;
    @Autowired
    private SqlCartRepository cartRepository;
    @Autowired
    private SqlOrderRepository orderRepository;
    @Autowired
    private SqlShipmentRepository shipmentRepository;
    @Autowired
    private SqlTransactionRepository transactionRepository;
    @Autowired
    private SqlProductsInStoresRepository productsInStoresRepository;

    public void insertClientAddressData(List<SqlAddress> clientAddressDataList, Iterable<CSVRecord> records) {
        for (CSVRecord record : records) {
            SqlAddress address = new SqlAddress();
            address.setCity(record.get("city"));
            address.setPostalCode(record.get("postalCode"));
            address.setStreet(record.get("street"));
            address.setStreetNumber(record.get("streetNumber"));
            clientAddressDataList.add(address);
        }
        addressRepository.saveAll(clientAddressDataList);
    }

    public void insertStoreAddressData(List<SqlAddress> storeAddressDataList, Iterable<CSVRecord> records) {
        for (CSVRecord record : records) {
            SqlAddress address = new SqlAddress();
            address.setCity(record.get("city"));
            address.setPostalCode(record.get("postalCode"));
            address.setStreet(record.get("street"));
            address.setStreetNumber(record.get("streetNumber"));
            storeAddressDataList.add(address);
        }
        addressRepository.saveAll(storeAddressDataList);
    }

    public void insertClientData(List<SqlClient> clientDataList, Iterable<CSVRecord> records, List<SqlAddress> addressList,List<Integer> addressIndexes) {
        int index = 0;
        for (CSVRecord record : records) {
            SqlClient client = new SqlClient();
            client.setName(record.get("name"));
            client.setPhoneNumber(record.get("phoneNumber"));
            client.setEmail(record.get("email"));
            client.setAddress(addressList.get(addressIndexes.get(index)));
            clientDataList.add(client);
            index++;
        }
        clientRepository.saveAll(clientDataList);
    }

    public void insertStoreData(List<SqlStore> storeDataList, Iterable<CSVRecord> records, List<SqlAddress> addressList) {
        int index = 0;
        for (CSVRecord record : records) {
            SqlStore store = new SqlStore();
            store.setName(record.get("name"));
            store.setAddress(addressList.get(index));
            storeDataList.add(store);
            index++;
        }
        storeRepository.saveAll(storeDataList);
    }

    public void insertDiscountData(List<SqlDiscount> discountDataList, Iterable<CSVRecord> records) {
        for (CSVRecord record : records) {
            SqlDiscount discount = new SqlDiscount();
            discount.setDiscountValue(Float.parseFloat(record.get("discountValue")));
            discountDataList.add(discount);
        }
        discountRepository.saveAll(discountDataList);
    }

    public void insertProductData(List<SqlProduct> productDataList, Iterable<CSVRecord> records, List<SqlDiscount> discountList, List<Integer> discountIdForProductId) {
        int index = 0;
        for (CSVRecord record : records) {
            SqlProduct product = new SqlProduct();
            product.setName(record.get("name"));
            product.setPrice(Float.parseFloat(record.get("price")));
            product.setDiscount(discountList.get(discountIdForProductId.get(index)));
            productDataList.add(product);
            index++;
        }
        productRepository.saveAll(productDataList);
    }

    public void insertCartData(List<SqlCart> cartDataList, List<SqlClient> clientList, List<Integer> clientIdForCartId, List<SqlProduct> productsList, List<List<Integer>> listOfProductIdsForCartId) {
        int index = 0;
        for (List<Integer> productIds : listOfProductIdsForCartId) {
            SqlCart cart = new SqlCart();
            cart.setClient(clientList.get(clientIdForCartId.get(index)));
            List<SqlProduct> products = new ArrayList<>();
            for (Integer productIndex : productIds) {
                products.add(productsList.get(productIndex));
            }
            cart.setProducts(products);
            cartDataList.add(cart);
            index++;
        }
        cartRepository.saveAll(cartDataList);
    }

    public void insertOrderData(List<SqlOrder> orderDataList, List<SqlClient> clientList, List<Integer> clientIdForOrderId, List<SqlProduct> productsList, List<List<Integer>> listOfProductIdsForOrderId) {
        int index = 0;
        for (List<Integer> productIds : listOfProductIdsForOrderId) {
            SqlOrder order = new SqlOrder();
            order.setClient(clientList.get(clientIdForOrderId.get(index)));
            List<SqlProduct> products = new ArrayList<>();
            for (Integer productIndex : productIds) {
                products.add(productsList.get(productIndex));
            }
            order.setProducts(products);
            orderDataList.add(order);
            index++;
        }
        orderRepository.saveAll(orderDataList);
    }

    public void insertShipmentData(List<SqlShipment> shipmentDataList, Iterable<CSVRecord> records, List<SqlOrder> orderList, List<Integer> orderIdForShipmentId) {
        int index = 0;
        for (CSVRecord record : records) {
            SqlShipment sqlShipment = new SqlShipment();
            sqlShipment.setTracingNumber(record.get("tracingNumber"));
            sqlShipment.setShipmentDetails(record.get("shipmentDetails"));
            sqlShipment.setOrder(orderList.get(orderIdForShipmentId.get(index)));
            shipmentDataList.add(sqlShipment);
            index++;
        }
        shipmentRepository.saveAll(shipmentDataList);
    }

    public void insertTransactionData(List<SqlTransaction> transactionDataList, Iterable<CSVRecord> records, List<SqlStore> storeList, List<DeliveryMode> deliveryModeList, List<Integer> storeIdForTransactionId, List<SqlProduct> productsList, List<List<Integer>> listOfProductIdsForTransactionId) {
        int index = 0;
        for (CSVRecord record : records) {
            SqlTransaction transaction = new SqlTransaction();
            transaction.setDate(Date.valueOf(record.get("date")));
            transaction.setDeliveryMode(deliveryModeList.get(index));
            transaction.setStore(storeList.get(storeIdForTransactionId.get(index)));
            List<SqlProduct> products = new ArrayList<>();
            for (Integer productId : listOfProductIdsForTransactionId.get(index)) {
                products.add(productsList.get(productId));
            }
            transaction.setProducts(products);
            transactionDataList.add(transaction);
            index++;
        }
        transactionRepository.saveAll(transactionDataList);
    }

    public void insertProductsInStores(List<SqlStore> storeList, List<Integer> storeIds, List<SqlProduct> productsList, List<List<Integer>> listOfProductIdsForStoreId,List<List<Long>> quantityOfProductsForId) {
        List<SqlProductsInStores> productsInStores = new ArrayList<>();
       /* int index=0;
        for (Integer storeId : storeIds) {
            Long storeEntityId = storeList.get(storeId).getId();
            for (Integer productIndex : listOfProductIdsForStoreId.get(index)) {
                SqlProductsInStores data = new SqlProductsInStores();
                data.setProductId(productsList.get(productIndex).getProductId());
                data.setStoreId(storeEntityId);
                data.setQuantity();
            }
            index++;
        }*/
       for(int i=0;i<storeIds.size();i++){
           Long storeEntityId = storeList.get(i).getId();
           for(int j=0;j<listOfProductIdsForStoreId.get(i).size();j++){
               SqlProductsInStores data = new SqlProductsInStores();
               Long productEntityId = productsList.get(listOfProductIdsForStoreId.get(i).get(j)).getProductId();
               data.setProductId(productEntityId);
               data.setStoreId(storeEntityId);
               Long quantity = quantityOfProductsForId.get(i).get(j);
               data.setQuantity(quantity);
               productsInStores.add(data);
           }
       }
       productsInStoresRepository.saveAll(productsInStores);
    }
}
