package pl.polsl.paweljaneta.databasebenchmark.dataInsertion.dataInsertors;

import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.polsl.paweljaneta.databasebenchmark.model.DeliveryMode;
import pl.polsl.paweljaneta.databasebenchmark.model.neo4j.entities.*;
import pl.polsl.paweljaneta.databasebenchmark.model.neo4j.repository.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class NeoDataInsertor {

    @Autowired
    private NeoAddressRepository addressRepository;
    @Autowired
    private NeoClientRepository clientRepository;
    @Autowired
    private NeoStoreRepository storeRepository;
    @Autowired
    private NeoDiscountRepository discountRepository;
    @Autowired
    private NeoProductRepository productRepository;
    @Autowired
    private NeoCartRepository cartRepository;
    @Autowired
    private NeoOrderRepository orderRepository;
    @Autowired
    private NeoShipmentRepository shipmentRepository;
    @Autowired
    private NeoTransactionRepository transactionRepository;
    @Autowired
    private NeoProductsInStoresRepository productsInStoresRepository;

    public void insertClientAddressData(List<NeoAddress> clientAddressDataList, Iterable<CSVRecord> records) {
        for (CSVRecord record : records) {
            NeoAddress address = new NeoAddress();
            address.setCity(record.get("city"));
            address.setPostalCode(record.get("postalCode"));
            address.setStreet(record.get("street"));
            address.setStreetNumber(record.get("streetNumber"));
            address.setEntityId(Long.parseLong(record.get("entityId")));
            clientAddressDataList.add(address);
        }
        addressRepository.saveAll(clientAddressDataList);
    }

    public void insertStoreAddressData(List<NeoAddress> storeAddressDataList, Iterable<CSVRecord> records) {
        for (CSVRecord record : records) {
            NeoAddress address = new NeoAddress();
            address.setCity(record.get("city"));
            address.setPostalCode(record.get("postalCode"));
            address.setStreet(record.get("street"));
            address.setStreetNumber(record.get("streetNumber"));
            address.setEntityId(Long.parseLong(record.get("entityId")));
            storeAddressDataList.add(address);
        }
        addressRepository.saveAll(storeAddressDataList);
    }

    public void insertClientData(List<NeoClient> clientDataList, Iterable<CSVRecord> records,
                                 List<NeoAddress> addressList, List<Integer> addressIndexes) {
        int index = 0;
        for (CSVRecord record : records) {
            NeoClient client = new NeoClient();
            client.setName(record.get("name"));
            client.setPhoneNumber(record.get("phoneNumber"));
            client.setEmail(record.get("email"));
            client.setAddress(addressList.get(addressIndexes.get(index)));
            client.setEntityId(Long.parseLong(record.get("entityId")));
            clientDataList.add(client);
            index++;
        }
        clientRepository.saveAll(clientDataList);
    }

    public void insertStoreData(List<NeoStore> storeDataList, Iterable<CSVRecord> records,
                                List<NeoAddress> addressList, List<Integer> addressIndexes) {
        int index = 0;
        for (CSVRecord record : records) {
            NeoStore store = new NeoStore();
            store.setName(record.get("name"));
            store.setAddress(addressList.get(addressIndexes.get(index)));
            store.setEntityId(Long.parseLong(record.get("entityId")));
            storeDataList.add(store);
            index++;
        }
        storeRepository.saveAll(storeDataList);
    }

    public void insertDiscountData(List<NeoDiscount> discountDataList, Iterable<CSVRecord> records) {
        for (CSVRecord record : records) {
            NeoDiscount discount = new NeoDiscount();
            discount.setDiscountValue(Float.parseFloat(record.get("discountValue").replace(',', '.')));
            discount.setEntityId(Long.parseLong(record.get("entityId")));
            discountDataList.add(discount);
        }
        discountRepository.saveAll(discountDataList);
    }

    public void insertProductData(List<NeoProduct> productDataList, Iterable<CSVRecord> records, List<NeoDiscount> discountList, List<Integer> discountIdForProductId) {
        int index = 0;
        for (CSVRecord record : records) {
            NeoProduct product = new NeoProduct();
            product.setName(record.get("name"));
            product.setPrice(Float.parseFloat(record.get("price").replace(',', '.')));
            product.setDiscount(discountList.get(discountIdForProductId.get(index)));
            product.setEntityId(Long.parseLong(record.get("entityId")));
            productDataList.add(product);
            index++;
        }
        productRepository.saveAll(productDataList);
    }

    public void insertCartData(List<NeoCart> cartDataList, List<NeoClient> clientList, List<Integer> clientIdForCartId, List<NeoProduct> productsList, List<List<Integer>> listOfProductIdsForCartId, List<Long> entityIds) {
        int index = 0;
        for (List<Integer> productIds : listOfProductIdsForCartId) {
            NeoCart cart = new NeoCart();
            cart.setClient(clientList.get(clientIdForCartId.get(index)));
            List<NeoProduct> products = new ArrayList<>();
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

    public void insertOrderData(List<NeoOrder> orderDataList, List<NeoClient> clientList, List<Integer> clientIdForOrderId, List<NeoProduct> productsList, List<List<Integer>> listOfProductIdsForOrderId, List<Long> entityIds) {
        int index = 0;
        for (List<Integer> productIds : listOfProductIdsForOrderId) {
            NeoOrder order = new NeoOrder();
            order.setClient(clientList.get(clientIdForOrderId.get(index)));
            List<NeoProduct> products = new ArrayList<>();
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

    public void insertShipmentData(List<NeoShipment> shipmentDataList, Iterable<CSVRecord> records, List<NeoOrder> orderList, List<Integer> orderIdForShipmentId) {
        int index = 0;
        for (CSVRecord record : records) {
            NeoShipment neoShipment = new NeoShipment();
            neoShipment.setTracingNumber(record.get("tracingNumber"));
            String shipmentDetails = record.get("shipmentDetails");
            if (shipmentDetails.length() > 255) {
                shipmentDetails = shipmentDetails.substring(0, 254);
            }
            neoShipment.setShipmentDetails(shipmentDetails);
            neoShipment.setOrder(orderList.get(orderIdForShipmentId.get(index)));
            neoShipment.setEntityId(Long.parseLong(record.get("entityId")));
            shipmentDataList.add(neoShipment);
            index++;
        }
        shipmentRepository.saveAll(shipmentDataList);
    }

    public void insertTransactionData(List<NeoTransaction> transactionDataList, Iterable<CSVRecord> records, List<NeoStore> storeList, List<DeliveryMode> deliveryModeList, List<Integer> storeIdForTransactionId, List<NeoProduct> productsList, List<List<Integer>> listOfProductIdsForTransactionId) {
        int index = 0;
        for (CSVRecord record : records) {
            NeoTransaction transaction = new NeoTransaction();
            transaction.setDate(new Date(Long.parseLong(record.get("date")) * 1000));
            transaction.setDeliveryMode(deliveryModeList.get(index));
            transaction.setStore(storeList.get(storeIdForTransactionId.get(index)));
            List<NeoProduct> products = new ArrayList<>();
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

    public void insertProductsInStores(List<NeoStore> storeList, List<NeoProduct> productsList, List<List<Integer>> listOfProductIdsForStoreId, List<List<Long>> quantityOfProductsForId, List<Long> entityIds) {
        List<NeoProductsInStores> productsInStores = new ArrayList<>();
        int index = 0;
        for (int i = 0; i < storeList.size(); i++) {
            NeoStore storeEntity = storeList.get(i);
            for (int j = 0; j < listOfProductIdsForStoreId.get(i).size(); j++) {
                NeoProductsInStores data = new NeoProductsInStores();
                NeoProduct productEntity = productsList.get(listOfProductIdsForStoreId.get(i).get(j));
                data.setProduct(productEntity);
                data.setStore(storeEntity);
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
