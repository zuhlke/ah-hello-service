
package greeting.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {

    private static final Logger logger = LoggerFactory.getLogger (GreetingController.class);
    private static final String template = "request received -- counter: %s; name: %s";
    private static final String greeting = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong ();

    @RequestMapping ("/greeting")
    public Greeting greeting (
        @RequestParam (value = "name", defaultValue = "World") String name
    ) {
        String message = String.format (template, counter.toString (), name);
        logger.info (message);
        return new Greeting (
            counter.incrementAndGet (),
            String.format (greeting, name)
        );
    }

}
