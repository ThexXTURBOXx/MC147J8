package de.femtopedia.mc147j8.transformers;

import nilloader.api.lib.mini.MiniTransformer;
import nilloader.api.lib.mini.PatchContext;
import nilloader.api.lib.mini.annotation.Patch;

@Patch.Class("cpw.mods.fml.relauncher.RelaunchLibraryManager")
public class RelaunchLibraryManagerTransformer extends MiniTransformer {

    @Patch.Method("downloadFile(Ljava/io/File;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V")
    public void increaseTimeouts(PatchContext ctx) {
        while (true) {
            PatchContext.SearchResult res = ctx.search(
                    SIPUSH(5000)
            );
            if (!res.isSuccessful()) break;
            res.jumpAfter();

            ctx.add(
                    POP(),
                    SIPUSH(30000)
            );
        }
    }

    @Patch.Method("<clinit>()V")
    public void increaseDownloadLimits(PatchContext ctx) {
        ctx.search(
                LDC(4194304)
        ).jumpAfter();

        ctx.add(
                POP(),
                LDC(8388608)
        );
    }

}
