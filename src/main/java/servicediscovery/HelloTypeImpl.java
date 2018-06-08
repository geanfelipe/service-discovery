package servicediscovery;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.servicediscovery.Record;
import io.vertx.servicediscovery.ServiceDiscovery;
import io.vertx.servicediscovery.ServiceReference;

public class HelloTypeImpl implements HelloType{
	@Override
	public String name() {
		return TYPE;
	}

	@Override
	public ServiceReference get(final Vertx vertx, 
								final ServiceDiscovery discovery, 
								final Record record,
								final JsonObject configuration) 
	{
		return new HelloReference(vertx, discovery,record, configuration);
	}
	
	public void sayGean() {
		System.out.println("say gean");
	}
	
}
