package com.abhijith.videoplaybackonrv.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.abhijith.videoplaybackonrv.others.PlayerProviderImpl
import com.abhijith.videoplaybackonrv.util.misc.CollectionUtils
import com.abhijith.videoplaybackonrv.util.misc.Preconditions
import com.abhijith.videoplaybackonrv.widget.PlayableItemsContainer.AutoplayMode
import com.abhijith.videoplaybackonrv.widget.PlayableItemsContainer.PlaybackTriggeringState
import java.util.*

/**
 * A concrete implementation of the [PlayableItemsContainer] based on the [RecyclerView]
 * which provides the management of the items' playbacks.
 */
class PlayableItemsRecyclerView : RecyclerView, PlayableItemsContainer {
    private val mPlaybackTriggeringStates: MutableSet<PlaybackTriggeringState> = HashSet()
    private var mPreviousScrollDeltaX = 0
    private var mPreviousScrollDeltaY = 0
    private var mAutoplayMode: AutoplayMode? = null
    private var mIsAutoplayEnabled = false
    private var mIsScrolling = false

    constructor(context: Context?) : super(context!!) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(
        context!!, attrs
    ) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(
        context!!, attrs, defStyle
    ) {
        init()
    }

    private fun init() {
        mPreviousScrollDeltaX = 0
        mPreviousScrollDeltaY = 0
        mAutoplayMode = AutoplayMode.ONE_AT_A_TIME
        mIsAutoplayEnabled = true
        mPlaybackTriggeringStates.addAll(DEFAULT_PLAYBACK_TRIGGERING_STATES)
        setRecyclerListener { holder: ViewHolder -> onRecyclerViewViewRecycled(holder) }
    }

    override fun startPlayback() {
        handleItemPlayback(true)
    }

    override fun stopPlayback() {
        stopItemPlayback()
    }

    override fun pausePlayback() {
        pauseItemPlayback()
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        startPlayback()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        releaseAllItems()
    }

    val playableAttachedCandidates = mutableListOf<PlayableItemViewHolder>()

    fun setMute(boolean: Boolean){
        playableAttachedCandidates.forEach {
            it.isMuted = boolean
        }
    }

    override fun onChildAttachedToWindow(child: View) {
        super.onChildAttachedToWindow(child)
        val viewHolder = getChildViewHolder(child)
        if (viewHolder !is Playable) {
            return
        }
        if (viewHolder is PlayableItemViewHolder)
            playableAttachedCandidates.add(viewHolder)
        // if necessary, reattaching the active player to the Playable
        val playable = viewHolder as Playable
        if (PlayerProviderImpl.getInstance(context).hasPlayer(playable.config, playable.key)) {
            val player =
                PlayerProviderImpl.getInstance(context).getPlayer(playable.config, playable.key)
            if (player != null && player.isPlaying && playable.wantsToPlay()) {
                playable.start()
            }
        }
    }

    override fun onChildDetachedFromWindow(child: View) {
        super.onChildDetachedFromWindow(child)
        val viewHolder = getChildViewHolder(child)
        if (viewHolder !is Playable) {
            return
        }
        if (viewHolder is PlayableItemViewHolder)
            playableAttachedCandidates.remove(viewHolder)
        // releasing the associated player (Playable-wise) and other resources
        val playable = viewHolder as Playable
        playable.release()
    }

    override fun onResume() {
        startPlayback()
    }

    override fun onPause() {
        pausePlayback()
    }

    override fun onDestroy() {
        releaseAllItems()
    }

    private fun onRecyclerViewViewRecycled(holder: ViewHolder) {
        if (holder !is Playable) {
            return
        }
        val playable = holder as Playable
        if (playable.wantsToPlay()) {
            return
        }
        playable.release()
    }

    private fun handleItemPlayback(allowPlay: Boolean) {
        val playableItems: MutableList<Playable> = ArrayList()
        val childCount = childCount
        val canHaveMultipleActiveItems = AutoplayMode.MULTIPLE_SIMULTANEOUSLY == mAutoplayMode
        var viewHolder: ViewHolder?
        var isInPlayableArea: Boolean
        var hasActiveItem = false

        // extracting all the playable visible items
        for (i in 0 until childCount) {
            viewHolder = findContainingViewHolder(getChildAt(i))
            if (viewHolder is Playable
                && (viewHolder as Playable).isTrulyPlayable
            ) {
                playableItems.add(viewHolder as Playable)
            }
        }

        // processing the extracted Playable items
        for (playable in playableItems) {
            isInPlayableArea = playable.wantsToPlay()

            // handling the playback state
            if (isInPlayableArea && (!hasActiveItem || canHaveMultipleActiveItems)) {
                if (!playable.isPlaying
                    && mIsAutoplayEnabled
                    && allowPlay
                ) {
                    playable.start()
                }
                hasActiveItem = true
            } else if (playable.isPlaying) {
                playable.pause()
            }
            playable.onPlayabilityStateChanged(isInPlayableArea)
        }
    }

