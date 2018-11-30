package com.nikitvad.oryanmat.trellowidget.ui.board

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class ListsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getCount(): Int {
        return 3;
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItem(position: Int): Fragment {
        return CardsFragment()
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return "title $position"
    }
}