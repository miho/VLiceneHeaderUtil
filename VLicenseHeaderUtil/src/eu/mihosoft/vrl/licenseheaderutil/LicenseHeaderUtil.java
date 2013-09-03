/* LicenseHeaderUtil.java
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2009–2012 Steinbeis Forschungszentrum (STZ Ölbronn),
 * Copyright (c) 2006–2012 by Michael Hoffer
 * 
 * This file is part of Visual Reflection Library (VRL).
 *
 * VRL is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License version 3
 * as published by the Free Software Foundation.
 * 
 * see: http://opensource.org/licenses/LGPL-3.0
 *      file://path/to/VRL/src/eu/mihosoft/vrl/resources/license/lgplv3.txt
 *
 * VRL is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * This version of VRL includes copyright notice and attribution requirements.
 * According to the LGPL this information must be displayed even if you modify
 * the source code of VRL. Neither the VRL Canvas attribution icon nor any
 * copyright statement/attribution may be removed.
 *
 * Attribution Requirements:
 *
 * If you create derived work you must do three things regarding copyright
 * notice and author attribution.
 *
 * First, the following text must be displayed on the Canvas:
 * "based on VRL source code". In this case the VRL canvas icon must be removed.
 * 
 * Second, the copyright notice must remain. It must be reproduced in any
 * program that uses VRL.
 *
 * Third, add an additional notice, stating that you modified VRL. In addition
 * you must cite the publications listed below. A suitable notice might read
 * "VRL source code modified by YourName 2012".
 * 
 * Note, that these requirements are in full accordance with the LGPL v3
 * (see 7. Additional Terms, b).
 *
 * Publications
 *
 * M. Hoffer, C.Poliwoda, G.Wittum. Visual Reflection Library -
 * A Framework for Declarative GUI Programming on the Java Platform.
 * Computing and Visualization in Science, 2011, in press.
 */
package eu.mihosoft.vrl.licenseheaderutil;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

/**
 * This utility class provides methods for easily add/replace license header
 * information in .java source files.
 *
 * @author Michael Hoffer &lt;info@michaelhoffer.de&gt;
 */
public class LicenseHeaderUtil {

    private LicenseHeaderUtil() {
        throw new AssertionError("Don't instanciate me!");
    }

    /**
     * Changes the license header of the specified .java source file. Everything
     * between the beginning of the file and the package declaration wil be
     * replaced with the specified string.
     *
     * @param srcFile the source file
     * @param destFile the destination file (can be equal to src file)
     * @param licenseComment license comment (must be a valid Java comment)
     * @return <code>true</code> if the file could be processed;
     * <code>false</code> otherwise
     * @throws RecognitionException
     * @throws IOException
     */
    public static boolean changeLicenseHeaderOfFile(
            Path srcFile,
            Path destFile,
            String licenseComment) throws RecognitionException, IOException {
        
        Date now = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat yearFormat = new SimpleDateFormat("yyyy");
        DateFormat monthFormat = new SimpleDateFormat("MM");
        DateFormat dayFormat = new SimpleDateFormat("dd");

        licenseComment = licenseComment.replace(
                "${VRL-LICENSE-HEADER-FILE-NAME}",
                destFile.getFileName().toString());

        licenseComment = licenseComment.replace(
                "${VRL-LICENSE-HEADER-YEAR}",
                "" + yearFormat.format(now));
        
        licenseComment = licenseComment.replace(
                "${VRL-LICENSE-HEADER-MONTH}",
                "" + monthFormat.format(now));
        
        licenseComment = licenseComment.replace(
                "${VRL-LICENSE-HEADER-DAY}",
                "" + dayFormat.format(now));
        
        
        licenseComment = licenseComment.replace(
                "${VRL-LICENSE-HEADER-DATE}",
                "" + dateFormat.format(now));

        InputStream is = null;

        if (srcFile != null && Files.isRegularFile(srcFile)) {
            is = Files.newInputStream(srcFile);
        }

        ANTLRInputStream input = new ANTLRInputStream(is);

        JavaLexer lexer = new JavaLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JavaParser parser = new JavaParser(tokens);
        ParserRuleContext tree = parser.compilationUnit();

        ParseTreeWalker walker = new ParseTreeWalker();
        ChangeLicenseHeaderListener extractor = 
                new ChangeLicenseHeaderListener(parser);
        walker.walk(extractor, tree);

        String fullCode = licenseComment + "\n" + extractor.getCode();
        if (extractor.hasPackage()) {
            Files.write(destFile, fullCode.getBytes("UTF-8"));
        } else {
            System.out.println(" -> skipping: " + srcFile);
            return false;
        }

        return true;
    }

