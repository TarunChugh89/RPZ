package CustomException;

public class JSONObjectFetchException extends Exception {
	
	
		public JSONObjectFetchException(String errorMessage, Throwable e)
		{
			super(errorMessage,e);
		}
		

}
