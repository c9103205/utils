
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName: StringUtil
 * @Description: 字符串工具類
 */
public class StringUtil {

    /**
     * 判斷是否為空
     *
     * @param str
     * @return
     */
    public static boolean isBlank(String str) {
        return str == null || "".equals(str);
    }

    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    /**
     * 去除空格
     *
     * @param str
     * @return
     */
    public static String trim(String str) {
        return (str == null ? "" : str.trim());
    }

    /**
     * 判斷一個字符串是不是數字,如果是就返回TRUE，如果不是就返回FALSE
     *
     * @param obj
     * @return boolean
     */
    public static boolean isNum(String obj) {
        boolean flag = true;
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNumber = pattern.matcher(obj);

        if (!isNumber.matches()) {
            flag = false;
        }
        return flag;
    }

    /**
     * 判斷性別是否為“男”或“女”，如果是就返回TRUE，如果不是就返回FALSE
     *
     * @param obj
     * @return boolean
     */
    public static boolean isSex(String obj) {
        boolean flag = true;
        Pattern pattern = Pattern.compile("[男女]{1}");
        Matcher isRightSex = pattern.matcher(obj);

        if (!isRightSex.matches()) {
            flag = false;
        }

        return flag;
    }

    /**
     * 字符串分割成整形數組
     *
     * @param str 字符串內容
     * @param reg 分隔符
     * @return
     */
    public static int[] strToIntArray(String str, String reg) {
        String[] ss = str.split(reg);
        int[] array = new int[ss.length];
        for (int i = 0; i < ss.length; i++) {
            if (!ss[i].equals("")) {
                array[i] = Integer.parseInt(ss[i].trim());
            }
        }
        return array;
    }


    /**
     * 字符串分割成字符串列表
     *
     * @param str 字符串內容
     * @param reg 分隔符
     * @return
     */
    public static List<String> strToStringList(String str, String reg) {
        String[] ss = str.split(reg);
        List<String> list = new ArrayList<>();
        for (String string : ss) {
            if (!string.equals("")) {
                list.add(string);
            }
        }
        return list;
    }

    /**
     * 字符串分割成長整形列表
     *
     * @param str 字符串內容
     * @param reg 分隔符
     * @return
     */
    public static List<Long> strToLongList(String str, String reg) {
        String[] ss = str.split(reg);
        List<Long> list = new ArrayList<>();
        for (String string : ss) {
            if (!string.equals("")) {
                list.add(Long.parseLong(string));
            }
        }
        return list;
    }

