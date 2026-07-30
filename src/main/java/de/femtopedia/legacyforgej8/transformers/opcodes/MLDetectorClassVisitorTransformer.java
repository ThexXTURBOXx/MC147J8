package de.femtopedia.legacyforgej8.transformers.opcodes;

import de.femtopedia.legacyforgej8.MiniPlusTransformer;
import nilloader.api.lib.mini.PatchContext;
import nilloader.api.lib.mini.annotation.Patch;

@Patch.Class("cpw.mods.fml.common.asm.FMLSanityChecker$MLDetectorClassVisitor")
public class MLDetectorClassVisitorTransformer extends MiniPlusTransformer {

    @Patch.Method("<init>()V")
    public void useASM5Opcode(PatchContext ctx) {
        migrateASM4ToASM5(ctx);
    }

}
