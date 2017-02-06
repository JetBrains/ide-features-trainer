package training.lang;

import com.intellij.ide.impl.ProjectUtil;
import com.intellij.ide.util.projectWizard.ModuleBuilder;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;

public abstract class LangSupport implements LangSupportInterface {
    public boolean needToCheckSDK(){
        return true;
    }

    @Nullable
    @Override
    public Project createProject(@NotNull String projectName, @Nullable Project projectToClose) {
        ModuleBuilder moduleBuilder = getModuleBuilder();
        if(moduleBuilder != null && !moduleBuilder.isUpdate()) {
            return moduleBuilder.createProject(projectName, getProjectFilePath(projectName));
        }
        return projectToClose;
    }

    @NotNull
    @Override
    public String getProjectFilePath(@NotNull String projectName) {
        return ProjectUtil.getBaseDir() + File.separator + projectName;
    }
}
