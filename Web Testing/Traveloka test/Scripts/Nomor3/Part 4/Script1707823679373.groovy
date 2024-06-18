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

WebUI.waitForElementPresent(findTestObject('21-33/27_Requirments'), 300)

WebUI.click(findTestObject('21-33/27_Requirments'))

WebUI.waitForElementPresent(findTestObject('21-33/28_Check_All'), 10)

WebUI.click(findTestObject('21-33/28_Check_All'))

WebUI.click(findTestObject('21-33/29_Save_Requirments'))

WebUI.click(findTestObject('21-33/30_Continoue_to_Payments_InBooking'))

WebUI.waitForElementPresent(findTestObject('21-33/31_Continoue_at_ReConfirm'), 10)

WebUI.click(findTestObject('21-33/31_Continoue_at_ReConfirm'))

WebUI.waitForElementPresent(findTestObject('21-33/32_BCA_Transfer'), 300)

WebUI.click(findTestObject('21-33/32_BCA_Transfer'))

WebUI.click(findTestObject('21-33/33_Pay_with_BCA_Transfer'))

