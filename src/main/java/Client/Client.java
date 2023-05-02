package Client;

import Setting.Setting;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static int port;
    public static String host = "localhost";

    public static void main(String[] args) throws IOException {
        go();

    }

    public static void go() throws IOException {
        port = Setting.readPort();

        try (Socket socket = new Socket(host, port)) {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            Scanner scanner = new Scanner(System.in);
            String message;
            String nameClient;
            ClientThread clientThread = new ClientThread(socket);
            clientThread.start();
            System.out.println("Введите свое имя");
            nameClient = scanner.nextLine();
            out.println(nameClient + " присоединился в нам в чат");
            System.out.println(nameClient + " Введите сообщение");
            while (true) {
                message = scanner.nextLine();
                out.println(nameClient + " : " + message);
                if (message.equals("/exit")) {
                    out.println(nameClient + " покинул чат");
                    break;
                }
            }

        }
    }
}
