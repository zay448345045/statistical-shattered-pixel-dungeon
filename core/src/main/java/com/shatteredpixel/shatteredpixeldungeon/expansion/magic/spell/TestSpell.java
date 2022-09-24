package com.shatteredpixel.shatteredpixeldungeon.expansion.magic.spell;

import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.expansion.magic.baseclass.SpellBase;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;

public class TestSpell extends SpellBase {
    {
        maxLevel = 9999;
        spellCate = Category.NONE;
        baseManaCost = 10f;
    }
    @Override
    public void castSpell(Char caster) {
        GLog.w("cast");
    }

    @Override
    public void onCast() {

    }

    @Override
    public void visualEffect(Char caster, int targetCell) {

    }

    @Override
    public void onHit(Char caster, int targetCell, Char victim) {

    }

    @Override
    public void dealDamage(Char caster, Char victim) {

    }

    @Override
    public void onDeath(Char caster, Char victim) {

    }
}
