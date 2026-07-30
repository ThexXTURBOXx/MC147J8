package de.femtopedia.legacyforgej8.transformers.libs;

import de.femtopedia.legacyforgej8.MiniPlusTransformer;
import nilloader.api.lib.mini.PatchContext;
import nilloader.api.lib.mini.PatchContext.SearchResult;
import nilloader.api.lib.mini.annotation.Patch;

@Patch.Class("cpw.mods.fml.relauncher.RelaunchLibraryManager")
public class RelaunchLibraryManagerTransformer extends MiniPlusTransformer {

    @Patch.Method("downloadFile(Ljava/io/File;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V")
    public void increaseTimeouts(PatchContext ctx) {
        while (true) {
            SearchResult res = ctx.search(
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
        SearchResult res = ctx.search(
                LDC(4194304)
        );
        if (!res.isSuccessful()) return; // Probably on 1.5.x and already applied

        res.jumpAfter();
        ctx.add(
                POP(),
                LDC(8388608)
        );
    }

}
