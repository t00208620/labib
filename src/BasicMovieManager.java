/**
 * Created by t00036478 on 08/02/2018.
 */
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class BasicMovieManager {
    private SessionFactory sessionFactory = null;
    // Creating SessionFactory using 4.2 version of Hibernate
    public void initSessionFactory(){
        if (sessionFactory == null) {
            // loads configuration and mappings
            Configuration configuration = new Configuration().configure();
            // builds a session factory from the service registry
            sessionFactory = configuration.buildSessionFactory();
        }
    }
    public void persistMovie(Movie movies) {
        Transaction tx = null;
        Session session = sessionFactory.getCurrentSession();
        try {
            tx = session.beginTransaction();
            session.save(movies);
            tx.commit();
        }
        catch(HibernateException ex) {
            if (tx != null) tx.rollback();
            throw ex;
        }
        finally{
            session.close();
        }
    }
    public static void main(String[] args){
        BasicMovieManager manager = new BasicMovieManager();
        manager.initSessionFactory();

        Movie movie = new Movie();
        movie.setId(6);
        movie.setDirector("Martin Scorsese");
        movie.setTitle("Casino");
        movie.setSynopsis("Mafia");
        System.out.println(manager);

        manager.persistMovie(movie);



    }

}



