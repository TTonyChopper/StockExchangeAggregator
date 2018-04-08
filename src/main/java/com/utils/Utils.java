package com.utils;

import java.io.StringWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Utils {
	public static void print2Json(Object o) {
		try {
			System.out.println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(o));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}

	public static void print2Xml(Object o) {
		StringWriter sw = new StringWriter();
		JAXBContext jaxbContext = null;
		try {
			jaxbContext = JAXBContext.newInstance(o.getClass());
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		Marshaller jaxbMarshaller;
		try {
			jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(o, sw);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		System.out.println(sw.toString());
	}

	public static String convertTZ2String(String inputDateTime, TimeZone sourceTZ, TimeZone destTZ,
			String sourceDateFormat, String destDateFormat) {
		SimpleDateFormat sdf = new SimpleDateFormat(sourceDateFormat);
		SimpleDateFormat formatter = new SimpleDateFormat(destDateFormat);
		try {
			sdf.setTimeZone(sourceTZ);
			formatter.setTimeZone(destTZ);
			return formatter.format(sdf.parse(inputDateTime));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Date convertTZ2Date(String inputDateTime, TimeZone sourceTZ, TimeZone destTZ, String sourceDateFormat,
			String destDateFormat) {
		String strDate = convertTZ2String(inputDateTime, sourceTZ, destTZ, sourceDateFormat, destDateFormat);
		SimpleDateFormat sdfRet = new SimpleDateFormat(destDateFormat);
		try {
			sdfRet.setTimeZone(destTZ);
			return sdfRet.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static XMLGregorianCalendar convertTZ2XMLGregorianCalendar(String inputDateTime, TimeZone sourceTZ,
			TimeZone destTZ, String sourceDateFormat, String destDateFormat) {
		Date myDate = convertTZ2Date(inputDateTime, sourceTZ, destTZ, sourceDateFormat, destDateFormat);
		GregorianCalendar c = new GregorianCalendar();
		c.setTimeZone(destTZ);
		c.setTime(myDate);

		try {
			return DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static String addStringDate(String inputDate, String sourceDateFormat, int field, int amount) {
		String ret = "";

		SimpleDateFormat sdfRet = new SimpleDateFormat(sourceDateFormat);
		try {
			Date myDate = sdfRet.parse(inputDate);
			Calendar c = Calendar.getInstance();
			c.setTime(myDate);
			c.add(field, amount);
			myDate = c.getTime();
			ret = sdfRet.format(myDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return ret;
	}

	public static void progressPercentage(int remain, int total) {
		/*
		 * if (remain > total) { throw new IllegalArgumentException(); }
		 */
		int maxBareSize = 10; // 10unit for 100%
		int remainProcent = ((100 * remain) / total) / maxBareSize;
		char defaultChar = '-';
		String icon = "*";
		String bare = new String(new char[maxBareSize]).replace('\0', defaultChar) + "]";
		StringBuilder bareDone = new StringBuilder();
		bareDone.append("[");
		for (int i = 0; i < remainProcent; i++) {
			bareDone.append(icon);
		}
		String bareRemain = bare.substring(remainProcent, bare.length());
		System.out.print("\r" + bareDone + bareRemain + " " + remainProcent * 10 + "% (" + remain + "/" + total + ")");
		if (remain >= total) {
			System.out.print("\n");
		}
	}

}
