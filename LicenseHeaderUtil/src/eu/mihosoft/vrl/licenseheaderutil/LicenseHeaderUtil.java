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
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTreeWalker;


/**
 * 
 * @author Michael Hoffer &lt;info@michaelhoffer.de&gt;
 */
public class LicenseHeaderUtil {

    public static boolean changeLicenseHeaderOfFile(
            Path inputFile,
            Path outputFile,
            String licenseComment) throws RecognitionException, IOException {

        licenseComment = licenseComment.replace(
                "${VRL-LICENSE-HEADER-FILE-NAME}",
                outputFile.getFileName().toString());

        InputStream is = null;

        if (inputFile != null && Files.isRegularFile(inputFile)) {
            is = Files.newInputStream(inputFile);
        }

        ANTLRInputStream input = new ANTLRInputStream(is);

        JavaLexer lexer = new JavaLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JavaParser parser = new JavaParser(tokens);
        ParserRuleContext tree = parser.compilationUnit();

        ParseTreeWalker walker = new ParseTreeWalker();
        ChangeLicenseHeaderListener extractor = new ChangeLicenseHeaderListener(parser);
        walker.walk(extractor, tree);

        String fullCode = licenseComment + "\n" + extractor.getCode();
        if (extractor.hasPackage()) {
            Files.write(outputFile, fullCode.getBytes("UTF-8"));
        } else {
            System.out.println(" -> skipping: " + inputFile);
            return false;
        }

        return true;
    }
    
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
            System.out.println("changing: " + file.toString());

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
