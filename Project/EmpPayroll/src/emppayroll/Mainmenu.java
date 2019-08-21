/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emppayroll;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Panel;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author sachith
 */
public class Mainmenu extends javax.swing.JFrame {

    Connection conn=null;
ResultSet rs=null;
PreparedStatement pst=null;
    
    int xMouse;
    int yMouse;
    
    
    GridBagLayout layout = new GridBagLayout();
    
    pnl_Empreg1 empreg;
    pnl_Deduction deduc;
    pnl_Payment pay;
    pnl_allowance all;
    pnl_audit_trail audt;
    pnl_search search;
    pnl_update_sal upsal;
    pnl_user user;
    pnl_reports RP;
    pnl_aboutus Aus;
    pnl_dball db;
    
    
    
    /**
     * Creates new form Mainmenu
     */
    public Mainmenu() {
        initComponents();
        
        //this.setLocation(300,100);
        initialize();
        
        //connect to DB
        conn = DBconnection.connect();
        btn_audit.setVisible(false);
        lbl_LUN.setText(String.valueOf(Emp.empun).toString());
        countemp();
        buttoned();
        
        showData();
        showTime();
        
        
        
        
        
        
        
        
        
        String un1=lbl_LUN.getText();
        Emp.empun = un1;
        
        
        empreg = new pnl_Empreg1();
        deduc = new pnl_Deduction();
        pay = new pnl_Payment();
        all = new pnl_allowance();
        audt = new pnl_audit_trail();
        search = new pnl_search();
        upsal = new pnl_update_sal();
        user = new pnl_user();
        RP = new pnl_reports();
        Aus = new pnl_aboutus();
        db = new pnl_dball();
        pnl_all.setLayout(layout);
        GridBagConstraints c = new GridBagConstraints();
        
        //Employee registration panel
        c.gridx = 0;
        c.gridy = 0;
        pnl_all.add(empreg,c);
        
        //Deduction panel
        GridBagConstraints dd = new GridBagConstraints();
        dd.gridx = 0;
        dd.gridy = 0;
        pnl_all.add(deduc,dd);
        
        //Payment panel
        GridBagConstraints pp = new GridBagConstraints();
        pp.gridx = 0;
        pp.gridy = 0;
        pnl_all.add(pay,pp);
        
        //Allowance panel
        GridBagConstraints aa = new GridBagConstraints();
        aa.gridx = 0;
        aa.gridy = 0;
        pnl_all.add(all,aa);
        
        //Audit trail panel
        GridBagConstraints au = new GridBagConstraints();
        au.gridx = 0;
        au.gridy = 0;
        pnl_all.add(audt,au);
        
        //search panel
        GridBagConstraints se = new GridBagConstraints();
        se.gridx = 0;
        se.gridy = 0;
        pnl_all.add(search,se);
        
        //Update salary panel
        GridBagConstraints up = new GridBagConstraints();
        up.gridx = 0;
        up.gridy = 0;
        pnl_all.add(upsal,up);
        
        //User salary panel
        GridBagConstraints us = new GridBagConstraints();
        us.gridx = 0;
        us.gridy = 0;
        pnl_all.add(user,us);
        
        //Report panel
        GridBagConstraints rp = new GridBagConstraints();
        rp.gridx = 0;
        rp.gridy = 0;
        pnl_all.add(RP,rp);
        
        //About Us panel
        GridBagConstraints AS = new GridBagConstraints();
        AS.gridx = 0;
        AS.gridy = 0;
        pnl_all.add(Aus,AS);
        
        //All database
        GridBagConstraints dba = new GridBagConstraints();
        AS.gridx = 0;
        AS.gridy = 0;
        pnl_all.add(db,dba);
        
        
        //pnl_dashboard.show();
        
        empreg.setVisible(false);
        deduc.setVisible(false);
        pay.setVisible(false);
        all.setVisible(false);
        audt.setVisible(false);
        search.setVisible(false);
        upsal.setVisible(false);
        user.setVisible(false);
        RP.setVisible(false);
        Aus.setVisible(false);
        db.setVisible(false);
        pnl_all.setVisible(false);
       
        
        
        
        
        
    }
    private void initialize(){
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("png.png")));
    }
    
    
    
    public boolean val;
    void showData() {
        Date d = new Date();
        SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
        lbl_date.setText(s.format(d));
    }

    void showTime() {
        new Timer(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
               Date d = new Date();
        SimpleDateFormat s = new SimpleDateFormat("hh:mm:ss a");
        lbl_time.setText(s.format(d)); 
            }

        }
        ).start();
    }

    void setColor(JPanel panel){
        panel.setBackground(new Color(45,160,255));
        val=true;
    }
    
    void resetColor(JPanel panel){
        panel.setBackground(new Color(0,82,147));
        val=false;
    }
    
    void EnteredColor(JPanel panel){
        panel.setBackground(new Color(0,49,89));
    }
    
    
    public void buttoned(){
        
        //String un1u=lbl_LUN.getText();
        if(lbl_LUN.getText().equals("admin")){
            
            btn_Database.setVisible(true);
            //btn_user.setVisible(true);
        }
        
        else{
            btn_Database.setVisible(false);
            //btn_user.setVisible(false);
        }
        
    }
    
    
    public void countemp(){
        
        try {
            
            String selectq="select count(empid) from employee";
            pst = conn.prepareStatement(selectq);
            rs=pst.executeQuery();
            
            while(rs.next()){
                String sum=rs.getString("count(empid)");
                lbl_count.setText(sum);
            }
            
            
        } catch (Exception e) {
        
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

        pnl_all = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        btn_empreg = new javax.swing.JButton();
        btn_upsal = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        btn_empmange = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        lbl_count = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        btn_payment = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        btn_logout = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        btn_allowance = new javax.swing.JButton();
        lbl_time = new javax.swing.JLabel();
        btn_deduction = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        FramDrag = new javax.swing.JPanel();
        lbl_Exit = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        lbl_LUN = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        lbl_date = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btn_dashboard = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btn_Database = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btn_reports = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        btn_audit = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        btn_user = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        btn_aboutUs = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(242, 242, 242));
        setLocationByPlatform(true);
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(984, 641));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnl_all.setMaximumSize(new java.awt.Dimension(746, 595));
        pnl_all.setPreferredSize(new java.awt.Dimension(746, 595));

        javax.swing.GroupLayout pnl_allLayout = new javax.swing.GroupLayout(pnl_all);
        pnl_all.setLayout(pnl_allLayout);
        pnl_allLayout.setHorizontalGroup(
            pnl_allLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 739, Short.MAX_VALUE)
        );
        pnl_allLayout.setVerticalGroup(
            pnl_allLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 590, Short.MAX_VALUE)
        );

        getContentPane().add(pnl_all, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 50, 739, 590));

        jLabel25.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(105, 105, 105));
        jLabel25.setText("Number of registered employees");
        getContentPane().add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 130, -1, -1));

        btn_empreg.setBackground(new java.awt.Color(242, 242, 242));
        btn_empreg.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        btn_empreg.setForeground(new java.awt.Color(242, 242, 242));
        btn_empreg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/emppayroll/btnempreg4.png"))); // NOI18N
        btn_empreg.setBorder(null);
        btn_empreg.setBorderPainted(false);
        btn_empreg.setContentAreaFilled(false);
        btn_empreg.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btn_empreg.setDefaultCapable(false);
        btn_empreg.setDoubleBuffered(true);
        btn_empreg.setFocusable(false);
        btn_empreg.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_empreg.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/emppayroll/btnempreg_pressed2.png"))); // NOI18N
        btn_empreg.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/emppayroll/btnempreg_pressed2.png"))); // NOI18N
        btn_empreg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_empregMousePressed(evt);
            }
        });
        getContentPane().add(btn_empreg, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 350, -1, -1));

        btn_upsal.setBackground(new java.awt.Color(242, 242, 242));
        btn_upsal.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        btn_upsal.setForeground(new java.awt.Color(242, 242, 242));
        btn_upsal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/emppayroll/upsalbtn.png"))); // NOI18N
        btn_upsal.setBorder(null);
        btn_upsal.setBorderPainted(false);
        btn_upsal.setContentAreaFilled(false);
        btn_upsal.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btn_upsal.setDefaultCapable(false);
        btn_upsal.setDoubleBuffered(true);
        btn_upsal.setFocusable(false);
        btn_upsal.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_upsal.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/emppayroll/upsalbtnpressed.png"))); // NOI18N
        btn_upsal.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/emppayroll/upsalbtnpressed.png"))); // NOI18N
        btn_upsal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_upsalMousePressed(evt);
            }
        });
        getContentPane().add(btn_upsal, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 350, -1, -1));

        jLabel18.setFont(new java.awt.Font("Century Gothic", 1, 15)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(105, 105, 105));
        jLabel18.setText("Update Salary");
        getContentPane().add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 450, -1, -1));

        jLabel19.setFont(new java.awt.Font("Century Gothic", 1, 15)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(105, 105, 105));
        jLabel19.setText("Deduction");
        getContentPane().add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 590, -1, -1));

        btn_empmange.setBackground(new java.awt.Color(242, 242, 242));
        btn_empmange.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        btn_empmange.setForeground(new java.awt.Color(242, 242, 242));
        btn_empmange.setIcon(new javax.swing.ImageIcon(getClass().getResource("/emppayroll/btnemp_manage.png"))); // NOI18N
        btn_empmange.setBorder(null);
        btn_empmange.setBorderPainted(false);
        btn_empmange.setContentAreaFilled(false);
        btn_empmange.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btn_empmange.setDefaultCapable(false);
        btn_empmange.setDoubleBuffered(true);
        btn_empmange.setFocusable(false);
        btn_empmange.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_empmange.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/emppayroll/btnemp_manage_pressed.png"))); // NOI18N
        btn_empmange.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/emppayroll/btnemp_manage_pressed.png"))); // NOI18N
        btn_empmange.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_empmangeMousePressed(evt);
            }
        });
        getContentPane().add(btn_empmange, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 350, -1, -1));

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/emppayroll/bar-chartgray 2.png"))); // NOI18N
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 60, -1, -1));

        lbl_count.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lbl_count.setForeground(new java.awt.Color(105, 105, 105));
        lbl_count.setIcon(new javax.swing.ImageIcon(getClass().getResource("/emppayroll/count3.gif"))); // NOI18N
        lbl_count.setText("1");
        lbl_count.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        getContentPane().add(lbl_count, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 170, -1, -1));

        jLabel20.setFont(new java.awt.Font("Century Gothic", 1, 15)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(105, 105, 105));
        jLabel20.setText("Allowance");
        getContentPane().add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 600, -1, -1));

        btn_payment.setBackground(new java.awt.Color(242, 242, 242));
        btn_payment.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        btn_payment.setForeground(new java.awt.Color(242, 242, 242));
        btn_payment.setIcon(new javax.swing.ImageIcon(getClass().getResource("/emppayroll/paymentbtn.png"))); // NOI18N
        btn_payment.setBorder(null);
        btn_payment.setBorderPainted(false);
        btn_payment.setContentAreaFilled(false);
        btn_payment.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btn_payment.setDefaultCapable(false);
        btn_payment.setDoubleBuffered(true);
        btn_payment.setFocusable(false);
        btn_payment.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_payment.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/emppayroll/paymentbtnpressed.png"))); // NOI18N
        btn_payment.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/emppayroll/paymentbtnpressed.png"))); // NOI18N
        btn_payment.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_paymentMousePressed(evt);
            }
        });
        getContentPane().add(btn_payment, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 500, -1, -1));

        jLabel21.setFont(new java.awt.Font("Century Gothic", 1, 15)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(105, 105, 105));
        jLabel21.setText("Payment");
        getContentPane().add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 600, -1, -1));

        btn_logout.setBackground(new java.awt.Color(242, 242, 242));
        btn_logout.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btn_logout.setForeground(new java.awt.Color(102, 102, 102));
        btn_logout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/emppayroll/exit.png"))); // NOI18N
        btn_logout.setText("Logout");
        btn_logout.setBorder(null);
        btn_logout.setBorderPainted(false);
        btn_logout.setContentAreaFilled(false);
        btn_logout.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btn_logout.setDefaultCapable(false);
        btn_logout.setDoubleBuffered(true);
        btn_logout.setFocusable(false);
        btn_logout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_logoutMousePressed(evt);
            }
        });
        btn_logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_logoutActionPerformed(evt);
            }
        });
        getContentPane().add(btn_logout, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 60, -1, -1));

        jLabel16.setFont(new java.awt.Font("Century Gothic", 1, 15)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(105, 105, 105));
        jLabel16.setText("Employee Manage");
        getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 450, -1, -1));

        jLabel14.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(105, 105, 105));
        jLabel14.setText("Dashboard");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 60, -1, -1));

        btn_allowance.setBackground(new java.awt.Color(242, 242, 242));
        btn_allowance.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        btn_allowance.setForeground(new java.awt.Color(242, 242, 242));
        btn_allowance.setIcon(new javax.swing.ImageIcon(getClass().getResource("/emppayroll/btnAllowance.png"))); // NOI18N
        btn_allowance.setBorder(null);
        btn_allowance.setBorderPainted(false);
        btn_allowance.setContentAreaFilled(false);
        btn_allowance.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btn_allowance.setDefaultCapable(false);
        btn_allowance.setDoubleBuffered(true);
        btn_allowance.setFocusable(false);
        btn_allowance.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_allowance.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/emppayroll/btnAllowancepressed.png"))); // NOI18N
        btn_allowance.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/emppayroll/btnAllowancepressed.png"))); // NOI18N
        btn_allowance.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_allowanceMousePressed(evt);
            }
        });
        getContentPane().add(btn_allowance, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 500, -1, -1));

        lbl_time.setFont(new java.awt.Font("Century Gothic", 0, 22)); // NOI18N
        lbl_time.setForeground(new java.awt.Color(105, 105, 105));
        lbl_time.setText("Time");
        getContentPane().add(lbl_time, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 60, -1, -1));

        btn_deduction.setBackground(new java.awt.Color(242, 242, 242));
        btn_deduction.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        btn_deduction.setForeground(new java.awt.Color(242, 242, 242));
        btn_deduction.setIcon(new javax.swing.ImageIcon(getClass().getResource("/emppayroll/deductionbtn.png"))); // NOI18N
        btn_deduction.setBorder(null);
        btn_deduction.setBorderPainted(false);
        btn_deduction.setContentAreaFilled(false);
        btn_deduction.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btn_deduction.setDefaultCapable(false);
        btn_deduction.setDoubleBuffered(true);
        btn_deduction.setFocusable(false);
        btn_deduction.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_deduction.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/emppayroll/deductionbtn_pressed.png"))); // NOI18N
        btn_deduction.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/emppayroll/deductionbtn_pressed.png"))); // NOI18N
        btn_deduction.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_deductionMousePressed(evt);
            }
        });
        getContentPane().add(btn_deduction, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 500, -1, -1));

        jLabel17.setFont(new java.awt.Font("Century Gothic", 1, 15)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(105, 105, 105));
        jLabel17.setText("Employee Registration");
        getContentPane().add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 450, -1, -1));

        FramDrag.setBackground(new java.awt.Color(14, 22, 33));
        FramDrag.setPreferredSize(new java.awt.Dimension(984, 46));
        FramDrag.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                FramDragMouseDragged(evt);
            }
        });
        FramDrag.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                FramDragMousePressed(evt);
            }
        });

        lbl_Exit.setBackground(new java.awt.Color(0, 81, 146));
        lbl_Exit.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        lbl_Exit.setForeground(new java.awt.Color(255, 255, 255));
        lbl_Exit.setText("X");
        lbl_Exit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_ExitMouseClicked(evt);
            }
        });

        jLabel22.setBackground(new java.awt.Color(14, 22, 33));
        jLabel22.setFont(new java.awt.Font("Century Gothic", 1, 15)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Welcome:");

        lbl_LUN.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        lbl_LUN.setForeground(new java.awt.Color(255, 255, 255));
        lbl_LUN.setText("User");

        jLabel24.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("Nemetech");

        lbl_date.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lbl_date.setForeground(new java.awt.Color(255, 255, 255));
        lbl_date.setText("Date");

        javax.swing.GroupLayout FramDragLayout = new javax.swing.GroupLayout(FramDrag);
        FramDrag.setLayout(FramDragLayout);
        FramDragLayout.setHorizontalGroup(
            FramDragLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, FramDragLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel24)
                .addGap(145, 145, 145)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_LUN)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 506, Short.MAX_VALUE)
                .addComponent(lbl_date)
                .addGap(44, 44, 44)
                .addComponent(lbl_Exit)
                .addContainerGap())
        );
        FramDragLayout.setVerticalGroup(
            FramDragLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, FramDragLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lbl_Exit, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(FramDragLayout.createSequentialGroup()
                .addGroup(FramDragLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(FramDragLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(FramDragLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22)
                            .addComponent(lbl_LUN)
                            .addComponent(jLabel24)))
                    .addGroup(FramDragLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lbl_date)))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        getContentPane().add(FramDrag, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2.setBackground(new java.awt.Color(0, 82, 147));
        jPanel2.setPreferredSize(new java.awt.Dimension(238, 595));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setPreferredSize(new java.awt.Dimension(238, 160));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/emppayroll/login logo2.png"))); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                .addContainerGap())
        );

        btn_dashboard.setBackground(new java.awt.Color(45, 160, 255));
        btn_dashboard.setPreferredSize(new java.awt.Dimension(238, 43));
        btn_dashboard.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btn_dashboardMouseMoved(evt);
            }
        });
        btn_dashboard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_dashboardMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_dashboardMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_dashboardMousePressed(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/emppayroll/bar-chart.png"))); // NOI18N
        jLabel2.setPreferredSize(new java.awt.Dimension(40, 40));

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Dashboard");

        javax.swing.GroupLayout btn_dashboardLayout = new javax.swing.GroupLayout(btn_dashboard);
        btn_dashboard.setLayout(btn_dashboardLayout);
        btn_dashboardLayout.setHorizontalGroup(
            btn_dashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btn_dashboardLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );
        btn_dashboardLayout.setVerticalGroup(
            btn_dashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_dashboardLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addGap(8, 8, 8))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btn_dashboardLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                .addGap(7, 7, 7))
        );

        btn_Database.setBackground(new java.awt.Color(0, 82, 147));
        btn_Database.setPreferredSize(new java.awt.Dimension(238, 43));
        btn_Database.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_DatabaseMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_DatabaseMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_DatabaseMousePressed(evt);
            }
        });

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/emppayroll/database.png"))); // NOI18N
        jLabel4.setPreferredSize(new java.awt.Dimension(40, 40));

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Database");

        javax.swing.GroupLayout btn_DatabaseLayout = new javax.swing.GroupLayout(btn_Database);
        btn_Database.setLayout(btn_DatabaseLayout);
        btn_DatabaseLayout.setHorizontalGroup(
            btn_DatabaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btn_DatabaseLayout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );
        btn_DatabaseLayout.setVerticalGroup(
            btn_DatabaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_DatabaseLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addGap(8, 8, 8))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btn_DatabaseLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(7, 7, 7))
        );

        btn_reports.setBackground(new java.awt.Color(0, 82, 147));
        btn_reports.setPreferredSize(new java.awt.Dimension(238, 43));
        btn_reports.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_reportsMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_reportsMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_reportsMousePressed(evt);
            }
        });

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/emppayroll/medical-history.png"))); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(40, 40));

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Reports");

        javax.swing.GroupLayout btn_reportsLayout = new javax.swing.GroupLayout(btn_reports);
        btn_reports.setLayout(btn_reportsLayout);
        btn_reportsLayout.setHorizontalGroup(
            btn_reportsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btn_reportsLayout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );
        btn_reportsLayout.setVerticalGroup(
            btn_reportsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_reportsLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addGap(8, 8, 8))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btn_reportsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(7, 7, 7))
        );

        btn_audit.setBackground(new java.awt.Color(0, 82, 147));
        btn_audit.setPreferredSize(new java.awt.Dimension(238, 43));
        btn_audit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_auditMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_auditMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_auditMousePressed(evt);
            }
        });

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/emppayroll/check-list .png"))); // NOI18N
        jLabel8.setPreferredSize(new java.awt.Dimension(40, 40));

        jLabel9.setBackground(new java.awt.Color(255, 255, 255));
        jLabel9.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Audit");

        javax.swing.GroupLayout btn_auditLayout = new javax.swing.GroupLayout(btn_audit);
        btn_audit.setLayout(btn_auditLayout);
        btn_auditLayout.setHorizontalGroup(
            btn_auditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btn_auditLayout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );
        btn_auditLayout.setVerticalGroup(
            btn_auditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_auditLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addGap(8, 8, 8))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btn_auditLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(7, 7, 7))
        );

        btn_user.setBackground(new java.awt.Color(0, 82, 147));
        btn_user.setPreferredSize(new java.awt.Dimension(238, 43));
        btn_user.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_userMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_userMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_userMousePressed(evt);
            }
        });

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/emppayroll/userbtn.png"))); // NOI18N
        jLabel10.setPreferredSize(new java.awt.Dimension(40, 40));

        jLabel11.setBackground(new java.awt.Color(255, 255, 255));
        jLabel11.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("User");

        javax.swing.GroupLayout btn_userLayout = new javax.swing.GroupLayout(btn_user);
        btn_user.setLayout(btn_userLayout);
        btn_userLayout.setHorizontalGroup(
            btn_userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btn_userLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );
        btn_userLayout.setVerticalGroup(
            btn_userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_userLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addGap(8, 8, 8))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btn_userLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                .addGap(7, 7, 7))
        );

        btn_aboutUs.setBackground(new java.awt.Color(0, 82, 147));
        btn_aboutUs.setPreferredSize(new java.awt.Dimension(238, 43));
        btn_aboutUs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_aboutUsMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_aboutUsMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_aboutUsMousePressed(evt);
            }
        });

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/emppayroll/group.png"))); // NOI18N
        jLabel12.setPreferredSize(new java.awt.Dimension(40, 40));

        jLabel13.setBackground(new java.awt.Color(255, 255, 255));
        jLabel13.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("About Us");

        javax.swing.GroupLayout btn_aboutUsLayout = new javax.swing.GroupLayout(btn_aboutUs);
        btn_aboutUs.setLayout(btn_aboutUsLayout);
        btn_aboutUsLayout.setHorizontalGroup(
            btn_aboutUsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btn_aboutUsLayout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );
        btn_aboutUsLayout.setVerticalGroup(
            btn_aboutUsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_aboutUsLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addGap(8, 8, 8))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btn_aboutUsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(7, 7, 7))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_dashboard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addComponent(btn_Database, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn_reports, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn_audit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn_user, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn_aboutUs, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_dashboard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_Database, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_user, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_reports, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_audit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_aboutUs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(69, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 47, -1, -1));

        setSize(new java.awt.Dimension(984, 641));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void lbl_ExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_ExitMouseClicked
        System.exit(0);
    }//GEN-LAST:event_lbl_ExitMouseClicked

    private void btn_dashboardMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_dashboardMousePressed
        countemp();
        setColor(btn_dashboard);
        resetColor(btn_Database);
        resetColor(btn_user);
        resetColor(btn_reports);
        resetColor(btn_audit);
        resetColor(btn_aboutUs);
        
        //pnl_dashboard.show();
        pnl_all.setVisible(false);
        empreg.setVisible(false);
        deduc.setVisible(false);
        pay.setVisible(false);
        all.setVisible(false);
        audt.setVisible(false);
        search.setVisible(false);
        upsal.setVisible(false);
        user.setVisible(false);
        RP.setVisible(false);
        Aus.setVisible(false);
        db.setVisible(false);
    }//GEN-LAST:event_btn_dashboardMousePressed

    private void btn_DatabaseMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_DatabaseMousePressed
        resetColor(btn_dashboard);
        setColor(btn_Database);
        resetColor(btn_user);
        resetColor(btn_reports);
        resetColor(btn_audit);
        resetColor(btn_aboutUs);
       pnl_all.setVisible(true);
        //pnl_dashboard.hide();
        db.setVisible(true);
        
        empreg.setVisible(false);
        deduc.setVisible(false);
        pay.setVisible(false);
        all.setVisible(false);
        audt.setVisible(false);
        search.setVisible(false);
        upsal.setVisible(false);
        user.setVisible(false);
        RP.setVisible(false);
        Aus.setVisible(false);
        
        
        
    }//GEN-LAST:event_btn_DatabaseMousePressed

    private void btn_userMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_userMousePressed
        resetColor(btn_dashboard);
        resetColor(btn_Database);
        setColor(btn_user);
        resetColor(btn_reports);
        resetColor(btn_audit);
        resetColor(btn_aboutUs);
        pnl_all.setVisible(true);
        //pnl_dashboard.hide();
        empreg.setVisible(false);
        deduc.setVisible(false);
        pay.setVisible(false);
        all.setVisible(false);
        audt.setVisible(false);
        search.setVisible(false);
        upsal.setVisible(false);
        user.setVisible(true);
        RP.setVisible(false);
        Aus.setVisible(false);
        db.setVisible(false);
    }//GEN-LAST:event_btn_userMousePressed

    private void btn_reportsMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_reportsMousePressed
        resetColor(btn_dashboard);
        resetColor(btn_Database);
        resetColor(btn_user);
        setColor(btn_reports);
        resetColor(btn_audit);
        resetColor(btn_aboutUs);
        pnl_all.setVisible(true);
        //pnl_dashboard.hide();
        empreg.setVisible(false);
        deduc.setVisible(false);
        pay.setVisible(false);
        all.setVisible(false);
        audt.setVisible(false);
        search.setVisible(false);
        upsal.setVisible(false);
        user.setVisible(false);
        RP.setVisible(true);
        Aus.setVisible(false);
        db.setVisible(false);
        
        
       
    }//GEN-LAST:event_btn_reportsMousePressed

    private void btn_auditMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_auditMousePressed
        resetColor(btn_dashboard);
        resetColor(btn_Database);
        resetColor(btn_user);
        resetColor(btn_reports);
        setColor(btn_audit);
        resetColor(btn_aboutUs);
        pnl_all.setVisible(true);
       // pnl_dashboard.hide();
        empreg.setVisible(false);
        deduc.setVisible(false);
        pay.setVisible(false);
        all.setVisible(false);
        audt.setVisible(true);
        search.setVisible(false);
        upsal.setVisible(false);
        user.setVisible(false);
        RP.setVisible(false);
        Aus.setVisible(false);
        db.setVisible(false);
    }//GEN-LAST:event_btn_auditMousePressed

    private void btn_aboutUsMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_aboutUsMousePressed
        resetColor(btn_dashboard);
        resetColor(btn_Database);
        resetColor(btn_user);
        resetColor(btn_reports);
        resetColor(btn_audit);
        setColor(btn_aboutUs);
        pnl_all.setVisible(true);
        //pnl_dashboard.hide();
        empreg.setVisible(false);
        deduc.setVisible(false);
        pay.setVisible(false);
        all.setVisible(false);
        audt.setVisible(false);
        search.setVisible(false);
        upsal.setVisible(false);
        user.setVisible(false);
        RP.setVisible(false);
        Aus.setVisible(true);
        db.setVisible(false);
    }//GEN-LAST:event_btn_aboutUsMousePressed

    private void btn_dashboardMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_dashboardMouseEntered
        EnteredColor(btn_dashboard);
        
        
        
        
    }//GEN-LAST:event_btn_dashboardMouseEntered

    private void btn_dashboardMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_dashboardMouseExited
             
        if(val=false){
            resetColor(btn_dashboard);
        }
        else{
            setColor(btn_dashboard);
            
        }
    }//GEN-LAST:event_btn_dashboardMouseExited

    private void btn_DatabaseMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_DatabaseMouseEntered
        EnteredColor(btn_Database);
        
        
        
        
        
    }//GEN-LAST:event_btn_DatabaseMouseEntered

    private void btn_DatabaseMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_DatabaseMouseExited
        if(val=false){
            resetColor(btn_Database);
        }
        else{
            setColor(btn_Database);
            
        }
    }//GEN-LAST:event_btn_DatabaseMouseExited

    private void btn_userMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_userMouseEntered
        EnteredColor(btn_user);
    }//GEN-LAST:event_btn_userMouseEntered

    private void btn_userMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_userMouseExited
        if(val=false){
            resetColor(btn_user);
        }
        else{
            setColor(btn_user);
            
        }
    }//GEN-LAST:event_btn_userMouseExited

    private void btn_reportsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_reportsMouseEntered
        EnteredColor(btn_reports);
    }//GEN-LAST:event_btn_reportsMouseEntered

    private void btn_reportsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_reportsMouseExited
        if(val=false){
            resetColor(btn_reports);
        }
        else{
            setColor(btn_reports);
        }
    }//GEN-LAST:event_btn_reportsMouseExited

    private void btn_auditMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_auditMouseEntered
        EnteredColor(btn_audit);
    }//GEN-LAST:event_btn_auditMouseEntered

    private void btn_auditMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_auditMouseExited
        if(val=false){
            resetColor(btn_audit);
        }
        else{
            setColor(btn_audit);
        }
    }//GEN-LAST:event_btn_auditMouseExited

    private void btn_aboutUsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_aboutUsMouseEntered
        EnteredColor(btn_aboutUs);
    }//GEN-LAST:event_btn_aboutUsMouseEntered

    private void btn_aboutUsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_aboutUsMouseExited
        if(val=false){
            resetColor(btn_aboutUs);
        }
        else{
            setColor(btn_aboutUs);
        }
    }//GEN-LAST:event_btn_aboutUsMouseExited

    private void btn_dashboardMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_dashboardMouseMoved
        //setColor(btn_dashboard);
    }//GEN-LAST:event_btn_dashboardMouseMoved

    private void FramDragMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FramDragMouseDragged
        int x=evt.getXOnScreen();
        int y=evt.getYOnScreen();
        
        this.setLocation(x-xMouse, y-yMouse);
    }//GEN-LAST:event_FramDragMouseDragged

    private void FramDragMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FramDragMousePressed
        xMouse=evt.getX();
        yMouse=evt.getY();
    }//GEN-LAST:event_FramDragMousePressed

    private void btn_empregMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_empregMousePressed
        pnl_all.setVisible(true);
