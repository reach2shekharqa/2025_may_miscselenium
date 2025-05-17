package com.example.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.yaml.snakeyaml.Yaml;

public final class YamlUtil {

    private Map<String, Object> data;

    protected YamlUtil() {
    }

    public YamlUtil(String fileName) {
        String path = "src/test/resources/" + fileName;
        try {
            InputStream ip = new FileInputStream(path);
            Yaml yaml = new Yaml();
            data = yaml.load(ip);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Failed to read/load YAML file...");
        }
    }

    @SuppressWarnings("unchecked")
    public void performActionsWithValidation(WebDriver driver, String section) {
        Map<String, Object> sectionData = (Map<String, Object>) data.get(section);
        if (sectionData == null) {
            throw new RuntimeException("Section not found in YAML: " + section);
        }

        for (Map.Entry<String, Object> entry : sectionData.entrySet()) {
            String fieldName = entry.getKey();
            Map<String, Object> field = (Map<String, Object>) entry.getValue();
            String type = (String) field.get("type");
            String locator = (String) field.get("locator");
            String locatorType = (String) field.get("locatorType");
            String expectedValue = (String) field.get("value");

            By by = getBy(locatorType, locator);
            WebElement element = driver.findElement(by);

            switch (type.toLowerCase()) {
                case "textbox":
                    element.clear();
                    element.sendKeys(expectedValue);
                    String actualText = element.getAttribute("value");
                    if (!expectedValue.equals(actualText)) {
                        throw new AssertionError("Textbox mismatch for '" + fieldName +
                                "'. Expected: '" + expectedValue + "', Found: '" + actualText + "'");
                    }
                    break;

                case "button":
                    element.click();
                    // No validation for buttons
                    break;

                case "dropdown":
                    Select dropdown = new Select(element);
                    dropdown.selectByValue(expectedValue);
                    String selectedValue = dropdown.getFirstSelectedOption().getAttribute("value");
                    if (!selectedValue.equals(expectedValue)) {
                        throw new AssertionError("Dropdown mismatch for '" + fieldName +
                                "'. Expected value: '" + expectedValue + "', Found: '" + selectedValue + "'");
                    }
                    break;

                case "checkbox":
                    boolean shouldBeChecked = Boolean.parseBoolean(expectedValue);
                    if (element.isSelected() != shouldBeChecked) {
                        element.click();
                    }
                    boolean isChecked = element.isSelected();
                    if (isChecked != shouldBeChecked) {
                        throw new AssertionError("Checkbox mismatch for '" + fieldName +
                                "'. Expected: " + shouldBeChecked + ", Found: " + isChecked);
                    }
                    break;

                default:
                    throw new UnsupportedOperationException("Unsupported type: " + type);
            }
        }
    }

    private By getBy(String locatorType, String locator) {
        switch (locatorType.toLowerCase()) {
            case "id":
                return By.id(locator);
            case "name":
                return By.name(locator);
            case "xpath":
                return By.xpath(locator);
            case "css":
            case "cssselector":
                return By.cssSelector(locator);
            case "classname":
                return By.className(locator);
            case "tagname":
                return By.tagName(locator);
            case "linktext":
                return By.linkText(locator);
            case "partiallinktext":
                return By.partialLinkText(locator);
            default:
                throw new IllegalArgumentException("Unsupported locator type: " + locatorType);
        }
    }
}
