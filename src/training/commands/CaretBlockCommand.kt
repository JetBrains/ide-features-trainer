package training.commands

import training.learn.lesson.LessonManager

/**
 * Created by karashevich on 30/01/15.
 */
class CaretBlockCommand : Command(Command.CommandType.CARETBLOCK) {

  override fun execute(executionList: ExecutionList) {
    executionList.elements.poll()
    //Block caret and perform next command
    LessonManager.instance.blockCaret(executionList.editor)
    startNextCommand(executionList)
  }
}
