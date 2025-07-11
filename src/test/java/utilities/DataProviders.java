package utilities;

import java.io.IOException;
import org.testng.annotations.DataProvider;

public class DataProviders {

	@DataProvider(name="FindingHospitalsData")
	public Object[][] getData() throws IOException{
		String path = ".\\testData\\FindingHospitals_TestData.xlsx";
		
		ExcelUtility xlutil = new ExcelUtility(path);
		
		int totalrows = xlutil.getRowCount("TC001"); //1: Counting starts from 0 to end
		int totalcols = xlutil.getCellCount("TC001",1); //2: Counting starts from 1 to end
		
		Object searchData[][] = new Object[totalrows][totalcols]; 
	
		for(int i=1; i<=totalrows; i++) { //i = 1 because we don't want the title
			for(int j = 0; j<totalcols; j++) {
				searchData[i-1][j] = xlutil.getCellData("TC001",i,j);
			}
		}
		return searchData;
	}
}
