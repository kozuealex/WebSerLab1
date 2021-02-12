module x.serlab.jpa {
    requires java.sql;
    requires net.bytebuddy;
    requires com.fasterxml.classmate;
    requires java.xml.bind;
    requires java.persistence;
    exports x.serlab.jpa;
    opens x.serlab.jpa to com.google.gson, org.hibernate.orm.core;

}