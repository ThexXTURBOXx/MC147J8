package de.femtopedia.legacyforgej8.transformers;

import nilloader.api.lib.mini.MiniTransformer;
import nilloader.api.lib.mini.PatchContext;
import nilloader.api.lib.mini.annotation.Patch;

@Patch.Class("cpw.mods.fml.relauncher.RelaunchClassLoader")
public class RelaunchClassLoaderTransformer extends MiniTransformer {

    @Patch.Method("<init>([Ljava/net/URL;)V")
    public void injectAPI(PatchContext ctx) {
        ctx.jumpToLastReturn();

        ctx.add(
                ALOAD(0),
                INVOKESTATIC("de/femtopedia/legacyforgej8/api/API", "registerExclusions",
                        "(Lcpw/mods/fml/relauncher/RelaunchClassLoader;)V")
        );
    }

}
