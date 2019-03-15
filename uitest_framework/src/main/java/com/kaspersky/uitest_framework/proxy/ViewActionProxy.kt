package com.kaspersky.uitest_framework.proxy

import android.support.test.espresso.UiController
import android.support.test.espresso.ViewAction
import android.view.View
import com.kaspersky.uitest_framework.interceptors.ViewActionInterceptor
import org.hamcrest.Matcher

class ViewActionProxy(
        private val viewAction: ViewAction,
        private val interceptors: List<ViewActionInterceptor>
) : ViewAction {

    override fun getDescription(): String = viewAction.description

    override fun getConstraints(): Matcher<View> = viewAction.constraints

    override fun perform(uiController: UiController, view: View) {

        interceptors.forEach { it.intercept(viewAction, view) }

        viewAction.perform(uiController, view)
    }
}