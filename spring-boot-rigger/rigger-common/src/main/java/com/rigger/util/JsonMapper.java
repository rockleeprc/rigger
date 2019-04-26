package com.rigger.util;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JsonMapper {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    static {
        // FAIL_ON_UNKNOWN_PROPERTIES在序列化的时候，如果遇到不认识的字段的处理方式
        // 默认启用特性，这意味着在遇到未知属性时抛出JsonMappingException。在引入该特性之前，这是默认的默认设置。
        OBJECT_MAPPER.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        // FAIL_ON_EMPTY_BEANS决定了在没有找到类型的存取器时发生了什么（并且没有注释表明它是被序列化的）。如果启用（默认），
        // 将抛出一个异常来指明这些是非序列化类型;如果禁用了，它们将被序列化为空对象，即没有任何属性。
        // 请注意，这个特性只对那些没有任何识别注释的“空”bean产生影响（如@json序列化）：那些有注释的bean不会导致抛出异常。
        OBJECT_MAPPER.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        //取消时间的转化格式,默认是时间戳,可以取消,同时需要设置要表现的时间格式
        OBJECT_MAPPER.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        OBJECT_MAPPER.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));


        // 过滤类的属性id
        // OBJECT_MAPPER.setFilters(new
        // SimpleFilterProvider().setFailOnUnknownId(false));
        // 在序列化时，只有那些值为null或被认为为空的值的属性才不会被包含在内。
        OBJECT_MAPPER.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
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
            return obj instanceof String ? (String) obj : OBJECT_MAPPER.writeValueAsString(obj);
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
                    : OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
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
//		if (StringUtils.isEmpty(src) || clazz == null) {
        if (src == null || src.length() == 0 || clazz == null) {
            return null;
        }
        try {
            return clazz.equals(String.class) ? (T) src : OBJECT_MAPPER.readValue(src, clazz);
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
//		if (StringUtils.isEmpty(src) || typeReference == null) {
        if (src == null || src.length() == 0 || typeReference == null) {
            return null;
        }
        try {
            return (T) (typeReference.getType().equals(String.class) ? src
                    : OBJECT_MAPPER.readValue(src, typeReference));
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
        JavaType javaType = OBJECT_MAPPER.getTypeFactory().constructParametricType(collectionClass, elementClasses);
        try {
            return OBJECT_MAPPER.readValue(src, javaType);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public final static boolean isJson(String json) {
        try {
            OBJECT_MAPPER.readTree(json);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public static void main(String[] args) {
        String json = "";
//        System.out.println(isJson(json));

        Canal canal = jsonToObject(json, Canal.class);

//        Map<String, Object>[] data = canal.getData();
//        for (Map<String, Object> map : data) {
//            System.out.println(map);
//
//            if(!map.containsKey("id")){
//                // TODO
//            }
//        }

//        System.out.println(canal.getType());
//        System.out.println(Arrays.toString(canal.getPkNames()));
//
//        System.out.println(canal.getSqlType());
//        System.out.println(canal.getMysqlType());

        System.out.println(canal.getSql());
    }

    private static class Canal implements Serializable {


        private Map<String, Object>[] data;

        private String database;
        private Long es;
        private Long id;
        private Boolean isDdl;
        private Map<String, Object> mysqlType;

        private String old;
        private String[] pkNames;
        private String sql;
        private Map<String, Object> sqlType;

        private String table;
        private Long ts;
        private String type;


        @Override
        public String toString() {
            return "Canal{" +
                    "data=" + Arrays.toString(data) +
                    ", database='" + database + '\'' +
                    ", es=" + es +
                    ", id=" + id +
                    ", isDdl=" + isDdl +
                    ", mysqlType=" + mysqlType +
                    ", old='" + old + '\'' +
                    ", pkNames=" + Arrays.toString(pkNames) +
                    ", sql='" + sql + '\'' +
                    ", sqlType=" + sqlType +
                    ", table='" + table + '\'' +
                    ", ts=" + ts +
                    ", type='" + type + '\'' +
                    '}';
        }

        public Map<String, Object>[] getData() {
            return data;
        }

        public void setData(Map<String, Object>[] data) {
            this.data = data;
        }

        public String getDatabase() {
            return database;
        }

        public void setDatabase(String database) {
            this.database = database;
        }

        public Long getEs() {
            return es;
        }

        public void setEs(Long es) {
            this.es = es;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Boolean getDdl() {
            return isDdl;
        }

        public void setDdl(Boolean ddl) {
            isDdl = ddl;
        }

        public Map<String, Object> getMysqlType() {
            return mysqlType;
        }

        public void setMysqlType(Map<String, Object> mysqlType) {
            this.mysqlType = mysqlType;
        }

        public String getOld() {
            return old;
        }

        public void setOld(String old) {
            this.old = old;
        }

        public String[] getPkNames() {
            return pkNames;
        }

        public void setPkNames(String[] pkNames) {
            this.pkNames = pkNames;
        }

        public String getSql() {
            return sql;
        }

        public void setSql(String sql) {
            this.sql = sql;
        }

        public Map<String, Object> getSqlType() {
            return sqlType;
        }

        public void setSqlType(Map<String, Object> sqlType) {
            this.sqlType = sqlType;
        }

        public String getTable() {
            return table;
        }

        public void setTable(String table) {
            this.table = table;
        }

        public Long getTs() {
            return ts;
        }

        public void setTs(Long ts) {
            this.ts = ts;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
