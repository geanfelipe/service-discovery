package serviceproxy;

import io.vertx.codegen.annotations.ProxyClose;
import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;

@ProxyGen
@VertxGen
public interface MyDatabaseConnection {

	void insert(JsonObject someData);

	void commit(Handler<AsyncResult<Void>> resultHandler);

	@ProxyClose
	void close();
}
