package steps;

import baseEntities.BaseStep;
import core.BrowserService;
import pages.GameDetailsPage;

public class GameDetailsPageSteps extends CommonHeaderSteps<GameDetailsPage>{
    public GameDetailsPageSteps(BrowserService browserService) {
        super(browserService, false);
    }

    @Override
    public BaseStep<GameDetailsPage> openPage() {
        return null;
    }
}
