import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
class TCPClient {
 /**
 * @param argv
 * @throws Exception
 */
public static void main(String argv[]) throws Exception
 {
    JFrame frame = new JFrame("Cliente");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(300, 200);
    frame.setVisible(true);
    frame.setLocationRelativeTo(null);
      
    Container contentPane = frame.getContentPane();
    contentPane.setLayout(null);
    contentPane.setBackground(java.awt.Color.white);

    JLabel labelCalculo = new JLabel("Obtenga su nota de cálculo aquí");
    labelCalculo.setBounds(50, 50, 200, 20);
    contentPane.add(labelCalculo);


    JLabel label_Parcial1JLabel = new JLabel("Ingrese la nota del primer parcial: ");
    label_Parcial1JLabel.setBounds(50, 70, 200, 20);
    contentPane.add(label_Parcial1JLabel);

    JTextField txtPrimeroparcial = new JTextField();
    txtPrimeroparcial.setBounds(50, 90, 200, 20);
    contentPane.add(txtPrimeroparcial);
 

    JLabel label_Parcial2JLabel = new JLabel("Ingrese la nota del segundo parcial: ");
    label_Parcial2JLabel.setBounds(50, 90, 200, 20);
    contentPane.add(label_Parcial2JLabel);
    JTextField txtSegundoparcial = new JTextField();
    txtSegundoparcial.setBounds(50, 110, 200, 20);
    contentPane.add(txtSegundoparcial);

    JLabel label_Parcial3JLabel = new JLabel("Ingrese la nota del tercer parcial: ");
    label_Parcial3JLabel.setBounds(50, 110, 200, 20);
    contentPane.add(label_Parcial3JLabel);
    JTextField txtTerceroparcial = new JTextField();
    txtTerceroparcial.setBounds(50, 130, 200, 20);
    contentPane.add(txtTerceroparcial);

    JLabel labelPrimerquiz = new JLabel("Ingrese la nota del primer quiz: ");
    labelPrimerquiz.setBounds(50, 130, 200, 20);
    contentPane.add(labelPrimerquiz);
    JTextField txtPrimerquiz = new JTextField();
    txtPrimerquiz.setBounds(50, 150, 200, 20);
    contentPane.add(txtPrimerquiz);

    JLabel labelsegundo = new JLabel("Ingrese la nota del segundo quiz: ");
    labelsegundo.setBounds(50, 150, 200, 20);
    contentPane.add(labelsegundo);
    JTextField txtSegundooquiz = new JTextField();
    txtSegundooquiz.setBounds(50, 170, 200, 20);
    contentPane.add(txtSegundooquiz);

    JLabel labelTercerquiz = new JLabel("Ingrese la nota del tercer quiz: ");
    labelTercerquiz.setBounds(50, 170, 200, 20);
    contentPane.add(labelTercerquiz);
    JTextField txtTercerquiz = new JTextField();
    txtTercerquiz.setBounds(50, 190, 200, 20);
    contentPane.add(txtTercerquiz);

    JLabel labelcuartoquiz = new JLabel("Ingrese la nota del cuarto quiz: ");
    labelcuartoquiz.setBounds(50, 190, 200, 20);
    contentPane.add(labelcuartoquiz);
    JTextField txtCuartoquiz = new JTextField();
    txtCuartoquiz.setBounds(50, 210, 200, 20);
    contentPane.add(txtCuartoquiz);

    JLabel labelQuintoquiz = new JLabel("Ingrese la nota del quinto quiz: ");
    labelQuintoquiz.setBounds(50, 210, 200, 20);
    contentPane.add(labelQuintoquiz);
    JTextField txtQuintoquiz = new JTextField();
    txtQuintoquiz.setBounds(50, 230, 200, 20);
    contentPane.add(txtQuintoquiz);

    JLabel labelSextoquiz = new JLabel("Ingrese la nota del sexto quiz: ");
    labelSextoquiz.setBounds(50, 230, 200, 20);
    contentPane.add(labelSextoquiz);
    JTextField txtSextoquiz = new JTextField();
    txtSextoquiz.setBounds(50, 250, 200, 20);
    contentPane.add(txtSextoquiz);

    JTextArea mostrar = new JTextArea();
    mostrar.setBounds(50, 270, 200, 100);
    mostrar.setEditable(false);
    contentPane.add(mostrar);

    JButton btnCalcular = new JButton("Calcular");
    btnCalcular.setBounds(50, 380, 200, 20);
    contentPane.add(btnCalcular);

    btnCalcular.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            double primeroparcial = Double.parseDouble(txtPrimeroparcial.getText());
            double segundoparcial = Double.parseDouble(txtSegundoparcial.getText());
            double terceroparcial = Double.parseDouble(txtTerceroparcial.getText());
            double primerquiz = Double.parseDouble(txtPrimerquiz.getText());
            double segundooquiz = Double.parseDouble(txtSegundooquiz.getText());
            double tercerquiz = Double.parseDouble(txtTercerquiz.getText());
            double cuartoquiz = Double.parseDouble(txtCuartoquiz.getText());
            double quintoquiz = Double.parseDouble(txtQuintoquiz.getText());
            double sextooquiz = Double.parseDouble(txtSextoquiz.getText());
            
            double notas[] = {primeroparcial, segundoparcial, terceroparcial, primerquiz, segundooquiz, tercerquiz, cuartoquiz, quintoquiz, sextooquiz};
            String  StringNotas []= {String.valueOf(primeroparcial), String.valueOf(segundoparcial), String.valueOf(terceroparcial), String.valueOf(primerquiz), String.valueOf(segundooquiz), String.valueOf(tercerquiz), String.valueOf(cuartoquiz), String.valueOf(quintoquiz), String.valueOf(sextooquiz)};
            boolean validacion = false;

            if(validacioncampos(StringNotas)){
              validacion = false;
            if(esNumerico(notas)){
             validacion = false;
             }
            
             if(metodo_servidor()){
            
             }
            }

        }
    });
    
 }
 public void  metodo_servidor(){

 BufferedReader inFromUser =
 new BufferedReader(new InputStreamReader(System.in));
 Socket clientSocket = new Socket("Miauto", 6789);
 DataOutputStream outToServer =
 new DataOutputStream(clientSocket.getOutputStream());
 BufferedReader inFromServer =
 new BufferedReader(new
 InputStreamReader(clientSocket.getInputStream()));
 sentence = inFromUser.readLine();
 outToServer.writeBytes(sentence + '\n');
 modifiedSentence = inFromServer.readLine();
 System.out.println("FROM SERVER: " + modifiedSentence);
 clientSocket.close();
 } 
public static boolean esNumerico(double[] notas) {
    for (double nota : notas) {
        if (nota < 0 || nota > 100) {
           JOptionPane.showMessageDialog(null, "Error: La nota " + nota + " no es válida. Debe estar entre 0 y 100.");
            return true;
        }
       
    }
    return false;

}
public static boolean validacioncampos(String[] StringNotas) {
    for (String nota : StringNotas) {
        if (nota.isEmpty()) {
            return true;
        }
        try {
            Double.parseDouble(nota);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error: La nota " + nota + " no es válida.");
            return true;
        }
    }
    return false;
    
}
}
