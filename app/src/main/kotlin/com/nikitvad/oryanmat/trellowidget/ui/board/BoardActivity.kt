package com.nikitvad.oryanmat.trellowidget.ui.board

import android.os.Bundle
import android.util.Log
import com.google.gson.Gson
import com.nikitvad.oryanmat.trellowidget.BaseActivity
import com.nikitvad.oryanmat.trellowidget.R
import com.nikitvad.oryanmat.trellowidget.databinding.ActivityBoardBinding
import com.nikitvad.oryanmat.trellowidget.old.model.Board
import javax.inject.Inject

class BoardActivity : BaseActivity<BoardViewModel, ActivityBoardBinding>() {

    companion object {
        val KEY_BOARD = "board_key"
    }

    @Inject
    lateinit var boardViewModel: BoardViewModel;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val json = intent.getStringExtra(KEY_BOARD)

        val board = Gson().fromJson<Board>(json, Board::class.java)

        Log.d("sdfsdfsdf", board.toString())

        viewBinding.lists.adapter = ListsPagerAdapter(supportFragmentManager)
        viewBinding.tabs.setupWithViewPager(viewBinding.lists)

    }

    override fun getLayoutId(): Int {
        return R.layout.activity_board
    }

    override fun getViewModel(): BoardViewModel {
        return boardViewModel
    }
}
