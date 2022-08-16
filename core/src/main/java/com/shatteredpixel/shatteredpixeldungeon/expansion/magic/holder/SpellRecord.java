package com.shatteredpixel.shatteredpixeldungeon.expansion.magic.holder;

import com.shatteredpixel.shatteredpixeldungeon.expansion.magic.baseclass.SpellTemplate;

import java.util.ArrayList;

public class SpellRecord {

    public static ArrayList<SpellTemplate> spells = new ArrayList<>();

    public static void addSpell(SpellTemplate spell){
        spells.add(spell);
    }
}
