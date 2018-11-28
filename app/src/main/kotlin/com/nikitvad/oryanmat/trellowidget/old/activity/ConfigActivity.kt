package com.nikitvad.oryanmat.trellowidget.old.activity

import android.app.Activity
import android.appwidget.AppWidgetManager.EXTRA_APPWIDGET_ID
import android.appwidget.AppWidgetManager.INVALID_APPWIDGET_ID
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.android.volley.Response
import com.android.volley.VolleyError
import com.nikitvad.oryanmat.trellowidget.R
import com.nikitvad.oryanmat.trellowidget.T_WIDGET
import com.nikitvad.oryanmat.trellowidget.old.model.Board
import com.nikitvad.oryanmat.trellowidget.old.model.BoardList
import com.nikitvad.oryanmat.trellowidget.old.model.BoardList.Companion.BOARD_LIST_TYPE
import com.nikitvad.oryanmat.trellowidget.util.*
import com.nikitvad.oryanmat.trellowidget.old.widget.updateWidget
import com.nikitvad.oryanmat.trellowidget.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_config.*

class ConfigActivity : Activity(), OnItemSelectedAdapter, Response.Listener<String>, Response.ErrorListener {
    private var appWidgetId = INVALID_APPWIDGET_ID
    private var board: Board = Board()
    private var list: BoardList = BoardList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_config)
        setWidgetId()
        get(TrelloAPIUtil.instance.boards(), this)
    }

    private fun setWidgetId() {
        if (intent.extras != null) {
            appWidgetId = intent.extras.getInt(EXTRA_APPWIDGET_ID, INVALID_APPWIDGET_ID)
        }

        if (appWidgetId == INVALID_APPWIDGET_ID) {
            finish()
        }
    }

    private fun get(url: String, listener: ConfigActivity) =
            TrelloAPIUtil.instance.getAsync(url, listener, listener)

    override fun onResponse(response: String) {
        progressBar.visibility = View.GONE
        content.visibility = View.VISIBLE
        val boards = Json.tryParseJson(response, BOARD_LIST_TYPE, emptyList<Board>())
        board = getBoard(appWidgetId)
        setSpinner(boardSpinner, boards, this, boards.indexOf(board))
    }

    override fun onErrorResponse(error: VolleyError) {
        finish()

        Log.e(T_WIDGET, error.toString())
        val text = getString(R.string.board_load_fail)
        Toast.makeText(this, text, Toast.LENGTH_LONG).show()
        startActivity(Intent(this, MainActivity::class.java))
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
        when (parent) {
            boardSpinner -> boardSelected(parent, position)
            listSpinner -> list = parent.getItemAtPosition(position) as BoardList
        }
    }

    private fun boardSelected(spinner: AdapterView<*>, position: Int) {
        board = spinner.getItemAtPosition(position) as Board
        list = getList(appWidgetId)
        setSpinner(listSpinner, board.lists, this, board.lists.indexOf(list))
    }

    private fun <T> setSpinner(spinner: Spinner, lists: List<T>,
                               listener: AdapterView.OnItemSelectedListener, selectedIndex: Int): Spinner {
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, lists)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
        spinner.onItemSelectedListener = listener
        spinner.setSelection(if (selectedIndex > -1) selectedIndex else 0)
        return spinner
    }

    fun ok(view: View) {
        if (board.id.isEmpty() || list.id.isEmpty()) return
        putConfigInfo(appWidgetId, board, list)
        updateWidget(appWidgetId)
        returnOk()
    }

    private fun returnOk() {
        val resultValue = Intent()
        resultValue.putExtra(EXTRA_APPWIDGET_ID, appWidgetId)
        setResult(Activity.RESULT_OK, resultValue)
        finish()
    }

    fun cancel(view: View) = finish()
}