import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.WindowConstants;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrmOrdenamiento extends JFrame {

    private JButton btnOrdenarBurbuja;
    private JButton btnOrdenarRapido;
    private JButton btnOrdenarInsercion;
    private JButton btnOrdenarSeleccion;
    private JButton btnOrdenarMezcla;
    private JToolBar tbOrdenamiento;
    private JComboBox cmbCriterio;
    private JTextField txtTiempo;
    private JButton btnBuscar;
    private JTextField txtBuscar;

    private JTable tblDocumentos;

    public FrmOrdenamiento() {

        tbOrdenamiento = new JToolBar();
        btnOrdenarBurbuja = new JButton();
        btnOrdenarInsercion = new JButton();
        btnOrdenarRapido = new JButton();
        cmbCriterio = new JComboBox();
        txtTiempo = new JTextField();
        btnOrdenarSeleccion = new JButton();
        btnOrdenarMezcla = new JButton();
        btnBuscar = new JButton();
        txtBuscar = new JTextField();

        tblDocumentos = new JTable();

        setSize(600, 400);
        setTitle("Ordenamiento Documentos");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        btnOrdenarBurbuja.setIcon(new ImageIcon(getClass().getResource("/iconos/Ordenar.png")));
        btnOrdenarBurbuja.setToolTipText("Ordenar Burbuja");
        btnOrdenarBurbuja.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnOrdenarBurbujaClick(evt);
            }
        });
        tbOrdenamiento.add(btnOrdenarBurbuja);

        btnOrdenarRapido.setIcon(new ImageIcon(getClass().getResource("/iconos/OrdenarRapido.png")));
        btnOrdenarRapido.setToolTipText("Ordenar Rapido");
        btnOrdenarRapido.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnOrdenarRapidoClick(evt);
            }
        });
        tbOrdenamiento.add(btnOrdenarRapido);

        btnOrdenarInsercion.setIcon(new ImageIcon(getClass().getResource("/iconos/OrdenarInsercion.png")));
        btnOrdenarInsercion.setToolTipText("Ordenar Inserción");
        btnOrdenarInsercion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnOrdenarInsercionClick(evt);
            }
        });
        tbOrdenamiento.add(btnOrdenarInsercion);

        cmbCriterio.setModel(new DefaultComboBoxModel(
                new String[] { "Nombre Completo, Tipo de Documento", "Tipo de Documento, Nombre Completo" }));
        tbOrdenamiento.add(cmbCriterio);
        tbOrdenamiento.add(txtTiempo);

        btnBuscar.setIcon(new ImageIcon(getClass().getResource("/iconos/Buscar.png")));
        btnBuscar.setToolTipText("Buscar");
        btnBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnBuscar(evt);
            }
        });
        tbOrdenamiento.add(btnBuscar);
        tbOrdenamiento.add(txtBuscar);
        //Se agrego el boton de seleccion para verificar su tiempo
        btnOrdenarSeleccion = new JButton("Ordenar Selección");
        btnOrdenarSeleccion.setToolTipText("Ordenar por Selección");
        btnOrdenarSeleccion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnOrdenarSeleccionClick(evt);
            }
        });
        tbOrdenamiento.add(btnOrdenarSeleccion);
        //Se agrego el boton de mezcla para verificar su tiempo
        btnOrdenarMezcla = new JButton("Ordenar Mezcla");
        btnOrdenarMezcla.setToolTipText("Ordenar por Mezcla");
        btnOrdenarMezcla.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnOrdenarMezclaClick(evt);
            }
        });
        tbOrdenamiento.add(btnOrdenarMezcla);

        JScrollPane spDocumentos = new JScrollPane(tblDocumentos);

        getContentPane().add(tbOrdenamiento, BorderLayout.NORTH);
        getContentPane().add(spDocumentos, BorderLayout.CENTER);

        String nombreArchivo = System.getProperty("user.dir")
                + "/src/datos/Datos.csv";

        Documento.desdeArchivo(nombreArchivo);
        Documento.mostrar(tblDocumentos);
    }

    private void btnOrdenarBurbujaClick(ActionEvent evt) {
        if (cmbCriterio.getSelectedIndex() >= 0) {
            Util.iniciarCronometro();
            Documento.ordenarBurbuja(cmbCriterio.getSelectedIndex());
            txtTiempo.setText(Util.getTextoTiempoCronometro());
            Documento.mostrar(tblDocumentos);
        } else {
            JOptionPane.showMessageDialog(null, "Elija el criterio de ordenamiento");
        }
    }

    private void btnOrdenarRapidoClick(ActionEvent evt) {
        if (cmbCriterio.getSelectedIndex() >= 0) {
            Util.iniciarCronometro();
            Documento.ordenarRapido(0, Documento.getTamaño() - 1, cmbCriterio.getSelectedIndex());
            txtTiempo.setText(Util.getTextoTiempoCronometro());
            Documento.mostrar(tblDocumentos);
        } else {
            JOptionPane.showMessageDialog(null, "Elija el criterio de ordenamiento");
        }
    }

    private void btnOrdenarInsercionClick(ActionEvent evt) {
        if (cmbCriterio.getSelectedIndex() >= 0) {
            Util.iniciarCronometro();
            Documento.ordenarInsercion(cmbCriterio.getSelectedIndex());
            txtTiempo.setText(Util.getTextoTiempoCronometro());
            Documento.mostrar(tblDocumentos);
        } else {
            JOptionPane.showMessageDialog(null, "Elija el criterio de ordenamiento");
        }
    }

    private void btnOrdenarSeleccionClick(ActionEvent evt) {
        if (cmbCriterio.getSelectedIndex() >= 0) {
            Util.iniciarCronometro();
            Documento.ordenarSeleccion(cmbCriterio.getSelectedIndex());
            txtTiempo.setText(Util.getTextoTiempoCronometro());
            Documento.mostrar(tblDocumentos);
        } else {
            JOptionPane.showMessageDialog(null, "Elija el criterio de ordenamiento");
        }
    }

    private void btnOrdenarMezclaClick(ActionEvent evt){
        if (cmbCriterio.getSelectedIndex() >= 0) {
            Util.iniciarCronometro();
            Documento.ordenarMezcla(cmbCriterio.getSelectedIndex());
            txtTiempo.setText(Util.getTextoTiempoCronometro());
            Documento.mostrar(tblDocumentos);
        } else {
            JOptionPane.showMessageDialog(null, "Elija el criterio de ordenamiento");
        }

    }

    private void btnBuscar(ActionEvent evt) {
       
    }  
    
}