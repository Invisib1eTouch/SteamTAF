package steps;

import core.BrowserService;
import pages.GameGenrePage;

public class GameGenrePageSteps extends CommonHeaderSteps<GameGenrePage> {
    public GameGenrePageSteps(BrowserService browserService) {
        super(browserService, false);
    }

    @Override
    public GameGenrePageSteps openPage() {
        return null;
    }

}
