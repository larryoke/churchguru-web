package com.laotek.churchguru.daos;

import java.io.StringReader;
import java.io.StringWriter;
import java.security.MessageDigest;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import sun.misc.BASE64Encoder;

public abstract class AbstractServiceImpl extends BaseSessionFactory {

    protected String createHash(String value) {
	try {
	    MessageDigest algorithm = MessageDigest.getInstance("MD5");
	    algorithm.reset();
	    algorithm.update(value.getBytes("UTF-8"));
	    return (new BASE64Encoder()).encode(algorithm.digest());
	} catch (Exception e) {
	    e.printStackTrace();
	    throw new RuntimeException("Hash Failure");
	}
    }

    protected <T> String getXml(T t) {
	String xml = null;
	try {
	    final StringWriter stringWriter = new StringWriter();
	    final JAXBContext context = JAXBContext.newInstance(t.getClass());
	    final Marshaller marshaller = context.createMarshaller();
	    marshaller.marshal(t, stringWriter);
	    xml = stringWriter.toString();
	} catch (Exception e) {
	    throw new RuntimeException();
	}
	return xml;
    }

    protected <T> Object getObject(String xml, Class<T> t) throws Exception {
	final JAXBContext context = JAXBContext.newInstance(t.getClass());
	final Unmarshaller unmarshaller = context.createUnmarshaller();
	return unmarshaller.unmarshal(new StringReader(xml));
    }

    protected String createRandomHash() {
	try {
	    String value = String.valueOf(System.currentTimeMillis());
	    MessageDigest algorithm = MessageDigest.getInstance("MD5");
	    algorithm.reset();
	    algorithm.update(value.getBytes("UTF-8"));
	    return (new BASE64Encoder()).encode(algorithm.digest());
	} catch (Exception e) {
	    e.printStackTrace();
	    throw new RuntimeException("Hash Failure");
	}
    }
}
