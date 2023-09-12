package com.example.demo

import com.intellij.codeInsight.inline.completion.DebouncedInlineCompletionProvider
import com.intellij.codeInsight.inline.completion.InlineCompletionElement
import com.intellij.codeInsight.inline.completion.InlineCompletionEvent
import com.intellij.codeInsight.inline.completion.InlineCompletionRequest
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlin.time.Duration.Companion.milliseconds

class DemoInlineCompletionProvider : DebouncedInlineCompletionProvider() {
    override val delay = 300.milliseconds
    override fun force(request: InlineCompletionRequest) = false
    override fun isEnabled(event: InlineCompletionEvent) = true

    override suspend fun getProposalsDebounced(request: InlineCompletionRequest): Flow<InlineCompletionElement> {
        return flow {
            emit("class DemoInlineCompletionProvider : DebouncedInlineCompletionProvider() {\n")
            delay(300)
            emit("    override val delay = 200.milliseconds\n")
            delay(300)
            emit("    override fun isEnabled(event: InlineCompletionEvent) = true")
            delay(300)
        }.map {
            InlineCompletionElement(it)
        }
    }
}
