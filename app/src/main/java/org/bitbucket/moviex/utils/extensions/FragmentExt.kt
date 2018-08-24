package org.bitbucket.moviex.utils.extensions

import androidx.fragment.app.Fragment
import org.bitbucket.moviex.App
import org.bitbucket.moviex.di.Injector

/**
 * Created by Emmanuel Victor Garcia
 */

val Fragment.app: App get() = activity!!.application as App

fun Fragment.getAppInjector(): Injector = (app).injector