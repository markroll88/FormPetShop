import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.*;
public class ClientesMascotas extends JFrame  {
    //establecer la conexion
    Connection connection;
    //Llamado para insertar
    PreparedStatement psmt;

    //llamado para insertar
    Statement stmt;

    ResultSet rs;
    DefaultListModel mod = new DefaultListModel<>();

    private JTextField textFieldId;
    private JTextField textFieldNombre;
    private JTextField textFieldApellido;
    private JTextField textFieldMascota;
    private JTextField textFieldEdad;
    private JTextField textFieldAlimento;
    private JTextField textFieldFecha;
    private JTextField textFieldEmail;
    private JTextField textFieldTelefono;
    private JTextField textFieldUsuario;
    private JTextField textFieldClave;
    private JRadioButton agregarRadioButton;
    private JRadioButton modificarRadioButton;
    private JRadioButton borrarRadioButton;
    private JRadioButton ingresarRadioButton;
    private JRadioButton salirRadioButton;
    private JList lista;
    private JRadioButton consultarRadioButton;
    private JPanel panelForm;
    private JLabel labelId;
   //  Connection connection;
public ClientesMascotas() {
    consultarRadioButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                listar();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

        }
    });


    ingresarRadioButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                insertar();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

        }
    });
}
//metodo para el listado de clientes y macotas
public void listar() throws SQLException {
    conectar();
    lista.setModel(mod);
    stmt = connection.createStatement();
    rs = stmt.executeQuery("SELECT id, nombre, apellido, mascota, apellido, mascota FROM clientesmascotas");
    mod.removeAllElements();
    while(rs.next()){
        mod.addElement(rs.getString(1)+""+rs.getString(2)+""+rs.getString(3)+""+rs.getString(3));

    }

}
//metodo para insertar datos
public void insertar() throws SQLException{
    conectar();
    psmt = connection.prepareStatement("INSERT INTO clientesmascotas VALUES (?,?,?,?,?,?,?,?,?,?,? ");
    psmt.setInt(1,Integer.parseInt(textFieldId.getText()));
    psmt.setString(2,textFieldNombre.getText());
    psmt.setString(3,textFieldApellido.getText());
    psmt.setString(4,textFieldMascota.getText());
    psmt.setInt(5,Integer.parseInt(textFieldEdad.getText()));
    psmt.setString(6, textFieldAlimento.getText());
    psmt.setDate(7,Date.valueOf(textFieldFecha.getText()));
    psmt.setString(8, textFieldEmail.getText());
    psmt.setString(9, textFieldTelefono.getText());
    psmt.setString(10,textFieldUsuario.getText());
    psmt.setString(11,textFieldClave.getText());
   //condicion que nos liste si se ingresa mas de una fila
    if (psmt.executeUpdate()>0){
        lista.setModel(mod);
        mod.removeAllElements();
        mod.addElement("Se ha insertado exitosamente");

        textFieldId.setText("");
        textFieldNombre.setText("");
        textFieldApellido.setText("");
        textFieldMascota.setText("");
        textFieldEdad.setText("");
        textFieldAlimento.setText("");
        textFieldFecha.setText("");
        textFieldEmail.setText("");
        textFieldTelefono.setText("");
        textFieldUsuario.setText("");
        textFieldClave.setText("");
    }

}


//metodo update
 // metodo delate
 //metodo salir, titlle, etc
public static void main(String[]args){
    ClientesMascotas clientesform= new ClientesMascotas();
    clientesform.setContentPane(new ClientesMascotas().panelForm);
    clientesform.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    clientesform.setVisible(true);
    clientesform.pack();

}

    //metodo conectar nuestra base de datos sql utilzando try/catch, nuestra url y nombre de base de datos
    public void conectar() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/tienda_crud", "root", "");
            System.out.println("La conexion se ha realizado exitosamente");
        } catch (SQLException e) {
            throw new RuntimeException(e);


        }

    }
}