import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

WebUI.openBrowser(GlobalVariable.Web)

WebUI.click(findTestObject('Elements/Page_DEMOQA/ElementsCard'))

WebUI.waitForElementPresent(findTestObject('Elements/Page_DEMOQA/TextBoxTab'), 50)

WebUI.click(findTestObject('Elements/Page_DEMOQA/TextBoxTab'))

WebUI.waitForElementPresent(findTestObject('Elements/Page_DEMOQA/ButtonSubmit'), 50)

WebUI.sendKeys(findTestObject('Elements/Page_DEMOQA/FullNameUserName'), GlobalVariable.FirstName)

WebUI.sendKeys(findTestObject('Elements/Page_DEMOQA/Email'), GlobalVariable.Email)

WebUI.sendKeys(findTestObject('Elements/Page_DEMOQA/CurrentAddress'), GlobalVariable.City)

WebUI.sendKeys(findTestObject('Elements/Page_DEMOQA/PermanentAddress'), GlobalVariable.Country)

WebUI.waitForElementPresent(findTestObject('Elements/Page_DEMOQA/ButtonSubmit'), 0)

WebUI.click(findTestObject('Elements/Page_DEMOQA/ButtonSubmit'))

WebUI.closeBrowser()

