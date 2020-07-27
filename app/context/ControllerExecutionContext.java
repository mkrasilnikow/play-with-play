package context;

import akka.actor.ActorSystem;
import play.libs.concurrent.CustomExecutionContext;

import javax.inject.Inject;

public class ControllerExecutionContext extends CustomExecutionContext {
    @Inject
    public ControllerExecutionContext(ActorSystem actorSystem) {
        super(actorSystem, "http-execution-context");
    }
}
