package training.commands

import org.jetbrains.annotations.TestOnly

import java.util.concurrent.ExecutionException

class TestCommand : Command(Command.CommandType.TEST) {

  @TestOnly
  @Throws(InterruptedException::class, ExecutionException::class, BadCommandException::class)
  override fun execute(executionList: ExecutionList) {
    startNextCommand(executionList)
  }
}
