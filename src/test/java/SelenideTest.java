import com.codeborne.selenide.AssertionMode;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.testng.SoftAsserts;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

@Listeners({ SoftAsserts.class})
public class SelenideTest {

    @BeforeTest
    public void Configuration (){
        Configuration.browser = "chrome";
        Configuration.holdBrowserOpen = true;
        Configuration.baseUrl = "https://demoqa.com/";
        Configuration.screenshots = true;
        Configuration.reportsFolder = "src/main/resources/Reports";
        Configuration.savePageSource = false;
        Configuration.assertionMode = AssertionMode.SOFT;
        //Configuration.timeout = 8;



    }



    @Test(priority = 0)
    public void booksFilter(){
        open("books");

        ElementsCollection books = $(".books-wrapper").find(".ReactTable.-striped.-highlight").find(".rt-table")
                .findAll(".rt-tr-group").filterBy(text("O'Reilly Media")).filterBy(text("Javascript"));
        for (int i = 0; i < books.size() ; i++) {
            System.out.println(books.get(i).getText() +  "\n" );
            if(books.indexOf(books.get(i)) == 0){
                ElementsCollection  javaScriptBookTextColl  = books.get(i).findAll(".rt-td").filterBy(text("Learning JavaScript Design Patterns"));
                for (int j = 0; j < javaScriptBookTextColl.size(); j++) {
                    SoftAssert softAssert = new SoftAssert();
                    softAssert.assertEquals(javaScriptBookTextColl.get(i).getText(),"Learning JavaScript Design Patterns");
                    softAssert.assertAll();
                }
            }
        }

        books.stream().forEach(el -> {
            el.$(".rt-td:nth-child(2) a").scrollTo().click();
            $("#addNewRecordButton").scrollTo().click();
        });


        books.shouldHave(CollectionCondition.size(10));


    }
    @Test(priority = 1)
    public void booksFind(){
        open("books");

        ElementsCollection books = $(".books-wrapper").find(".ReactTable.-striped.-highlight").find(".rt-table")
                .findAll(".rt-tr-group").filterBy(text("O'Reilly Media")).filterBy(text("Javascript"));
        for (int i = 0; i < books.size() ; i++) {
            System.out.println(books.get(i).getText() + "\n");
        }


        ElementsCollection pictureOfBook = $(".books-wrapper").find(".ReactTable.-striped.-highlight").find(".rt-table")
                .findAll(byTagName("img"));

        System.out.println();
        for (int i = 0; i < pictureOfBook.size(); i++) {
            System.out.println(pictureOfBook.get(i).shouldBe(visible).shouldHave(attribute("src")));
        }

    }
}
