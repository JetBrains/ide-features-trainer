package training.learn

import com.intellij.ide.impl.NewProjectUtil
import com.intellij.ide.impl.ProjectUtil
import com.intellij.ide.util.projectWizard.ModuleBuilder
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.project.ex.ProjectManagerEx
import com.intellij.openapi.roots.ui.configuration.ModulesProvider
import com.intellij.openapi.util.io.FileUtil
import com.intellij.openapi.wm.IdeFocusManager
import com.intellij.openapi.wm.WindowManager
import com.intellij.openapi.wm.ex.IdeFrameEx
import com.intellij.openapi.wm.impl.IdeFrameImpl
import training.lang.LangManager
import training.lang.LangSupportInterface
import training.learn.dialogs.LearnProjectWarningDialog
import java.io.File
import java.io.IOException

/**
 * Created by karashevich on 24/09/15.
 */
object NewLearnProjectUtil {

    @Throws(IOException::class)
    fun createLearnProject(projectName: String, projectToClose: Project?, langSupport: LangSupportInterface): Project? {
        val projectManager = ProjectManagerEx.getInstanceEx()
        val moduleBuilder : ModuleBuilder? = langSupport.getModuleBuilder()
        val projectFilePath = langSupport.getProjectFilePath(projectName)

        try {
            val projectDir = File(projectFilePath).parentFile        //dir where project located
            FileUtil.ensureExists(projectDir)

            val ideaDir = File(projectFilePath, Project.DIRECTORY_STORE_FOLDER)
            FileUtil.ensureExists(ideaDir)

            val newProject: Project? =
                       LangManager.getInstance().getLangSupport()!!.createProject(projectName, projectToClose);

            if (newProject == null) return projectToClose!!

            langSupport.applyProjectSdk(newProject)

            if (!ApplicationManager.getApplication().isUnitTestMode) {
                newProject.save()
            }

            if (moduleBuilder != null) {
                if (!moduleBuilder.validate(projectToClose, newProject)) {
                    return projectToClose
                }
            }

            //close previous project if needed
            if (newProject !== projectToClose && !ApplicationManager.getApplication().isUnitTestMode && projectToClose != null)
                NewProjectUtil.closePreviousProject(projectToClose)

            moduleBuilder?.commit(newProject, null, ModulesProvider.EMPTY_MODULES_PROVIDER)

            if (newProject !== projectToClose) {
                ProjectUtil.updateLastProjectLocation(projectFilePath)

                if (WindowManager.getInstance().isFullScreenSupportedInCurrentOS) {
                    val instance = IdeFocusManager.findInstance()
                    val lastFocusedFrame = instance.lastFocusedFrame
                    if (lastFocusedFrame is IdeFrameEx) {
                        val fullScreen = lastFocusedFrame.isInFullScreen
                        if (fullScreen) {
                            newProject.putUserData(IdeFrameImpl.SHOULD_OPEN_IN_FULL_SCREEN, java.lang.Boolean.TRUE)
                        }
                    }
                }
                if (ApplicationManager.getApplication().isUnitTestMode)
                    return newProject
                else
                    projectManager.openProject(newProject)
            }

            newProject.save()
            return newProject

        } finally {
            moduleBuilder?.cleanup()
        }
    }

    fun showDialogOpenLearnProject(project: Project): Boolean {
        //        final SdkProblemDialog dialog = new SdkProblemDialog(project, "at least JDK 1.6 or IDEA SDK with corresponding JDK");
        val dialog = LearnProjectWarningDialog(project)
        dialog.show()
        return dialog.isOK
    }


}
