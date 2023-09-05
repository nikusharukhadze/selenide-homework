import com.codeborne.selenide.Config;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static com.codeborne.selenide.CollectionCondition.exactTexts;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class SelenideTests {

    @BeforeTest
     public void setUp(){
        Configuration.browser = "chrome";
        Configuration.holdBrowserOpen = true;
        Configuration.baseUrl = "https://the-internet.herokuapp.com/";
        Configuration.screenshots = false;
    }

    @Test(priority = 0)
    public void checkBox(){
        open("http://the-internet.herokuapp.com/checkboxes");
        $("input[type=checkbox]",0).click();
        $("input[type=checkbox]",0).shouldHave(attribute("type","checkbox"));
        $("input[type=checkbox]",1).shouldHave(attribute("type","checkbox"));
    }

    @Test(priority = 1)
    public void dropDown(){
        open("http://the-internet.herokuapp.com/dropdown");
        $(byCssSelector("#dropdown > option:nth-child(1)")).shouldHave(attribute("selected"));
        $(byId("dropdown")).selectOptionByValue("2");
        $(byCssSelector("#dropdown > option:nth-child(3)")).shouldHave(attribute("selected"));
    }

    @Test(priority = 2)
    public void textBox(){
        open("https://demoqa.com/text-box");
        $(byId("userName")).setValue("Nikusha");
        $(byXpath("//input[@placeholder='name@example.com']")).setValue("nikusharukhadze@gmail.com");
        $("#currentAddress-wrapper").$("div.col-md-9.col-sm-12").$("textarea").setValue("Saburtalo");
        $("#permanentAddress-wrapper").$("div.col-md-9.col-sm-12").$(".form-control").setValue("Saburtalo");
        $(byXpath("//*[@id=\"submit\"]")).click();
        $$("#output > div > #name").shouldHave(exactTexts("Name:Nikusha"));
        $$("#output > div > #email").shouldHave(exactTexts("Email:nikusharukhadze@gmail.com"));
        $$("#output > div > #currentAddress").shouldHave(exactTexts("Current Address :Saburtalo"));
        $$("#output > div > #permanentAddress").shouldHave(exactTexts("Permananet Address :Saburtalo"));

    }
}
