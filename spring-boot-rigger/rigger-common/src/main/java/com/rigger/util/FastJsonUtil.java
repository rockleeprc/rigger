package com.rigger.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class FastJsonUtil {

	private static final Logger logger = LoggerFactory
			.getLogger(FastJsonUtil.class);

	private static SerializeConfig mapping = new SerializeConfig();
	private static SimpleDateFormatSerializer formatSerializer = new SimpleDateFormatSerializer("yyyy-MM-dd HH:mm:ss");
	private static final SerializerFeature[] features;

	static {
		features = new SerializerFeature[] { SerializerFeature.WriteMapNullValue,
				SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteNullNumberAsZero,
				SerializerFeature.WriteNullBooleanAsFalse, SerializerFeature.WriteNullStringAsEmpty };
	}

	public FastJsonUtil() {
	}

	public static final <T> T getObject(String json, Class<T> clazz) {
		if (StringUtils.isEmpty(json) || clazz == null) {
			return null;
		}
		try {
			return JSON.parseObject(json, clazz);
		} catch (Exception e) {
			logger.error("parse json fail " + json, e);
		}
		return null;
	}


	public static <T> T parseToClass(String jsonStr, Class<?> clazz) {
		T javaObject = (T) JSON.toJavaObject(JSON.parseObject(jsonStr), clazz);
		return javaObject;
	}

	public static String parseToJSON(Object object) {
		return JSON.toJSONString(object, configMapping(), new SerializerFeature[0]);
	}

	public static String parseUnicodeJSON(Object object) {
		return JSON.toJSONString(object, new SerializerFeature[] { SerializerFeature.BrowserCompatible });
	}

	public static String parseJSONString(Object object, SimplePropertyPreFilter filter) {
		return JSON.toJSONString(object, filter, new SerializerFeature[0]);
	}

	public static String parseJSONString(Object object, String formatDate) {
		return JSON.toJSONString(object, configMapping(formatDate), new SerializerFeature[0]);
	}

	public static List<?> getListJSONToJava(String jsonString, Class<?> pojoClass) {
		JSONArray jsonArray = JSONArray.parseArray(jsonString);
		List<Object> list = new ArrayList();

		for (int i = 0; i < jsonArray.size(); ++i) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			Object pojoValue = JSON.toJavaObject(jsonObject, pojoClass);
			list.add(pojoValue);
		}

		return list;
	}

	public static Map<Object, Object> getMapJSON(String jsonString) {
		JSONObject jsonObject = JSONObject.parseObject(jsonString);
		Map<Object, Object> parseJSONMap = new LinkedHashMap(jsonObject);
		return parseJSONMap;
	}

	private static SerializeConfig configMapping() {
		mapping.put(Date.class, formatSerializer);
		return mapping;
	}

	private static SerializeConfig configMapping(String format) {
		SerializeConfig mapping = new SerializeConfig();
		mapping.put(Date.class, new SimpleDateFormatSerializer(format));
		return mapping;
	}

	public static SimplePropertyPreFilter filterProperty(Class<?> className, String... param) {
		SimplePropertyPreFilter filter = new SimplePropertyPreFilter(className, param);
		return filter;
	}
}
