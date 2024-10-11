package br.com.asoncs.multi.passwords.extension

import org.w3c.dom.Window

val Window.isMobile: Boolean
    get() = "(Android)|(webOS)|(iPhone)|(iPad)|(iPod)|(BlackBerry)|(Windows Phone)"
        .toRegex(RegexOption.IGNORE_CASE)
        .containsMatchIn(navigator.userAgent)
