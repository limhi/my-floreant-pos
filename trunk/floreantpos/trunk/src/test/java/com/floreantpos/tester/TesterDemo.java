package test.java.com.floreantpos.tester;

import static org.junit.Assert.*;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import com.floreantpos.main.Application;
import com.floreantpos.model.Tester;
import com.floreantpos.model.dao.TesterDAO;
import com.floreantpos.model.dao._RootDAO;

public class TesterDemo {
	private static Log logger = LogFactory.getLog(TesterDemo.class);
	
	@Test
	public void dbTest(){
		
//		_RootDAO.initialize();
//		// luke 20120114 start
//				if (true) {
//					// db test
//					TesterDAO testerDAO = new TesterDAO();
//					// add some
//					Tester tester = new Tester(101, "Hi101");
//					tester.setRate(101.001);
//					testerDAO.saveOrUpdate(tester);
//
//					// list all
//					List<Tester> testeres = testerDAO.findAll();
//					for (Tester t : testeres) {
//						logger.debug("Id=" + t.getId() + "\tName=" + t.getName()
//								+ "Rate=" + t.getRate());
//					}
//					logger.debug("-------");
//
//					// update some
//					tester = testerDAO.findAll().get(0);
//					tester.setName("Hi202");
//					tester.setRate(202.002);
//					testerDAO.saveOrUpdate(tester);
//
//					// list all
//					testeres = testerDAO.findAll();
//					for (Tester t : testeres) {
//						logger.debug("Id=" + t.getId() + "\tName=" + t.getName()
//								+ "Rate=" + t.getRate());
//					}
//					logger.debug("-------");
//
//					// remove one
//					tester = testerDAO.findAll().get(0);
//					testerDAO.delete(tester);
//				}
//				// luke 20120114 end
	}

}
