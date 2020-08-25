package ca.bc.gov.open.bambora.payment.starter;

public class BamboraConstants {
    public static final String PARAM_PPRDIR_SERVICE_VERSION = "serviceVersion";
    public static final String PARAM_PPRDIR_MERCHANT_ID = "merchantId";
    public static final String PARAM_PPRDIR_LANGUAGE = "trnLanguage";
    public static final String PARAM_PPRDIR_OPERATION_TYPE = "operationType";
    public static final String PARAM_PPRDIR_RETURN_URL = "trnReturnURL";
    public static final String PARAM_PPRDIR_ORDER_NUMBER = "trnOrderNumber";
    public static final String PARAM_PPRDIR_CUSTOMER_CODE = "customerCode";
    public static final String PARAM_PPRDIR_REF1 = "ref1";
    public static final String LANGUAGE_TYPE = "eng";
    public static final String PARAM_TRANS_HASH_VALUE = "hashValue";
    public static final String PARAM_TRANS_HASH_EXPIRY = "hashExpiry";
    public static final String PARAM_TRANS_HASH_EXPIRY_FORMAT = "yyyyMMddkkmm";


    public enum OperationTypes {
        N, M
    }


}
