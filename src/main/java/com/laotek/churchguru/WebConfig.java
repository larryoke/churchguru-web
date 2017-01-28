package com.laotek.churchguru;

import java.io.FileNotFoundException;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.deploy.SecurityCollection;
import org.apache.catalina.deploy.SecurityConstraint;
import org.apache.coyote.http11.Http11NioProtocol;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Bean
    public TomcatEmbeddedServletContainerFactory servletContainer(@Value("${keystore.file}") String keystoreFile,
	    @Value("${keystore.password}") String keystorePassword, @Value("${keystore.type}") String keystoreType,
	    @Value("${keystore.alias}") String keystoreAlias) throws FileNotFoundException {

	TomcatEmbeddedServletContainerFactory factory = new TomcatEmbeddedServletContainerFactory(8443) {
	    @Override
	    protected void postProcessContext(Context context) {
		SecurityConstraint securityConstraint = new SecurityConstraint();
		securityConstraint.setUserConstraint("CONFIDENTIAL");
		SecurityCollection collection = new SecurityCollection();
		collection.addPattern("/*");
		securityConstraint.addCollection(collection);
		context.addConstraint(securityConstraint);
	    }
	};

	factory.addConnectorCustomizers(

		new TomcatConnectorCustomizer() {
		    @Override
		    public void customize(Connector con) {
			Http11NioProtocol proto = (Http11NioProtocol) con.getProtocolHandler();
			proto.setSSLEnabled(true);
			con.setScheme("https");
			con.setSecure(true);
			proto.setKeystoreFile(keystoreFile);
			proto.setKeystorePass(keystorePassword);
			proto.setKeystoreType(keystoreType);
			proto.setProperty("keystoreProvider", "SunJSSE");
			proto.setKeyAlias(keystoreAlias);
		    }
		});

	factory.addAdditionalTomcatConnectors(initiateHttpConnector());
	return factory;

    }

    private Connector initiateHttpConnector() {
	Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
	connector.setScheme("http");
	connector.setPort(8080);
	connector.setSecure(false);
	return connector;
    }
}
