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

WebUI.waitForElementPresent(findTestObject('01-08/Page_Traveloka - Southeast Asias Leading Travel Platform/09_Mobil_pilihan2'), 
    60)

WebUI.click(findTestObject('01-08/Page_Traveloka - Southeast Asias Leading Travel Platform/09_Mobil_pilihan2'))

WebUI.click(findTestObject('01-08/Page_Traveloka - Southeast Asias Leading Travel Platform/10_Provider_Kedua'))

WebUI.waitForElementPresent(findTestObject('11-20/11_Pengambilan_Office'), 30)

WebUI.click(findTestObject('11-20/11_Pengambilan_Office'))

WebUI.click(findTestObject('11-20/12_Pengambilan_Opsi_1'))

WebUI.click(findTestObject('11-20/13_Pencarian_Pengambilan'))

WebUI.setText(findTestObject('11-20/13_Pencarian_Pengambilan'), 'Ragunan zoo')

WebUI.waitForElementPresent(findTestObject('11-20/Pengembalian_ke_Ragunan_Zoo'), 20)

WebUI.click(findTestObject('11-20/Pengembalian_ke_Ragunan_Zoo'))

WebUI.click(findTestObject('11-20/15_Note'))

WebUI.setText(findTestObject('11-20/15_Note'), 'Ini catatan')

WebUI.click(findTestObject('11-20/16_Continoue'))

