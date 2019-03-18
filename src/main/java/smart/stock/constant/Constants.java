package smart.stock.constant;

/**
 * @Auther: sunjx
 * @Date: 2019/3/7 0007 16:08
 * @Description:
 */
public class Constants {

    /**
     * 股票市场
     */
    public enum MarketType{
        SH(6,"上海交易所"),
        SZ(0,"深圳交易所"),
        ;
        private int key;
        private String text;

        MarketType(int key, String text) {
            this.key = key;
            this.text = text;
        }

        public int getKey() {
            return key;
        }

        public String getText() {
            return text;
        }

        public static String getTextByKey(int key){
            for (MarketType type :MarketType.values()){
                if(type.key == key){
                    return type.text;
                }
            }
            return null;
        }
    }

    /**
     * 信托人状态
     */
    public enum TrusteeStatus{
        Enabled(0,"启用"),
        Disabled(1,"不启用"),
        ;
        private int key;
        private String text;

        TrusteeStatus(int key, String text) {
            this.key = key;
            this.text = text;
        }

        public int getKey() {
            return key;
        }

        public String getText() {
            return text;
        }

        public static String getTextByKey(int key){
            for (TrusteeStatus type :TrusteeStatus.values()){
                if(type.key == key){
                    return type.text;
                }
            }
            return null;
        }
    }

    /**
     * 股票交易类型
     */
    public enum StockTradeTypes{
        Buy(0,"买入"),
        Sale(1,"卖出"),
        ;
        private int key;
        private String text;

        StockTradeTypes(int key, String text) {
            this.key = key;
            this.text = text;
        }

        public int getKey() {
            return key;
        }

        public String getText() {
            return text;
        }

        public static String getTextByKey(int key){
            for (StockTradeTypes type :StockTradeTypes.values()){
                if(type.key == key){
                    return type.text;
                }
            }
            return null;
        }
    }

    /**
     * 信托年化利率
     */
    public enum InterestRate{
        Five(5,"5% / 年"),
        ;
        private int key;
        private String text;

        InterestRate(int key, String text) {
            this.key = key;
            this.text = text;
        }

        public int getKey() {
            return key;
        }

        public String getText() {
            return text;
        }

        public static String getTextByKey(int key){
            for (InterestRate type :InterestRate.values()){
                if(type.key == key){
                    return type.text;
                }
            }
            return null;
        }
    }

    /**
     * 信托交易状态
     */
    public enum TrusteeTradeStatus{
        Cancel(-1,"取消"),
        Confirming(0,"确认中"),
        Confirmed(1,"已确认"),
        Sold(2,"已赎回"),
        ;
        private int key;
        private String text;

        TrusteeTradeStatus(int key, String text) {
            this.key = key;
            this.text = text;
        }

        public int getKey() {
            return key;
        }

        public String getText() {
            return text;
        }

        public static String getTextByKey(int key){
            for (TrusteeTradeStatus type :TrusteeTradeStatus.values()){
                if(type.key == key){
                    return type.text;
                }
            }
            return null;
        }
    }

    /**
     * 基金持有股票状态
     */
    public enum FundStockStatus{
        Holding(0,"持有"),
        UnHolding(1,"不持有"),
        ;
        private int key;
        private String text;

        public int getKey() {
            return key;
        }

        public String getText() {
            return text;
        }

        FundStockStatus(int key, String text) {
            this.key = key;
            this.text = text;
        }

        public static String getTextByKey(int key){
            for (FundStockStatus type :FundStockStatus.values()){
                if(type.key == key){
                    return type.text;
                }
            }
            return null;
        }
    }

    /**
     * 爬虫获取财务报表时期类型
     */
    public enum FinanceDateTypes{
        //第一季日期后缀
        ONE_QUARTER_DATE_SUFFIX(1, ".03.15"),

        //半年日期后缀
        HALF_YEAR_DATE_SUFFIX(2, ".06.30"),

        //前三季日期后缀
        THREE_QUARTER_DATE_SUFFIX(3, ".09.30"),

        //年报日期后缀
        FULL_YEAR_DATE_SUFFIX(4, ".12.31")
        ;
        private int key;
        private String text;

        public int getKey() {
            return key;
        }

        public String getText() {
            return text;
        }

        FinanceDateTypes(int key, String text) {
            this.key = key;
            this.text = text;
        }

        public static String getTextByKey(int key){
            for (FinanceDateTypes type :FinanceDateTypes.values()){
                if(type.key == key){
                    return type.text;
                }
            }
            return null;
        }

        public static int getKeyByText(String text){
            for (FinanceDateTypes type :FinanceDateTypes.values()){
                if(type.text.equals(text)){
                    return type.key;
                }
            }
            return -1;
        }
    }

    /**
     * 财务报表数据类型
     */
    public enum FinanceInfoTypes{
        ZXCWZB ("ZXCWZB", "最新财务指标"),
        CWBL ("CWBL", "财务比率"),
        CFZ ("CFZ", "资产负债"),
        LR ("LR", "利润表"),
        XJLL ("XJLL", "现金流量"),
        ZYSRFB ("ZYSRFB", "主营收入"),
        ZCJZ ("ZCJZ", "资产减值"),
        YSZK ("YSZK", "应收账款"),
        QTYSZK ("QTYSZK", "其他应收账款"),
        ;
        private String key;
        private String text;

        public String getKey() {
            return key;
        }

        public String getText() {
            return text;
        }

        FinanceInfoTypes(String key, String text) {
            this.key = key;
            this.text = text;
        }
    }

}
