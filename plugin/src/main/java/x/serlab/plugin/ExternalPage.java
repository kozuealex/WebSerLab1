package x.serlab.plugin;

import x.serlab.spi.Page;

public class ExternalPage implements Page {

    public ExternalPage() {
        System.out.println("Constructor of External Page");
    }

    @Override
    public void execute() {
        System.out.println("This is External Page");
    }
}