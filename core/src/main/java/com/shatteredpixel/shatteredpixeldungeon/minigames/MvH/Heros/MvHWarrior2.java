package com.shatteredpixel.shatteredpixeldungeon.minigames.MvH.Heros;

import com.shatteredpixel.shatteredpixeldungeon.minigames.MvH.MvHHeroSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.HeroSprite;

public class MvHWarrior2 extends MvHHeroSprite {

    public boolean haveUpdate = false;

    public MvHWarrior2() {
        super(true,2);
        HT = HP = 550;
        DELAY = delay = 0.5f;
        gameSpeed = 2f;
        level = 2;
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