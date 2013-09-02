/* JavaListener.java
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

import org.antlr.v4.runtime.tree.*;
import org.antlr.v4.runtime.Token;

public interface JavaListener extends ParseTreeListener {
	void enterInnerCreator(JavaParser.InnerCreatorContext ctx);
	void exitInnerCreator(JavaParser.InnerCreatorContext ctx);

	void enterGenericMethodDeclaration(JavaParser.GenericMethodDeclarationContext ctx);
	void exitGenericMethodDeclaration(JavaParser.GenericMethodDeclarationContext ctx);

	void enterExpressionList(JavaParser.ExpressionListContext ctx);
	void exitExpressionList(JavaParser.ExpressionListContext ctx);

	void enterTypeDeclaration(JavaParser.TypeDeclarationContext ctx);
	void exitTypeDeclaration(JavaParser.TypeDeclarationContext ctx);

	void enterForUpdate(JavaParser.ForUpdateContext ctx);
	void exitForUpdate(JavaParser.ForUpdateContext ctx);

	void enterAnnotation(JavaParser.AnnotationContext ctx);
	void exitAnnotation(JavaParser.AnnotationContext ctx);

	void enterEnumConstant(JavaParser.EnumConstantContext ctx);
	void exitEnumConstant(JavaParser.EnumConstantContext ctx);

	void enterImportDeclaration(JavaParser.ImportDeclarationContext ctx);
	void exitImportDeclaration(JavaParser.ImportDeclarationContext ctx);

	void enterAnnotationMethodOrConstantRest(JavaParser.AnnotationMethodOrConstantRestContext ctx);
	void exitAnnotationMethodOrConstantRest(JavaParser.AnnotationMethodOrConstantRestContext ctx);

	void enterEnumConstantName(JavaParser.EnumConstantNameContext ctx);
	void exitEnumConstantName(JavaParser.EnumConstantNameContext ctx);

	void enterFinallyBlock(JavaParser.FinallyBlockContext ctx);
	void exitFinallyBlock(JavaParser.FinallyBlockContext ctx);

	void enterVariableDeclarators(JavaParser.VariableDeclaratorsContext ctx);
	void exitVariableDeclarators(JavaParser.VariableDeclaratorsContext ctx);

	void enterElementValuePairs(JavaParser.ElementValuePairsContext ctx);
	void exitElementValuePairs(JavaParser.ElementValuePairsContext ctx);

	void enterInterfaceMethodDeclaration(JavaParser.InterfaceMethodDeclarationContext ctx);
	void exitInterfaceMethodDeclaration(JavaParser.InterfaceMethodDeclarationContext ctx);

	void enterInterfaceBodyDeclaration(JavaParser.InterfaceBodyDeclarationContext ctx);
	void exitInterfaceBodyDeclaration(JavaParser.InterfaceBodyDeclarationContext ctx);

	void enterEnumConstants(JavaParser.EnumConstantsContext ctx);
	void exitEnumConstants(JavaParser.EnumConstantsContext ctx);

	void enterCatchClause(JavaParser.CatchClauseContext ctx);
	void exitCatchClause(JavaParser.CatchClauseContext ctx);

	void enterConstantExpression(JavaParser.ConstantExpressionContext ctx);
	void exitConstantExpression(JavaParser.ConstantExpressionContext ctx);

	void enterEnumDeclaration(JavaParser.EnumDeclarationContext ctx);
	void exitEnumDeclaration(JavaParser.EnumDeclarationContext ctx);

	void enterExplicitGenericInvocationSuffix(JavaParser.ExplicitGenericInvocationSuffixContext ctx);
	void exitExplicitGenericInvocationSuffix(JavaParser.ExplicitGenericInvocationSuffixContext ctx);

	void enterTypeParameter(JavaParser.TypeParameterContext ctx);
	void exitTypeParameter(JavaParser.TypeParameterContext ctx);

	void enterEnumBodyDeclarations(JavaParser.EnumBodyDeclarationsContext ctx);
	void exitEnumBodyDeclarations(JavaParser.EnumBodyDeclarationsContext ctx);

	void enterTypeBound(JavaParser.TypeBoundContext ctx);
	void exitTypeBound(JavaParser.TypeBoundContext ctx);

	void enterStatementExpression(JavaParser.StatementExpressionContext ctx);
	void exitStatementExpression(JavaParser.StatementExpressionContext ctx);

	void enterVariableInitializer(JavaParser.VariableInitializerContext ctx);
	void exitVariableInitializer(JavaParser.VariableInitializerContext ctx);

	void enterBlock(JavaParser.BlockContext ctx);
	void exitBlock(JavaParser.BlockContext ctx);

	void enterGenericInterfaceMethodDeclaration(JavaParser.GenericInterfaceMethodDeclarationContext ctx);
	void exitGenericInterfaceMethodDeclaration(JavaParser.GenericInterfaceMethodDeclarationContext ctx);

	void enterLocalVariableDeclarationStatement(JavaParser.LocalVariableDeclarationStatementContext ctx);
	void exitLocalVariableDeclarationStatement(JavaParser.LocalVariableDeclarationStatementContext ctx);

	void enterSuperSuffix(JavaParser.SuperSuffixContext ctx);
	void exitSuperSuffix(JavaParser.SuperSuffixContext ctx);

	void enterFieldDeclaration(JavaParser.FieldDeclarationContext ctx);
	void exitFieldDeclaration(JavaParser.FieldDeclarationContext ctx);

	void enterFormalParameterList(JavaParser.FormalParameterListContext ctx);
	void exitFormalParameterList(JavaParser.FormalParameterListContext ctx);

	void enterExplicitGenericInvocation(JavaParser.ExplicitGenericInvocationContext ctx);
	void exitExplicitGenericInvocation(JavaParser.ExplicitGenericInvocationContext ctx);

	void enterParExpression(JavaParser.ParExpressionContext ctx);
	void exitParExpression(JavaParser.ParExpressionContext ctx);

	void enterSwitchLabel(JavaParser.SwitchLabelContext ctx);
	void exitSwitchLabel(JavaParser.SwitchLabelContext ctx);

	void enterTypeParameters(JavaParser.TypeParametersContext ctx);
	void exitTypeParameters(JavaParser.TypeParametersContext ctx);

	void enterQualifiedName(JavaParser.QualifiedNameContext ctx);
	void exitQualifiedName(JavaParser.QualifiedNameContext ctx);

	void enterClassDeclaration(JavaParser.ClassDeclarationContext ctx);
	void exitClassDeclaration(JavaParser.ClassDeclarationContext ctx);

	void enterAnnotationConstantRest(JavaParser.AnnotationConstantRestContext ctx);
	void exitAnnotationConstantRest(JavaParser.AnnotationConstantRestContext ctx);

	void enterTypeName(JavaParser.TypeNameContext ctx);
	void exitTypeName(JavaParser.TypeNameContext ctx);

	void enterArguments(JavaParser.ArgumentsContext ctx);
	void exitArguments(JavaParser.ArgumentsContext ctx);

	void enterConstructorBody(JavaParser.ConstructorBodyContext ctx);
	void exitConstructorBody(JavaParser.ConstructorBodyContext ctx);

	void enterFormalParameters(JavaParser.FormalParametersContext ctx);
	void exitFormalParameters(JavaParser.FormalParametersContext ctx);

	void enterTypeArgument(JavaParser.TypeArgumentContext ctx);
	void exitTypeArgument(JavaParser.TypeArgumentContext ctx);

	void enterForInit(JavaParser.ForInitContext ctx);
	void exitForInit(JavaParser.ForInitContext ctx);

	void enterVariableDeclarator(JavaParser.VariableDeclaratorContext ctx);
	void exitVariableDeclarator(JavaParser.VariableDeclaratorContext ctx);

	void enterAnnotationTypeDeclaration(JavaParser.AnnotationTypeDeclarationContext ctx);
	void exitAnnotationTypeDeclaration(JavaParser.AnnotationTypeDeclarationContext ctx);

	void enterExpression(JavaParser.ExpressionContext ctx);
	void exitExpression(JavaParser.ExpressionContext ctx);

	void enterResources(JavaParser.ResourcesContext ctx);
	void exitResources(JavaParser.ResourcesContext ctx);

	void enterFormalParameter(JavaParser.FormalParameterContext ctx);
	void exitFormalParameter(JavaParser.FormalParameterContext ctx);

	void enterType(JavaParser.TypeContext ctx);
	void exitType(JavaParser.TypeContext ctx);

	void enterElementValueArrayInitializer(JavaParser.ElementValueArrayInitializerContext ctx);
	void exitElementValueArrayInitializer(JavaParser.ElementValueArrayInitializerContext ctx);

	void enterAnnotationName(JavaParser.AnnotationNameContext ctx);
	void exitAnnotationName(JavaParser.AnnotationNameContext ctx);

	void enterEnhancedForControl(JavaParser.EnhancedForControlContext ctx);
	void exitEnhancedForControl(JavaParser.EnhancedForControlContext ctx);

	void enterAnnotationMethodRest(JavaParser.AnnotationMethodRestContext ctx);
	void exitAnnotationMethodRest(JavaParser.AnnotationMethodRestContext ctx);

	void enterPrimary(JavaParser.PrimaryContext ctx);
	void exitPrimary(JavaParser.PrimaryContext ctx);

	void enterClassBody(JavaParser.ClassBodyContext ctx);
	void exitClassBody(JavaParser.ClassBodyContext ctx);

	void enterClassOrInterfaceModifier(JavaParser.ClassOrInterfaceModifierContext ctx);
	void exitClassOrInterfaceModifier(JavaParser.ClassOrInterfaceModifierContext ctx);

	void enterDefaultValue(JavaParser.DefaultValueContext ctx);
	void exitDefaultValue(JavaParser.DefaultValueContext ctx);

	void enterVariableModifier(JavaParser.VariableModifierContext ctx);
	void exitVariableModifier(JavaParser.VariableModifierContext ctx);

	void enterConstDeclaration(JavaParser.ConstDeclarationContext ctx);
	void exitConstDeclaration(JavaParser.ConstDeclarationContext ctx);

	void enterCreatedName(JavaParser.CreatedNameContext ctx);
	void exitCreatedName(JavaParser.CreatedNameContext ctx);

	void enterInterfaceDeclaration(JavaParser.InterfaceDeclarationContext ctx);
	void exitInterfaceDeclaration(JavaParser.InterfaceDeclarationContext ctx);

	void enterPackageDeclaration(JavaParser.PackageDeclarationContext ctx);
	void exitPackageDeclaration(JavaParser.PackageDeclarationContext ctx);

	void enterConstantDeclarator(JavaParser.ConstantDeclaratorContext ctx);
	void exitConstantDeclarator(JavaParser.ConstantDeclaratorContext ctx);

	void enterCatchType(JavaParser.CatchTypeContext ctx);
	void exitCatchType(JavaParser.CatchTypeContext ctx);

	void enterTypeArguments(JavaParser.TypeArgumentsContext ctx);
	void exitTypeArguments(JavaParser.TypeArgumentsContext ctx);

	void enterClassCreatorRest(JavaParser.ClassCreatorRestContext ctx);
	void exitClassCreatorRest(JavaParser.ClassCreatorRestContext ctx);

	void enterModifier(JavaParser.ModifierContext ctx);
	void exitModifier(JavaParser.ModifierContext ctx);

	void enterStatement(JavaParser.StatementContext ctx);
	void exitStatement(JavaParser.StatementContext ctx);

	void enterInterfaceBody(JavaParser.InterfaceBodyContext ctx);
	void exitInterfaceBody(JavaParser.InterfaceBodyContext ctx);

	void enterPackageOrTypeName(JavaParser.PackageOrTypeNameContext ctx);
	void exitPackageOrTypeName(JavaParser.PackageOrTypeNameContext ctx);

	void enterClassBodyDeclaration(JavaParser.ClassBodyDeclarationContext ctx);
	void exitClassBodyDeclaration(JavaParser.ClassBodyDeclarationContext ctx);

	void enterLastFormalParameter(JavaParser.LastFormalParameterContext ctx);
	void exitLastFormalParameter(JavaParser.LastFormalParameterContext ctx);

	void enterForControl(JavaParser.ForControlContext ctx);
	void exitForControl(JavaParser.ForControlContext ctx);

	void enterTypeList(JavaParser.TypeListContext ctx);
	void exitTypeList(JavaParser.TypeListContext ctx);

	void enterLocalVariableDeclaration(JavaParser.LocalVariableDeclarationContext ctx);
	void exitLocalVariableDeclaration(JavaParser.LocalVariableDeclarationContext ctx);

	void enterVariableDeclaratorId(JavaParser.VariableDeclaratorIdContext ctx);
	void exitVariableDeclaratorId(JavaParser.VariableDeclaratorIdContext ctx);

	void enterCompilationUnit(JavaParser.CompilationUnitContext ctx);
	void exitCompilationUnit(JavaParser.CompilationUnitContext ctx);

	void enterElementValue(JavaParser.ElementValueContext ctx);
	void exitElementValue(JavaParser.ElementValueContext ctx);

	void enterClassOrInterfaceType(JavaParser.ClassOrInterfaceTypeContext ctx);
	void exitClassOrInterfaceType(JavaParser.ClassOrInterfaceTypeContext ctx);

	void enterTypeArgumentsOrDiamond(JavaParser.TypeArgumentsOrDiamondContext ctx);
	void exitTypeArgumentsOrDiamond(JavaParser.TypeArgumentsOrDiamondContext ctx);

	void enterAnnotationTypeElementDeclaration(JavaParser.AnnotationTypeElementDeclarationContext ctx);
	void exitAnnotationTypeElementDeclaration(JavaParser.AnnotationTypeElementDeclarationContext ctx);

	void enterBlockStatement(JavaParser.BlockStatementContext ctx);
	void exitBlockStatement(JavaParser.BlockStatementContext ctx);

	void enterAnnotationTypeBody(JavaParser.AnnotationTypeBodyContext ctx);
	void exitAnnotationTypeBody(JavaParser.AnnotationTypeBodyContext ctx);

	void enterQualifiedNameList(JavaParser.QualifiedNameListContext ctx);
	void exitQualifiedNameList(JavaParser.QualifiedNameListContext ctx);

	void enterCreator(JavaParser.CreatorContext ctx);
	void exitCreator(JavaParser.CreatorContext ctx);

	void enterMemberDeclaration(JavaParser.MemberDeclarationContext ctx);
	void exitMemberDeclaration(JavaParser.MemberDeclarationContext ctx);

	void enterMethodDeclaration(JavaParser.MethodDeclarationContext ctx);
	void exitMethodDeclaration(JavaParser.MethodDeclarationContext ctx);

	void enterAnnotationTypeElementRest(JavaParser.AnnotationTypeElementRestContext ctx);
	void exitAnnotationTypeElementRest(JavaParser.AnnotationTypeElementRestContext ctx);

	void enterResourceSpecification(JavaParser.ResourceSpecificationContext ctx);
	void exitResourceSpecification(JavaParser.ResourceSpecificationContext ctx);

	void enterConstructorDeclaration(JavaParser.ConstructorDeclarationContext ctx);
	void exitConstructorDeclaration(JavaParser.ConstructorDeclarationContext ctx);

	void enterResource(JavaParser.ResourceContext ctx);
	void exitResource(JavaParser.ResourceContext ctx);

	void enterElementValuePair(JavaParser.ElementValuePairContext ctx);
	void exitElementValuePair(JavaParser.ElementValuePairContext ctx);

	void enterMethodBody(JavaParser.MethodBodyContext ctx);
	void exitMethodBody(JavaParser.MethodBodyContext ctx);

	void enterArrayInitializer(JavaParser.ArrayInitializerContext ctx);
	void exitArrayInitializer(JavaParser.ArrayInitializerContext ctx);

	void enterNonWildcardTypeArgumentsOrDiamond(JavaParser.NonWildcardTypeArgumentsOrDiamondContext ctx);
	void exitNonWildcardTypeArgumentsOrDiamond(JavaParser.NonWildcardTypeArgumentsOrDiamondContext ctx);

	void enterPrimitiveType(JavaParser.PrimitiveTypeContext ctx);
	void exitPrimitiveType(JavaParser.PrimitiveTypeContext ctx);

	void enterNonWildcardTypeArguments(JavaParser.NonWildcardTypeArgumentsContext ctx);
	void exitNonWildcardTypeArguments(JavaParser.NonWildcardTypeArgumentsContext ctx);

	void enterArrayCreatorRest(JavaParser.ArrayCreatorRestContext ctx);
	void exitArrayCreatorRest(JavaParser.ArrayCreatorRestContext ctx);

	void enterInterfaceMemberDeclaration(JavaParser.InterfaceMemberDeclarationContext ctx);
	void exitInterfaceMemberDeclaration(JavaParser.InterfaceMemberDeclarationContext ctx);

	void enterGenericConstructorDeclaration(JavaParser.GenericConstructorDeclarationContext ctx);
	void exitGenericConstructorDeclaration(JavaParser.GenericConstructorDeclarationContext ctx);

	void enterLiteral(JavaParser.LiteralContext ctx);
	void exitLiteral(JavaParser.LiteralContext ctx);

	void enterSwitchBlockStatementGroup(JavaParser.SwitchBlockStatementGroupContext ctx);
	void exitSwitchBlockStatementGroup(JavaParser.SwitchBlockStatementGroupContext ctx);
}