package com.abhijith.videoplaybackonrv.others;

import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;

import com.abhijith.videoplaybackonrv.player.Player;
import com.abhijith.videoplaybackonrv.util.misc.Preconditions;

/**
 * An internal {@link Player} holder.
 */
@RestrictTo(RestrictTo.Scope.LIBRARY)
public final class PlayerNode implements Comparable<PlayerNode> {


    private long lastAccessTime;
    private String key;
    private Player player;




    public PlayerNode(@NonNull Player player) {
        this(System.currentTimeMillis(), player);
    }




    public PlayerNode(long lastAccessTime, @NonNull Player player) {
        this.lastAccessTime = lastAccessTime;
        this.player = Preconditions.checkNonNull(player);
        this.key = "";
    }




    @NonNull
    public final com.abhijith.videoplaybackonrv.others.PlayerNode setLastAccessTime(long lastAccessTime) {
        this.lastAccessTime = lastAccessTime;
        return this;
    }




    public final long getLastAccessTime() {
        return this.lastAccessTime;
    }




    @NonNull
    public final com.abhijith.videoplaybackonrv.others.PlayerNode setPlayer(@Nullable Player player) {
        this.player = player;
        return this;
    }




    @Nullable
    public final Player getPlayer() {
        return this.player;
    }




    public final boolean hasPlayer() {
        return (this.player != null);
    }




    @NonNull
    public final com.abhijith.videoplaybackonrv.others.PlayerNode setKey(@NonNull String key) {
        this.key = Preconditions.checkNonNull(key);
        return this;
    }




    @NonNull
    public final com.abhijith.videoplaybackonrv.others.PlayerNode removeKey() {
        this.key = "";
        return this;
    }




    @NonNull
    public final String getKey() {
        return this.key;
    }




    public final boolean isKeySet() {
        return !TextUtils.isEmpty(this.key);
    }




    @Override
    public final int compareTo(@NonNull com.abhijith.videoplaybackonrv.others.PlayerNode otherNode) {
        if(this.lastAccessTime > otherNode.lastAccessTime) {
            return 1;
        } else if(this.lastAccessTime < otherNode.lastAccessTime) {
            return -1;
        }

        return 0;
    }




    @Override
    public final int hashCode() {
        return ((this.player != null) ? this.player.hashCode() : super.hashCode());
    }




    @Override
    public final boolean equals(Object obj) {
        return ((obj instanceof PlayerProviderImpl) && (hashCode() == obj.hashCode()));
    }




}
