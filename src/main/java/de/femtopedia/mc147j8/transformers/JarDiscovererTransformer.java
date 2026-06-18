package de.femtopedia.mc147j8.transformers;

import nilloader.api.lib.asm.tree.LabelNode;
import nilloader.api.lib.mini.MiniTransformer;
import nilloader.api.lib.mini.PatchContext;
import nilloader.api.lib.mini.annotation.Patch;

@Patch.Class("cpw.mods.fml.common.discovery.JarDiscoverer")
public class JarDiscovererTransformer extends MiniTransformer {

    @Patch.Method("discover(Lcpw/mods/fml/common/discovery/ModCandidate;Lcpw/mods/fml/common/discovery/ASMDataTable;)" +
                  "Ljava/util/List;")
    public void skipModuleInfos(PatchContext ctx) {
        LabelNode Lskip = new LabelNode();

        ctx.search(
                ASTORE(7)
        ).jumpAfter();

        ctx.add(
                Lskip
        );

        ctx.search(
                LDC("__MACOSX")
        ).jumpBefore();
        ctx.searchBackward(
                ALOAD(8)
        ).jumpBefore();

        ctx.add(
                ALOAD(8),
                INVOKEVIRTUAL("java/util/zip/ZipEntry", "getName", "()Ljava/lang/String;"),
                LDC("META-INF/versions/"), // Only supported with version 9+ - so we can safely ignore on Java 8
                INVOKEVIRTUAL("java/lang/String", "startsWith", "(Ljava/lang/String;)Z"),
                IFNE(Lskip),
                ALOAD(8),
                INVOKEVIRTUAL("java/util/zip/ZipEntry", "getName", "()Ljava/lang/String;"),
                LDC("module-info.class"), // Safeguard for stray module infos
                INVOKEVIRTUAL("java/lang/String", "endsWith", "(Ljava/lang/String;)Z"),
                IFNE(Lskip)
        );
    }

}
