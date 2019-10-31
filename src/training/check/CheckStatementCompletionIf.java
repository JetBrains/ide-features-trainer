/*
 * Copyright 2000-2019 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
 */
package training.check;

import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiForStatement;
import com.intellij.psi.util.PsiTreeUtil;

public class CheckStatementCompletionIf implements Check{

    Project project;
    Editor editor;

    @Override
    public void set(Project project, Editor editor) {
        this.project = project;
        this.editor = editor;
    }

    @Override
    public void before() {
    }

    @Override
    public boolean check() {
        final Document document = editor.getDocument();
        final PsiFile psiFile = PsiDocumentManager.getInstance(project).getPsiFile(document);

        final Object[] psiForStatements = (Object[]) PsiTreeUtil.findChildrenOfType(psiFile, PsiForStatement.class).toArray();
        if (psiForStatements.length < 2) return false;

        final PsiForStatement psiForStatement = (PsiForStatement) psiForStatements[1];

        String text = psiForStatement.getBody().getText();
        String trimmedText = text.replaceAll("\\s+", "");
        return trimmedText.equals("{if(){}}");
    }

    @Override
    public boolean listenAllKeys() {
        return false;
    }

}
