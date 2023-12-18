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
public class GUIVolly extends javax.swing.JFrame {

    /**
     * Creates new form GUIVolly
     */
    public GUIVolly() {
        initComponents();
        
         
        tampil();
        tampil_namaSiswa();
    }
    public void batal() {
        txt_absen.setText("");
        txt_kelas.setText("");
        txt_tim.setText("");
        txt_position.setText("");
}
   
    public Connection conn;
    // Variabel conn digunakan untuk menyimpan koneksi ke database
    
    String Nama1, Absen1,kelas1 ,ekskul1, jk1, alamat1 ;
    // Membuat variabel baru yang digunakan untuk menyimpan data yang akan ditampilkan dalam textfield dan buttongrup saat item dipilih
    
    public void itempilih() {
    // Membuat method itempilih() yang digunakan untuk menetapkan nilai textfield dan memilih buttongrup berdasarkan nilai variabel yang telah disimpan sebelumnya
       
      
        txt_absen.setText(Absen1);
        txt_kelas.setText(kelas1);
        txt_tim.setText(ekskul1);
        txt_position.setText(alamat1);
        
    }
    
    public void koneksi() throws SQLException {
    // Membuat method koneksi() yang digunakan untuk membuat koneksi ke database MySQL menggunakan driver JDBC
        try {
            conn = null;
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/OOP_NaufalD?user=root&password=");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GUIVolly.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException e) {
            Logger.getLogger(GUIVolly.class.getName()).log(Level.SEVERE, null, e);
        } catch (Exception es) {
            Logger.getLogger(GUIVolly.class.getName()).log(Level.SEVERE, null, es);
        }
    }
    
