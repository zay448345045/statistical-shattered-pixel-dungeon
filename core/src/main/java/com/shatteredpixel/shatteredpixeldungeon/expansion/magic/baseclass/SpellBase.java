package com.shatteredpixel.shatteredpixeldungeon.expansion.magic.baseclass;

import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.custom.messages.M;
import com.watabou.utils.Bundlable;
import com.watabou.utils.Bundle;

public abstract class SpellBase implements Bundlable{
    public float baseManaCost = 0f;
    public Category spellCate = Category.NONE;
    public int targetProperty;
    public int level = 1;
    public int maxLevel = 1;

    //cache data before mana is cost.
    public float maxMana;
    public float curMana;
    public float manaRegen;
    public float spellPower;
    public float spellDamage;
    public float costReduction;

    public void tryToCastSpell(Char caster){
        Mana mana = caster.buff(Mana.class);
        if(mana != null){
            buildData(mana);
            //yes, you can have negative mana value, but the penalty is high.
            mana.costMana(manaCost());
            castSpell(caster);
            caster.spendAndNext(castTime());
        }
    }

    protected void buildData(Mana mana){
        maxMana = mana.RMaxMana();
        curMana = mana.RCurMana();
        manaRegen = mana.RManaRegen();
        spellPower = mana.RSpellPower(spellCate);
        spellDamage = mana.RSpellDamage(spellCate);
        costReduction = mana.RCostReduce(spellCate);
    }





    public abstract void castSpell(Char caster);

    public abstract void onCast();

    public abstract void visualEffect(Char caster, int targetCell);

    public abstract void onHit(Char caster, int targetCell, Char victim);

    public abstract void dealDamage(Char caster, Char victim);

    public abstract void onDeath(Char caster, Char victim);






    public String name(){
        return M.L(this.getClass(), "name");
    }

    public String desc(){
        return M.L(this.getClass(), "desc") + "\n\n" + descAppendix();
    }

    public String descAppendix(){
        return M.L(this.getClass(), "desc_more", level, manaCost());
    }

    public float manaCost(){
        return baseManaCost;
    }

    public void upgrade(int lvl){
        level = Math.min(level + lvl, maxLevel);
    }

    public void degrade(int lvl){
        level -= lvl;
    }

    public float castTime(){
        return 1f;
    }

    //Consider: Can one spell have more than one element?
    public enum Category{
        FIRE,
        ICE,
        LIGHTNING,
        SHADOW,
        NONE,
        PASSIVE
    }

    public static final int T_NO = 0x01;
    public static final int T_NO_AUTO_AIM = 0x02;
    public static final int T_ANY = 0x04;
    public static final int T_ONLY_FRIENDLY = 0x08;
    public static final int T_NO_SELF = 0x10;
    public static final int T_NO_FRIENDLY = 0x20;
    public static final int T_NO_BOSS = 0x40;

    @Override
    public void storeInBundle(Bundle bundle) {
        bundle.put("spell_level", level);
    }

    @Override
    public void restoreFromBundle(Bundle bundle) {
        level = bundle.getInt("spell_level");
    }
}
