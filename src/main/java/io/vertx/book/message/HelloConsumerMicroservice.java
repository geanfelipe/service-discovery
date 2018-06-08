package io.vertx.book.message;

import io.vertx.core.json.JsonObject;
import io.vertx.rxjava.core.AbstractVerticle;
import io.vertx.rxjava.core.http.HttpServerRequest;
import io.vertx.rxjava.core.http.HttpServerResponse;
import io.vertx.rxjava.ext.web.Router;
import io.vertx.rxjava.ext.web.RoutingContext;
import io.vertx.rxjava.servicediscovery.ServiceDiscovery;
import io.vertx.rxjava.servicediscovery.ServiceReference;
import io.vertx.servicediscovery.Record;
import io.vertx.servicediscovery.ServiceDiscoveryOptions;
import servicediscovery.HelloService;
import servicediscovery.HelloType;

public class HelloConsumerMicroservice extends AbstractVerticle {

	@Override
	public void start() {

		Router router = Router.router(vertx);
		router.get("/").handler(this::index);
		router.get("/hello/:nome").handler(this::sayHello);

		vertx.createHttpServer()
			.requestHandler(router::accept)
			.listen(8080);
		
		
		/* Service discovery */
		ServiceDiscovery discovery = ServiceDiscovery.create(vertx,
				new ServiceDiscoveryOptions()
					.setAnnounceAddress("service-announce")
					.setName("my-name")
				);
		
		/*create a new custom record and publish "my-service" service and unpublish it*/
//		Record record1 = new Record()
//				.setType("eventbus-service-proxy")
//				.setLocation(new JsonObject().put("endpoint", "the-service-address"))
//				.setName("my-service")
//				.setMetadata(new JsonObject().put("name", "@geanfelipe"));
//		discovery.publish(record1, ar -> {
//			if(ar.succeeded()) {
//				System.out.println("#service '"+record1.getName()+"' successfully published!#");
//				Record publishedRecord = ar.result();
//			}else {
//				System.out.println("##occurred an error with service "+record1.getName()+"##");
//			}
//		});
//		discovery.unpublish(record1.getRegistration(), ar-> {
//			if(ar.succeeded()) {
//				System.out.println("# service '"+record1.getName()+"' successfully unpublished!");
//			}else {
//				System.out.println("##occurred an error with service '"+record1.getName()+"'!##");
//			}
//		});
//		
//		
//		/*create a record from type and publish the service*/
//		Record record2 = HttpEndpoint.createRecord("some-rest-api", "legis.senado.gov.br",80,"/");
//		discovery.publish(record2, ar-> {
//			if(ar.succeeded()) {
//				System.out.println("#service '"+record2.getName()+"' successfully published!#");
//				Record publishedRecord = ar.result();
//			}else {
//				System.out.println("##occurred an error with service "+record2.getName()+"##");
//			}
//		});
//		discovery.getRecord(r-> r.getName().equals(record2.getName()), ar->{
//			if(ar.succeeded()) {
//				if(ar.result()!=null) {
//					/*retrieve the service reference*/
//					ServiceReference reference = discovery.getReference(ar.result());
//					
//					/*retrieve the service object*/
//					HttpClient client = reference.get();
//					
//					System.out.println("\n#Consuming "+record2.getName()+"#\n");
//
//					client.getNow("/dadosabertos/materia/pesquisa/lista?ufAutor=PT", response -> {
//						/*release the service*/
//						response.bodyHandler(body -> {
//							System.out.println("\\body//");
//						});
//						reference.release();
//					});
//				}else {
//					System.out.println("##error not returned nothing ##");
//				}
//			}else {
//				System.out.println("##error consume the service"+ ar.cause() +"##");
//			}
//		});
//		
//		
//		/*create a record from type and publish the service and retrive it*/
//		Record record3  = EventBusService.createRecord("event-bus-service", "my-bus", JsonObject.class.getName(),new JsonObject().put("message", "hello"));
//		discovery.publish(record3, ar -> {
//			if(ar.succeeded()) {
//				System.out.println("#service '"+record3.getName()+"' successfully published!#");
//				Record published = ar.result();
//			}else {
//				System.out.println("##occurred an error with service"+ record3.getName() +"##");
//			}
//		});
//		discovery.getRecord(new JsonObject().put("name", "event-bus-service"), ar-> {
//			if(ar.succeeded() && ar.result()!=null) {
//				ServiceReference reference = discovery.getReference(ar.result());
//				
//				JsonObject json = reference.getAs(JsonObject.class);
//				System.out.println("event-bus-service "+json.encodePrettily());
//				reference.release();
//			}else {
//				System.out.println("\n## error consume the service "+ record3.getName()  + ar.cause() +"##\n");
//			}
//		});
//		
//		
//		/*create a record from type and publish the service and retrieve it*/
//		Record record4 = MessageSource.createRecord(
//				"some-other-message-source-service", // The service name
//				"some-address", // The event bus address
//				JsonObject.class, // The message payload type
//				new JsonObject().put("some-metadata", "some value")
//				);
//		discovery.publish(record4, ar -> {
//			if(ar.succeeded()) {
//				System.out.println("#service '"+record4.getName()+"' successfully published!#");
//				Record published = ar.result();
//			}else {
//				System.out.println("##occurred an error with service"+ record4.getName() +"##");
//			}
//		});
//		discovery.getRecord(new JsonObject().put("name", "some-message-source-service"), ar -> {
//			  if (ar.succeeded() && ar.result() != null) {
//			    ServiceReference reference = discovery.getReference(ar.result());
//			    MessageConsumer<JsonObject> consumer = reference.getAs(MessageConsumer.class);
//			    consumer.handler(message -> {
//			      JsonObject payload = message.body();
//			    });
//			    reference.release();
//			  }else {
//				  System.out.println("\n## error consume the service "+ record4.getName()  + ar.cause() +"##\n");
//			  }
//			});
		
		
		System.out.println("\n\n\n");
		Record helloRecord = HelloType.createRecord("helloooo", 
													"the-hellooo-service-address",
													new JsonObject());
//		Record helloRecord = 
//			     new Record()
//			         .setType(HelloType.TYPE)
//			         .setLocation(new JsonObject()
//			         .put("endpoint", "the-hellooo-service-address"))
//			         .setName("helloooo")
//			         .setMetadata(new JsonObject());
		discovery.publish(helloRecord, ar -> {
			if(ar.succeeded()) {
				Record helloServiceRecord = ar.result();
				System.out.println("hello service successfully published!");
			} else {
				System.out.println("Hello service has not been published");
			}
		});
		
		
		ServiceDiscovery discovery1 = ServiceDiscovery.create(vertx);
		discovery1.getRecord(filter -> {
				System.out.println("# type: "+filter.getType());
				return HelloType.TYPE.equals(filter.getType());
		}, ar -> {
			int a=0;
			if(ar.succeeded() && ar.result() != null) {
				a=3;
				ServiceReference reference = discovery.getReference(ar.result());
//				ServiceReference reference = discovery
//	                        .getReferenceWithConfiguration(ar.result(), // (1)
//	                                                       new JsonObject().put("lower-case", false));
				HelloService service = reference.getAs(HelloService.class);
				service.sayHello("hello");
				reference.release();
			} else {
				a=1;
				System.out.println("-------->> ERROR "+ar.cause());
			}
		});
		
		discovery.close();
	}

	private void sayHello(RoutingContext context) {
		HttpServerRequest request = context.request();
		HttpServerResponse response = request.response();
		String hello = request.getParam("name") != null ? 
				"Hello "+request.getParam("name") : "Hello";
		response.end(hello);
	}
	
	private void index(RoutingContext context) {
		context.response().end("Home");
	}
}
