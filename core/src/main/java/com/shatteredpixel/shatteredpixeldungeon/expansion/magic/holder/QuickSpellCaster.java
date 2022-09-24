package com.shatteredpixel.shatteredpixeldungeon.expansion.magic.holder;

import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.custom.ch.ChallengeItem;
import com.shatteredpixel.shatteredpixeldungeon.custom.messages.M;
import com.shatteredpixel.shatteredpixeldungeon.expansion.magic.baseclass.Mana;
import com.shatteredpixel.shatteredpixeldungeon.expansion.magic.baseclass.SpellBase;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.utils.Bundle;

import java.util.ArrayList;
import java.util.Locale;

public class QuickSpellCaster extends ChallengeItem {
    {
        image = ItemSpriteSheet.WAND_HOLDER;
        defaultAction = AC_CAST;
        stackable = false;
    }

    private static final String AC_CAST = "cast";
    private static final String AC_RESELECT = "reselect";

    private SpellBase quickSpell;

    @Override
    public ItemSprite.Glowing glowing() {
        if(quickSpell == null){
            return null;
        }
        switch (quickSpell.spellCate){
            case FIRE: return new ItemSprite.Glowing(0xFF4400);
            case ICE: return new ItemSprite.Glowing(0x00FFFF);
            case LIGHTNING: return new ItemSprite.Glowing(0xFFFF44);
            case SHADOW: return new ItemSprite.Glowing(0x440066);
            case NONE: default: return new ItemSprite.Glowing(0xFFFFFF);
        }
    }

    public void setQuickSpell(SpellBase sp){
        if(sp.spellCate == SpellBase.Category.PASSIVE){
            GLog.w(M.L(QuickSpellCaster.class, "cant_passive"));
        }else {
            quickSpell = sp;
        }
    }

    @Override
    public ArrayList<String> actions(Hero hero) {
        ArrayList<String> action = super.actions(hero);
        action.add(AC_CAST);
        action.add(AC_RESELECT);
        return action;
    }

    @Override
    public void execute(Hero hero, String action) {
        super.execute(hero, action);
        if(action.equals(AC_CAST)){
            quickSpell = SpellRecord.findSpell(quickSpell);
            if(quickSpell != null){
                quickSpell.tryToCastSpell(hero);
            }else{
                GameScene.show(new SpellHolder.WndSpellList());
            }
            hero.next();
        }else if(action.equals(AC_RESELECT)){
            GameScene.show(new SpellHolder.WndSpellList());
        }
    }

    @Override
    public String status() {
        int leftUses = 0;
        if(quickSpell != null){
            float cost = quickSpell.manaCost();
            return String.format(Locale.ENGLISH, "%d", Math.round(cost));
        }
        return null;
    }

    @Override
    public String desc() {
        if(quickSpell == null){
            return M.L(QuickSpellCaster.class, "desc_none");
        }
        return M.L(QuickSpellCaster.class, "desc_spell", quickSpell.name(), quickSpell.desc());
    }

    @Override
    public void storeInBundle(Bundle bundle) {
        super.storeInBundle(bundle);
        bundle.put("quick_spell", quickSpell);
    }

    @Override
    public void restoreFromBundle(Bundle bundle) {
        super.restoreFromBundle(bundle);
        quickSpell = (SpellBase) bundle.get("quick_spell");
        quickSpell = SpellRecord.findSpell(quickSpell);
    }
}
