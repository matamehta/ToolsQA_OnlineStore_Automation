package executionAction;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import pageObjectRepository.AccessoriesPage;
import utility.PublicFunctions;

import config._Constants;

public class AccessoriesPageListView_Action extends LoadableComponent<AccessoriesPageListView_Action> {
	PublicFunctions pf = new PublicFunctions();
	AccessoriesPage ap = new AccessoriesPage();
	public WebDriver driver;
	
	public AccessoriesPageListView_Action() {
		// TODO Auto-generated constructor stub
		driver = new FirefoxDriver();
		PageFactory.initElements(driver, ap);
	}
	
	@Override
	protected void load() {
		// TODO Auto-generated method stub
		driver.navigate().to(_Constants.URLAccessoriesPageList);
		driver.manage().window().maximize();
	}
	
	@Override
	protected void isLoaded() throws Error {
		// TODO Auto-generated method stub
		Assert.assertTrue(driver.getTitle().equals(_Constants.AccessoriesPage_Title));
	}

	/**
	 * Assert the accessories's page text
	 * @throws InterruptedException 
	 */
	public void assertPageText() throws InterruptedException {
		Assert.assertEquals(ap.txtHeader.getText(), "Accessories");
		pf.captureScreenShot(driver, "TC004_TS1_AccessoriesPageListView");
	}
	
	/**
	 * Assert the product's content in List View
	 * @throws Exception 
	 */
	public void assertProductContentsListView(String sheetname) throws Exception {
		// Assert the number of products
		List<WebElement> products = driver.findElements(By.className("default_product_display"));
		Assert.assertEquals(products.size(), 6);
		
		// Assert the product name of products
		List<String> expectedProdcutNames = null;
		List<String> actualProdcutNames = null;
		List<WebElement> productNames = null;
		pf.assertProdcutContents(sheetname, expectedProdcutNames, actualProdcutNames, 1, driver, productNames, "wpsc_product_title");

		// Assert the product prices of products
		List<String> expectedProdcutPrices = null;
		List<String> actualProdcutPrices = null;
		List<WebElement> productPrices = null;
		pf.assertProdcutContents(sheetname, expectedProdcutPrices, actualProdcutPrices, 2, driver, productPrices,"currentprice");
	}
	
	
	/**
	 * View product image
	 * @throws InterruptedException 
	 */
	public void viewProductImage() throws InterruptedException {
		List<WebElement> productImages = driver.findElements(By.className("product_image"));
		for (WebElement productImage : productImages) {
			productImage.click();
			Thread.sleep(3000);
			
			// Conditional wait for the visible of one element
			WebDriverWait wait = new WebDriverWait(driver, 15);
			wait.until(ExpectedConditions.visibilityOf(ap.linkPrevious));
			
			ap.linkPrevious.click();
			Thread.sleep(1000);
			ap.linkNext.click();
			Thread.sleep(1000);
		
			ap.linkClose.click();
			Thread.sleep(3000);
		}
	}
}
