package com.example.emulator_project.api;


import java.sql.*;

public class SQLiteDBHandler {
    private static SQLiteDBHandler instance;
    private Connection connection;

    private SQLiteDBHandler() {
    }

    public static SQLiteDBHandler getInstance() {
        if (instance == null) {
            instance = new SQLiteDBHandler();
        }
        return instance;
    }

    public Connection getConnection() {
        try {
            if (this.connection == null) {
                this.connection = DriverManager.getConnection(Queries.DB_CONNECTION_STRING);
                if (this.connection != null) {
                    System.out.println("DB Connected to Successfully");
                }
            }
        } catch (SQLException e) {
            // handle any errors
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());

            // Print Stack Trace
            e.printStackTrace();
        }
        return this.connection;
    }

    // Initialise database with tables if no table exists -- create tables
    public void initialise() {
        Statement stmt1 = null;
        Statement stmt2 = null;
        Statement stmt3 = null;
        // connect if not connected
        if (this.connection == null) {
            this.getConnection();
        }

        try {
            // check if table is does not exist and create it if it does not -- contact
            stmt1 = this.connection.createStatement();
            stmt1.execute(Queries.CREATE_PHONEBOOK_TABLES);

            // check if table is does not exist and create it if it does not -- call logs
            stmt2 = this.connection.createStatement();
            stmt2.execute(Queries.CREATE_CALL_LOGS_TABLES);

            // check if table is does not exist and create it if it does not -- message
            stmt3 = this.connection.createStatement();
            stmt3.execute(Queries.CREATE_MESSAGES_TABLES);


        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println(ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());

        } finally {
            // release resources
            try {
                if (stmt1 != null) {
                    // System.out.println("contact table created or already exists");
                    stmt1.close();
                }
                if (stmt2 != null) {
                    // System.out.println("call_log table created or already exists");
                    stmt2.close();
                }
                if (stmt3 != null) {
                    // System.out.println("message table created or already exists");
                    stmt3.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    // Phonebook
    // -- insert into contacts table
    public boolean insertContact(String name, String phone, String image) {
        PreparedStatement prepStmt = null;

        boolean status = false;

        // connect if not connected
        if (this.connection == null) {
            this.getConnection();
        }

        try {
            String sql;

            // add contact to phonebook table
            if (image == "") {
                sql = "INSERT INTO phonebook(name, phone) VALUES(?,?)";
                prepStmt = this.connection.prepareStatement(sql);
            } else {
                sql = "INSERT INTO phonebook(name, phone, image) VALUES(?,?,?)";
                prepStmt = this.connection.prepareStatement(sql);
                prepStmt.setString(3, image);
            }

            prepStmt.setString(1, name);
            prepStmt.setString(2, phone);
            prepStmt.execute();

            status = true;

        } catch (SQLException ex) {

            status = false;
            System.out.println(ex.getMessage());
            System.out.println(ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            System.out.println("Return Status: " + status);

        } finally {
            // release resources
            try {
                if (prepStmt != null) {
                    prepStmt.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }

        return status;
    }

    // -- read from contacts table
    public ResultSet fetchAllContacts() {
        Statement stmt = null;
        ResultSet res = null;

        // connect if not connected
        if (this.connection == null) {
            getConnection();
        }

        try {
            String sql;

            stmt = this.connection.createStatement();
            sql = "SELECT * FROM phonebook ORDER BY name";
            res = stmt.executeQuery(sql);

            return res;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println(ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());

            return null;
        }
    }

    // -- find contact
    public String findContact(String phone) {
        // find contact with phone number from phonebook table and return name
        PreparedStatement prepStmt = null;
        ResultSet res = null;

        // connect if not connected
        if (connection == null) {
            getConnection();
        }

        try {
            String sql;
            sql = "SELECT name FROM phonebook WHERE phone=?";
            prepStmt = connection.prepareStatement(sql);
            prepStmt.setString(1, phone);
            res = prepStmt.executeQuery();

            String name = res.getString("name");

            if (!name.contentEquals(""))
                return name;
            else
                return "";

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println(ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());

            return "";
        }
    }

    // search for contact by name or phone
    public ResultSet findContactByNameOrPhone(String arg) {
        PreparedStatement prepStmt = null;

        // connect if not connected
        if (this.connection == null) {
            getConnection();
        }

        try {
            String sql;
            try {
                int num = Integer.parseInt(arg);
                sql = "SELECT * FROM phonebook WHERE phone LIKE ?";
            } catch (Exception e) {
                sql = "SELECT * FROM phonebook WHERE name LIKE ?";
            }

            prepStmt = this.connection.prepareStatement(sql);
            prepStmt.setString(1, arg + "%");
            return prepStmt.executeQuery();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println(ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());

            return null;
        }
    }

    // update contact image
    public void updateContactImage(String phone, String image) {
        PreparedStatement prepStmt = null;

        // connect if not connected
        if (this.connection == null) {
            getConnection();
        }

        try {
            prepStmt = this.connection.prepareStatement(Queries.UPDATE_CONTACT_IMAGE);
            prepStmt.setString(1, image);
            prepStmt.setString(2, phone);
            prepStmt.execute();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println(ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());

        }
    }

    // delete Contact
    public boolean deleteContact(String phone) {
        PreparedStatement prepStmt = null;

        // connect if not connected
        if (this.connection == null) {
            getConnection();
        }

        try {
            prepStmt = this.connection.prepareStatement(Queries.DELETE_CONTACT);
            prepStmt.setString(1, phone);
            prepStmt.execute();

            return true;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println(ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());

            return false;

        } finally {
            // release resources
            try {
                if (prepStmt != null) {
                    prepStmt.close();
                }
            } catch (Exception ex) {

            }
        }
    }

    // Call log
    // -- add call logs
    public boolean insertCallLog(String phone, String date, String time, String category) {
        PreparedStatement prepStmt = null;

        boolean status = false;

        // connect if not connected
        if (this.connection == null) {
            getConnection();
        }

        try {
            // add contact to call_log table
            prepStmt = this.connection.prepareStatement(Queries.INSERT_CALL_LOG);
            prepStmt.setString(1, phone);
            prepStmt.setString(2, date);
            prepStmt.setString(3, time);
            prepStmt.setString(4, category);

            prepStmt.execute();

            status = true;
            // System.out.printf("call log entry created{phone: %s, date: %s, time: %s, cateory: %s} %n", phone, date, time, category);
            System.out.println("its a " + category + " call!");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println(ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());

            status = false;
        } finally {
            try {
                // release resources
                if (prepStmt != null) {
                    prepStmt.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return status;
    }

    // -- fetch call history
    public ResultSet fetchAllCallLog() {
        Statement stmt = null;

        // connect if not connected
        if (this.connection == null) {
            getConnection();
        }

        try {
            stmt = this.connection.createStatement();
            return stmt.executeQuery(Queries.GET_ALL_CALL_LOGS);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println(ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());

            return null;
        }
    }

    // -- fetch a specific category of call logs
    public ResultSet fetchSpecificCallLog(String category) {
        PreparedStatement prepStmt = null;

        // connect if not connected
        if (this.connection == null) {
            getConnection();
        }

        try {
            prepStmt = this.connection.prepareStatement(Queries.GET_CALL_LOG_BY_ID);
            prepStmt.setString(1, category);
            return prepStmt.executeQuery();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println(ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());

            return null;
        }
    }

    // count number of rows returned from result set
    public int countNumOfRowsFrom(ResultSet rs) {
        // connect if not connected
        if (this.connection == null) {
            getConnection();
        }

        int rowCount = 0;

        try {
            do {
                rowCount++;
            } while (rs.next());
            // System.out.println("row count = "+rowCount);

            return rowCount;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println(ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());

            return 0;
        }
    }

    // clear call log
    public boolean clearCallLog() {
        PreparedStatement prepStmt = null;

        // connect if not connected
        if (this.connection == null) {
            getConnection();
        }

        try {
            prepStmt = this.connection.prepareStatement(Queries.DELETE_ALL_CALL_LOGS);
            prepStmt.execute();

            return true;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println(ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());

            return false;

        } finally {
            // release resources
            try {
                if (prepStmt != null) {
                    prepStmt.close();
                }
            } catch (Exception ex) {

            }
        }
    }

    // Messages
    // add message to database
    public void saveMessage(String msg, String date, String time, String receiver) {
        PreparedStatement prepStmt = null;
        if (this.connection == null) {
            getConnection();
        }

        try {
            prepStmt = this.connection.prepareStatement(Queries.INSERT_MESSAGE);
            prepStmt.setString(1, msg);
            prepStmt.setString(2, date);
            prepStmt.setString(3, time);
            prepStmt.setString(4, receiver);

            prepStmt.execute();


            System.out.println("Values inserted into database!");

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    // get contact phone number using the name -- returns first contact with such name
    public String validateContact(String name) {
        // find contact with phone number from phonebook table and return name
        PreparedStatement prepStmt = null;
        ResultSet res = null;


        // connect if not connected
        if (this.connection == null) {
            getConnection();
        }

        try {
            prepStmt = this.connection.prepareStatement(Queries.VALIDATE_CONTACT);
            prepStmt.setString(1, name);
            res = prepStmt.executeQuery();

            String phone = res.getString("phone");

            if (!name.contentEquals(""))
                return phone;
            else
                return "";

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println(ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());

            return "";
        }
    }

    // get all messages sent by contact
    public ResultSet returnAllMessagesToThis(String contact) {
        // find contact with phone number from phonebook table and return name
        PreparedStatement prepStmt = null;
        ResultSet res = null;


        // connect if not connected
        if (this.connection == null) {
            getConnection();
        }

        try {
            String sql;

            prepStmt = this.connection.prepareStatement(Queries.GET_ALL_MESSAGES_THIS);
            prepStmt.setString(1, contact);
            res = prepStmt.executeQuery();

            return res;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println(ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());

            return res = null;
        }

    }

    // get all messages in the messages table
    public ResultSet getAllMessages() {
        // find contact with phone number from phonebook table and return name
        PreparedStatement prepStmt = null;
        ResultSet res = null;


        // connect if not connected
        if (this.connection == null) {
            getConnection();
        }

        try {
            prepStmt = this.connection.prepareStatement(Queries.GET_ALL_MESSAGES);
            res = prepStmt.executeQuery();

            return res;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println(ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());

            return res = null;
        }

    }

    // clear call log
    public boolean clearMessageLog() {
        PreparedStatement prepStmt = null;

        // connect if not connected
        if (this.connection == null) {
            getConnection();
        }

        try {
            String sql;

            prepStmt = this.connection.prepareStatement(Queries.DELETE_ALL_MESSAGES);
            prepStmt.execute();

            return true;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println(ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());

            return false;

        } finally {
            // release resources
            try {
                if (prepStmt != null) {
                    prepStmt.close();
                }
            } catch (Exception ex) {

            }
        }
    }

    public static class Queries {
        public static final String DB_CONNECTION_STRING = "jdbc:sqlite:C:/Users/Axornam/Projects/emulator_project/src/main/java/com/example/emulator_project/db/android_phone.db";
        public static final String CREATE_PHONEBOOK_TABLES = String.format("CREATE TABLE IF NOT EXISTS phonebook(id INTEGER,name VARCHAR(20),phone VARCHAR(20) UNIQUE NOT NULL,image VARCHAR(255),PRIMARY KEY(id))");
        public static final String CREATE_CALL_LOGS_TABLES = String.format("CREATE TABLE IF NOT EXISTS call_log(log_id INTEGER,log_phone VARCHAR(20) NOT NULL,date VARCHAR(255) NOT NULL,time VARCHAR(10) NOT NULL,category VARCHAR(10) NOT NULL,PRIMARY KEY(log_id))");
        public static final String CREATE_MESSAGES_TABLES = String.format("CREATE TABLE IF NOT EXISTS message(msg_id INTEGER,body VARCHAR(255) NOT NULL,date VARCHAR(255) NOT NULL,time VARCHAR(10),receiver VARCHAR(10),PRIMARY KEY(msg_id))");
        public static final String DELETE_ALL_MESSAGES = "DELETE FROM message";
        public static final String GET_ALL_MESSAGES = "SELECT DISTINCT * FROM message LEFT JOIN phonebook ON message.receiver=phonebook.phone ORDER BY msg_id DESC";
        public static final String GET_ALL_MESSAGES_THIS = "SELECT body FROM message LEFT JOIN phonebook ON message.receiver=phonebook.phone WHERE receiver=?";
        public static final String VALIDATE_CONTACT = "SELECT phone FROM phonebook WHERE name=? LIMIT 1";
        public static final String INSERT_MESSAGE = "INSERT INTO message(body,date,time,receiver) VALUES(?,?,?,?)";
        public static final String DELETE_ALL_CALL_LOGS = "DELETE FROM call_log";
        public static final String GET_CALL_LOG_BY_ID = "SELECT * FROM call_log AS c LEFT JOIN phonebook AS p ON c.log_phone=p.phone WHERE category=? ORDER BY c.log_id DESC";
        public static final String GET_ALL_CALL_LOGS = "SELECT * FROM call_log AS c LEFT JOIN phonebook AS p ON c.log_phone=p.phone ORDER BY c.log_id DESC";
        //language=GenericSQL
        public static final String INSERT_CALL_LOG = "INSERT INTO call_log(log_phone, date, time, category) VALUES(?,?,?,?)";
        public static final String DELETE_CONTACT = "DELETE FROM phonebook WHERE phone=?";
        public static final String UPDATE_CONTACT_IMAGE = "UPDATE phonebook SET image=? WHERE phone=?";
    }
}
