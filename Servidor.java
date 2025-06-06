import java.io.*;
import java.net.*;

class TCPServer {
public static void main(String argv[]) throws Exception
{


    ServerSocket welcomeSocket = new ServerSocket( 7544);
    System.out.println("Esperando conexiones...");


    while(true) {

        Socket connectionSocket = welcomeSocket.accept();
        BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
        System.out.println("Conexión establecida con el cliente: " + connectionSocket.getInetAddress() + ":" + connectionSocket.getPort());

        DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());

        double examenes[] = new double[3];
        double quices[] = new double[6];
        double examenesPorcentados[] = new double[3];
        double quicesPorcentados[] = new double[6];

        double porcentajeExamenes = 26.6666666667;
        double porcentajeQuices = 3.3333333333;

        for(int i = 0; i < 3; i++) {
            
            String nota = inFromClient.readLine();
            examenes[i] = Double.parseDouble(nota);
        }
        for(int i = 0; i < 6; i++) {
            
            String nota = inFromClient.readLine();
            quices[i] = Double.parseDouble(nota);
        }

        for(int i = 0; i < 3; i++){
            examenesPorcentados[i] = examenes[i] * porcentajeExamenes /100;
        }
        for(int i = 0; i < 6; i++){
            quicesPorcentados[i] = quices[i] * porcentajeQuices / 100;
        }

        double sumaExamenes = examenesPorcentados[0] + examenesPorcentados[1] + examenesPorcentados[2];
        double sumaQuices = quicesPorcentados[0] + quicesPorcentados[1] + quicesPorcentados[2] + quicesPorcentados[3] + quicesPorcentados[4] + quicesPorcentados[5];
        double promedio = sumaExamenes + sumaQuices;
        long sumaQuicesRedondeado = Math.round(sumaQuices);
        long sumaExamenesRedondeado = Math.round(sumaExamenes);
        long promedioRedondeado = Math.round(promedio);

        

        for(int i = 0; i < 3; i++){
            outToClient.writeBytes("Porcentaje del examen " + (i + 1) + ": " + examenesPorcentados[i] + "\n");
        }
        for(int i = 0; i < 6; i++){
            outToClient.writeBytes("Porcentaje del quiz " + (i + 1) + ": " + quicesPorcentados[i] + "\n");
        }
        outToClient.writeBytes("Suma de examenes: " + sumaExamenesRedondeado + "\n");
        outToClient.writeBytes("Suma de quices: " + sumaQuicesRedondeado + "\n");
        outToClient.writeBytes("Promedio Final " + promedioRedondeado + "\n");

        
        
    }
    }
    }
