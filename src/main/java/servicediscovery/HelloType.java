package servicediscovery;

import java.util.Objects;

import io.vertx.core.json.JsonObject;
import io.vertx.servicediscovery.Record;
import io.vertx.servicediscovery.spi.ServiceType;

public interface HelloType extends ServiceType{
	 String TYPE = "hello-type";
	 
	static Record createRecord(final String aName, 
							   final String aAddress, 
							   final JsonObject aMetadata) 
	{
		Objects.requireNonNull(aName);
		Objects.requireNonNull(aAddress);
		JsonObject location = new JsonObject().put("endpoint", aAddress);
		return createRecord(aName, location, aMetadata);
	}
	
	static Record createRecord(final String aName, 
							   final JsonObject aLocation, 
							   final JsonObject aMetadata) {
		Objects.requireNonNull(aName);
		Objects.requireNonNull(aLocation);
		
		Record record = new Record().setName(aName)
		           .setType(TYPE)
		           .setLocation(aLocation);
		
		if (aMetadata != null) {
			record.setMetadata(aMetadata);
		}
		return record;
	}

}
