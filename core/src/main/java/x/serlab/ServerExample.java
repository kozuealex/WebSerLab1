package x.serlab;

import x.serlab.fileutils.FileReader;
import x.serlab.plugin.ProductsHandler;
import x.serlab.spi.URLHandler;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerExample {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newCachedThreadPool();

        try {
            ServerSocket serverSocket = new ServerSocket(5050);

            while (true) {
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
            var output = new PrintWriter(socket.getOutputStream());

            String url = readHeaders(input);
//            String parameter = readParameter(input);
//            String parameter2 = cutParameter(input);

//            Map<String, URLHandler> routes = new HashMap<>();
//            routes.put("/products", new ProductsHandler());
//            var handler = routes.get(url);

            File file = new File("core" + File.separator + "web" + File.separator + url);
            byte[] page = FileReader.readFromFile(file);
            String contentType = Files.probeContentType(file.toPath());


            if (url.equals("/products")) {

                var handler = new ProductsHandler();

                byte[] json = handler.handleURL().getBytes();
                output.println("HTTP/1.1 200 OK");
                output.println("Content-Length:" + json.length);
                output.println("Content-Type: application/json");
                output.println("");
                output.flush();

                var dataOut = new BufferedOutputStream(socket.getOutputStream());
                dataOut.write(json);
                dataOut.flush();


            } else if (file.exists()) {
                output.println("HTTP/1.1 200 OK");
                output.println("Content-Length:" + page.length);
                output.println("Content-Type:" + contentType);
                output.println("");
                output.flush();

                var dataOut = new BufferedOutputStream(socket.getOutputStream());
                dataOut.write(page);
                dataOut.flush();

            } else {
                output.println("HTTP/1.1 404");
                output.println("Content-Length:" + page.length);
                output.flush();
            }

            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static String readHeaders(BufferedReader input) throws IOException {
        String requestedUrl = "";
        while (true) {
            String headerLine = input.readLine();
            if (headerLine.startsWith("GET")) {
                requestedUrl = headerLine.split(" ")[1];
            }

            if (headerLine.startsWith("HEAD")) {
                requestedUrl = headerLine.split(" ")[1];
            }

            if (headerLine.startsWith("POST")) {
                requestedUrl = headerLine.split(" ")[1];
            }
            System.out.println(headerLine);

            if (headerLine.isEmpty()) {
                break;
            }
        }
        return requestedUrl;
    }

    private static String readParameter(BufferedReader input) throws IOException {
        String parameter = "";
        while (true) {
            String headerLine = input.readLine();
            if (headerLine.startsWith("GET")) {
                parameter = headerLine.split("\\?")[1];
            }
            if (headerLine.isEmpty()) {
                break;
            }
        }
        return parameter;
    }

    private static String cutParameter(BufferedReader input) throws IOException {
        String parameter = readParameter(input);
        while (true) {
            parameter = parameter.split("\\=")[1];

            if (parameter.isEmpty()) {
                break;
            }
        }
        return parameter;
    }
}


// java -p "core-1.0-SNAPSHOT.jar;plugin-1.0-SNAPSHOT.jar;spi-1.0-SNAPSHOT.jar;fileutils-1.0-SNAPSHOT.jar;jpa-1.0-SNAPSHOT.jar;gson-2.8.6.jar" -m core/x.serlab.ServerExample