//pnl_dashboard.hide();
        empreg.setVisible(true);
        deduc.setVisible(false);
        pay.setVisible(false);
        all.setVisible(false);
        audt.setVisible(false);
        search.setVisible(false);
        upsal.setVisible(false);
        user.setVisible(false);
        Aus.setVisible(false);
        RP.setVisible(false);
        db.setVisible(false);
    }//GEN-LAST:event_btn_empregMousePressed

    private void btn_empmangeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_empmangeMousePressed
       pnl_all.setVisible(true);
// pnl_dashboard.hide();
        empreg.setVisible(false);
        deduc.setVisible(false);
        pay.setVisible(false);
        all.setVisible(false);
        audt.setVisible(false);
        search.setVisible(true);
        upsal.setVisible(false);
        user.setVisible(false);
        Aus.setVisible(false);
        RP.setVisible(false);
        db.setVisible(false);
    }//GEN-LAST:event_btn_empmangeMousePressed

    private void btn_upsalMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_upsalMousePressed
        pnl_all.setVisible(true);
//pnl_dashboard.hide();
        empreg.setVisible(false);
        deduc.setVisible(false);
        pay.setVisible(false);
        all.setVisible(false);
        audt.setVisible(false);
        search.setVisible(false);
        upsal.setVisible(true);
        user.setVisible(false);
        Aus.setVisible(false);
        RP.setVisible(false);
        db.setVisible(false);
    }//GEN-LAST:event_btn_upsalMousePressed

    private void btn_allowanceMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_allowanceMousePressed
       pnl_all.setVisible(true);
