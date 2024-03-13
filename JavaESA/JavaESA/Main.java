package JavaESA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        final int PORTA = 12345;

        try {
            ServerSocket servidor = new ServerSocket(PORTA);
            System.out.println("Esperando a conexão do cliente.");
            Socket cliente = servidor.accept();
            System.out.println("Cliente conectado.");
            // InputStreamReader objetoA = new InputStreamReader(cliente.getInputStream())
            // //

            BufferedReader entrada = new BufferedReader(
                    new InputStreamReader(cliente.getInputStream()));
            PrintWriter saida = new PrintWriter(cliente.getOutputStream(), true);
            BufferedReader teclado = new BufferedReader(
                    new InputStreamReader(System.in));

            FileWriter arquivoLog = new FileWriter("ServerLog.txt", true);
            BufferedWriter escritorLog = new BufferedWriter(arquivoLog);
            escritorLog.append("Log da conversa do servidor");
            escritorLog.flush();

            String mensagemCliente;
            while ((mensagemCliente = entrada.readLine()) != null) {
                System.out.println("Cliente:  " + mensagemCliente);
                escritorLog.append(mensagemCliente + '\n');

                escritorLog.flush();

                System.out.print("Servidor: ");
                String mensagemServidor = teclado.readLine();
                saida.println(mensagemServidor + '\n');
                escritorLog.flush();
            }

            servidor.close();
            cliente.close();
            escritorLog.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
