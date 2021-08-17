package baseEntities;

import core.BrowserService;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;

public abstract class BaseStep<Page extends BasePage> {
    protected BrowserService browserService;
    protected Page page;

    @SuppressWarnings("unchecked")
    public BaseStep(BrowserService browserService, boolean openPageByUrl) {
        this.browserService = browserService;
        this.page = getPageInstance();

        if (openPageByUrl) {
            this.page.open();
        }
        this.page.verifyPageOpened();
    }

    @SuppressWarnings("unchecked")
    public Page getPageInstance() {
        if (this.page != null) return this.page;
        Page page = null;
        try {
            page = ((Class<Page>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0])
                    .getConstructor(BrowserService.class)
                    .newInstance(this.browserService);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return page;
    }

    public abstract BaseStep<Page> openPage();
}
