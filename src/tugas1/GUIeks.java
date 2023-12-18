/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package tugas1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author HP
 */
public class GUIeks extends javax.swing.JFrame {

    /**
     * Creates new form GUIeks
     */
    public GUIeks() {
        initComponents();
        
        tampil();
        

//    //mengambil nilai dari class dan menampilkan didalam GUI
//    Siswa sw = new Siswa();
//     txtkelas.setText(sw.kelas);
//     txtkelas.setEnabled(false);
//     TxtAbsen.setText (sw.absen);
//     TxtAbsen.setEnabled (false);
//     
    }
    
   public void batal() {
        // Mengosongkan semua textfield dan membatalkan pemilihan buttongrup
        txtNama.setText("");
        TxtAbsen.setText("");
        txtalamat.setText("");
        txtekskul.setText("");
 }
    public Connection conn;
    // Variabel conn digunakan untuk menyimpan koneksi ke database
    
    String Nama1, Absen1,kelas1 ,ekskul1, jk1, alamat1 ;
    // Membuat variabel baru yang digunakan untuk menyimpan data yang akan ditampilkan dalam textfield dan buttongrup saat item dipilih
    
    public void itempilih() {
    // Membuat method itempilih() yang digunakan untuk menetapkan nilai textfield dan memilih buttongrup berdasarkan nilai variabel yang telah disimpan sebelumnya
       
       txtNama.setText(Nama1);
        TxtAbsen.setText(Absen1);
        cmbKelas.setSelectedItem(kelas1);
        txtekskul.setText(ekskul1);
        txtalamat.setText(alamat1);
        if (jk1.equalsIgnoreCase("Laki-laki")) {
            laki1.setSelected(true);
        } else {
            perempuan2.setSelected(true);
        }
    }
    
    public void koneksi() throws SQLException {
    // Membuat method koneksi() yang digunakan untuk membuat koneksi ke database MySQL menggunakan driver JDBC
        try {
            conn = null;
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/OOP_NaufalD?user=root&password=");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GUIeks.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException e) {
            Logger.getLogger(GUIeks.class.getName()).log(Level.SEVERE, null, e);
        } catch (Exception es) {
            Logger.getLogger(GUIeks.class.getName()).log(Level.SEVERE, null, es);
        }
    }
    
