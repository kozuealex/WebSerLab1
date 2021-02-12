package x.serlab.jpa;

public class DAOMain {
    public static void main(String[] args) {

        DAO dao = new DAOImpl();
        dao.findById(1);
    }
}
