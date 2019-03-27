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
import java.sql.DriverManager;
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
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import java.lang.Object;
import java.util.ResourceBundle;
import java.nio.charset.Charset;
import static java.nio.charset.StandardCharsets.ISO_8859_1;
import static java.nio.charset.StandardCharsets.UTF_8;
/**
 *
 * @author kazuto
 */
public class Main_Window extends javax.swing.JFrame {

    /**
     * Creates new form Main_Window
     */
    public Main_Window() {
        initComponents();
        // center form
        this.setLocationRelativeTo(null);
        setButton(true);
        Show_Products_In_JTable();
    }
    
    String ImgPath = null;
    int pos = 0;

    // hàm kết nối với MySQL Database 
    public Connection getConnection()
    {
        Connection con = null;
        
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/java_db","root","");
            return con;
        } catch (SQLException ex) {
            Logger.getLogger(Main_Window.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
     // Hàm kiểm tra giá trị của các textbox
    public boolean checkInputs()
    {
        if(
              txt_name.getText() == null
           || txt_price.getText() == null
           || txt_AddDate.getDate() == null
          ){
            return false;
        }
        else{
            try{
                Float.parseFloat(txt_price.getText());
                return true;
            }catch(Exception ex)
            {
                return false;
            }
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
    public ArrayList<Product> getProductList()
    {
            ArrayList<Product> productList  = new ArrayList<Product>();
            Connection con = getConnection();
            String query = "SELECT * FROM products";
            
            Statement st;
            ResultSet rs;
            
        try {
            
            st = con.createStatement();
            rs = st.executeQuery(query);
            Product product;
            
            while(rs.next())
            {
                product = new Product(rs.getInt("id"),rs.getString("name"),Float.parseFloat(rs.getString("price")),rs.getString("add_date"),rs.getBytes("image"));
                productList.add(product);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Main_Window.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return productList; 
                
    }
    //      2 - Đưa dlsp lên JTable
    
    public void Show_Products_In_JTable()
    {
        ArrayList<Product> list = getProductList();
        DefaultTableModel model = (DefaultTableModel)JTable_Products.getModel();
        // clear jtable content
       
        model.setRowCount(0);
        Object[] row = new Object[4];
        for(int i = 0; i < list.size(); i++)
        {
            row[0] = list.get(i).getId();
            row[1] = list.get(i).getName();
//            byte[] ptext = list.get(i).getName().getBytes(ISO_8859_1); 
//            row[1] = new String(ptext, UTF_8); 
            row[2] = list.get(i).getPrice();
            row[3] = list.get(i).getAddDate();
            
            model.addRow(row);
        }
    
    }
    
    //Hàm xóa các TextField
    public void setNull()
    {
        this.txt_id.setText(null);
        this.txt_name.setText(null);
        this.txt_price.setText(null);
        this.lbl_image.setIcon(null);
        this.txt_name.requestFocus();
    }
    
    //Hàm khóa các TextField
    public void setKhoa(boolean a)
    {
        //Khóa hoặc mở khóa cho các JTextField
        this.txt_name.setEnabled(!a);
        this.txt_price.setEnabled(!a);
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
            txt_id.setText(Integer.toString(getProductList().get(index).getId()));
            txt_name.setText(getProductList().get(index).getName());
            txt_price.setText(Float.toString(getProductList().get(index).getPrice()));
            
        try {
           Date addDate = null;
            addDate = new SimpleDateFormat("dd-MM-yyyy").parse((String)getProductList().get(index).getAddDate());
            txt_AddDate.setDate(addDate);
        } catch (ParseException ex) {
            Logger.getLogger(Main_Window.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        lbl_image.setIcon(ResizeImage(null, getProductList().get(index).getImage()));
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_id = new javax.swing.JTextField();
        txt_name = new javax.swing.JTextField();
        txt_price = new javax.swing.JTextField();
        txt_AddDate = new com.toedter.calendar.JDateChooser();
        lbl_image = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTable_Products = new javax.swing.JTable();
        Btn_Choose_Image = new javax.swing.JButton();
        Btn_Insert = new javax.swing.JButton();
        Btn_Update = new javax.swing.JButton();
        Btn_Delete = new javax.swing.JButton();
        Btn_First = new javax.swing.JButton();
        Btn_Last = new javax.swing.JButton();
        BtnRefresh = new javax.swing.JButton();
        Btn_Save = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        BtnMenu = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 204));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setText("ID:");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel2.setText("Tên:");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel3.setText("Giá:");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel4.setText("Ngày:");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel5.setText("Hình:");

        txt_id.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N
        txt_id.setEnabled(false);

        txt_name.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N

        txt_price.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N

        txt_AddDate.setDateFormatString("dd-MM-yyyy");
        txt_AddDate.setFont(new java.awt.Font("Times New Roman", 2, 24)); // NOI18N
        txt_AddDate.setName("txt_AddDate"); // NOI18N

        lbl_image.setOpaque(true);

        JTable_Products.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Tên", "Giá (triệu VNĐ)", "Ngày thêm"
            }
        ));
        JTable_Products.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTable_ProductsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(JTable_Products);

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

        BtnRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/refresh (1).png"))); // NOI18N
        BtnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnRefreshActionPerformed(evt);
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

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));
        jPanel1.setForeground(new java.awt.Color(102, 102, 102));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 3, 36)); // NOI18N
        jLabel6.setText("Danh sách thú cưng");

        BtnMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/menu.png"))); // NOI18N
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
                .addGap(292, 292, 292)
                .addComponent(BtnMenu))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(BtnMenu)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(Btn_Insert)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Btn_Update)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Btn_Delete)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Btn_Save)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Btn_Choose_Image, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt_id)
                            .addComponent(txt_name, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
                            .addComponent(txt_price, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
                            .addComponent(txt_AddDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbl_image, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 21, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 619, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(414, 414, 414)
                        .addComponent(BtnRefresh)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Btn_First, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Btn_Last, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txt_name, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txt_price, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(txt_AddDate, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addComponent(jLabel5))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(lbl_image, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Btn_Choose_Image, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 469, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Btn_Last)
                            .addComponent(Btn_First)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Btn_Insert, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Btn_Update, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Btn_Delete, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Btn_Save, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BtnRefresh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // nút để chọn hình ảnh
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

    
   
    // nút truyền dữ liệu vào db
    // kiểm tra ảnh vs các text nhập vào có null không
    // truyền dữ liệu
    private void Btn_InsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_InsertActionPerformed
        // TODO add your handling code here:
       
      if(checkInputs() && ImgPath != null)
        {
            try {
                 Connection con = getConnection();
                PreparedStatement ps = con.prepareStatement("INSERT INTO products(name,price,add_date,image)"
                        + "values(?,?,?,?) ");
                ps.setString(1, txt_name.getText());
                ps.setString(2, txt_price.getText());
               
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String addDate = dateFormat.format(txt_AddDate.getDate());
                ps.setString(3, addDate);
               
                InputStream img = new FileInputStream(new File(ImgPath));
                ps.setBlob(4, img);
                ps.executeUpdate();
                Show_Products_In_JTable();
               
                JOptionPane.showMessageDialog(null, "Data Inserted");
            } catch (Exception ex) {
                 JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }else{
            JOptionPane.showMessageDialog(null, "One Or More Field Are Empty");
    }//GEN-LAST:event_Btn_InsertActionPerformed
    }
    
    //nút update data
    //kiểm tra  xem text có null không
    //nếu không có image thì không update image 
    private void Btn_UpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_UpdateActionPerformed
        // TODO add your handling code here:
        if(checkInputs() && txt_id.getText() != null)
        {
            String UpdateQuery = null;
            PreparedStatement ps = null;
            Connection con = getConnection();
           
            //update mà không cần image
            if(ImgPath == null)
            {
                try {
                    UpdateQuery = "UPDATE products SET name = ?, price = ?"
                            + ", add_date = ? WHERE id = ?";
                    ps = con.prepareStatement(UpdateQuery);
                   
                    ps.setString(1, txt_name.getText());
                    ps.setString(2, txt_price.getText());
                   
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                    String addDate = dateFormat.format(txt_AddDate.getDate());
                   
                    ps.setString(3, addDate);
                   
                    ps.setInt(4, Integer.parseInt(txt_id.getText()));
                   
                    ps.executeUpdate();
                    Show_Products_In_JTable();
                    JOptionPane.showMessageDialog(null, "Đã cập nhật sản phẩm");
                   
                } catch (SQLException ex) {
                    Logger.getLogger(Main_Window.class.getName()).log(Level.SEVERE, null, ex);
                }
               
            }
            // update có image
            else{
                try{
                InputStream img = new FileInputStream(new File(ImgPath));
               
                 UpdateQuery = "UPDATE products SET name = ?, price = ?"
                            + ", add_date = ?, image = ? WHERE id = ?";
               
                  ps = con.prepareStatement(UpdateQuery);
                   
                    ps.setString(1, txt_name.getText());
                    ps.setString(2, txt_price.getText());
                   
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                    String addDate = dateFormat.format(txt_AddDate.getDate());
                   
                    ps.setString(3, addDate);
                   
                    ps.setBlob(4, img);
                   
                    ps.setInt(5, Integer.parseInt(txt_id.getText()));
                   
                    ps.executeUpdate();
                    Show_Products_In_JTable();
                    JOptionPane.showMessageDialog(null, "Đã cập nhật sản phẩm");
               
                }catch(Exception ex)
                {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        }else{
            JOptionPane.showMessageDialog(null, "Dữ liệu còn thiếu, vui lòng nhập thêm");
        }
    }//GEN-LAST:event_Btn_UpdateActionPerformed
 
    
    // Button Delete The Data From MySQL Database   
    private void Btn_DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_DeleteActionPerformed
        // TODO add your handling code here:
        String ten = txt_name.getText();
         if(!txt_id.getText().equals(""))
        {
            try {
                if (JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa sản phẩm "+ten+" không?","Thông báo", 2)==0){
                     Connection con = getConnection();
                PreparedStatement ps = con.prepareStatement("DELETE FROM products WHERE id = ?");
                int id = Integer.parseInt(txt_id.getText());
                ps.setInt(1, id);
                ps.executeUpdate();
                Show_Products_In_JTable();
                //Xóa dữ liệu trong TextField
                setNull();
                }
               
            } catch (SQLException ex) {
                Logger.getLogger(Main_Window.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Xóa thất bại");
            }
         
        }else{
            JOptionPane.showMessageDialog(null, "Chọn 1 sản phẩm để xóa","Thông báo", 1);
        }
    }//GEN-LAST:event_Btn_DeleteActionPerformed

    
    // JTable Mouse Clicked
    // Display The Selected Row Data Into JTextFields
    // And The Image Into JLabel
    private void JTable_ProductsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTable_ProductsMouseClicked
        // TODO add your handling code here:
        setButton(true);
        int index = JTable_Products.getSelectedRow();
        ShowItem(index);
    }//GEN-LAST:event_JTable_ProductsMouseClicked

     // Button First Show The First Record
    private void Btn_FirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_FirstActionPerformed
        // TODO add your handling code here:
         pos = 0;
        ShowItem(pos);
    }//GEN-LAST:event_Btn_FirstActionPerformed

    
    // Button Last Show The Last Record
    private void Btn_LastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_LastActionPerformed
        // TODO add your handling code here:
        pos = getProductList().size()-1;
        ShowItem(pos);
    }//GEN-LAST:event_Btn_LastActionPerformed

    private void BtnMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnMenuActionPerformed
        // TODO add your handling code here:
        Menu_Form form = new Menu_Form();
        form.setVisible(true);
        form.pack();
        form.setLocationRelativeTo(null);
        // Đóng form hiện tại (Main_Window) mở form Menu (Menu_Form)
        this.dispose();
    }//GEN-LAST:event_BtnMenuActionPerformed

    private void BtnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRefreshActionPerformed
        // TODO add your handling code here:
        Main_Window form = new Main_Window();
        form.setVisible(true);
        form.pack();
        form.setLocationRelativeTo(null);
        // Đóng form hiện tại (Main_Window) mở form Menu (Menu_Form)
        this.dispose();
    }//GEN-LAST:event_BtnRefreshActionPerformed

    private void Btn_SaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_SaveActionPerformed
        // TODO add your handling code here:
        if(checkInputs() && ImgPath != null)
        {
            try {
                Connection con = getConnection();
//                String query = "INSERT INTO products(name,price,add_date,image) values(N'"+ten+"','"+gia+"','"+addDate+"','"+img+"')" ;
                PreparedStatement ps = con.prepareStatement("INSERT INTO products(name,price,add_date,image)" + " values(?,?,?,?) ");
                //PreparedStatement ps = con.prepareStatement(query);
                ps.setString(1, txt_name.getText());
                ps.setString(2, txt_price.getText());
               
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                String addDate = dateFormat.format(txt_AddDate.getDate());
                ps.setString(3, addDate);
               
                InputStream img = new FileInputStream(new File(ImgPath));
                ps.setBlob(4, img);
                ps.executeUpdate();
                setNull();
                setButton(true);
                Show_Products_In_JTable();
               
                JOptionPane.showMessageDialog(null, "Dữ liệu đã được thêm");
            } catch (Exception ex) {
                 JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }else{
            JOptionPane.showMessageDialog(null, "Dữ liệu còn thiếu, vui lòng nhập thêm");
        }
       
        // only for test
        System.out.println("Name => "+txt_name.getText());
        System.out.println("Price => "+txt_price.getText());
        System.out.println("Image => "+ImgPath);
    }//GEN-LAST:event_Btn_SaveActionPerformed
                                       
                                   
                               
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
            java.util.logging.Logger.getLogger(Main_Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main_Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main_Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main_Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main_Window().setVisible(true);
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
    private javax.swing.JTable JTable_Products;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_image;
    private com.toedter.calendar.JDateChooser txt_AddDate;
    private javax.swing.JTextField txt_id;
    private javax.swing.JTextField txt_name;
    private javax.swing.JTextField txt_price;
    // End of variables declaration//GEN-END:variables
}
