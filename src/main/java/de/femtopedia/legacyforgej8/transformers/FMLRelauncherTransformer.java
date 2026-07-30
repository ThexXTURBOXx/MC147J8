package de.femtopedia.legacyforgej8.transformers;

import de.femtopedia.legacyforgej8.MiniPlusTransformer;
import nilloader.api.lib.mini.PatchContext;
import nilloader.api.lib.mini.PatchContext.SearchResult;
import nilloader.api.lib.mini.annotation.Patch;

@Patch.Class("cpw.mods.fml.relauncher.FMLRelauncher")
public class FMLRelauncherTransformer extends MiniPlusTransformer {

    @Patch.Method("setupHome(Ljava/io/File;)V")
    public void allowJava8(PatchContext ctx) {
        SearchResult res = ctx.search(
                LDC("1.8")
        );
        if (!res.isSuccessful()) return;
        res.jumpAfter();

        ctx.add(
                POP(),
                LDC("IGNORE_PLS")
        );
    }

}
