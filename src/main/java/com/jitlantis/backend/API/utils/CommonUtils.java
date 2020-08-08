package com.jitlantis.backend.API.utils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Some of the common utility functions needed in other classes.
 *
 * @author Kevin Zhijun Wang
 * @version 2020.0808
 */
public class CommonUtils {

    public static String getUUID() {
        String s = UUID.randomUUID().toString();
        return s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18) + s.substring(19, 23) + s.substring(24);
    }

    public static String getUUIDAndTimestamp() {
        return getUUID() + "_" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
    }

    public static String renameFile(String fileName) {
        String extensionName = fileName.substring(fileName.lastIndexOf("."));
        return UUID.randomUUID().toString().replace("-", "") + extensionName;
    }

    public static String getRandomNumber() {
        Random random = new Random();
        int number = random.nextInt(999999);
        return String.format("%06d", number);
    }

    public static Boolean examineIds(String target, String[] ids) {
        boolean flag = false;
        for (String id : ids) {
            if (id.equals(target)) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    public static long getPageNumber(Object value, long defaultNumber) {
        long returnNumber = defaultNumber;
        if (value != null) {
            try {
                returnNumber = Long.parseLong(String.valueOf(value));
                if (returnNumber == 0 && defaultNumber != 0) {
                    returnNumber = defaultNumber;
                }
            } catch (Exception exception) {
                returnNumber = defaultNumber;
            }
        }
        return returnNumber;
    }

    public static boolean isInStringArray(String[] array, String targetValue) {
        for (String string: array) {
            if (string.equals(targetValue)) {
                return true;
            }
        }
        return false;
    }

    public static Map<String, Object> beanToMap(Object object, String[] array) {
        if (object == null) {
            return null;
        }

        Map<String, Object> map = new HashMap<>();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(object.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property: propertyDescriptors) {
                String key = property.getName();
                boolean continueFlag = true;
                if (array != null) {
                    continueFlag = isInStringArray(array, key);
                }

                if (continueFlag && !key.equals("class")) {
                    Method getter = property.getReadMethod();
                    Object value = getter.invoke(object);
                    map.put(key, value);
                }
            }
        } catch (Exception exception) {
            System.out.println("beanToMap Error " + exception);
        }

        return map;
    }

    public static Object mergeObject(Object sourceBean, Object targetBean) {
        Class<?> sourceBeanClass = sourceBean.getClass();
        Class<?> targetBeanClass = targetBean.getClass();

        Field[] sourceFields = sourceBeanClass.getDeclaredFields();
        Field[] targetFields = targetBeanClass.getDeclaredFields();
        for (int i = 0; i < sourceFields.length; i++) {
            Field sourceField = sourceFields[i];
            if (Modifier.isStatic(sourceField.getModifiers())) {
                continue;
            }

            Field targetField = targetFields[i];
            if (Modifier.isStatic(targetField.getModifiers())) {
                continue;
            }

            sourceField.setAccessible(true);
            targetField.setAccessible(true);
            try {
                if (sourceField.get(sourceBean) != null
                        && !"serialVersionUID".equals(sourceField.getName())) {
                    targetField.set(targetBean, sourceField.get(sourceBean));
                }
            } catch (IllegalArgumentException | IllegalAccessException exception) {
                exception.printStackTrace();
            }
        }
        return targetBean;
    }

    public static String readContentFromGet(String url) throws IOException {
        URL urlRetrieved = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) urlRetrieved.openConnection();
        connection.connect();
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        String result = reader.readLine();

        reader.close();
        connection.disconnect();

        return result;
    }

    public static String readContentFromPost(String url, String params) throws IOException {
        StringBuffer xmlBuffer = new StringBuffer();
        URL postUrl = new URL(url);

        HttpURLConnection connection = (HttpURLConnection) postUrl.openConnection();
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setRequestMethod("POST");
        connection.setUseCaches(false);
        connection.setInstanceFollowRedirects(true);
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        connection.connect();
        DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
        outputStream.writeBytes(params);

        outputStream.flush();
        outputStream.close();

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
        String line;
        while ((line = reader.readLine()) != null) {
            xmlBuffer.append(line).append("\n");
        }

        reader.close();
        connection.disconnect();

        return xmlBuffer.toString();
    }

    public static String readContentFromPostByToken(String url, String params, String tokenName, String token)
            throws IOException {
        StringBuffer xmlBuffer = new StringBuffer();
        URL postUrl = new URL(url);

        HttpURLConnection connection = (HttpURLConnection) postUrl.openConnection();
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setRequestMethod("POST");
        connection.setUseCaches(false);
        connection.setInstanceFollowRedirects(true);
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        connection.setRequestProperty(tokenName, token);
        connection.connect();
        DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
        outputStream.writeBytes(params);

        outputStream.flush();
        outputStream.close();

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
        String line;
        while ((line = reader.readLine()) != null) {
            xmlBuffer.append(line).append("\n");
        }

        reader.close();
        connection.disconnect();

        return xmlBuffer.toString();
    }

    public static String getDateString(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(date);
    }

    public static Date addDate(Date date, long day) {
        if (date != null && day > 0) {
            long time = date.getTime();
            day = day * 24 * 60 * 60 * 1000;
            time += day;
            return new Date(time);
        }
        return null;
    }
}
