package serviceproxy;

import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.rxjava.core.Vertx;

@ProxyGen
@VertxGen
public interface SomeDatabaseService {
	
	static SomeDatabaseService create(Vertx vertx) {
		return new SomeDatabaseServiceImpl(vertx);
	}
	
	static SomeDatabaseService createProxy(Vertx vertx, String address) {
		return new SomeDatabaseServiceVertxEBProxy(vertx, address);
	}
	
	void save(String collection, JsonObject document, Handler<AsyncResult<Void>> resultHandler);
	
	void findOne(String collection, JsonObject query,Handler<AsyncResult<JsonObject>> result);
	
	
	
	
}
