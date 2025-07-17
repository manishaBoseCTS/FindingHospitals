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
	

	@DataProvider(name="LoginData")
	public Object[][] getLoginData() throws IOException{
		String path = ".\\testData\\Login_TestData.xlsx";
		
		ExcelUtility xlutil = new ExcelUtility(path);
		
		int totalrows = xlutil.getRowCount("TC004"); //1: Counting starts from 0 to end
		int totalcols = xlutil.getCellCount("TC004",1); //2: Counting starts from 1 to end
		
		Object searchData[][] = new Object[totalrows][totalcols]; 
	
		for(int i=1; i<=totalrows; i++) { //i = 1 because we don't want the title
			for(int j = 0; j<totalcols; j++) {
				searchData[i-1][j] = xlutil.getCellData("TC004",i,j);
			}
		}
		return searchData;
	}
	
	@DataProvider(name="DoctorsSearchData")
	public Object[][] getDoctorsSearchData() throws IOException{
		String path = ".\\testData\\NearbyDoctors_TestData.xlsx";
		
		ExcelUtility xlutil = new ExcelUtility(path);
		
		int totalrows = xlutil.getRowCount("TC002"); //1: Counting starts from 0 to end
		int totalcols = xlutil.getCellCount("TC002",1); //2: Counting starts from 1 to end
		
		Object searchData[][] = new Object[totalrows][totalcols]; 
	
		for(int i=1; i<=totalrows; i++) { //i = 1 because we don't want the title
			for(int j = 0; j<totalcols; j++) {
				searchData[i-1][j] = xlutil.getCellData("TC002",i,j);
			}
		}
		return searchData;
	}
	
	@DataProvider(name = "HealthArticleSearchData")
    public String[][] getHealthArticlesSearchData() throws IOException {
        
        String path = System.getProperty("user.dir") + "\\testData\\HealthArticles_TestData.xlsx";
        ExcelUtility xl = new ExcelUtility(path);

        int rows = xl.getRowCount("TC005_Input");
        int cols = xl.getCellCount("TC005_Input", 1);

        String[][] data = new String[rows][cols];

        for (int i = 1; i <= rows; i++) {
            for (int j = 0; j < cols; j++) {
                data[i - 1][j] = xl.getCellData("TC005_Input", i, j);
                System.out.println("Cell [" + i + "," + j + "] data= " + data[i - 1][j]);
            }
        }

        return data;
    }

    
}
