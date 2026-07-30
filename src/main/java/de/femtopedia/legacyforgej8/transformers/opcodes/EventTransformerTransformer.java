package de.femtopedia.legacyforgej8.transformers.opcodes;

import de.femtopedia.legacyforgej8.MiniPlusTransformer;
import nilloader.api.lib.asm.tree.LabelNode;
import nilloader.api.lib.mini.PatchContext;
import nilloader.api.lib.mini.annotation.Patch;

@Patch.Class("net.minecraftforge.transformers.EventTransformer")
public class EventTransformerTransformer extends MiniPlusTransformer {

    @Patch.Method("buildEvents(Lorg/objectweb/asm/tree/ClassNode;)Z")
    public void useASM5Opcode(PatchContext ctx) {
        migrateASM4ToASM5(ctx);
    }

    @Patch.Method.Optional // If this method does not exist, we are (hopefully) on newer 1.4.7 Forge or 1.5.x
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
