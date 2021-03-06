package ee.ttu.olivereivak.veebirakex2.test;

import org.gwizard.services.Run;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.jboss.resteasy.plugins.providers.jackson.ResteasyJackson2Provider;
import org.junit.After;
import org.junit.Before;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Module;

import ee.ttu.olivereivak.veebirakex2.guice.module.RestModule;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>This starts the full web stack and allows to issue real http requests against the target. </p>
 */
@Slf4j
public class FullWebStackTestBase<T> extends TestBase {

    public static int TEST_PORT = 18080;

    /**
     * We need to get the RestModule included if we want the web stack to run
     */
    @Override
    protected Module overrideModule() {
        return new RestModule();
    }

    @Before
    public void setUpWebStack() throws Exception {
        injector.getInstance(Run.class).start();
    }

    @After
    public void tearDownWebStack() throws Exception {
        injector.getInstance(Run.class).stop();
    }

    private ResteasyClient getClientWithObjectMapper() {
        ResteasyJackson2Provider provider = new ResteasyJackson2Provider();
        provider.setMapper(instance(ObjectMapper.class));

        return new ResteasyClientBuilder().register(provider).build();
    }

    protected T getClient(Class<T> resourceInterface) {
        ResteasyClient client = getClientWithObjectMapper();

        ResteasyWebTarget target = client.target(String.format("http://localhost:%s/", TEST_PORT));
        return target.proxy(resourceInterface);
    }

}