    private fun stopItemPlayback() {
        val childCount = childCount
        var viewHolder: ViewHolder?
        for (i in 0 until childCount) {
            viewHolder = findContainingViewHolder(getChildAt(i))
            if (viewHolder is Playable
                && (viewHolder as Playable).isTrulyPlayable
            ) {
                (viewHolder as Playable).stop()
            }
        }
    }

    private fun pauseItemPlayback() {
        val childCount = childCount
        var viewHolder: ViewHolder?
        for (i in 0 until childCount) {
            viewHolder = findContainingViewHolder(getChildAt(i))
            if (viewHolder is Playable
                && (viewHolder as Playable).isTrulyPlayable
            ) {
                (viewHolder as Playable).pause()
            }
        }
    }

    private fun releaseAllItems() {
        val childCount = childCount
        var viewHolder: ViewHolder?
        for (i in 0 until childCount) {
            viewHolder = findContainingViewHolder(getChildAt(i))
            if (viewHolder is Playable
                && (viewHolder as Playable).isTrulyPlayable
            ) {
                (viewHolder as Playable).release()
            }
        }
    }

    override fun setAutoplayMode(autoplayMode: AutoplayMode) {
        mAutoplayMode = Preconditions.checkNonNull(autoplayMode)
        if (isAutoplayEnabled) {
            startPlayback()
        }
    }

    override fun getAutoplayMode(): AutoplayMode {
        return mAutoplayMode!!
    }

    override fun setPlaybackTriggeringStates(vararg states: PlaybackTriggeringState) {
        Preconditions.nonNull(states)
        mPlaybackTriggeringStates.clear()
        mPlaybackTriggeringStates.addAll(
            if (states.size == 0) DEFAULT_PLAYBACK_TRIGGERING_STATES else CollectionUtils.hashSetOf(
                *states
            )
        )
    }

    override fun getPlaybackTriggeringStates(): Set<PlaybackTriggeringState> {
        return mPlaybackTriggeringStates
    }

    private fun getPlaybackStateForScrollState(scrollState: Int): PlaybackTriggeringState {
        return when (scrollState) {
            SCROLL_STATE_SETTLING -> PlaybackTriggeringState.SETTLING
            SCROLL_STATE_DRAGGING -> PlaybackTriggeringState.DRAGGING
            else -> PlaybackTriggeringState.IDLING
        }
    }

    override fun setAutoplayEnabled(isAutoplayEnabled: Boolean) {
        mIsAutoplayEnabled = isAutoplayEnabled
        if (isAutoplayEnabled) {
            startPlayback()
        } else {
            stopPlayback()
        }
    }

    override fun isAutoplayEnabled(): Boolean {
        return mIsAutoplayEnabled
    }

    override fun onScrollStateChanged(state: Int) {
        super.onScrollStateChanged(state)
        handleItemPlayback(canPlay())
    }

    override fun onScrolled(dx: Int, dy: Int) {
        super.onScrolled(dx, dy)
        mIsScrolling =
            Math.abs(mPreviousScrollDeltaX - dx) > 0 || Math.abs(mPreviousScrollDeltaY - dy) > 0
        handleItemPlayback(canPlay())
        mPreviousScrollDeltaX = dx
        mPreviousScrollDeltaY = dy
    }

    private fun canPlay(): Boolean {
        val state = getPlaybackStateForScrollState(scrollState)
        val containsState = mPlaybackTriggeringStates.contains(state)
        val isDragging = PlaybackTriggeringState.DRAGGING == state && !mIsScrolling
        val isSettling = PlaybackTriggeringState.SETTLING == state
        val isIdling = PlaybackTriggeringState.IDLING == state
        return containsState && (isDragging || isSettling || isIdling)
    }

    companion object {
        private val DEFAULT_PLAYBACK_TRIGGERING_STATES = CollectionUtils.hashSetOf(
            PlaybackTriggeringState.DRAGGING,
            PlaybackTriggeringState.IDLING
        )
    }
}