    public static String listToStr(List array, String reg) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.size(); i++) {
            sb.append(array.get(i));
            if (i < array.size() - 1) {
                sb.append(reg);
            }
        }

        return sb.toString();
    }

    public static String[] listToArray(List<String> list) {
        String[] array = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        return array;
    }

    public static String intArrayToStr(int[] array, String reg) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            sb.append(array[i]);
            if (i < array.length - 1) {
                sb.append(reg);
            }
        }
        return sb.toString();
    }

    public static String stringArrayToStr(String[] array, String reg) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            sb.append(array[i]);
            if (i < array.length - 1) {
                sb.append(reg);
            }
        }
        return sb.toString();
    }

    /**
     * 把數組所有元素排序，並按照“參數=參數值”的模式用“&”字符拼接成字符串
     *
     * @param params 需要排序並參與字符拼接的參數組
     * @return 拼接後字符串
     */
    public static String createLinkString(Map<String, String> params) {
        StringBuilder content = new StringBuilder();
        List<String> keys = new ArrayList<>(params.keySet());
        Collections.sort(keys);
        int index = 0;
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);
            if (StringUtil.isNotBlank(key) && StringUtil.isNotBlank(value)) {
                content.append((index == 0 ? "" : "&") + key + "=" + value);
                index++;
            }
        }
        return content.toString();
    }

    /**
     * 值為空時也會參與拼接
     * @param params
     * @return
     */
    public static String createLinkStringAllowEmptyVal(Map<String, String> params) {
        StringBuilder content = new StringBuilder();
        List<String> keys = new ArrayList<>(params.keySet());
        Collections.sort(keys);
        int index = 0;
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);
            if (StringUtil.isNotBlank(key)) {
                content.append((index == 0 ? "" : "&") + key + "=" + value);
                index++;
            }
        }
        return content.toString();
    }

    /**
     * 把數組所有元素排序，並按照“參數=參數值”的模式用“&”字符拼接成字符串
     *
     * @param params 需要排序並參與字符拼接的參數組
     * @return 拼接後字符串
     */
    public static String createLinkObject(Map<String, Object> params) {
        StringBuilder content = new StringBuilder();
        List<String> keys = new ArrayList<>(params.keySet());
        Collections.sort(keys);
        int index = 0;
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = null == params.get(key) ? "" : params.get(key).toString();
            if (StringUtil.isNotBlank(key) && StringUtil.isNotBlank(value)) {
                content.append((index == 0 ? "" : "&") + key + "=" + value);
                index++;
            }
        }
        return content.toString();
    }

    /**
     * 值為空時也會參與拼接
     * @param params
     * @return
     */
    public static String createLinkObjectAllowEmptyVal(Map<String, Object> params) {
        StringBuilder content = new StringBuilder();
        List<String> keys = new ArrayList<>(params.keySet());
        Collections.sort(keys);
        int index = 0;
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = null == params.get(key) ? "" : params.get(key).toString();
            if (StringUtil.isNotBlank(key)) {
                content.append((index == 0 ? "" : "&") + key + "=" + value);
                index++;
            }
        }
        return content.toString();
    }

    /**
     * 從set,get方法獲取屬性名
     *
     * @param methodName
     * @return
     */
    public static String getFieldName(String methodName) {
        if (methodName == null) {
            return null;
        }
        return methodName.substring(3, 4).toLowerCase() + methodName.substring(4);
    }

    /**
     * 是否存在目標字符串
     *
     * @param target 目標字符串
     * @param strs   源字符串
     * @return 有存在則返回真，不存在則為假
     */
    public static boolean equals(String target, String... strs) {
        for (String str : strs) {
            if (target.equals(str)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 利用正則表達式判斷字符串是否是數字
     *
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        for (int i = str.length(); --i >= 0; ) {
            int chr = str.charAt(i);
            if (chr < 48 || chr > 57)
                return false;
        }
        return true;
    }


    //最長公共子序列方法計算字符串相似度
    public static double assemblePercent(String v1, String v2) {
        if (isBlank(v1) || isBlank(v2)) {
            return 0d;
        }
        char[] c1 = v1.toCharArray();
        char[] c2 = v2.toCharArray();
        int[][] dp = new int[c1.length + 1][c2.length + 1];
        //初始化dp
        for (int i = 0; i < c1.length; i++) {
            dp[i][0] = 0;
        }
        for (int j = 0; j < c2.length; j++) {
            dp[0][j] = 0;
        }

        for (int i = 1; i <= c1.length; i++) {
            for (int j = 1; j <= c2.length; j++) {
                if (c1[i - 1] == c2[j - 1])
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                else
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
            }
        }
        int maxLength = Math.max(c1.length, c2.length);
        return dp[c1.length][c2.length] * 1.0 / maxLength;
    }

    public static boolean toBoolean(String str) {
        boolean returnValue = false;
        if ("1".equalsIgnoreCase(str) || "yes".equalsIgnoreCase(str) || "true".equalsIgnoreCase(str) || "t".equalsIgnoreCase(str) || "on".equalsIgnoreCase(str)) {
            returnValue = true;
        }

        return returnValue;
    }

    //首字母大寫
    public static String fisrtCharCapture(String name) {
        char[] cs = name.toCharArray();
        cs[0] -= 32;
        return String.valueOf(cs);
    }

    public static String getDef(String str, String def) {
        return isNotBlank(str) ? str : def;
    }

    /**
     * 名稱省略，如：test-》te***st
     * @param str
     * @return
     */
    public static String abbreviateMiddle(String str) {
        if (isBlank(str) || str.length() <= 2) {
            return str;
        }
        return str.substring(0, 2) + "***" + str.substring(Math.max(str.length() - 2,2));
    }

    public static String print(String format, Object... arguments) {
        String temp = format;
        String source = "\\{\\}";

        Pattern p = Pattern.compile(source);
        Matcher m = p.matcher(format);

        int i = 0; // arguments
        while (m.find() && i < arguments.length) {
            temp = temp.replaceFirst(source, String.valueOf(arguments[i++]));
        }

        return temp;
    }
}
