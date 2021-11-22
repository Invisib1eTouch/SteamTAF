package baseEntities;

import core.BrowserService;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;

import java.lang.reflect.ParameterizedType;

@Log4j2
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

    @SneakyThrows
    @SuppressWarnings("unchecked")
    public Page getPageInstance() {
        if (this.page != null) return this.page;
        Page page = null;
        try {
            page = ((Class<Page>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0])
                    .getConstructor(BrowserService.class)
                    .newInstance(this.browserService);
        } catch (Exception e) {
            String errMes = "Page instance was not initialised:" + e.getMessage();
            log.error(errMes);
            throw new Exception(errMes);
        }
        return page;
    }

    public abstract BaseStep<Page> openPage();
}
