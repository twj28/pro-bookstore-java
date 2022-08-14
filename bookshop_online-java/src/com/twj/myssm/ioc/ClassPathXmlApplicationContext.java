package com.twj.myssm.ioc;

import com.twj.myssm.utils.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassNmae ClassPathXmlApplicationContext
 * @Description TODO
 * @Author twj280
 * @Date 2022/5/3 21:09
 * @Version 1.0
 **/
public class ClassPathXmlApplicationContext implements BeanFactory {
    private Map<String, Object> map = new HashMap<>();
    private String path = "applicationContext.xml";
    public ClassPathXmlApplicationContext(){
        this("applicationContext.xml");
    }

    public ClassPathXmlApplicationContext(String path) {
        if(StringUtils.isEmpty(path)){
            throw new RuntimeException("IOC容器配置错误");
        }
        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream(path);
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(is);

            NodeList nodeList = document.getElementsByTagName("bean");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element elementNode = (Element) node;
                    String BeanId = elementNode.getAttribute("id");
                    String ClassName = elementNode.getAttribute("class");
                    Class BeanClass = Class.forName(ClassName);
                    Object BeanObj = BeanClass.newInstance();
                    map.put(BeanId, BeanObj);
                }
            }
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element elementNode = (Element) node;
                    String BeanId = elementNode.getAttribute("id");

                    NodeList childNodes = node.getChildNodes();
                    for (int j = 0; j < childNodes.getLength(); j++) {
                        Node childNode = childNodes.item(j);
//                        System.out.println(childNode);
                        if ("property".equals(childNode.getNodeName()) && childNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element elementChildNode = (Element) childNode;
                            String propertyName = elementChildNode.getAttribute("name");
                            String propertyRef = elementChildNode.getAttribute("ref");

                            //获取ref的实例对象
                            Object refObj = getBean(propertyRef);
                            //获取bean标签的对象
                            Object beanObj = getBean(BeanId);

                            //通过反射获取 bean中的property的属性赋值
                            Class ObjBeanClazz = beanObj.getClass();

                            Field propertyField = ObjBeanClazz.getDeclaredField(propertyName);
                            propertyField.setAccessible(true);
                            propertyField.set(beanObj,refObj);


                        }
                    }

                }
            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Object getBean(String id) {
        return map.get(id);
    }
}
