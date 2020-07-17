package com.mooc.generator.server;

import com.mooc.generator.util.DbUtil;
import com.mooc.generator.util.Field;
import com.mooc.generator.util.FreemarkerUtil;
import freemarker.template.TemplateException;

import java.io.File;
import java.util.*;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class ServerGenerator {

    static String MODULE = "business";
    //static String Domain = "Section";
    //static String domain = "section";
    static String toServicePath = "server/src/main/java/com/mooc/server/service/";
    static String toControllerPath = MODULE + "/src/main/java/com/mooc/" + MODULE + "/controller/admin/";
    static String toDtoPath = "server/src/main/java/com/mooc/server/dto/";
    static String generatorConfigPath = "server/src/main/resources/generator/generatorConfig.xml";



    public static void main(String[] args) throws Exception {

        // only generate first element in table
        File file = new File(generatorConfigPath);
        SAXReader reader=new SAXReader();
        //read xml to document
        Document doc=reader.read(file);
        //get the root element of xml
        Element rootElement=doc.getRootElement();
        //read context element
        Element contextElement = rootElement.element("context");
        //declare an element
        Element tableElement;
        //get first table element
        tableElement=contextElement.elementIterator("table").next();
        String Domain = tableElement.attributeValue("domainObjectName");
        String tableName = tableElement.attributeValue("tableName");
        //String tableNameCn = DbUtil.getTableComment(tableName);
        String domain = Domain.substring(0, 1).toLowerCase() + Domain.substring(1);
        System.out.println("Table："+tableElement.attributeValue("tableName"));
        System.out.println("Domain："+tableElement.attributeValue("domainObjectName"));
        
        
        List<Field> fieldList = DbUtil.getColumnByTableName(tableName);
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
