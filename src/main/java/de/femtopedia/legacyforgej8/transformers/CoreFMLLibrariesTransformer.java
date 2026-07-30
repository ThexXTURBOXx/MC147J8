package de.femtopedia.legacyforgej8.transformers;

import nilloader.api.lib.mini.MiniTransformer;
import nilloader.api.lib.mini.PatchContext;
import nilloader.api.lib.mini.annotation.Patch;

@Patch.Class("cpw.mods.fml.relauncher.CoreFMLLibraries")
public class CoreFMLLibrariesTransformer extends MiniTransformer {

    @Patch.Method("getRootURL()Ljava/lang/String;")
    public void useMirror(PatchContext ctx) {
        ctx.jumpToStart();

        ctx.add(
                INVOKESTATIC("de/femtopedia/legacyforgej8/transformers/CoreFMLLibrariesTransformer$Hooks",
                        "getRootURL", "()Ljava/lang/String;"),
                ARETURN()
        );
    }

    @Patch.Method("<clinit>()V")
    public void updateLibrariesAndAddGson(PatchContext ctx) {
        ctx.search(
                PUTSTATIC("cpw/mods/fml/relauncher/CoreFMLLibraries", "libraries", "[Ljava/lang/String;")
        ).jumpBefore();

        ctx.add(
                INVOKESTATIC("de/femtopedia/legacyforgej8/transformers/CoreFMLLibrariesTransformer$Hooks",
                        "getLibraries", "([Ljava/lang/String;)[Ljava/lang/String;")
        );

        ctx.search(
                PUTSTATIC("cpw/mods/fml/relauncher/CoreFMLLibraries", "checksums", "[Ljava/lang/String;")
        ).jumpBefore();

        ctx.add(
                INVOKESTATIC("de/femtopedia/legacyforgej8/transformers/CoreFMLLibrariesTransformer$Hooks",
                        "getChecksums", "([Ljava/lang/String;)[Ljava/lang/String;")
        );
    }

    public static class Hooks {

        private static final String[] libraries = new String[]{
                "argo-2.25.jar",
                "guava-12.0.1.jar",
                "asm-all-5.2.jar",
                "gson-2.14.0.jar"
        };

        private static final String[] checksums = new String[]{
                "bb672829fde76cb163004752b86b0484bd0a7f4b",
                "b8e78b9af7bf45900e14c6f958486b6ca682195f",
                "2ea49e08b876bbd33e0a7ce75c8f371d29e1f10a",
                "efc0e34ede4e3204eaefb84a00e55e8c86634382"
        };

        private static final String[] libraries147 = new String[]{
                "argo-2.25.jar",
                "guava-12.0.1.jar",
                "asm-all-5.2.jar",
                //"bcprov-jdk15on-1.69.jar",
                "bcprov-jdk15on-147.jar",
                "gson-2.14.0.jar"
        };

        private static final String[] checksums147 = new String[]{
                "bb672829fde76cb163004752b86b0484bd0a7f4b",
                "b8e78b9af7bf45900e14c6f958486b6ca682195f",
                "2ea49e08b876bbd33e0a7ce75c8f371d29e1f10a",
                //"91e1628251cf3ca90093ce9d0fe67e5b7dab3850",
                "b6f5d9926b0afbde9f4dbe3db88c5247be7794bb",
                "efc0e34ede4e3204eaefb84a00e55e8c86634382"
        };

        private static final String[] libraries15x = new String[]{
                "argo-small-3.2.jar",
                "guava-14.0-rc3.jar",
                "asm-all-5.2.jar",
                //"bcprov-jdk15on-1.69.jar",
                "bcprov-jdk15on-147.jar",
                null, // Will get replaced in method patcher
                "scala-library.jar",
                "gson-2.14.0.jar"
        };

        private static final String[] checksums15x = new String[]{
                "58912ea2858d168c50781f956fa5b59f0f7c6b51",
                "931ae21fa8014c3ce686aaa621eae565fefb1a6a",
                "2ea49e08b876bbd33e0a7ce75c8f371d29e1f10a",
                //"91e1628251cf3ca90093ce9d0fe67e5b7dab3850",
                "b6f5d9926b0afbde9f4dbe3db88c5247be7794bb",
                null, // Will get replaced in method patcher
                "458d046151ad179c85429ed7420ffb1eaf6ddf85",
                "efc0e34ede4e3204eaefb84a00e55e8c86634382"
        };

        public static String[] getLibraries(String[] old) {
            if (old == null) {
                // Should never happen...
                return old;
            }
            if (old.length < 4) {
                // <= 1.4.5
                return libraries;
            }
            if (old.length < 5) {
                // <= 1.4.7
                return libraries147;
            }
            // 1.5.x
            libraries15x[4] = old[4];
            return libraries15x;
        }

        public static String[] getChecksums(String[] old) {
            if (old == null) {
                // Should never happen...
                return old;
            }
            if (old.length < 4) {
                // <= 1.4.5
                return checksums;
            }
            if (old.length < 5) {
                // <= 1.4.7
                return checksums147;
            }
            // 1.5.x
            checksums15x[4] = old[4];
            return checksums15x;
        }

        public static String getRootURL() {
            return System.getProperty("fml.core.libraries.mirror",
                    "https://github.com/ThexXTURBOXx/LegacyForgeJ8/raw/refs/heads/main/libs/%s");
        }

    }

}
