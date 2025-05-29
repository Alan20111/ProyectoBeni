package inicioarchivos;

/**
 * @author markb
 */
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import static inicioarchivos.InicioArchivos.obAlumnos;
import static inicioarchivos.InicioArchivos.obInscripciones;
import static inicioarchivos.InicioArchivos.obMaterias;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

class BotonTabla extends AbstractCellEditor implements TableCellRenderer, TableCellEditor, ActionListener {

    JButton a_boton;
    JTable a_tabla;
    String a_tipoTabla;

    public BotonTabla(JTable p_tabla, String p_tipoTabla) {
        a_tabla = p_tabla;
        a_tipoTabla = p_tipoTabla;
        a_boton = new JButton("Editar");
        a_boton.addActionListener(this);
    }

    @Override
    public Object getCellEditorValue() {
        return "Editar";
    }

    @Override
    public Component getTableCellRendererComponent(JTable p_tabla, Object p_valor, boolean p_sel, boolean p_foco, int p_fila, int p_col) {
        return a_boton;
    }

    @Override
    public Component getTableCellEditorComponent(JTable p_tabla, Object p_valor, boolean p_sel, int p_fila, int p_col) {
        return a_boton;
    }

    @Override
    public void actionPerformed(ActionEvent p_evt) {
        int v_fila = a_tabla.getSelectedRow();
        String v_info = "";

        switch (a_tipoTabla) {
            case "A" ->
                v_info = "Alumno con ID: " + a_tabla.getValueAt(v_fila, 0);

            case "M" ->
                v_info = "Materia con clave: " + a_tabla.getValueAt(v_fila, 0);
            case "I" ->
                v_info = "Inscripción de alumno: " + a_tabla.getValueAt(v_fila, 0);
            default ->
                v_info = "Tabla desconocida ";
        }

        JOptionPane.showMessageDialog(null, "Editando " + v_info + " en fila: " + v_fila);
        fireEditingStopped();
    }
}

public class Ventana extends javax.swing.JFrame {

    Alumno obAlumno = new Alumno();
    Materia obMateria = new Materia();
    Inscripcion obInscripcion = new Inscripcion();
    ArchivoAlumnos obAlumnosHijo = (ArchivoAlumnos) obAlumnos;
    ArchivoMaterias obMateriasHijo = (ArchivoMaterias) obMaterias;

    DefaultTableModel TablaModelo = new DefaultTableModel();

