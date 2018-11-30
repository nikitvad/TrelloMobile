package com.nikitvad.oryanmat.trellowidget.ui.boards

import android.databinding.Bindable
import com.nikitvad.oryanmat.trellowidget.*
import com.nikitvad.oryanmat.trellowidget.data.Api
import com.nikitvad.oryanmat.trellowidget.databinding.LayoutBoardItemBinding
import com.nikitvad.oryanmat.trellowidget.old.model.Board
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class BoardsViewModel(val api: Api) : BaseViewModel<BoardsNavigator>() {

    var boards: List<Board>? = null

    override fun onBind() {
        api.getBoards().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    boards = it.body()
                    notifyPropertyChanged(BR.adapter);
                }
    }

    @Bindable
    fun getAdapter(): NiceRecyclerAdapter<LayoutBoardItemBinding, Board> {

        var adapter: NiceRecyclerAdapter<LayoutBoardItemBinding, Board>

        if (boards != null) {
            adapter = NiceRecyclerAdapter<LayoutBoardItemBinding, Board>(boards!!, R.layout.layout_board_item,
                    object : OnItemClickListener<Board> {
                        override fun onItemClick(pos: Int, item: Board) {
                            navigator?.openBoard(item)
                        }

                    })
        } else {
            adapter = NiceRecyclerAdapter<LayoutBoardItemBinding, Board>(emptyList(), R.layout.layout_board_item,
                    object : OnItemClickListener<Board> {
                        override fun onItemClick(pos: Int, item: Board) {
                            navigator?.openBoard(item)
                        }

                    })
        }
        return adapter
    }

}