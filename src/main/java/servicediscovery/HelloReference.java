package servicediscovery;

import java.util.Objects;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.servicediscovery.Record;
import io.vertx.servicediscovery.ServiceDiscovery;
import io.vertx.servicediscovery.types.AbstractServiceReference;

public class HelloReference extends AbstractServiceReference<HelloService>{
	private final JsonObject conf;
	 
	public HelloReference(final Vertx aVertx, 
						  final ServiceDiscovery aDiscovery,
						  final Record aRecord, 
						  final JsonObject aConfiguration) 
	{
		super(aVertx, aDiscovery, aRecord);
		Objects.requireNonNull(aConfiguration);
		conf = aConfiguration; // (1)
	}

	@Override
	protected HelloService retrieve() {
		Boolean isLowerCase = conf.getBoolean("lower-case", Boolean.TRUE);
        return new HelloServiceImpl(isLowerCase);
	}
	
	@Override
    public void close() { // (3)
		super.close();
    }

}
