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
}
