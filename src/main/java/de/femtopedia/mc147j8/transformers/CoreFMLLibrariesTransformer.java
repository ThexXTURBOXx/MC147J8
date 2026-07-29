package de.femtopedia.mc147j8.transformers;

import nilloader.api.lib.mini.MiniTransformer;
import nilloader.api.lib.mini.PatchContext;
import nilloader.api.lib.mini.annotation.Patch;

@Patch.Class("cpw.mods.fml.relauncher.CoreFMLLibraries")
public class CoreFMLLibrariesTransformer extends MiniTransformer {

    @Patch.Method("getRootURL()Ljava/lang/String;")
    public void useMirror(PatchContext ctx) {
        ctx.jumpToStart();

        ctx.add(
                LDC("https://github.com/ThexXTURBOXx/MC147J8/raw/refs/heads/main/libs/%s"),
                ARETURN()
        );
    }

    @Patch.Method("<clinit>()V")
    public void updateLibrariesAndAddGson(PatchContext ctx) {
        ctx.search(
                PUTSTATIC("cpw/mods/fml/relauncher/CoreFMLLibraries", "libraries", "[Ljava/lang/String;")
        ).jumpBefore();

        ctx.add(
                POP(),
                GETSTATIC("de/femtopedia/mc147j8/transformers/CoreFMLLibrariesTransformer$Hooks", "libraries",
                        "[Ljava/lang/String;")
        );

        ctx.search(
                PUTSTATIC("cpw/mods/fml/relauncher/CoreFMLLibraries", "checksums", "[Ljava/lang/String;")
        ).jumpBefore();

        ctx.add(
                POP(),
                GETSTATIC("de/femtopedia/mc147j8/transformers/CoreFMLLibrariesTransformer$Hooks", "checksums",
                        "[Ljava/lang/String;")
        );
    }

    public static class Hooks {

        public static final String[] libraries = new String[]{
                "argo-2.25.jar",
                "guava-12.0.1.jar",
                "asm-all-5.2.jar",
                "bcprov-jdk15on-1.69.jar",
                "gson-2.14.0.jar"
        };

        public static final String[] checksums = new String[]{
                "bb672829fde76cb163004752b86b0484bd0a7f4b",
                "b8e78b9af7bf45900e14c6f958486b6ca682195f",
                "2ea49e08b876bbd33e0a7ce75c8f371d29e1f10a",
                "91e1628251cf3ca90093ce9d0fe67e5b7dab3850",
                "efc0e34ede4e3204eaefb84a00e55e8c86634382"
        };

    }

}
