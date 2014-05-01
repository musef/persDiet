package control;

import java.util.List;

/**
 * interface to beans JPA.
 * 
 * @author musef
 * 
 * @version 1.0
 * 
 */

public interface DbOperations {
	
	public boolean record(Object obj);
	public boolean delete(long ident);
	public boolean modify(long ident, Object obj);
	public String[] read(long ident);
	
	public List<String[]> showAll(String keyUser);

}
