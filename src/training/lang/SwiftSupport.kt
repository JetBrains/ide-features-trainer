package training.lang

import com.google.common.io.Files
import com.intellij.ide.impl.ProjectUtil
import com.intellij.ide.util.projectWizard.ModuleBuilder
import com.intellij.openapi.project.Project
import com.intellij.openapi.projectRoots.Sdk
import com.intellij.openapi.projectRoots.SdkTypeId
import training.util.ZipUtil


/**
 * @author Sergey Karashevich
 */
class SwiftSupport : LangSupport() {
    override fun getModuleBuilder(): ModuleBuilder? {
        return null
    }


    private val acceptableLanguages = setOf("swift")
    override fun acceptLang(ext: String) = acceptableLanguages.contains(ext.toLowerCase())
    override val FILE_EXTENSION: String
        get() = "swift"

    override fun applyProjectSdk(project: Project) {
    }

    override fun applyToProjectAfterConfigure(): (Project) -> Unit = {
    }


    override fun checkSdkCompatibility(sdk: Sdk, sdkTypeId: SdkTypeId) {
    }

    override fun needToCheckSDK(): Boolean {
        return false
    }

    override fun createProject(projectName: String, projectToClose: Project?): Project? {
        val path = getProjectFilePath("")
        ZipUtil.unzip("/data/modules/Projects/$FILE_EXTENSION/project.zip", path)
        return ProjectUtil.openOrImport(path +"/"+ projectName, null, false) //TODO: read/write actions sync
    }

    override fun getProjectFilePath(projectName: String): String {
//        return ProjectUtil.getBaseDir() + File.separator
        val tempDir = Files.createTempDir()
        return tempDir.absolutePath
    }
}