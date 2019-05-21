package training.commands

import training.learn.lesson.LessonManager

/**
 * Created by karashevich on 30/01/15.
 */
class CaretUnblockCommand : Command(Command.CommandType.CARETUNBLOCK) {

  override fun execute(executionList: ExecutionList) {
    executionList.elements.poll()
    //Unblock caret and perform next command
    LessonManager.instance.unblockCaret()
    startNextCommand(executionList)

  }
}
