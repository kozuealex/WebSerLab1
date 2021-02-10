package x.serlab;

import x.serlab.spi.Page;

import java.util.ServiceLoader;

public class PluginExample {

    // java -p "core-1.0-SNAPSHOT.jar;plugin-1.0-SNAPSHOT.jar;spi-1.0-SNAPSHOT.jar" -m core/x.serlab.PluginExample
    // Ladda in ExternalPage klassen när programmet körs.

    public static void main(String[] args) {

        ServiceLoader<Page> loader = ServiceLoader.load(Page.class);

        for(var page : loader) {
            page.execute();
        }

//        loader.reload();
//
//        for(var page : loader) {
//            page.execute();
//        }

//        Iterator<Page> iterator = loader.iterator();
//        while(iterator.hasNext()) {
//            Page page = iterator.next();
//        }

    }
}
