package de.femtopedia.mc147j8.transformers;

import nilloader.api.lib.mini.MiniTransformer;
import nilloader.api.lib.mini.PatchContext;
import nilloader.api.lib.mini.annotation.Patch;

@Patch.Class("cpw.mods.fml.relauncher.FMLRelauncher")
public class FMLRelauncherTransformer extends MiniTransformer {

    @Patch.Method("setupHome(Ljava/io/File;)V")
    public void allowJava8(PatchContext ctx) {
        PatchContext.SearchResult res = ctx.search(
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
