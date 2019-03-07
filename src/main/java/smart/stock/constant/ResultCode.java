package smart.stock.constant;

/**
 * @Auther: sunjx
 * @Date: 2019/3/7 0007 16:16
 * @Description:
 */
public class ResultCode {

    public enum SYS{
        SUCCESS("0000","成功"),
        FAIL("0001","失败"),
        ;
        private String code;
        private String text;

        SYS(String code, String text) {
            this.code = code;
            this.text = text;
        }

        public String getCode() {
            return code;
        }

        public String getText() {
            return text;
        }

        public static String getTextByCode(String code){
            for (ResultCode.SYS type :ResultCode.SYS.values()){
                if(type.code == code){
                    return type.text;
                }
            }
            return null;
        }
    }
}
