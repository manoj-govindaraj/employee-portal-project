/**
 * 
 */
package com.employee.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.employee.exception.AppException;
import com.employee.management.EmployeeManagement;

/**
 * Controller class for employee based transactions.
 * 
 * @author manoj.g
 *
 */
@ComponentScan("com.employee.management")
@Controller
public class EmployeeController {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	EmployeeManagement employeeMgmnt;

	private static EmployeeManagement employeeManagement;

	@RequestMapping("/portal")
	public ModelAndView index() {
		return new ModelAndView("portal");
	}

	@RequestMapping("/view-upload")
	public ModelAndView upload() {
		return new ModelAndView("upload");
	}

	@RequestMapping("/view-employee-inventory")
	public ModelAndView viewInventory() {
		return new ModelAndView("inventory");
	}

	@RequestMapping(value = "/upload-employee-details", method = RequestMethod.POST)
	public @ResponseBody ModelAndView uploadEmployeeDetails(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("file") MultipartFile multipartFile) {
		LOGGER.info("Uploading file initiated");
		File csvFile = null;
		String failureMessage = null;
		try {
			csvFile = getCSVFileFromMultipart(multipartFile);
			if (csvFile != null) {
				employeeManagement.addEmployeeDatails(csvFile);
				// If uploaded successfully, load inventory
				return new ModelAndView("inventory");
			} else {
				failureMessage = "Please upload a valid csv file.";
			}
		} catch (AppException e) {
			failureMessage = e.getMessage();
		} catch (Exception e) {
			failureMessage = "Upload failed. Please contact admin.";
		} finally {
			try {
				if (csvFile != null) {
					final Path path = Paths.get(csvFile.getAbsolutePath());
					Files.delete(path);
				}
			} catch (IOException e) {
				LOGGER.error("Error when deleting the temporary file", e);
			}
		}
		final Map<String, String> responseMap = new HashMap<>();
		responseMap.put("message", failureMessage);
		return new ModelAndView("upload", responseMap);
	}

	private File getCSVFileFromMultipart(MultipartFile multipartFile) {
		try {
			if (multipartFile != null && !multipartFile.isEmpty()) {
				final String fileNameWithExtension = multipartFile.getOriginalFilename();
				if (fileNameWithExtension.contains(".")) {
					final int dotIndex = fileNameWithExtension.lastIndexOf(".");
					final String extension = fileNameWithExtension.substring(dotIndex, fileNameWithExtension.length());
					// To validate the format of the file
					if (".csv".equals(extension)) {
						File file = new File(System.getProperty("java.io.tmpdir") + "/" + fileNameWithExtension);
						multipartFile.transferTo(file);
						return file;
					}
				}
			}
		} catch (IOException e) {
			System.out.println("Error when converting multipart file to a file." + e);
		}
		return null;
	}

	public static Map<String, Object> retrieveEmployeeList(int startindex, int limit) throws AppException {
		LOGGER.info("Retrieving employee list with start index: {} and page limit: {}", startindex, limit);
		return getInstance().retrieveEmployeeList(startindex, limit);
	}

	private static EmployeeManagement getInstance() {
		return employeeManagement;
	}

	@PostConstruct
	public void init() {
		EmployeeController.employeeManagement = employeeMgmnt;
	}

}
