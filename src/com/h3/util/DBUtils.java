/* 
 * Copyright 2014 (C) 3HSolutions LLC 
 *  
 * Created on : 12-11-2014 
 * Author     : huyhh 
 */

package com.h3.util;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.apache.log4j.Logger;

import com.h3.model.AbstractEntity;

public class DBUtils {

    // log
    private static final Logger logger = Logger.getLogger(DBUtils.class);

    public static DataSource ds;

    static {

        Context envCtx;
        try {
            Context initCtx = new InitialContext();
            envCtx = (Context) initCtx.lookup("java:comp/env");
            ds = (DataSource) envCtx.lookup("jdbc/postgres");
        } catch (NamingException ex) {
            logger.error(SysUtil.getStackTrace(ex));
        }

    }

    public static List<AbstractEntity> query(String sql, Class clazz) {

        logger.info("query");
        logger.info("sql=" + sql);

        Connection conn = null;
        PreparedStatement pst = null;
        List<AbstractEntity> results = null;
        try {
            conn = ds.getConnection();

            pst = conn.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();

            results = rs2Entity(rs, clazz);
        } catch (SQLException ex) {
            logger.error(SysUtil.getStackTrace(ex));
        } finally {

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    logger.error(SysUtil.getStackTrace(ex));
                }
            }

            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException ex) {
                    logger.error(SysUtil.getStackTrace(ex));
                }
            }
        }

        return results;
    }

    public static List<AbstractEntity> query(String sql, Class clazz, Object... args) {

        logger.info("query");
        logger.info("sql=" + sql);

        Connection conn = null;
        PreparedStatement pst = null;
        List<AbstractEntity> results = null;
        try {
            conn = ds.getConnection();

            // get prepareStatement
            pst = conn.prepareStatement(sql);

            // set parameters
            int i = 1;
            for (Object arg : args) {
                pst.setObject(i++, arg);
            }

            ResultSet rs = pst.executeQuery();
            results = rs2Entity(rs, clazz);
        } catch (SQLException ex) {
            logger.error(SysUtil.getStackTrace(ex));
        } finally {

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    logger.error(SysUtil.getStackTrace(ex));
                }
            }

            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException ex) {
                    logger.error(SysUtil.getStackTrace(ex));
                }
            }
        }

        return results;
    }

    public static List<Object[]> query(String sql, Object... args) {

        logger.info("query");
        logger.info("sql=" + sql);

        Connection conn = null;
        PreparedStatement pst = null;
        List<Object[]> results = null;
        try {
            conn = ds.getConnection();

            // get prepareStatement
            pst = conn.prepareStatement(sql);

            if (args != null) {
                // set parameters
                int i = 1;
                for (Object arg : args) {
                    pst.setObject(i++, arg);
                }
            }
            
            ResultSet rs = pst.executeQuery();
            results = rs2Array(rs);
        } catch (SQLException ex) {
            logger.error(SysUtil.getStackTrace(ex));
        } finally {

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    logger.error(SysUtil.getStackTrace(ex));
                }
            }

            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException ex) {
                    logger.error(SysUtil.getStackTrace(ex));
                }
            }
        }

        return results;
    }
    
    public static List<Object[]> query(String sql) {

        logger.info("query");
        logger.info("sql=" + sql);

        Connection conn = null;
        PreparedStatement pst = null;
        List<Object[]> results = null;
        try {
            conn = ds.getConnection();

            // get prepareStatement
            pst = conn.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();
            results = rs2Array(rs);
        } catch (SQLException ex) {
            logger.error(SysUtil.getStackTrace(ex));
        } finally {

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    logger.error(SysUtil.getStackTrace(ex));
                }
            }

            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException ex) {
                    logger.error(SysUtil.getStackTrace(ex));
                }
            }
        }

        return results;
    }
     
    public static List<Object[]> rs2Array(ResultSet rs) {

        // better add performance
        List<Object[]> records = new LinkedList<Object[]>();

        try {
            // call only once
            int cols = rs.getMetaData().getColumnCount();
            while (rs.next()) {
                Object[] arr = new Object[cols];
                for (int i = 0; i < cols; i++) {
                        arr[i] = rs.getObject(i + 1);
                }
                records.add(arr);
            }
        } catch (SQLException ex) {
            logger.error(SysUtil.getStackTrace(ex));
        }

        return records;
    }

    public static List<AbstractEntity> rs2Entity(ResultSet rs, Class clazz) {

        // better add performance
        List<AbstractEntity> records = new LinkedList<AbstractEntity>();

        try {
           Field[] fields = clazz.getDeclaredFields();
            while (rs.next()) {
                AbstractEntity obj = (AbstractEntity) clazz.newInstance();
                for (Field field : fields) {
                    field.setAccessible(true);
                    String fieldName = field.getName();
                    field.set(obj, rs.getObject(fieldName));
                }
                records.add(obj);
            }
        } catch (SQLException | IllegalArgumentException | IllegalAccessException | InstantiationException ex) {
            logger.error(SysUtil.getStackTrace(ex));
        }

        return records;
    }

    public static int executeUpdate(String sql, Object... args) {

        logger.info("executeUpdate");
        logger.info("sql=" + sql);

        Connection conn = null;
        PreparedStatement pst = null;
        int rowsAffected = 0;
        try {

            conn = ds.getConnection();

            // get prepareStatement
            pst = conn.prepareStatement(sql);

            if (args != null) {
                // set parameters
                int i = 1;
                for (Object arg : args) {
                    pst.setObject(i++, arg);
                }
            }

            rowsAffected = pst.executeUpdate();
        } catch (SQLException ex) {
            logger.error(SysUtil.getStackTrace(ex));
        } finally {

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    logger.error(SysUtil.getStackTrace(ex));
                }
            }

            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException ex) {
                    logger.error(SysUtil.getStackTrace(ex));
                }
            }
        }

        return rowsAffected;
    }

    public static int executeUpdate(Connection conn, String sql, Object... args) {

        logger.info("executeUpdate");
        logger.info("sql=" + sql);

        PreparedStatement pst = null;
        int rowsAffected = 0;
        try {

            // get prepareStatement
            pst = conn.prepareStatement(sql);

            if (args != null) {
                // set parameters
                int i = 1;
                for (Object arg : args) {
                    pst.setObject(i++, arg);
                }
            }

            rowsAffected = pst.executeUpdate();
        } catch (SQLException ex) {
            logger.error(SysUtil.getStackTrace(ex));
        } finally {

            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException ex) {
                    logger.error(SysUtil.getStackTrace(ex));
                }
            }
        }

        return rowsAffected;
    }
}
