/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal.implement;

import context.DBContext;
import dal.interfaces.IOrderDAO;
import dal.interfaces.ITableDAO;
import java.sql.Connection;
import models.Order;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.DiningTable;

public class OrderDAOImpl extends context.DBContext implements IOrderDAO {

    Connection connection = null;
    DBContext dBContext = new DBContext();

    @Override
    public Order checkDishOrderByUser(int userId, int product_id) {
        try {
            connection = dBContext.openConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(OrderDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        String sql = "select * from [Order] o join [OrderDetail] od on o.id = od.order_id \n"
                + "where o.customer_id = ? and od.dish_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, userId);
            st.setInt(2, product_id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Order c = new Order();
                c.setId(rs.getInt(1));
                return c;
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dBContext.closeConnection(connection);
            } catch (SQLException e) {
            }
        }
        return null;
    }

    @Override
    public int createNewOrder(int sum, String fullname, String email, String phone, String address, int user_id, String note, String cod, int table_id) {
        try {
            connection = dBContext.openConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(OrderDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql = "INSERT INTO [dbo].[Order]\n"
                + "           (order_datetime \n"
                + "           ,[total_cost]\n"
                + "           ,[customer_name]\n"
                + "           ,[email]\n"
                + "           ,[phone]\n"
                + "           ,[address]\n"
                + "           ,[customer_id]\n"
                + "           ,[note], payment_method, table_id, status)\n"
                + "     VALUES\n"
                + "           (GETDATE(),?,?,?,?,?,?,?,?,?, 'pending') ";

        try {
            PreparedStatement st = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setInt(1, sum);
            st.setString(2, fullname);
            st.setString(3, email);
            st.setString(4, phone);
            st.setString(5, address);
            st.setInt(6, user_id);
            st.setString(7, note);
            st.setString(8, cod);
            st.setInt(9, table_id);

            st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dBContext.closeConnection(connection);
            } catch (SQLException e) {
            }
        }
        return 0;
    }

    @Override
    public Order getCurrentOrder(int userId, String afalse) {
        try {
            connection = dBContext.openConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(OrderDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        String sql = "select * from [Order] "
                + "where customer_id = ? and  (status = 'pending' or status = 'success') ";

        if (afalse == null || afalse.equals("true")) {
            sql += " and table_id = -1";
        } else {
            sql += " and table_id <> -1";
        }
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, userId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Order c = new Order();
                c.setId(rs.getInt(1));
                if (rs.getInt(4) != -1) {
                    ITableDAO td = new TableDAOImpl();
                    DiningTable t = td.getTableById(rs.getInt(4));
                    c.setTable(t);
                }
                return c;
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dBContext.closeConnection(connection);
            } catch (SQLException e) {
            }
        }
        return null;
    }

}
