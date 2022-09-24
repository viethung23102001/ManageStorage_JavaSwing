/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.QuanLyKhoModel;
import Model.Danhmuchang;
import View.DanhMucHangView;
import View.NguoiDungView;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Action;
import javax.swing.JOptionPane;

/**
 *
 * @author H
 */
public class DanhMucHangController implements Action {

    private DanhMucHangView view;
    private QuanLyKhoModel model;

    public DanhMucHangController(DanhMucHangView view) throws ClassNotFoundException, SQLException {
        this.view = view;
        model = new QuanLyKhoModel();
    }

    @Override
    public Object getValue(String key) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void putValue(String key, Object value) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setEnabled(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean isEnabled() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String cmd = e.getActionCommand();
            switch (cmd) {
                case "Thêm":
                    if (Danhmuchang.isIdExist(view.getTextTfMa())) {
                        JOptionPane.showMessageDialog(view, "Mã này đã tồn tại!!");
                    } else {
                        Danhmuchang.InsertDB(view.getTextTfMa(), view.getTextTfTen());
                        JOptionPane.showMessageDialog(view, "Thêm thành công");
                    }
                    view.loadJTable();
                    break;
                case "Sửa":
                    if (view.getIndexTbl() < 0) {
                        JOptionPane.showMessageDialog(view, "Bạn chưa chỉ định dữ liệu để thao tác!!");
                    } else {
                        try {
                            Danhmuchang.UpdateDB(view.getTextSelectedId(), view.getTextTfMa(), view.getTextTfTen());
                            JOptionPane.showMessageDialog(view, "Sửa thành công");
                        } catch (SQLException ex) {
                            JOptionPane.showMessageDialog(view, "Mã này đã tồn tại!!");
                        }
                        view.loadJTable();
                    }
                    break;
                case "Xoá":
                    if (view.getIndexTbl() < 0) {
                        JOptionPane.showMessageDialog(view, "Bạn chưa chỉ định dữ liệu để thao tác!!");
                    } else {
                        if (JOptionPane.showConfirmDialog(view, "Bạn có chắc muốn xoá không?") == 0) {
                            Danhmuchang.DeleteDB(view.getTextSelectedId());
                            JOptionPane.showMessageDialog(view, "Xoá thành công");
                            view.loadJTable();
                        }                      
                    }
                    break;
                case "Tìm":
                    Danhmuchang res = Danhmuchang.FindByID(view.getTextTfIdCanTim());
                    view.setJTable(res);
                    break;
                case "Làm mới":
                    view.loadJTable();
                    view.clear();
                    break;
                default:
                    break;
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(NguoiDungController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, "Thao tác không hợp lệ bởi một trong lý do sau: \n - Trường dữ liệu trống\n - Dữ liệu bị trùng\n - Dữ liệu đang được sử dụng");
        }
    }

}