    /**
     * Changes the license header of all .java source files in the specified
     * directory. Everything between the beginning of the file and the package
     * declaration wil be replaced with the specified string.
     *
     * @param srcFile the source directory
     * @param destFile the destination directory (can be equal to src directory)
     * @param newLicenseHeader location of license comment file (must be contain
     * a valid Java comment)
     * @return <code>true</code> if the file could be processed;
     * <code>false</code> otherwise
     */
    public static boolean changeLicenseHeaderInDir(
            Path inputDir,
            Path outputDir, Path newLicenseHeader) {
        try {
            List<String> lines = Files.readAllLines(newLicenseHeader,
                    Charset.forName("UTF-8"));

            StringBuilder sb = new StringBuilder();

            for (String string : lines) {
                sb.append(string).append("\n");
            }

            return changeLicenseHeaderInDir(inputDir, outputDir, sb.toString());
        } catch (IOException ex) {
            Logger.getLogger(LicenseHeaderUtil.class.getName()).
                    log(Level.SEVERE, null, ex);
        }

        return false;
    }

    /**
     * Changes the license header of all .java source files in the specified
     * directory. Everything between the beginning of the file and the package
     * declaration wil be replaced with the specified string.
     *
     * @param srcFile the source directory
     * @param destFile the destination directory (can be equal to src directory)
     * @param licenseComment license comment (must be a valid Java comment)
     * @return <code>true</code> if the file could be processed;
     * <code>false</code> otherwise
     */
    public static boolean changeLicenseHeaderInDir(
            Path inputDir,
            Path outputDir, String newLicenseHeader) {
        areDirs(inputDir);

        ChangeLicenseVisitor changeLicense =
                new ChangeLicenseVisitor(inputDir, outputDir, newLicenseHeader);
        try {
            Files.walkFileTree(inputDir, changeLicense);
        } catch (IOException ex) {
            Logger.getLogger(LicenseHeaderUtil.class.getName()).
                    log(Level.SEVERE, null, ex);
        }

        return changeLicense.getSkipped().isEmpty();
    }

    private static void areDirs(Path... paths) {
        for (Path path : paths) {
            Objects.requireNonNull(path);
            if (!Files.isDirectory(path)) {
                throw new IllegalArgumentException(
                        String.format("%s is not a directory", path.toString()));
            }
        }
    }
}

/**
 *
 * @author Michael Hoffer &lt;info@michaelhoffer.de&gt;
 */
class ChangeLicenseVisitor extends SimpleFileVisitor<Path> {

    private Path fromPath;
    private Path toPath;
    private StandardCopyOption copyOption;
    private String newLicenseHeader;
    private List<Path> skipped = new ArrayList<>();

    public ChangeLicenseVisitor(Path fromPath, Path toPath, StandardCopyOption copyOption, String newLicenseHeader) {
        this.fromPath = fromPath;
        this.toPath = toPath;
        this.copyOption = copyOption;
        this.newLicenseHeader = newLicenseHeader;
    }

    public ChangeLicenseVisitor(Path fromPath, Path toPath, String newLicenseHeader) {
        this(fromPath, toPath, StandardCopyOption.REPLACE_EXISTING, newLicenseHeader);
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {

        Path targetPath = toPath.resolve(fromPath.relativize(dir));
        if (!Files.exists(targetPath)) {
            Files.createDirectory(targetPath);
        }
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

        if (file.toString().endsWith(".java")) {
            System.out.println(" -> changing: " + file.toString());

            Path to = toPath.resolve(fromPath.relativize(file));

            if (!LicenseHeaderUtil.changeLicenseHeaderOfFile(file, to, newLicenseHeader)) {
                getSkipped().add(file);
            }
        }

        return FileVisitResult.CONTINUE;
    }

    /**
     * @return the skipped
     */
    public List<Path> getSkipped() {
        return skipped;
    }
}
