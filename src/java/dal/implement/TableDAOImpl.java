/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal.implement;

import dal.interfaces.ITableDAO;
import models.DiningTable;
import context.DBContext;
import java.sql.Connection;
import models.Category;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TableDAOImpl extends DBContext implements ITableDAO {

    Connection connection = null;
    DBContext dBContext = new DBContext();

    @Override
    public List<DiningTable> getAvalableTables() {
        List<DiningTable> list = new ArrayList();
        try {
            connection = dBContext.openConnection();
            String sql = "SELECT dt.*\n"
                    + "FROM DiningTable dt\n"
                    + "LEFT JOIN [Order] o ON dt.id = o.table_id\n"
                    + "WHERE o.table_id IS NULL;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new DiningTable(rs.getInt("id"), rs.getString("name"), rs.getString("capacity")));
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dBContext.closeConnection(connection);
            } catch (SQLException e) {
                Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return list;
    }

    @Override
    public DiningTable getTableById(int aInt) {
        try {
            connection = dBContext.openConnection();
            String sql = "SELECT * FROM DiningTable  where id=? ";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, aInt);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new DiningTable(rs.getInt("id"), rs.getString("name"), rs.getString("capacity"));
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dBContext.closeConnection(connection);
            } catch (SQLException e) {
                Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return null;
    }

}