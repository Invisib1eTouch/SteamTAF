package baseEntities;

import core.BrowserService;
import utils.Logger;

import java.lang.reflect.ParameterizedType;

public abstract class BaseStep<Page extends BasePage> {
    protected BrowserService browserService;
    protected Page page;

    public BaseStep(BrowserService browserService, boolean openPageByUrl) {
        this.browserService = browserService;
        try {
            this.page = getPageInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (openPageByUrl) {
            this.page.open();
        }
        this.page.verifyPageOpened();
    }

    public Page getPageInstance() {
        if (this.page != null) return this.page;
        Page page = null;
        try {
            page = ((Class<Page>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0])
                    .getConstructor(BrowserService.class)
                    .newInstance(this.browserService);
        } catch (Exception e) {
            String errMes = "Page instance was not initialised:" + e.getMessage();
            Logger.log.error(errMes);
            throw new ExceptionInInitializerError();
        }
        return page;
    }

    public abstract BaseStep<Page> openPage();
}
