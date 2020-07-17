package com.mooc.generator.server;

import com.mooc.generator.util.DbUtil;
import com.mooc.generator.util.Field;
import com.mooc.generator.util.FreemarkerUtil;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.util.*;

public class ServerGenerator {

    static String MODULE = "business";
    static String Domain = "Section";
    static String domain = "section";
    static String toServicePath = "server/src/main/java/com/mooc/server/service/";
    static String toControllerPath = MODULE + "/src/main/java/com/mooc/" + MODULE + "/controller/admin/";
    static String toDtoPath = "server/src/main/java/com/mooc/server/dto/";


    public static void main(String[] args) throws Exception {
        List<Field> fieldList = DbUtil.getColumnByTableName(domain);
        Set<String> typeSet = getJavaTypes(fieldList);
        Map<String, Object> map = new HashMap<>();


        map.put("Domain", Domain);
        map.put("domain", domain);
        map.put("module", MODULE);
        map.put("fieldList", fieldList);
        map.put("typeSet", typeSet);

        // dto
        FreemarkerUtil.initConfig("dto.ftl");
        FreemarkerUtil.generator(toDtoPath + Domain + "Dto.java", map);

        // Service
        FreemarkerUtil.initConfig("service.ftl");
        FreemarkerUtil.generator(toServicePath + Domain + "Service.java", map);

        // Controller
        FreemarkerUtil.initConfig("controller.ftl");
        FreemarkerUtil.generator(toControllerPath + Domain + "Controller.java", map);


    }

    /**
     * get all java type
     */
    private static Set<String> getJavaTypes(List<Field> fieldList) {
        Set<String> set = new HashSet<>();
        for (int i = 0; i < fieldList.size(); i++) {
            Field field = fieldList.get(i);
            set.add(field.getJavaType());
        }
        return set;
    }
}
