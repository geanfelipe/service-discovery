package servicediscovery;

public class HelloServiceImpl implements HelloService{
	private boolean isLowerCase;
	 
	public HelloServiceImpl(final boolean aIsLowerCase) {
		isLowerCase = aIsLowerCase;
	}
	
	@Override
	public void sayHello(String aName) {
		String hello = "HELLO %s!!!\n";

        if (isLowerCase) {
        	hello = hello.toLowerCase();
        }
        System.out.printf(hello, aName);
    }
}
