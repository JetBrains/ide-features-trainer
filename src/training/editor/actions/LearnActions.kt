package training.editor.actions

import org.jetbrains.annotations.NonNls

interface LearnActions {

  val actionId: String

  fun unregisterAction()

  companion object {
    @NonNls
    val LEARN_NEXT_LESSON_ACTION = "LearnNextLessonAction"
    val LEARN_BLOCK_EDITOR_CARET_ACTION = "LearnBlockEditorCaretAction"
    val LEARN_BLOCK_MOUSE_ACTION = "LearnBlockMouseAction"
  }
}
