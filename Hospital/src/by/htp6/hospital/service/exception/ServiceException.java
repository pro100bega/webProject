package by.htp6.hospital.service.exception;

public class ServiceException extends Exception{
	private static final long serialVersionUID = -3757273135607617166L;

	public ServiceException() {
		super();
	}
	
	public ServiceException(String message){
		super(message);
	}
	
	public ServiceException(Exception e){
		super(e);
	}
	
	public ServiceException(String message, Exception e){
		super(message, e);
	}
}
