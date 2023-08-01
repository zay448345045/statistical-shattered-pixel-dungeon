package com.shatteredpixel.shatteredpixeldungeon.minigames.MvH.Level;

import com.shatteredpixel.shatteredpixeldungeon.minigames.MvH.Heros.MvHWarrior1;
import com.shatteredpixel.shatteredpixeldungeon.minigames.MvH.Heros.MvHWarrior2;
import com.shatteredpixel.shatteredpixeldungeon.minigames.MvH.Heros.MvHWarrior3;
import com.shatteredpixel.shatteredpixeldungeon.minigames.MvH.Monsters.MvHBomb;
import com.shatteredpixel.shatteredpixeldungeon.minigames.MvH.Monsters.MvHDewcatcher;
import com.shatteredpixel.shatteredpixeldungeon.minigames.MvH.Monsters.MvHElementalNewBornFire;
import com.shatteredpixel.shatteredpixeldungeon.minigames.MvH.Monsters.MvHExplosiveTrap;
import com.shatteredpixel.shatteredpixeldungeon.minigames.MvH.Monsters.MvHYog;
import com.shatteredpixel.shatteredpixeldungeon.minigames.MvH.MvHHeroSpawner;
import com.shatteredpixel.shatteredpixeldungeon.minigames.MvH.WndMvH;

import java.util.ArrayList;
import java.util.Arrays;

public class MvH1_5 extends WndMvH {
    {
        cards = new ArrayList<>(
                Arrays.asList(
                        new Card(new MvHElementalNewBornFire()),
                        new Card(new MvHDewcatcher()),
                        new Card(new MvHBomb()),
                        new Card(new MvHYog()),
                        new Card(new MvHExplosiveTrap())));
        addCards();
    }

    @Override
    public void addHeroSpawner() {
        mvHHeroSpawner = new MvHHeroSpawner();
        mvHHeroSpawner.appearHero = new ArrayList<>(Arrays.asList(
                new MvHWarrior1(),
                new MvHWarrior2(),
                new MvHWarrior3()
        ));

        mvHHeroSpawner.allWave = new ArrayList<>(Arrays.asList(
                mvHHeroSpawner.new Wave(MvHHeroSpawner.Condition.TIME,0,15f),
                mvHHeroSpawner.new Wave(MvHHeroSpawner.Condition.TIME,0,1f,0),//before first zombie
                mvHHeroSpawner.new Wave(MvHHeroSpawner.Condition.ALL,1),
                mvHHeroSpawner.new Wave(MvHHeroSpawner.Condition.ALL,1),
                mvHHeroSpawner.new Wave(MvHHeroSpawner.Condition.ALL,1),
                mvHHeroSpawner.new Wave(MvHHeroSpawner.Condition.ALL,2),
                mvHHeroSpawner.new Wave(MvHHeroSpawner.Condition.ALL,2),
                mvHHeroSpawner.new Wave(MvHHeroSpawner.Condition.ALL,2),
                mvHHeroSpawner.new Wave(MvHHeroSpawner.Condition.ALL,3),
                mvHHeroSpawner.new Wave(MvHHeroSpawner.Condition.ALL,3),
                mvHHeroSpawner.new Wave(MvHHeroSpawner.Condition.DIE,3),
                mvHHeroSpawner.new Wave(MvHHeroSpawner.Condition.TIME,0,2f,1),
                mvHHeroSpawner.new Wave(MvHHeroSpawner.Condition.ALL,4),
                mvHHeroSpawner.new Wave(MvHHeroSpawner.Condition.ALL,4),
                mvHHeroSpawner.new Wave(MvHHeroSpawner.Condition.ALL,4),
                mvHHeroSpawner.new Wave(MvHHeroSpawner.Condition.ALL,5),
                mvHHeroSpawner.new Wave(MvHHeroSpawner.Condition.ALL,5),
                mvHHeroSpawner.new Wave(MvHHeroSpawner.Condition.ALL,5),
                mvHHeroSpawner.new Wave(MvHHeroSpawner.Condition.ALL,6),
                mvHHeroSpawner.new Wave(MvHHeroSpawner.Condition.ALL,6),
                mvHHeroSpawner.new Wave(MvHHeroSpawner.Condition.DIE,6),
                mvHHeroSpawner.new Wave(MvHHeroSpawner.Condition.TIME,0,2f,1),//before last wave
                mvHHeroSpawner.new Wave(MvHHeroSpawner.Condition.TIME,0,2f,2),
                mvHHeroSpawner.new Wave(MvHHeroSpawner.Condition.DIE,14)
        ));

        mvHHeroSpawner.mustAppear.add(new MvHWarrior3());

        mvHHeroSpawner.x = 0;
        mvHHeroSpawner.y = 0;
        addToBack(mvHHeroSpawner);
    }
}
