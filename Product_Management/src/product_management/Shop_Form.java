/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package product_management;

import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author kazuto
 */
public class Shop_Form extends javax.swing.JFrame {

    /**
     * Creates new form Shop_Form
     */
    public Shop_Form() {
        initComponents();
        // Khởi tạo form giữa màn hình
        this.setLocationRelativeTo(null);
        // Mở khóa button
        setButton(true);
        // Show dữ liệu lên JTable
        Show_Shop_In_JTable();
    }
    String ImgPath = null;
    int pos = 0;
    
    // Hàm kiểm tra giá trị của các textbox
    public boolean checkInputs()
    {
        if(
              txt_name.getText().equals("")
           || txt_phone.getText().equals("")
           || txt_address.getText().equals("")
           || txt_AddDate.getDate().equals("")
          ){
            return false;
        }
        else{
            return true;
        }
    }
    //Hàm thay đổi kích thước ảnh cho vừa với JLabel
    public ImageIcon ResizeImage(String imagePath, byte[] pic)
    {
        ImageIcon myImage = null;
        
        if(imagePath != null)
        {
            myImage = new ImageIcon(imagePath);
        }else{
            myImage = new ImageIcon(pic);
        }
        
        Image img = myImage.getImage();
        Image img2 = img.getScaledInstance(lbl_image.getWidth(), lbl_image.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(img2);
        return image;
        
    }
    
    //Hiển thị dữ liệu lên JTable
    // 1 -  Đổ Data vào mảng Product
    public ArrayList<Shop> getShopList()
    {
            ArrayList<Shop> shopList  = new ArrayList<Shop>();
            Connection con = My_CNX.getConnection();
            String query = "SELECT * FROM shop";
            
            Statement st;
            ResultSet rs;
            
        try {
            
            st = con.createStatement();
            rs = st.executeQuery(query);
            Shop shop;
            
            while(rs.next())
            {
                shop = new Shop(rs.getInt("shopid"),rs.getString("name"),rs.getString("address"),rs.getString("phone"),rs.getString("add_date"),rs.getBytes("image"));
                shopList.add(shop);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Main_Window.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return shopList; 
                
    }
    //      2 - Đưa dlsp lên JTable
    
    public void Show_Shop_In_JTable()
    {
        ArrayList<Shop> list = getShopList();
        DefaultTableModel model = (DefaultTableModel)JTable_Shop.getModel();
        // clear jtable content
       
        model.setRowCount(0);
        Object[] row = new Object[5];
        for(int i = 0; i < list.size(); i++)
        {
            row[0] = list.get(i).getShopid();
            row[1] = list.get(i).getName();
            row[2] = list.get(i).getAddress();
            row[3] = list.get(i).getPhone();
            row[4] = list.get(i).getAddDate();
            
            model.addRow(row);
        }
    
    }
    
    //Hàm xóa các TextField
    public void setNull()
    {
        this.txt_id.setText(null);
        this.txt_name.setText(null);
        this.txt_address.setText(null);
        this.txt_phone.setText(null);
        this.lbl_image.setIcon(null);
        this.txt_name.requestFocus();
    }
    
    //Hàm khóa các TextField
    public void setKhoa(boolean a)
    {
        //Khóa hoặc mở khóa cho các JTextField
        this.txt_name.setEnabled(!a);
        this.txt_address.setEnabled(!a);
        this.txt_phone.setEnabled(!a);
        this.txt_AddDate.setEnabled(!a);
        this.Btn_Choose_Image.setEnabled(!a);
    }
    
    //Hàm khóa các Button
    public void setButton(boolean a)
    {
        //Vô hiệu hoặc có hiệu lực cho các JButton
        this.Btn_Insert.setEnabled(a);
        this.Btn_Delete.setEnabled(a);
        this.Btn_Update.setEnabled(a);
        this.Btn_Save.setEnabled(!a);
        
    }
    
    // hiện dữ liệu trên input
    public void ShowItem(int index)
    {
            txt_id.setText(Integer.toString(getShopList().get(index).getShopid()));
            txt_name.setText(getShopList().get(index).getName());
            txt_address.setText(getShopList().get(index).getAddress());
            txt_phone.setText(getShopList().get(index).getPhone());
            
            
        try {
           Date addDate = null;
            addDate = new SimpleDateFormat("dd-MM-yyyy").parse((String)getShopList().get(index).getAddDate());
            txt_AddDate.setDate(addDate);
        } catch (ParseException ex) {
            Logger.getLogger(Main_Window.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        lbl_image.setIcon(ResizeImage(null, getShopList().get(index).getPicture()));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        BtnMenu = new javax.swing.JButton();
        txt_id = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txt_name = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txt_AddDate = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lbl_image = new javax.swing.JLabel();
        Btn_Choose_Image = new javax.swing.JButton();
        Btn_Insert = new javax.swing.JButton();
        Btn_Update = new javax.swing.JButton();
        Btn_Delete = new javax.swing.JButton();
        Btn_Save = new javax.swing.JButton();
        BtnRefresh = new javax.swing.JButton();
        Btn_First = new javax.swing.JButton();
        Btn_Last = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTable_Shop = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        txt_address = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txt_phone = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));
        jPanel1.setForeground(new java.awt.Color(102, 102, 102));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 3, 36)); // NOI18N
        jLabel6.setText("Danh sách Shop");

        BtnMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/menu.png"))); // NOI18N
        BtnMenu.setToolTipText("Về trang chủ");
        BtnMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnMenuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addGap(348, 348, 348)
                .addComponent(BtnMenu)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(BtnMenu))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        txt_id.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N
        txt_id.setEnabled(false);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setText("ID:");

        txt_name.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel2.setText("Tên:");

        txt_AddDate.setDateFormatString("dd-MM-yyyy");
        txt_AddDate.setFont(new java.awt.Font("Times New Roman", 2, 24)); // NOI18N
        txt_AddDate.setName("txt_AddDate"); // NOI18N

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel4.setText("Ngày:");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel5.setText("Hình:");

        lbl_image.setOpaque(true);

        Btn_Choose_Image.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        Btn_Choose_Image.setText("Chọn hình");
        Btn_Choose_Image.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_Choose_ImageActionPerformed(evt);
            }
        });

        Btn_Insert.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        Btn_Insert.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/add.png"))); // NOI18N
        Btn_Insert.setText("Thêm");
        Btn_Insert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_InsertActionPerformed(evt);
            }
        });

        Btn_Update.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        Btn_Update.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/refresh.png"))); // NOI18N
        Btn_Update.setText("Cập nhật");
        Btn_Update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_UpdateActionPerformed(evt);
            }
        });

        Btn_Delete.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        Btn_Delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/delete.png"))); // NOI18N
        Btn_Delete.setText("Xóa");
        Btn_Delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_DeleteActionPerformed(evt);
            }
        });

        Btn_Save.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        Btn_Save.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/save-file-option.png"))); // NOI18N
        Btn_Save.setText("Lưu");
        Btn_Save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_SaveActionPerformed(evt);
            }
        });

        BtnRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/refresh (1).png"))); // NOI18N
        BtnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnRefreshActionPerformed(evt);
            }
        });

        Btn_First.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        Btn_First.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/left-arrow-outline-to-first-track.png"))); // NOI18N
        Btn_First.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_FirstActionPerformed(evt);
            }
        });

        Btn_Last.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        Btn_Last.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/last-track-outlined-button.png"))); // NOI18N
        Btn_Last.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_LastActionPerformed(evt);
            }
        });

        JTable_Shop.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Tên", "Địa chỉ", "Số điện thoại", "Ngày thêm"
            }
        ));
        JTable_Shop.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTable_ShopMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(JTable_Shop);

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel3.setText("Địa chỉ:");

        txt_address.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel7.setText("Số điện thoại:");

        txt_phone.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(107, 107, 107)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(94, 94, 94)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(jLabel3))
                    .addComponent(jLabel7)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4))))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lbl_image, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txt_id)
                        .addComponent(txt_name)
                        .addComponent(txt_address)
                        .addComponent(txt_phone)
                        .addComponent(txt_AddDate, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE))
                    .addComponent(Btn_Choose_Image, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 619, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(Btn_Insert)
                .addGap(9, 9, 9)
                .addComponent(Btn_Update)
                .addGap(9, 9, 9)
                .addComponent(Btn_Delete)
                .addGap(9, 9, 9)
                .addComponent(Btn_Save)
                .addGap(9, 9, 9)
                .addComponent(BtnRefresh)
                .addGap(354, 354, 354)
                .addComponent(Btn_First, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(Btn_Last, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 15, Short.MAX_VALUE))
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel1)
                        .addGap(26, 26, 26)
                        .addComponent(jLabel2)
                        .addGap(26, 26, 26)
                        .addComponent(jLabel3)
                        .addGap(26, 26, 26)
                        .addComponent(jLabel7)
                        .addGap(25, 25, 25)
                        .addComponent(jLabel4)
                        .addGap(83, 83, 83)
                        .addComponent(jLabel5))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)
                        .addComponent(txt_name, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)
                        .addComponent(txt_address, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)
                        .addComponent(txt_phone, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)
                        .addComponent(txt_AddDate, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)
                        .addComponent(lbl_image, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Btn_Choose_Image, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 519, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Btn_Insert, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Btn_Update, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Btn_Delete, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Btn_Save, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Btn_First)
                    .addComponent(Btn_Last))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnMenuActionPerformed
        // TODO add your handling code here:
        Menu_Form form = new Menu_Form();
        form.setVisible(true);
        form.pack();
        form.setLocationRelativeTo(null);
        // Đóng form hiện tại (Main_Window) mở form Menu (Menu_Form)
        this.dispose();
    }//GEN-LAST:event_BtnMenuActionPerformed

    private void Btn_Choose_ImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_Choose_ImageActionPerformed
        // TODO add your handling code here:
        JFileChooser file = new JFileChooser();
        file.setCurrentDirectory(new File(System.getProperty("user.home")));

        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.images", "jpg","png");
        file.addChoosableFileFilter(filter);
        int result = file.showSaveDialog(null);
        if(result == JFileChooser.APPROVE_OPTION)
        {
            File selectedFile = file.getSelectedFile();
            String path = selectedFile.getAbsolutePath();
            lbl_image.setIcon(ResizeImage(path, null));
            ImgPath = path;
        }
        else{
            System.out.println("Không có ảnh được chọn");
        }
    }//GEN-LAST:event_Btn_Choose_ImageActionPerformed

    private void Btn_InsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_InsertActionPerformed
        // TODO add your handling code here:
        //Xóa TextField
        setNull();
        //Khóa các button không liên quan
        setButton(false);
    }//GEN-LAST:event_Btn_InsertActionPerformed

    private void Btn_UpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_UpdateActionPerformed
        // TODO add your handling code here:
        if(checkInputs() && txt_id.getText() != null)
        {
            String UpdateQuery = null;
            PreparedStatement ps = null;
            Connection con = My_CNX.getConnection();

            //update mà không cần image
            if(ImgPath == null)
            {
                try {
                    UpdateQuery = "UPDATE shop SET name = ?, address = ?, phone = ?"
                    + ", add_date = ? WHERE shopid = ?";
                    ps = con.prepareStatement(UpdateQuery);

                    ps.setString(1, txt_name.getText());
                    ps.setString(2, txt_address.getText());
                    ps.setString(3, txt_phone.getText());

                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                    String addDate = dateFormat.format(txt_AddDate.getDate());

                    ps.setString(4, addDate);

                    ps.setInt(5, Integer.parseInt(txt_id.getText()));

                    ps.executeUpdate();
                    Show_Shop_In_JTable();
                    JOptionPane.showMessageDialog(null, "Đã cập nhật Shop");

                } catch (SQLException ex) {
                    Logger.getLogger(Main_Window.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            // update có image
            else{
                try{
                    InputStream img = new FileInputStream(new File(ImgPath));

                   UpdateQuery = "UPDATE shop SET name = ?, address = ?, phone = ?"
                    + ", add_date = ?, image = ? WHERE shopid = ?";

                    ps = con.prepareStatement(UpdateQuery);

                    ps.setString(1, txt_name.getText());
                    ps.setString(2, txt_address.getText());
                    ps.setString(3, txt_phone.getText());

                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                    String addDate = dateFormat.format(txt_AddDate.getDate());

                    ps.setString(4, addDate);

                    ps.setBlob(5, img);

                    ps.setInt(6, Integer.parseInt(txt_id.getText()));

                    ps.executeUpdate();
                    Show_Shop_In_JTable();
                    JOptionPane.showMessageDialog(null, "Đã cập nhật Shop");

                }catch(Exception ex)
                {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        }else{
            JOptionPane.showMessageDialog(null, "Dữ liệu còn thiếu, vui lòng nhập thêm");
        }
    }//GEN-LAST:event_Btn_UpdateActionPerformed

    private void Btn_DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_DeleteActionPerformed
        // TODO add your handling code here:
        String ten = txt_name.getText();
        if(!txt_id.getText().equals(""))
        {
            try {
                if (JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa Shop "+ten+" không?","Thông báo", 2)==0){
                    Connection con = My_CNX.getConnection();
                    PreparedStatement ps = con.prepareStatement("DELETE FROM shop WHERE shopid = ?");
                    int id = Integer.parseInt(txt_id.getText());
                    ps.setInt(1, id);
                    ps.executeUpdate();
                    Show_Shop_In_JTable();
                    //Xóa dữ liệu trong TextField
                    setNull();
                }

            } catch (SQLException ex) {
                Logger.getLogger(Main_Window.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Xóa thất bại");
            }

        }else{
            JOptionPane.showMessageDialog(null, "Chọn 1 Shop để xóa","Thông báo", 1);
        }
    }//GEN-LAST:event_Btn_DeleteActionPerformed

    private void Btn_SaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_SaveActionPerformed
        // TODO add your handling code here:
        if(checkInputs() && ImgPath != null)
        {
            try {
                Connection con = My_CNX.getConnection();
                PreparedStatement ps = con.prepareStatement("INSERT INTO shop(name,address,phone,add_date,image)" + " values(?,?,?,?,?) ");
                
                ps.setString(1, txt_name.getText());
                ps.setString(2, txt_address.getText());
                ps.setString(3, txt_phone.getText());

                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                String addDate = dateFormat.format(txt_AddDate.getDate());
                ps.setString(4, addDate);

                InputStream img = new FileInputStream(new File(ImgPath));
                ps.setBlob(5, img);
                ps.executeUpdate();
                setNull();
                setButton(true);
                Show_Shop_In_JTable();

                JOptionPane.showMessageDialog(null, "Dữ liệu đã được thêm");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }else{
            JOptionPane.showMessageDialog(null, "Dữ liệu còn thiếu, vui lòng nhập thêm");
        }

        // test
        System.out.println("Name => "+txt_name.getText());
        System.out.println("Address => "+txt_address.getText());
        System.out.println("Phone => "+txt_phone.getText());
        System.out.println("Image => "+ImgPath);
    }//GEN-LAST:event_Btn_SaveActionPerformed

    private void BtnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRefreshActionPerformed
        // TODO add your handling code here:
        Main_Window form = new Main_Window();
        form.setVisible(true);
        form.pack();
        form.setLocationRelativeTo(null);
        // Đóng form hiện tại (Main_Window) mở form Menu (Menu_Form)
        this.dispose();
    }//GEN-LAST:event_BtnRefreshActionPerformed

    private void Btn_FirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_FirstActionPerformed
        // TODO add your handling code here:
        pos = 0;
        ShowItem(pos);
    }//GEN-LAST:event_Btn_FirstActionPerformed

    private void Btn_LastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_LastActionPerformed
        // TODO add your handling code here:
        pos = getShopList().size()-1;
        ShowItem(pos);
    }//GEN-LAST:event_Btn_LastActionPerformed

    private void JTable_ShopMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTable_ShopMouseClicked
        // TODO add your handling code here:
        setButton(true);
        int index = JTable_Shop.getSelectedRow();
        ShowItem(index);
    }//GEN-LAST:event_JTable_ShopMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Shop_Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Shop_Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Shop_Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Shop_Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Shop_Form().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnMenu;
    private javax.swing.JButton BtnRefresh;
    private javax.swing.JButton Btn_Choose_Image;
    private javax.swing.JButton Btn_Delete;
    private javax.swing.JButton Btn_First;
    private javax.swing.JButton Btn_Insert;
    private javax.swing.JButton Btn_Last;
    private javax.swing.JButton Btn_Save;
    private javax.swing.JButton Btn_Update;
    private javax.swing.JTable JTable_Shop;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_image;
    private com.toedter.calendar.JDateChooser txt_AddDate;
    private javax.swing.JTextField txt_address;
    private javax.swing.JTextField txt_id;
    private javax.swing.JTextField txt_name;
    private javax.swing.JTextField txt_phone;
    // End of variables declaration//GEN-END:variables
}
