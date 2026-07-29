package de.femtopedia.mc147j8;

import de.femtopedia.mc147j8.transformers.ASMTransformerTransformer;
import de.femtopedia.mc147j8.transformers.CoreFMLLibrariesTransformer;
import de.femtopedia.mc147j8.transformers.EventTransformerTransformer;
import de.femtopedia.mc147j8.transformers.FMLRelauncherTransformer;
import de.femtopedia.mc147j8.transformers.JarDiscovererTransformer;
import de.femtopedia.mc147j8.transformers.MLDetectorClassVisitorTransformer;
import de.femtopedia.mc147j8.transformers.ModAnnotationVisitorTransformer;
import de.femtopedia.mc147j8.transformers.ModClassVisitorTransformer;
import de.femtopedia.mc147j8.transformers.ModFieldVisitorTransformer;
import de.femtopedia.mc147j8.transformers.ModMethodVisitorTransformer;
import de.femtopedia.mc147j8.transformers.RelaunchLibraryManagerTransformer;
import nilloader.api.ClassTransformer;
import nilloader.api.NilLogger;

public class MC147J8Premain implements Runnable {

    public static final NilLogger log = NilLogger.get("MC147J8");

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
    }

}
