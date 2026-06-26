package context;

import com.microsoft.playwright.Page;

public class TestContext {
// shared object - without this for everypage we need to set page - PicoContainer
    private Page page;

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }
}