    public void tampil() {
    // Membuat Method tampil()yang digunakan untuk menampilkan data dari tabel tb_pembayarangaji ke dalam komponen tabel table_pembayarangaji
        DefaultTableModel tabelhead = new DefaultTableModel();
       
        tabelhead.addColumn("Nama Karyawan");
        tabelhead.addColumn("Absen");
        tabelhead.addColumn("Kelas");
        tabelhead.addColumn("Ekskul");
        tabelhead.addColumn("Jk1");
        tabelhead.addColumn("Alamat");
        
        try {
            koneksi();
            String sql = "SELECT * FROM tb_ekskul";
            Statement stat = conn.createStatement();
            ResultSet res = stat.executeQuery(sql);
            while (res.next()) {
                // Menambahkan baris dengan nilai-nilai yang sesuai ke model tabel
                tabelhead.addRow(new Object[]{res.getString(2), res.getString(3), res.getString(4), res.getString(5), res.getString(6), res.getString(7),});
            }
            // Mengatur model tabel ke tabel_pembayarangaji
            table_data_ekskul.setModel(tabelhead);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "BELUM TERKONEKSI");
        }
    }
    
    public void refresh() {
    // Membuat instance baru dari GUI_PembayaranGaji dan menampilkannya
        new GUIeks().setVisible(true);
        this.setVisible(false);
    }
    
    public void insert() {
        // Mendapatkan nilai-nilai dari textfield dan buttongrup
        String nama = txtNama.getText();
        String absen = TxtAbsen.getText();
        String kelas = (String) cmbKelas.getSelectedItem();
        String ekskul = txtekskul.getText();
        String Jenis_kelamin;
        // Mengambil metode pembayaran yang dipilih
        if (laki1.isSelected()) {
            Jenis_kelamin = "Laki-laki";
        } else {
            Jenis_kelamin = "Perempuan";
        }
        String alamat = txtalamat.getText();
        
        try {
            koneksi();
            // Membuat statement untuk koneksi database
            Statement statement = conn.createStatement();
            // Menambahkan data baru ke tabel tb_pembayarangaji
            statement.executeUpdate("INSERT INTO tb_ekskul (nama, absen, kelas, ekskul, Jk, alamat)"
                    + "VALUES('" + nama + "','" + absen + "','" + kelas + "','" + ekskul + "','" + Jenis_kelamin + "','" + alamat  + "')");
            statement.close();
            // Menampilkan pesan sukses setelah menambahkan data
            JOptionPane.showMessageDialog(null, "Berhasil Memasukan Data Siswa" + "\n");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Terjadi Kesalahan Input!");
        }
        refresh();
    }
    
    public void update() {
        // Mendapatkan nilai-nilai dari textfield dan buttongrup
         String nama = txtNama.getText();
        String absen = TxtAbsen.getText();
        String kelas = (String) cmbKelas.getSelectedItem();
        String ekskul = txtekskul.getText();
        String Jenis_kelamin;
        if (laki1.isSelected()) {
            Jenis_kelamin = "Laki-laki";
        } else {
            Jenis_kelamin = "Perempuan";
        }
        String namalama = Nama1;
        String alamat = txtalamat.getText();
        
        // Mengambil metode pembayaran yang dipilih
        try {
            // Membuat statement untuk koneksi database
            Statement statement = conn.createStatement();
            // Menjalankan query UPDATE untuk memperbarui data dalam tabel tb_pembayarangaji
            statement.executeUpdate("UPDATE tb_ekskul SET nama='" + nama + "'," + "absen='" + absen + "',"
                    + "kelas='" + kelas + "'" + ",ekskul='" + ekskul + "',jenis_kelamin='" + Jenis_kelamin + "',alamat='"
                    + alamat + "' WHERE nama = '" + namalama + "'");
            statement.close();
            conn.close();
            // Menampilkan pesan sukses setelah memperbarui data
            JOptionPane.showMessageDialog(null, "Update Data siswa telah Berhasil!");
        } catch (Exception e) {
            System.out.println("Error : " + e);
        }
        refresh();
    }
    
    public void delete() {
        // Menampilkan dialog konfirmasi untuk menghapus data
        int ok = JOptionPane.showConfirmDialog(null, "Apakah Anda yakin akan menghapus data ?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (ok == 0) {
            try {
                // Query DELETE untuk menghapus data dari tabel tb_pembayarangaji
                String sql = "DELETE FROM tb_ekskul WHERE nama='" + txtNama.getText() + "'";
                java.sql.PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.executeUpdate();
                
                // Menampilkan pesan sukses setelah menghapus data
                JOptionPane.showMessageDialog(null, "Data Berhasil di hapus");
                batal();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Data Gagal di hapus");
            }
        }
        refresh();
    }
    
    public void cari() {
        try {
            try ( Statement statement = conn.createStatement()) {
                
                // Query SELECT untuk mencari data berdasarkan id_karyawan
                String sql = "SELECT * FROM tb_ekskul WHERE nama  LIKE '%" + txtsearch.getText() + "%'";
                ResultSet rs = statement.executeQuery(sql); //menampilkan data dari sql query
                if (rs.next()) // .next() = scanner method
                {
                    // Menampilkan data yang ditemukan ke elemen-elemen input
                    
                    txtNama.setText(rs.getString(2));
                    TxtAbsen.setText(rs.getString(3));
                    cmbKelas.setSelectedItem(rs.getString(4));
                    txtekskul.setText(rs.getString(5));
                    txtalamat.setText(rs.getString(6));
                    String jk = rs.getString(7);
                    
                    // Memeriksa metode pembayaran yang digunakan
                    if (jk.equalsIgnoreCase("Laki-Laki")) {
                        laki1.setSelected(true);
                    } else {
                        perempuan2.setSelected(true);
                    }
                } else {
                    // Menampilkan pesan jika tidak ada data yang dicari
                    JOptionPane.showMessageDialog(null, "Data yang Anda cari tidak tersedia");
                }
            }
        } catch (Exception ex) {
            System.out.println("Error." + ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnkelas = new javax.swing.ButtonGroup();
        btnjk = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtNama = new javax.swing.JTextField();
        txtekskul = new javax.swing.JTextField();
        txtalamat = new javax.swing.JTextField();
        btnhapus = new javax.swing.JButton();
        laki1 = new javax.swing.JRadioButton();
        perempuan2 = new javax.swing.JRadioButton();
        TxtAbsen = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_data_ekskul = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        cmbKelas = new javax.swing.JComboBox<>();
        txtsearch = new javax.swing.JTextField();
        btnsearch = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        form_volly = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Pendataan Ekstrakurikuler Siswa");

        jLabel2.setText("Nama                      :");

        jLabel3.setText("Absen                      :");

        jLabel4.setText("Kelas                        :");

        jLabel5.setText("Ekskul yang diikuti  :");

        jLabel6.setText("Jenis Kelamin          :");

        jLabel7.setText("Alamat                    :");

        txtekskul.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtekskulActionPerformed(evt);
            }
        });

        txtalamat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtalamatActionPerformed(evt);
            }
        });

        btnhapus.setText("hapus");
        btnhapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhapusActionPerformed(evt);
            }
        });

        btnjk.add(laki1);
        laki1.setText("Laki - Laki");

        btnjk.add(perempuan2);
        perempuan2.setText("Perempuan");

        table_data_ekskul.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Nama", "Absen", "Kelas", "Kas yang akan dibayar"
            }
        ));
        table_data_ekskul.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_data_ekskulMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table_data_ekskul);

        jButton1.setText("Simpan");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("batal");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Edit");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        cmbKelas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Kelas X", "Kelas XI", "Kelas XII" }));

        btnsearch.setText("cari");
        btnsearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsearchActionPerformed(evt);
            }
        });

        jButton4.setText("tutup");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        form_volly.setText("Form Volly");
        form_volly.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                form_vollyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtekskul, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNama)
                            .addComponent(txtalamat, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
                            .addComponent(laki1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(perempuan2, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TxtAbsen)
                            .addComponent(cmbKelas, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 681, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnhapus, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(form_volly)
                        .addGap(43, 43, 43))))
            .addGroup(layout.createSequentialGroup()
                .addGap(376, 376, 376)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnsearch)
                .addGap(18, 18, 18)
                .addComponent(txtsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtsearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnsearch))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(TxtAbsen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(cmbKelas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtekskul, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(laki1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(perempuan2)
                        .addGap(2, 2, 2)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtalamat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jButton3)
                            .addComponent(btnhapus)
                            .addComponent(jButton2)
                            .addComponent(jButton4)
                            .addComponent(form_volly))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtalamatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtalamatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtalamatActionPerformed

    private void txtekskulActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtekskulActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtekskulActionPerformed

    private void btnhapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhapusActionPerformed
        // TODO add your handling code here:
        delete();
    }//GEN-LAST:event_btnhapusActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        update();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        insert();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        batal();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnsearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsearchActionPerformed
        // TODO add your handling code here:
        cari();
    }//GEN-LAST:event_btnsearchActionPerformed

    private void table_data_ekskulMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_data_ekskulMouseClicked
        // TODO add your handling code here:
        int tabel = table_data_ekskul.getSelectedRow(); // Mendapatkan baris yang dipilih saat mengklik tb_pembayarangaji
        // Menyimpan nilai-nilai baris yang dipilih ke variabel-variabel
        Nama1 = table_data_ekskul.getValueAt(tabel, 0).toString();
        Absen1 = table_data_ekskul.getValueAt(tabel, 1).toString();
        kelas1 = table_data_ekskul.getValueAt(tabel, 2).toString();
        ekskul1 = table_data_ekskul.getValueAt(tabel, 3).toString();
        jk1 = table_data_ekskul.getValueAt(tabel, 4).toString();
        alamat1 = table_data_ekskul.getValueAt(tabel, 5).toString();
              
        itempilih(); // Memanggil metode itempilih()
    }//GEN-LAST:event_table_data_ekskulMouseClicked

    private void form_vollyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_form_vollyActionPerformed
        // TODO add your handling code here:
        new GUIVolly().setVisible(true);
    }//GEN-LAST:event_form_vollyActionPerformed

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
            java.util.logging.Logger.getLogger(GUIeks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUIeks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUIeks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUIeks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUIeks().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField TxtAbsen;
    private javax.swing.JButton btnhapus;
    private javax.swing.ButtonGroup btnjk;
    private javax.swing.ButtonGroup btnkelas;
    private javax.swing.JButton btnsearch;
    private javax.swing.JComboBox<String> cmbKelas;
    private javax.swing.JButton form_volly;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton laki1;
    private javax.swing.JRadioButton perempuan2;
    private javax.swing.JTable table_data_ekskul;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextField txtalamat;
    private javax.swing.JTextField txtekskul;
    private javax.swing.JTextField txtsearch;
    // End of variables declaration//GEN-END:variables
}
