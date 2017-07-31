package com.yl.crm.util;

import java.lang.reflect.Field;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.yl.crm.model.Notice;

public class BeanUtil {

	public static Object parseRequestToBean(HttpServletRequest request, Class<?> clz) {
		Object obj = null;
		// key 请求参参数名 value 请求参数值
		// {name:["zhangsa"],gender:["男"],birthDay:["2017-09-08"]}
		Map<String, String[]> paramaterMap = request.getParameterMap();
		try {
			obj = clz.newInstance();
			for (Map.Entry<String, String[]> entry : paramaterMap.entrySet()) {
				// 请求参数名
				String name = entry.getKey();
				// 请求参数值
				String[] values = entry.getValue();
				// 请求参数名要和对象属性的保持，约定大于编码
				Field field = clz.getDeclaredField(name);
				field.setAccessible(true);
				String filedTypeName = field.getType().getSimpleName();
				if (filedTypeName.equals("int")) {
					field.set(obj, Integer.parseInt(values[0]));
				} else if (filedTypeName.equals("Integer")) {
					field.set(obj, Integer.valueOf(values[0]));
				} else if (filedTypeName.equals("Date")) {

					field.set(obj, DateUtil.stringToDate(values[0]));
				} else if (filedTypeName.equals("Timestamp")) {
					Date date = DateUtil.stringToDate(values[0]);
					field.set(obj, new Timestamp(date.getTime()));

				} else {
					//
					field.set(obj, values[0]);

				}

			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		return obj;
	}

	public static void parsePropsToBean(Notice notice, PreparedStatement ps) {
		System.out.println(notice.toString());
		Field[] fields = notice.getClass().getDeclaredFields();
		for (int i = 1; i < fields.length; i++) {
			int j = 1;
			String filedTypeName = fields[i].getType().getSimpleName();

			String param = fields[i].getName();
			// String methodName = "get" + param.substring(0, 1).toUpperCase() +
			// param.substring(1);
			// System.out.println(methodName);

			String object = null;

			fields[i].setAccessible(true);
			try {
				object = (String) fields[i].get(param);

				if (!"0".equals(object) && !"null".equals(object)) {

					if (filedTypeName.equals("int")) {
						ps.setInt(j, Integer.parseInt(object));

					} else if (filedTypeName.equals("Integer")) {
						ps.setInt(j, Integer.parseInt(object));
					} else if (filedTypeName.equals("Date")) {
						ps.setDate(j, DateUtil.stringToDate(object));

					} else if (filedTypeName.equals("Timestamp")) {
						Date date = DateUtil.stringToDate(object);

						ps.setTimestamp(j, new Timestamp(date.getTime()));

					} else {
						//
						ps.setString(j, object);
					}

					j++;
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

}
