package training.learn.exceptons

import com.intellij.openapi.projectRoots.JavaSdkVersion

class OldJdkException : InvalidSdkException {

  constructor(s: String) : super(s) {}

  constructor(javaSdkVersion: JavaSdkVersion) : super(" Old Java SDK version for Project SDK.") {}

  constructor(javaSdkVersion: JavaSdkVersion, atLeastVersion: JavaSdkVersion) : super(
    " Old Java SDK version for Project SDK. Please use version " + atLeastVersion.toString()) {
  }
}
