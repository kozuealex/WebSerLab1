package x.serlab.spi;

import java.io.IOException;

public interface URLHandler {
    String handleURL();
    void handlePost(String body) throws IOException;
}
