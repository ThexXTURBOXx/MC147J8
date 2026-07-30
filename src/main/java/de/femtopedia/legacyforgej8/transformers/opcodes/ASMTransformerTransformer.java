package de.femtopedia.legacyforgej8.transformers.opcodes;

import de.femtopedia.legacyforgej8.MiniPlusTransformer;
import nilloader.api.lib.mini.PatchContext;
import nilloader.api.lib.mini.annotation.Patch;

@Patch.Class("cpw.mods.fml.common.asm.ASMTransformer")
public class ASMTransformerTransformer extends MiniPlusTransformer {

    @Patch.Method("transform(Ljava/lang/String;[B)[B")
    public void useASM5Opcode(PatchContext ctx) {
        migrateASM4ToASM5(ctx);
    }

}
