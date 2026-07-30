package de.femtopedia.legacyforgej8.transformers.opcodes;

import de.femtopedia.legacyforgej8.MiniPlusTransformer;
import nilloader.api.lib.mini.PatchContext;
import nilloader.api.lib.mini.annotation.Patch;

@Patch.Class("cpw.mods.fml.common.discovery.asm.ModClassVisitor")
public class ModClassVisitorTransformer extends MiniPlusTransformer {

    @Patch.Method("<init>(Lcpw/mods/fml/common/discovery/asm/ASMModParser;)V")
    public void useASM5Opcode(PatchContext ctx) {
        migrateASM4ToASM5(ctx);
    }

}
