package de.femtopedia.legacyforgej8.transformers;

import nilloader.api.lib.asm.Opcodes;
import nilloader.api.lib.mini.MiniTransformer;
import nilloader.api.lib.mini.PatchContext;
import nilloader.api.lib.mini.annotation.Patch;

@Patch.Class("cpw.mods.fml.common.discovery.asm.ModMethodVisitor")
public class ModMethodVisitorTransformer extends MiniTransformer {

    @Patch.Method("<init>(Ljava/lang/String;Lcpw/mods/fml/common/discovery/asm/ASMModParser;)V")
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
