package sample;

import sample.configuration.Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;


public class DBHandler extends Config {
    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectString = "jdbc:postgresql://" + dbHost + ":" + dbPort + "/" + dbName;

        Class.forName("org.postgresql.Driver");
        dbConnection = DriverManager.getConnection(connectString, dbUser, dbPass);

        return dbConnection;
    }

    public void singUpUser(String name, String surName, String age, String sex, String country, String login, String password) {

        String insert = "INSERT INTO " + Const.USER_TABLE + "(" + Const.NAME + "," + Const.SURNAME + ","
                + Const.AGE + "," + Const.SEX + "," + Const.COUNTRY + "," + Const.LOGIN + "," + Const.PASSWORD + ")" + "VALUES(?,?,?,?,?,?,?)";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);

            prSt.setString(1, name);
            prSt.setString(2, surName);
            prSt.setString(3, age);
            prSt.setString(4, sex);
            prSt.setString(5, country);
            prSt.setString(6, login);
            prSt.setString(7, password);

            prSt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getUser(User user) {
        ResultSet resultSet = null;

        String select = "SELECT * From " + Const.USER_TABLE + " WHERE " + Const.LOGIN + "=? AND " + Const.PASSWORD + "=?";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);

            prSt.setString(1, user.getLogin());
            prSt.setString(2, user.getPassword());

            resultSet = prSt.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public ResultSet getLogin(User user) {
        ResultSet resultSet = null;
        String loginCheck = "SELECT * FROM " + Const.USER_TABLE + " WHERE " + Const.LOGIN + "=?";

        try {
            PreparedStatement prSt1 = getDbConnection().prepareStatement(loginCheck);

            prSt1.setString(1, user.getLogin());

            resultSet = prSt1.executeQuery();

        }catch (SQLException e) {
            e.printStackTrace();
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

}
