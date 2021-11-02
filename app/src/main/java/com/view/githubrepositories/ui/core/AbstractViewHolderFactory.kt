package com.view.githubrepositories.ui.core

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


/**
 * @author Master255 on 30.08.2021
 * masters@inbox.ru
 */

abstract class AbstractViewHolderFactory {

    protected fun Int.makeView(parent: ViewGroup) : View
        = LayoutInflater.from(parent.context).inflate(this,parent,false)
}