    public Ventana() {

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent p_evt) {
                m_preCerrar();
            }
        }
        );
        moreComponents();
        initComponents();
        TablaModelo(TableAlumnos, "No.Control", "Nombre", "Semestre", 'A');
        TablaModelo(TableMaterias, "No.Clave", "Nombre", "Creditos", 'M');
        TablaModelo(TableInscripciones, "No.Control", "No.Clave", "", 'I');
    }

    public void TablaModelo(JTable Tabla, String a, String b, String c, char op) {
        DefaultTableModel TablaModelo = new DefaultTableModel();
        TablaModelo.addColumn(a);
        TablaModelo.addColumn(b);
        TablaModelo.addColumn(c);
        TablaModelo.addColumn("Acciones");

        switch (op) {
            case 'A' -> {
                Tabla.setModel(TablaModelo);
                TableColumn v_colAccion = Tabla.getColumnModel().getColumn(3);
                v_colAccion.setCellRenderer(new BotonTabla(Tabla, "A"));
                v_colAccion.setCellEditor(new BotonTabla(Tabla, "A"));

                try {
                    int i = 0;
                    while ((obAlumnos.canal.length() / 53) > i) {
                        System.out.println(i);
                        obAlumnosHijo.leerReg(obAlumnos.canal, i, obAlumno);
                        TablaModelo.addRow(new Object[]{obAlumno.nroCtrl, obAlumno.nom, obAlumno.sem, "Editar"});
                        i++;
                    }
                } catch (IOException e) {
                    // Maneja la excepción según sea necesario
                }
            }
            case 'M' -> {
                Tabla.setModel(TablaModelo);
                TableColumn v_colAccion = Tabla.getColumnModel().getColumn(3);
                v_colAccion.setCellRenderer(new BotonTabla(Tabla, "M"));
                v_colAccion.setCellEditor(new BotonTabla(Tabla, "M"));

                try {
                    int i = 0;
                    while ((obMaterias.canal.length() / 37) > i) {
                        System.out.println(i);
                        obMateriasHijo.leerReg(obMaterias.canal, i, obMateria);
                        TablaModelo.addRow(new Object[]{obMateria.cve, obMateria.nom, obMateria.cred, ""});
                        i++;
                    }
                } catch (IOException e) {
                    // Maneja la excepción según sea necesario
                }
            }
            case 'I' -> {
                Tabla.setModel(TablaModelo);
                TableColumn v_colAccion = Tabla.getColumnModel().getColumn(3);
                v_colAccion.setCellRenderer(new BotonTabla(Tabla, "I"));
                v_colAccion.setCellEditor(new BotonTabla(Tabla, "I"));

                ArchivoInscripciones obInscripcionesHijo = (ArchivoInscripciones) obInscripciones;
                try {
                    int i = 0;
                    while ((obInscripciones.canal.length() / 53) > i) {
                        System.out.println(i);
                        obInscripcionesHijo.leerReg(obInscripciones.canal, i, obInscripcion);
                        TablaModelo.addRow(new Object[]{obInscripcion.nroCtrl, obInscripcion.cve, "", ""});
                        i++;
                    }
                } catch (IOException e) {
                    // Maneja la excepción según sea necesario
                }
            }
            default ->
                throw new AssertionError();
        }

    }

    private void m_preCerrar() {
        try {
            obAlumnos.canal.close();
        } catch (IOException ex) {
            Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            obMaterias.canal.close();
        } catch (IOException ex) {
            Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            obInscripciones.canal.close();
            obInscripciones.canal1.close();
            obInscripciones.canal2.close();
        } catch (IOException ex) {
            Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
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

        btgOpciones = new javax.swing.ButtonGroup();
        pnlTODO = new javax.swing.JPanel();
        pnlInicio = new javax.swing.JPanel();
        pnlITitulo = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        Opciones = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        pnlIOpciones = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        Duke = new javax.swing.JLabel();
        pnlAlumno = new javax.swing.JPanel();
        pnlATitulo = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 32767));
        rbtCrear = new javax.swing.JRadioButton();
        rbtBuscar = new javax.swing.JRadioButton();
        pnlAMain = new javax.swing.JPanel();
        pnlATabla = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TableAlumnos = new javax.swing.JTable();
        pnlAOpciones = new javax.swing.JPanel();
        pnlCrearA = new javax.swing.JPanel();
        pnlCrearAData = new javax.swing.JPanel();
        CNoCrtl = new javax.swing.JPanel();
        LCNoCtrl = new javax.swing.JLabel();
        txtCNoCtrl = new javax.swing.JTextField();
        CNombre = new javax.swing.JPanel();
        LCNombre = new javax.swing.JLabel();
        txtCNombre = new javax.swing.JTextField();
        CSemestre = new javax.swing.JPanel();
        LCSemestre = new javax.swing.JLabel();
        txtCSemestre = new javax.swing.JTextField();
        btnCAgregar = new javax.swing.JButton();
        pnlBuscar = new javax.swing.JPanel();
        pnlBuscarAData = new javax.swing.JPanel();
        BNoCtrl = new javax.swing.JPanel();
        lblBNoCtrl = new javax.swing.JLabel();
        txtBNoCtrl = new javax.swing.JTextField();
        btnCAgregar3 = new javax.swing.JButton();
        pnlMaterias = new javax.swing.JPanel();
        pnlMTitulo = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        pnlMMain = new javax.swing.JPanel();
        pnlMTabla = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        TableMaterias = new javax.swing.JTable();
        btnMActualizar = new javax.swing.JButton();
        pnlCrearM = new javax.swing.JPanel();
        pnlCrearMData = new javax.swing.JPanel();
        CNoCrtl1 = new javax.swing.JPanel();
        LCClave = new javax.swing.JLabel();
        txtCClave = new javax.swing.JTextField();
        CNombre1 = new javax.swing.JPanel();
        LCNombreM = new javax.swing.JLabel();
        txtCNombreM = new javax.swing.JTextField();
        CSemestre1 = new javax.swing.JPanel();
        LCCreditos = new javax.swing.JLabel();
        txtCCreditos = new javax.swing.JTextField();
        btnCAgregar1 = new javax.swing.JButton();
        pnlInscribir = new javax.swing.JPanel();
        pnlNMain = new javax.swing.JPanel();
        pnlNTitulo = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        pnlNOpciones = new javax.swing.JPanel();
        pnlCrearM1 = new javax.swing.JPanel();
        pnlCrearMData1 = new javax.swing.JPanel();
        INoCrtl = new javax.swing.JPanel();
        LINoCtrl = new javax.swing.JLabel();
        txtINoCtrl = new javax.swing.JTextField();
        IMateria = new javax.swing.JPanel();
        LINombreM = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        btnCAgregar2 = new javax.swing.JButton();
        pnlNMain1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        btnNReporte = new javax.swing.JButton();
        pnlNTablaReporte = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        TableInscripciones = new javax.swing.JTable();
        filler8 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 32767));
        filler9 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 32767));
        pnlCreditos = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        menInicio = new javax.swing.JMenu();
        menAlumno = new javax.swing.JMenu();
        menMateria = new javax.swing.JMenu();
        menInscribir = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistema de Inscripciones");
        setFont(new java.awt.Font("Segoe UI Semibold", 0, 10)); // NOI18N
        setForeground(java.awt.Color.white);
        setLocationByPlatform(true);
        setMinimumSize(new java.awt.Dimension(900, 600));
        setName("Registro de Alumnos"); // NOI18N
        getContentPane().setLayout(new java.awt.BorderLayout(10, 10));

        pnlTODO.setBackground(new java.awt.Color(255, 255, 255));
        pnlTODO.setLayout(new java.awt.CardLayout());

        pnlInicio.setLayout(new java.awt.BorderLayout());

        pnlITitulo.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 10, 10));

        jLabel3.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        jLabel3.setText("Bienvenido al Sistema De Inscripciones!");
        jLabel3.setAlignmentX(0.5F);
        pnlITitulo.add(jLabel3);

        pnlInicio.add(pnlITitulo, java.awt.BorderLayout.NORTH);

        Opciones.setLayout(new javax.swing.BoxLayout(Opciones, javax.swing.BoxLayout.PAGE_AXIS));

        jLabel4.setText("¿Qué deseas hacer el día de hoy?");
        jLabel4.setAlignmentX(0.5F);
        Opciones.add(jLabel4);

        jButton1.setText("Alumno");
        jButton1.setAlignmentX(0.5F);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InicioMAlumno(evt);
            }
        });
        pnlIOpciones.add(jButton1);

        jButton2.setText("Materia");
        jButton2.setAlignmentX(0.5F);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InicioMMateria(evt);
            }
        });
        pnlIOpciones.add(jButton2);

        jButton3.setText("Inscibir");
        jButton3.setAlignmentX(0.5F);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InicioMInscribir(evt);
            }
        });
        pnlIOpciones.add(jButton3);

        Opciones.add(pnlIOpciones);

        Duke.setIcon(DukeInicio);
        Duke.setAlignmentX(0.5F);
        Duke.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                EasterEgg(evt);
            }
        });
        Opciones.add(Duke);

        pnlInicio.add(Opciones, java.awt.BorderLayout.CENTER);

        pnlTODO.add(pnlInicio, "card2");

        pnlAlumno.setLayout(new java.awt.BorderLayout(10, 10));

        pnlATitulo.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel1.setText("Registro de Alumnos");
        pnlATitulo.add(jLabel1);
        pnlATitulo.add(filler1);

        btgOpciones.add(rbtCrear);
        rbtCrear.setSelected(true);
        rbtCrear.setText("Crear");
        rbtCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtCrearActionPerformed(evt);
            }
        });
        pnlATitulo.add(rbtCrear);

        btgOpciones.add(rbtBuscar);
        rbtBuscar.setText("Buscar");
        rbtBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        rbtBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtBuscarActionPerformed(evt);
            }
        });
        pnlATitulo.add(rbtBuscar);

        pnlAlumno.add(pnlATitulo, java.awt.BorderLayout.NORTH);

        pnlAMain.setLayout(new java.awt.BorderLayout(10, 10));

        pnlATabla.setEnabled(false);
        pnlATabla.setLayout(new java.awt.BorderLayout(10, 10));

        TableAlumnos.setModel(TableAlumnos.getModel());
        TableAlumnos.setColumnSelectionAllowed(true);
        jScrollPane2.setViewportView(TableAlumnos);
        TableAlumnos.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        pnlATabla.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        pnlAMain.add(pnlATabla, java.awt.BorderLayout.CENTER);

        pnlAOpciones.setLayout(new java.awt.CardLayout());

        pnlCrearA.setAlignmentX(0.0F);
        pnlCrearA.setLayout(new javax.swing.BoxLayout(pnlCrearA, javax.swing.BoxLayout.LINE_AXIS));

        pnlCrearAData.setAlignmentX(0.0F);
        pnlCrearAData.setAlignmentY(1.0F);
        pnlCrearAData.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        CNoCrtl.setLayout(new java.awt.BorderLayout());

        LCNoCtrl.setText("No. Control:");
        LCNoCtrl.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        CNoCrtl.add(LCNoCtrl, java.awt.BorderLayout.NORTH);

        txtCNoCtrl.setColumns(8);
        CNoCrtl.add(txtCNoCtrl, java.awt.BorderLayout.CENTER);

        pnlCrearAData.add(CNoCrtl);

        CNombre.setLayout(new java.awt.BorderLayout());

        LCNombre.setText("Nombre:");
        CNombre.add(LCNombre, java.awt.BorderLayout.NORTH);

        txtCNombre.setColumns(35);
        CNombre.add(txtCNombre, java.awt.BorderLayout.CENTER);

        pnlCrearAData.add(CNombre);

        CSemestre.setLayout(new java.awt.BorderLayout());

        LCSemestre.setText("Sem:");
        CSemestre.add(LCSemestre, java.awt.BorderLayout.NORTH);

        txtCSemestre.setColumns(2);
        CSemestre.add(txtCSemestre, java.awt.BorderLayout.CENTER);

        pnlCrearAData.add(CSemestre);

        pnlCrearA.add(pnlCrearAData);

        btnCAgregar.setText("Agregar");
        btnCAgregar.setAlignmentY(1.0F);
        btnCAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AgregarAlumno(evt);
            }
        });
        pnlCrearA.add(btnCAgregar);

        pnlAOpciones.add(pnlCrearA, "card2");

        pnlBuscar.setLayout(new javax.swing.BoxLayout(pnlBuscar, javax.swing.BoxLayout.LINE_AXIS));

        pnlBuscarAData.setAlignmentY(1.0F);
        pnlBuscarAData.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        BNoCtrl.setLayout(new java.awt.BorderLayout());

        lblBNoCtrl.setText("No. Control:");
        lblBNoCtrl.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        BNoCtrl.add(lblBNoCtrl, java.awt.BorderLayout.NORTH);

        txtBNoCtrl.setColumns(8);
        txtBNoCtrl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BuscarAlumno(evt);
            }
        });
        BNoCtrl.add(txtBNoCtrl, java.awt.BorderLayout.CENTER);

        pnlBuscarAData.add(BNoCtrl);

        pnlBuscar.add(pnlBuscarAData);

        btnCAgregar3.setText("Buscar");
        btnCAgregar3.setAlignmentY(1.0F);
        btnCAgregar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BuscarAlumno(evt);
            }
        });
        pnlBuscar.add(btnCAgregar3);

        pnlAOpciones.add(pnlBuscar, "card3");

        pnlAMain.add(pnlAOpciones, java.awt.BorderLayout.NORTH);

        pnlAlumno.add(pnlAMain, java.awt.BorderLayout.CENTER);

        pnlTODO.add(pnlAlumno, "card3");

        pnlMaterias.setLayout(new java.awt.BorderLayout(10, 10));

        pnlMTitulo.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel2.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel2.setText("Registro de Materias");
        pnlMTitulo.add(jLabel2);

        pnlMaterias.add(pnlMTitulo, java.awt.BorderLayout.NORTH);

        pnlMMain.setLayout(new java.awt.BorderLayout(5, 5));

        pnlMTabla.setEnabled(false);
        pnlMTabla.setLayout(new java.awt.BorderLayout(10, 10));

        TableMaterias.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Clave", "Nombre", "Creditos", "Inscripciones"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Byte.class, java.lang.Long.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TableMaterias.setColumnSelectionAllowed(true);
        jScrollPane3.setViewportView(TableMaterias);
        TableMaterias.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        if (TableMaterias.getColumnModel().getColumnCount() > 0) {
            TableMaterias.getColumnModel().getColumn(0).setResizable(false);
            TableMaterias.getColumnModel().getColumn(1).setResizable(false);
            TableMaterias.getColumnModel().getColumn(2).setResizable(false);
            TableMaterias.getColumnModel().getColumn(3).setResizable(false);
        }

        pnlMTabla.add(jScrollPane3, java.awt.BorderLayout.CENTER);

        btnMActualizar.setText("Actualizar Tabla");
        btnMActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MActualizar(evt);
            }
        });
        pnlMTabla.add(btnMActualizar, java.awt.BorderLayout.PAGE_END);

        pnlMMain.add(pnlMTabla, java.awt.BorderLayout.CENTER);

        pnlCrearM.setLayout(new javax.swing.BoxLayout(pnlCrearM, javax.swing.BoxLayout.LINE_AXIS));

        pnlCrearMData.setAlignmentY(1.0F);
        pnlCrearMData.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        CNoCrtl1.setLayout(new java.awt.BorderLayout());

        LCClave.setText("Clave:");
        LCClave.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        CNoCrtl1.add(LCClave, java.awt.BorderLayout.NORTH);

        txtCClave.setColumns(8);
        CNoCrtl1.add(txtCClave, java.awt.BorderLayout.CENTER);

        pnlCrearMData.add(CNoCrtl1);

        CNombre1.setLayout(new java.awt.BorderLayout());

        LCNombreM.setText("Nombre:");
        CNombre1.add(LCNombreM, java.awt.BorderLayout.NORTH);

        txtCNombreM.setColumns(35);
        txtCNombreM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCNombreMActionPerformed(evt);
            }
        });
        CNombre1.add(txtCNombreM, java.awt.BorderLayout.CENTER);

        pnlCrearMData.add(CNombre1);

        CSemestre1.setLayout(new java.awt.BorderLayout());

        LCCreditos.setText("Cred:");
        CSemestre1.add(LCCreditos, java.awt.BorderLayout.NORTH);

        txtCCreditos.setColumns(2);
        CSemestre1.add(txtCCreditos, java.awt.BorderLayout.CENTER);

        pnlCrearMData.add(CSemestre1);

        pnlCrearM.add(pnlCrearMData);

        btnCAgregar1.setText("Agregar");
        btnCAgregar1.setAlignmentY(1.0F);
        btnCAgregar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AgregarMateria(evt);
            }
        });
        pnlCrearM.add(btnCAgregar1);

        pnlMMain.add(pnlCrearM, java.awt.BorderLayout.NORTH);

        pnlMaterias.add(pnlMMain, java.awt.BorderLayout.CENTER);

        pnlTODO.add(pnlMaterias, "card4");

        pnlInscribir.setLayout(new java.awt.BorderLayout(10, 10));

        pnlNMain.setLayout(new javax.swing.BoxLayout(pnlNMain, javax.swing.BoxLayout.PAGE_AXIS));

        pnlNTitulo.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel6.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel6.setText("Inscribir Alumnos");
        pnlNTitulo.add(jLabel6);

        pnlNMain.add(pnlNTitulo);

        pnlNOpciones.setLayout(new java.awt.BorderLayout());

        pnlCrearM1.setLayout(new javax.swing.BoxLayout(pnlCrearM1, javax.swing.BoxLayout.LINE_AXIS));

        pnlCrearMData1.setAlignmentY(1.0F);
        pnlCrearMData1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        INoCrtl.setLayout(new java.awt.BorderLayout());

        LINoCtrl.setText("No. Control");
        LINoCtrl.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        INoCrtl.add(LINoCtrl, java.awt.BorderLayout.NORTH);

        txtINoCtrl.setColumns(8);
        INoCrtl.add(txtINoCtrl, java.awt.BorderLayout.CENTER);

        pnlCrearMData1.add(INoCrtl);

        IMateria.setLayout(new java.awt.BorderLayout());

        LINombreM.setText("Nombre de la Materia:");
        IMateria.add(LINombreM, java.awt.BorderLayout.NORTH);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Fundamentos en Programación", "Programación Orientada a Objetos", " " }));
        IMateria.add(jComboBox1, java.awt.BorderLayout.PAGE_END);

        pnlCrearMData1.add(IMateria);

        pnlCrearM1.add(pnlCrearMData1);

        btnCAgregar2.setText("Inscribir");
        btnCAgregar2.setAlignmentY(1.0F);
        btnCAgregar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCAgregar2AgregarMateria(evt);
            }
        });
        pnlCrearM1.add(btnCAgregar2);

        pnlNOpciones.add(pnlCrearM1, java.awt.BorderLayout.NORTH);

        pnlNMain.add(pnlNOpciones);

        pnlInscribir.add(pnlNMain, java.awt.BorderLayout.NORTH);

        pnlNMain1.setLayout(new java.awt.BorderLayout(10, 10));

        jLabel7.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel7.setText("Generar Reporte");
        jLabel7.setAlignmentY(1.0F);
        pnlNMain1.add(jLabel7, java.awt.BorderLayout.NORTH);

        btnNReporte.setText("Crear Reporte");
        btnNReporte.setAlignmentY(1.0F);
        btnNReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNReporteAgregarMateria(evt);
            }
        });
        pnlNMain1.add(btnNReporte, java.awt.BorderLayout.SOUTH);

        pnlNTablaReporte.setLayout(new java.awt.BorderLayout());

        TableInscripciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane4.setViewportView(TableInscripciones);

        pnlNTablaReporte.add(jScrollPane4, java.awt.BorderLayout.CENTER);

        pnlNMain1.add(pnlNTablaReporte, java.awt.BorderLayout.CENTER);

        pnlInscribir.add(pnlNMain1, java.awt.BorderLayout.CENTER);

        pnlTODO.add(pnlInscribir, "card5");

        getContentPane().add(pnlTODO, java.awt.BorderLayout.CENTER);
        getContentPane().add(filler8, java.awt.BorderLayout.WEST);
        getContentPane().add(filler9, java.awt.BorderLayout.EAST);

        pnlCreditos.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        jLabel5.setForeground(new java.awt.Color(153, 153, 153));
        jLabel5.setText("®NULL NULL NULL Sahur");
        pnlCreditos.add(jLabel5);

        getContentPane().add(pnlCreditos, java.awt.BorderLayout.SOUTH);

        menInicio.setText("Inicio");
        menInicio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mostrarMInicio(evt);
            }
        });
        jMenuBar1.add(menInicio);

        menAlumno.setText("Alumno");
        menAlumno.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mostrarMAlumno(evt);
            }
        });
        jMenuBar1.add(menAlumno);

        menMateria.setText("Materia");
        menMateria.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mostrarMMateria(evt);
            }
        });
        jMenuBar1.add(menMateria);

        menInscribir.setText("Inscribir");
        menInscribir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mostrarMInscribir(evt);
            }
        });
        jMenuBar1.add(menInscribir);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void moreComponents() {
        DukeInicio = new ImageIcon(Objects.requireNonNull(getClass().getResource("/inicioarchivos/Duke.png")));
        Image image = DukeInicio.getImage();
        Image duke = image.getScaledInstance(120, 200, Image.SCALE_SMOOTH);
        DukeInicio = new ImageIcon(duke);
    }

    private void rbtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtBuscarActionPerformed
        pnlCrearA.setVisible(rbtCrear.isSelected());
        pnlBuscar.setVisible(rbtBuscar.isSelected());
    }//GEN-LAST:event_rbtBuscarActionPerformed

    private void rbtCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtCrearActionPerformed
        pnlCrearA.setVisible(rbtCrear.isSelected());
        pnlBuscar.setVisible(rbtBuscar.isSelected());
    }//GEN-LAST:event_rbtCrearActionPerformed

    //MOSTRAR PANELES
    // {
    private void mostrarMInicio(MouseEvent evt) {//GEN-FIRST:event_mostrarMInicio
        // MOSTRAR Menu Inicio
        CardLayout = (CardLayout) pnlTODO.getLayout();
        CardLayout.show(pnlTODO, "card2");
    }//GEN-LAST:event_mostrarMInicio

    private void mostrarMAlumno(java.awt.event.MouseEvent evt) {
        // MOSTRAR Menu Alumno
        CardLayout = (CardLayout) pnlTODO.getLayout();
        CardLayout.show(pnlTODO, "card3");
    }

    private void mostrarMMateria(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mostrarMMateria
        // MOSTRAR Menu Alumno
        CardLayout = (CardLayout) pnlTODO.getLayout();
        CardLayout.show(pnlTODO, "card4");
    }//GEN-LAST:event_mostrarMMateria

    private void mostrarMInscribir(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mostrarMInscribir
        // MOSTRAR Menu Inscribir
        CardLayout = (CardLayout) pnlTODO.getLayout();
        CardLayout.show(pnlTODO, "card5");
    }//GEN-LAST:event_mostrarMInscribir
    // }

    //BOTONES PARA INICIO
    private void InicioMAlumno(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InicioMAlumno
        // MOSTRAR Alumno desde Inicio
        CardLayout = (CardLayout) pnlTODO.getLayout();
        CardLayout.show(pnlTODO, "card3");
    }//GEN-LAST:event_InicioMAlumno

    private void InicioMMateria(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InicioMMateria
        // MOSTRAR Materia desde Inicio
        CardLayout = (CardLayout) pnlTODO.getLayout();
        CardLayout.show(pnlTODO, "card4");
    }//GEN-LAST:event_InicioMMateria

    private void InicioMInscribir(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InicioMInscribir
        // MOSTRAR Inscribir desde Inicio
        CardLayout = (CardLayout) pnlTODO.getLayout();
        CardLayout.show(pnlTODO, "card5");
    }//GEN-LAST:event_InicioMInscribir

    //BOTONES DE MAS
    private void AgregarMateria(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AgregarMateria
        boolean a = txtCClave.getText().isEmpty(),
                b = txtCNombreM.getText().isEmpty(),
                c = txtCCreditos.getText().isEmpty();
        if (!b & !c & !a) {
            if (obMaterias.altas(obMaterias.canal,
                    txtCClave.getText(),
                    txtCNombreM.getText(),
                    Byte.parseByte(txtCCreditos.getText()))) {
                TablaModelo(TableMaterias, "No.Clave", "Nombre", "Creditos", 'M');
            }

            txtCClave.setText(null);
            txtCNombreM.setText(null);
            txtCCreditos.setText(null);
            btnCAgregar.setEnabled(true);
        } else {
            if (a & b & c) {
                JOptionPane.showMessageDialog(null, "Ingrese una Materia", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Faltan datos por agregar", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_AgregarMateria

    private void AgregarAlumno(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AgregarAlumno
        boolean a = txtCNoCtrl.getText().isEmpty(),
                b = txtCNombre.getText().isEmpty(),
                c = txtCSemestre.getText().isEmpty();
        if (!b & !c & !a) {
            if (obAlumnos.altas(obAlumnos.canal,
                    txtCNoCtrl.getText(),
                    txtCNombre.getText(),
                    Byte.parseByte(txtCSemestre.getText()))) {
                TablaModelo(TableAlumnos, "No.Control", "Nombre", "Semestre", 'A');
            }
            txtCNoCtrl.setText(null);
            txtCNombre.setText(null);
            txtCSemestre.setText(null);
            btnCAgregar.setEnabled(true);
        } else {
            if (a & b & c) {
                JOptionPane.showMessageDialog(null, "Ingrese un alumno", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Faltan datos por agregar", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_AgregarAlumno

    private void btnCAgregar2AgregarMateria(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCAgregar2AgregarMateria
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCAgregar2AgregarMateria

    private void btnNReporteAgregarMateria(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNReporteAgregarMateria
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNReporteAgregarMateria

    private void BuscarAlumno(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuscarAlumno
        boolean a = txtBNoCtrl.getText().isEmpty();
        if (!a) {
            int n = obAlumnos.busqueda(obAlumnos.canal, txtBNoCtrl.getText());
            ArchivoAlumnos obAlumnosHijo = (ArchivoAlumnos) obAlumnos;
            obAlumnosHijo.leerReg(obAlumnos.canal, n, obAlumno);
            System.out.println(obAlumno.nom + " " + obAlumno.nroCtrl);//Alumno
            txtBNoCtrl.setText(null);
            btnCAgregar.setEnabled(true);
        } else {
            JOptionPane.showMessageDialog(null, "Ingrese un alumno", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_BuscarAlumno

    private void MActualizar(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MActualizar
        // TODO add your handling code here:
    }//GEN-LAST:event_MActualizar

    private void txtCNombreMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCNombreMActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCNombreMActionPerformed

    private void EasterEgg(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EasterEgg
        JOptionPane.showMessageDialog(rootPane, "Funciona");
    }//GEN-LAST:event_EasterEgg

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
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ventana().setVisible(true);

            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel BNoCtrl;
    private javax.swing.JPanel CNoCrtl;
    private javax.swing.JPanel CNoCrtl1;
    private javax.swing.JPanel CNombre;
    private javax.swing.JPanel CNombre1;
    private javax.swing.JPanel CSemestre;
    private javax.swing.JPanel CSemestre1;
    private javax.swing.JLabel Duke;
    private javax.swing.JPanel IMateria;
    private javax.swing.JPanel INoCrtl;
    private javax.swing.JLabel LCClave;
    private javax.swing.JLabel LCCreditos;
    private javax.swing.JLabel LCNoCtrl;
    private javax.swing.JLabel LCNombre;
    private javax.swing.JLabel LCNombreM;
    private javax.swing.JLabel LCSemestre;
    private javax.swing.JLabel LINoCtrl;
    private javax.swing.JLabel LINombreM;
    private javax.swing.JPanel Opciones;
    javax.swing.JTable TableAlumnos;
    private javax.swing.JTable TableInscripciones;
    private javax.swing.JTable TableMaterias;
    private javax.swing.ButtonGroup btgOpciones;
    private javax.swing.JButton btnCAgregar;
    private javax.swing.JButton btnCAgregar1;
    private javax.swing.JButton btnCAgregar2;
    private javax.swing.JButton btnCAgregar3;
    private javax.swing.JButton btnMActualizar;
    private javax.swing.JButton btnNReporte;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler8;
    private javax.swing.Box.Filler filler9;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lblBNoCtrl;
    private javax.swing.JMenu menAlumno;
    private javax.swing.JMenu menInicio;
    private javax.swing.JMenu menInscribir;
    private javax.swing.JMenu menMateria;
    private javax.swing.JPanel pnlAMain;
    private javax.swing.JPanel pnlAOpciones;
    private javax.swing.JPanel pnlATabla;
    private javax.swing.JPanel pnlATitulo;
    public javax.swing.JPanel pnlAlumno;
    javax.swing.JPanel pnlBuscar;
    private javax.swing.JPanel pnlBuscarAData;
    javax.swing.JPanel pnlCrearA;
    private javax.swing.JPanel pnlCrearAData;
    javax.swing.JPanel pnlCrearM;
    javax.swing.JPanel pnlCrearM1;
    private javax.swing.JPanel pnlCrearMData;
    private javax.swing.JPanel pnlCrearMData1;
    private javax.swing.JPanel pnlCreditos;
    private javax.swing.JPanel pnlIOpciones;
    private javax.swing.JPanel pnlITitulo;
    private javax.swing.JPanel pnlInicio;
    private javax.swing.JPanel pnlInscribir;
    private javax.swing.JPanel pnlMMain;
    private javax.swing.JPanel pnlMTabla;
    private javax.swing.JPanel pnlMTitulo;
    public javax.swing.JPanel pnlMaterias;
    private javax.swing.JPanel pnlNMain;
    private javax.swing.JPanel pnlNMain1;
    private javax.swing.JPanel pnlNOpciones;
    private javax.swing.JPanel pnlNTablaReporte;
    private javax.swing.JPanel pnlNTitulo;
    javax.swing.JPanel pnlTODO;
    private javax.swing.JRadioButton rbtBuscar;
    private javax.swing.JRadioButton rbtCrear;
    private javax.swing.JTextField txtBNoCtrl;
    private javax.swing.JTextField txtCClave;
    private javax.swing.JTextField txtCCreditos;
    private javax.swing.JTextField txtCNoCtrl;
    private javax.swing.JTextField txtCNombre;
    private javax.swing.JTextField txtCNombreM;
    private javax.swing.JTextField txtCSemestre;
    private javax.swing.JTextField txtINoCtrl;
    // End of variables declaration//GEN-END:variables
    // Extra variables @m
    java.awt.CardLayout CardLayout;
    javax.swing.ImageIcon DukeInicio;
    // End of extra variables @m
}
