package x.serlab;

import x.serlab.fileutils.FileReader;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerExample {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newCachedThreadPool();

        try {
            ServerSocket serverSocket = new ServerSocket(5050);

            while(true) {
                Socket socket = serverSocket.accept();
                executorService.execute(() -> handleConnection(socket));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void handleConnection(Socket socket) {
        System.out.println(Thread.currentThread());

        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String url = readHeaders(input);

            var output = new PrintWriter(socket.getOutputStream());

            File file = new File("web" + File.separator + url);
            byte[] page = FileReader.readFromFile(file);

            // file.getPath().endsWith(".html");
            String contentType = Files.probeContentType(file.toPath());

            if(!file.exists()) {
                output.println("HTTP/1.1 404");
                output.println("Content-Length:" + page.length);
                output.flush();

            } else {

                output.println("HTTP/1.1 200 OK");
                output.println("Content-Length:" + page.length);
                output.println("Content-Type:" + contentType);
                output.println("");
                // output.print(Arrays.toString(page));
                output.flush();

                var dataOut = new BufferedOutputStream(socket.getOutputStream());
                dataOut.write(page);
                dataOut.flush();
            }
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String readHeaders(BufferedReader input) throws IOException {
        String requestedUrl = "";
        while(true) {
            String headerLine = input.readLine();
            if(headerLine.startsWith("GET")) {
                requestedUrl = headerLine.split(" ")[1];
            }
            System.out.println(headerLine);
            if(headerLine.isEmpty()) {
                break;
            }
        }
        return requestedUrl;
    }

}


// java -p "core-1.0-SNAPSHOT.jar;plugin-1.0-SNAPSHOT.jar;spi-1.0-SNAPSHOT.jar;fileutils-1.0-SNAPSHOT.jar" -m core/x.serlab.ServerExample