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

            String address = readHeaders(input);

            String url;
            String query;
            int idNumber = 0;

            if (address.contains("?")) {
                int position1 = address.indexOf("?");
                System.out.println("position of '?': " + position1);
                url = address.substring(0, position1);
                System.out.println("url: " + url);
                query = address.substring(position1 + 1, address.length());
                System.out.println("query: " + query);
                int position2 = query.indexOf("=");
                idNumber = Integer.parseInt(query.substring(position2 + 1, query.length()));
            } else {
                url = address;
            }

//            Map<String, URLHandler> routes = new HashMap<>();
//            routes.put("/products", new ProductsHandler());
//            var handler = routes.get(url);


            File file = new File("core" + File.separator + "web" + File.separator + url);
            byte[] page = FileReader.readFromFile(file);
            String contentType = Files.probeContentType(file.toPath());



            if (url.equals("/products")) {

                ProductsHandler handler = new ProductsHandler();
                byte[] body = input.readLine().getBytes();

                if(body.length > 0) {

                    String outputBody = new String(body, StandardCharsets.UTF_8);
                    System.out.println(outputBody);


                } else {

                    handler.setIdNumber(idNumber);

                    byte[] json = handler.handleURL().getBytes();
                    output.println("HTTP/1.1 200 OK");
                    output.println("Content-Length:" + json.length);
                    output.println("Content-Type: application/json");
                    output.println("");
                    output.flush();

                    var dataOut = new BufferedOutputStream(socket.getOutputStream());
                    dataOut.write(json);
                    dataOut.flush();

                }

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

}
