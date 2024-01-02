package moe.snowflake.jwSystem.utils;

public class PasswordUtil {

    /**
     * 通过逆向网页js,用于教务处方式进入的教务系统登录.
     * <p>
     * 新版的教务系统都用这个，但是可以用它更底层的登录方式进行登录
     * @param dataStr 服务端获取的加密密钥
     * @param code 账号%%%密码
     * @return encoded 的数据
     */
    @Deprecated
    public static String encodeUserData(String dataStr, String code) {
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
