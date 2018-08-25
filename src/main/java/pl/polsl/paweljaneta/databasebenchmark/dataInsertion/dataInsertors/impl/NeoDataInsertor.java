package pl.polsl.paweljaneta.databasebenchmark.dataInsertion.dataInsertors.impl;

import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;
import pl.polsl.paweljaneta.databasebenchmark.dataInsertion.dataInsertors.DataInsertor;

import java.util.List;

@Component
public class NeoDataInsertor implements DataInsertor {
    @Override
    public void insertClientAddressData(List<Object> clientAddressDataList, Iterable<CSVRecord> records) {

    }

    @Override
    public void insertStoreAddressData(List<Object> storeAddressDataList, Iterable<CSVRecord> records) {

    }

    @Override
    public void insertClientData(List<Object> clientDataList, Iterable<CSVRecord> records, List<Object> addressList) {

    }

    @Override
    public void insertStoreData(List<Object> storeDataList, Iterable<CSVRecord> records, List<Object> storeList) {

    }

    @Override
    public void insertDiscountData(List<Object> discountDataList, Iterable<CSVRecord> records) {

    }

    @Override
    public void insertProductData(List<Object> productDataList, Iterable<CSVRecord> records, List<Object> discountList, List<Integer> discountIdForProductId) {

    }

    @Override
    public void insertCartData(List<Object> cartDataList, Iterable<CSVRecord> records, List<Object> clientList, List<Integer> clientIdForCartId, List<Object> productsList, List<List<Integer>> listOfProductIdsForCartId) {

    }

    @Override
    public void insertOrderData(List<Object> orderDataList, Iterable<CSVRecord> records, List<Object> clientList, List<Integer> clientIdForOrderId, List<Object> productsList, List<List<Integer>> listOfProductIdsForOrderId) {

    }

    @Override
    public void insertShipmentData(List<Object> shipmentDataList, Iterable<CSVRecord> records, List<Object> orderList, List<Integer> orderIdForShipmentId) {

    }

    @Override
    public void insertTransactionData(List<Object> transactionDataList, Iterable<CSVRecord> records, List<Object> storeList, List<Integer> storeIdForTransactionId, List<Object> productsList, List<List<Integer>> listOfProductIdsForTransactionId) {

    }

    @Override
    public void insertProductsInStores(Iterable<CSVRecord> records, List<Object> storeList, List<Object> productsList, List<List<Integer>> listOfProductIdsForStoreId) {

    }
}
