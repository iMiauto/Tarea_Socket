import java.io.*;
import java.net.*;

class TCPServer {
public static void main(String argv[]) throws Exception
{


    ServerSocket welcomeSocket = new ServerSocket(6789);
    System.out.println("Esperando conexiones...");


    while(true) {

        Socket connectionSocket = welcomeSocket.accept();
        BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));

        DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());


        double ex1 = Double.parseDouble(inFromClient.readLine());
        double ex2 = Double.parseDouble(inFromClient.readLine());
        double ex3 = Double.parseDouble(inFromClient.readLine());

        double q1 = Double.parseDouble(inFromClient.readLine());
        double q2 = Double.parseDouble(inFromClient.readLine());
        double q3 = Double.parseDouble(inFromClient.readLine());
        double q4 = Double.parseDouble(inFromClient.readLine());
        double q5 = Double.parseDouble(inFromClient.readLine());
        double q6 = Double.parseDouble(inFromClient.readLine());

        double promedioExamenes = (ex1 + ex2 + ex3) /3;
        double promedioQuices = (q1 + q2 + q3 + q4 + q5 + q6) / 6;

        double promedioFinal = (promedioExamenes * 0.8) + (promedioQuices * 0.2);


        outToClient.writeBytes("Promedio exa");
    }
    }
    }
// Comentario: El servidor escucha en el puerto 6789 y espera conexiones de clientes.


// cambio de prueba