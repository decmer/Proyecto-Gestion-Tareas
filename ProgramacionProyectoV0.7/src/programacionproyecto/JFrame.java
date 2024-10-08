/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package programacionproyecto;


import javax.swing.table.JTableHeader;
import java.awt.*;
import com.db4o.ObjectSet;
import com.db4o.query.Query;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import programacionproyecto.*;

/**
 *
 * @author jose
 */
public class JFrame extends javax.swing.JFrame {

    public DefaultTableModel mU;
    public DefaultTableModel mAE;
    public DefaultTableModel mAR;
    public DefaultTableModel mMP;
    public DefaultTableModel mMT;
    public Usuarios usu = null;
    public int rowSelect;
    public int rowSelectAux;
    
    public JFrame() {
        BaseDatos.abreBBDD();
        inicializaid();
        initComponents();
        superAdmin();
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                BaseDatos.exitBBDD();
            }
        });
    }
    
    private void superAdmin(){
        Query q = BaseDatos.getBBDD().query();
        q.constrain(Usuarios.class);
        if(!q.execute().hasNext())
            BaseDatos.getBBDD().store(new Administradores("SuperUsuario", "SPU"));
    }
    
    private void inicializaid(){
        Usuarios.asignaIDAux();
        Proyectos.asignaIDAux();
    }
    
    public void iniciarTablaUsu(){
        mU = new DefaultTableModel(null, new String[]{"ID","NOMBRE", "PUESTO"});
        jTableAdminMostrarUsu.setModel(mU);
        Query q = BaseDatos.getBBDD().query();
        q.constrain(Usuarios.class);
        q.descend("id").orderAscending();
        ObjectSet oS= q.execute();
        while(oS.hasNext()){
            Object u = oS.next();
            mU.addRow(new String[]{((Usuarios)u).getId()+"", ((Usuarios)u).getNombre(),Usuarios.tipoUsuario(u)});
        }
    }
    
    public void iniciarTablaDesEspera(){
        mAE = new DefaultTableModel(null, new String[]{"ID","NOMBRE", "IDp", "NombreP"});
        jTableDesarrolloTareas.setModel(mAE);

        for (Tareas t : ((Desarrolladores)usu).getTareasAssignadas()) {
            if (t.getEstado().equals(Estados.EN_ESPERA)) {
                mAE.addRow(new String[]{t.getId()+"", t.getNombre(), t.getPerteneProyecto().getId()+"", t.getPerteneProyecto().getNombre()});
            }
        }
        int max = jTableDesarrolloTareas.getRowCount();
        jSliderDes.setMaximum(max);
        jSpinner.setModel(new SpinnerNumberModel(0, 0, max, 1));
    }
    
    public void iniciarTablaDesEnProceso(){
        mAR = new DefaultTableModel(null, new String[]{"ID","NOMBRE", "IDp", "NombreP"});
        jTableDesarrolloTareas1.setModel(mAR);

        for (Tareas t : ((Desarrolladores)usu).getTareasAssignadas()) {
            if (t.getEstado().equals(Estados.EN_REALIZACION)) {
                mAR.addRow(new String[]{t.getId()+"", t.getNombre(), t.getPerteneProyecto().getId()+"", t.getPerteneProyecto().getNombre()});
            }
        }
    }

    public void iniciarTablaManagerProyect(){
        mMP = new DefaultTableModel(null, new String[]{"ID","NOMBRE", "F_LIMITE", "ESTADO"});
        jTableManagersProyect.setModel(mMP);
        Query q = BaseDatos.getBBDD().query();
        q.constrain(Proyectos.class);
        q.descend("id").orderAscending();
        ObjectSet oS= q.execute();
        while(oS.hasNext()){
            Proyectos p = (Proyectos)oS.next();
            if (p.getManager() != null && p.getManager().getId() == usu.getId()) {
                mMP.addRow(new String[]{p.getId()+"", p.getNombre(), p.getFechaLimite().getDate()+"/"+p.getFechaLimite().getMonth()+"/"+p.getFechaLimite().getYear(), p.isCerrado()?"Cerrado":"Abierto"});
            }
        }
    }
    
    public void iniciarTablaManagerTareas(int idPro){
        mMT = new DefaultTableModel(null, new String[]{"ID","NOMBRE","ESTADO","D_ENCARGADO","PRIORIDAD"});
        jTableManagersTareas.setModel(mMT);
        Query q = BaseDatos.getBBDD().query();
        q.constrain(Tareas.class);
        q.descend("id").orderAscending();
        ObjectSet oS= q.execute();
        while(oS.hasNext()){
            Tareas t = (Tareas)oS.next();
            if (t.getPerteneProyecto().getId() == idPro) {
                if (t.getDesarrollador() == null) 
                    mMT.addRow(new String[]{t.getId()+"", t.getNombre(), t.getEstado().getValor(), "No asignado", t.getId()+""});
                else
                    mMT.addRow(new String[]{t.getId()+"", t.getNombre(), t.getEstado().getValor(), t.getDesarrollador().getNombre(), t.getPrioridad()+""});
            }
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

        jPanel1 = new javax.swing.JPanel();
        jLabelTituloPrincipal = new javax.swing.JLabel();
        jLabelTituloPrincipalNobreUsu = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jButtonCerrarSesion = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanelInicio = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabelTituloPrincipal2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabelTituloPrincipal3 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jButtonInicioEnviar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldInicioID = new javax.swing.JTextField();
        jButtonIDIndex = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jPasswordField = new javax.swing.JPasswordField();
        jPanelAdmin = new javax.swing.JPanel();
        jLabelTituloAdmin = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableAdminMostrarUsu = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jButtonAdminAnadirUsuario = new javax.swing.JButton();
        jButtonAdminEditUsuario = new javax.swing.JButton();
        jButtonAdminDelUsu = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jPanelDesarrollador = new javax.swing.JPanel();
        jLabelTituloAdmin1 = new javax.swing.JLabel();
        jScrollPaneDesarrollo = new javax.swing.JScrollPane();
        jTableDesarrolloTareas = new javax.swing.JTable();
        jSliderDes = new javax.swing.JSlider();
        jSpinner = new javax.swing.JSpinner();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPaneDesarrollo1 = new javax.swing.JScrollPane();
        jTableDesarrolloTareas1 = new javax.swing.JTable();
        jPanelManager = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabelTituloAdmin2 = new javax.swing.JLabel();
        jScrollPaneDesarrollo2 = new javax.swing.JScrollPane();
        jTableManagersProyect = new javax.swing.JTable();
        jScrollPaneDesarrollo3 = new javax.swing.JScrollPane();
        jTableManagersTareas = new javax.swing.JTable();
        jButtonManNUevoProyect = new javax.swing.JButton();
        jButtonManModifiProyect = new javax.swing.JButton();
        jButtonManBorrarProyect = new javax.swing.JButton();
        jButtonManCerrarProyect = new javax.swing.JButton();
        jButtonManCreaInforme = new javax.swing.JButton();
        jButtonManNuevaTarea = new javax.swing.JButton();
        jButtonManBorrarTarea = new javax.swing.JButton();
        jButtonManAsignaTarea = new javax.swing.JButton();
        jButtonManModifiTarea = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistema de Gestión de Proyectos");
        setMaximumSize(new java.awt.Dimension(895, 695));
        setMinimumSize(new java.awt.Dimension(10, 10));
        setPreferredSize(new java.awt.Dimension(810, 625));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));
        jPanel1.setForeground(new java.awt.Color(153, 153, 153));

        jLabelTituloPrincipal.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        jLabelTituloPrincipal.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTituloPrincipal.setText("Proyecto Sistema de Gestión de Tareas");

        jLabelTituloPrincipalNobreUsu.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabelTituloPrincipalNobreUsu.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTituloPrincipalNobreUsu.setText("Inicia sesion");

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/templates/logoIndex.png"))); // NOI18N

        jButtonCerrarSesion.setVisible(false);
        jButtonCerrarSesion.setBackground(new java.awt.Color(102, 102, 102));
        jButtonCerrarSesion.setForeground(new java.awt.Color(255, 255, 255));
        jButtonCerrarSesion.setText("Cerrar Sesión");
        jButtonCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCerrarSesionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelTituloPrincipal)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButtonCerrarSesion)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelTituloPrincipalNobreUsu)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 85, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(21, 21, 21))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabelTituloPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonCerrarSesion)
                            .addComponent(jLabelTituloPrincipalNobreUsu))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 100));

        jPanelInicio.setLayout(null);

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setLayout(null);

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/templates/fotoLateralInicio2.png"))); // NOI18N
        jPanel2.add(jLabel9);
        jLabel9.setBounds(-7, 2, 200, 510);

        jPanelInicio.add(jPanel2);
        jPanel2.setBounds(0, 0, 190, 510);

        jPanel3.setBackground(new java.awt.Color(51, 51, 51));

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Ciclo formativo de formación profesional avanza Desarrollo de aplicaciones multiplataforma");
        jLabel4.setToolTipText("ciclo formativo de formación profesional avanza \nDesarrollo de aplicaciones multiplataforma");

        jLabelTituloPrincipal2.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabelTituloPrincipal2.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTituloPrincipal2.setText("Jose Antonio Merino Decena");

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Proyecto de:");
        jLabel5.setToolTipText("ciclo formativo de formación profesional avanza \nDesarrollo de aplicaciones multiplataforma");

        jLabelTituloPrincipal3.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabelTituloPrincipal3.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTituloPrincipal3.setText("Design by");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabelTituloPrincipal3))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelTituloPrincipal2)
                    .addComponent(jLabel4))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelTituloPrincipal3)
                    .addComponent(jLabelTituloPrincipal2))
                .addGap(16, 16, 16))
        );

        jPanelInicio.add(jPanel3);
        jPanel3.setBounds(190, 430, 610, 80);

        jPanel4.setBackground(new java.awt.Color(35, 35, 35));

        jButtonInicioEnviar.setText("Acceder");
        jButtonInicioEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonInicioEnviarActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Contraseña");

        jLabel2.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("ID");

        jTextFieldInicioID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldInicioIDActionPerformed(evt);
            }
        });

        jButtonIDIndex.setText("?");
        jButtonIDIndex.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonIDIndexActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Inicio de sesión");

        jPasswordField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPasswordFieldMouseClicked(evt);
            }
        });
        jPasswordField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordFieldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addGap(258, 258, 258)
                            .addComponent(jButtonInicioEnviar, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addGap(140, 140, 140)
                                    .addComponent(jLabel2)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel3)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jPasswordField)
                                .addComponent(jTextFieldInicioID, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE))
                            .addGap(18, 18, 18)
                            .addComponent(jButtonIDIndex, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(212, 212, 212)
                        .addComponent(jLabel8)))
                .addContainerGap(138, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(110, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addGap(37, 37, 37)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldInicioID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonIDIndex))
                .addGap(19, 19, 19)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addComponent(jButtonInicioEnviar)
                .addGap(132, 132, 132))
        );

        jPanelInicio.add(jPanel4);
        jPanel4.setBounds(190, 0, 610, 430);

        jTabbedPane1.addTab("tab1", jPanelInicio);

        jLabelTituloAdmin.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabelTituloAdmin.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTituloAdmin.setText("Administradores");

        jPanel5.setBackground(new java.awt.Color(204, 204, 255));
        jPanel5.setLayout(null);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Lista de Usuarios");
        jPanel5.add(jLabel7);
        jLabel7.setBounds(320, 10, 140, 25);

        jTableAdminMostrarUsu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTableAdminMostrarUsu.setFont(new Font("Arial", Font.PLAIN, 14));
        jTableAdminMostrarUsu.setRowHeight(30);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        jTableAdminMostrarUsu.setDefaultRenderer(Object.class, centerRenderer);

        JTableHeader header = jTableAdminMostrarUsu.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 16));
        header.setBackground(new Color(102, 205, 170)); // Color más moderno
        header.setForeground(Color.BLACK);
        jScrollPane1.setViewportView(jTableAdminMostrarUsu);

        jScrollPane1.getVerticalScrollBar().setUI(new ModernScrollBarUI());
        jScrollPane1.getHorizontalScrollBar().setUI(new ModernScrollBarUI());

        jPanel5.add(jScrollPane1);
        jScrollPane1.setBounds(20, 40, 746, 340);

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/templates/FondoAdmin.png"))); // NOI18N
        jLabel6.setText("jLabel6");
        jPanel5.add(jLabel6);
        jLabel6.setBounds(0, -40, 800, 460);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jButtonAdminAnadirUsuario.setBackground(new java.awt.Color(51, 51, 51));
        jButtonAdminAnadirUsuario.setForeground(new java.awt.Color(255, 255, 255));
        jButtonAdminAnadirUsuario.setText("Añadir Usuario");
        jButtonAdminAnadirUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAdminAnadirUsuarioActionPerformed(evt);
            }
        });

        jButtonAdminEditUsuario.setBackground(new java.awt.Color(51, 51, 51));
        jButtonAdminEditUsuario.setForeground(new java.awt.Color(255, 255, 255));
        jButtonAdminEditUsuario.setText("Editar Usuario");
        jButtonAdminEditUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAdminEditUsuarioActionPerformed(evt);
            }
        });

        jButtonAdminDelUsu.setBackground(new java.awt.Color(51, 51, 51));
        jButtonAdminDelUsu.setForeground(new java.awt.Color(255, 255, 255));
        jButtonAdminDelUsu.setText("Borrar Usuario");
        jButtonAdminDelUsu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAdminDelUsuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonAdminAnadirUsuario)
                .addGap(223, 223, 223)
                .addComponent(jButtonAdminEditUsuario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonAdminDelUsu)
                .addGap(127, 127, 127))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAdminAnadirUsuario)
                    .addComponent(jButtonAdminEditUsuario)
                    .addComponent(jButtonAdminDelUsu))
                .addGap(0, 488, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 868, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 732, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanelAdminLayout = new javax.swing.GroupLayout(jPanelAdmin);
        jPanelAdmin.setLayout(jPanelAdminLayout);
        jPanelAdminLayout.setHorizontalGroup(
            jPanelAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAdminLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanelAdminLayout.createSequentialGroup()
                        .addComponent(jLabelTituloAdmin)
                        .addGap(0, 760, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanelAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelAdminLayout.createSequentialGroup()
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanelAdminLayout.setVerticalGroup(
            jPanelAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAdminLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabelTituloAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanelAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelAdminLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(258, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("tab2", jPanelAdmin);

        jPanelDesarrollador.setBackground(new java.awt.Color(51, 51, 51));

        jLabelTituloAdmin1.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabelTituloAdmin1.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTituloAdmin1.setText("Desarrolladores");

        jTableDesarrolloTareas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTableDesarrolloTareas.setFont(new Font("Arial", Font.PLAIN, 14));
        jTableDesarrolloTareas.setRowHeight(30);

        DefaultTableCellRenderer centerRendererDesarrolloTareas = new DefaultTableCellRenderer();
        centerRendererDesarrolloTareas.setHorizontalAlignment(JLabel.CENTER);
        jTableDesarrolloTareas.setDefaultRenderer(Object.class, centerRenderer);

        JTableHeader headerDesarrolloTareas = jTableDesarrolloTareas.getTableHeader();
        headerDesarrolloTareas.setFont(new Font("Arial", Font.BOLD, 16));
        headerDesarrolloTareas.setBackground(new Color(102, 205, 170)); // Color más moderno
        headerDesarrolloTareas.setForeground(Color.BLACK);
        jScrollPaneDesarrollo.setViewportView(jTableDesarrolloTareas);

        jScrollPaneDesarrollo.getVerticalScrollBar().setUI(new ModernScrollBarUI());
        jScrollPaneDesarrollo.getHorizontalScrollBar().setUI(new ModernScrollBarUI());

        jSliderDes.setBackground(new java.awt.Color(255, 255, 255));
        jSliderDes.setValue(0);
        jSliderDes.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSliderDesStateChanged(evt);
            }
        });

        jSpinner.setModel(new javax.swing.SpinnerNumberModel(0, 0, 999, 1));
        jSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinnerStateChanged(evt);
            }
        });

        jButton1.setText("Terminar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Realizar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Crea Informe");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jTableDesarrolloTareas1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTableDesarrolloTareas1.setFont(new Font("Arial", Font.PLAIN, 14));
        jTableDesarrolloTareas1.setRowHeight(30);

        DefaultTableCellRenderer centerRendererDesarrolloTareas1 = new DefaultTableCellRenderer();
        centerRendererDesarrolloTareas1.setHorizontalAlignment(JLabel.CENTER);
        jTableDesarrolloTareas1.setDefaultRenderer(Object.class, centerRendererDesarrolloTareas1);

        JTableHeader headerDesarrolloTareas1 = jTableDesarrolloTareas1.getTableHeader();
        headerDesarrolloTareas1.setFont(new Font("Arial", Font.BOLD, 16));
        headerDesarrolloTareas1.setBackground(new Color(102, 205, 170));
        headerDesarrolloTareas1.setForeground(Color.BLACK);
        jScrollPaneDesarrollo1.setViewportView(jTableDesarrolloTareas1);

        jScrollPaneDesarrollo.getVerticalScrollBar().setUI(new ModernScrollBarUI());
        jScrollPaneDesarrollo.getHorizontalScrollBar().setUI(new ModernScrollBarUI());

        javax.swing.GroupLayout jPanelDesarrolladorLayout = new javax.swing.GroupLayout(jPanelDesarrollador);
        jPanelDesarrollador.setLayout(jPanelDesarrolladorLayout);
        jPanelDesarrolladorLayout.setHorizontalGroup(
            jPanelDesarrolladorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDesarrolladorLayout.createSequentialGroup()
                .addGroup(jPanelDesarrolladorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelDesarrolladorLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanelDesarrolladorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelTituloAdmin1)
                            .addGroup(jPanelDesarrolladorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelDesarrolladorLayout.createSequentialGroup()
                                    .addComponent(jSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(122, 122, 122)
                                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelDesarrolladorLayout.createSequentialGroup()
                                    .addComponent(jScrollPaneDesarrollo, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jScrollPaneDesarrollo1, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanelDesarrolladorLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSliderDes, javax.swing.GroupLayout.PREFERRED_SIZE, 776, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(48, Short.MAX_VALUE))
        );
        jPanelDesarrolladorLayout.setVerticalGroup(
            jPanelDesarrolladorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDesarrolladorLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabelTituloAdmin1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanelDesarrolladorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelDesarrolladorLayout.createSequentialGroup()
                        .addComponent(jScrollPaneDesarrollo, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanelDesarrolladorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1)
                            .addGroup(jPanelDesarrolladorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton2)
                                .addComponent(jButton3))))
                    .addComponent(jScrollPaneDesarrollo1, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSliderDes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(60, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab3", jPanelDesarrollador);

        jLabelTituloAdmin2.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabelTituloAdmin2.setText("Managers");

        jTableManagersProyect.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTableManagersProyect.setFont(new Font("Arial", Font.PLAIN, 14));
        jTableManagersProyect.setRowHeight(30);

        DefaultTableCellRenderer centerRendererManagersProyecto = new DefaultTableCellRenderer();
        centerRendererManagersProyecto.setHorizontalAlignment(JLabel.CENTER);
        jTableManagersProyect.setDefaultRenderer(Object.class, centerRendererManagersProyecto);

        JTableHeader headerManagersProyect = jTableManagersProyect.getTableHeader();
        headerManagersProyect.setFont(new Font("Arial", Font.BOLD, 16));
        headerManagersProyect.setBackground(new Color(102, 205, 170)); // Color más moderno
        headerManagersProyect.setForeground(Color.BLACK);
        jTableManagersProyect.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableManagersProyectMouseClicked(evt);
            }
        });
        jScrollPaneDesarrollo2.setViewportView(jTableManagersProyect);

        jScrollPaneDesarrollo.getVerticalScrollBar().setUI(new ModernScrollBarUI());
        jScrollPaneDesarrollo.getHorizontalScrollBar().setUI(new ModernScrollBarUI());

        jTableManagersTareas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTableManagersTareas.setFont(new Font("Arial", Font.PLAIN, 14));
        jTableManagersTareas.setRowHeight(30);

        DefaultTableCellRenderer centerRendererManagersTareas = new DefaultTableCellRenderer();
        centerRendererManagersTareas.setHorizontalAlignment(JLabel.CENTER);
        jTableManagersTareas.setDefaultRenderer(Object.class, centerRendererManagersTareas);

        JTableHeader headerManagersTareas = jTableManagersTareas.getTableHeader();
        headerManagersTareas.setFont(new Font("Arial", Font.BOLD, 16));
        headerManagersTareas.setBackground(new Color(102, 205, 170));
        headerManagersTareas.setForeground(Color.BLACK);
        jScrollPaneDesarrollo3.setViewportView(jTableManagersTareas);

        jScrollPaneDesarrollo.getVerticalScrollBar().setUI(new ModernScrollBarUI());
        jScrollPaneDesarrollo.getHorizontalScrollBar().setUI(new ModernScrollBarUI());

        jButtonManNUevoProyect.setText("Nuevo Proyecto");
        jButtonManNUevoProyect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonManNUevoProyectActionPerformed(evt);
            }
        });

        jButtonManModifiProyect.setText("Editar Proyecto");
        jButtonManModifiProyect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonManModifiProyectActionPerformed(evt);
            }
        });

        jButtonManBorrarProyect.setText("Borrar Proyecto");
        jButtonManBorrarProyect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonManBorrarProyectActionPerformed(evt);
            }
        });

        jButtonManCerrarProyect.setText("Cerrar  Proyecto");
        jButtonManCerrarProyect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonManCerrarProyectActionPerformed(evt);
            }
        });

        jButtonManCreaInforme.setText("Crear Informe");
        jButtonManCreaInforme.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonManCreaInformeActionPerformed(evt);
            }
        });

        jButtonManNuevaTarea.setText("Nueva Tarea");
        jButtonManNuevaTarea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonManNuevaTareaActionPerformed(evt);
            }
        });

        jButtonManBorrarTarea.setText("Borrar Tarea");
        jButtonManBorrarTarea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonManBorrarTareaActionPerformed(evt);
            }
        });

        jButtonManAsignaTarea.setText("Asignar Tarea");
        jButtonManAsignaTarea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonManAsignaTareaActionPerformed(evt);
            }
        });

        jButtonManModifiTarea.setText("Editar Tarea");
        jButtonManModifiTarea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonManModifiTareaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jScrollPaneDesarrollo2, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(jScrollPaneDesarrollo3, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabelTituloAdmin2)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonManNUevoProyect)
                            .addComponent(jButtonManModifiProyect))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonManCerrarProyect)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jButtonManBorrarProyect)
                                .addGap(18, 18, 18)
                                .addComponent(jButtonManCreaInforme)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonManNuevaTarea)
                            .addComponent(jButtonManModifiTarea))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonManAsignaTarea)
                            .addComponent(jButtonManBorrarTarea))))
                .addContainerGap(88, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabelTituloAdmin2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPaneDesarrollo2, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPaneDesarrollo3, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonManNUevoProyect)
                            .addComponent(jButtonManBorrarProyect)
                            .addComponent(jButtonManCreaInforme))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonManModifiProyect)
                            .addComponent(jButtonManCerrarProyect)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonManNuevaTarea)
                            .addComponent(jButtonManBorrarTarea))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonManModifiTarea)
                            .addComponent(jButtonManAsignaTarea))))
                .addContainerGap(58, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanelManagerLayout = new javax.swing.GroupLayout(jPanelManager);
        jPanelManager.setLayout(jPanelManagerLayout);
        jPanelManagerLayout.setHorizontalGroup(
            jPanelManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanelManagerLayout.setVerticalGroup(
            jPanelManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelManagerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab4", jPanelManager);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 54, 830, 590));
        jTabbedPane1.setEnabledAt(0, false);
        jTabbedPane1.setEnabledAt(1, false);
        jTabbedPane1.setEnabledAt(2, false);
        jTabbedPane1.setEnabledAt(3, false);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldInicioIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldInicioIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldInicioIDActionPerformed

    private void jButtonIDIndexActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonIDIndexActionPerformed
        new JDialogUsuarios(this, rootPaneCheckingEnabled).setVisible(true);
    }//GEN-LAST:event_jButtonIDIndexActionPerformed

    private void jButtonInicioEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInicioEnviarActionPerformed
        if(jTextFieldInicioID.getText().equals("") || jPasswordField.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Debe de introducir todos los campos", "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        Query q = BaseDatos.getBBDD().query();
        q.constrain(Usuarios.class);
        q.descend("id").constrain(Integer.parseInt(jTextFieldInicioID.getText()+"")).equal().and(q.descend("contrasena").constrain(jPasswordField.getText()+"").equal());
        ObjectSet oS = q.execute();
        if (oS.hasNext()) {
            usu = (Usuarios)oS.next();
            switch (Usuarios.tipoUsuario(usu)) {
                case "Administrador":
                    jTabbedPane1.setSelectedIndex(1);
                    iniciarTablaUsu();
                    break;
                case "Desarrollador":
                    jTabbedPane1.setSelectedIndex(2);
                    iniciarTablaDesEnProceso();
                    iniciarTablaDesEspera();
                    break;
                case "Managers":
                    jTabbedPane1.setSelectedIndex(3);
                    iniciarTablaManagerProyect();
                    break;
                default:
                    System.out.println("Error");
                    throw new AssertionError();
            }
            jLabelTituloPrincipalNobreUsu.setText(usu.getNombre());
            jButtonCerrarSesion.setVisible(true);
            jTextFieldInicioID.setText("");
            jPasswordField.setText("");
        }
    }//GEN-LAST:event_jButtonInicioEnviarActionPerformed

    private void jButtonCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCerrarSesionActionPerformed
        jTabbedPane1.setSelectedIndex(0);
        jButtonCerrarSesion.setVisible(false);
        jLabelTituloPrincipalNobreUsu.setText("Inicia sesion");
    }//GEN-LAST:event_jButtonCerrarSesionActionPerformed

    private void jButtonAdminEditUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAdminEditUsuarioActionPerformed
        int row = jTableAdminMostrarUsu.getSelectedRow();
        if(row>=0 && Integer.parseInt(jTableAdminMostrarUsu.getValueAt(row, 0)+"") != 1){
            rowSelect = row;
            new JDialogModUsuario(this, rootPaneCheckingEnabled).setVisible(true);
        }else
            JOptionPane.showMessageDialog(this, "Seleciona una fila porfavor y que no sea el SuperUsuario", "Error", JOptionPane.ERROR_MESSAGE);
    }//GEN-LAST:event_jButtonAdminEditUsuarioActionPerformed

    private void jButtonAdminDelUsuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAdminDelUsuActionPerformed
        int row = jTableAdminMostrarUsu.getSelectedRow();
        if(row>=0){
            int confirmado = JOptionPane.showConfirmDialog(null, "¿Desea eliminar "+jTableAdminMostrarUsu.getValueAt(jTableAdminMostrarUsu.getSelectedRow(), 0)+"?");
            if (JOptionPane.OK_OPTION == confirmado){
                Usuarios us = BaseDatos.encuentraUsu(Integer.parseInt(mU.getValueAt(row, 0)+""));
                if (((Administradores)usu).borrarUsu(us)){
                    iniciarTablaUsu();
                }else
                    JOptionPane.showConfirmDialog(null, "Error");
            }
        }else
            JOptionPane.showMessageDialog(this, "Seleciona una fila porfavor", "Error", JOptionPane.ERROR_MESSAGE);
    }//GEN-LAST:event_jButtonAdminDelUsuActionPerformed

    private void jButtonAdminAnadirUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAdminAnadirUsuarioActionPerformed
        new JDialogAnadirUsuario(this, rootPaneCheckingEnabled).setVisible(true);
    }//GEN-LAST:event_jButtonAdminAnadirUsuarioActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        new JDialogDesaInforme(this, rootPaneCheckingEnabled).setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        for (int i = 0 ; i < jSliderDes.getValue() ; ++i){
            Query q = BaseDatos.getBBDD().query();
            q.constrain(Desarrolladores.class);
            q.descend("id").constrain(usu.getId()).equal();
            ObjectSet o = q.execute();
            if (o.hasNext()) {
                usu = (Desarrolladores)o.next();
                for (Tareas t : ((Desarrolladores)usu).getTareasAssignadas()) {
                    if (t.getId() == Integer.parseInt(jTableDesarrolloTareas.getValueAt(i, 0)+"") && t.getPerteneProyecto().getId() == Integer.parseInt(jTableDesarrolloTareas.getValueAt(i, 2)+"")) {
                        ((Desarrolladores)usu).realizarTarea(t);
                    }
                }
            }
        }

        iniciarTablaDesEnProceso();
        iniciarTablaDesEspera();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jSliderDesStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSliderDesStateChanged
        jSpinner.setValue(jSliderDes.getValue());
    }//GEN-LAST:event_jSliderDesStateChanged

    private void jSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinnerStateChanged
        jSliderDes.setValue(Integer.parseInt(jSpinner.getValue()+""));
    }//GEN-LAST:event_jSpinnerStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        Query q = BaseDatos.getBBDD().query();
        q.constrain(Desarrolladores.class);
        q.descend("id").constrain(usu.getId()).equal();
        ObjectSet o = q.execute();
        if (o.hasNext()) {
            usu = (Desarrolladores)o.next();
            for (Tareas t : ((Desarrolladores)usu).getTareasAssignadas()) {
                if (t.getId() == Integer.parseInt(jTableDesarrolloTareas1.getValueAt(0, 0)+"") && t.getPerteneProyecto().getId() == Integer.parseInt(jTableDesarrolloTareas1.getValueAt(0, 2)+"")) {
                    ((Desarrolladores)usu).finalizarTarea(t);
                }
            }
        }

        iniciarTablaDesEnProceso();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTableManagersProyectMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableManagersProyectMouseClicked
        int row = jTableManagersProyect.rowAtPoint(evt.getPoint());
        if (row >= 0) {
            iniciarTablaManagerTareas(Integer.parseInt(jTableManagersProyect.getValueAt(row, 0)+""));
        }
    }//GEN-LAST:event_jTableManagersProyectMouseClicked

    private void jButtonManNUevoProyectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonManNUevoProyectActionPerformed
        new JDialogAnadirProyecto(this, rootPaneCheckingEnabled).setVisible(true);
    }//GEN-LAST:event_jButtonManNUevoProyectActionPerformed

    private void jPasswordFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswordFieldActionPerformed
        jButtonInicioEnviar.doClick();
    }//GEN-LAST:event_jPasswordFieldActionPerformed

    private void jPasswordFieldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPasswordFieldMouseClicked
        jPasswordField.selectAll();
    }//GEN-LAST:event_jPasswordFieldMouseClicked

    private void jButtonManModifiProyectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonManModifiProyectActionPerformed
        int row = jTableManagersProyect.getSelectedRow();
        if (row >= 0) {
            rowSelect = row;
            new JDialogModifiProyecto(this, rootPaneCheckingEnabled).setVisible(true);
        }
    }//GEN-LAST:event_jButtonManModifiProyectActionPerformed

    private void jButtonManBorrarProyectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonManBorrarProyectActionPerformed
        int row = jTableManagersProyect.getSelectedRow();
        if (row >= 0) {
            Query q = BaseDatos.getBBDD().query();
            q.constrain(Proyectos.class);
            q.descend("id").constrain(Integer.parseInt(jTableManagersProyect.getValueAt(row, 0)+"")).equal();
            ObjectSet oS = q.execute();
            if (oS.hasNext()) {
                ((Managers)usu).borrarProyecto((Proyectos)oS.next());
            }
            mMP.removeRow(row);
        }
    }//GEN-LAST:event_jButtonManBorrarProyectActionPerformed

    private void jButtonManCerrarProyectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonManCerrarProyectActionPerformed
        int row = jTableManagersProyect.getSelectedRow();
        if (row >= 0) {
            Query q = BaseDatos.getBBDD().query();
            q.constrain(Proyectos.class);
            q.descend("id").constrain(Integer.parseInt(jTableManagersProyect.getValueAt(row, 0)+"")).equal();
            ObjectSet oS = q.execute();
            if (oS.hasNext()) {
                ((Managers)usu).cerrarProyecto((Proyectos)oS.next());
                mMP.setValueAt("Cerrado", row, 3);
            }
        }
    }//GEN-LAST:event_jButtonManCerrarProyectActionPerformed

    private void jButtonManCreaInformeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonManCreaInformeActionPerformed
        int row = jTableManagersProyect.getSelectedRow();
        if (row >= 0) {
            Query q = BaseDatos.getBBDD().query();
            q.constrain(Proyectos.class);
            q.descend("id").constrain(Integer.parseInt(jTableManagersProyect.getValueAt(row, 0)+"")).equal();
            ObjectSet oS = q.execute();
            if (oS.hasNext()) {
                try {
                    ((Managers)usu).crearInforme((Proyectos)oS.next());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }//GEN-LAST:event_jButtonManCreaInformeActionPerformed

    private void jButtonManNuevaTareaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonManNuevaTareaActionPerformed
        int row = jTableManagersProyect.getSelectedRow();
        if (row >= 0) {
            rowSelect = row;
            new JDialogAnadirTarea(this, rootPaneCheckingEnabled).setVisible(true);
        }
    }//GEN-LAST:event_jButtonManNuevaTareaActionPerformed

    private void jButtonManBorrarTareaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonManBorrarTareaActionPerformed
        int rowProyect = jTableManagersProyect.getSelectedRow();
        int row = jTableManagersTareas.getSelectedRow();
        Proyectos p;
        if (row >= 0 && rowProyect >= 0) {
            int idProye =Integer.parseInt(jTableManagersProyect.getValueAt(rowProyect, 0)+"");
            Query q = BaseDatos.getBBDD().query();
            q.constrain(Tareas.class);
            q.descend("id").constrain(Integer.parseInt(jTableManagersTareas.getValueAt(row, 0)+"")).equal();
            ObjectSet oS = q.execute();
            if (oS.hasNext()) {
                try {
                    Tareas t = (Tareas)oS.next();
                    if (t.getPerteneProyecto().getId() == idProye) {
                        ((Managers)usu).borrarTarea(t);
                        iniciarTablaManagerTareas(idProye);
                        return;
                    }
                } catch (ExcepcionPer ex) {
                    ex.printStackTrace();
                }
            }
        }
    }//GEN-LAST:event_jButtonManBorrarTareaActionPerformed

    private void jButtonManAsignaTareaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonManAsignaTareaActionPerformed
        int row = jTableManagersProyect.getSelectedRow();
        int rowAux = jTableManagersTareas.getSelectedRow();
        if (row >= 0 && rowAux >= 0) {
            rowSelect = row;
            rowSelectAux = rowAux;
            new JDialogManagersAsignarTare(this, rootPaneCheckingEnabled).setVisible(true);
        }
    }//GEN-LAST:event_jButtonManAsignaTareaActionPerformed

    private void jButtonManModifiTareaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonManModifiTareaActionPerformed
        int rowProyect = jTableManagersProyect.getSelectedRow();
        int row = jTableManagersTareas.getSelectedRow();
        if (row >= 0 && rowProyect >= 0) {
            rowSelect = rowProyect;
            rowSelectAux = row;
            new JDialogModifiTarea(this, rootPaneCheckingEnabled).setVisible(true);
        }
    }//GEN-LAST:event_jButtonManModifiTareaActionPerformed

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
            java.util.logging.Logger.getLogger(JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButtonAdminAnadirUsuario;
    private javax.swing.JButton jButtonAdminDelUsu;
    private javax.swing.JButton jButtonAdminEditUsuario;
    private javax.swing.JButton jButtonCerrarSesion;
    private javax.swing.JButton jButtonIDIndex;
    private javax.swing.JButton jButtonInicioEnviar;
    private javax.swing.JButton jButtonManAsignaTarea;
    private javax.swing.JButton jButtonManBorrarProyect;
    private javax.swing.JButton jButtonManBorrarTarea;
    private javax.swing.JButton jButtonManCerrarProyect;
    private javax.swing.JButton jButtonManCreaInforme;
    private javax.swing.JButton jButtonManModifiProyect;
    private javax.swing.JButton jButtonManModifiTarea;
    private javax.swing.JButton jButtonManNUevoProyect;
    private javax.swing.JButton jButtonManNuevaTarea;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelTituloAdmin;
    private javax.swing.JLabel jLabelTituloAdmin1;
    private javax.swing.JLabel jLabelTituloAdmin2;
    private javax.swing.JLabel jLabelTituloPrincipal;
    private javax.swing.JLabel jLabelTituloPrincipal2;
    private javax.swing.JLabel jLabelTituloPrincipal3;
    private javax.swing.JLabel jLabelTituloPrincipalNobreUsu;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanelAdmin;
    private javax.swing.JPanel jPanelDesarrollador;
    private javax.swing.JPanel jPanelInicio;
    private javax.swing.JPanel jPanelManager;
    private javax.swing.JPasswordField jPasswordField;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPaneDesarrollo;
    private javax.swing.JScrollPane jScrollPaneDesarrollo1;
    private javax.swing.JScrollPane jScrollPaneDesarrollo2;
    private javax.swing.JScrollPane jScrollPaneDesarrollo3;
    private javax.swing.JSlider jSliderDes;
    private javax.swing.JSpinner jSpinner;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTableAdminMostrarUsu;
    private javax.swing.JTable jTableDesarrolloTareas;
    private javax.swing.JTable jTableDesarrolloTareas1;
    private javax.swing.JTable jTableManagersProyect;
    private javax.swing.JTable jTableManagersTareas;
    public javax.swing.JTextField jTextFieldInicioID;
    // End of variables declaration//GEN-END:variables
}
