import x.serlab.jpa.DAO;
import x.serlab.jpa.DAOImpl;

module x.serlab.jpa {
    requires java.sql;
    requires net.bytebuddy;
    requires com.fasterxml.classmate;
    requires java.xml.bind;
    requires java.persistence;
    requires com.fasterxml.jackson.databind;
    exports x.serlab.jpa;
    opens x.serlab.jpa to com.google.gson, org.hibernate.orm.core;
    provides DAO with DAOImpl;
}