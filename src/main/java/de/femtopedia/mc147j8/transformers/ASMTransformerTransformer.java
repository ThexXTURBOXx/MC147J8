package de.femtopedia.mc147j8.transformers;

import nilloader.api.lib.asm.Opcodes;
import nilloader.api.lib.mini.MiniTransformer;
import nilloader.api.lib.mini.PatchContext;
import nilloader.api.lib.mini.annotation.Patch;

@Patch.Class("cpw.mods.fml.common.asm.ASMTransformer")
public class ASMTransformerTransformer extends MiniTransformer {

    @Patch.Method("transform(Ljava/lang/String;[B)[B")
    public void useASM5Opcode(PatchContext ctx) {
        ctx.search(
                LDC(Opcodes.ASM4)
        ).jumpAfter();

        ctx.add(
                POP(),
                LDC(Opcodes.ASM5)
        );
    }

}
