package pom;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ManageSavedCardsPage {

	@FindBy(xpath="//span[text()='MyCard']")
	private WebElement myCard;
}
