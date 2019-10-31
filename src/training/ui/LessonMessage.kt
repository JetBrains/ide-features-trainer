/*
 * Copyright 2000-2019 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
 */
package training.ui

import java.util.*

internal class LessonMessage {

  val myMessages: ArrayList<Message> = ArrayList<Message>()
  var start: Int = 0
    private set
  var end: Int = 0
    private set
  var isPassed: Boolean = false

  constructor(text: String, start: Int, end: Int) {
    myMessages.add(Message(text, Message.MessageType.TEXT_REGULAR))
    this.start = start
    this.end = end
  }

  constructor(messages: Array<Message>, start: Int, end: Int) {
    myMessages.addAll(Arrays.asList(*messages))
    this.start = start
    this.end = end
  }
}
