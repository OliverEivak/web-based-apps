package ee.ttu.olivereivak.webbasedapps.repair;

import java.io.File;

import org.gwizard.config.ConfigModule;
import org.gwizard.hibernate.HibernateModule;
import org.gwizard.logging.LoggingModule;
import org.gwizard.services.Run;

import com.google.inject.Guice;
import com.google.inject.Injector;

import ee.ttu.olivereivak.webbasedapps.repair.config.ApplicationConfig;
import ee.ttu.olivereivak.webbasedapps.repair.guice.GuiceInjector;
import ee.ttu.olivereivak.webbasedapps.repair.guice.module.ApplicationModule;
import ee.ttu.olivereivak.webbasedapps.repair.guice.module.RestModule;

/**
 * Set up the injector and start all services
 */
public class Main {

	public static String[] arguments;

	public static void main(String[] args) throws Exception {
		if (args.length < 1) {
			System.err.println("First argument needs to be a yaml config file, doofus");
			return;
		}

		arguments = args;

		Injector injector = Guice.createInjector(
				new ApplicationModule(),
				new ConfigModule(new File(args[0]), ApplicationConfig.class),
				new LoggingModule(),
				new RestModule(),
				new HibernateModule());

		injector.getInstance(Run.class).start();
		GuiceInjector.setInjector(injector);
	}

}
