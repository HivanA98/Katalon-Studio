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

WebUI.verifyElementClickable(findTestObject('Collector Admin/Collector Admin_Setting/Process Type/Btn_New_Process Type'))

WebUI.click(findTestObject('Collector Admin/Collector Admin_Setting/Process Type/Btn_New_Process Type'))

WebUI.clearText(findTestObject('Collector Admin/Collector Admin_Setting/Process Type/Input_TraChineseName'))

WebUI.setText(findTestObject('Collector Admin/Collector Admin_Setting/Process Type/Input_TraChineseName'), TraditionalChinese)

WebUI.clearText(findTestObject('Collector Admin/Collector Admin_Setting/Process Type/Input_SimpChineseName'))

WebUI.setText(findTestObject('Collector Admin/Collector Admin_Setting/Process Type/Input_SimpChineseName'), SimplyChinese)

WebUI.clearText(findTestObject('Collector Admin/Collector Admin_Setting/Process Type/Input_EnglishName'))

WebUI.setText(findTestObject('Collector Admin/Collector Admin_Setting/Process Type/Input_EnglishName'), EnglishName)

WebUI.click(findTestObject('Collector Admin/Collector Admin_Setting/Process Type/Table Day'))

WebUI.waitForElementPresent(findTestObject('Collector Admin/Collector Admin_Setting/Process Type/Table Day_5'), 500)

WebUI.click(findTestObject('Collector Admin/Collector Admin_Setting/Process Type/Table Day_5'))

WebUI.click(findTestObject('Collector Admin/Collector Admin_Setting/Process Type/Table Time'))

WebUI.waitForElementPresent(findTestObject('Collector Admin/Collector Admin_Setting/Process Type/Table Time_Hour'), 500)

WebUI.click(findTestObject('Collector Admin/Collector Admin_Setting/Process Type/Table Time_Hour'))

WebUI.click(findTestObject('Collector Admin/Collector Admin_Setting/Process Type/Table Weight'))

WebUI.click(findTestObject('Collector Admin/Collector Admin_Setting/Process Type/Table Weight_Kg'))

WebUI.clearText(findTestObject('Collector Admin/Collector Admin_Setting/Process Type/Input_Description'))

WebUI.setText(findTestObject('Collector Admin/Collector Admin_Setting/Process Type/Input_Description'), GlobalVariable.Description)

WebUI.clearText(findTestObject('Collector Admin/Collector Admin_Setting/Process Type/Input_Remark'))

WebUI.setText(findTestObject('Collector Admin/Collector Admin_Setting/Process Type/Input_Remark'), GlobalVariable.Remark)

