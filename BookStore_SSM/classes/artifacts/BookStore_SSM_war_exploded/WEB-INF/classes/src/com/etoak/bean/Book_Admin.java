package com.etoak.bean;

public class Book_Admin {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SCOTT.BOOK_ADMIN.ID
     *
     * @mbggenerated Tue Nov 21 20:14:57 CST 2017
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SCOTT.BOOK_ADMIN.NAME
     *
     * @mbggenerated Tue Nov 21 20:14:57 CST 2017
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SCOTT.BOOK_ADMIN.PASSWORD
     *
     * @mbggenerated Tue Nov 21 20:14:57 CST 2017
     */
    private String password;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SCOTT.BOOK_ADMIN.STATUS
     *
     * @mbggenerated Tue Nov 21 20:14:57 CST 2017
     */
    private String status;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SCOTT.BOOK_ADMIN.ID
     *
     * @return the value of SCOTT.BOOK_ADMIN.ID
     *
     * @mbggenerated Tue Nov 21 20:14:57 CST 2017
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SCOTT.BOOK_ADMIN.ID
     *
     * @param id the value for SCOTT.BOOK_ADMIN.ID
     *
     * @mbggenerated Tue Nov 21 20:14:57 CST 2017
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SCOTT.BOOK_ADMIN.NAME
     *
     * @return the value of SCOTT.BOOK_ADMIN.NAME
     *
     * @mbggenerated Tue Nov 21 20:14:57 CST 2017
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SCOTT.BOOK_ADMIN.NAME
     *
     * @param name the value for SCOTT.BOOK_ADMIN.NAME
     *
     * @mbggenerated Tue Nov 21 20:14:57 CST 2017
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SCOTT.BOOK_ADMIN.PASSWORD
     *
     * @return the value of SCOTT.BOOK_ADMIN.PASSWORD
     *
     * @mbggenerated Tue Nov 21 20:14:57 CST 2017
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SCOTT.BOOK_ADMIN.PASSWORD
     *
     * @param password the value for SCOTT.BOOK_ADMIN.PASSWORD
     *
     * @mbggenerated Tue Nov 21 20:14:57 CST 2017
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SCOTT.BOOK_ADMIN.STATUS
     *
     * @return the value of SCOTT.BOOK_ADMIN.STATUS
     *
     * @mbggenerated Tue Nov 21 20:14:57 CST 2017
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SCOTT.BOOK_ADMIN.STATUS
     *
     * @param status the value for SCOTT.BOOK_ADMIN.STATUS
     *
     * @mbggenerated Tue Nov 21 20:14:57 CST 2017
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}