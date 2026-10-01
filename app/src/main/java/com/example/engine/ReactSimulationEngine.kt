package com.example.engine

import com.example.model.TerminalLog
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class SimulationState(
    val activeColumns: Int = 3, // 1 to 4 columns in the preview grid
    val activeTheme: String = "primary", // primary, success, danger, dark, info
    val cardTitle: String = "Bootstrap Responsive Card",
    val cardText: String = "Mastering 12-column grids and utility classes with Awiskar Acharya.",
    val counterValue: Int = 0,
    val todos: List<SimTodo> = listOf(
        SimTodo(1, "Link Bootstrap 5 CDN & Meta Viewport", true),
        SimTodo(2, "Master .container and .row layout", true),
        SimTodo(3, "Understand 12-column responsive classes (col-md-6)", false),
        SimTodo(4, "Build full responsive landing page", false)
    ),
    val logs: List<TerminalLog> = listOf(
        TerminalLog(System.currentTimeMillis() - 4000, "INFO", "Bootstrap 5.3.3 CSS loaded from CDN"),
        TerminalLog(System.currentTimeMillis() - 3000, "LOG", "12-Column Grid calculated: 12 columns / row"),
        TerminalLog(System.currentTimeMillis() - 1000, "LOG", "Responsive Breakpoint: md (≥768px)")
    ),
    val renderCount: Int = 1,
    val activeFileTab: String = "index.html"
)

data class SimTodo(
    val id: Long,
    val text: String,
    val done: Boolean
)

object ReactSimulationEngine {
    private val _state = MutableStateFlow(SimulationState())
    val state: StateFlow<SimulationState> = _state.asStateFlow()

    fun setColumns(cols: Int) {
        val nextRenders = _state.value.renderCount + 1
        val log = TerminalLog(
            System.currentTimeMillis(),
            "LOG",
            "[Layout #${nextRenders}] Grid updated: ${cols} columns per row (col-${12 / cols})"
        )
        _state.value = _state.value.copy(
            activeColumns = cols,
            renderCount = nextRenders,
            logs = (_state.value.logs + log).takeLast(40)
        )
    }

    fun setTheme(theme: String) {
        val nextRenders = _state.value.renderCount + 1
        val log = TerminalLog(
            System.currentTimeMillis(),
            "LOG",
            "[Theme #${nextRenders}] Applied utility classes: bg-$theme text-white btn-$theme"
        )
        _state.value = _state.value.copy(
            activeTheme = theme,
            renderCount = nextRenders,
            logs = (_state.value.logs + log).takeLast(40)
        )
    }

    fun toggleTodo(id: Long) {
        val nextRenders = _state.value.renderCount + 1
        val updated = _state.value.todos.map {
            if (it.id == id) it.copy(done = !it.done) else it
        }
        val target = updated.find { it.id == id }
        val log = TerminalLog(
            System.currentTimeMillis(),
            "LOG",
            "[DOM Update #${nextRenders}] Todo #${id} checked=${target?.done}"
        )
        _state.value = _state.value.copy(
            todos = updated,
            renderCount = nextRenders,
            logs = (_state.value.logs + log).takeLast(40)
        )
    }

    fun incrementCounter() {
        val next = _state.value.counterValue + 1
        val nextRenders = _state.value.renderCount + 1
        val log = TerminalLog(
            System.currentTimeMillis(),
            "LOG",
            "[State #${nextRenders}] React-Bootstrap <Button> clicked: count = $next"
        )
        _state.value = _state.value.copy(
            counterValue = next,
            renderCount = nextRenders,
            logs = (_state.value.logs + log).takeLast(40)
        )
    }

    fun clearConsole() {
        _state.value = _state.value.copy(logs = emptyList())
    }
}
