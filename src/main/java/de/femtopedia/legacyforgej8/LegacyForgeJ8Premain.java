package de.femtopedia.legacyforgej8;

import de.femtopedia.legacyforgej8.transformers.ASMTransformerTransformer;
import de.femtopedia.legacyforgej8.transformers.CoreFMLLibrariesTransformer;
import de.femtopedia.legacyforgej8.transformers.EventTransformerTransformer;
import de.femtopedia.legacyforgej8.transformers.FMLRelauncherTransformer;
import de.femtopedia.legacyforgej8.transformers.JarDiscovererTransformer;
import de.femtopedia.legacyforgej8.transformers.MLDetectorClassVisitorTransformer;
import de.femtopedia.legacyforgej8.transformers.MethodInsnNodeTransformer;
import de.femtopedia.legacyforgej8.transformers.ModAnnotationVisitorTransformer;
import de.femtopedia.legacyforgej8.transformers.ModClassVisitorTransformer;
import de.femtopedia.legacyforgej8.transformers.ModFieldVisitorTransformer;
import de.femtopedia.legacyforgej8.transformers.ModMethodVisitorTransformer;
import de.femtopedia.legacyforgej8.transformers.RelaunchClassLoaderTransformer;
import de.femtopedia.legacyforgej8.transformers.RelaunchLibraryManagerTransformer;
import nilloader.api.ClassTransformer;
import nilloader.api.NilLogger;

public class LegacyForgeJ8Premain implements Runnable {

    public static final NilLogger log = NilLogger.get("LegacyForgeJ8");

    @Override
    public void run() {
        ClassTransformer.register(new CoreFMLLibrariesTransformer());
        ClassTransformer.register(new RelaunchLibraryManagerTransformer());

        ClassTransformer.register(new ASMTransformerTransformer());
        ClassTransformer.register(new EventTransformerTransformer());
        ClassTransformer.register(new MLDetectorClassVisitorTransformer());

        ClassTransformer.register(new ModAnnotationVisitorTransformer());
        ClassTransformer.register(new ModClassVisitorTransformer());
        ClassTransformer.register(new ModFieldVisitorTransformer());
        ClassTransformer.register(new ModMethodVisitorTransformer());

        ClassTransformer.register(new FMLRelauncherTransformer());

        log.info("Java 8 mods are now supported!");

        ClassTransformer.register(new JarDiscovererTransformer());

        log.info("Skipping some unsupported Java 9+ entries...");

        ClassTransformer.register(new RelaunchClassLoaderTransformer());

        log.info("Injected API for registering own exclusions.");

        ClassTransformer.register(new MethodInsnNodeTransformer());

        log.info("Patched ASM itself just to be safe.");
    }

}
