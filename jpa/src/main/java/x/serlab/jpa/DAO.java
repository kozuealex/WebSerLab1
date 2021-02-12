package x.serlab.jpa;

import java.util.List;

public interface DAO {
    List<Products> findById(int id);
}
