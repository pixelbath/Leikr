package leikr.loaders;

import java.util.logging.Level;
import java.util.logging.Logger;
import leikr.GameRuntime;
import org.mini2Dx.core.assets.AssetManager;
import org.mini2Dx.core.assets.loader.TextureLoader;
import org.mini2Dx.core.files.LocalFileHandleResolver;
import org.mini2Dx.core.graphics.Sprite;
import org.mini2Dx.core.graphics.SpriteSheet;
import org.mini2Dx.core.graphics.Texture;

/**
 *
 * @author tor
 */
public class SpriteLoader {

    AssetManager assetManager;
    TextureLoader assetLoader;

    SpriteSheet spriteBank;
    SpriteSheet spriteBank16;
    SpriteSheet spriteBank32;
    SpriteSheet spriteBank64;

    String rootPath;

    private static SpriteLoader instance;

    private SpriteLoader() {
        assetLoader = new TextureLoader();
        assetManager = new AssetManager(new LocalFileHandleResolver());
        assetManager.setAssetLoader(Texture.class, assetLoader);
    }

    public static SpriteLoader getSpriteLoader() {
        if (instance == null) {
            instance = new SpriteLoader();
        }
        instance.resetSpriteLoader();
        instance.loadSpriteSheets();
        instance.addSpritesToSpriteBank();

        return instance;
    }

    public void loadManualSpritesheets(String programName) {
        try {
            disposeSprites();
            rootPath = "Programs/" + programName + "/Sprites/Sprites.png";
            instance.loadSpriteSheets();
            instance.addSpritesToSpriteBank();
        } catch (Exception ex) {
            Logger.getLogger(SpriteLoader.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Manual Sprite sheet not loadable.");
        }
    }

    private void resetSpriteLoader() {
        disposeSprites();
        rootPath = GameRuntime.getProgramPath() + "/Sprites/Sprites.png";
    }

    private void loadSpriteSheets() {
        assetManager.load(rootPath, Texture.class);
        assetManager.finishLoading();

    }

    private void addSpritesToSpriteBank() {
        spriteBank = new SpriteSheet(assetManager.get(rootPath, Texture.class), 8, 8);
        spriteBank16 = new SpriteSheet(assetManager.get(rootPath, Texture.class), 16, 16);
        spriteBank32 = new SpriteSheet(assetManager.get(rootPath, Texture.class), 32, 32);
        spriteBank64 = new SpriteSheet(assetManager.get(rootPath, Texture.class), 64, 64);
    }

    public Sprite getSprite(int id, int size) {
        switch (size) {
            case 0:
            default:
                return spriteBank.getSprite(id);
            case 1:
                return spriteBank16.getSprite(id);
            case 2:
                return spriteBank32.getSprite(id);
            case 3:
                return spriteBank64.getSprite(id);
        }
    }

    public void disposeSprites() {
        //assetManager.clearAssetLoaders();
    }

}
