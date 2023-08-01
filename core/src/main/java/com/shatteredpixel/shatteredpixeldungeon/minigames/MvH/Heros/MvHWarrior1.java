package com.shatteredpixel.shatteredpixeldungeon.minigames.MvH.Heros;

import com.shatteredpixel.shatteredpixeldungeon.minigames.MvH.MvHHeroSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.HeroSprite;

public class MvHWarrior1 extends MvHHeroSprite {

    public boolean haveUpdate = false;

    public MvHWarrior1() {
        super(true,1);
        HT = HP = 180;
        DELAY = delay = 0.5f;
        gameSpeed = 2f;
        level = 1;
        flipHorizontal = true;
        runSlowly();
    }

    @Override
    public void update() {
        if (!haveUpdate && HP < 180 && HP > 0) {
            tier = 1;
            updateArmor();
            haveUpdate = true;
        }

        super.update();
    }
}
