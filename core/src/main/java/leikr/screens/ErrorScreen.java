package leikr.screens;

import leikr.GameRuntime;
import org.mini2Dx.core.game.GameContainer;
import org.mini2Dx.core.Graphics;
import org.mini2Dx.core.Mdx;
import org.mini2Dx.core.assets.AssetManager;
import org.mini2Dx.core.graphics.Colors;
import org.mini2Dx.core.graphics.viewport.FitViewport;
import org.mini2Dx.core.screen.BasicGameScreen;
import org.mini2Dx.core.screen.GameScreen;
import org.mini2Dx.core.screen.ScreenManager;
import org.mini2Dx.core.screen.Transition;
import org.mini2Dx.core.screen.transition.FadeInTransition;
import org.mini2Dx.core.screen.transition.FadeOutTransition;
import org.mini2Dx.gdx.Input.Keys;

/**
 *
 * @author tor
 */
public class ErrorScreen extends BasicGameScreen {

    public static int ID = 4;
    AssetManager assetManager;
    FitViewport viewport;
    boolean MENU = false;
    boolean RELOAD = false;
    static String errorMessage;

    public ErrorScreen(AssetManager assetManager) {
        this.assetManager = assetManager;
        viewport = new FitViewport(GameRuntime.WIDTH, GameRuntime.HEIGHT);
        errorMessage = "";
    }

    public static void setErrorMessage(String message) {
        errorMessage = message;
    }

    void reloadEngine(ScreenManager sm) {
        sm.enterGameScreen(LoadScreen.ID, null, null);
    }

    @Override
    public void preTransitionIn(Transition transitionIn) {
    }

    @Override
    public void preTransitionOut(Transition transitionOut) {
    }

    @Override
    public void initialise(GameContainer gc) {
      
    }

    @Override
    public void update(GameContainer gc, ScreenManager<? extends GameScreen> sm, float f) {
        if (MENU || Mdx.input.isKeyJustPressed(Keys.ESCAPE) || Mdx.input.isKeyJustPressed(Keys.ENTER) || Mdx.input.isKeyJustPressed(Keys.SPACE) || Mdx.input.isKeyJustPressed(Keys.Q)) {
            MENU = false;
            sm.enterGameScreen(MenuScreen.ID, new FadeOutTransition(Colors.TEAL()), new FadeInTransition(Colors.FOREST()));
        }

        //TODO: Implement once keyPress is available
//        if (Mdx.input.isKeyPressed(Keys.CONTROL_LEFT) && Mdx.input.isKeyPressed(Keys.R) || Mdx.input.isKeyPressed(Keys.F5)) {
//            reloadEngine(sm);
//        }
    }

    @Override
    public void interpolate(GameContainer gc, float f) {
    }

    @Override
    public void render(GameContainer gc, Graphics g) {
        viewport.apply(g);
        g.setColor(Colors.RED());
        g.drawString("Message:  " + errorMessage, 0, 0, 232);
        g.setColor(Colors.BLACK());
        g.drawRect(0, 152, 240, 8);
        g.setColor(Colors.GREEN());
        g.drawString(":q to quit", 0, 152);
    }

    @Override
    public int getId() {
        return ID;
    }
}