    public void tampil() {
    // Membuat Method tampil()yang digunakan untuk menampilkan data dari tabel tb_pembayarangaji ke dalam komponen tabel table_pembayarangaji
        DefaultTableModel tabelhead = new DefaultTableModel();
       
        tabelhead.addColumn("Nama ");
        tabelhead.addColumn("Absen ");
        tabelhead.addColumn("Kelas ");
        tabelhead.addColumn("Nama_Tim ");
        tabelhead.addColumn("Posisi ");
       
        
        try {
            koneksi();
            String sql = "SELECT * FROM tb_volly";
            Statement stat = conn.createStatement();
            ResultSet res = stat.executeQuery(sql);
            while (res.next()) {
                // Menambahkan baris dengan nilai-nilai yang sesuai ke model tabel
                tabelhead.addRow(new Object[]{res.getString(2), res.getString(3), res.getString(4), res.getString(5), res.getString(6),});
            }
            // Mengatur model tabel ke tabel_pembayarangaji
            table_volly.setModel(tabelhead);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "BELUM TERKONEKSI");
        }
    }
    public void tampil_namaSiswa() {
        try {
            koneksi();
            
            // Mengambil data nama_karyawan dari tabel tb_pembayarangaji
            String sql = "SELECT nama FROM tb_ekskul order by nama asc";
            Statement stt = conn.createStatement();
            ResultSet res = stt.executeQuery(sql);
            
            // Menambahkan nama_karyawan ke elemen combobox cmbIdKar
            while (res.next()) {
                Object[] ob = new Object[3];
                ob[0] = res.getString(1);
                cmbnama.addItem((String) ob[0]);
            }
            res.close();
            stt.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void refresh() {
    // Membuat instance baru dari GUI_PembayaranGaji dan menampilkannya
        new GUIVolly().setVisible(true);
        this.setVisible(false);
    }
    
    public void insert() {
        // Mendapatkan nilai-nilai dari textfield dan buttongrup
        String nama = (String)cmbnama.getSelectedItem();
        String absen = txt_absen.getText();
        String kelas = txt_kelas.getText();
        String Nama_tim = txt_tim.getText();
        String Posisi = txt_position.getText();
        
        try {
            koneksi();
            // Membuat statement untuk koneksi database
            Statement statement = conn.createStatement();
            // Menambahkan data baru ke tabel tb_pembayarangaji
            statement.executeUpdate("INSERT INTO tb_volly (Nama, Kelas, Absen , Nama_tim, Posisi)"
                    + "VALUES('" + nama + "','" + kelas + "','" + absen + "','" + Nama_tim + "','"+ Posisi +"')");
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
        String nama = (String) cmbnama.getSelectedItem();
        String absen = txt_absen.getText();
        String kelas = txt_kelas.getText();
        String tim = txt_tim.getText();
        String posisi = txt_position.getText();
        String namanama = Nama1;
        
        // Mengambil metode pembayaran yang dipilih
        try {
            // Membuat statement untuk koneksi database
            Statement statement = conn.createStatement();
            // Menjalankan query UPDATE untuk memperbarui data dalam tabel tb_pembayarangaji
            statement.executeUpdate("UPDATE tb_volly SET nama='" + nama + "'," + "absen='" + absen + "',"
                    + "kelas='" + kelas + "'" + ",tim='" + tim + "',posisi='" + posisi +"' WHERE nama = '" + namanama+ "'");
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
                String sql = "DELETE FROM tb_volly WHERE nama='" + cmbnama.getSelectedItem() + "'";
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
                String sql = "SELECT * FROM tb_volly WHERE nama  LIKE '%" + txt_search.getText() + "%'";
                ResultSet rs = statement.executeQuery(sql); //menampilkan data dari sql query
                if (rs.next()) // .next() = scanner method
                {
                    // Menampilkan data yang ditemukan ke elemen-elemen input
                    
                    cmbnama.setSelectedItem(rs.getString(2));
                    txt_absen.setText(rs.getString(3));
                    txt_kelas.setText(rs.getString(4));
                    txt_tim.setText(rs.getString(5));
                    txt_position.setText(rs.getString(6));
                   
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
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txt_kelas = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txt_absen = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txt_tim = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txt_position = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_volly = new javax.swing.JTable();
        btnsimpan = new javax.swing.JButton();
        btndelete = new javax.swing.JButton();
        btnbatal = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        cmbnama = new javax.swing.JComboBox<>();
        btnsearch = new javax.swing.JButton();
        txt_search = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Pendataan Siswa Ekstrakurikuler Volly");

        jLabel2.setText("Nama");

        jLabel3.setText("Kelas");

        jLabel4.setText("Absen");

        jLabel5.setText("Nama Tim");

        jLabel6.setText("Posisi Pemain");

        table_volly.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Nama", "Kelas", "Absen", "Tim volly", "Posisi Pemain"
            }
        ));
        jScrollPane1.setViewportView(table_volly);

        btnsimpan.setText("Simpan");
        btnsimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsimpanActionPerformed(evt);
            }
        });

        btndelete.setText("Hapus");
        btndelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndeleteActionPerformed(evt);
            }
        });

        btnbatal.setText("batal");
        btnbatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbatalActionPerformed(evt);
            }
        });

        jButton1.setText("Form Ekstrakurikuler");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("edit");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Tutup");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        cmbnama.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "~Nama Siswa~" }));
        cmbnama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbnamaActionPerformed(evt);
            }
        });

        btnsearch.setText("Search");
        btnsearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(166, 166, 166)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_kelas, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                            .addComponent(txt_absen)
                            .addComponent(txt_tim)
                            .addComponent(txt_position)
                            .addComponent(cmbnama, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnsimpan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnbatal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btndelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnsearch)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_search, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(52, 52, 52)
                        .addComponent(jButton1))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 454, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(cmbnama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txt_kelas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txt_absen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txt_tim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txt_position, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addComponent(btnsimpan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btndelete)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnbatal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(btnsearch)
                    .addComponent(txt_search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsimpanActionPerformed
        // TODO add your handling code here:
        //untuk menampilkan pesan misal data telah masuk
        insert();
    }//GEN-LAST:event_btnsimpanActionPerformed

    private void btndeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndeleteActionPerformed
        // TODO add your handling code here:
        //untuk menghapus data didalam table
        delete();
    }//GEN-LAST:event_btndeleteActionPerformed

    private void btnbatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbatalActionPerformed
        // TODO add your handling code here:
        //untuk membatalkan proses
        batal();
    }//GEN-LAST:event_btnbatalActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        new GUIeks().setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btnsearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsearchActionPerformed
        // TODO add your handling code here:
        cari();
    }//GEN-LAST:event_btnsearchActionPerformed

    private void cmbnamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbnamaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbnamaActionPerformed

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
            java.util.logging.Logger.getLogger(GUIVolly.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUIVolly.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUIVolly.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUIVolly.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUIVolly().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnbatal;
    private javax.swing.JButton btndelete;
    private javax.swing.JButton btnsearch;
    private javax.swing.JButton btnsimpan;
    private javax.swing.JComboBox<String> cmbnama;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table_volly;
    private javax.swing.JTextField txt_absen;
    private javax.swing.JTextField txt_kelas;
    private javax.swing.JTextField txt_position;
    private javax.swing.JTextField txt_search;
    private javax.swing.JTextField txt_tim;
    // End of variables declaration//GEN-END:variables
}
