package de.femtopedia.legacyforgej8;

import nilloader.api.ClassRetransformer;
import nilloader.api.lib.asm.Opcodes;
import nilloader.api.lib.asm.tree.AbstractInsnNode;
import nilloader.api.lib.asm.tree.InsnList;
import nilloader.api.lib.mini.MiniTransformer;
import nilloader.api.lib.mini.PatchContext;
import nilloader.api.lib.mini.PatchContext.SearchResult;

public abstract class MiniPlusTransformer extends MiniTransformer implements ClassRetransformer {

    protected final String hooks() {
        return getClass().getName().replace('.', '/') + "$Hooks";
    }

    protected final InsnList toInsnList(AbstractInsnNode... insns) {
        InsnList li = new InsnList();
        for (AbstractInsnNode ain : insns) li.add(ain);
        return li;
    }

    protected final void migrateASM4ToASM5(PatchContext ctx) {
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

}
