package com.nikitvad.oryanmat.trellowidget.ui.main

import android.app.Fragment
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import com.nikitvad.oryanmat.trellowidget.R
import com.nikitvad.oryanmat.trellowidget.data.Api
import com.nikitvad.oryanmat.trellowidget.old.activity.LoggedInFragment
import com.nikitvad.oryanmat.trellowidget.old.activity.LoginFragment
import com.nikitvad.oryanmat.trellowidget.ui.boards.BoardsActivity
import com.nikitvad.oryanmat.trellowidget.util.PreferencesUtil
import com.nikitvad.oryanmat.trellowidget.util.TOKEN_PREF_KEY
import com.nikitvad.oryanmat.trellowidget.util.preferences
import dagger.android.DaggerActivity
import javax.inject.Inject

class MainActivity : DaggerActivity() {

    @Inject
    lateinit var preferencesUtil: PreferencesUtil

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userToken = preferences().getString(TOKEN_PREF_KEY, "")

        if(!TextUtils.isEmpty(userToken)){
            val intent = Intent(this, BoardsActivity::class.java)
            startActivity(intent)
        }

    }

    fun startBrowserWithAuthURL(view: View) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(Api.AUTH_URL))

        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        if (intent.action == Intent.ACTION_VIEW) {
            saveUserToken(intent)
            preferencesUtil.saveToken(intent.data.fragment.replace("token=", ""))
            Log.d("dfsdfsdf", ": " + preferencesUtil.getToken());
        }
    }

    private fun saveUserToken(intent: Intent) {
        preferences().edit()
                .putString(TOKEN_PREF_KEY, intent.data.fragment)
                .apply()

        replaceFragment(LoggedInFragment())

        val intent = Intent(this, BoardsActivity::class.java)
        startActivity(intent)
    }

    @JvmOverloads
    fun logout(view: View? = null) {
        preferences().edit()
                .remove(TOKEN_PREF_KEY)
                .apply()

        replaceFragment(LoginFragment())
    }

    private fun replaceFragment(fragment: Fragment) = fragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
}