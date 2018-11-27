package com.cities.demo.controller;

import com.cities.demo.classes.city;
import com.cities.demo.repositories.interfaceCityService;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

@RestController
@EnableAutoConfiguration
public class MainController {

	@Autowired
	interfaceCityService cityService;

	@RequestMapping("/")
	@ResponseBody
	String home() {
		ArrayList<city> result = new ArrayList<>(); // list for result table

		city []max = new city[3]; // 3 cities with max population
		max[0] = new city();
		max[1] = new city();
		max[2] = new city();
		max[0].setPopulation("0");
		max[1].setPopulation("0"); // to prevent error
		max[2].setPopulation("0");

		List<city> cities = (List<city>) cityService.findAll(); // get all cities from DB
		result.add(cities.get(0)); // add info row as first row
		String currentCountry = cities.get(1).getCountry(); // get first country name

		for (int i=1; i<cities.size(); i++){

			if (i == cities.size()-1){ // if it is last country
				result.add(max[0]);
				result.add(max[1]);
				result.add(max[2]);
				break;
			}
			if (!currentCountry.equals(cities.get(i+1).getCountry())){ // if it is last city of current country
				if (!cities.get(i).getPopulation().isEmpty()) {
					max = cityPriority(max, cities.get(i));
				}
				if (!(max[0].getId() == 0))
					result.add(max[0]);
				if (!(max[1].getId() == 0))
					result.add(max[1]);
				if (!(max[2].getId() == 0))
					result.add(max[2]);
				max[0] = new city();
				max[1] = new city();
				max[2] = new city();
				max[0].setPopulation("0");
				max[1].setPopulation("0"); // to prevent error
				max[2].setPopulation("0");
				currentCountry = cities.get(i+1).getCountry();
				continue;
			}

			if (!cities.get(i).getPopulation().isEmpty()) {
				max = cityPriority(max, cities.get(i));
			}
		}

		try {
			Workbook workbook = new XSSFWorkbook(); // new HSSFWorkbook() for generating `.xls` file
			// Create a Sheet
			Sheet sheet = workbook.createSheet("Cities");
			// Create a Row
			// Create Other rows and cells with employees data
			int rowNum = 0;
			for(int i=0; i<result.size(); i++) {
				Row row = sheet.createRow(rowNum++);
				row.createCell(0).setCellValue(result.get(i).getCountry());
				row.createCell(1).setCellValue(result.get(i).getCity());
				row.createCell(2).setCellValue(result.get(i).getPopulation());
			}
			// Resize all columns to fit the content size
			for(int i = 0; i < result.size(); i++) {
				sheet.autoSizeColumn(i);
			}
			// Write the output to a file
			FileOutputStream fileOut = new FileOutputStream("population.xlsx");
			workbook.write(fileOut);
			fileOut.close();
			// Closing the workbook
			workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "Created";
	}

	city[] cityPriority(city []max, city city){
		double q = Double.parseDouble(city.getPopulation());
		int w = (int) q;
		for (int j = 0; j < 3; j++) {
			if ((int)Double.parseDouble(city.getPopulation()) < (int)Double.parseDouble(max[j].getPopulation())) {
				continue;
			} else {
				if (j == 0) {
					max[2] = max[1];
					max[1] = max[0];
					max[0] = city;
					break;
				}
				if (j == 1) {
					max[2] = max[1];
					max[1] = city;
					break;
				}
				if (j == 2) {
					max[2] = city;
					break;
				}
			}
		}
		return max;
	}
}
