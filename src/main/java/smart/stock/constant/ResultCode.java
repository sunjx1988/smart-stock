package smart.stock.constant;

/**
 * @Auther: sunjx
 * @Date: 2019/3/7 0007 16:16
 * @Description:
 */
public class ResultCode {

    public enum SYS{
        SUCCESS("0000","成功"),
        FAIL("0001","未知错误,请联系管理员"),
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

    public enum LOGIN{
        EMPTY_PHONE("0101", "电话不能为空"),
        EMPTY_PWD("0102", "密码不能为空"),
        DISABLED("0103", "账号被禁用"),
        NOT_EXISTS("0104", "用户不存在"),
        ERROR_PWD("0105", "用户密码错误")
        ;
        private String code;
        private String text;

        LOGIN(String code, String text) {
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
            for (ResultCode.LOGIN type :ResultCode.LOGIN.values()){
                if(type.code == code){
                    return type.text;
                }
            }
            return null;
        }
    }
}
