package de.femtopedia.mc147j8.transformers;

import nilloader.api.lib.mini.MiniTransformer;
import nilloader.api.lib.mini.PatchContext;
import nilloader.api.lib.mini.annotation.Patch;

@Patch.Class("cpw.mods.fml.relauncher.CoreFMLLibraries")
public class CoreFMLLibrariesTransformer extends MiniTransformer {

    @Patch.Method("getRootURL()Ljava/lang/String;")
    public void useWaybackMachineIfNeeded(PatchContext ctx) {
        ctx.search(
                LDC("http://files.minecraftforge.net/fmllibs/%s")
        ).jumpAfter();

        ctx.add(
                POP(),
                LDC("https://web.archive.org/web/20190716130335if_/http://files.minecraftforge.net/fmllibs/%s")
        );
    }

    @Patch.Method("<clinit>()V")
    public void updateLibrariesAndAddGson(PatchContext ctx) {
        ctx.search(
                ICONST_4()
        ).jumpAfter();
        ctx.add(
                POP(),
                ICONST_5()
        );

        ctx.search(
                LDC("asm-all-4.0.jar")
        ).jumpAfter();
        ctx.add(
                POP(),
                LDC("asm-all-5.2.jar")
        );

        ctx.search(
                LDC("bcprov-jdk15on-147.jar")
        ).jumpAfter();
        ctx.add(
                POP(),
                LDC("bcprov-jdk15on-1.69.jar")
        );

        ctx.add(
                AASTORE(),
                DUP(),
                ICONST_4(),
                LDC("gson-2.13.1.jar")
        );

        ctx.search(
                ICONST_4()
        ).jumpAfter();
        ctx.add(
                POP(),
                ICONST_5()
        );

        ctx.search(
                LDC("98308890597acb64047f7e896638e0d98753ae82")
        ).jumpAfter();
        ctx.add(
                POP(),
                LDC("2ea49e08b876bbd33e0a7ce75c8f371d29e1f10a")
        );

        ctx.search(
                LDC("b6f5d9926b0afbde9f4dbe3db88c5247be7794bb")
        ).jumpAfter();
        ctx.add(
                POP(),
                LDC("91e1628251cf3ca90093ce9d0fe67e5b7dab3850")
        );

        ctx.add(
                AASTORE(),
                DUP(),
                ICONST_4(),
                LDC("853ce06c11316b33a8eae5e9095da096a9528b8f")
        );
    }

}
