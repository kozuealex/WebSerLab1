package x.serlab.jpa;

import java.net.ProtocolFamily;
import java.util.List;

public interface DAO {

    List<Products> printAll();

    List<Products> findById(int id);

    void createNew(Products p);
}
