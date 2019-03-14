package smart.stock.constant;

/**
 * @Auther: sunjx
 * @Date: 2019/3/7 0007 16:08
 * @Description:
 */
public class Constants {

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
}
