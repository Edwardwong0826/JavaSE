package com.life;

import java.sql.*;

public class jdbcConnection
{

    // only META-INF/service/java.sql.driver is required in JDBC 4+
    // Driver and statement implementation are in a database-specific JAR file
    // Driver, Connection, Statement, Result interface and Driver Manager are part of JDK

    // JDBC Driver 3 required class.forName, start from 4 does not required
    //Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

    static String url = "jdbc:mysql://localhost:3306/demo?serverTimezone=UTC";
    static String username = "root";
    static String password = "destiny61376554";

    public static void preparedStatement() throws SQLException{


        // PreparedStatement is more fast and secure to prevent sql injection compare to statement
        // exam for this
        // PreparedStatement can take for index for setXXX(firstxxx,xxx) for first parameter
        // statement executeQuery() cannot bind variables, only prepareStatement one can
        // since prepareStatement extends from statement, if prepareStatement.executeQuery("xxx")
        // overload with string, it will calls the statement one instead of it own it and cannot bind variable



        Connection conn = DriverManager.getConnection(url,username,password);
        System.out.println("success!");
        int value = 100;
        int id = 0;
        String query = "select * from account where amount > ? and accountID > ? ";

        try(PreparedStatement stmt = conn.prepareStatement(query))  // if prepareStatement without sql will not compile
        {
            stmt.setObject(1, value); //sql return column index start from 1
            stmt.setObject(2,id);

            //stmt.setInt(1,1);
            stmt.setInt(2,400);

            // executeQuery for select and return ResultSet
            ResultSet rs = stmt.executeQuery();


            // next() is must use at least once to get the record from the result set
            // All result getXXX can be index or column name
            while (rs.next())
            {
                String name = rs.getString("accountID"); // or rs.getString(1)
                String title = rs.getString("name"); // or rs.getString(2)
                int amount = rs.getInt( 3);
                System.out.println(name + " " + title + " " + amount );

            }

            // use if if want only return the number of rows in the table in result set
            if(rs.next())
            {
                System.out.println("--------");
                System.out.println(rs.getInt(1));
            }

        }


        // execute for delete,insert,select,update and return boolean and can use in prepared statement
        // executeUpdate for delete,insert,update return number of rows of affected
        // by default, statement is not scrollable, attempt to use next() or previous will throw exception
        // statement no more in Java 11 SE 2 exam
//        PreparedStatement st = conn.prepareStatement("select * from employee");
//
//        boolean isResultSet = st.execute();
//        if(isResultSet)
//        {
//            System.out.println("Result set returned");
//        }
//
//
//        String insertQuery = "INSERT INTO Employee VALUES('JJ','waiter',25,2500)";
//        PreparedStatement ps = conn.prepareStatement(insertQuery);
//
//        ps.executeUpdate();


    }

    public static void callableStatement() throws SQLException {

        Connection conn = DriverManager.getConnection(url,username,password);

        // CallableStatement allow non-sql statement (like stored procedures) to be execute
        // Passing an IN/INOUT parameter for stored procedure, must use {} in for include sql statement
        try(CallableStatement cstmt = conn.prepareCall("{CALL EmplAgeCount(?,?)}"))
        {
            int age = 24;
            cstmt.setInt(1, age);

            String name = "Type";
            cstmt.setString("Name", name);  // we can use the column index or column name

            ResultSet crs = cstmt.executeQuery();
        }

        // Returning an OUT parameter by stored procedure
        // is also possible to use one same parameter for both input and output as INOUT parameter
        // must use registerOutParameter() on OUT and INOUT parameters
            try(var callstmt = conn.prepareCall("?= call magic_number(?)"))
        {
            callstmt.registerOutParameter(2, Types.INTEGER);
            boolean result = callstmt.execute();
            int count = callstmt.getInt(2);
            System.out.println("There are" + count + "Employees over the age of" + count);
        }

    }


    public static void main(String[] args) throws SQLException {

        // JDBC connection is expensive to create, not closing cause resource leak and will slow down program
        // the resources need to be closed in a specific order. ResultSet is closed first, then
        // PreparedStatement and then the connection

        // closing connection also closes Prepared or CallableStatement and ResultSet
        // closing Prepared or Callable Statement also closes ResultSet

        //preparedStatement();
        callableStatement();


    }


    //1.b 2.bc 3a 4.e 5.b 6.be 7.cef 8e 9.b 10.c 11.e 12d 13.a 14.bf 15.f 16.e 17.cd 18.a 19.c
    // 20.d

    // 1.BF 2.CF 3.A 4.CD 5.C 6.B 7.ABE 8.C 9.AB 10.F 11.C 12.D 13.C 14.BF 15.C 16.E 17.C
    // 18.D 19.E 20.E 21.B

}
