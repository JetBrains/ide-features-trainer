package training.commands

import training.learn.lesson.LessonManager

/**
 * Created by karashevich on 30/01/15.
 */
class WinCommand : Command(Command.CommandType.WIN) {

  @Throws(InterruptedException::class)
  override fun execute(executionList: ExecutionList) {

    executionList.elements.poll()

    val project = executionList.project
    val lesson = executionList.lesson
    lesson.pass()
    LessonManager.instance.passLesson(project, lesson)
  }
}
