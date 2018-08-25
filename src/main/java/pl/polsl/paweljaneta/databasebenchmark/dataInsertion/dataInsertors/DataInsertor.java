package pl.polsl.paweljaneta.databasebenchmark.dataInsertion.dataInsertors;

import org.apache.commons.csv.CSVRecord;

import java.util.List;

public interface DataInsertor {

    void insertClientAddressData(List<Object> clientAddressDataList, Iterable<CSVRecord> records);

    void insertStoreAddressData(List<Object> storeAddressDataList, Iterable<CSVRecord> records);

    void insertClientData(List<Object> clientDataList, Iterable<CSVRecord> records, List<Object> addressList);

    void insertStoreData(List<Object> storeDataList, Iterable<CSVRecord> records, List<Object> storeList);

    void insertDiscountData(List<Object> discountDataList, Iterable<CSVRecord> records);

    void insertProductData(List<Object> productDataList, Iterable<CSVRecord> records, List<Object> discountList,
                           List<Integer> discountIdForProductId);

    void insertCartData(List<Object> cartDataList, Iterable<CSVRecord> records, List<Object> clientList,
                        List<Integer> clientIdForCartId, List<Object> productsList,
                        List<List<Integer>> listOfProductIdsForCartId);

    void insertOrderData(List<Object> orderDataList, Iterable<CSVRecord> records, List<Object> clientList,
                         List<Integer> clientIdForOrderId, List<Object> productsList,
                         List<List<Integer>> listOfProductIdsForOrderId);

    void insertShipmentData(List<Object> shipmentDataList, Iterable<CSVRecord> records, List<Object> orderList,
                            List<Integer> orderIdForShipmentId);

    void insertTransactionData(List<Object> transactionDataList, Iterable<CSVRecord> records, List<Object> storeList,
                               List<Integer> storeIdForTransactionId, List<Object> productsList,
                               List<List<Integer>> listOfProductIdsForTransactionId);

    void insertProductsInStores(Iterable<CSVRecord> records, List<Object> storeList, List<Object> productsList,
                                List<List<Integer>> listOfProductIdsForStoreId);
}
