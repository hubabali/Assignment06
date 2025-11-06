package playwrightTraditional;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import org.junit.jupiter.api.Test;
import java.nio.file.Paths;

public class BookstoreTest {
    @Test
    void testFullBookstoreFlow() {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(
                    new BrowserType.LaunchOptions().setHeadless(false)
            );
            BrowserContext context = browser.newContext(
                    new Browser.NewContextOptions()
                            .setRecordVideoDir(Paths.get("videos/"))
                            .setRecordVideoSize(1280, 720)
            );
            Page page = context.newPage();
            page.navigate("https://depaul.bncollege.com/");
            page.getByPlaceholder("Enter your search details (").click();
            page.getByPlaceholder("Enter your search details (").fill("Earbuds");
            page.getByPlaceholder("Enter your search details (").press("Enter");
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("brand")).click();
            page.locator("#facet-brand").getByRole(AriaRole.LIST).locator("label").filter(new Locator.FilterOptions().setHasText("brand JBL (12)")).getByRole(AriaRole.IMG).click();
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Color")).click();
            page.locator("label").filter(new Locator.FilterOptions().setHasText("Color Black (9)")).locator("svg").first().click();
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Price")).click();
            page.locator("#facet-price svg").nth(2).click();
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("JBL Quantum True Wireless")).click();
            page.getByLabel("Add to cart").click();
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Cart 1 items")).click();
            page.getByText("FAST In-Store PickupDePaul").click();
            page.getByLabel("Enter Promo Code").click();
            page.getByLabel("Enter Promo Code").fill("TEST");
            page.getByLabel("Apply Promo Code").click();
            page.waitForSelector("text=Promo", new Page.WaitForSelectorOptions().setTimeout(15000).setState(WaitForSelectorState.ATTACHED));
            page.waitForTimeout(2000);
            page.locator("button:has-text('Proceed')").first().click();
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Proceed As Guest")).click();
            page.getByPlaceholder("Please enter your first name").click();
            page.getByPlaceholder("Please enter your first name").fill("Kawhi");
            page.getByPlaceholder("Please enter your last name").click();
            page.getByPlaceholder("Please enter your last name").fill("Leonard");
            page.getByPlaceholder("Please enter a valid email").click();
            page.getByPlaceholder("Please enter a valid email").fill("toronto2019champs@ontario.com");
            page.getByPlaceholder("Please enter a valid phone").click();
            page.getByPlaceholder("Please enter a valid phone").fill("1223334444");
            page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Student")).click();
            page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName("Student")).click();
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Continue")).click();
            page.getByText("I'll pick them up").click();
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Continue")).click();
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Back to cart")).click();
            page.getByLabel("Remove product JBL Quantum").click();
            browser.close();
        }
    }
}
