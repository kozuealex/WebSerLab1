module x.serlab.jpa {
    requires java.persistence;
    exports x.serlab.jpa;
    opens x.serlab.jpa to com.google.gson;
}