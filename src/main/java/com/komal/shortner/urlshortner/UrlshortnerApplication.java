package com.komal.shortner.urlshortner;

import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;
import org.apache.ignite.cluster.ClusterState;
import org.apache.ignite.configuration.DataRegionConfiguration;
import org.apache.ignite.configuration.DataStorageConfiguration;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class UrlshortnerApplication {
	private static final Logger logger = LogManager.getLogger(UrlshortnerApplication.class);


	public static void main(String[] args) {
		SpringApplication.run(UrlshortnerApplication.class, args);
	}
	
	@Bean
	public Ignite initIgnite() {
		logger.info("Initializing ignite");
		IgniteConfiguration configuration =  new IgniteConfiguration();
		DataStorageConfiguration dsCfg = new DataStorageConfiguration();
		DataRegionConfiguration dfltDataRegConf = new DataRegionConfiguration();
		dfltDataRegConf.setPersistenceEnabled(true);
		dsCfg.setDefaultDataRegionConfiguration(dfltDataRegConf);
		configuration.setDataStorageConfiguration(dsCfg );
		Ignite ignite = Ignition.getOrStart(configuration);
		ignite.cluster().state(ClusterState.ACTIVE);
		logger.info("Ignite is initialized");
		return ignite;
	}

}
