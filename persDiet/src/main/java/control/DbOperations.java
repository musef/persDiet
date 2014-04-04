package control;

import java.util.List;

public interface DbOperations {
	
	public boolean record(Object obj);
	public boolean delete(long ident);
	public boolean modify(long ident, Object obj);
	public String[] read(long ident);
	
	public List<String[]> showAll(String keyUser);

}
