package de.femtopedia.legacyforgej8.api;

import cpw.mods.fml.relauncher.RelaunchClassLoader;
import java.util.HashSet;
import java.util.Set;

/**
 * Provides useful API methods for injecting into Forge's/FML's ClassLoader ecosystem.
 */
public class API {

    private static final Set<String> classLoaderExceptions = new HashSet<>();
    private static final Set<String> transformerExceptions = new HashSet<>();

    static {
        // Add known candidates
        addClassLoaderExclusion("de.femtopedia.legacyforgej8."); // LegacyForgeJ8
        addTransformerExclusion("de.femtopedia.hufixes."); // HexxitUltimateFixes
        addTransformerExclusion("de.femtopedia.nil125fix."); // NilFix
        addTransformerExclusion("net.scoobis.nilmenu."); // NilMenu
    }

    /**
     * Use this to add your own ClassLoader exclusion rule to any
     * {@link RelaunchClassLoader} instance that is being loaded from now on.
     * The given string is being prefix-matched.
     * Classes that match prefixes in this exclusion list are loaded using
     * the parent ClassLoader instance of the {@link RelaunchClassLoader}.
     *
     * @param toExclude The ClassLoader exclusion rule to add.
     * @see RelaunchClassLoader#addClassLoaderExclusion(String)
     */
    public static void addClassLoaderExclusion(String toExclude) {
        classLoaderExceptions.add(toExclude);
    }

    /**
     * Use this to add your own transformer exclusion rule to any
     * {@link RelaunchClassLoader} instance that is being loaded from now on.
     * The given string is being prefix-matched.
     * Classes that match prefixes in this exclusion list are not being
     * transformed using the registered transformers.
     *
     * @param toExclude The transformer exclusion rule to add.
     * @see RelaunchClassLoader#addTransformerExclusion(String)
     */
    public static void addTransformerExclusion(String toExclude) {
        transformerExceptions.add(toExclude);
    }

    /**
     * YOU PROBABLY DO NOT NEED TO USE THIS!
     * Registers the currently registered exceptions into the given
     * {@link RelaunchClassLoader} instance.
     *
     * @param rcl The {@link RelaunchClassLoader} instance to register
     *            the exceptions in.
     */
    public static void registerExclusions(RelaunchClassLoader rcl) {
        classLoaderExceptions.forEach(rcl::addClassLoaderExclusion);
        transformerExceptions.forEach(rcl::addTransformerExclusion);
    }

}
