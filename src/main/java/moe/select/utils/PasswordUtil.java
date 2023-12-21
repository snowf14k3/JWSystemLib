package moe.select.utils;

public class PasswordUtil {

    /**
     * 通过逆向网页js
     * @param dataStr 服务端获取的加密密钥
     * @param code 账号%%%密码
     * @return encoded 的数据
     */
    public static String encodeUserInfo(String dataStr, String code) {
        StringBuilder encoded = new StringBuilder();
        String scode = dataStr.split("#")[0];
        String sxh = dataStr.split("#")[1];

        for (int i = 0; i < code.length(); i++) {
            if (i < 20) {
                int end = Integer.parseInt(sxh.substring(i, i + 1));
                encoded.append(code.charAt(i)).append(scode, 0, end);
                scode = scode.substring(end);
            } else {
                encoded.append(code.substring(i));
                i = code.length();
            }
        }
        return encoded.toString();
    }


}
