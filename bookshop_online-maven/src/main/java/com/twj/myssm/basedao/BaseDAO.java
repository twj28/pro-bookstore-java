package com.twj.myssm.basedao;



import com.twj.myssm.exceptions.DAOException;
import com.twj.myssm.utils.JDBCUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author twj28
 * @create 2022 2022/4/7 15:50
 */

public class BaseDAO<T> {
    private Class clazz;
    private Connection conn ;

    {
        //获取父类的泛型参数
        Type type = this.getClass().getGenericSuperclass();
        ParameterizedType parameterizedType = (ParameterizedType) type;
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        clazz = (Class) actualTypeArguments[0];
    }
    protected Connection getConn(){
        return JDBCUtils.getThreadLocalConn();
    }

    /**
     * @return int
     * @Author: twj280
     * @Description: 执行更新（增删改） ，返回影响条数
     * @date: 10:37 2022/4/8
     * @Param: [conn, sql, args]
     **/
    public int executeUpdate(String sql, Object... args) {
        PreparedStatement ps = null;
        try {
            conn=getConn();
            boolean insertFlag = false;
            insertFlag = sql.trim().toUpperCase().startsWith("INSERT");
            if (insertFlag) {
                //预编译sql
                ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);//获取自增列方式
                //填充占位符
                setParam(ps, args);
                //执行sql
                ps.executeUpdate();
                //获取主键
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    return ((Long) rs.getLong(1)).intValue();
                }
            } else {
                //预编译sql
                ps = conn.prepareStatement(sql);
                //填充占位符
                setParam(ps, args);
                //执行sql
                return ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException("BaseDAO update 出错了。。。");
        } finally {
            //关闭资源
            JDBCUtils.closeResource(null, ps);
        }
        return 0;
    }
    public static boolean isNotMyType(String typeName){
        return "java.lang.Integer".equals(typeName) ||
                "java.lang.String".equals(typeName) ||
                "java.util.Date".equals(typeName) ||
                "java.sql.Date".equals(typeName) ||
                "java.lang.Double".equals(typeName) ||
                "java.lang.Byte".equals(typeName) ||
                "java.lang.Float".equals(typeName) ||
                "java.lang.Long".equals(typeName) ||
                "java.math.BigDecimal".equals(typeName)
                ;

    }
    public static boolean isMyType(String typeName){
        return !isNotMyType(typeName);
    }
    /**
     * @Author: twj280
     * @Description: 通过反射技术，给obj对象的property属性赋propertyValue值
     * @date: 19:59 2022/5/8
     * @Param: [obj, property, propertyValue]
     * @return void
     **/
    //通过反射技术，给obj对象的property属性赋propertyValue值
    public void setValue(Object obj,String property,Object propertyValue) {
        Class Clazz = obj.getClass();
        try {
            Field field = clazz.getDeclaredField(property);
            if(field != null){
                //获取当前字段的类型名称
                String typeName = field.getType().getName();
                //判断如果是自定义类型，则需要调用这个自定义类的带一个参数的构造方法，创建出这个自定义类的实例对象，然后将实例对象赋值给这个属性。

                //根据字段类型名 和系统类型名，判断类型 是否为自定义类型
                if(isMyType(typeName)){
                    //根据字段类型名 获取Class对象
                    Class typeNameClass  = Class.forName(typeName);
                    //获得自定义类带一个参数的构造器
                    Constructor  constructor = typeNameClass.getDeclaredConstructor(Integer.class);
                    //通过自定义类的 带一个参数的构造器，创建自定义类的实例对象。
                    propertyValue = constructor.newInstance(propertyValue);
                }

                field.setAccessible(true);
                field.set(obj,propertyValue);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("BaseDAO setValue 出错了。。。");
        }

    }
    /**
     * @return T
     * @Author: twj280
     * @Description: 执行查询，返回单个实体对象
     * @date: 10:38 2022/4/8
     * @Param: [conn, sql, args]
     **/
    public T load(String sql, Object... args) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //预处理sql
            conn=getConn();
            ps = conn.prepareStatement(sql);
            //填充占位符
            setParam(ps, args);
            //执行，并获取结果集
            rs = ps.executeQuery();
            if (rs.next()) {
                T t = (T) clazz.newInstance();
                //得到表的元数据
                ResultSetMetaData rsm = rs.getMetaData();
                //获取列数
                int columnCount = rsm.getColumnCount();
                for (int i = 0; i < columnCount; i++) {
                    Object columnValue = rs.getObject(i + 1);
                    String columnLabel = rsm.getColumnLabel(i + 1);
                    setValue(t,columnLabel,columnValue);

//                    Field field = clazz.getDeclaredField(columnLabel);
//                    field.setAccessible(true);
//                    field.set(t, columnValue);
                }
                return t;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("BaseDAO getInstance 出错了。。。");
        } finally {

            JDBCUtils.closeResource(null, ps, rs);
        }
        return null;
    }

    /**
     * @return java.util.List<T>
     * @Author: twj280
     * @Description: 执行查询返回List，即返回多条记录
     * @date: 10:38 2022/4/8
     * @Param: [conn, sql, args]
     **/
    public List<T> executeQuery(String sql, Object... args) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //预处理sql
            conn=getConn();
            ps = conn.prepareStatement(sql);
            //填充占位符
            setParam(ps, args);
            //执行，并获取结果集
            rs = ps.executeQuery();
            List<T> list = new ArrayList<>();
            while (rs.next()) {
                T t = (T) clazz.newInstance();
                //得到表的元数据

                ResultSetMetaData rsm = rs.getMetaData();
                //获取列数
                int columnCount = rsm.getColumnCount();
                for (int i = 0; i < columnCount; i++) {
                    Object columnValue = rs.getObject(i + 1);
                    String columnLabel = rsm.getColumnLabel(i + 1);

                    setValue(t,columnLabel,columnValue);
                }
                list.add(t);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("BaseDAO getForList 出错了");
        } finally {
            JDBCUtils.closeResource(null, ps, rs);
        }
    }


    /**
     * @return T
     * @Author: twj280
     * @Description: 执行复杂查询，返回例如统计结果
     * @date: 10:38 2022/4/8
     * @Param: [conn, sql]
     **/
    public Object[] executeComplexQuery(String sql,Object ...args) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //预处理sql
            conn=getConn();
            ps = conn.prepareStatement(sql);
            setParam(ps,args);
            //执行，并获取结果集
            rs = ps.executeQuery();
            ResultSetMetaData rsm = rs.getMetaData();
            int columnCount = rsm.getColumnCount();
            Object[] columnValueArr = new Object[columnCount];
            if (rs.next()) {
                for (int i = 0; i < columnCount; i++) {
                    Object columnValue = rs.getObject(i + 1);
                    columnValueArr[i] = columnValue;

                }

                return columnValueArr;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException("BaseDAO getValue 出错了。。。");
        } finally {

            JDBCUtils.closeResource(null, ps, rs);
        }
        return null;
    }
    //给预处理命令对象设置参数
    private void setParam(PreparedStatement ps, Object... args) {
        if (args != null && args.length > 0) {
            for (int i = 0; i < args.length; i++) {
                try {
                    ps.setObject(i + 1, args[i]);
                } catch (SQLException e) {
                    e.printStackTrace();
                    throw new DAOException("BaseDAO setParam出错了。。。");
                }
            }

        }
    }
}
