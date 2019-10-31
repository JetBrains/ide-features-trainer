/*
 * Copyright 2000-2019 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
 */
package training.check;

import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiFile;
import com.intellij.psi.html.HtmlTag;
import com.intellij.psi.util.PsiTreeUtil;

import java.util.Collection;

public class CheckMultipleSelections implements Check{

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

        final Collection<HtmlTag> childrenOfType1 = PsiTreeUtil.findChildrenOfType(psiFile, HtmlTag.class);

        int count = 0;

        for (HtmlTag htmlTag: childrenOfType1){
            if (htmlTag.getName().equals("th")) return false;
            if (htmlTag.getName().equals("td")) count++;
        }

        return count == 6;

    }

    @Override
    public boolean listenAllKeys() {
        return false;
    }

}
