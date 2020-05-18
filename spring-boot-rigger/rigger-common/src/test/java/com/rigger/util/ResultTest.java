package com.rigger.util;

import com.rigger.common.Result;
import com.rigger.common.SystemCode;
import org.junit.Test;

import java.util.Arrays;

public class ResultTest {

    @Test
    public void testParseResult() {
        String j1 = JsonMapper.objectToJson(Result.ok());
        Result r1 = JsonMapper.jsonToObject(j1, Result.class);
        System.out.println(r1);

        String j2 = JsonMapper.objectToJson(Result.ok(Arrays.asList("1", "AA", "VV")));
        Result r2 = JsonMapper.jsonToObject(j2, Result.class);
        System.out.println(r2);

        String j3 = JsonMapper.objectToJson(Result.ok(Arrays.asList("1", "AA", "VV")));
        Result r3 = FastJsonUtil.getObject(j2, Result.class);
        System.out.println(r3);

        String j4 = JsonMapper.objectToJson(Result.ok());
        Result r4 = FastJsonUtil.parseToClass(j4, Result.class);
        System.out.println(r4);

    }

    @Test
    public void testCreateResult() {
        System.out.println(JsonMapper.objectToJson(Result.ok()));
        System.out.println(JsonMapper.objectToJson(Result.ok(Arrays.asList("1", "AA", "VV"))));
        System.out.println(JsonMapper.objectToJson(Result.no(SystemCode.UNAUTHORIZED, "认证失败")));
        System.out.println(JsonMapper.objectToJson(Result.no(SystemCode.DATA_ALREADY_EXISTED)));
        System.out.println(JsonMapper.objectToJson(Result.no(SystemCode.DATA_ALREADY_EXISTED, Arrays.asList("1", "2", "4"))));
    }
}
