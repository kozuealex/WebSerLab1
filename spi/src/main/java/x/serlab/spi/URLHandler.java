package x.serlab.spi;

import java.io.IOException;

public interface URLHandler {
    String handleURL();
    String handlePost(String body) throws IOException;
}
