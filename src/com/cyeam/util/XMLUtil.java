package com.cyeam.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class XMLUtil {

	public static void marshaller(Class cls, Object obj, String fileName) {
		JAXBContext context = null;
		Marshaller marshaller = null;
		
		try {
			context = JAXBContext.newInstance(cls);
			marshaller = context.createMarshaller();
			marshaller.marshal(obj, new FileOutputStream(fileName));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