// pnl_dashboard.hide();
        empreg.setVisible(false);
        deduc.setVisible(false);
        pay.setVisible(false);
        all.setVisible(true);
        audt.setVisible(false);
        search.setVisible(false);
        upsal.setVisible(false);
        user.setVisible(false);
        Aus.setVisible(false);
        RP.setVisible(false);
        db.setVisible(false);
    }//GEN-LAST:event_btn_allowanceMousePressed

    private void btn_deductionMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_deductionMousePressed
       pnl_all.setVisible(true);
// pnl_dashboard.hide();
        empreg.setVisible(false);
        deduc.setVisible(true);
        pay.setVisible(false);
        all.setVisible(false);
        audt.setVisible(false);
        search.setVisible(false);
        upsal.setVisible(false);
        user.setVisible(false);
        Aus.setVisible(false);
        RP.setVisible(false);
        db.setVisible(false);
    }//GEN-LAST:event_btn_deductionMousePressed

    private void btn_paymentMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_paymentMousePressed
        pnl_all.setVisible(true);
//pnl_dashboard.hide();
        empreg.setVisible(false);
        deduc.setVisible(false);
        pay.setVisible(true);
        all.setVisible(false);
        audt.setVisible(false);
        search.setVisible(false);
        upsal.setVisible(false);
        user.setVisible(false);
        Aus.setVisible(false);
        RP.setVisible(false);
        db.setVisible(false);
    }//GEN-LAST:event_btn_paymentMousePressed

    private void btn_logoutMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_logoutMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_logoutMousePressed

    private void btn_logoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_logoutActionPerformed
        Login ll = new Login();
        ll.show(true);
        this.dispose();
    }//GEN-LAST:event_btn_logoutActionPerformed

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Mainmenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Mainmenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Mainmenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Mainmenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Mainmenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel FramDrag;
    private javax.swing.JPanel btn_Database;
    private javax.swing.JPanel btn_aboutUs;
    private javax.swing.JButton btn_allowance;
    private javax.swing.JPanel btn_audit;
    private javax.swing.JPanel btn_dashboard;
    private javax.swing.JButton btn_deduction;
    private javax.swing.JButton btn_empmange;
    private javax.swing.JButton btn_empreg;
    private javax.swing.JButton btn_logout;
    private javax.swing.JButton btn_payment;
    private javax.swing.JPanel btn_reports;
    private javax.swing.JButton btn_upsal;
    private javax.swing.JPanel btn_user;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lbl_Exit;
    private javax.swing.JLabel lbl_LUN;
    private javax.swing.JLabel lbl_count;
    private javax.swing.JLabel lbl_date;
    private javax.swing.JLabel lbl_time;
    private javax.swing.JPanel pnl_all;
    // End of variables declaration//GEN-END:variables
}
