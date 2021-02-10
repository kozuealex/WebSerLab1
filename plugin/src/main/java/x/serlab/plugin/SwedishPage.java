package x.serlab.plugin;

import x.serlab.spi.Page;

public class SwedishPage implements Page {

    public SwedishPage() {
        System.out.println("Swedish constructor");
    }

    @Override
    public void execute() {
        System.out.println("This is Swedish Page");
    }
}
