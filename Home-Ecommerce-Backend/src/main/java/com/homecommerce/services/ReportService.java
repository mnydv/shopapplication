package com.homecommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.homecommerce.models.*;
import java.util.*;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;

import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
//import java.io.JRException;
@Service
public class ReportService {
   
	@Autowired
	private OrderService repository;
	
	@Autowired
	private CustomerService customer;

	
	
	public String exportOrderReport(String reportFormat) throws FileNotFoundException, JRException {

		String path = "C:\\server\\Report";
		List<Order> order= repository.getAllOrders();

		//load file and compile it

		File file = ResourceUtils.getFile("classpath:order.jrxml");

		JasperReport jasperReport= JasperCompileManager.compileReport (file.getAbsolutePath());
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(order);
		
		Map<String, Object> parameters = new HashMap<>();

		parameters.put("createdBy", "Java Techie");

		Connection conn;
		try {
//		    DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		    Class.forName("com.mysql.cj.jdbc.Driver");
		    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/capstonproject", "root", "2626");
		    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, conn);

	        if (reportFormat.equalsIgnoreCase("html")) 
			{
				JasperExportManager.exportReportToHtmlFile(jasperPrint, path+"\\order.html");
			}
			
			if (reportFormat.equalsIgnoreCase("pdf")) {

			JasperExportManager.exportReportToPdfFile (jasperPrint,  path + "\\order.pdf");}
		    
		} catch (Exception ex) {} 
		return " Order report generate in path"+path;
		}
	
	public String exportCustomerReport(String reportFormat) throws FileNotFoundException, JRException {

		String path = "C:\\server\\Report";
		List<Customer> customer1= customer.allCustomers();

		//load file and compile it

		File file = ResourceUtils.getFile("classpath:customer.jrxml");
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(customer1);
		JasperReport jasperReport= JasperCompileManager.compileReport (file.getAbsolutePath());
		
		Map<String, Object> parameters = new HashMap<>();
		 parameters.put("test", customer1.get(0));
		    parameters.put("DS1", dataSource);
		Connection conn;
		try {

		    Class.forName("com.mysql.cj.jdbc.Driver");
		    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/capstonproject", "root", "2626");
		    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, conn);

	        if (reportFormat.equalsIgnoreCase("html")) 
			{
				JasperExportManager.exportReportToHtmlFile(jasperPrint, path+"\\customer.html");
			}
			
			if (reportFormat.equalsIgnoreCase("pdf")) {

			JasperExportManager.exportReportToPdfFile (jasperPrint,  path + "\\customer.pdf");}
		    
		} catch (Exception ex) {} 
	
		return "Customer report generate in path"+path;
		}
	
	
	
	public String exportOrderdetilsReport(String reportFormat) throws FileNotFoundException, JRException {

		String path = "C:\\server\\Report";
		List<Customer> customer1= customer.allCustomers();

		//load file and compile it

		File file = ResourceUtils.getFile("classpath:orderdetails.jrxml");
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(customer1);
		JasperReport jasperReport= JasperCompileManager.compileReport (file.getAbsolutePath());
		
		Map<String, Object> parameters = new HashMap<>();
		 parameters.put("test", customer1.get(0));
		    parameters.put("DS1", dataSource);
		Connection conn;
		try {

		    Class.forName("com.mysql.cj.jdbc.Driver");
		    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/capstonproject", "root", "2626");
		    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, conn);

	        if (reportFormat.equalsIgnoreCase("html")) 
			{
				JasperExportManager.exportReportToHtmlFile(jasperPrint, path+"\\orderdetails.html");
			}
			
			if (reportFormat.equalsIgnoreCase("pdf")) {

			JasperExportManager.exportReportToPdfFile (jasperPrint,  path + "\\orderdetails.pdf");}
		    
		} catch (Exception ex) {} 
	
		return "orderdetails report generate in path"+path;
		}
	
	public String exportproductReport(String reportFormat) throws FileNotFoundException, JRException {

		String path = "C:\\server\\Report";
//		List<Customer> customer1= customer.allCustomers();

		//load file and compile it

		File file = ResourceUtils.getFile("classpath:product.jrxml");
//		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(customer1);
		JasperReport jasperReport= JasperCompileManager.compileReport (file.getAbsolutePath());
		
		Map<String, Object> parameters = new HashMap<>();
//		 parameters.put("test", customer1.get(0));
//		    parameters.put("DS1", dataSource);
		Connection conn;
		try {

		    Class.forName("com.mysql.cj.jdbc.Driver");
		    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/capstonproject", "root", "2626");
		    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, conn);

	        if (reportFormat.equalsIgnoreCase("html")) 
			{
				JasperExportManager.exportReportToHtmlFile(jasperPrint, path+"\\product.html");
			}
			
			if (reportFormat.equalsIgnoreCase("pdf")) {

			JasperExportManager.exportReportToPdfFile (jasperPrint,  path + "\\product.pdf");}
		    
		} catch (Exception ex) {} 
	
		return "product report generate in path"+path;
		}
}
