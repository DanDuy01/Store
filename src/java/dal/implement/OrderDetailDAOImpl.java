/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal.implement;

import context.DBContext;
import dal.interfaces.IOrderDetailDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Cart;
import models.OrderDetail;

public class OrderDetailDAOImpl extends DBContext implements IOrderDetailDAO {

    Connection connection = null;
    DBContext dBContext = new DBContext();

    @Override
    public void addCartToOrder(List<Cart> listCart, int order_id) {
        try {
            connection = dBContext.openConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(OrderDetailDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            for (Cart cart : listCart) {
                String sql = "INSERT INTO [dbo].[OrderDetail] values (?,?,?,?)";
                PreparedStatement st = connection.prepareStatement(sql);

                st.setInt(1, order_id);
                st.setInt(2, cart.getDish_id());
                st.setInt(3, cart.getQuantity());
                st.setInt(4, cart.getTotal_cost());
                st.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDetailDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dBContext.closeConnection(connection);
            } catch (SQLException e) {
            }
        }
    }

    @Override
    public List<OrderDetail> getOrderDetailByOrder(int orderId) {

        try {
            connection = dBContext.openConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(OrderDetailDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        List<OrderDetail> list = new ArrayList<>();
        try {
            String sql = "select * from OrderDetail where order_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, orderId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                OrderDetail od = new OrderDetail();
                od.setOrder_id(rs.getInt(1));
                od.setDish_id(rs.getInt(2));
                od.setQuantity(rs.getInt(3));
                od.setPrice(rs.getInt(4));
                list.add(od);
            }
        } catch (SQLException ex) {
            try {
                dBContext.closeConnection(connection);
            } catch (SQLException e) {
            }
        }
        return list;
    }

}
