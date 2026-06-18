package de.femtopedia.mc147j8.transformers;

import nilloader.api.lib.asm.Opcodes;
import nilloader.api.lib.asm.tree.LabelNode;
import nilloader.api.lib.mini.MiniTransformer;
import nilloader.api.lib.mini.PatchContext;
import nilloader.api.lib.mini.PatchContext.SearchResult;
import nilloader.api.lib.mini.annotation.Patch;

@Patch.Class("net.minecraftforge.transformers.EventTransformer")
public class EventTransformerTransformer extends MiniTransformer {

    @Patch.Method("buildEvents(Lorg/objectweb/asm/tree/ClassNode;)Z")
    public void useASM5Opcode(PatchContext ctx) {
        while (true) {
            SearchResult res = ctx.search(
                    LDC(Opcodes.ASM4)
            );
            if (!res.isSuccessful()) break;
            res.jumpAfter();

            ctx.add(
                    POP(),
                    LDC(Opcodes.ASM5)
            );
        }
    }

    @Patch.Method.AffectsControlFlow
    @Patch.Method("transform(Ljava/lang/String;[B)[B")
    public void makeSureClassContentIsNotNull(PatchContext ctx) {
        LabelNode Lreturn = new LabelNode();

        ctx.jumpToStart();
        ctx.add(
                ALOAD(2),
                IFNULL(Lreturn)
        );

        ctx.search(
                ARETURN()
        ).jumpBefore();
        ctx.add(
                POP(),
                Lreturn,
                ALOAD(2)
        );
    }

}
