package com.api.utils;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;

import com.dataProviders.api.bean.UserBean;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class CSVReaderUtil {
	
	private CSVReaderUtil(){
		
	}
	
	public static Iterator<UserBean> loadCSV(String pathOfCSVFile){


		InputStream resPath = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("testData/LoginCreds.csv");
		
		InputStreamReader iReader = new InputStreamReader(resPath);

		CSVReader csvReader = new CSVReader(iReader);

		CsvToBean<UserBean> csvToBean = new CsvToBeanBuilder(csvReader)
				.withType(UserBean.class)
				.withIgnoreEmptyLine(true)
				.build();
		
	     List<UserBean> listOfUser = csvToBean.parse();
	     
	     return listOfUser.iterator();
	     
			
		}
	

}
