package com.homecommerce.controller;

import java.io.FileNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.homecommerce.services.ReportService;

import net.sf.jasperreports.engine.JRException;
@SpringBootApplication
@RestController
public class ReportController {

	@Autowired ReportService service;
	@GetMapping("/report/order/{format}")
	public String generatereport(@PathVariable String format) throws FileNotFoundException, JRException
	{
		return service.exportOrderReport(format);
	}
	@GetMapping("/report/customer/{format}")
	public String exportCustomerReport(@PathVariable String format) throws FileNotFoundException, JRException
	{
		return service.exportCustomerReport(format);
	}
	@GetMapping("/report/orderdetails/{format}")
	public String exportOrderdetilsReport(@PathVariable String format) throws FileNotFoundException, JRException
	{
		return service.exportOrderdetilsReport(format);
	}
	@GetMapping("/report/product/{format}")
	public String exportproductReport(@PathVariable String format) throws FileNotFoundException, JRException
	{
		return service.exportproductReport(format);
	}
	
}
