package com.etoak.dao;

import com.etoak.bean.Book_Admin;
import com.etoak.bean.Book_AdminExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Book_AdminMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SCOTT.BOOK_ADMIN
     *
     * @mbggenerated Tue Nov 21 20:14:57 CST 2017
     */
    int countByExample(Book_AdminExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SCOTT.BOOK_ADMIN
     *
     * @mbggenerated Tue Nov 21 20:14:57 CST 2017
     */
    int deleteByExample(Book_AdminExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SCOTT.BOOK_ADMIN
     *
     * @mbggenerated Tue Nov 21 20:14:57 CST 2017
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SCOTT.BOOK_ADMIN
     *
     * @mbggenerated Tue Nov 21 20:14:57 CST 2017
     */
    int insert(Book_Admin record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SCOTT.BOOK_ADMIN
     *
     * @mbggenerated Tue Nov 21 20:14:57 CST 2017
     */
    int insertSelective(Book_Admin record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SCOTT.BOOK_ADMIN
     *
     * @mbggenerated Tue Nov 21 20:14:57 CST 2017
     */
    List<Book_Admin> selectByExample(Book_AdminExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SCOTT.BOOK_ADMIN
     *
     * @mbggenerated Tue Nov 21 20:14:57 CST 2017
     */
    Book_Admin selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SCOTT.BOOK_ADMIN
     *
     * @mbggenerated Tue Nov 21 20:14:57 CST 2017
     */
    int updateByExampleSelective(@Param("record") Book_Admin record, @Param("example") Book_AdminExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SCOTT.BOOK_ADMIN
     *
     * @mbggenerated Tue Nov 21 20:14:57 CST 2017
     */
    int updateByExample(@Param("record") Book_Admin record, @Param("example") Book_AdminExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SCOTT.BOOK_ADMIN
     *
     * @mbggenerated Tue Nov 21 20:14:57 CST 2017
     */
    int updateByPrimaryKeySelective(Book_Admin record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SCOTT.BOOK_ADMIN
     *
     * @mbggenerated Tue Nov 21 20:14:57 CST 2017
     */
    int updateByPrimaryKey(Book_Admin record);
}