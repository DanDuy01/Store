/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal.implement;

import context.DBContext;
import dal.interfaces.IDishDAO;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import models.Dish;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Image;

/**
 *
 * @author ThinkPro
 */
public class DishDAOImpl extends context.DBContext implements IDishDAO {

    Connection connection = null;
    DBContext dBContext = new DBContext();

    @Override
    public List<Dish> filterPaging(int index, int record_per_page, String searchKey, String categoryId, String type, String value) {
        List<Dish> list = new ArrayList<>();
        String query = "select * from Dish where category_id " + categoryId + " and name like N'%" + searchKey + "%' "
                + "order by " + value + " " + type + " offset ? rows fetch next ? rows only;";
        try {
            connection = dBContext.openConnection();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, (index - 1) * record_per_page);
            ps.setInt(2, record_per_page);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Dish d = new Dish();
                d.setId(rs.getInt("id"));
                d.setName(rs.getString("name"));
                d.setBrief_info(rs.getString("brief_info"));
                d.setDescription(rs.getString("description"));
                d.setPrice(rs.getInt("price"));
                d.setIs_available(rs.getBoolean("is_available"));
                d.setCategory_id(rs.getInt("category_id"));
                d.setImages(getImagesOfDish(rs.getInt("id")));

                list.add(d);
            }
        } catch (SQLException e) {
            Logger.getLogger(DishDAOImpl.class.getName()).log(Level.SEVERE, null, e);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DishDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dBContext.closeConnection(connection);
            } catch (SQLException e) {
            }
        }

        return list;
    }

    private List<Image> getImagesOfDish(int aInt) {
        List<Image> list = new ArrayList<>();
        String sql = "select * from DishImage di join Image i on di.image_id = i.id where di.dish_id=?";
        try {
            connection = dBContext.openConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, aInt);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Image i = new Image();
                i.setId(rs.getInt("id"));
                i.setUrl(rs.getString("url"));
                list.add(i);
            }
        } catch (SQLException e) {
            Logger.getLogger(DishDAOImpl.class.getName()).log(Level.SEVERE, null, e);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DishDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dBContext.closeConnection(connection);
            } catch (SQLException e) {
            }
        }
        return list;
    }

    @Override
    public int countDishByCondition(String searchKey, String categoryId) {
        try {
            // index: trang click
            connection = dBContext.openConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DishDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        String query = "select count(*) from Dish where category_id " + categoryId + "  and name like N'%" + searchKey + "%' ";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            Logger.getLogger(DishDAOImpl.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                dBContext.closeConnection(connection);
            } catch (SQLException e) {
            }
        }
        return -1;
    }

    @Override
    public List<Dish> getAll() {
        List<Dish> list = new ArrayList<>();
        String query = "select * from Dish ";
        try {
            connection = dBContext.openConnection();
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Dish d = new Dish();
                d.setId(rs.getInt("id"));
                d.setName(rs.getString("name"));
                d.setBrief_info(rs.getString("brief_info"));
                d.setDescription(rs.getString("description"));
                d.setPrice(rs.getInt("price"));
                d.setIs_available(rs.getBoolean("is_available"));
                d.setCategory_id(rs.getInt("category_id"));
                d.setImages(getImagesOfDish(rs.getInt("id")));

                list.add(d);
            }
        } catch (SQLException e) {
            Logger.getLogger(DishDAOImpl.class.getName()).log(Level.SEVERE, null, e);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DishDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dBContext.closeConnection(connection);
            } catch (SQLException e) {
            }
        }

        return list;
    }

}
