package com.rigger.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.commons.lang3.StringUtils;

public class JsonMapper {

	private static ObjectMapper objectMapper = new ObjectMapper();

	static {
		// FAIL_ON_UNKNOWN_PROPERTIES在序列化的时候，如果遇到不认识的字段的处理方式
		// 默认启用特性，这意味着在遇到未知属性时抛出JsonMappingException。在引入该特性之前，这是默认的默认设置。
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		// FAIL_ON_EMPTY_BEANS决定了在没有找到类型的存取器时发生了什么（并且没有注释表明它是被序列化的）。如果启用（默认），
		// 将抛出一个异常来指明这些是非序列化类型;如果禁用了，它们将被序列化为空对象，即没有任何属性。
		// 请注意，这个特性只对那些没有任何识别注释的“空”bean产生影响（如@json序列化）：那些有注释的bean不会导致抛出异常。
		objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		//取消时间的转化格式,默认是时间戳,可以取消,同时需要设置要表现的时间格式
		objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

		
		// 过滤类的属性id
		// objectMapper.setFilters(new
		// SimpleFilterProvider().setFailOnUnknownId(false));
		// 在序列化时，只有那些值为null或被认为为空的值的属性才不会被包含在内。
		objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
	}
	
	

	/**
	 * 对象转换成json
	 * 
	 * @param obj
	 * @param <T>
	 * @return
	 */
	public static <T> String objectToJson(T obj) {
		if (obj == null) {
			return null;
		}
		try {
			return obj instanceof String ? (String) obj : objectMapper.writeValueAsString(obj);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 对象转换成格式化的json
	 * 
	 * @param obj
	 * @param <T>
	 * @return
	 */
	public static <T> String objectToJsonPretty(T obj) {
		if (obj == null) {
			return null;
		}
		try {
			return obj instanceof String ? (String) obj
					: objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 将json转换成对象Class
	 * 
	 * @param src
	 * @param clazz
	 * @param <T>
	 * @return
	 */
	public static <T> T jsonToObject(String src, Class<T> clazz) {
		if (StringUtils.isEmpty(src) || clazz == null) {
			return null;
		}
		try {
			return clazz.equals(String.class) ? (T) src : objectMapper.readValue(src, clazz);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 将json转换成对象TypeReference
	 * 
	 * @param src
	 * @param typeReference
	 * @param <T>
	 * @return
	 */
	public static <T> T jsonToObject(String src, TypeReference<T> typeReference) {
		if (StringUtils.isEmpty(src) || typeReference == null) {
			return null;
		}
		try {
			return (T) (typeReference.getType().equals(String.class) ? src
					: objectMapper.readValue(src, typeReference));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 将json转换成对象
	 * 
	 * @param src
	 * @param collectionClass
	 * @param elementClasses
	 * @param <T>
	 * @return
	 */
	public static <T> T jsonToObject(String src, Class<?> collectionClass, Class<?>... elementClasses) {
		JavaType javaType = objectMapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
		try {
			return objectMapper.readValue(src, javaType);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public final static boolean isJson(String json) {
		try {
			objectMapper.readTree(json);
			return true;
		} catch (IOException e) {
			return false;
		}
	}

	public static void main(String[] args) {
		User u = new User();
		u.setId(1);
		u.setName("hello");
		u.setList(Arrays.asList("A", "B", "C"));
		String userJson = JsonMapper.objectToJson(u);
		System.out.println(userJson);

		 String json = "{\"id\":1,\"ids\":41,\"list\":[\"A\",\"B\",\"C\"]}";
//		String json = "";
		User toUser = JsonMapper.jsonToObject(json, User.class);
		System.out.println(toUser);
		
		System.out.println(isJson(json));
	}

	private static class User {

		private int id;
		private String name;

		List<String> list;

		public List<String> getList() {
			return list;
		}

		public void setList(List<String> list) {
			this.list = list;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return "User [id=" + id + ", name=" + name + ", list=" + list + "]";
		}

	}
}
