package org.isandy.hope.Utils;

/**
 * @author Tammy
 * @date 2025/4/28 下午4:33
 */
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//


import java.io.Serial;
import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class SaResult extends LinkedHashMap<String, Object> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    public static final int CODE_SUCCESS = 200;
    public static final int CODE_ERROR = 500;

    public SaResult() {
    }

    public SaResult(int code, String msg, Object data) {
        this.setCode(code);
        this.setMsg(msg);
        this.setData(data);
    }

    public SaResult(Map<String, ?> map) {
        this.setMap(map);
    }

    public Integer getCode() {
        return (Integer)this.get("code");
    }

    public String getMsg() {
        return (String)this.get("msg");
    }

    public Object getData() {
        return this.get("data");
    }

    public SaResult setCode(int code) {
        this.put("code", code);
        return this;
    }

    public SaResult setMsg(String msg) {
        this.put("msg", msg);
        return this;
    }

    public SaResult setData(Object data) {
        this.put("data", data);
        return this;
    }

    public SaResult set(String key, Object data) {
        this.put(key, data);
        return this;
    }


    public SaResult setMap(Map<String, ?> map) {

        for (String key : map.keySet()) {
            this.put(key, map.get(key));
        }

        return this;
    }


    public SaResult removeDefaultFields() {
        this.remove("code");
        this.remove("msg");
        this.remove("data");
        return this;
    }

    public static SaResult ok() {
        return new SaResult(200, "ok", (Object)null);
    }

    public static SaResult ok(String msg) {
        return new SaResult(200, msg, (Object)null);
    }

    public static SaResult code(int code) {
        return new SaResult(code, (String)null, (Object)null);
    }

    public static SaResult data(Object data) {
        return new SaResult(200, "ok", data);
    }

    public static SaResult error() {
        return new SaResult(500, "error", (Object)null);
    }

    public static SaResult error(String msg) {
        return new SaResult(500, msg, (Object)null);
    }

    public static SaResult get(int code, String msg, Object data) {
        return new SaResult(code, msg, data);
    }

    public static SaResult empty() {
        return new SaResult();
    }

    public String toString() {
        return "{\"code\": " + this.getCode() + ", \"msg\": " + this.transValue(this.getMsg()) + ", \"data\": " + this.transValue(this.getData()) + "}";
    }

    private String transValue(Object value) {
        if (value == null) {
            return null;
        } else {
            return value instanceof String ? "\"" + value + "\"" : String.valueOf(value);
        }
    }
}

