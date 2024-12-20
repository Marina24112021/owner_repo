package tests;

import api.books.MethodsApi;
import api.models.GetCollectionOfAllBooksModel;
import helpers.extensions.WithLogin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.ProfilePage;

import static org.assertj.core.api.Assertions.assertThat;

@Tag("restapi")
public class DeleteBookFromCollectionStoreTest extends TestBase {

    @Test
    @WithLogin
    @DisplayName("Using API and UI to delete books and check the empty collection")
    void successfulDeleteBookTest() {
        ProfilePage profilePage = new ProfilePage();
        MethodsApi methodsApi = new MethodsApi();
        methodsApi.deleteAllBooksApi();
        methodsApi.addBookToCardApi("9781449325862");

        profilePage.openPageFromUI();
        profilePage.deleteOneBookFromUI();
        profilePage.openPageFromUI();
        profilePage.checkDeleteBookWithUI();

        GetCollectionOfAllBooksModel response = methodsApi.getAllCollectionOfBooksFromCardApi();
        assertThat(response.getBooks()).isEmpty();
    }
}
