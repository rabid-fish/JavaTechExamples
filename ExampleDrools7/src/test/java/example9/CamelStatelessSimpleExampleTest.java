package example9;

import com.github.rabid_fish.Number;
import org.apache.camel.EndpointInject;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelSpringTestSupport;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CamelStatelessSimpleExampleTest extends CamelSpringTestSupport {

	@Produce(uri = "direct:start")
	private ProducerTemplate template;

	@EndpointInject(uri = "mock:end")
	protected MockEndpoint resultEndpoint;
	
	@Override
	protected AbstractApplicationContext createApplicationContext() {
		return new ClassPathXmlApplicationContext("classpath:/com/github/rabid_fish/example9/CamelStatelessSimpleExample.xml");
	}
	
	@Test
	public void testCamelRoute() throws Exception {

		resultEndpoint.expectedMessageCount(1);
		
		Number number = new Number("5");
		template.sendBody(number);
		
		resultEndpoint.assertIsSatisfied();
	}
}
