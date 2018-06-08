package serviceproxy;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.rxjava.core.Vertx;

public class SomeDatabaseServiceVertxEBProxy implements SomeDatabaseService {
	
	public SomeDatabaseServiceVertxEBProxy() {
	
	}
	
	public SomeDatabaseServiceVertxEBProxy(Vertx vertx, String address) {
		
	}

	@Override
	public void save(String collection, JsonObject document, Handler<AsyncResult<Void>> resultHandler) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void findOne(String collection, JsonObject query, Handler<AsyncResult<JsonObject>> result) {
		// TODO Auto-generated method stub
		
	}
	
}
