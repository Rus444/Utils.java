import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class Utils extends BasePage {

    //1 Clicking element
    public static void clickElement(By by) {
        driver.findElement(by).click();
    }

    //2 Clearing text from a box
    public static void clearBox(By by) {
        driver.findElement(by).clear();
    }

    //3 Entering text into a field
    public static void enterText(By by, String text) {
        driver.findElement(by).sendKeys(text);
    }

    //4 Clearing the box and then entering text
    public static void clearThenEnterText(By by, String text) {
        driver.findElement(by).clear();
        driver.findElement(by).sendKeys(text);
    }

    //5 Checking if WebElement present in DOM
    public boolean isElementPresent(By by) {
        try {
            driver.findElement(by).isEnabled();
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    //6 Checking if WebElement is displayed or not
    public boolean isElementDisplayed(By by) {
        try {
            driver.findElement(by).isDisplayed();
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    //7 Wait for fixed time given in seconds
    public static void implicitWait(int timeInSeconds, TimeUnit timeUnit){
        driver.manage().timeouts().implicitlyWait(timeInSeconds,timeUnit);
    }

    //8 Try to click element three times if not available in first go
    public boolean retryForElement(By by) {
        boolean result = false;
        int attempt = 0;
        while (attempt < 3) {
            try {
                Thread.sleep(10);
                driver.findElement(by).click();
                result = true;
                break;
            } catch (Exception e) {
            }attempt++;
        }return result;
    }

    //9 Wait for locator to be clickable for given time in seconds
    public static void waitForLocatorToBeClickable(By by, int time){
        WebDriverWait wait=new WebDriverWait(driver, time);
        wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    //10 Wait for element to be clickable
    public static void waitForElementToBeClickable(WebElement element, int time) {
        WebDriverWait wait = new WebDriverWait(driver, time);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    //11 Wait for for visibility of element with given time
    public static void explicitWaitForVisibility (By by, int time){
        WebDriverWait wait= new WebDriverWait(driver, time);
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    //12 Scroll to view element
    public void scrollToElement(By by, WebElement element, int time){
        try {
            ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);)",element);
            driver.findElement(by);
            Thread.sleep(time);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    //13 Scroll to element and click
    public void scrollToElementAndClick(By by, WebElement element){
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", element);
        driver.findElement(by).click();
    }

    //14 Wait for alert to display
    public boolean isAlertPresent(){
        boolean present=false;
        try {
            //Check the presence of alert
            Alert alert=driver.switchTo().alert();
            //Alert present; set the flag
            present=true;
            //If present consume the alert
            alert.accept();
        }catch (NoAlertPresentException ex){
            //Alert not present
            ex.printStackTrace();
        }
        return present;
    }

    //15 Get attribute of element
    public static String getAttributeOfElement(WebElement element, String attr){
        String value=element.getAttribute(attr);
        return value;
    }

    //16 Get css property of element
    public String getCssPropertyOfElement (WebElement element, String css) {
        String value=element.getCssValue(css);
        return value;
    }

    //17 Select text from value
    public static String getActualText (By by){
        String actualResult=driver.findElement(by).getText();
        return actualResult;
    }

    //18 Select element by visible text
    public static void selectElementByVisibleText (By by, String text){
        Select select=new Select(driver.findElement(by));
        select.selectByVisibleText(text);
    }

    //19 Get selected value from dropdown
    public static void selectElementByValue(By by, String value){
        Select select=new Select(driver.findElement(by));
        select.selectByValue(value);
    }

    //20 Wait for element to be invisible
    public static void waitForElementToBeInvisible(By by, int time){
        WebDriverWait wait = new WebDriverWait(driver, time);
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(by)));
    }

    //21 Select element by index
    public static void selectElementByIndex(By by, int index){
        Select select=new Select(driver.findElement(by));
        select.selectByIndex(index);
    }

    //22 Long date stamp
    public static String dateStamp() {
        DateFormat dateFormat = new SimpleDateFormat("DDMMYYYYSSmmHH");
        Date date = new Date();
        String date1 = dateFormat.format(date);
        return date1;
    }

    //23 Short date stamp
    public static String shortDateStamp (){
        DateFormat dateFormat=new SimpleDateFormat("DDMMHHMM");
        Date date= new Date();
        String date2=dateFormat.format(date);
        return date2;
    }

    //24 Assert
    public static void assertByGetText(String expectedResult, By by, String errorMessage){
        Assert.assertEquals(expectedResult, driver.findElement(by).getText(), errorMessage);
    }

    //25 Is Dropdown present
    public static boolean isDropdownMenuPresent (By by, String name){
        if (driver.findElement(by).getTagName().contains("Select")) {
            return true;
        } else {System.out.println("Dropdown menu should not be present");
        return false;
        }
    }

    //27 Get selected value from dropdown
    public static String selectValueFromDropdown(By by){
        driver.findElement(by).isDisplayed();
        Select dropBox = new Select(driver.findElement(by));
        return dropBox.getFirstSelectedOption().getText();
    }

    //28 Convert date
    public static void getDateToNumeric (String dayMonthYear){
        DateFormat dateFormat=new SimpleDateFormat();
        Date date = null;
        try {
            date= dateFormat.parse(dayMonthYear);
        }catch (Exception e){
        }
    }
}