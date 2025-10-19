package com.hasoftware.ustore.ui.animations

import androidx.compose.animation.core.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer

/**
 * Animation utilities for the Stature Clothing App
 */

@Composable
fun rememberFadeInAnimation(
    delay: Int = 0,
    duration: Int = 1000
): Float {
    val alpha by animateFloatAsState(
        targetValue = 1f,
        animationSpec = tween(
            durationMillis = duration,
            delayMillis = delay,
            easing = EaseOutCubic
        ),
        label = "fade_in"
    )
    return alpha
}

@Composable
fun rememberSlideInFromBottomAnimation(
    delay: Int = 0,
    duration: Int = 800
): Float {
    val offsetY by animateFloatAsState(
        targetValue = 0f,
        animationSpec = tween(
            durationMillis = duration,
            delayMillis = delay,
            easing = EaseOutCubic
        ),
        label = "slide_in_bottom"
    )
    return offsetY
}

@Composable
fun rememberSlideInFromTopAnimation(
    delay: Int = 0,
    duration: Int = 800
): Float {
    val offsetY by animateFloatAsState(
        targetValue = 0f,
        animationSpec = tween(
            durationMillis = duration,
            delayMillis = delay,
            easing = EaseOutCubic
        ),
        label = "slide_in_top"
    )
    return offsetY
}

@Composable
fun rememberScaleAnimation(
    delay: Int = 0,
    duration: Int = 600
): Float {
    val scale by animateFloatAsState(
        targetValue = 1f,
        animationSpec = tween(
            durationMillis = duration,
            delayMillis = delay,
            easing = EaseOutBack
        ),
        label = "scale"
    )
    return scale
}

@Composable
fun rememberBounceAnimation(
    delay: Int = 0,
    duration: Int = 1000
): Float {
    val bounce by animateFloatAsState(
        targetValue = 1f,
        animationSpec = tween(
            durationMillis = duration,
            delayMillis = delay,
            easing = EaseOutBounce
        ),
        label = "bounce"
    )
    return bounce
}

// Animation modifiers - These need to be used inside @Composable functions
@Composable
fun Modifier.fadeIn(
    delay: Int = 0,
    duration: Int = 1000
): Modifier {
    val alpha = rememberFadeInAnimation(delay, duration)
    return this.graphicsLayer {
        this.alpha = alpha
    }
}

@Composable
fun Modifier.slideInFromBottom(
    delay: Int = 0,
    duration: Int = 800
): Modifier {
    val translationY = rememberSlideInFromBottomAnimation(delay, duration) + 100f
    val alpha = rememberFadeInAnimation(delay, duration)
    return this.graphicsLayer {
        this.translationY = translationY
        this.alpha = alpha
    }
}

@Composable
fun Modifier.slideInFromTop(
    delay: Int = 0,
    duration: Int = 800
): Modifier {
    val translationY = rememberSlideInFromTopAnimation(delay, duration) - 100f
    val alpha = rememberFadeInAnimation(delay, duration)
    return this.graphicsLayer {
        this.translationY = translationY
        this.alpha = alpha
    }
}

@Composable
fun Modifier.scaleIn(
    delay: Int = 0,
    duration: Int = 600
): Modifier {
    val scale = rememberScaleAnimation(delay, duration)
    val alpha = rememberFadeInAnimation(delay, duration)
    return this.graphicsLayer {
        this.scaleX = scale
        this.scaleY = scale
        this.alpha = alpha
    }
}

@Composable
fun Modifier.bounceIn(
    delay: Int = 0,
    duration: Int = 1000
): Modifier {
    val scale = rememberBounceAnimation(delay, duration)
    val alpha = rememberFadeInAnimation(delay, duration)
    return this.graphicsLayer {
        this.scaleX = scale
        this.scaleY = scale
        this.alpha = alpha
    }
}

