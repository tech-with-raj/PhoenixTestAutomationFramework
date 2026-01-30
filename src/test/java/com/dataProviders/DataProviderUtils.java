package com.dataProviders;

import java.util.ArrayList;
import java.util.Iterator;

import org.testng.annotations.DataProvider;

import com.api.request.model.CreateJobPayload;
import com.api.utils.CSVReaderUtil;
import com.api.utils.CreateJobBeanMapper;
import com.api.utils.FakerDataGenerator;
import com.dataProviders.api.bean.CreateJobBean;
import com.dataProviders.api.bean.UserBean;

public class DataProviderUtils {

	@DataProvider(name = "LoginAPIDataProvider", parallel = true)
	public static Iterator<UserBean> loginAPIDataProvider() {

		return CSVReaderUtil.loadCSV("testData/LoginCreds.csv", UserBean.class);

	}

	@DataProvider(name = "createJobAPIDataProvider", parallel = true)
	public static Iterator<CreateJobPayload> createJobDataProvider() {

		Iterator<CreateJobBean> CreateJobBeanIterator = CSVReaderUtil.loadCSV("testData/CreateJobAPIData.csv",
				CreateJobBean.class);

		ArrayList<CreateJobPayload> payloadsList = new ArrayList<CreateJobPayload>();

		while (CreateJobBeanIterator.hasNext()) {

			CreateJobBean tempBean = CreateJobBeanIterator.next();

			CreateJobPayload tempPayload = CreateJobBeanMapper.mapper(tempBean);

			payloadsList.add(tempPayload);

		}

		return payloadsList.iterator();

	}
	
	
	@DataProvider(name = "createJobAPIFakerDataProvider", parallel = true)
	public static Iterator<CreateJobPayload> createJobFakerDataProvider() {
		
     Iterator<CreateJobPayload> payloadIterator = FakerDataGenerator.generateFakeCreateJobData(100);
     return payloadIterator;
      
	}


}
