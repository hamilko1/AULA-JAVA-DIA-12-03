package JavaESA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Cliente {
    public static void main(String[] args) {
        final String HOST = "localhost";
        final int PORTA = 12345;

        try {
            Socket socket = new Socket(HOST, PORTA);

            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            PrintWriter saida = new PrintWriter(socket.getOutputStream(), true);

            BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));

            String mensagemServidor;
            while (true) {
                System.out.print("Cliente:  ");
                String mensagemCliente = teclado.readLine();
                saida.println(mensagemCliente);

                mensagemServidor = entrada.readLine();
                System.out.println("Servidor:  " + mensagemServidor);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
