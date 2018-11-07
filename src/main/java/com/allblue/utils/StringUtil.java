package com.allblue.utils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Description: 字符串处理工具类 All Rights Reserved.
 *
 * @version 1.0 2014-6-30 上午11:37:46
 */
public final class StringUtil extends StringUtils {

    /**
     * Creates a new instance of StringUtil.
     */
    private StringUtil() {
    }

    /**
     * Description: 判断字符串是否为null或空串
     *
     * @param str
     * @return
     * @Version1.0 2014-6-30 上午11:43:57
     */
    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    /**
     * Description: 判断字符串是否为null、空串或空格
     *
     * @param str
     * @return
     * @Version1.0 2014-6-30 上午11:45:00
     */
    public static boolean isBlank(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Description: 删除一个字符串中某些字符 e.g.: oldString="abcde" charsToDelete="ce"
     * 输出"abd"
     *
     * @param oldString     原始字符串
     * @param charsToDelete 需要删除的字符组成的串
     * @return
     * @Version1.0 2014-6-30 上午11:50:19
     */
    public static String deleteAny(String oldString, String charsToDelete) {
        if (isEmpty(oldString) || isEmpty(charsToDelete)) {
            return oldString;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < oldString.length(); i++) {
            char c = oldString.charAt(i);
            if (charsToDelete.indexOf(c) == -1) {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * Description: 若一个字符串为null或空串，赋默认值
     *
     * @param originalValue 原始字符串
     * @param defaultValue  默认值
     * @return
     * @Version1.0 2014-6-30 下午1:47:44
     */
    public static String initEmpty(String originalValue, String defaultValue) {
        return isEmpty(originalValue) ? defaultValue : originalValue;
    }

    /**
     * Description: 若一个字符串为null或空串，将其更改为空字符串
     *
     * @param originalValue 原始字符串
     * @return
     * @Version1.0 2014-6-30 下午1:50:23
     */
    public static String initEmpty(String originalValue) {
        return initEmpty(originalValue, "");
    }

    /**
     * Description: 若一个字符串为null、空串或空格，赋默认值
     *
     * @param originalValue 原始字符串
     * @param defaultValue  默认值
     * @return
     * @Version1.0 2014-6-30 下午1:51:12
     */
    public static String initBlank(String originalValue, String defaultValue) {
        return isBlank(originalValue) ? defaultValue : originalValue;
    }

    /**
     * Description: 将一个String泛型的集合类，转换为一个字符串数组
     *
     * @param collection
     * @return
     * @Version1.0 2014-6-30 下午1:57:28
     */
    public static String[] toStringArray(Collection<String> collection) {
        if (collection == null) {
            return null;
        }
        return collection.toArray(new String[collection.size()]);
    }

    /**
     * Description: 验证字符串是否符合电子邮箱格式
     *
     * @param emailStr 待验证字符串
     * @return
     * @Version1.0 2014-6-30 下午1:59:27
     */
    public static boolean emailValidation(String emailStr) {
        Pattern pattern = Pattern
                .compile("^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:"
                        + "[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$");
        return pattern.matcher(emailStr).matches();
    }

    /**
     * Description: 校验座机电话
     *
     * @param str
     * @return
     * @Version1.0 2014-7-17 下午7:41:33
     */
    public static boolean phoneValidation(String str) {
        Pattern p1 = null, p2 = null;
        Matcher m = null;
        boolean b = false;
        p1 = Pattern.compile("^[0][1-9][0-9]{1,3}-[0-9]{5,10}$"); // 验证带区号的
        p2 = Pattern.compile("^[1-9]{1}[0-9]{5,8}$"); // 验证没有区号的
        if (str.length() > 9) {
            m = p1.matcher(str);
            b = m.matches();
        } else {
            m = p2.matcher(str);
            b = m.matches();
        }
        return b;
    }

    /**
     * Description: 验证字符串是否都为汉字
     *
     * @param chiStr 待验证字符串
     * @return
     * @Version1.0 2014-6-30 下午2:00:42
     */
    public static boolean chineseAllValidation(String chiStr) {
        Pattern pattern = Pattern.compile("([\u4e00-\u9fa5]+)");
        return pattern.matcher(chiStr).matches();
    }

    /**
     * Description: 验证字符串是否符合IP地址格式
     *
     * @param ipStr 待验证字符串
     * @return
     * @Version1.0 2014-6-30 下午2:01:32
     */
    public static boolean ipValidation(String ipStr) {
        Pattern pattern = Pattern
                .compile("\\b((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\."
                        + "((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\."
                        + "((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\."
                        + "((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\b");
        return pattern.matcher(ipStr).matches();
    }

    /**
     * Description: 验证字符串是否符合日期时间格式yyyy-MM-dd hh:mm:ss ，解决闰月
     *
     * @param dateTimeStr
     * @return
     * @Version1.0 2014-6-30 下午2:03:04
     */
    public static boolean dateTimeValidation(String dateTimeStr) {
        if (dateTimeStr == null || "".equals(dateTimeStr)) {
            return false;
        }
        String regex = "("
                +
                // 第一种情况为月份是大月的有31天。
                "(^\\d{3}[1-9]|\\d{2}[1-9]\\d{1}|\\d{1}[1-9]\\d{2}|[1-9]\\d{3}"
                + // 年
                "([-/\\._]?)"
                + // 时间间隔符(-,/,.,_)
                "(10|12|0?[13578])"
                + // 大月
                "([-/\\._]?)"
                + // 时间间隔符(-,/,.,_)
                "((3[01]|[12][0-9]|0?[1-9])?)"
                + // 日(31)要验证年月因此出现0/1次
                "([\\s]?)"
                + // 空格
                "((([0-1]?[0-9]|2[0-3]):([0-5]?[0-9]):([0-5]?[0-9]))?))$"
                + // 时分秒
                "|"
                + // 或
                // 第二种情况为月份是小月的有30天，不包含2月。
                "(^\\d{3}[1-9]|\\d{2}[1-9]\\d{1}|\\d{1}[1-9]\\d{2}|[1-9]\\d{3}"
                + // 年
                "([-/\\._]?)"
                + // 时间间隔符(-,/,.,_)
                "(11|0?[469])"
                + // 小月不含2月
                "([-/\\._]?)"
                + // 时间间隔符(-,/,.,_)
                "(30|[12][0-9]|0?[1-9])"
                + // 日(30)
                "([\\s]?)"
                + // 空格
                "((([0-1]?[0-9]|2[0-3]):([0-5]?[0-9]):([0-5]?[0-9]))?))$"
                + // 时分秒
                "|"
                + // 或
                // 第三种情况为平年月份是2月28天的。
                "(^\\d{3}[1-9]|\\d{2}[1-9]\\d{1}|\\d{1}[1-9]\\d{2}|[1-9]\\d{3}"
                + // 年
                "([-/\\._]?)"
                + // 时间间隔符(-,/,.,_)
                "(0?2)"
                + // 平年2月
                "([-/\\._]?)"
                + // 时间间隔符(-,/,.,_)
                "(2[0-8]|1[0-9]|0?[1-9])"
                + // 日(28)
                "([\\s]?)"
                + // 空格
                "((([0-1]?[0-9]|2[0-3]):([0-5]?[0-9]):([0-5]?[0-9]))?))$"
                + // 时分秒
                "|"
                + // 或
                // 第四种情况为闰年月份是2月29天的。
                // 可以被4整除但不能被100整除的年份。
                // 可以被400整除的数亦是能被100整除，因此后两位是00，所以只要保证前两位能被4整除即可。
                "(^((\\d{2})(0[48]|[2468][048]|[13579][26]))|((0[48]|[2468][048]|[13579][26])00)"
                + "([-/\\._]?)" + "(0?2)" + "([-/\\._]?)" + "(29)" + "([\\s]?)"
                + "((([0-1]?\\d|2[0-3]):([0-5]?\\d):([0-5]?\\d))?))$" + // 时分秒
                ")";
        return match(regex, dateTimeStr);
    }

    private static boolean match(String regex, String str) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    /**
     * Description: 验证字符串是否为手机号 ..兼容最新的170号段
     *
     * @param cellPhoneNum 待验证字符串
     * @return
     * @Version1.0 2014-6-30 下午2:05:38
     */
    public static boolean cellPhoneNumValidation(String cellPhoneNum) {
        Pattern pattern = Pattern
                .compile("^((13[0-9])|(14[0-9])|(15[^4,\\D])|(16[0-9])|(17[0-9])|(18[0,1-9]))\\d{8}$");
        return pattern.matcher(cellPhoneNum).matches();
    }

    /**
     * Description: 验证一个字符串是否为整数，如否，返回一个默认值
     *
     * @param originalValue 原始字符串
     * @param defaultValue  默认值
     * @return
     * @Version1.0 2014-6-30 下午2:07:14
     */
    public static String initInteger(String originalValue, String defaultValue) {
        if (isEmpty(originalValue)) {
            return defaultValue;
        }
        if (!isInteger(originalValue)) {
            return defaultValue;
        }
        return originalValue;
    }

    /**
     * Description: 验证一个字符串是否符合整数格式
     *
     * @param str 待验证字符串
     * @return
     * @Version1.0 2014-6-30 下午2:12:01
     */
    public static boolean isInteger(String str) {
        if (str == null) {
            return false;
        }
        return str.matches("^\\d+$|^-[0-9]+$");
    }

    /**
     * Description: 验证一个字符串是否符合数字格式，包括整数和浮点数
     *
     * @param str
     * @return
     * @Version1.0 2014-6-30 下午2:12:45
     */
    public static boolean isNum(String str) {
        if (str == null) {
            return false;
        }
        return str.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
    }

    /**
     * Description: 如果字符串最后一个字符为给定的字符，剔除这个字符.
     *
     * @param str     原始字符串
     * @param endChar 给定的字符
     * @return
     * @Version1.0 2014-6-30 下午2:15:12
     */
    public static String deleteEndIndex(String str, char endChar) {
        if (isEmpty(str)) {
            return str;
        }
        if (str.charAt(str.length() - 1) == endChar) {
            str = str.substring(0, str.length() - 1);
        }
        return str;
    }

    /**
     * Description: 计算字符串的字节数，中文，全角按照2个字节计算
     *
     * @param str
     * @return
     * @Version1.0 2014-6-30 下午2:20:29
     */
    public static int getStrByteLen(String str) {
        if (null == str) {
            return 0;
        }
        char[] chars = str.toCharArray();
        int len = 0;
        for (char c : chars) {
            if (c < 256) {
                len++;
            } else {
                len += 2;
            }
        }
        return len;
    }

    /**
     * Description: 转义like语句中的_和%
     *
     * @param likeStr
     * @return
     * @Version1.0 2014-6-30 下午2:21:22
     */
    public static String escapeSQLLike(String likeStr) {
        String str = replace(likeStr, "/", "//");
        str = replace(str, "_", "/_");
        str = replace(str, "%", "/%");

        return str;
    }

    /**
     * Description: 验证一个字符串是否符合十六进制数字格式
     *
     * @param hexStr 待验证字符串
     * @return
     * @Version1.0 2014-6-30 下午2:23:00
     */
    public static boolean isHexNum(String hexStr) {
        if (hexStr == null) {
            return false;
        }
        return hexStr.matches("^[-+]?(([0-9A-Fa-f]+))$");
    }

    /**
     * Description: 判断一个字符串是否符合十六进制数字格式，若不是，赋予默认值
     *
     * @param originalValue 原始字符串
     * @param defaultValue  默认值
     * @return
     * @Version1.0 2014-6-30 下午2:23:34
     */
    public static String initHexNum(String originalValue, String defaultValue) {
        if (isEmpty(originalValue)) {
            return defaultValue;
        }
        if (!isHexNum(originalValue)) {
            return defaultValue;
        }
        return originalValue;
    }

    /**
     * Description: 按字节截取字符串
     *
     * @param str      源字符串
     * @param width    要截取的字节数
     * @param ellipsis
     * @return
     * @Version1.0 2014-10-11 下午3:19:44
     */
    public static String abbreviate(String str, int width, String ellipsis) {
        if (str == null || "".equals(str)) {
            return "";
        }

        int d = 0; // byte length  
        int n = 0; // char length  
        for (; n < str.length(); n++) {
            d = (int) str.charAt(n) > 256 ? d + 2 : d + 1;
            if (d > width) {
                break;
            }
        }

        if (d > width) {
            n = n - ellipsis.length() / 2;
            return str.substring(0, n > 0 ? n : 0) + ellipsis;
        }

        return str = str.substring(0, n);
    }

    /**
     * Description:
     *
     * @param money    金额
     * @param intHasDp 整数金额是否保留小数点 true是 false否
     * @param prefix   前缀
     * @param suffix   后缀
     * @return
     * @Version1.0 2014-10-13 上午11:47:37
     */
    public static String moneyFormat(Double money, Boolean intHasDp, String prefix, String suffix) {
        DecimalFormat format = new DecimalFormat();
        format.applyPattern("##,##0.00");
        if (money % 1.0 == 0 && !intHasDp) {//整数
            format.applyPattern("##,###");
        }
        String moneyStr = format.format(money);
        prefix = initBlank(prefix, "").trim();
        suffix = initBlank(suffix, "").trim();
        return prefix + moneyStr + suffix;
    }

    public static String moneyFormat(Double money, String prefix) {
        return moneyFormat(money, false, prefix, null);
    }

    public static String moneyFormat(Double money) {
        return moneyFormat(money, null);
    }

    public static String getFieldGetMothorStr(String field) {
        String f = field.substring(0, 1);
        String methodStr = "get" + f.toUpperCase() + field.substring(1);
        return methodStr;
    }

    /**
     * Description: 截字符串，末尾用指定符号提示后面还有。
     * 例如 cutStr("我爱北京天安门，天安门上太阳升", 13, "...") = "我爱北京天安门，天安门上太..."
     *
     * @param str
     * @param len
     * @param moreSign
     * @return
     * @Version1.0 2015-6-4 下午4:30:38
     */
    public static final String cutStr(String str, int len, String moreSign) {
        if (null != str && str.length() > len) {
            str = str.substring(0, len) + moreSign;
        }
        return str;
    }


    /**
     * 用filterOffUtf8Mb4
     * Description: 过滤率四个字节的utf-8字符（emoji表情符）,替换成四个空格。
     * 四字节utf-8字符mysql存储报错
     *
     * @param s
     * @return
     * @throws UnsupportedEncodingException
     * @throws Exception
     * @Version1.0 2015-7-22 上午11:48:48
     */
    public static String parseUtf8(String s) throws UnsupportedEncodingException {
        byte[] b = s.getBytes("utf-8");
        for (int i = 0, len = b.length; i < len; i++) {
            if ((b[i] & 0xF8) == 0xF0) {//F8=1111 1000,F0=1111 0000过滤4位UTF8编码（字头11110xxx）
                b[i++] = 32;
                b[i++] = 32;
                b[i++] = 32;
                b[i++] = 32;
            } else if ((b[i] & 0xFC) == 0xF8) {//FC=1111 1100,F8=1111 1000过滤5位UTF8编码（字头111110xx）
                b[i++] = 32;
                b[i++] = 32;
                b[i++] = 32;
                b[i++] = 32;
                b[i++] = 32;
            } else if ((b[i] & 0xFE) == 0xFC) {//FE=1111 1110,FC=1111 1100,过滤6位UTF8编码（字头1111110x）
                b[i++] = 32;
                b[i++] = 32;
                b[i++] = 32;
                b[i++] = 32;
                b[i++] = 32;
                b[i++] = 32;
            }
        }
        return new String(b, "utf-8");

    }

    /**
     * Description: 字符串数组转换成数字数组
     *
     * @Version1.0 2015年8月4日15:51:35
     */
    public static Integer[] StringsToIntegers(String[] str) {
        Integer[] integer = new Integer[str.length];
        for (int i = 0; i < str.length; i++) {
            integer[i] = Integer.parseInt(str[i]);
        }
        return integer;

    }

    /**
     * Description: 过滤emoji表情
     *
     * @Version1.0 2015年8月4日15:52:00
     */
    public static String filterOffUtf8Mb4(String text) throws UnsupportedEncodingException {
        byte[] bytes = text.getBytes("utf-8");
        ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
        int i = 0;
        while (i < bytes.length) {
            short b = bytes[i];
            if (b > 0) {
                buffer.put(bytes[i++]);
                continue;
            }
            b += 256;
            if ((b ^ 0xC0) >> 4 == 0) {
                buffer.put(bytes, i, 2);
                i += 2;
            } else if ((b ^ 0xE0) >> 4 == 0) {
                buffer.put(bytes, i, 3);
                i += 3;
            } else if ((b ^ 0xF0) >> 4 == 0) {
                i += 4;
            } else {
                i++;
            }
        }
        buffer.flip();
        String str = new String(buffer.array(), "utf-8");
        buffer.clear();
        return str;
    }

    /**
     * 判断字符串是否含有emoji表情
     *
     * @return
     */
    public static boolean hasEmoji(String source) {
        if (source == null || "".equals(source)) {
            return false;
        }

        int len = source.length();

        for (int i = 0; i < len; i++) {
            char codePoint = source.charAt(i);
            if (isEmojiCharacter(codePoint)) {
                return true;
            }
        }

        return false;
    }


    private static boolean isEmojiCharacter(char codePoint) {
        return !((codePoint == 0x0) || (codePoint == 0x9) || (codePoint == 0xA)
                || (codePoint == 0xD) || ((codePoint >= 0x20) && (codePoint <= 0xD7FF))
                || ((codePoint >= 0xE000) && (codePoint <= 0xFFFD))
                || ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF)));
    }

    /**
     * Description: 校验字符串是否包含特殊字符串 。参考common.js中checkValidity
     *
     * @Version1.0 2016年6月15日 下午1:30:26
     */
    public static boolean checkValidity(String str) {
        String checkString = "`~!@#$%^&*()+=[]{}\\|;':\",/<>?";
        for (Character j = 0; j < checkString.toCharArray().length; j++) {
            if (str.indexOf(checkString.substring(j, j + 1)) != -1) {
                return true;
            }
        }
        return false;
    }

    public static class TemplateParseException extends Exception {
        public TemplateParseException() {
            super();
        }

        public TemplateParseException(String message) {
            super(message);
        }

        public TemplateParseException(String message, Throwable cause) {
            super(message, cause);
        }

        public TemplateParseException(Throwable cause) {
            super(cause);
        }
    }

    public static String parseTemp(String tempStr, boolean usePropertyName, Object... param) throws TemplateParseException {
        StringReader reader = new StringReader(tempStr);
        StringWriter writer = null;
        try {
            Template template = new Template("", reader, new Configuration());
            Map<String, Object> context = new HashMap<String, Object>();
            for (int i = 0, len = param.length; i < len; i++) {
                if (usePropertyName) {
                    if (param[i] instanceof Map) {
                        context.putAll((Map) param[i]);
                    } else {
                        context.put(param[i].getClass().getSimpleName(), param[i]);
                    }
                } else {
                    context.put("p" + (i + 1), param[i]);
                }
            }
            writer = new StringWriter();
            template.process(context, writer);
            return writer.toString();
        } catch (Exception e) {
            throw new TemplateParseException(e);
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                throw new TemplateParseException();
            } finally {
                if (reader != null) {
                    reader.close();
                }
            }
        }
    }

    /**
     * @param
     * @return
     * @throws UnsupportedEncodingException
     * @Description emoji表情去掉空格
     * @author
     */
    public static String parseUtf8NoWS(String str) throws UnsupportedEncodingException {
        return parseUtf8(str).replace(" ", "");
    }

    /**
     * @Description:转化时间格式
     * @Author Xone
     * @Date 11:04 2018/11/7
     **/
    public static String formatTime(String format, Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }
}
