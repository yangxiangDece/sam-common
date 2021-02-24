/**
 *
 */
package com.yang.common;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PinYinTools {

    private static final Logger log = LoggerFactory.getLogger(PinYinTools.class);

    /**
     * 将文字转为汉语拼音 字符串中有数字或者字母，都会显示出来
     *
     * @param chineseLanguage 要转成拼音的中文
     * @param type            转换的大小写类型
     */
    public static String getHanyuPinyin(String chineseLanguage, HanyuPinyinCaseType type) {
        if (StringUtils.isBlank(chineseLanguage)) {
            return chineseLanguage;
        }

        char[] cl_chars = chineseLanguage.trim().toCharArray();
        String hanyupinyin = "";
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(type);// 输出拼音全部小写
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);// 不带声调
        defaultFormat.setVCharType(HanyuPinyinVCharType.WITH_V);
        try {
            for (int i = 0; i < cl_chars.length; i++) {
                if (String.valueOf(cl_chars[i]).matches("[\u4e00-\u9fa5]+")) {// 如果字符是中文,则将中文转为汉语拼音
                    hanyupinyin += PinyinHelper.toHanyuPinyinStringArray(cl_chars[i], defaultFormat)[0];
                } else {// 如果字符不是中文,则不转换
                    hanyupinyin += cl_chars[i];
                }
            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            log.error("characters cannot be converted into Chinese phonetic alphabet！", e);
        }
        return hanyupinyin;
    }

    /**
     * 读取汉字大写简拼 字符串中有数字或者字母，都会显示出来
     *
     * @param ChineseLanguage
     * @return
     */
    public static String getFirstLettersUp(String ChineseLanguage) {
        return getAllFirstLetters(ChineseLanguage, HanyuPinyinCaseType.UPPERCASE);
    }

    /**
     * 读取汉字小写简拼 字符串中有数字或者字母，都会显示出来
     *
     * @param ChineseLanguage
     * @return
     */
    public static String getFirstLettersLow(String ChineseLanguage) {
        return getAllFirstLetters(ChineseLanguage, HanyuPinyinCaseType.LOWERCASE);
    }

    /**
     * 读取汉字简拼 字符串中有数字或者字母，都会显示出来
     *
     * @param chineseLanguage
     * @param caseType
     * @return
     */
    public static String getAllFirstLetters(String chineseLanguage, HanyuPinyinCaseType caseType) {
        if (StringUtils.isBlank(chineseLanguage)) {
            return chineseLanguage;
        }

        char[] cl_chars = chineseLanguage.trim().toCharArray();
        String hanyupinyin = "";
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(caseType);// 输出拼音全部大写
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);// 不带声调
        try {
            for (int i = 0; i < cl_chars.length; i++) {
                String str = String.valueOf(cl_chars[i]);
                if (str.matches("[\u4e00-\u9fa5]+")) {// 如果字符是中文,则将中文转为汉语拼音,并取第一个字母
                    hanyupinyin += PinyinHelper.toHanyuPinyinStringArray(cl_chars[i], defaultFormat)[0].substring(0, 1);
                } else if (str.matches("[0-9]+")) {// 如果字符是数字,取数字
                    hanyupinyin += cl_chars[i];
                } else if (str.matches("[a-zA-Z]+")) {// 如果字符是字母,取字母
                    hanyupinyin += cl_chars[i];
                } else {// 否则不转换
                    hanyupinyin += cl_chars[i];// 如果是标点符号的话，带着
                }
            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            log.error("characters cannot be converted into Chinese phonetic alphabet！", e);
        }
        return hanyupinyin;
    }

    /**
     * 取第一个汉字的第一个字符 String @throws
     */
    public static String getFirstLetter(String chineseLanguage) {
        if (StringUtils.isBlank(chineseLanguage)) {
            return chineseLanguage;
        }

        char[] cl_chars = chineseLanguage.trim().toCharArray();
        String hanyupinyin = "";
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE);// 输出拼音全部大写
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);// 不带声调
        try {
            String str = String.valueOf(cl_chars[0]);
            if (str.matches("[\u4e00-\u9fa5]+")) {// 如果字符是中文,则将中文转为汉语拼音,并取第一个字母
                hanyupinyin = PinyinHelper.toHanyuPinyinStringArray(cl_chars[0], defaultFormat)[0].substring(0, 1);
            } else if (str.matches("[0-9]+")) {// 如果字符是数字,取数字
                hanyupinyin += cl_chars[0];
            } else if (str.matches("[a-zA-Z]+")) {// 如果字符是字母,取字母
                hanyupinyin += cl_chars[0];
            } else {// 否则不转换

            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            log.error("characters cannot be converted into Chinese phonetic alphabet！", e);
        }
        return hanyupinyin;
    }

    /**
     * 获取字符串内的所有汉字的汉语拼音并大写每个字的首字母
     * @param chinese
     * @return
     */
    public static String chineseToSpell(String chinese) {
        if (chinese == null) {
            return null;
        }
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        //小写
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        // 不标声调
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        // u:的声母替换为v
        format.setVCharType(HanyuPinyinVCharType.WITH_V);
        try {
            StringBuilder stringBuilder = new StringBuilder();
            int chineseLength = chinese.length();
            for (int num = 0; num < chineseLength; num++) {
                String[] array = PinyinHelper.toHanyuPinyinStringArray(chinese.charAt(num), format);
                if (array == null || array.length == 0) {
                    continue;
                }
                // 不管多音字,只取第一个
                String str = array[0];
                // 获取拼音第一个字母
                char firstCharacter = str.charAt(0);
                String pinyin;
                if (num == 0) {
                    pinyin = String.valueOf(firstCharacter).toUpperCase().concat(str.substring(1));
                } else {
                    pinyin = String.valueOf(firstCharacter).concat(str.substring(1));
                }

                stringBuilder.append(pinyin);
            }
            return stringBuilder.toString();
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            e.printStackTrace();
        }
        return null;
    }
}
