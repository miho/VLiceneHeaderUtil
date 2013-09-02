/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.mihosoft.vrl.licenseheaderutil;

/**
 * *
 * Excerpted from "The Definitive ANTLR 4 Reference", published by The Pragmatic
 * Bookshelf. Copyrights apply to this code. It may not be used to create
 * training material, courses, books, articles, and the like. Contact us if you
 * are in doubt. We make no guarantees that this code is fit for any purpose.
 * Visit http://www.pragmaticprogrammer.com/titles/tpantlr2 for more book
 * information.
**
 */
import org.antlr.v4.runtime.TokenStream;

public class ChangeLicenseHeaderListener extends JavaBaseListener {

    private JavaParser parser;
    private String code;
    private boolean hasPackage = false;

    public ChangeLicenseHeaderListener(JavaParser parser) {
        this.parser = parser;
    }
    
    public boolean hasPackage() {
        return hasPackage;
    }
    

    @Override
    public void enterCompilationUnit(JavaParser.CompilationUnitContext ctx) {
        TokenStream tokens = parser.getTokenStream();

        code = "";

        if (ctx.packageDeclaration() != null) {
            code = tokens.getText(
                    ctx.packageDeclaration().start,
                    tokens.get(tokens.size() - 1));
            hasPackage = true;
        } else if (!ctx.importDeclaration().isEmpty()) {
            code = tokens.getText(
                    ctx.importDeclaration(0).start,
                    tokens.get(tokens.size() - 1));
            hasPackage = false;
        } else if (!ctx.typeDeclaration().isEmpty()) {
            code = tokens.getText(
                    ctx.typeDeclaration(0).start,
                    tokens.get(tokens.size() - 1));
            hasPackage = false;
        }

    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }
}